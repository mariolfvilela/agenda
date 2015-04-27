package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.renovar.modelo.VersaoSistema;

public class VersaoSistemaDAOHibernate implements VersaoSistemaDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(VersaoSistema versaoSistema) {
		this.session.save(versaoSistema);
	}

	public void atualizar(VersaoSistema versaoSistema) {
		this.session.update(versaoSistema);
	}

	public void excluir(VersaoSistema versaoSistema) {
		this.session.delete(versaoSistema);
	}

	public VersaoSistema carregar(Integer verSisId) {
		return (VersaoSistema) this.session.get(VersaoSistema.class, verSisId);
	}

	public VersaoSistema buscarPorVersaoSistema(String versaoSistema) {
		String hql = "select e from VersaoSistema e where e.verSisNome = :versaoSistema";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("versaoSistema", versaoSistema);
		return (VersaoSistema) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<VersaoSistema> listar() {
		return this.session.createCriteria(VersaoSistema.class).list();
	}

}
