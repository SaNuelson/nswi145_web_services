package saajcalc;

import javax.xml.namespace.QName;
import javax.xml.soap.*;

public class SaajCalculator {

    private SaajCalculator() {}

    public static SOAPMessage getOpSoap(String op, int a, int b) throws SOAPException {
        MessageFactory msgFac = MessageFactory.newInstance();
        SOAPMessage msg = msgFac.createMessage();

        // dig down to get body
        SOAPPart soapPart = msg.getSOAPPart();
        SOAPEnvelope soapEnv = soapPart.getEnvelope();
        SOAPBody soapBody = soapEnv.getBody();

        // get rid of header
        soapEnv.getHeader().detachNode();

        QName opQName = new QName("http://tempuri.org/", op, "temp");
        SOAPElement soapEl = soapBody.addBodyElement(opQName);

        QName aQName = new QName("http://tempuri.org/", "intA", "temp");
        soapEl.addChildElement(aQName).addTextNode(String.valueOf(a));

        QName bQName = new QName("http://tempuri.org/", "intB", "temp");
        soapEl.addChildElement(bQName).addTextNode(String.valueOf(b));

        return msg;
    }

    public static SOAPMessage sendOpSoap(String op, int a, int b) throws SOAPException {
        SOAPConnectionFactory soapConnFac = SOAPConnectionFactory.newInstance();
        SOAPConnection conn = soapConnFac.createConnection();

        SOAPMessage msg = getOpSoap(op, a, b);

        String endpoint = "http://www.dneonline.com/calculator.asmx";

        return conn.call(msg, endpoint);
    }

    public static SOAPMessage sendSoapMsg(SOAPMessage msg) throws SOAPException {
        SOAPConnectionFactory soapConnFac = SOAPConnectionFactory.newInstance();
        SOAPConnection conn = soapConnFac.createConnection();

        String endpoint = "http://www.dneonline.com/calculator.asmx";

        return conn.call(msg, endpoint);
    }

    public static String parseSoapMsg(SOAPMessage msg, String op) throws SOAPException{
        SOAPBody body = msg.getSOAPBody();

        if (body.hasFault()) {
            System.out.println(body.getFault().getFaultString());
        }

        QName responseQName = new QName("http://tempuri.org/", op + "Response");
        SOAPBodyElement responseEl = (SOAPBodyElement) body.getChildElements(responseQName).next();

        QName resultQName = new QName("http://tempuri.org/", op + "Result");
        SOAPBodyElement resultEl = (SOAPBodyElement) responseEl.getChildElements(resultQName).next();

        return resultEl.getValue();
    }

    public static int Add(int a, int b) throws SOAPException {
        var msg = sendOpSoap("Add", a, b);
        return Integer.parseInt(parseSoapMsg(msg, "Add"));
    }
}
