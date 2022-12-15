package aplicacion.modelo;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="animales")
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="crotal")
	private String crotal;
	@Column(name="tipoanimal")
	private String tipoAnimal;
	@Column(name="raza")
	private String raza;
	@Column(name="fnacimiento")
	private String fNacimiento;
	@Column(name="genero")
	private String genero;
	@ManyToOne // (cascade = { CascadeType.ALL },optional = true)
	@JoinColumn(name = "id_explotacion", nullable = true)
	@JsonIgnore
	private Explotacion explotacion;
	public Animal() {
	}
	
	public Animal(String crotal, String tipoAnimal, String raza, String fNacimiento, String genero) {
		super();
		this.crotal = crotal;
		this.tipoAnimal = tipoAnimal;
		this.raza = raza;
		this.fNacimiento = fNacimiento;
		this.genero = genero;
	}
	public Animal(Explotacion e){
		explotacion=e;
	}

	public Explotacion getExplotacion() {
		return explotacion;
	}

	public void setExplotacion(Explotacion explotacion) {
		this.explotacion = explotacion;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCrotal() {
		return crotal;
	}

	public void setCrotal(String crotal) {
		this.crotal = crotal;
	}

	public String getTipoAnimal() {
		return tipoAnimal;
	}

	public void setTipoAnimal(String tipoAnimal) {
		this.tipoAnimal = tipoAnimal;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(String fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	


}
