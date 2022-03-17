package webservices;

import mockups.Contract;
import interfaces.IContractAccessPoint;
import interfaces.IDatabaseWrapper;

import javax.jws.WebService;
import java.util.UUID;

@WebService(endpointInterface = "interfaces.IContractAccessPoint")
public class ContractAccessPoint implements IContractAccessPoint {
    IDatabaseWrapper db;

    public ContractAccessPoint(IDatabaseWrapper db) {
        this.db = db;
    }

    @Override
    public boolean AssignFreelancer(String contractId, String freelancerId) {
        System.out.printf("ContractAccessPoint.AssignFreelancer(%s, %s)%n", contractId, freelancerId);
        var maybeContract = db.TryGetContractById(UUID.fromString(contractId));
        if (maybeContract.isEmpty()) {
            System.out.println(".. contract not found, rejecting...");
            return false;
        }
        var success = maybeContract.get().tryAssignFreelancer(UUID.fromString(freelancerId));
        System.out.println(".. contract found, assign success = " + success);
        return success;
    }

    @Override
    public boolean DismissFreelancer(String contractId) {
        System.out.printf("ContractAccessPoint.DismissFreelancer(%s)%n", contractId);
        var maybeContract = db.TryGetContractById(UUID.fromString(contractId));
        if (maybeContract.isEmpty()) {
            System.out.println(".. contract not found, rejecting...");
            return false;
        }
        var success = maybeContract.get().tryDismissFreelancer();
        System.out.println(".. contract found, dismiss success = " + success);
        return success;
    }

    @Override
    public boolean SetState(String contractId, String newState) {
        System.out.printf("ContractAccessPoint.SetState(%s, %s)%n", contractId, newState);
        var maybeContract = db.TryGetContractById(UUID.fromString(contractId));
        if (maybeContract.isEmpty()) {
            System.out.println(".. contract not found, rejecting...");
            return false;
        }
        var success = maybeContract.get().trySetState(Contract.State.valueOf(newState));
        System.out.println(".. contract found, state change success = " + success);
        return success;
    }
}
