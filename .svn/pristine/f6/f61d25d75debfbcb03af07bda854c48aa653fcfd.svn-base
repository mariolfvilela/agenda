package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.FuncionarioDAO;
import br.com.renovar.modelo.Funcionario;
import br.com.renovar.modelo.Pessoa;
import br.com.renovar.util.DAOFactory;

public class FuncionarioRN {
	private FuncionarioDAO funcionarioDAO;

	public FuncionarioRN() {
		this.funcionarioDAO = DAOFactory.criarFuncionarioDAO();
	}

	public Funcionario carregar(Integer pesId) {
		return this.funcionarioDAO.carregar(pesId);

	}

	public Funcionario buscarPorFuncionario(String loguin) {
		return this.funcionarioDAO.buscarPorFuncionario(loguin);
	}

	public void salvar(Funcionario Funcionario) {
		Integer codigo = Funcionario.getPesId();
		if (codigo == null || codigo == 0) {
			this.funcionarioDAO.salvar(Funcionario);
		} else {
			this.funcionarioDAO.atualizar(Funcionario);
		}

	}

	public void excluir(Funcionario Funcionario) {		
			this.funcionarioDAO.excluir(Funcionario);
	}

	public List<Funcionario> listar() {
		return this.funcionarioDAO.listar();
	}
	
	public List<Pessoa> listarFuncionariosPessoas() {
		return this.funcionarioDAO.listarFuncionariosPessoas();
	}
	
	public List<Pessoa> listaPessoasNaoFuncionarios() {
		return this.funcionarioDAO.listaPessoasNaoFuncionarios();
	}
}
