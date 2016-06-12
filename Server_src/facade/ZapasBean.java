package facade;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.ZapasDAO;
import entity.Zapasy;


/**
 * Session Bean implementation class ZapasBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ZapasBean implements ZapasBeanRemote {
	
	private static final Logger log = Logger.getLogger(ZapasBean.class.getName());
	
	@PersistenceContext
	private EntityManager em;
	
	ZapasDAO zapasDAO = new ZapasDAO();

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Zapasy> findAll() {
		List<Zapasy> matches = zapasDAO.getAll(em);
		return matches;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Zapasy z) {
		em.persist(z);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Zapasy getMost() {
		log.log(Level.INFO, "Rozhodca remote lookup error");
		return zapasDAO.getMost(em);
	}

}
