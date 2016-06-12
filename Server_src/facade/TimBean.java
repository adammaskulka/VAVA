package facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.CasomeracDAO;
import dao.TimDAO;
import entity.Rozhodcovia;
import entity.Timy;

/**
 * Session Bean implementation class TimBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TimBean implements TimBeanRemote {

	TimDAO timDAO = new TimDAO();
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Timy> findAll() {
		List<Timy> teams = timDAO.getAll(em);
		return teams;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Timy t) {
		em.persist(t);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Timy findByName(String meno) {
		
		Timy ref = timDAO.findByName(meno, em);
		return ref;
	}

}
