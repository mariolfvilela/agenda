package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.PessoaJuridicaDAO;
import br.com.renovar.modelo.PessoaJuridica;
import br.com.renovar.util.DAOFactory;

public class PessoaJuridicaRN {
	private PessoaJuridicaDAO pessoaJuridicaDAO;

	public PessoaJuridicaRN() {
		this.pessoaJuridicaDAO = DAOFactory.criarPessoaJuridicaDAO();
	}

	public PessoaJuridica carregar(Integer pesId) {
		return this.pessoaJuridicaDAO.carregar(pesId);

	}

	public PessoaJuridica buscarPorPessoaJuridica(String pessoaJuridica) {
		return this.pessoaJuridicaDAO.buscarPorPessoaJuridica(pessoaJuridica);
	}

	public void salvar(PessoaJuridica pessoaFisica) {
		Integer codigo = pessoaFisica.getPesId();
		if (codigo == null || codigo == 0) {
			this.pessoaJuridicaDAO.salvar(pessoaFisica);
		} else {
			this.pessoaJuridicaDAO.atualizar(pessoaFisica);
		}

	}

	public void excluir(PessoaJuridica pessoaFisica) {		
			this.pessoaJuridicaDAO.excluir(pessoaFisica);
	}

	public List<PessoaJuridica> listar() {
		return this.pessoaJuridicaDAO.listar();
	}
}
