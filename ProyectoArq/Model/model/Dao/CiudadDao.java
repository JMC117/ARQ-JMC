package model.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Dto.Ciudad;

public class CiudadDao {
	private static final String PersistenceUnitName = "ProyectoArq";
	private static EntityManager EM;
	private static EntityManagerFactory EF;

	public CiudadDao() {
		EF = Persistence.createEntityManagerFactory(PersistenceUnitName);
		EM = EF.createEntityManager();
	}

	public boolean Create(Ciudad ciu) {

		try {

			EM.getTransaction().begin();
			EM.persist(ciu);
			EM.getTransaction().commit();
			EM.close();
			return true;
		} catch (Exception e) {

			EM.getTransaction().rollback();
			EM.close();
			System.out.println("ERROR" + e.getMessage());

			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Ciudad> SelectCon() {
		List<Ciudad> lista;
		lista = EM.createQuery("from ciudad c").getResultList();
		EM.close();

		return lista;

	}

	
	
	public Ciudad Select(int id) {

		Ciudad ciudad = EM.find(Ciudad.class, id);
		EM.close();

		return ciudad;

	}

	public int UpdateCiuNom(String nombre,int id) {

		try {
			
			EM.getTransaction().begin();
			Ciudad ciudad = EM.find(Ciudad.class, id);
			ciudad.setNombre(nombre);
			EM.persist(ciudad);
			EM.getTransaction().commit();
			EM.close();

			return 0;

		} catch (Exception e) {

			EM.getTransaction().rollback();
			EM.close();
			System.out.println("ERROR" + e.getMessage());

			return 1;
		}

	}
	
	public int UpdateCiuState(int state,int id) {

		try {
			
			EM.getTransaction().begin();
			Ciudad ciudad = EM.find(Ciudad.class, id);
			ciudad.setEstado(state);
			EM.persist(ciudad);
			EM.getTransaction().commit();
			EM.close();

			return 0;

		} catch (Exception e) {

			EM.getTransaction().rollback();
			EM.close();
			System.out.println("ERROR" + e.getMessage());

			return 1;
		}

	}
	

}
