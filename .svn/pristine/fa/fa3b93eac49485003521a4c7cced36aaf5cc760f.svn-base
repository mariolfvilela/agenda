package br.com.renovar.dao;

import java.util.List;

import br.com.renovar.modelo.Atualizacao;
import br.com.renovar.modelo.Cliente;

public interface AtualizacaoDAO {
	public void salvar(Atualizacao atualizacao);

	public void atualizar(Atualizacao atualizacao);

	public void excluir(Atualizacao atualizacao);

	public Atualizacao carregar(Integer atuId);

	public Atualizacao buscarAtualizacaoTipo(String atualizacao);

	public List<Atualizacao> listar();
	
	public List<Atualizacao> listarPorCliente(Cliente cliente);
	
	public List<Atualizacao> listarPorClienteNaoAtualizado(Cliente cliente);
	
	public List<Atualizacao> listarTodasAtualizacoesAgendadas();
	
	public Atualizacao buscarPorCliente(Cliente cliente);
}
