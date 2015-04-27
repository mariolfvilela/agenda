package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.PessoaFisicaDAO;
import br.com.renovar.modelo.PessoaFisica;
import br.com.renovar.util.DAOFactory;

public class PessoaFisicaRN {
	private PessoaFisicaDAO pessoaFisicaDAO;

	public PessoaFisicaRN() {
		this.pessoaFisicaDAO = DAOFactory.criarPessoaFisicaDAO();
	}

	public PessoaFisica carregar(Integer pesId) {
		return this.pessoaFisicaDAO.carregar(pesId);

	}

	public PessoaFisica buscarPorPessoaFisica(String pessoaFisica) {
		return this.pessoaFisicaDAO.buscarPorPessoaFisica(pessoaFisica);
	}

	public void salvar(PessoaFisica pessoaFisica) {
		Integer codigo = pessoaFisica.getPesId();
		if (codigo == null || codigo == 0) {
			this.pessoaFisicaDAO.salvar(pessoaFisica);
		} else {
			this.pessoaFisicaDAO.atualizar(pessoaFisica);
		}

	}

	public void excluir(PessoaFisica pessoaFisica) {		
			this.pessoaFisicaDAO.excluir(pessoaFisica);
	}

	public List<PessoaFisica> listar() {
		return this.pessoaFisicaDAO.listar();
	}
}
