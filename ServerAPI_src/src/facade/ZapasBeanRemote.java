package facade;

import java.util.List;

import javax.ejb.Remote;

import entity.Zapasy;

@Remote
public interface ZapasBeanRemote {
	
	public List<Zapasy> findAll();
	
	public void persist(Zapasy z);
	
	public Zapasy getMost();

}
