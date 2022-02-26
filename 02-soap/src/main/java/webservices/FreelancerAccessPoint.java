package webservices;

import interfaces.IDatabaseWrapper;
import interfaces.IFreelancerAccessPoint;
import mockups.Freelancer;

import javax.jws.WebService;
import java.util.*;

@WebService(endpointInterface = "interfaces.IFreelancerAccessPoint")
public class FreelancerAccessPoint implements IFreelancerAccessPoint {
    IDatabaseWrapper db;

    public FreelancerAccessPoint(IDatabaseWrapper db) {
        this.db = db;
    }

    @Override
    public String[] GetByJobType(String jobType) {
        System.out.printf("FreelancerAccessPoint.GetByJobtype(%s)%n", jobType);
        var compatFreelancers = db.TryGetFreelancersByJobType(jobType);
        var compatFreelancerIds = compatFreelancers.stream().map(f -> f.getId().toString()).toArray(String[]::new);
        System.out.printf(".. found compatible freelancers: %s %n", Arrays.toString(compatFreelancerIds));
        return compatFreelancerIds;
    }

    @Override
    public String[] GetFreelancerInfo(String freelancerId) {
        System.out.printf("FreelancerAccessPoint.GetFreelancerInfo(%s)%n", freelancerId);
        var maybeFreelancer = db.TryGetFreelancerById(UUID.fromString(freelancerId));
        if (maybeFreelancer.isEmpty()) {
            System.out.println(".. freelancer not found, rejecting...");
            return new String[0];
        }
        var freelancerInfo = maybeFreelancer.get().getInfo();
        System.out.printf(".. freelancer with info %s found.%n", Arrays.toString(freelancerInfo));
        return freelancerInfo;
    }
}
