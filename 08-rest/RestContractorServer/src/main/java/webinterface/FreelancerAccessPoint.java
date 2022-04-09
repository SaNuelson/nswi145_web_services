package webinterface;

import common.FreelancerInfo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;


public interface FreelancerAccessPoint {

    @GET
    @Path("byJob/{jobType}")
    int[] getCompatibleFreelancers(@PathParam("jobType") String jobType);

    @GET
    @Path("/{freelancerId}")
    FreelancerInfo getFreelancerDetails(@PathParam("freelancerId") String freelancerId);
}
