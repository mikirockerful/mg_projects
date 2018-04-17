package es.upm.dit.apsv.msth.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //Esto indica que esta clase es una entidad JPA (una clase cuyos objetos pueden ser guardados en una base de datos)
public class MsTH implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String autor;
	private String titulo;
	private String resumen;
	private String tutor;
	private String secretario;
	private String fichero;
	private int estado;
	
	public MsTH(String autor, String titulo, String resumen, String tutor, String secretario, String fichero){
		this.autor=autor;
		this.titulo=titulo;
		this.resumen=resumen;
		this.tutor=tutor;
		this.secretario=secretario;
		this.fichero=fichero;
		this.estado=1;
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public String getSecretario() {
		return secretario;
	}

	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}

	public String getFichero() {
		return fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
