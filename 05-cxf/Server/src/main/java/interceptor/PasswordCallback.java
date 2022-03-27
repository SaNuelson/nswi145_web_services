package interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import webservice.FreelancerAccessPoint;
import org.apache.wss4j.common.ext.WSPasswordCallback;

import mockup.Customer;
import mockup.DB;

public class PasswordCallback implements CallbackHandler {
	private DB db;
	
	public PasswordCallback() {
		db = DB.Mockup();
	}
    
	/**
	* Here, we attempt to get the password from the private
 	* alias/passwords map.
 	*/
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		System.out.println("Security callback called...");
		
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback wspc = (WSPasswordCallback)callbacks[i];
			Optional<Customer> maybeCustomer = db.TryGetCustomerByName(wspc.getIdentifier());
			
			if (maybeCustomer.isPresent()) {
				wspc.setPassword(maybeCustomer.get().getPass());
				return;
			}
		}
	}
}