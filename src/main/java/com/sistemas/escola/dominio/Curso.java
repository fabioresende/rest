package com.sistemas.escola.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="tb_cursos")
public class Curso implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codCurso;
	private String nome;
	private Integer cargaHoraria;
	private BigDecimal preco;
	private Double pontuacao;
	
	@OneToMany(mappedBy="curso")
	@JsonIgnore
	private List<Turma> turmas;


	public Curso() {
		super();
		turmas = new ArrayList<>(); 
	}


	public Curso(Integer codCurso, String nome, Integer cargaHoraria, BigDecimal preco, Double pontuacao) {
		super();
		this.codCurso = codCurso;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.preco = preco;
		this.pontuacao = pontuacao;
		turmas = new ArrayList<>(); 
		
	}


	public Integer getCodCurso() {
		return codCurso;
	}


	public void setCodCurso(Integer codCurso) {
		this.codCurso = codCurso;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Integer getCargaHoraria() {
		return cargaHoraria;
	}


	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}


	public BigDecimal getPreco() {
		return preco;
	}


	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	public Double getPontuacao() {
		return pontuacao;
	}


	public void setPontuacao(Double pontuacao) {
		this.pontuacao = pontuacao;
	}


	public List<Turma> getTurmas() {
		return turmas;
	}


	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}
	
	public void addTurma(Turma t){
		this.turmas.add(t);
		t.setCurso(this);
	}


	@Override
	public String toString() {
		return "Curso [codCurso=" + codCurso + ", nome=" + nome + ", cargaHoraria=" + cargaHoraria + ", preco=" + preco
				+ ", pontuacao=" + pontuacao + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codCurso == null) ? 0 : codCurso.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (codCurso == null) {
			if (other.codCurso != null)
				return false;
		} else if (!codCurso.equals(other.codCurso))
			return false;
		return true;
	}
	
	
}
