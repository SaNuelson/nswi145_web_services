package proxy;

import util.Constants;
import util.FreelancerInfo;

import interfaces.IContractAccessPoint;
import interfaces.IFreelancerAccessPoint;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SaajContractorClient implements ClientProxy {

    private final SOAPConnection conn;
    private final MessageFactory msgFactory;

    private IFreelancerAccessPoint freelancerProxy;
    private IContractAccessPoint contractProxy;

    public SaajContractorClient() throws SOAPException {
        conn = SOAPConnectionFactory.newInstance().createConnection();
        msgFactory = MessageFactory.newInstance();

        freelancerProxy = new FreelancerAPProxy(conn, msgFactory);
        contractProxy = new ContractAPProxy(conn, msgFactory);

    }

    @Override
    public IFreelancerAccessPoint GetFreelancerProxy() {
        return freelancerProxy;
    }

    @Override
    public IContractAccessPoint GetContractProxy() {
        return contractProxy;
    }

    private static abstract class APProxy {
        final SOAPConnection conn;
        final MessageFactory msgFactory;
        final String ENDPOINT;
        final String ACCESS_POINT;

        private APProxy(SOAPConnection connection, MessageFactory messageFactory, String endpoint, String accessPoint) {
            conn = connection;
            msgFactory = messageFactory;
            ENDPOINT = endpoint;
            ACCESS_POINT = accessPoint;
        }

        protected SOAPBody send(MessageNode bodyContent) {
            try {
                SOAPMessage msg = msgFactory.createMessage();

                // dig down to get body
                SOAPPart soapPart = msg.getSOAPPart();
                SOAPEnvelope soapEnv = soapPart.getEnvelope();
                SOAPBody soapBody = soapEnv.getBody();

                // get rid of header
                soapEnv.getHeader().detachNode();

                // fill body using my hack'n'slash bulletproof method
                bodyContent.Fill(soapBody);

                // send
                SOAPMessage response = conn.call(msg, ENDPOINT);

                return response.getSOAPBody();
            }
            catch (SOAPException e) {
                return null;
            }
        }
    }

    private static class FreelancerAPProxy extends APProxy implements IFreelancerAccessPoint {

        private final QName getByJobTypeQName = new QName(ACCESS_POINT, "GetByJobType");
        private final QName getByJobTypeResQName = new QName(ACCESS_POINT, "GetByJobTypeResponse");
        private final QName getFreelancerInfoQName = new QName(ACCESS_POINT, "GetFreelancerInfo");
        private final QName getFreelancerInfoResQName = new QName(ACCESS_POINT, "GetFreelancerInfoResponse");

        private final QName freelancerInfoQName = new QName("freelancerInfo");
        private final QName freelancerIdQName = new QName("freelancerId");
        private final QName idQName = new QName("id");
        private final QName nameQName = new QName("name");
        private final QName jobTypeQName = new QName("jobType");


        private FreelancerAPProxy(SOAPConnection connection, MessageFactory messageFactory) {
            super(connection, messageFactory, Constants.CONTRACTOR_FREELANCER_ENDPOINT, Constants.CONTRACTOR_INTERFACE_NAMESPACE);
        }

        @Override
        public String[] GetByJobType(String jobType) {

            MessageValueNode jobTypeNode = new MessageValueNode(jobTypeQName, jobType);
            MessageListNode getJobsNode = new MessageListNode(getByJobTypeQName, jobTypeNode);

            SOAPBody resBody = send(getJobsNode);
            if (resBody.hasFault()) {
                System.out.printf("FreelancerProxy.GetByJobType(%s) encountered exception %s%n",
                        jobType, resBody.getFault().getFaultString());
                return null;
            }

            SOAPElement getJobsResEl = (SOAPElement) resBody.getChildElements(getByJobTypeResQName).next();
            Iterator<Node> flcIdIter = getJobsResEl.getChildElements(freelancerIdQName);
            ArrayList<String> freelancerIds = new ArrayList<>();

            while (flcIdIter.hasNext()) {
                SOAPElement flcIdEl = (SOAPElement) flcIdIter.next();
                freelancerIds.add(flcIdEl.getValue());
            }
            return freelancerIds.toArray(String[]::new);
        }

        @Override
        public FreelancerInfo GetFreelancerInfo(String freelancerId) {

            MessageValueNode flcIdNode = new MessageValueNode(freelancerIdQName, freelancerId);
            MessageListNode getFlcInfoNode = new MessageListNode(getFreelancerInfoQName, flcIdNode);

            SOAPBody resBody = send(getFlcInfoNode);
            if (resBody.hasFault()) {
                System.out.printf("FreelancerProxy.GetFreelancerInfo(%s) encountered exception %s%n",
                        freelancerId, resBody.getFault().getFaultString());
                return null;
            }

            SOAPElement getFlcInfoResEl = (SOAPElement) resBody.getChildElements(getFreelancerInfoResQName).next();
            SOAPElement flcInfo = (SOAPElement) getFlcInfoResEl.getChildElements(freelancerInfoQName).next();

            SOAPElement flcIdEl = (SOAPElement) flcInfo.getChildElements(idQName).next();
            String flcId = flcIdEl.getValue();

            SOAPElement flcNameEl = (SOAPElement) flcInfo.getChildElements(nameQName).next();
            String flcName = flcNameEl.getValue();

            Iterator<Node> flcJobTypeIter = flcInfo.getChildElements(jobTypeQName);
            ArrayList<String> flcJobTypes = new ArrayList<String>();
            while (flcJobTypeIter.hasNext()) {
                SOAPElement flcJobTypeEl = (SOAPElement) flcJobTypeIter.next();
                flcJobTypes.add(flcJobTypeEl.getValue());
            }

            return new FreelancerInfo(flcId, flcName, flcJobTypes);
        }
    }

    private static class ContractAPProxy extends APProxy implements IContractAccessPoint {

        private final QName assignFreelancerQName = new QName(ACCESS_POINT, "AssignFreelancer");
        private final QName contractIdQName = new QName("contractId");
        private final QName freelancerIdQName = new QName("freelancerId");
        private final QName dismissFreelancerQName = new QName(ACCESS_POINT, "DismissFreelancer");
        private final QName setStateQName = new QName(ACCESS_POINT, "SetState");
        private final QName newStateQName = new QName("newState");

        private ContractAPProxy(SOAPConnection connection, MessageFactory messageFactory) {
            super(connection, messageFactory, Constants.CONTRACTOR_CONTRACT_ENDPOINT, Constants.CONTRACTOR_INTERFACE_NAMESPACE);
        }

        @Override
        public boolean AssignFreelancer(String contractId, String freelancerId) {
            return false;
            //MessageListNode assignFlcNode = new MessageListNode(assignFreelancerQName, new MessageNode[] {
            //        new MessageValueNode(freelancerIdQName, freelancerId),
            //        new MessageValueNode(contractIdQName, contractId)
            //});

            //SOAPBody resBody = send(assignFlcNode);
            //if (resBody.hasFault()) {
            //    System.out.printf("ContractProxy.AssignFreelancer(%s, %s) encountered exception %s%n",
            //            contractId, freelancerId, resBody.getFault().getFaultString());
            //    return false;
            //}

            //SOAPElement

            //SOAPElement getJobsResEl = (SOAPElement) resBody.getChildElements(getJobsResQName).next();
            //Iterator<Node> flcIdIter = getJobsResEl.getChildElements(freelancerIdQName);

            //ArrayList<String> freelancerIds = new ArrayList<String>();
            //while (flcIdIter.hasNext()) {
            //    SOAPElement flcIdEl = (SOAPElement) flcIdIter.next();
            //    freelancerIds.add(flcIdEl.getValue());
            //}
            //return freelancerIds.toArray(String[]::new);
        }

        @Override
        public boolean DismissFreelancer(String contractId) {
            return false;
        }

        @Override
        public boolean SetState(String contractId, String newState) {
            return false;
        }
    }

    private static abstract class MessageNode {
        protected QName qName;
        /** Add MessageNode tree structure to the SOAPBody using its methods */
        public abstract void Fill(SOAPBody body) throws SOAPException;
        protected abstract void internalFill(SOAPElement soapElement) throws SOAPException;
    }

    private static class MessageListNode extends MessageNode {
        private MessageNode[] content;
        public MessageListNode(QName qName, MessageNode content) {
            this.qName = qName;
            this.content = new MessageNode[] { content };
        }
        public MessageListNode(QName qName, MessageNode[] content) {
            this.qName = qName;
            this.content = content;
        }

        @Override
        public void Fill(SOAPBody body) throws SOAPException {
            SOAPBodyElement bodyEl = body.addBodyElement(qName);
            for (MessageNode node : content) {
                node.internalFill(bodyEl);
            }
        }

        @Override
        protected void internalFill(SOAPElement soapElement) throws SOAPException {
            SOAPElement nextSoapElement = soapElement.addChildElement(qName);
            for (MessageNode node : content) {
                node.internalFill(nextSoapElement);
            }
        }
    }

    private static class MessageValueNode extends MessageNode {
        private String value;
        public MessageValueNode(QName qName) {
            this.qName = qName;
        }
        public MessageValueNode(QName qName, String value) {
            this.qName = qName;
            this.value = value;
        }
        @Override
        public void Fill(SOAPBody body) throws SOAPException {
            body.addBodyElement(qName).addTextNode(value);
        }
        @Override
        protected void internalFill(SOAPElement soapElement) throws SOAPException {
            soapElement.addChildElement(qName).addTextNode(value);
        }
    }
}