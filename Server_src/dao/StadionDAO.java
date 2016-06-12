package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Casomeraci;
import entity.Stadiony;

public class StadionDAO {
	
	public List<Stadiony> getAll(EntityManager em) {
		Query q = em.createQuery("select s from Stadiony s");
		return q.getResultList();
	}
	
public Stadiony findByName(String priezvisko,EntityManager em) {
		
		Query q = em.createQuery("select r from Stadiony r where r.name = :name");
		q.setParameter("name", priezvisko);
		Stadiony ref = (Stadiony) q.getSingleResult();
		//Rozhodcovia r = ref.get(0);
		
		return ref;
	}

}
