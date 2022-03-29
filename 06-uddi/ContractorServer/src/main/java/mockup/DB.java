package mockup;

import java.util.ArrayList;
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
        Freelancer f6 = new Freelancer("Dan Slacker", new ArrayList<String>());
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
        List<Freelancer> flcs = new ArrayList<Freelancer>();
        for (Freelancer flc : this.freelancers) {
        	if (flc.jobTypes.contains(jobType))
        		flcs.add(flc);
        }
        return (Freelancer[]) flcs.toArray();
    }

    @Override
    public Optional<Freelancer> TryGetFreelancerById(UUID id) {
        for (Freelancer flc : this.freelancers) {
        	if (flc.getId().equals(id))
        		return Optional.of(flc);
        }
        return Optional.empty();
    }

    @Override
    public List<Freelancer> TryGetFreelancersByJobType(String jobType) {
        List<Freelancer> flcs = new ArrayList<Freelancer>();
        for (Freelancer flc : this.freelancers) {
        	if (flc.jobTypes.contains(jobType))
        		flcs.add(flc);
        }
        return flcs;
    }

    @Override
    public Optional<Contract> TryGetContractById(UUID id) {
        for (Contract contract: this.contracts) {
        	if (contract.getId().equals(id))
        		return Optional.of(contract);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Customer> TryGetCustomerById(UUID id) {
        for (Customer customer : this.customers) {
        	if (customer.getId().equals(id))
        		return Optional.of(customer);
        }
        return Optional.empty();
    }
    
    @Override
    public Optional<Customer> TryGetCustomerByName(String name) {
        for (Customer customer : this.customers) {
        	if (customer.getName().equals(name))
        		return Optional.of(customer);
        }
        return Optional.empty();
    }
    
}
