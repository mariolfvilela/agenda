package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.EnderecoDAO;
import br.com.renovar.modelo.Endereco;
import br.com.renovar.util.DAOFactory;

public class EnderecoRN {
	private EnderecoDAO enderecoDAO;

	public EnderecoRN() {
		this.enderecoDAO = DAOFactory.criarEnderecoDAO();
	}

	public Endereco carregar(Integer endId) {
		return this.enderecoDAO.carregar(endId);

	}

	public Endereco buscarPorEndereco(String endereco) {
		return this.enderecoDAO.buscarPorEndereco(endereco);
	}

	public void salvar(Endereco endereco) {
		Integer codigo = endereco.getPesId();
		if (codigo == null || codigo == 0) {
			this.enderecoDAO.salvar(endereco);
		} else {
			this.enderecoDAO.atualizar(endereco);
		}

	}

	public void excluir(Endereco endereco) {
		this.enderecoDAO.excluir(endereco);
	}

	public List<Endereco> listar() {
		return this.enderecoDAO.listar();
	}
}
