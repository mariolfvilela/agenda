package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.renovar.modelo.PessoaJuridica;

public class PessoaJuridicaDAOHibernate implements PessoaJuridicaDAO {
	private Session session;
	public void setSession(Session session) {
	this.session = session;
}
	public void salvar(PessoaJuridica pessoaJuridica) {
		this.session.save(pessoaJuridica);
	}

	public void atualizar(PessoaJuridica pessoaJuridica) {
		this.session.update(pessoaJuridica);
	}

	public void excluir(PessoaJuridica pessoaJuridica) {
		this.session.delete(pessoaJuridica);
	}

	public PessoaJuridica carregar(Integer pesId) {
		return (PessoaJuridica)this.session.get(PessoaJuridica.class, pesId);
	}

	public PessoaJuridica buscarPorPessoaJuridica(String pessoaJuridica) {
		String hql="select b from PessoaJuridica b where b.pessoa.pesNome = :pessoaJuridica";
		Query consulta= this.session.createQuery(hql);
		consulta.setString("pessoaJuridica", pessoaJuridica);
		return (PessoaJuridica) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<PessoaJuridica> listar() {
		Criteria crit= this.session.createCriteria(PessoaJuridica.class);
		crit.addOrder(Order.asc("pesId"));
		return crit.list();
	}

	public boolean dependecias(PessoaJuridica pessoaJuridica) {
		// TODO Auto-generated method stub
		return false;
	}

}
