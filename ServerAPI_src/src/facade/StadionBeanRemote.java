package facade;

import java.util.List;

import javax.ejb.Remote;

import entity.Rozhodcovia;
import entity.Stadiony;

@Remote
public interface StadionBeanRemote {
	
	public List<Stadiony> findAll();
	
	public void persist(Stadiony s);
	
	public Stadiony findByName(String priezvisko);

}
