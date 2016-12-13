package com.sistemas.cursos.servico;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sistemas.escola.dominio.Aluno;
import com.sistemas.escola.repositorio.AlunoRepositorio;
import com.sistemas.escola.repositorio.AlunoRepositorioCustom;
import com.sistemas.cursos.servico.excecoes.NaoEncontradoException;
import com.sistemas.cursos.servico.excecoes.ServicoException;
import com.sistemas.cursos.servico.excecoes.ValidacaoException;


@Service
public class AlunoServico {


	@Autowired
	private AlunoRepositorio repo;


	@Autowired
	private AlunoRepositorioCustom repoCustom;


	public void validar(Aluno x) {
		List<String> erros = new ArrayList<>();
		
		if (x.getNome()==null) {
			erros.add("Favor preencher o campo nome");
		}
		if (x.getCpf()==null) {
			erros.add("Favor preencher o campo cpf");
		}
		if (x.getEmail()==null) {
			erros.add("Favor preencher um valor válido para o email");
		}
		if (x.getNascimento()==null) {
			erros.add("Favor preencher um valor válido para a data de nascimento");
		}
		if (x.getRenda()==null) {
			erros.add("Favor preencher um valor válido para a renda");
		}
		if (x.getTelefone()==null) {
			erros.add("Favor preencher um valor válido para telefone");
		}
		if (!erros.isEmpty()) {
			throw new ValidacaoException("Erro de validação", erros);
		}
	}
	
	public Aluno inserir(Aluno x) throws ServicoException {
		Aluno aux = repoCustom.buscarNomeExato(x.getNome());
		if (aux != null) {
			throw new ServicoException("Já existe um curso com esse nome!", 1);
		}
		validar(x);
		return repo.save(x);
	}


	public Aluno atualizar(Aluno x) throws ServicoException {
		Aluno aux = repo.findOne(x.getCodAluno());
		if (aux == null) {
			throw new NaoEncontradoException("Aluno não encontrado!", 1);
		}
		aux = repoCustom.buscarNomeExato(x.getNome());
		if (aux != null && aux.getCodAluno()!=x.getCodAluno()) {
			throw new ServicoException("Já existe um outro curso com esse nome!", 1);
		}
		validar(x);
		return repo.save(x);
	}


	public void excluir(int cod) throws ServicoException {
		Aluno cs = repo.findOne(cod);
		if (cs == null) {
			throw new NaoEncontradoException("Aluno não encontrado!", 1);
		}
		repo.delete(cs);
	}
	
	public Aluno buscar(int cod) {
		Aluno cs = repo.findOne(cod);
		if (cs == null) {
			throw new NaoEncontradoException("Aluno não encontrado!", 1);
		}
		return cs;
	}
	
	public List<Aluno> buscarTodosOrdenadosPorNome() {
		return repoCustom.buscarTodosOrdenadosPorNome();
	}
	
	public List<Aluno> buscarPorNome(String trecho) {
		return repoCustom.buscarPorNome(trecho);
	}
}

