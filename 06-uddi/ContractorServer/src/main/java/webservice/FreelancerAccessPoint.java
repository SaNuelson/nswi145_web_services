package webservice;

import java.util.*;

import javax.jws.WebService;

import org.apache.juddi.v3.annotations.UDDIService;
import org.apache.juddi.v3.annotations.UDDIServiceBinding;

import mockup.DB;
import mockup.Freelancer;
import webinterface.IDatabaseWrapper;
import webinterface.IFreelancerAccessPoint;

@UDDIService(
		businessKey="uddi:${keyDomain}:${department}",
		serviceKey="uddi:${keyDomain}:services-freelancer-access-point", 
		description = "Freelancer service")
@UDDIServiceBinding(
		bindingKey="uddi:${keyDomain}:${serverName}-${serverPort}-{department}-freelancer-ap-wsdl",
	    description="WSDL endpoint for the freelancer access point",
	    accessPointType="wsdlDeployment",
	    accessPoint="http://${serverName}:11080/uddi-annotations/wsdl/freelanceraccesspoint.wsdl")
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

        List<String> compatFreelancerIds = new ArrayList<String>();
        for (Freelancer flc : compatFreelancers)
        	compatFreelancerIds.add(flc.getId().toString());
        
        System.out.printf(".. found compatible freelancers: %s %n", compatFreelancerIds);
        return (String[]) compatFreelancerIds.toArray();
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

