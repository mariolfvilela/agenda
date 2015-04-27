package br.com.renovar.dao;

import java.util.List;

import br.com.renovar.modelo.Release;
import br.com.renovar.modelo.VersaoSistema;

public interface ReleaseDAO {
	public void salvar(Release release);

	public void atualizar(Release release);

	public void excluir(Release release);

	public Release carregar(Integer relId);

	public Release buscarPorRelease(String release);

	public List<Release> listar();
	
	public List<Release> listarPorVersao(VersaoSistema versaoSistema);
}
