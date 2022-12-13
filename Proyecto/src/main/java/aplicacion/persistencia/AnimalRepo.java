package aplicacion.persistencia;
 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aplicacion.modelo.Animal;
public interface AnimalRepo extends JpaRepository<Animal, Integer> {

	public Optional<Animal> findByCrotal(String crotal);
	
}
