package com.sistemas.escola.repositorio.impl;


import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import org.springframework.stereotype.Component;


import com.sistemas.escola.dominio.Curso;
import com.sistemas.escola.repositorio.CursoRepositorioCustom;


@Component
public class CursoRepositorioCustomImpl implements CursoRepositorioCustom {


	@PersistenceContext
	private EntityManager em;
	
	@Override
	@SuppressWarnings("unchecked")
	public Curso buscarNomeExato(String nome) {
		String jpql = "SELECT x FROM Curso x WHERE x.nome = :p1";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", nome);
		List<Curso> aux = query.getResultList();
		return (aux.size() > 0) ? aux.get(0) : null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> buscarTodosOrdenadosPorNome() {
		String jpql = "SELECT x FROM Curso x ORDER BY x.nome";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> buscarPorNome(String trecho) {
		String jpql = "SELECT x FROM Curso x WHERE x.nome LIKE :p1";
		Query query = em.createQuery(jpql);
		query.setParameter("p1", "%"+trecho+"%");
		return query.getResultList();
	}


}
