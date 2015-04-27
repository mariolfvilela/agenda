package br.com.renovar.dao;

import java.util.List;

import br.com.renovar.modelo.Cliente;

public interface ClienteDAO {
	public void salvar(Cliente cliente);

	public void atualizar(Cliente cliente);

	public void excluir(Cliente cliente);

	public Cliente carregar(Integer pesId);

	public Cliente buscarPorCliente(String cliente);

	public List<Cliente> listar();
	
	public boolean dependecias(Cliente cliente);
}
