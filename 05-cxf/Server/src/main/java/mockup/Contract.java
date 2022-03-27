package mockup;

import java.util.Optional;
import java.util.UUID;

public class Contract {
    public enum State {
        Created, // set up by customer
        Initiated, // assigned freelancer
        WaitingForPayment, // freelancer finished and delivered
        WaitingForPickup, // customer paid
        Cancelled, // cancelled in some way
        Completed // customer picked up order
    };

    State state = State.Created;
    UUID id;
    String name;
    UUID customerID;
    Optional<UUID> freelancerID;

    public Contract(String name, UUID custID) {
        this.name = name;
        this.customerID = custID;
        this.id = UUID.randomUUID();
    }

    public boolean tryAssignFreelancer(UUID freelancerId) {
        if (state != State.Created || this.freelancerID.isPresent()) {
            return false;
        }
        // expects to be called only after customer deposit is made
        state = State.Initiated;
        this.freelancerID = Optional.ofNullable(freelancerId);
        return true;
    }

    public boolean tryDismissFreelancer() {
        if (state == State.Created || !this.freelancerID.isPresent()) {
            return false;
        }
        state = State.Created;
        this.freelancerID = Optional.empty();
        return true;
    }

    public boolean trySetState(State state) {
        if (this.state == State.Completed) {
            return false;
        }

        // Should incorporate some DFA here but eh...
        this.state = state;
        return true;

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public UUID getCustomerID() {
        return customerID;
    }

    public Optional<UUID> getFreelancerID() {
        return freelancerID;
    }
}
