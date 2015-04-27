package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.HistoricoDAO;
import br.com.renovar.modelo.Cliente;
import br.com.renovar.modelo.Historico;
import br.com.renovar.util.DAOFactory;

public class HistoricoRN {
	private HistoricoDAO historicoDAO;

	public HistoricoRN() {
		this.historicoDAO = DAOFactory.criarHistoricoDAO();
	}

	public Historico carregar(Integer hisId) {
		return this.historicoDAO.carregar(hisId);

	}

	public Historico buscarPorHistorico(String hisChamado) {
		return this.historicoDAO.buscarPorHistorico(hisChamado);
	}

	public void salvar(Historico historico) {
		Integer codigo = historico.getHisId();
		if (codigo == null || codigo == 0) {
			this.historicoDAO.salvar(historico);
		} else {
			this.historicoDAO.atualizar(historico);
		}

	}

	public void excluir(Historico historico) {
		this.historicoDAO.excluir(historico);
	}

	public List<Historico> listar() {
		return this.historicoDAO.listar();
	}
	
	public List<Historico> listaPorCliente(Cliente cliente) {
		return this.historicoDAO.listaPorCliente(cliente);
	}
	
	public Historico ultimoHistoricoPorCliente(Cliente cliente) {
		return this.historicoDAO.ultimoHistoricoPorCliente(cliente);
	}
}
