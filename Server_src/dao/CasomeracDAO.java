package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import entity.Casomeraci;
import entity.Rozhodcovia;

public class CasomeracDAO {
	
	private static final Logger log = Logger.getLogger(CasomeracDAO.class.getName());
	

	public List<Casomeraci> getAll(EntityManager em) {
		Query q;
		
			q = em.createQuery("select c from Casomeraci c");
		
		return q.getResultList();
	}
	
public Casomeraci findByName(String priezvisko,EntityManager em) {
		
		Casomeraci ref = null;
		try {
			Query q = em.createQuery("select r from Casomeraci r where r.surname = :name");
			q.setParameter("name", priezvisko);
			ref = (Casomeraci) q.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.log(Level.SEVERE, "select r from Casomeraci r where r.surname error",e);
		}
		//Rozhodcovia r = ref.get(0);
		
		return ref;
	}
}
