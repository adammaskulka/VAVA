package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.Rozhodcovia;
import entity.Testy;

public class TestDAO {
	
	public List<Testy> getAllTests(EntityManager em) {
		Query q = em.createQuery("select t from Testy t");
		return q.getResultList();
	}
	
	public List<Testy> findTestByDate(Rozhodcovia r,Date from, Date to,EntityManager em) {
		int id = r.getId();
		Query query= em.createQuery("FROM Testy AS t WHERE t.examdate BETWEEN :stDate AND :edDate AND referee_id = :ID ORDER BY t.examdate")
				.setParameter("stDate", from)
				.setParameter("edDate", to)
				.setParameter("ID", id);
		List<Testy> testy = (List<Testy>) query.getResultList();
				return testy; 
	}

}
