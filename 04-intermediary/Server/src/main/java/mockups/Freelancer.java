package mockups;

import webservices.FreelancerInfo;

import java.util.List;
import java.util.UUID;

public class Freelancer {
    UUID id;
    String name;
    List<String> jobTypes;

    public Freelancer(String name, List<String> jobTypes) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.jobTypes = jobTypes;
    }

    public boolean hasJobType(String jobType) {
        return this.jobTypes.contains(jobType);
    }

    public String getName() {
        return this.name;
    }

    public FreelancerInfo getInfo() {
        return new FreelancerInfo(this.id.toString(), this.name, this.jobTypes);
    }

    public UUID getId() {
        return id;
    }
}
