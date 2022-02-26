package interfaces;

import mockups.Contract;
import mockups.Customer;
import mockups.Freelancer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDatabaseWrapper {

    public Optional<Freelancer> TryGetFreelancerById(UUID id);
    public List<Freelancer> TryGetFreelancersByJobType(String jobType);

    public Optional<Contract> TryGetContractById(UUID id);

    public Optional<Customer> TryGetCustomerById(UUID id);

}
