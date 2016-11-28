package com.sistemas.escola.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="tb_avaliacoes")
public class Avaliacao implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codAvaliacao;
	private Double pontuacao;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date date;
	@ManyToOne
	@JoinColumn(name="turma")
	@JsonIgnore
	private Turma turma;
	@OneToMany(mappedBy="avaliacao")
	@JsonIgnore
	private List<Resultado> resultados;
	
	
	public Avaliacao() {
		super();
		resultados = new ArrayList<>();
	}


	public Avaliacao(Integer codAvaliacao, Double pontuacao, Date date, Turma turma) {
		super();
		this.codAvaliacao = codAvaliacao;
		this.pontuacao = pontuacao;
		this.date = date;
		this.turma = turma;
		resultados = new ArrayList<>();
		turma.addAvaliacao(this);
	}


	public Integer getCodAvaliacao() {
		return codAvaliacao;
	}


	public void setCodAvaliacao(Integer codAvaliacao) {
		this.codAvaliacao = codAvaliacao;
	}


	public Double getPontuacao() {
		return pontuacao;
	}


	public void setPontuacao(Double pontuacao) {
		this.pontuacao = pontuacao;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}


	public List<Resultado> getResultados() {
		return resultados;
	}


	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}
	public void addResultado(Resultado r){
		this.resultados.add(r);
		r.setAvaliacao(this);
	}
	public void removeResultado(Resultado r){
		this.resultados.remove(r);
	}


	@Override
	public String toString() {
		return "Avaliacao [codAvaliacao=" + codAvaliacao + ", pontuacao=" + pontuacao + ", date=" + date + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codAvaliacao == null) ? 0 : codAvaliacao.hashCode());
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
		Avaliacao other = (Avaliacao) obj;
		if (codAvaliacao == null) {
			if (other.codAvaliacao != null)
				return false;
		} else if (!codAvaliacao.equals(other.codAvaliacao))
			return false;
		return true;
	}
	
}
