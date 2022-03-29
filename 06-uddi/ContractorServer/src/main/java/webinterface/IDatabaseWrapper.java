package webinterface;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import mockup.Contract;
import mockup.Customer;
import mockup.Freelancer;

public interface IDatabaseWrapper {

    public Optional<Freelancer> TryGetFreelancerById(UUID id);
    public List<Freelancer> TryGetFreelancersByJobType(String jobType);

    public Optional<Contract> TryGetContractById(UUID id);

    public Optional<Customer> TryGetCustomerById(UUID id);
	
    public Optional<Customer> TryGetCustomerByName(String name);

}
