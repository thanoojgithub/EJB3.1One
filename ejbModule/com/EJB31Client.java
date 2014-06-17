package com;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJB31Client {

	private static final String LOOKUP_STRING = "HelloWorld/remote";

	public static void main(String[] args) {
		IHelloWorld bean = doLookup();
		// 3. Call business logic
		System.out.println(bean.sayHello("Sriram"));
	}

	private static IHelloWorld doLookup() {
		Context context = null;
		IHelloWorld bean = new HelloWorld();
		try {
			// 1. Obtaining Context
			context = getInitialContext();
			// 2. Lookup and cast
			System.out.println("context.lookup(LOOKUP_STRING) :: " + context.lookup(LOOKUP_STRING));
			bean = (IHelloWorld) context.lookup(LOOKUP_STRING);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return bean;
	}

	public static Context getInitialContext() throws NamingException {
		/*
		 * location of JBoss JNDI Service provider the client will use. It
		 * should be URL string.
		 */
		final String PROVIDER_URL = "jnp://localhost:1099";

		/*
		 * specifying the list of package prefixes to use when loading in URL
		 * context factories. colon separated
		 */
		final String JNP_INTERFACES = "org.jboss.naming:org.jnp.interfaces";

		/*
		 * Factory that creates initial context objects. fully qualified class
		 * name.
		 */
		final String INITIAL_CONTEXT_FACTORY = "org.jnp.interfaces.NamingContextFactory";

		Context initialContext = null;
		// Properties extends HashTable
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		prop.put(Context.URL_PKG_PREFIXES, JNP_INTERFACES);
		prop.put(Context.PROVIDER_URL, PROVIDER_URL);
		initialContext = new InitialContext(prop);
		return initialContext;
	}
}
