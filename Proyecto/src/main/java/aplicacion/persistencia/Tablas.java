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
        Animal a3 = new Animal("ES0545485441", "Bovino", "Asturiana de los Valles", "16/03/2020", "Macho");
        Animal a4 = new Animal("ES0803485598", "Bovino", "Asturiana de los Valles", "10/10/2008", "Hembra");
        Usuario u2=new Usuario("Y0053939H", "Robert Cristian","Scripcaru", 636017566, "robertcs62@educastur.es");
        Explotacion e2=new Explotacion("ES058584583", 2);
        e1.getAnimales().add(a1);
        e1.getAnimales().add(a2);
        a1.setExplotacion(e1);
        a2.setExplotacion(e1);
        u1.getExplotaciones().add(e1);
        e1.setUsuario(u1);
        e2.getAnimales().add(a3);
        e2.getAnimales().add(a4);
        a3.setExplotacion(e2);
        a4.setExplotacion(e2);
        u2.getExplotaciones().add(e2);
        e2.setUsuario(u2);
             
        UsuarioDAO usuarioDao = new UsuarioDAO();
        AnimalDAO animalDao = new AnimalDAO();
        ExplotacionDAO explotacionDao=new ExplotacionDAO();
        
        usuarioDao.insertarUsuarioJPA(u1);
        explotacionDao.insertarExplotacionJPA(e1);
        animalDao.insertarAnimalJPA(a1);
        animalDao.insertarAnimalJPA(a2);
        usuarioDao.insertarUsuarioJPA(u2);
        explotacionDao.insertarExplotacionJPA(e2);
        animalDao.insertarAnimalJPA(a3);
        animalDao.insertarAnimalJPA(a4);
        
	}
}
