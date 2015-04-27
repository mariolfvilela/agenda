package br.com.renovar.dao;

import java.util.List;

import br.com.renovar.modelo.Cliente;
import br.com.renovar.modelo.Historico;

public interface HistoricoDAO {
	public void salvar(Historico historico);

	public void atualizar(Historico historico);

	public void excluir(Historico historico);

	public Historico carregar(Integer hisId);

	public Historico buscarPorHistorico(String historico);

	public List<Historico> listar();
	
	public List<Historico> listaPorCliente(Cliente cliente);
	
	public Historico ultimoHistoricoPorCliente(Cliente cliente);
	
	public boolean dependecias(Historico historico);
}
