package webservice;

import common.ContractInfo;
import common.FreelancerInfo;
import mockup.DatabaseMock;
import webinterface.ContractAccessPoint;

import javax.jws.WebService;
import java.util.Optional;

@WebService(endpointInterface = "webinterface.ContractAccessPoint")
public class ContractAccessPointImpl implements ContractAccessPoint {
    private DatabaseMock db;

    public ContractAccessPointImpl() {
        System.out.println("Creating ContractAccessPointImpl with mock DB.");
        db = DatabaseMock.getMockInstance();
    }


    @Override
    public int[] getSubmittedContracts(int customerId) {
        return db.contractInfos.stream()
                .filter(c -> c.getCustomerId() == customerId)
                .mapToInt(c -> c.getId())
                .toArray();
    }

    @Override
    public ContractInfo getContractInfo(int contractId) {
        return db.contractInfos.stream()
                .filter(c -> c.getId() == contractId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean submitContract(ContractInfo contractInfo) {
        db.contractInfos.add(contractInfo);
        return true;
    }

    @Override
    public boolean assignFreelancer(int contractId, int freelancerId) {
        Optional<ContractInfo> maybeContract = db.contractInfos.stream().filter(c -> c.getId() == contractId).findFirst();
        if (!maybeContract.isPresent())
            return false;

        ContractInfo contract = maybeContract.get();
        if (contract.getFreelancerId() >= FreelancerInfo.ID_MIN)
            return false;

        if (db.freelancerInfos.stream().noneMatch(f -> f.getId() == freelancerId))
            return false;

        contract.setFreelancerId(freelancerId);
        return true;
    }

    @Override
    public boolean dismissFreelancer(int contractId) {
        Optional<ContractInfo> maybeContract = db.contractInfos.stream().filter(c -> c.getId() == contractId).findFirst();
        if (!maybeContract.isPresent())
            return false;

        ContractInfo contract = maybeContract.get();
        if (contract.getFreelancerId() < FreelancerInfo.ID_MIN)
            return false;

        contract.setFreelancerId(0);
        return true;
    }
}
