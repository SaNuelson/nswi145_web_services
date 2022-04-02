package webinterface;

import common.ContractInfo;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface ContractAccessPoint {

    // Querying

    @WebMethod
    @WebResult(name="customerIds")
    int[] getSubmittedContracts(int customerId);

    public ContractInfo getContractInfo(int contractId);

    // Modification

    @WebMethod
    public void submitContract(ContractInfo contractInfo);

    @WebMethod
    @WebResult(name="success")
    public boolean assignFreelancer(int contractId, int freelancerId);

    @WebMethod
    @WebResult(name="success")
    public boolean dismissFreelancer(int contractId);

}
