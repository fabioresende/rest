package com.sistemas.escola.repositorio.impl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sistemas.escola.dominio.Aluno;
import com.sistemas.escola.repositorio.AlunoRepositorioCustom;


@Repository
@Transactional(readOnly=true)
public class AlunoRepositorioImpl implements AlunoRepositorioCustom {


	@PersistenceContext
	private EntityManager em;
	
	@Override
	@SuppressWarnings("unchecked")
	public Aluno buscarPorCpf(String cpf) {
		String jpql = "SELECT x FROM Aluno x WHERE x.cpf = :p1";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", cpf);
		List<Aluno> aux = query.getResultList();
		return (aux.size() > 0) ? aux.get(0) : null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> buscarTodosOrdenadosPorNome() {
		String jpql = "SELECT x FROM Curso x ORDER BY x.nome";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Aluno> buscarPorNome(String trecho) {
		String jpql = "SELECT x FROM Curso x WHERE x.nome LIKE :p1";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", "%"+trecho+"%");
		return query.getResultList();
	}



}
