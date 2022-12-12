package aplicacion.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import aplicacion.modelo.Explotacion;
import aplicacion.utils.JPAUtil;

public class ExplotacionDAO {
	public void insertarExplotacionJPA(Explotacion explotacion) {
		
		//JPA
	    EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(explotacion);
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
		 
		
		
	}
	public void modificarExplotacionJPA(Explotacion explotacion) {
		
		//JPA
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(explotacion);
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
		
	}	
	public void eliminarExplotacionJPA(Explotacion explotacion) {
		
		//JPA
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		
		em.remove(em.contains(explotacion)? explotacion:em.merge(explotacion) );
		
		em.getTransaction().commit();
		}
		catch(PersistenceException e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
		}
		finally {
			em.close();
		}
				
	}
	public ArrayList<Explotacion> listarExplotacionJPA() {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			
			ArrayList<Explotacion> misUsuarios = (ArrayList<Explotacion>) em.createQuery("from Explotacion").getResultList();
			em.getTransaction().commit();
			return misUsuarios;
			
			}
			catch(PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
		
		return null;
		
	}
	public Explotacion buscarExplotacionPorCeaJPA(String cea) {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Explotacion explotacion = em.find(Explotacion.class, cea);
			System.out.println("La explotacion buscada tiene el CEA :"+explotacion.getCea());
			return explotacion;
			}
			catch(PersistenceException e) {
				em.getTransaction().rollback();
				System.out.println(e.getMessage());
			}
			finally {
				em.close();
			}
			return null;		
	}
	
	
	
	public void imprimirExplotacion(ArrayList<Explotacion> misExplotaciones) {
		System.out.println("Listado de explotaciones");
		for(Explotacion e:misExplotaciones) {
			e.toString();
		}
	}
}
