package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Timy;
import entity.Zapasy;

public class ZapasDAO {
	
	public List<Zapasy> getAll(EntityManager em) {
		Query q = em.createQuery("select z from Zapasy z");
		return q.getResultList();
	}
	
	public Zapasy getMost(EntityManager em) {
		Query q = em.createNativeQuery("select z from Zapasy as z join Timy as t "+
				"on t.id = z.hometeam or t.id = z.awayteam "+
				"group by t.name order by count(t.name) desc limit 1");
		Zapasy z = (Zapasy) q.getSingleResult();
		
		return z;
	}
	
	
	
	

}
