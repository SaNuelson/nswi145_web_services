package mockups;

import java.util.UUID;

public class Customer {
    UUID id;
    String name;

    public Customer(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
