package dao;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;



import entity.Rozhodcovia;

public class RozhodcaDAO {
	
	public List<Rozhodcovia> getAllStudents(EntityManager em) {
		Query q = em.createQuery("select r from Rozhodcovia r");
		return q.getResultList();
	}
	public Rozhodcovia findByName(String priezvisko,EntityManager em) {
		
		Query q = em.createQuery("select r from Rozhodcovia r where r.surname = :name");
		q.setParameter("name", priezvisko);
		Rozhodcovia ref = (Rozhodcovia) q.getSingleResult();
		//Rozhodcovia r = ref.get(0);
		
		return ref;
	}
	public List<Rozhodcovia> findNoMatchRefs(EntityManager em) {
		
		List<Rozhodcovia> count = null;
		
		try {
			Query query= em.createQuery("select r from Rozhodcovia r where r.id NOT IN ( select r.id from Rozhodcovia r "+
					"join Zapasy z on r.id=z.HR1 or r.id=z.HR2 or r.id=z.CR1 or r.id=z.CR2 group by r.id)");
					
			
			 count = query.getResultList();
			
		} catch (Exception e) {
			//log.log(Level.SEVERE, "count Tests  failed ", e);
           
		}
		finally {
			//currentSession.close();
		}
		//System.out.println(count.size());
		return count;
	}
	
	public List<Rozhodcovia> findAllNames(EntityManager em) {
		List<Rozhodcovia> list = null;
		
		//List list = null;
		
			Query q = em.createQuery("select r.surname from Rozhodcovia r");
			  
			List<Rozhodcovia> ref = q.getResultList(); 
			  
			   
		return ref;
	}
	public List<Rozhodcovia> findBad(EntityManager em) {
		List<Rozhodcovia> list = null;
		
		//List list = null;
		
		Query query= em.createQuery("select r.surname from Rozhodcovia r join Testy t on r.id=t.referee_id "+
				"group by r.surname having avg(t.rules) < (select avg(rules) from Testy) and avg(t.cooper) < (select avg(cooper) from Testy)");
				
			  
			List<Rozhodcovia> ref = query.getResultList(); 
			  
			   
		return ref;
	}
	

}
