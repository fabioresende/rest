package com.sistemas.escola.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="tb_resultados")
public class Resultado implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codResultado;
	private Double nota;
	@ManyToOne
	@JoinColumn(name="matricula")
	@JsonIgnore
	private Matricula matricula;
	@ManyToOne
	@JoinColumn(name="avaliacao")
	@JsonIgnore
	private Avaliacao avaliacao;
	
	
	public Resultado() {
		super();
	}


	public Resultado(Integer codResultado, Double nota, Matricula matricula, Avaliacao avaliacao) {
		super();
		this.codResultado = codResultado;
		this.nota = nota;
		this.matricula = matricula;
		this.avaliacao = avaliacao;
		matricula.addResultado(this);
		avaliacao.addResultado(this);
	}


	public Integer getCodResultado() {
		return codResultado;
	}


	public void setCodResultado(Integer codResultado) {
		this.codResultado = codResultado;
	}


	public Double getNota() {
		return nota;
	}


	public void setNota(Double nota) {
		this.nota = nota;
	}


	public Matricula getMatricula() {
		return matricula;
	}


	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}


	public Avaliacao getAvaliacao() {
		return avaliacao;
	}


	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}


	@Override
	public String toString() {
		return "Resultado [codResultado=" + codResultado + ", nota=" + nota + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codResultado == null) ? 0 : codResultado.hashCode());
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
		Resultado other = (Resultado) obj;
		if (codResultado == null) {
			if (other.codResultado != null)
				return false;
		} else if (!codResultado.equals(other.codResultado))
			return false;
		return true;
	}
	
	
}
