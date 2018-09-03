package model.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Dto.Registro;

public class RegistroDao {
	private static final String PersistenceUnitName = "ProyectoArq";
	private static EntityManager EM;
	private static EntityManagerFactory EF;

	public Integer consultarId() {
		int maximo=0;
		TypedQuery<Registro> query= EM.createQuery("select t from Registro t",Registro.class);
		List<Registro> registros = query.getResultList();
		for(Registro r: registros) {
			maximo= r.getIdregistro();
		}
		return maximo+1;
	}
		
	public RegistroDao() {
		EF = Persistence.createEntityManagerFactory(PersistenceUnitName);
		EM = EF.createEntityManager();
	}

	public boolean create(Registro reg) {

		try {
			EM.getTransaction().begin();
			EM.persist(reg);
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
	public List<Registro> SelectCon(){
		List<Registro> lista;
		lista= EM.createQuery("from registro c").getResultList();
		EM.close();
		return lista;
	}
	
	public Registro Select(int id) {
		Registro registro = EM.find(Registro.class,id);
		EM.close();
		return registro;
		
	} 

	public int UpdateRegState( int state,int id) {
		
		try {
			EM.getTransaction().begin();
			Registro reg= EM.find(Registro.class, id);
			reg.setEstado(state);
			EM.persist(reg);
			EM.getTransaction().commit();
			EM.close();
			
			return 0;
		} catch (Exception e) {
			
			EM.getTransaction().rollback();
			EM.close();
			System.out.println("ERROR 	"+e.getMessage());
			return 1;
		}
	}
	
}
