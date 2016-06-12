package test;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import entity.Rozhodcovia;
import facade.RozhodcaBeanRemote;
import facade.TestBeanRemote;
import facade.ZapasBeanRemote;

public class Client {

	public static void main(String[] args) throws Exception {
		
		Context ctx = new InitialContext();
		RozhodcaBeanRemote remote = (RozhodcaBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/RozhodcaBean!facade.RozhodcaBeanRemote");
		ZapasBeanRemote remoteZapas = (ZapasBeanRemote)ctx.lookup("/EJBTestEAR/EJBTestServer/ZapasBean!facade.ZapasBeanRemote");
		
		System.out.println(remoteZapas.getMost().getRozhodcoviaByHr1());
		
		/*List<Rozhodcovia> refs = remote.getRefs();
		
		System.out.println(refs.size() + refs.get(1).toString());*/

	}

}
