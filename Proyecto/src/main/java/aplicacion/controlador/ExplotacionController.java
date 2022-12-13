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
import aplicacion.persistencia.AnimalDAO;
import aplicacion.persistencia.AnimalRepo;
import aplicacion.persistencia.ExplotacionDAO;
import aplicacion.persistencia.ExplotacionRepo;

@RequestMapping("/explotaciones")
@Controller
public class ExplotacionController {
	@Autowired
	private AnimalRepo animalRepo;
	@Autowired
	private ExplotacionRepo explotacionRepo;
	AnimalDAO animalDAO = new AnimalDAO();
	ExplotacionDAO explotacionDAO = new ExplotacionDAO();

	@GetMapping(value = { "", "/" })
	String explotaciones(Model model) {

		ArrayList<Explotacion> misExplotaciones = (ArrayList<Explotacion>) explotacionRepo.findAll();
		ArrayList<Animal> misAnimales = (ArrayList<Animal>) animalRepo.findAll();
		model.addAttribute("listaExplotaciones", misExplotaciones);
		model.addAttribute("listaAnimales", misAnimales);
		model.addAttribute("explotacionNueva", new Explotacion());

		return "explotaciones";
	}

	@PostMapping("/add")
	public String addExplotacion(@ModelAttribute("explotacionNueva") Explotacion explotacionNueva,
			BindingResult bidingresult) {
		explotacionDAO.insertarExplotacionJPA(explotacionNueva);
		return "redirect:/explotaciones";
	}

	@GetMapping({ "/{cea}" })
	String ceaExplotacion(Model model, @PathVariable String cea) {
		ArrayList<Explotacion> misExplotaciones = (ArrayList<Explotacion>) explotacionRepo.findAll();
		ArrayList<Animal> misAnimales = (ArrayList<Animal>) animalRepo.findAll();

		model.addAttribute("listaExplotaciones", misExplotaciones);
		model.addAttribute("listaAnimales", misAnimales);
		Explotacion explotacionMostrar = explotacionRepo.findByCea(cea).get();
		model.addAttribute("explotacionMostrar", explotacionMostrar);

		return "explotacion";
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
