package interfaces;

@javax.jws.WebService
public interface IFreelancerAccessPoint {

    @javax.jws.WebMethod
    public String[] GetByJobType(String jobType);

    @javax.jws.WebMethod
    public String[] GetFreelancerInfo(String freelancerId);
}
