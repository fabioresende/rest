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


import com.sistemas.escola.dominio.Aluno;
import com.sistemas.cursos.servico.AlunoServico;


@RestController
@RequestMapping("/alunos")
public class AlunoRecurso {


	@Autowired
	private AlunoServico crs;


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Aluno>> todos() {
		List<Aluno> lista = crs.buscarTodosOrdenadosPorNome();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Integer id) {
		Aluno cs = crs.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(cs);
	}


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> criar(@RequestBody Aluno curso) {
		curso = crs.inserir(curso);
		URI uri = getUri("/{id}", curso.getCodAluno());
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Integer id) {
		crs.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Aluno curso, @PathVariable("id") Integer id) {
		curso.setCodAluno(id);
		curso = crs.atualizar(curso);
		return ResponseEntity.noContent().build();
	}
	
	private URI getUri(String nome, Integer valor) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(nome).buildAndExpand(valor).toUri();
	}
}

