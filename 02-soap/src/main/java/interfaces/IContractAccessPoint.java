package interfaces;

@javax.jws.WebService
public interface IContractAccessPoint {

    @javax.jws.WebMethod
    public boolean AssignFreelancer(String contractId, String freelancerId);

    @javax.jws.WebMethod
    public boolean DismissFreelancer(String contractId);

    @javax.jws.WebMethod
    public boolean SetState(String contractId, String newState);
}
