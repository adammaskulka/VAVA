package test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import facade.TestBeanRemote;


public class ClientTestik {
	
 TestBeanRemote remote;
Context ctx;
	

	/*public void vykonaj() {
		if(remote==null) {
			
			Context ctx;
			try {
				ctx = new InitialContext();
				remote = (TestBeanRemote)ctx.lookup("ejb:EJBTestEAR/EJBTestServer//TestBean!test.TestBeanRemote");
				System.out.println(remote.strPlus("Ahoj", "Jaro"));
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
			
		}
	}*/

public void vykonaj() {
	if (remote == null) {
		try {
			ctx = new InitialContext();
			remote =  (TestBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/TestBean!facade.TestBeanRemote");
			//System.out.println(remote.strPlus("Ahoj", "Jaro"));
		} catch (NamingException e) {
			ctx = null;
			remote = null;
			e.printStackTrace();
		}
	}
}
	
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		
	/*	final Hashtable props = new Hashtable();
		props.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		props.put("java.naming.factory.url.pkgs", "org.jboss.ejb.client.naming");
		props.put("java.naming.provider.url", "remote://localhost:4447");
		props.put("jboss.naming.client.ejb.context","true");
		
		//System.out.println(props.toString());
		
		Context ctx = new InitialContext(props);*/
		
		/*final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");*/
		
	//	Context ctx = new InitialContext(jndiProperties);
		
		
		ClientTestik ct = new ClientTestik();
		ct.vykonaj();
		
	}
		
}
