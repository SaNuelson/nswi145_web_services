import webservice.ContractAccessPointImpl;
import webservice.DebugAccessPointImpl;
import webservice.FreelancerAccessPointImpl;

import javax.xml.ws.Endpoint;

public class Runner {

    public static void main(String[] args)
    {
        Endpoint.publish("http://127.0.0.1:8000/freelancers", new FreelancerAccessPointImpl());
        Endpoint.publish("http://127.0.0.1:8000/contracts", new ContractAccessPointImpl());
        Endpoint.publish("http://127.0.0.1:8000/debug", new DebugAccessPointImpl());
    }

}
