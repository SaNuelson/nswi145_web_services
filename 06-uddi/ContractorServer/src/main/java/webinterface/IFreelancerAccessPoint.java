package webinterface;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;

import webservice.FreelancerInfo;

@WebService(name = "IFreelancerAccessPoint", targetNamespace = "http://webinterface/")
public interface IFreelancerAccessPoint {

	@WebMethod(operationName = "GetByJobType", action = "urn:GetByJobType")
	public String[] GetByJobType(String jobType);

	@WebMethod(operationName = "GetFreelancerInfo", action = "urn:GetFreelancerInfo")
	@ResponseWrapper(className = "webinterface.jaxws.GetFreelancerInfoResponse", localName = "GetFreelancerInfoResponse", targetNamespace = "http://webinterface/")
	public FreelancerInfo GetFreelancerInfo(String freelancerId);
}
