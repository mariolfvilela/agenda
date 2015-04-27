package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.ReleaseDAO;
import br.com.renovar.modelo.Release;
import br.com.renovar.modelo.VersaoSistema;
import br.com.renovar.util.DAOFactory;

public class ReleaseRN {
	private ReleaseDAO releaseDAO;

	public ReleaseRN() {
		this.releaseDAO = DAOFactory.criarReleaseDAO();
	}

	public Release carregar(Integer relId) {
		return this.releaseDAO.carregar(relId);

	}

	public Release buscarPorRelease(String release) {
		return this.releaseDAO.buscarPorRelease(release);
	}

	public void salvar(Release release) {
		Integer codigo = release.getRelId();
		if (codigo == null || codigo == 0) {
			this.releaseDAO.salvar(release);
		} else {
			this.releaseDAO.atualizar(release);
		}

	}

	public void excluir(Release release) {
		this.releaseDAO.excluir(release);
	}

	public List<Release> listar() {
		return this.releaseDAO.listar();
	}
	
	public List<Release> listarPorVersao(VersaoSistema versaoSistema) {
		return this.releaseDAO.listarPorVersao(versaoSistema);
	}
}
