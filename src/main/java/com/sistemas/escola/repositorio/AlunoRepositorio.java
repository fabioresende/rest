package com.sistemas.escola.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemas.escola.dominio.Aluno;

public interface AlunoRepositorio extends JpaRepository<Aluno, Integer>{

}
