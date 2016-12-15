package com.sistemas.escola.servico;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sistemas.escola.dominio.Curso;
import com.sistemas.escola.repositorio.CursoRepositorio;
import com.sistemas.escola.repositorio.CursoRepositorioCustom;
import com.sistemas.escola.servico.excecoes.NaoEncontradoException;
import com.sistemas.escola.servico.excecoes.ServicoException;
import com.sistemas.escola.servico.excecoes.ValidacaoException;


@Service
public class CursoServico {


	@Autowired
	private CursoRepositorio repo;


	@Autowired
	private CursoRepositorioCustom repoCustom;


	public void validar(Curso x) {
		List<String> erros = new ArrayList<>();
		
		if (x.getNome()==null) {
			erros.add("Favor preencher o campo nome");
		}
		if (x.getCargaHoraria()==null) {
			erros.add("Favor preencher o campo nacionalidade");
		}
		if (x.getPreco()==null) {
			erros.add("Favor preencher um valor válido para o cache");
		}
		if (x.getPontuacao()==null) {
			erros.add("Favor preencher um valor válido para a data de nascimento");
		}
		
		if (!erros.isEmpty()) {
			throw new ValidacaoException("Erro de validação", erros);
		}
	}
	
	public Curso inserir(Curso x) throws ServicoException {
		Curso aux = repoCustom.buscarNomeExato(x.getNome());
		if (aux != null) {
			throw new ServicoException("Já existe um curso com esse nome!", 1);
		}
		validar(x);
		return repo.save(x);
	}


	public Curso atualizar(Curso x) throws ServicoException {
		Curso aux = repo.findOne(x.getCodCurso());
		if (aux == null) {
			throw new NaoEncontradoException("Curso não encontrado!", 1);
		}
		aux = repoCustom.buscarNomeExato(x.getNome());
		if (aux != null && aux.getCodCurso()!=x.getCodCurso()) {
			throw new ServicoException("Já existe um outro curso com esse nome!", 1);
		}
		validar(x);
		return repo.save(x);
	}


	public void excluir(int cod) throws ServicoException {
		Curso cs = repo.findOne(cod);
		if (cs == null) {
			throw new NaoEncontradoException("Curso não encontrado!", 1);
		}
		if (!cs.getTurmas().isEmpty()) {
			throw new ServicoException("Exclusão não permitida: este curso possui turmas já cadastradas!", 2);
		}
		repo.delete(cs);
	}
	
	public Curso buscar(int cod) {
		Curso cs = repo.findOne(cod);
		if (cs == null) {
			throw new NaoEncontradoException("Curso não encontrado!", 1);
		}
		return cs;
	}
	
	public List<Curso> buscarTodosOrdenadosPorNome() {
		return repoCustom.buscarTodosOrdenadosPorNome();
	}
	
	public List<Curso> buscarPorNome(String trecho) {
		return repoCustom.buscarPorNome(trecho);
	}
}

