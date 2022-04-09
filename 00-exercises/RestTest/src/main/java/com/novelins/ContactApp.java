package com.novelins;

import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class ContactApp extends ResourceConfig {
	public ContactApp() {
		packages("com.novelins");
	}
}
