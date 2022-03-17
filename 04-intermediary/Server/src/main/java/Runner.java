import mockups.DB;
import webservices.FreelancerAccessPoint;
import webservices.ContractAccessPoint;

import javax.xml.ws.Endpoint;

public class Runner {

    public static final String CONTRACTOR_ACCESS_POINT = "http://interfaces.server/";
    public static final String CONTRACTOR_FREELANCER_ENDPOINT = "http://127.0.0.1:8000/freelancers";
    public static final String CONTRACTOR_CONTRACT_ENDPOINT = "http://127.0.0.1:8000/contracts";

    public static void main(String[] args)
    {
        var database = DB.Mockup();
        Endpoint.publish(CONTRACTOR_FREELANCER_ENDPOINT, new FreelancerAccessPoint(database));
        Endpoint.publish(CONTRACTOR_CONTRACT_ENDPOINT, new ContractAccessPoint(database));
    }
}

