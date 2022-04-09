package webservice;

import common.FreelancerInfo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import mockup.DatabaseMock;
import webinterface.FreelancerAccessPoint;

import java.util.Arrays;

@Path("/freelancers")
public class FreelancerAccessPointImpl implements FreelancerAccessPoint {
    private DatabaseMock db;

    public FreelancerAccessPointImpl() {
        System.out.println("Creating FreelancerAccessPointImpl with mock DB.");
        db = DatabaseMock.getMockInstance();
    }

    @GET
    @Override
    @Path("byJob/{jobType}")
    public int[] getCompatibleFreelancers(@PathParam("jobType") String jobType) {
        return db.freelancerInfos.stream()
                .filter(f -> Arrays.asList(f.getJobTypes()).contains(jobType))
                .mapToInt(FreelancerInfo::getId)
                .toArray();
    }

    @GET
    @Override
    @Path("/{freelancerId}")
    public FreelancerInfo getFreelancerDetails(@PathParam("freelancerId") String freelancerId) {
        int fId;
        try {
            fId = Integer.parseInt(freelancerId);
        }
        catch(NumberFormatException nfe) {
            return null;
        }

        return db.freelancerInfos.stream()
                .filter(f -> f.getId() == fId)
                .findFirst()
                .orElse(null);
    }
}
