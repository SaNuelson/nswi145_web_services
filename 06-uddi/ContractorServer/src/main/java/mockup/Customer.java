package mockup;

import java.util.UUID;

public class Customer {
    UUID id;
    String name;
    String pass;

    public Customer(String name, String pass) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.pass = pass;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String getPass() {
    	return pass;
    }
}
