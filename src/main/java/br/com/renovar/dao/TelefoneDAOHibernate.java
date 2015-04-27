package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.renovar.modelo.Pessoa;
import br.com.renovar.modelo.Telefone;

public class TelefoneDAOHibernate implements TelefoneDAO {
	private Session session;
	public void setSession(Session session) {
	this.session = session;
}
	public void salvar(Telefone telefone) {
		this.session.save(telefone);
	}

	public void atualizar(Telefone telefone) {
		this.session.update(telefone);
	}

	public void excluir(Telefone telefone) {
		this.session.delete(telefone);
	}

	public Telefone carregar(Integer telefone) {
		return (Telefone)this.session.get(Telefone.class, telefone);
	}

	public Telefone buscarPorTelefone(String telefone) {
		String hql="select b from Telefone b where b.telNumero = :telefone";
		Query consulta= this.session.createQuery(hql);
		consulta.setString("telefone", telefone);
		return (Telefone) consulta.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<Telefone> listar() {
		Criteria crit= this.session.createCriteria(Telefone.class);
		crit.addOrder(Order.asc("telNumero"));
		return crit.list();
	}

	public boolean dependecias(Telefone telefone) {
		// TODO Auto-generated method stub
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<Telefone> carregarTelefonesPorPessoa(Pessoa pessoa) {
		Criteria crit= this.session.createCriteria(Telefone.class);
		crit.add(Restrictions.eq("pessoa.pesId", pessoa.getPesId()));
		crit.addOrder(Order.asc("telNumero"));
		return crit.list();
	}

}
