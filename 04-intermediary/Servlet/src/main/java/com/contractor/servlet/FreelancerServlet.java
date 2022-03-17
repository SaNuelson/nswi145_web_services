package com.contractor.servlet;

import com.contractor.shared.Constants;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.soap.*;

/**
 * Servlet for accessing freelancer data on the server.
 * Currently supports augmenting GetFreelancerInfo operation.
 * Namely, it adds privacy by hiding personal information about the freelancer.
 * Servlet is triggered by adding following tag into the SOAP body:
 * &lt;privacy xlmns="http://servlet.privacy/" level="full" /&gt;
 * where level can be one of:
 * - "full" - all personal information is fully replaced
 * - "partial" - information is partially hidden (e.g. "John Doe" gets replace by "John D.")
 * - "none" - equivalent to ommiting the tag completely, keeps the message unchanged
 */

@WebServlet(name = "freelancerServlet", value = "/freelancer-servlet")
public class FreelancerServlet extends HttpServlet {

    private final MessageFactory messageFactory;
    private final SOAPConnection connection;

    public FreelancerServlet() throws SOAPException {
        messageFactory = MessageFactory.newInstance();
        connection = SOAPConnectionFactory.newInstance().createConnection();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            SOAPMessage message = messageFactory.createMessage(null, request.getInputStream());
            SOAPBody body = message.getSOAPPart().getEnvelope().getBody();

            boolean isFlcInfoRequest = body.getChildElements(Constants.QNames.GET_FLC_INFO).hasNext();
            boolean isFlcByJobRequest = body.getChildElements(Constants.QNames.GET_BY_JOB_TYPE).hasNext();

            if (isFlcByJobRequest && isFlcInfoRequest) {
                throw new SOAPException("Incoming SOAP contains both GetByJobType and GetFreelancerInfo elements.");
            }

            SOAPMessage responseMessage = null;

            if (isFlcByJobRequest) {
                responseMessage = handleGetByJobTypeRequest(message);
            }

            if (isFlcInfoRequest) {
                responseMessage = handleGetFlcInfoRequest(message);
            }

            if (responseMessage != null) {
                responseMessage.writeTo(response.getOutputStream());
                return;
            }

            throw new SOAPException("Incoming SOAP doesn't contain any of the supported elements.");
        }
        catch (Exception ignored) { }

        try {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private SOAPMessage handleGetByJobTypeRequest(SOAPMessage requestMessage) throws SOAPException {
        // Not implemented, send forward and back unchanged
        return connection.call(requestMessage, Constants.CONTRACTOR_FREELANCER_ENDPOINT);
    }

    private SOAPMessage handleGetFlcInfoRequest(SOAPMessage requestMessage) throws SOAPException {

        // check for the privacy tag
        SOAPBody requestBody = requestMessage.getSOAPPart().getEnvelope().getBody();
        boolean hasPrivacyElement = requestBody.getChildElements(Constants.QNames.SERVLET_PRIVACY).hasNext();

        // in case it's missing, send forward and back unchanged
        if (!hasPrivacyElement) {
            return connection.call(requestMessage, Constants.CONTRACTOR_FREELANCER_ENDPOINT);
        }

        // otherwise detach, send and afterwards augment
        SOAPElement privacyElement = (SOAPElement) requestBody.getChildElements(Constants.QNames.SERVLET_PRIVACY).next();
        privacyElement.detachNode();
        SOAPMessage responseMessage = connection.call(requestMessage, Constants.CONTRACTOR_FREELANCER_ENDPOINT);

        String privacyLevel = privacyElement.getAttributeValue(Constants.QNames.LEVEL);

        SOAPBody responseBody = responseMessage.getSOAPPart().getEnvelope().getBody();
        SOAPElement privacyResElement = responseBody.addChildElement(Constants.QNames.SERVLET_PRIVACY);

        // case "none", no change
        if (Objects.equals(privacyLevel, "none")) {
            privacyResElement.setTextContent("None privacy provided.");
            return responseMessage;
        }
        SOAPElement flcInfoResElement = (SOAPElement) responseBody.getChildElements(Constants.QNames.GET_FLC_INFO_RES).next();
        SOAPElement flcInfoDetailsElement = (SOAPElement) flcInfoResElement.getChildElements(Constants.QNames.FLC_INFO).next();
        SOAPElement nameElement = (SOAPElement) flcInfoDetailsElement.getChildElements(Constants.QNames.NAME).next();
        String freelancerName = nameElement.getValue();
        switch(privacyLevel) {
            case "partial":
                String partiallyCensoredName = partiallyCensor(freelancerName);
                nameElement.setTextContent(partiallyCensoredName);
                privacyResElement.setTextContent("Partial privacy provided.");
                return responseMessage;
            case "full":
                String fullyCensoredName = freelancerName.replaceAll("[a-zA-Z]","*");
                nameElement.setTextContent(fullyCensoredName);
                privacyResElement.setTextContent("Full privacy provided.");
                return responseMessage;
            default:
                throw new SOAPException("Invalid value provided for privacy[@level].");
        }
    }

    private static String partiallyCensor(String name) {
        String[] tokens = name.split(" ");
        if (tokens.length == 0) {
            return "";
        }

        String ret = tokens[0];
        for (int i = 1; i < tokens.length; i++) {
            if (Character.isUpperCase(tokens[i].charAt(0))) {
                ret += " " + tokens[i].charAt(0) + ".";
            }
        }
        return ret;
    }

}