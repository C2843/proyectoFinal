package aplicacion.persistencia;

import aplicacion.modelo.Animal;
import aplicacion.modelo.Explotacion;
import aplicacion.modelo.Usuario;

public class Tablas {
public void crearTablas() {
        Animal a1 = new Animal("ES080308280466", "Bovino", "Asturiana de los Valles", "16/03/2020", "Macho");
        Animal a2 = new Animal("ES080306160778", "Bovino", "Asturiana de los Valles", "10/10/2008", "Hembra");
        Usuario u1=new Usuario("09382041L", "Jose Enrique","Alonso López", 626797115, "robert-cristian@hotmail.es");
        Explotacion e1=new Explotacion("ES000034453", 2);
        u1.getAnimales().add(a1);
        u1.getAnimales().add(a2);
        a1.setUsuario(u1);
        a2.setUsuario(u1);
        e1.getAnimales().add(a1);
        e1.getAnimales().add(a2);
        a1.setExplotacion(e1);
        a2.setExplotacion(e1);
        u1.getExplotaciones().add(e1);
        e1.setUsuario(u1);
             
        UsuarioDAO usuarioDao = new UsuarioDAO();
        AnimalDAO animalDao = new AnimalDAO();
        ExplotacionDAO explotacionDao=new ExplotacionDAO();
        
        //usuarioDao.insertarUsuarioJPA(u1);
        //explotacionDao.insertarExplotacionJPA(e1);
        //animalDao.insertarAnimalJPA(a1);
        //animalDao.insertarAnimalJPA(a2);
        
	}
}
