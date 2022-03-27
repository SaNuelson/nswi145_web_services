
package webinterface;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webinterface package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetByJobType_QNAME = new QName("http://webinterface/", "GetByJobType");
    private final static QName _GetByJobTypeResponse_QNAME = new QName("http://webinterface/", "GetByJobTypeResponse");
    private final static QName _GetFreelancerInfo_QNAME = new QName("http://webinterface/", "GetFreelancerInfo");
    private final static QName _GetFreelancerInfoResponse_QNAME = new QName("http://webinterface/", "GetFreelancerInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webinterface
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetByJobType }
     * 
     */
    public GetByJobType createGetByJobType() {
        return new GetByJobType();
    }

    /**
     * Create an instance of {@link GetByJobTypeResponse }
     * 
     */
    public GetByJobTypeResponse createGetByJobTypeResponse() {
        return new GetByJobTypeResponse();
    }

    /**
     * Create an instance of {@link GetFreelancerInfo }
     * 
     */
    public GetFreelancerInfo createGetFreelancerInfo() {
        return new GetFreelancerInfo();
    }

    /**
     * Create an instance of {@link GetFreelancerInfoResponse }
     * 
     */
    public GetFreelancerInfoResponse createGetFreelancerInfoResponse() {
        return new GetFreelancerInfoResponse();
    }

    /**
     * Create an instance of {@link FreelancerInfo }
     * 
     */
    public FreelancerInfo createFreelancerInfo() {
        return new FreelancerInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByJobType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetByJobType }{@code >}
     */
    @XmlElementDecl(namespace = "http://webinterface/", name = "GetByJobType")
    public JAXBElement<GetByJobType> createGetByJobType(GetByJobType value) {
        return new JAXBElement<GetByJobType>(_GetByJobType_QNAME, GetByJobType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByJobTypeResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetByJobTypeResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webinterface/", name = "GetByJobTypeResponse")
    public JAXBElement<GetByJobTypeResponse> createGetByJobTypeResponse(GetByJobTypeResponse value) {
        return new JAXBElement<GetByJobTypeResponse>(_GetByJobTypeResponse_QNAME, GetByJobTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFreelancerInfo }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFreelancerInfo }{@code >}
     */
    @XmlElementDecl(namespace = "http://webinterface/", name = "GetFreelancerInfo")
    public JAXBElement<GetFreelancerInfo> createGetFreelancerInfo(GetFreelancerInfo value) {
        return new JAXBElement<GetFreelancerInfo>(_GetFreelancerInfo_QNAME, GetFreelancerInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFreelancerInfoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFreelancerInfoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://webinterface/", name = "GetFreelancerInfoResponse")
    public JAXBElement<GetFreelancerInfoResponse> createGetFreelancerInfoResponse(GetFreelancerInfoResponse value) {
        return new JAXBElement<GetFreelancerInfoResponse>(_GetFreelancerInfoResponse_QNAME, GetFreelancerInfoResponse.class, null, value);
    }

}
