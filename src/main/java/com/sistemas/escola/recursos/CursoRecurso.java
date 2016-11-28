package com.sistemas.escola.recursos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoRecurso {

	@RequestMapping(method=RequestMethod.GET)
	public String todos(){
		return "Testando rest";
	}
}
