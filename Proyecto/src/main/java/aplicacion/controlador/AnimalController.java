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
import aplicacion.persistencia.AnimalDAO;
import aplicacion.persistencia.AnimalRepo;

@RequestMapping("/animales")
@Controller
public class AnimalController {
	@Autowired
	private AnimalRepo animalRepo;
	AnimalDAO animalDAO = new AnimalDAO();

	@GetMapping(value = { "", "/" })
	String animales(Model model) {

		ArrayList<Animal> misAnimales = (ArrayList<Animal>) animalRepo.findAll();

		model.addAttribute("listaAnimales", misAnimales);
		model.addAttribute("animalNuevo", new Animal());
		model.addAttribute("animalEditado", new Animal());
		return "animales";
	}

	@PostMapping("/edit/{crotal}")
	public String editarAnimal(@PathVariable String crotal, @ModelAttribute("animalEditado") Animal animalEditado,
			BindingResult bidingresult) {
		Animal animalaEditar = animalRepo.findByCrotal(crotal).get();
		animalaEditar.setfNacimiento(animalEditado.getfNacimiento());
		animalaEditar.setRaza(animalEditado.getRaza());
		animalRepo.save(animalaEditar);
		return "redirect:/animales";
	}

	@GetMapping({ "/{crotal}" })
	String crotalAnimal(Model model, @PathVariable String crotal) {
		ArrayList<Animal> misAnimales = (ArrayList<Animal>) animalRepo.findAll();
		model.addAttribute("listaAnimales", misAnimales);
		Animal animalMostrar = animalRepo.findByCrotal(crotal).get();
		model.addAttribute("animalMostrar", animalMostrar);
		return "animal";
	}

	@GetMapping({ "/delete/{crotal}" })
	String deleteAnimal(Model model, @PathVariable String crotal) {
		Animal animalaBorrar = animalRepo.findByCrotal(crotal).get();
		animalRepo.delete(animalaBorrar);
		return "redirect:/animales";

	}

	@PostMapping("/add")
	public String addAnimal(@ModelAttribute("animalNuevo") Animal animalNuevo, BindingResult bidingresult) {
		animalDAO.insertarAnimalJPA(animalNuevo);
		return "redirect:/animales";
	}
}
