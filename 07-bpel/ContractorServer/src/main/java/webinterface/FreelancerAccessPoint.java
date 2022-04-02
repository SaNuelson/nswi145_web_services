package webinterface;

import common.FreelancerInfo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface FreelancerAccessPoint {

    @WebMethod
    @WebResult(name="freelancerIds")
    public int[] getCompatibleFreelancers(@WebParam(name="jobType") String jobType);

    @WebMethod
    @WebResult(name="freelancerInfo")
    public FreelancerInfo getFreelancerDetails(@WebParam(name="freelancerId") int freelancerId);

}
