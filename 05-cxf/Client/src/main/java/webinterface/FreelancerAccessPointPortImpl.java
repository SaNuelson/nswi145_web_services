
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package webinterface;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.5.1
 * 2022-03-27T21:13:16.168+02:00
 * Generated source version: 3.5.1
 *
 */

@javax.jws.WebService(
                      serviceName = "FreelancerAccessPointService",
                      portName = "FreelancerAccessPointPort",
                      targetNamespace = "http://webservice/",
                      wsdlLocation = "http://localhost:8080/Server/wsdl/freelanceraccesspoint.wsdl",
                      endpointInterface = "webinterface.IFreelancerAccessPoint")

public class FreelancerAccessPointPortImpl implements IFreelancerAccessPoint {

    private static final Logger LOG = Logger.getLogger(FreelancerAccessPointPortImpl.class.getName());

    /* (non-Javadoc)
     * @see webinterface.IFreelancerAccessPoint#getByJobType(java.lang.String arg0)*
     */
    public java.util.List<java.lang.String> getByJobType(java.lang.String arg0) {
        LOG.info("Executing operation getByJobType");
        System.out.println(arg0);
        try {
            java.util.List<java.lang.String> _return = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnVal1 = "_returnVal-1883856934";
            _return.add(_returnVal1);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see webinterface.IFreelancerAccessPoint#getFreelancerInfo(java.lang.String arg0)*
     */
    public webinterface.FreelancerInfo getFreelancerInfo(java.lang.String arg0) {
        LOG.info("Executing operation getFreelancerInfo");
        System.out.println(arg0);
        try {
            webinterface.FreelancerInfo _return = new webinterface.FreelancerInfo();
            _return.setId("Id-476861750");
            java.util.List<java.lang.String> _returnJobTypes = new java.util.ArrayList<java.lang.String>();
            java.lang.String _returnJobTypesVal1 = "_returnJobTypesVal1821707145";
            _returnJobTypes.add(_returnJobTypesVal1);
            _return.getJobTypes().addAll(_returnJobTypes);
            _return.setName("Name-781550694");
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
