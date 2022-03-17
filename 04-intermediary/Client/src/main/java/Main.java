import interfaces.IFreelancerAccessPoint;
import util.FreelancerInfo;

import proxy.AutoContractorClient;
import proxy.ClientProxy;
import proxy.SaajContractorClient;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        try {
            ClientProxy autoClient = new AutoContractorClient();
            ClientProxy saajClient = new SaajContractorClient();

            TestProxy("Service client", autoClient);
            TestProxy("SAAJ client", saajClient);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void TestProxy(String title, ClientProxy proxy) {
        System.out.println("Client proxy test - " + title);
        IFreelancerAccessPoint flcProxy = proxy.GetFreelancerProxy();
        String[] flcIds = flcProxy.GetByJobType("knitting");
        System.out.println(".. for knitting found IDs: " + Arrays.toString(flcIds));
        for (String id : flcIds) {
            FreelancerInfo info = flcProxy.GetFreelancerInfo(id);
            System.out.println(".. info for ID: " + id);
            System.out.println(info.toLongString());
        }
    }

}
