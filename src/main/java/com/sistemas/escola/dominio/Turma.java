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
@Table(name="tb_turma")
public class Turma implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codTurma;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date datainicio;
	private Integer numeroDeVagas;
	private Boolean finalizada;
	@ManyToOne
	@JoinColumn(name="curso")
	@JsonIgnore
    private Curso curso;
	@JsonIgnore
	@OneToMany(mappedBy="turma")
    private List<Avaliacao> avaliacoes;
	@JsonIgnore
	@OneToMany(mappedBy="turma")
    private List<Matricula> matriculas;
	
    
    public Turma() {
		super();
		avaliacoes = new ArrayList<>();
		matriculas = new ArrayList<>();
	}


	public Turma(Integer codTurma, Date datainicio, Integer numeroDeVagas, Boolean finalizada, Curso curso) {
		super();
		this.codTurma = codTurma;
		this.datainicio = datainicio;
		this.numeroDeVagas = numeroDeVagas;
		this.finalizada = finalizada;
		this.curso = curso;
		avaliacoes = new ArrayList<>();
		matriculas = new ArrayList<>();
		curso.addTurma(this);
	}


	public Integer getCodTurma() {
		return codTurma;
	}


	public void setCodTurma(Integer codTurma) {
		this.codTurma = codTurma;
	}


	public Date getDatainicio() {
		return datainicio;
	}


	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	}


	public Integer getNumeroDeVagas() {
		return numeroDeVagas;
	}


	public void setNumeroDeVagas(Integer numeroDeVagas) {
		this.numeroDeVagas = numeroDeVagas;
	}


	public Boolean getFinalizada() {
		return finalizada;
	}


	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}


	public Curso getCurso() {
		return curso;
	}


	public void setCurso(Curso curso) {
		this.curso = curso;
	}


	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}


	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}


	public List<Matricula> getMatriculas() {
		return matriculas;
	}


	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	public void addAvaliacao(Avaliacao a){
		this.avaliacoes.add(a);
		a.setTurma(this);
	}
	public void removeAvaliacao(Avaliacao a){
		this.avaliacoes.remove(a);
	}
	public void addMatricula(Matricula m){
		this.matriculas.add(m);
		m.setTurma(this);
	}
	public void removeMatricula(Matricula m){
		this.matriculas.remove(m);
	}


	@Override
	public String toString() {
		return "Turma [codTurma=" + codTurma + ", datainicio=" + datainicio + ", numeroDeVagas=" + numeroDeVagas
				+ ", finalizada=" + finalizada + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codTurma == null) ? 0 : codTurma.hashCode());
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
		Turma other = (Turma) obj;
		if (codTurma == null) {
			if (other.codTurma != null)
				return false;
		} else if (!codTurma.equals(other.codTurma))
			return false;
		return true;
	}
    
    
	public Double notaMedia(){
		Double media = 0.0;
		int x = 0;
		for(Matricula m:matriculas){
			media = media + m.notaTotal();
			x++;
		}
		media = media/x;
		return media;
	}
    
    
}
