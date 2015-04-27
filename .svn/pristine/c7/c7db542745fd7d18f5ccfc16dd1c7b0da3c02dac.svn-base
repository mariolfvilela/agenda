package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.renovar.modelo.Release;
import br.com.renovar.modelo.VersaoSistema;

public class ReleaseDAOHibernate implements ReleaseDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Release release) {
		this.session.save(release);
	}

	public void atualizar(Release release) {
		this.session.update(release);
	}

	public void excluir(Release release) {
		this.session.delete(release);
	}

	public Release carregar(Integer relId) {
		return (Release) this.session.get(Release.class, relId);
	}

	public Release buscarPorRelease(String release) {
		String hql = "select e from Release e where e.relNome = :release";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("release", release);
		return (Release) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Release> listar() {
		return this.session.createCriteria(Release.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Release> listarPorVersao(VersaoSistema versaoSistema) {
		Criteria crit= this.session.createCriteria(Release.class);
		crit.add(Restrictions.eq("versaoSistema.verSisId", versaoSistema.getVerSisId()));
		crit.addOrder(Order.desc("relDataCadastro"));
		return crit.list();
	}

}
