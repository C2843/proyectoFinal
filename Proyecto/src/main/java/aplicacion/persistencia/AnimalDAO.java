package aplicacion.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import aplicacion.modelo.Animal;
import aplicacion.utils.JPAUtil;

public class AnimalDAO {
	public void insertarAnimalJPA(Animal animal) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.persist(animal);
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
	
	public void modificarAnimalJPA(Animal animal) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.merge(animal);
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
	public void eliminarAnimalJPA(Animal animal) {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
		em.getTransaction().begin();
		em.remove(em.contains(animal)? animal:em.merge(animal) );
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
	
	public ArrayList<Animal> listarAnimalJPA() {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			
			ArrayList<Animal> misAnimales = (ArrayList<Animal>) em.createQuery("from Animal").getResultList();
			em.getTransaction().commit();
			return misAnimales;
			
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
	public Animal buscarAnimalPorCrotalJPA(String crotal) {
		
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			Animal animal = em.find(Animal.class, crotal);
			System.out.println("El animal buscado tiene el crotal :"+animal.getCrotal());
			return animal;
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
	
	public void imprimir(ArrayList<Animal> misAnimales) {
		System.out.println("Listado de Animales");
		for(Animal a:misAnimales) {
			a.toString();
		}
	}
}
