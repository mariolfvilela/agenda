package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.VersaoSistemaDAO;
import br.com.renovar.modelo.VersaoSistema;
import br.com.renovar.util.DAOFactory;

public class VersaoSistemaRN {
	private VersaoSistemaDAO versaoSistemaDAO;

	public VersaoSistemaRN() {
		this.versaoSistemaDAO = DAOFactory.criarVersaoSistemaDAO();
	}

	public VersaoSistema carregar(Integer verSisId) {
		return this.versaoSistemaDAO.carregar(verSisId);

	}

	public VersaoSistema buscarPorVersaoSistema(String versaoSistema) {
		return this.versaoSistemaDAO.buscarPorVersaoSistema(versaoSistema);
	}

	public void salvar(VersaoSistema versaoSistema) {
		Integer codigo = versaoSistema.getVerSisId();
		if (codigo == null || codigo == 0) {
			this.versaoSistemaDAO.salvar(versaoSistema);
		} else {
			this.versaoSistemaDAO.atualizar(versaoSistema);
		}

	}

	public void excluir(VersaoSistema versaoSistema) {
		this.versaoSistemaDAO.excluir(versaoSistema);
	}

	public List<VersaoSistema> listar() {
		return this.versaoSistemaDAO.listar();
	}
}
