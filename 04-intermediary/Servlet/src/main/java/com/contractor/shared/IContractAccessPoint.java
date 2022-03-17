package com.contractor.shared;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface IContractAccessPoint {

    @WebMethod
    @WebResult(name="success")
    public boolean AssignFreelancer(
            @WebParam(name="contractId") String contractId,
            @WebParam(name="freelancerId") String freelancerId);

    @WebMethod
    @WebResult(name="success")
    public boolean DismissFreelancer(
            @WebParam(name="contractId") String contractId);

    @WebMethod
    @WebResult(name="success")
    public boolean SetState(
            @WebParam(name="contractId") String contractId,
            @WebParam(name="newState") String newState);
}