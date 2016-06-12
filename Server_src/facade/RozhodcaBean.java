package facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.RozhodcaDAO;
import entity.Rozhodcovia;

/**
 * Session Bean implementation class RozhodcaBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RozhodcaBean implements RozhodcaBeanRemote {
	
	RozhodcaDAO rozhodcaDAO = new RozhodcaDAO();
	
	@PersistenceContext
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Rozhodcovia> getRefs() {
		
		List<Rozhodcovia> refs = rozhodcaDAO.getAllStudents(em);
		//System.out.println("ROZHODCOVIA-> " +refs.size());
		return refs;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void persist(Rozhodcovia r) {
		
		em.persist(r);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Rozhodcovia findByName(String priezvisko) {
		
		Rozhodcovia ref = rozhodcaDAO.findByName(priezvisko, em);
		return ref;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Rozhodcovia r) {
		
		em.merge(r);
		
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Rozhodcovia r) {
		
		em.remove(em.merge(r));
		
	}

	@Override
	public List<Rozhodcovia> NoMatchRefs() {
		List<Rozhodcovia> res = rozhodcaDAO.findNoMatchRefs(em);
		return res;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Rozhodcovia> findAllNames() {
		List<Rozhodcovia> res = rozhodcaDAO.findAllNames(em);
		return res;
	}

	@Override
	public List<String> findBadRefs() {
		List<Rozhodcovia> res = rozhodcaDAO.findBad(em);
		List<String> result = null;
		
		for(Rozhodcovia r : res) {
			result.add(r.getSurname());
		}
		
		return result;
	}

}
