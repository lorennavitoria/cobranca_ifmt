package edu.projeto.bancoh2.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.projeto.bancoh2.cobranca.model.Titulo;
import edu.projeto.bancoh2.cobranca.repository.TitulosRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TitulosRepository titulosRepository;
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		titulosRepository.save(titulo);
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Foi salvo com sucesso");
		
		return mv;
	}

}
