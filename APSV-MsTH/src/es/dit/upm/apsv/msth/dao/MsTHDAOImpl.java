package es.dit.upm.apsv.msth.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.upm.dit.apsv.msth.model.MsTH;

public class MsTHDAOImpl implements MsTHDAO{
	private static MsTHDAOImpl instance;//Esta sera mi unica instancia de esta clase permitida.
	private MsTHDAOImpl(){ //Hago que el constructor sea privado, solo esta clase puede llamarlo.
	}
	public static MsTHDAOImpl getInstance(){//Si no hay ninguna instancia la creo, si la hay, la devuelvo. Solamente permito una instancia.
		if (instance==null){
			instance=new MsTHDAOImpl();
		}
		return instance;
	}
	
	public List<MsTH> crear(String autor, String titulo, String resumen, String tutor, String secretario, String fichero) {
		EntityManager em= EMFService.get().createEntityManager();
		MsTH trabajo=new MsTH(autor, titulo, resumen, tutor, secretario, fichero);
		List<MsTH> devolver = new ArrayList<MsTH>();
		devolver.add(trabajo);
		em.persist(trabajo);
		em.close();
		return devolver;
	}

	public List<MsTH> leer(String autor) {
		EntityManager em= EMFService.get().createEntityManager();
		Query consulta = em.createQuery("SELECT trabajos FROM MsTH trabajos WHERE trabajos.autor= :autor");
		consulta.setParameter("autor", autor);
		List<MsTH> listado=consulta.getResultList();
		try{
			return listado;
		} 
		catch (Exception e){
			return null;
		}
	}

	public List<MsTH> listar_todos() {
		EntityManager em= EMFService.get().createEntityManager();
		Query consulta = em.createQuery("SELECT trabajos FROM MsTH trabajos");
		List<MsTH> listado=consulta.getResultList();
		return listado;
	}

	public List<MsTH> listar_de_tutor(String tutor) {
		EntityManager em= EMFService.get().createEntityManager();
		Query consulta = em.createQuery("SELECT trabajos FROM MsTH trabajos WHERE trabajos.tutor= :tutor");
		consulta.setParameter("tutor", tutor);
		List<MsTH> listado=consulta.getResultList();
		return listado;
	}

	
	public List<MsTH> listar_de_secretario(String secretario) {
		EntityManager em= EMFService.get().createEntityManager();
		Query consulta = em.createQuery("SELECT trabajos FROM MsTH trabajos WHERE trabajos.secretario= :secretario");
		consulta.setParameter("secretario", secretario);
		List<MsTH> listado=consulta.getResultList();
		return listado;
	}

	
	public List<MsTH> listar_por_estado(int estado) {
		EntityManager em= EMFService.get().createEntityManager();
		Query consulta = em.createQuery("SELECT trabajos FROM MsTH trabajos WHERE trabajos.estado= :estado");
		consulta.setParameter("estado", estado);//Ojo, igual es necesario parsear a string aqui
		List<MsTH> listado=consulta.getResultList();
		return listado;
	}

	
	public MsTH actualizar(MsTH trabajo) {
		EntityManager em= EMFService.get().createEntityManager();
		em.merge(trabajo);
		em.close();
		return trabajo;
		
	}

	
	public MsTH borrar(MsTH trabajo) {
		EntityManager em= EMFService.get().createEntityManager();
		em.remove(trabajo);
		em.close();
		return trabajo;
	}

}
