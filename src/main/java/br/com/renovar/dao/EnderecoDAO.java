package br.com.renovar.dao;

import java.util.List;

import br.com.renovar.modelo.Endereco;

public interface EnderecoDAO {
	public void salvar(Endereco endereco);

	public void atualizar(Endereco endereco);

	public void excluir(Endereco endereco);

	public Endereco carregar(Integer endId);

	public Endereco buscarPorEndereco(String endereco);

	public List<Endereco> listar();
}
