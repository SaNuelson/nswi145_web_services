package mockups;

import interfaces.IDatabaseWrapper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class DB implements IDatabaseWrapper {
    List<Freelancer> freelancers;
    List<Customer> customers;
    List<Contract> contracts;

    public static DB Mockup() {
        var db = new DB();

        var f1 = new Freelancer("John Doe", List.of("knitting"));
        var f2 = new Freelancer("Jack Dove", List.of("knitting"));
        var f3 = new Freelancer("Thor", List.of("smithing"));
        var f4 = new Freelancer("Tim Smith", List.of("smithing"));
        var f5 = new Freelancer("Littlefinger", List.of("knitting", "smithing", "other"));
        var f6 = new Freelancer("Dan Slacker", List.of());
        db.freelancers = Arrays.asList(f1, f2, f3, f4, f5, f6);

        var c1 = new Customer("Jack Luck");
        var c2 = new Customer("Jane Vain");
        var c3 = new Customer("Tim Tin");
        db.customers = Arrays.asList(c1, c2, c3);


        var x1 = new Contract("Custom made tin", c3.id);
        var x2 = new Contract("Lucky horseshoe", c1.id);
        db.contracts = Arrays.asList(x1, x2);

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
}
