package webinterface;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "IContractAccessPoint", targetNamespace = "http://interfaces/")
public interface IContractAccessPoint {

    @WebMethod(operationName = "AssignFreelancer", action = "urn:AssignFreelancer")
	public boolean AssignFreelancer(String contractId, String freelancerId);

    @WebMethod(operationName = "DismissFreelancer", action = "urn:DismissFreelancer")
	public boolean DismissFreelancer(String contractId);

    @WebMethod(operationName = "SetState", action = "urn:SetState")
	public boolean SetState(String contractId, String newState);
}