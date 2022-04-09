import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("webservice")
public class Runner extends ResourceConfig {
    public Runner() {
    	System.out.println("Deploying package webservice...");
        packages("webservice");
    }
}
