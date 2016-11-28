package com.sistemas.escola.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemas.escola.dominio.Curso;

public interface CursoRepositorio extends JpaRepository<Curso, Integer>{

}
