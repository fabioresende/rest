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

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="tb_matriculas")
public class Matricula implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codMatricula;
	private Date dataMatricula;
	private Integer numParcelas;
	@ManyToOne
	@JoinColumn(name="aluno")
	@JsonIgnore
	private Aluno aluno;
	@ManyToOne
	@JoinColumn(name="turma")
	@JsonIgnore
	private Turma turma;
	@JsonIgnore
	@OneToMany(mappedBy="matricula")
	private List<Resultado> resultados;

	public Matricula() {
		super();
		resultados = new ArrayList<>();
	}

	public Matricula(Integer codMatricula, Date dataMatricula, Integer numParcelas, Aluno aluno, Turma turma) {
		super();
		this.codMatricula = codMatricula;
		this.dataMatricula = dataMatricula;
		this.numParcelas = numParcelas;
		this.aluno = aluno;
		this.turma = turma;
		resultados = new ArrayList<>();
		
		aluno.addMatricula(this);
		turma.addMatricula(this);
	}

	public Integer getCodMatricula() {
		return codMatricula;
	}

	public void setCodMatricula(Integer codMatricula) {
		this.codMatricula = codMatricula;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}

	public Integer getNumParcelas() {
		return numParcelas;
	}

	public void setNumParcelas(Integer numParcelas) {
		this.numParcelas = numParcelas;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
		r.setMatricula(this);
	}
	public void removeResultado(Resultado r){
		this.resultados.remove(r);
	}

	@Override
	public String toString() {
		return "Matricula [codMatricula=" + codMatricula + ", dataMatricula=" + dataMatricula + ", numParcelas="
				+ numParcelas + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codMatricula == null) ? 0 : codMatricula.hashCode());
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
		Matricula other = (Matricula) obj;
		if (codMatricula == null) {
			if (other.codMatricula != null)
				return false;
		} else if (!codMatricula.equals(other.codMatricula))
			return false;
		return true;
	}
	public Double notaTotal(){
		double total = 0.0;
		for(Resultado r:resultados){
			total = total + r.getNota();
		}
		return total;
	}
	public boolean aprovado(){
		boolean resultado = false;
		double soma = 0.0;
		for(Resultado r:resultados){
			soma = soma + r.getAvaliacao().getPontuacao();
		}
		soma = soma*70/100;
		if(this.notaTotal()>=soma){
			resultado = true;
		}
		return resultado;
	}
	
}
