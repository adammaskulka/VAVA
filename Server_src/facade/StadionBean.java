package facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.RozhodcaDAO;
import dao.StadionDAO;
import entity.Stadiony;

/**
 * Session Bean implementation class StadionBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class StadionBean implements StadionBeanRemote {

	StadionDAO stadionDAO = new StadionDAO();
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Stadiony> findAll() {
		List<Stadiony> stadiums = stadionDAO.getAll(em);
		return stadiums;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Stadiony s) {
		em.persist(s);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Stadiony findByName(String priezvisko) {
		Stadiony stadion = stadionDAO.findByName(priezvisko, em);
		return stadion;
	}

}
