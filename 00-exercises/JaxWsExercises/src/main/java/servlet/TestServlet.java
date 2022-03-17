package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import saajcalc.SaajCalculator;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.IOException;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage message = messageFactory.createMessage(null, request.getInputStream());
            var tweakElement = message
                    .getSOAPPart()
                    .getEnvelope()
                    .getBody()
                    .getChildElements(new QName("tweak"))
                    .next();

            if (tweakElement == null) {
                System.out.println("Error: post SOAP body doesn't contain a <tweak> element.");
                response.setStatus(400);
                return;
            }

            var tweakValue = tweakElement.getValue();
            tweakElement.detachNode();

            SOAPMessage calcResponse = SaajCalculator.sendSoapMsg(message);

            SOAPBody calcBody = calcResponse.getSOAPBody();

            if (calcBody.hasFault()) {
                System.out.println(calcBody.getFault().getFaultString());
                response.setStatus(400);
                return;
            }

            QName responseQName = new QName("http://tempuri.org/", "AddResponse");
            SOAPBodyElement responseEl = (SOAPBodyElement) calcBody.getChildElements(responseQName).next();

            QName resultQName = new QName("http://tempuri.org/", "AddResult");
            SOAPBodyElement resultEl = (SOAPBodyElement) responseEl.getChildElements(resultQName).next();

            String calcValue = resultEl.getValue();

            var tweakInt = Integer.parseInt(tweakValue);
            var calcInt = Integer.parseInt(calcValue);
            var mulInt = tweakInt * calcInt;
            var mulString = String.valueOf(mulInt);

            calcBody.addBodyElement(new QName("http://tempuri.org/", "tweaked")).setTextContent(mulString);

            calcResponse.writeTo(response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
