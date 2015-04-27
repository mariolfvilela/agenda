package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.renovar.modelo.PessoaFisica;

public class PessoaFisicaDAOHibernate implements PessoaFisicaDAO{
	private Session session;
	public void setSession(Session session) {
	this.session = session;
}
	
	public void salvar(PessoaFisica pessoaFisica) {
		this.session.save(pessoaFisica);

	}

	public void atualizar(PessoaFisica pessoaFisica) {
		this.session.update(pessoaFisica);

	}

	public void excluir(PessoaFisica pessoaFisica) {
		this.session.delete(pessoaFisica);

	}

	public PessoaFisica carregar(Integer pesId) {
		return (PessoaFisica)this.session.get(PessoaFisica.class, pesId);
	}

	public PessoaFisica buscarPorPessoaFisica(String pessoaFisica) {
		String hql="select b from PessoaFisica b where b.pessoa.pesNome = :pessoaFisica";
		Query consulta= this.session.createQuery(hql);
		consulta.setString("pessoaFisica", pessoaFisica);
		return (PessoaFisica) consulta.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<PessoaFisica> listar() {
		Criteria crit= this.session.createCriteria(PessoaFisica.class);
		crit.addOrder(Order.asc("pesId"));
		return crit.list();
	}

	public boolean dependecias(PessoaFisica pessoa) {
		// TODO Auto-generated method stub
		return false;
	}
}
