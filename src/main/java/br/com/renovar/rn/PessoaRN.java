package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.PessoaDAO;
import br.com.renovar.modelo.Pessoa;
import br.com.renovar.util.DAOFactory;


public class PessoaRN {
	private PessoaDAO pessoaDAO;

	public PessoaRN() {
		this.pessoaDAO = DAOFactory.criarPessoaDAO();
	}

	public Pessoa carregar(Integer pesId) {
		return this.pessoaDAO.carregar(pesId);

	}

	public Pessoa buscarPorRegiao(String pessoa) {
		return this.pessoaDAO.buscarPorPessoa(pessoa);
	}

	public void salvar(Pessoa pessoa) {
		Integer codigo = pessoa.getPesId();
		if (codigo == null || codigo == 0) {
			this.pessoaDAO.salvar(pessoa);
		} else {
			this.pessoaDAO.atualizar(pessoa);
		}

	}

	public void excluir(Pessoa pessoa) {		
			this.pessoaDAO.excluir(pessoa);
	}

	public List<Pessoa> listar() {
		return this.pessoaDAO.listar();
	}
}
