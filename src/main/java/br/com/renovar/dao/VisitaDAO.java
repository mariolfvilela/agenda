package br.com.renovar.dao;

import java.util.List;

import br.com.renovar.modelo.Cliente;
import br.com.renovar.modelo.Visita;

public interface VisitaDAO {
	public void salvar(Visita visita);

	public void atualizar(Visita visita);

	public void excluir(Visita visita);

	public Visita carregar(Integer pesId);

	public Visita buscarPorVisita(String visita);

	public List<Visita> listar();
	
	public List<Visita> listarNaoVisitadas();
	
	public List<Visita> listarVisitasPorCliente(Cliente cliente);
}
