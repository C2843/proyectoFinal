package aplicacion.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="explotaciones")
public class Explotacion {
	@Id
	@Column(name="cea")
	private String cea;
	@Column(name="animal")
	private Animal animal;
	@Column(name="nAnimales")
	private Integer nAnimales;
	
	@OneToMany(cascade= {CascadeType.ALL}, mappedBy="explotacion", fetch = FetchType.EAGER)
	private Set<Animal> animales;
	
	@ManyToOne // (cascade = { CascadeType.ALL },optional = true)
	@JoinColumn(name = "dni_usuario", nullable = true)
	@JsonIgnore
	private Usuario usuario;
	public Explotacion(String cea, Integer nAnimales) {
		super();
		this.cea = cea;
		this.nAnimales = nAnimales;
		animales = new HashSet<Animal>();
	}
	public Explotacion(String cea) {
		this.cea = cea;
		animales = new HashSet<Animal>();
	}
	public Explotacion() {
		animales = new HashSet<Animal>();
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Set<Animal> getAnimales() {
		return animales;
	}

	public void setAnimales(Set<Animal> animales) {
		this.animales = animales;
	}
	
	public String getCea() {
		return cea;
	}
	public void setCea(String cea) {
		this.cea = cea;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public Integer getnAnimales() {
		return nAnimales;
	}
	public void setnAnimales(Integer nAnimales) {
		this.nAnimales = nAnimales;
	}
	@Override
	public String toString() {
		return imprimir();
	}
	public String imprimir() {

		String resultado = "Animales de la explotación " + cea + "\n";
		if (animales.size() == 0) {

		} else {
			for (Animal a : animales) {
				resultado = resultado + a.toString();
			}
		}
		return resultado;
	}
}
