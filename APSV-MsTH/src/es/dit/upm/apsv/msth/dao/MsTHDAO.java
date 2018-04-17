package es.dit.upm.apsv.msth.dao;



import java.util.List;

import es.upm.dit.apsv.msth.model.MsTH;

public interface MsTHDAO {
	public List<MsTH> crear(String autor, String titulo, String resumen, String tutor, String secretario, String fichero);
	public List<MsTH> leer (String autor);
	public List<MsTH> listar_todos();
	public List<MsTH> listar_de_tutor(String tutor);
	public List<MsTH> listar_de_secretario(String secretario);
	public List<MsTH> listar_por_estado(int estado);
	public MsTH actualizar(MsTH trabajo);
	public MsTH borrar(MsTH trabajo);
	
}
