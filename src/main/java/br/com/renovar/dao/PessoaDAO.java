package br.com.renovar.dao;

import java.util.List;

import br.com.renovar.modelo.Pessoa;


public interface PessoaDAO {
	public void salvar(Pessoa pessoa);

	public void atualizar(Pessoa pessoa);

	public void excluir(Pessoa pessoa);

	public Pessoa carregar(Integer pesId);

	public Pessoa buscarPorPessoa(String pessoa);

	public List<Pessoa> listar();
	
	public boolean dependecias(Pessoa pessoa);
}
