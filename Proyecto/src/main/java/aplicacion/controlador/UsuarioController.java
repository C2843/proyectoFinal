package aplicacion.controlador;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import aplicacion.modelo.Animal;
import aplicacion.modelo.Explotacion;
import aplicacion.modelo.Usuario;
import aplicacion.persistencia.AnimalDAO;
import aplicacion.persistencia.AnimalRepo;
import aplicacion.persistencia.ExplotacionDAO;
import aplicacion.persistencia.ExplotacionRepo;
import aplicacion.persistencia.UsuarioDAO;
import aplicacion.persistencia.UsuarioRepo;

@RequestMapping("/usuarios")
@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepo usuarioRepo;
	@Autowired
	private ExplotacionRepo explotacionRepo;
	UsuarioDAO usuarioDAO = new UsuarioDAO();
	ExplotacionDAO explotacionDAO = new ExplotacionDAO();

	@GetMapping(value = { "", "/" })
	String usuarios(Model model) {
		ArrayList<Usuario> misUsuarios = (ArrayList<Usuario>) usuarioRepo.findAll();
		ArrayList<Explotacion> misExplotaciones = (ArrayList<Explotacion>) explotacionRepo.findAll();
		model.addAttribute("listaUsuarios", misUsuarios);
		model.addAttribute("listaExplotaciones", misExplotaciones);
		model.addAttribute("usuarioNuevo", new Usuario());

		return "usuarios";
	}

	@PostMapping("/add")
	public String addUsuario(@ModelAttribute("usuarioNuevo") Usuario usuarioNuevo,
			BindingResult bidingresult) {
		usuarioDAO.insertarUsuarioJPA(usuarioNuevo);
		return "redirect:/usuarios";
	}

	@GetMapping({ "/{dni}" })
	String dniUsuario(Model model, @PathVariable String dni) {
		ArrayList<Usuario> misUsuarios = (ArrayList<Usuario>) usuarioRepo.findAll();
		ArrayList<Explotacion> misExplotaciones = (ArrayList<Explotacion>) explotacionRepo.findAll();

		model.addAttribute("listaUsuarios", misUsuarios);
		model.addAttribute("listaExplotaciones", misExplotaciones);
		Usuario usuarioMostrar = usuarioRepo.findByDni(dni).get();
		model.addAttribute("usuarioMostrar", usuarioMostrar);

		return "usuario";
	}

	@PostMapping("/edit/{cea}")
	public String editarExplotacion(@PathVariable String cea,
			@ModelAttribute("explotacionaEditar") Explotacion explotacionEditada, BindingResult bidingresult) {
		Explotacion explotacionaEditar = explotacionRepo.findByCea(cea).get();
		explotacionaEditar.setCea(explotacionEditada.getCea());
		explotacionRepo.save(explotacionaEditar);
		return "redirect:/explotaciones";
	}

	@GetMapping({ "/delete/{cea}" })
	String deleteExplotacion(Model model, @PathVariable String cea) {
		Explotacion explotacionaBorrar = explotacionRepo.findByCea(cea).get();
		explotacionRepo.delete(explotacionaBorrar);
		return "redirect:/explotaciones";

	}

}

