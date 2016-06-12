package facade;

import java.util.List;

import javax.ejb.Remote;

import entity.Timy;

@Remote
public interface TimBeanRemote {
	
	public List<Timy> findAll();
	
	public void persist(Timy t);
	
	public Timy findByName(String meno);

}
