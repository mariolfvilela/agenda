package br.com.renovar.dao;

import java.util.List;

import br.com.renovar.modelo.PessoaJuridica;

public interface PessoaJuridicaDAO {
	public void salvar(PessoaJuridica pessoaJuridica);

	public void atualizar(PessoaJuridica pessoaJuridica);

	public void excluir(PessoaJuridica pessoaJuridica);

	public PessoaJuridica carregar(Integer pesId);

	public PessoaJuridica buscarPorPessoaJuridica(String pessoaJuridica);

	public List<PessoaJuridica> listar();
	
	public boolean dependecias(PessoaJuridica pessoaJuridica);
}
