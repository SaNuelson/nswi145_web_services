package exercise.servlet.servlettest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/*
* SoapUI - send to http://localhost:8080/ServletTest_war_exploded/TestServlet
<soapenv:Envelope
xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
xmlns:tem="http://tempuri.org/"
xmlns:tw="http://tweaks.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <tw:tweak multiplicationFactor="11" />
      <tem:Add>
         <tem:intA>2</tem:intA>
         <tem:intB>3</tem:intB>
      </tem:Add>
   </soapenv:Body>
</soapenv:Envelope>
*/

/* Get response
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope
xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"
xmlns:xsd="http://www.w3.org/2001/XMLSchema"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
   <soap:Body>
      <AddResponse xmlns="http://tempuri.org/">
         <AddResult>
            55
         </AddResult>
      </AddResponse>
      <tweaked xmlns="http://tweaks.com/">
         Multiplied by: 11
      </tweaked>
   </soap:Body>
</soap:Envelope>
*/

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage message = messageFactory.createMessage(null, request.getInputStream());
            SOAPElement tweakElement = (SOAPElement) message
                    .getSOAPPart()
                    .getEnvelope()
                    .getBody()
                    .getChildElements(new QName("http://tweaks.com/", "tweak"))
                    .next();

            log("SXSXAFAGE");
            if (tweakElement == null) {
                System.out.println("Error: post SOAP body doesn't contain a <tweak> element.");
                response.sendError(400);
                return;
            }

            String tweakValue = tweakElement.getAttributeNode("multiplicationFactor").getValue();
            tweakElement.detachNode();

            SOAPConnectionFactory soapConnFac = SOAPConnectionFactory.newInstance();
            SOAPConnection conn = soapConnFac.createConnection();

            String endpoint = "http://www.dneonline.com/calculator.asmx";
            SOAPMessage calcResponse = conn.call(message, endpoint);

            SOAPBody calcBody = calcResponse.getSOAPBody();

            if (calcBody.hasFault()) {
                System.out.println(calcBody.getFault().getFaultString());
                response.sendError(400);
                return;
            }

            QName responseQName = new QName("http://tempuri.org/", "AddResponse");
            SOAPBodyElement responseEl = (SOAPBodyElement) calcBody.getChildElements(responseQName).next();

            QName resultQName = new QName("http://tempuri.org/", "AddResult");
            SOAPBodyElement resultEl = (SOAPBodyElement) responseEl.getChildElements(resultQName).next();

            String calcValue = resultEl.getValue();

            int tweakInt = Integer.parseInt(tweakValue);
            int calcInt = Integer.parseInt(calcValue);
            int mulInt = tweakInt * calcInt;
            String tweakString = String.valueOf(tweakInt);
            String mulString = String.valueOf(mulInt);

            calcBody.addBodyElement(new QName("http://tweaks.com/", "tweaked")).setTextContent("Multiplied by: " + tweakString);
            resultEl.setTextContent(mulString);

            calcResponse.writeTo(response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
