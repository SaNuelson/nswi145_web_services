package webservice;

import java.util.Optional;
import java.util.UUID;

import javax.jws.WebService;

import mockup.Contract;
import mockup.DB;
import webinterface.IContractAccessPoint;
import webinterface.IDatabaseWrapper;

@WebService(targetNamespace = "http://webservices/", endpointInterface = "interfaces.IContractAccessPoint", portName = "ContractAccessPointPort", serviceName = "ContractAccessPointService")
public class ContractAccessPoint implements IContractAccessPoint {
    IDatabaseWrapper db;

    public ContractAccessPoint() {
        this.db = DB.Mockup();
    }

    @Override
    public boolean AssignFreelancer(String contractId, String freelancerId) {
        System.out.printf("ContractAccessPoint.AssignFreelancer(%s, %s)%n", contractId, freelancerId);
        Optional<Contract> maybeContract = db.TryGetContractById(UUID.fromString(contractId));
        if (!maybeContract.isPresent()) {
            System.out.println(".. contract not found, rejecting...");
            return false;
        }
        boolean success = maybeContract.get().tryAssignFreelancer(UUID.fromString(freelancerId));
        System.out.println(".. contract found, assign success = " + success);
        return success;
    }

    @Override
    public boolean DismissFreelancer(String contractId) {
        System.out.printf("ContractAccessPoint.DismissFreelancer(%s)%n", contractId);
        Optional<Contract> maybeContract = db.TryGetContractById(UUID.fromString(contractId));
        if (!maybeContract.isPresent()) {
            System.out.println(".. contract not found, rejecting...");
            return false;
        }
        boolean success = maybeContract.get().tryDismissFreelancer();
        System.out.println(".. contract found, dismiss success = " + success);
        return success;
    }

    @Override
    public boolean SetState(String contractId, String newState) {
        System.out.printf("ContractAccessPoint.SetState(%s, %s)%n", contractId, newState);
        Optional<Contract> maybeContract = db.TryGetContractById(UUID.fromString(contractId));
        if (!maybeContract.isPresent()) {
            System.out.println(".. contract not found, rejecting...");
            return false;
        }
        boolean success = maybeContract.get().trySetState(Contract.State.valueOf(newState));
        System.out.println(".. contract found, state change success = " + success);
        return success;
    }
}
