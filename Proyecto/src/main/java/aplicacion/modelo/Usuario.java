package aplicacion.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@Column(name="dni")
	private String dni;
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellidos")
	private String apellidos;
	@Column(name="contacto")
	private Integer nTelefono;
	@Column(name="email")
	private String email;
	@OneToMany(cascade= {CascadeType.ALL}, mappedBy="usuario", fetch = FetchType.EAGER)
	private Set<Animal> animales; 
	@OneToMany(cascade= {CascadeType.ALL}, mappedBy="usuario", fetch = FetchType.EAGER)
	private Set<Explotacion> explotaciones;
	public Usuario(String dni, String nombre, String apellidos, Integer nTelefono, String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nTelefono = nTelefono;
		this.email = email;
		animales = new HashSet<Animal>();
		explotaciones = new HashSet<Explotacion>();
	}
	public Usuario(String nombre) {
		this.nombre = nombre;
		animales = new HashSet<Animal>();
		explotaciones = new HashSet<Explotacion>();
	}
	public Usuario() {
		animales = new HashSet<Animal>();
		explotaciones = new HashSet<Explotacion>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public Integer getnTelefono() {
		return nTelefono;
	}
	public void setnTelefono(Integer nTelefono) {
		this.nTelefono = nTelefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Animal> getAnimales() {
		return animales;
	}

	public void setAnimales(Set<Animal> animales) {
		this.animales = animales;
	}
	public Set<Explotacion> getExplotaciones() {
		return explotaciones;
	}

	public void setExplotaciones(Set<Explotacion> explotaciones) {
		this.explotaciones = explotaciones;
	}

	public void imprimir() {
		
		System.out.println("Usuario DNI=" + dni + ", nombre=" + getNombre());
		for(Explotacion e: explotaciones) {
			e.imprimir();
			System.out.println("");
		}	
	}
	@Override
	public String toString() {
		return "Usuario [DNI=" + dni + ", nombre=" + nombre + "]";
	}
	
}
