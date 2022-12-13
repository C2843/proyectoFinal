package aplicacion.persistencia;
 

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import aplicacion.modelo.Explotacion;
public interface ExplotacionRepo extends JpaRepository<Explotacion, Integer> {

	public Optional<Explotacion> findByCea(String cea);
	
}
