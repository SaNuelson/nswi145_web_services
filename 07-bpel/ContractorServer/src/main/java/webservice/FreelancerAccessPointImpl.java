package webservice;

import common.FreelancerInfo;
import mockup.DatabaseMock;
import webinterface.FreelancerAccessPoint;

import javax.jws.WebService;
import java.util.Arrays;

@WebService(endpointInterface = "webinterface.FreelancerAccessPoint")
public class FreelancerAccessPointImpl implements FreelancerAccessPoint {
    private DatabaseMock db;

    public FreelancerAccessPointImpl() {
        System.out.println("Creating FreelancerAccessPointImpl with mock DB.");
        db = DatabaseMock.getMockInstance();
    }

    @Override
    public int[] getCompatibleFreelancers(String jobType) {
        return db.freelancerInfos.stream()
                .filter(f -> Arrays.asList(f.getJobTypes()).contains(jobType))
                .mapToInt(f -> f.getId())
                .toArray();
    }

    @Override
    public FreelancerInfo getFreelancerDetails(int freelancerId) {
        return db.freelancerInfos.stream()
                .filter(f -> f.getId() == freelancerId)
                .findFirst()
                .orElse(null);
    }
}
