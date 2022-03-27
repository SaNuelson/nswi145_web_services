package webservice;

import java.util.*;

import javax.jws.WebService;

import mockup.DB;
import mockup.Freelancer;
import webinterface.IDatabaseWrapper;
import webinterface.IFreelancerAccessPoint;

@WebService(targetNamespace = "http://webservice/", endpointInterface = "webinterface.IFreelancerAccessPoint", portName = "FreelancerAccessPointPort", serviceName = "FreelancerAccessPointService")
public class FreelancerAccessPoint implements IFreelancerAccessPoint {
    IDatabaseWrapper db;

    public FreelancerAccessPoint() {
        this.db = DB.Mockup();
    }

    @Override
    public String[] GetByJobType(String jobType) {
        System.out.printf("FreelancerAccessPoint.GetByJobtype(%s)%n", jobType);
        List<Freelancer> compatFreelancers = db.TryGetFreelancersByJobType(jobType);
        String[] compatFreelancerIds = compatFreelancers.stream().map(f -> f.getId().toString()).toArray(String[]::new);
        System.out.printf(".. found compatible freelancers: %s %n", Arrays.toString(compatFreelancerIds));
        return compatFreelancerIds;
    }

    @Override
    public FreelancerInfo GetFreelancerInfo(String freelancerId) {
        System.out.printf("FreelancerAccessPoint.GetFreelancerInfoTest(%s)%n", freelancerId);
        Optional<Freelancer> maybeFreelancer = db.TryGetFreelancerById(UUID.fromString(freelancerId));
        if (!maybeFreelancer.isPresent()) {
            System.out.println(".. freelancer not found, rejecting...");
            return FreelancerInfo.Empty();
        }
        FreelancerInfo freelancerInfo = maybeFreelancer.get().getInfo();
        System.out.printf(".. freelancer found.%n");
        return freelancerInfo;
    }
}

