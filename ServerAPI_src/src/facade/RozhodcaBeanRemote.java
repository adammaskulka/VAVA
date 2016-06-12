package facade;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import entity.Rozhodcovia;

@Remote
public interface RozhodcaBeanRemote {
	
	public List<Rozhodcovia> getRefs();
	
	public void persist(Rozhodcovia r);
	
	public Rozhodcovia findByName(String priezvisko);
	
	public void update(Rozhodcovia r);
	
	public void delete(Rozhodcovia r);
	
	public List<Rozhodcovia> NoMatchRefs();
	
	public List<Rozhodcovia> findAllNames();
	
	public List<String> findBadRefs();

}
