package com.contractor.shared;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


@WebService
public interface IFreelancerAccessPoint {

    @WebMethod
    @WebResult(name="freelancerId")
    public String[] GetByJobType(
            @WebParam(name="jobType") String jobType
    );

    @WebMethod
    @WebResult(name="freelancerInfo")
    public FreelancerInfo GetFreelancerInfo(
            @WebParam(name="freelancerId") String freelancerId
    );
}
