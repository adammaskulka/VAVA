package facade;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entity.Rozhodcovia;
import entity.Testy;

@Remote
public interface TestBeanRemote {
	
	public List<Testy> findAll();
	
	public void persist(Testy t);
	
	public List<Testy> findByDateRef(Rozhodcovia r,Date from,Date to);

}
