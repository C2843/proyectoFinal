package aplicacion.persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import aplicacion.modelo.Usuario;
import aplicacion.utils.JPAUtil;

public class UsuarioDAO {
	public void insertarUsuarioJPA(Usuario usuario) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 try {
			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
			
		} finally {
			em.close();
		}
		 
	 }
	public void editarUsuarioJPA (Usuario usuario) {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
	 }
	public void eliminarUsuarioJPA (Usuario usuario) {

		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			Usuario u = em.find(Usuario.class, usuario.getDni());
			em.getTransaction().begin();
			em.remove(u);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
	 }
	public ArrayList<Usuario> listarUsuariosJPA () {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) em.createQuery("from Usuario").getResultList();
			em.getTransaction().commit();
			return listaUsuarios;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
		 return null;
	}
 public void imprimirUsuarios(ArrayList<Usuario> listaUsuarios) {
		 
		 for (Usuario u: listaUsuarios) {
			 u.imprimir();
		 }
	 }
	 
	 public Usuario readUsuario(String dni) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 Usuario us = em.find(Usuario.class, dni);
		 
		 return us;
	 }
	 
	 
	 public Usuario buscarDniJPA(String dni) {
	        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	        try {
	            entity.getTransaction().begin();
	            Usuario usuario = entity.find(Usuario.class, dni);
	            entity.getTransaction().commit();
	            System.out.println("El usuario con DNI " +dni+ " es " +usuario.getNombre());
	            return usuario;
	        } catch (PersistenceException exception) {
	            entity.getTransaction().rollback();
	            System.out.println(exception.getMessage());
	        } finally {
	            entity.close();
	        }
	        return null;    
	    }
	 
	
}
