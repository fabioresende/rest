package com.sistemas.escola.dominio;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="tb_alunos")
public class Aluno implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codAluno;
	private String nome;
	private String cpf;
	private String telefone;
	private Date nascimento;
	private String email;
	private BigDecimal renda;
	@OneToMany(mappedBy="aluno")
	@JsonIgnore
	private List<Matricula> matriculas;

	public Aluno() {
		super();
		matriculas = new ArrayList<>();
	}

	public Aluno(Integer codAluno, String nome, String cpf, String telefone, Date nascimento, String email,
			BigDecimal renda) {
		super();
		this.codAluno = codAluno;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.nascimento = nascimento;
		this.email = email;
		this.renda = renda;
		matriculas = new ArrayList<>();
	}

	public Integer getCodAluno() {
		return codAluno;
	}

	public void setCodAluno(Integer codAluno) {
		this.codAluno = codAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}
    public void addMatricula(Matricula m){
    	this.matriculas.add(m);
    	m.setAluno(this);
    }
    public void removeMatricula(Matricula m){
    	this.matriculas.remove(m);
    }

	@Override
	public String toString() {
		return "Aluno [codAluno=" + codAluno + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone
				+ ", nascimento=" + nascimento + ", email=" + email + ", renda=" + renda + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codAluno == null) ? 0 : codAluno.hashCode());
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
		Aluno other = (Aluno) obj;
		if (codAluno == null) {
			if (other.codAluno != null)
				return false;
		} else if (!codAluno.equals(other.codAluno))
			return false;
		return true;
	}

	
	
	
}
