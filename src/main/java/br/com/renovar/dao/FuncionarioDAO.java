package br.com.renovar.dao;

import java.util.List;

import br.com.renovar.modelo.Funcionario;
import br.com.renovar.modelo.Pessoa;

public interface FuncionarioDAO {
	public void salvar(Funcionario funcionario);

	public void atualizar(Funcionario funcionario);

	public void excluir(Funcionario funcionario);

	public Funcionario carregar(Integer pesId);

	public Funcionario buscarPorFuncionario(String funcionario);

	public List<Funcionario> listar();
	
	public List<Pessoa> listarFuncionariosPessoas();
	
	public List<Pessoa> listaPessoasNaoFuncionarios();
	
	public boolean dependecias(Funcionario funcionario);
}
