package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.renovar.modelo.Pessoa;

public class PessoaDAOHibernate implements PessoaDAO {

	private Session session;
	public void setSession(Session session) {
	this.session = session;
}
	
	public void salvar(Pessoa pessoa) {
		this.session.save(pessoa);

	}

	public void atualizar(Pessoa pessoa) {
		this.session.update(pessoa);

	}

	public void excluir(Pessoa pessoa) {
		this.session.delete(pessoa);

	}

	public Pessoa carregar(Integer pesId) {
		return (Pessoa)this.session.get(Pessoa.class, pesId);
	}

	public Pessoa buscarPorPessoa(String pessoa) {
		String hql="select b from Pessoa b where b.pesNome = :pessoa";
		Query consulta= this.session.createQuery(hql);
		consulta.setString("pessoa", pessoa);
		return (Pessoa) consulta.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Pessoa> listar() {
		Criteria crit= this.session.createCriteria(Pessoa.class);
		crit.addOrder(Order.asc("pesNome"));
		return crit.list();
	}

	public boolean dependecias(Pessoa pessoa) {
		// TODO Auto-generated method stub
		return false;
	}

}
