package edu.projeto.bancoh2.cobranca.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.projeto.bancoh2.cobranca.model.Titulo;
import edu.projeto.bancoh2.cobranca.model.enuns.StatusTitulo;
import edu.projeto.bancoh2.cobranca.repository.TitulosRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private TitulosRepository tituloRepository;

	public TituloController(TitulosRepository tituloRepository) {
		this.tituloRepository = tituloRepository;
	}

	@GetMapping
	public List<Titulo> getAllTitulos() {
		return tituloRepository.findAll();
	}

	@GetMapping("/{codigo}")
	public Optional<Titulo> getTituloByCodigo(@PathVariable Long codigo) {
		return tituloRepository.findById(codigo);
	}

	@PostMapping("/criar")
	public Titulo addTitulo(@RequestBody Titulo newTitulo) {
		return tituloRepository.save(newTitulo);
	}

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("todosStatusTitulo", StatusTitulo.values());
		return mv;
	}

	@PutMapping("/{codigo}")
	public Titulo updateTitulo(@RequestBody Titulo updatedTitulo, @PathVariable Long codigo) {
		updatedTitulo.setCodigo(codigo);
		return tituloRepository.save(updatedTitulo);
	}

	@DeleteMapping("/{codigo}")
	public void deleteTitulo(@PathVariable Long codigo) {
		tituloRepository.deleteById(codigo);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		tituloRepository.save(titulo);
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Foi salvo com sucesso");

		return mv;
	}

	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = tituloRepository.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;

	}

}
