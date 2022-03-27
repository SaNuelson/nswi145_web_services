package mockup;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import webinterface.IDatabaseWrapper;

public class DB implements IDatabaseWrapper {
    List<Freelancer> freelancers;
    List<Customer> customers;
    List<Contract> contracts;

    private static DB mockupInstance = null;
    public static DB Mockup() {
    	
    	if (mockupInstance != null)
    		return mockupInstance;
    	
        DB db = new DB();

        Freelancer f1 = new Freelancer("John Doe", Arrays.asList("knitting"));
        Freelancer f2 = new Freelancer("Jack Dove", Arrays.asList("knitting"));
        Freelancer f3 = new Freelancer("Thor", Arrays.asList("smithing"));
        Freelancer f4 = new Freelancer("Tim Smith", Arrays.asList("smithing"));
        Freelancer f5 = new Freelancer("Littlefinger", Arrays.asList("knitting", "smithing", "other"));
        Freelancer f6 = new Freelancer("Dan Slacker", Arrays.asList());
        db.freelancers = Arrays.asList(f1, f2, f3, f4, f5, f6);

        Customer c1 = new Customer("Jack Luck", "pass123");
        Customer c2 = new Customer("Jane Vain", "pass456");
        Customer c3 = new Customer("Tim Tin", "pass789");
        db.customers = Arrays.asList(c1, c2, c3);


        Contract x1 = new Contract("Custom made tin", c3.id);
        Contract x2 = new Contract("Lucky horseshoe", c1.id);
        db.contracts = Arrays.asList(x1, x2);

        mockupInstance = db;
        return db;
    };

    public Freelancer[] GetFreelancers(String jobType) {
        return this.freelancers.stream().filter(f -> f.jobTypes.contains(jobType)).toArray(Freelancer[]::new);
    }

    @Override
    public Optional<Freelancer> TryGetFreelancerById(UUID id) {
        return freelancers.stream().filter(f -> f.getId().equals(id)).findFirst();
    }

    @Override
    public List<Freelancer> TryGetFreelancersByJobType(String jobType) {
        return freelancers.stream().filter(f -> f.hasJobType(jobType)).collect(Collectors.toList());
    }

    @Override
    public Optional<Contract> TryGetContractById(UUID id) {
        return contracts.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Customer> TryGetCustomerById(UUID id) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
    
    @Override
    public Optional<Customer> TryGetCustomerByName(String name) {
    	return customers.stream().filter(c -> c.getName().equals(name)).findFirst();
    }
    
}
