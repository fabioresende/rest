package com.sistemas.escola.repositorio;

import java.util.List;

import com.sistemas.escola.dominio.Aluno;
import com.sistemas.escola.dominio.Curso;

public interface AlunoRepositorioCustom {
	
	public List<Aluno> buscarTodosOrdenadosPorNome();
	public List<Aluno> buscarPorNome(String trecho);
	public Aluno buscarPorCpf(String cpf);
}
