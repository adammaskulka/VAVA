package facade;

import java.util.List;

import javax.ejb.Remote;

import entity.Casomeraci;
import entity.Rozhodcovia;

@Remote
public interface CasomeracBeanRemote {
	
	public List<Casomeraci> findAll();
	
	public void persist(Casomeraci c);
	
	public Casomeraci findByName(String priezvisko);

}
