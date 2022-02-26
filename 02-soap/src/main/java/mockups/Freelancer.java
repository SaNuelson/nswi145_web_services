package mockups;

import java.io.Serializable;
import java.util.Arrays;
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

    public String[] getInfo() {
        return new String[] {this.name, this.jobTypes.toString()};
    }

    public UUID getId() {
        return id;
    }
}
