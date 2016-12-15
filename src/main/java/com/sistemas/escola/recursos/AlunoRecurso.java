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
import com.sistemas.escola.servico.AlunoServico;


@RestController
@RequestMapping("/alunos")
public class AlunoRecurso {


	@Autowired
	private AlunoServico as;


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Aluno>> todos() {
		List<Aluno> lista = as.buscarTodosOrdenadosPorNome();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Integer id) {
		Aluno al = as.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(al);
	}


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> criar(@RequestBody Aluno aluno) {
		aluno = as.inserir(aluno);
		URI uri = getUri("/{id}", aluno.getCodAluno());
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Integer id) {
		as.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Aluno aluno, @PathVariable("id") Integer id) {
		aluno.setCodAluno(id);
		aluno = as.atualizar(aluno);
		return ResponseEntity.noContent().build();
	}
	
	private URI getUri(String nome, Integer valor) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(nome).buildAndExpand(valor).toUri();
	}
	
	
}

