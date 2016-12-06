package com.sistemas.escola.repositorio;

import java.util.List;

import com.sistemas.escola.dominio.Curso;

public interface CursoRepositorioCustom {

	public Curso buscarNomeExato(String nome);
	public List<Curso> buscarTodosOrdenadosPorNome();
	public List<Curso> buscarPorNome(String trecho);
}
