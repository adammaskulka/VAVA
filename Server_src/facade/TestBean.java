package facade;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.RozhodcaDAO;
import dao.TestDAO;
import entity.Rozhodcovia;
import entity.Testy;
import executive.TestExecutiveBean;
import facade.TestBeanRemote;

/**
 * Session Bean implementation class TestBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TestBean implements TestBeanRemote {
	
	@PersistenceContext
	private EntityManager em;

	TestDAO testDAO = new TestDAO();

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Testy> findAll() {
		List<Testy> tests = testDAO.getAllTests(em);
		return tests;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Testy t) {
		
		em.persist(t);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Testy> findByDateRef(Rozhodcovia r, Date from, Date to) {
		List<Testy> tests = testDAO.findTestByDate(r, from, to, em);
		return tests;
	}

	
	

}
