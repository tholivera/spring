package br.org.generation.habilidades.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping("/habilidades")
public class HabilidadesController {
	@GetMapping
	public String habilidades() {
		return "\nAdaptabilidade, persistência e atenção aos detalhes.";
	}

}