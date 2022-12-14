package aplicacion.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="explotaciones")
public class Explotacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="cea")
	private String cea;
	@Transient
	private Integer nAnimales;
	
	@OneToMany(cascade= {CascadeType.MERGE}, mappedBy="explotacion", fetch = FetchType.EAGER)
	private Set<Animal> animales;
	
	@ManyToOne // (cascade = { CascadeType.ALL },optional = true)
	@JoinColumn(name = "id_usuario", nullable = true)
	@JsonIgnore
	private Usuario usuario;
	
	public Explotacion(String cea, Integer nAnimales) {
		super();
		this.cea = cea;
		this.nAnimales = nAnimales;
		animales = new HashSet<Animal>();
	}
	public Explotacion(Usuario u) {
		usuario=u;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public Explotacion() {
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCea() {
		return cea;
	}
	public void setCea(String cea) {
		this.cea = cea;
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
