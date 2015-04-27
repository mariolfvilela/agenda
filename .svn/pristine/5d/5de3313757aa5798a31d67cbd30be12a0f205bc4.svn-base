package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import br.com.renovar.modelo.EMail;
import br.com.renovar.modelo.Pessoa;

public class EMailDAOHibernate implements EMailDAO {
	private Session session;
	public void setSession(Session session) {
	this.session = session;
}
	public void salvar(EMail eMail) {
		this.session.save(eMail);
	}

	public void atualizar(EMail eMail) {
		this.session.update(eMail);
	}

	public void excluir(EMail eMail) {
		this.session.delete(eMail);
	}

	public EMail carregar(Integer eMail) {
		return (EMail)this.session.get(EMail.class, eMail);
	}

	public EMail buscarPorEMail(String eMail) {
		String hql="select b from EMail b where b.maiEndereco = :eMail";
		Query consulta= this.session.createQuery(hql);
		consulta.setString("eMail", eMail);
		return (EMail) consulta.uniqueResult();

	}
	@SuppressWarnings("unchecked")
	public List<EMail> listar() {
		Criteria crit= this.session.createCriteria(EMail.class);
		crit.addOrder(Order.asc("maiEndereco"));
		return crit.list();
	}

	public boolean dependecias(EMail eMail) {
		// TODO Auto-generated method stub
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<EMail> carregarEmailsPorPessoa(Pessoa pessoa) {
		Criteria crit= this.session.createCriteria(EMail.class);
		crit.add(Restrictions.eq("pessoa.pesId", pessoa.getPesId()));
		crit.addOrder(Order.asc("maiEndereco"));
		return crit.list();
	}

}
