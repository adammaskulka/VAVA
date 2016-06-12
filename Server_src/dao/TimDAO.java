package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Casomeraci;
import entity.Rozhodcovia;
import entity.Timy;

public class TimDAO {
	
	public List<Timy> getAll(EntityManager em) {
		Query q = em.createQuery("select t from Timy t");
		return q.getResultList();
	}
	
public Timy findByName(String priezvisko,EntityManager em) {
		
		Query q = em.createQuery("select t from Timy t where t.name = :name");
		q.setParameter("name", priezvisko);
		Timy ref = (Timy) q.getSingleResult();
		//Rozhodcovia r = ref.get(0);
		
		return ref;
	}

}
