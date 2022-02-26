import mockups.DB;
import webservices.FreelancerAccessPoint;
import webservices.ContractAccessPoint;

import javax.xml.ws.Endpoint;

public class Runner {
    public static void main(String[] args)
    {
        var database = DB.Mockup();
        Endpoint.publish("http://127.0.0.1:8000/freelancers", new FreelancerAccessPoint(database));
        Endpoint.publish("http://127.0.0.1:8000/contracts", new ContractAccessPoint(database));
    }
}

