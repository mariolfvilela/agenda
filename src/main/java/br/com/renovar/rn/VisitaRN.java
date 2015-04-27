package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.VisitaDAO;
import br.com.renovar.modelo.Cliente;
import br.com.renovar.modelo.Visita;
import br.com.renovar.util.DAOFactory;

public class VisitaRN {
private VisitaDAO visitaDAO;
	public VisitaRN() {
		this.visitaDAO = DAOFactory.criarVisitaDAO();
	}
	public Visita carregar(Integer pesId) {
		return this.visitaDAO.carregar(pesId);

	}

	public Visita buscarPorVisita(String visita) {
		return this.visitaDAO.buscarPorVisita(visita);
	}

	public void salvar(Visita visita) {
		Integer codigo = visita.getVisId();
		if (codigo == null || codigo == 0) {
			this.visitaDAO.salvar(visita);
		} else {
			this.visitaDAO.atualizar(visita);
		}

	}

	public void excluir(Visita visita) {
		this.visitaDAO.excluir(visita);
	}

	public List<Visita> listar() {
		return this.visitaDAO.listar();
	}
	
	public List<Visita> listarVisitasPorCliente(Cliente cliente) {
		return this.visitaDAO.listarVisitasPorCliente(cliente);
	}
	
	public List<Visita> listarNaoVisitadas() {
		return this.visitaDAO.listarNaoVisitadas();
	}
}
