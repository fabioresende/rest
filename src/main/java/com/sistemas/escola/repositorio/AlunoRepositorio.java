package com.sistemas.escola.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemas.escola.dominio.Aluno;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno, Integer>, AlunoRepositorioCustom {

}
