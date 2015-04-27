package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.AtualizacaoDAO;
import br.com.renovar.modelo.Atualizacao;
import br.com.renovar.modelo.Cliente;
import br.com.renovar.util.DAOFactory;

public class AtualizacaoRN {
	private AtualizacaoDAO atualizacaoDAO;

	public AtualizacaoRN() {
		this.atualizacaoDAO = DAOFactory.criarAtualizacaoDAO();
	}

	public Atualizacao carregar(Integer atuId) {
		return this.atualizacaoDAO.carregar(atuId);

	}

	public Atualizacao buscarAtualizacaoTipo(String atualizacaoTipo) {
		return this.atualizacaoDAO.buscarAtualizacaoTipo(atualizacaoTipo);
	}

	public void salvar(Atualizacao atualizacao) {
		Integer codigo = atualizacao.getAtuId();
		if (codigo == null || codigo == 0) {
			this.atualizacaoDAO.salvar(atualizacao);
		} else {
			this.atualizacaoDAO.atualizar(atualizacao);
		}

	}

	public void excluir(Atualizacao atualizacao) {
		this.atualizacaoDAO.excluir(atualizacao);
	}

	public List<Atualizacao> listar() {
		return this.atualizacaoDAO.listar();
	}
	
	public Atualizacao buscarPorCliente(Cliente cliente) {
		return this.atualizacaoDAO.buscarPorCliente(cliente);
	}
	
	public List<Atualizacao> listarPorCliente(Cliente cliente) {
		return this.atualizacaoDAO.listarPorCliente(cliente);
	}
	
	public List<Atualizacao> listarPorClienteNaoAtualizado(Cliente cliente) {
		return this.atualizacaoDAO.listarPorClienteNaoAtualizado(cliente);
	}
	
	public List<Atualizacao> listarTodasAtualizacoesAgendadas() {
		return this.atualizacaoDAO.listarTodasAtualizacoesAgendadas();
	}
}
