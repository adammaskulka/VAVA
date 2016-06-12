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
import dao.RozhodcaDAO;
import entity.Casomeraci;

/**
 * Session Bean implementation class CasomeracBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CasomeracBean implements CasomeracBeanRemote {

	CasomeracDAO casomeracDAO = new CasomeracDAO();
	
	@PersistenceContext
	private EntityManager em;

	
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Casomeraci> findAll() {
		List<Casomeraci> cass = casomeracDAO.getAll(em);
		return cass;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Casomeraci c) {
		
		em.persist(c);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Casomeraci findByName(String priezvisko) {
		Casomeraci cas = casomeracDAO.findByName(priezvisko, em);
		return cas;
	}

   

}
