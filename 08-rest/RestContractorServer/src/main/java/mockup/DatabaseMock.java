package mockup;

import common.ContractInfo;
import common.CustomerInfo;
import common.FreelancerInfo;
import util.Random;

import java.util.ArrayList;
import java.util.List;

public class DatabaseMock {

    public List<CustomerInfo> customerInfos = new ArrayList<>();
    public List<FreelancerInfo> freelancerInfos = new ArrayList<>();
    public List<ContractInfo> contractInfos = new ArrayList<>();

    private DatabaseMock() {}

    public static DatabaseMock getMockInstance() {
        if (mockInstance == null)
            createMock();
        return mockInstance;
    }

    private static DatabaseMock mockInstance;
    private static void createMock() {
        String[] jobTypes = new String[] {"knitting","painting","smithing","glassblowing"};

        mockInstance = new DatabaseMock();

        for (int i = 0; i < 10; i++) {
            mockInstance.customerInfos.add(
                    new CustomerInfo(
                            Random.getRandomName(),
                            Random.getRandomPass()));
        }

        for (int i = 0; i < 10; i++) {
            mockInstance.freelancerInfos.add(
                    new FreelancerInfo(
                            Random.getRandomName(),
                            Random.getRandomSubset(jobTypes),
                            Random.getRandomInt(100, 500)));
        }

        for (int i = 0; i < 5; i++) {
            mockInstance.contractInfos.add(
                    new ContractInfo(
                            "Sample contract #" + i,
                            "Sample description for contract number " + i,
                            Random.pickRandom(jobTypes),
                            Random.pickRandom(mockInstance.customerInfos).getId(),
                            Random.pickRandom(mockInstance.freelancerInfos).getId()));
        }
    }

}
