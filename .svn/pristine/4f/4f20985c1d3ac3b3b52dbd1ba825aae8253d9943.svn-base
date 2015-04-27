package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.AtendimentoDAO;
import br.com.renovar.modelo.Atendimento;
import br.com.renovar.modelo.Funcionario;
import br.com.renovar.util.DAOFactory;

public class AtendimentoRN {
	private AtendimentoDAO atendimentoDAO;

	public AtendimentoRN() {
		this.atendimentoDAO = DAOFactory.criarAtendimentoDAO();
	}

	public Atendimento carregar(Integer ateId) {
		return this.atendimentoDAO.carregar(ateId);

	}

	public Atendimento buscarPorAtendimento(String atendimento) {
		return this.atendimentoDAO.buscarPorAtendimento(atendimento);
	}

	public void salvar(Atendimento atendimento) {
		Integer codigo = atendimento.getAteId();
		if (codigo == null || codigo == 0) {
			this.atendimentoDAO.salvar(atendimento);
		} else {
			this.atendimentoDAO.atualizar(atendimento);
		}

	}

	public void excluir(Atendimento atendimento) {
		this.atendimentoDAO.excluir(atendimento);
	}

	public List<Atendimento> listar() {
		return this.atendimentoDAO.listar();
	}
	
	public List<Atendimento> listarPorFuncionario(Funcionario funcionario) {
		return this.atendimentoDAO.listarPorFuncionario(funcionario);
	}
}
