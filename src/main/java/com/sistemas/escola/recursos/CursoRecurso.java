package com.sistemas.escola.recursos;

import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sistemas.escola.dominio.Curso;
import com.sistemas.escola.repositorio.CursoRepositorio;

@RestController
@RequestMapping("/cursos")
public class CursoRecurso {

	@Autowired
	private CursoRepositorio repo;


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Curso>> todos() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Integer id) {
		Curso crs = repo.findOne(id);
		if (crs == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(crs);
	}


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> criar(@RequestBody Curso curso) {
		curso = repo.save(curso);
		URI uri = getUri("/{id}", curso.getCodCurso());
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Integer id) {
		repo.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Curso curso, @PathVariable("id") Integer id) {
		curso.setCodCurso(id);
		repo.save(curso);
		return ResponseEntity.noContent().build();
	}
	
	private URI getUri(String nome, Integer valor) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(nome).buildAndExpand(valor).toUri();
	}

}
