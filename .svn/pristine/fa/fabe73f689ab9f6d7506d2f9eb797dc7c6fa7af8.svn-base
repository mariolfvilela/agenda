package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.renovar.modelo.Atualizacao;
import br.com.renovar.modelo.Cliente;

public class AtualizacaoDAOHibernate implements AtualizacaoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	public void salvar(Atualizacao atualizacao) {
		this.session.save(atualizacao);
	}

	public void atualizar(Atualizacao atualizacao) {
		this.session.update(atualizacao);
	}

	public void excluir(Atualizacao atualizacao) {
		this.session.delete(atualizacao);
	}

	public Atualizacao carregar(Integer atuId) {
		return (Atualizacao) this.session.get(Atualizacao.class, atuId);
	}

	public Atualizacao buscarAtualizacaoTipo(String atualizacao) {
		String hql = "select e from Atualizacao e where e.atuTipo = :atualizacao";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("atualizacao", atualizacao);
		return (Atualizacao) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Atualizacao> listar() {
		return this.session.createCriteria(Atualizacao.class).list();
	}
	@SuppressWarnings("unchecked")
	public Atualizacao buscarPorCliente(Cliente cliente) {
		Criteria crit= this.session.createCriteria(Atualizacao.class);
		crit.add(Restrictions.eq("cliente.pesId", cliente.getPesId()));
		crit.addOrder(Order.desc("atuDataAtualizada"));
		List<Atualizacao> lista= crit.list();
		Atualizacao atualizacao=null;
		for(Atualizacao at: lista){
			atualizacao=at;
			break;
		}
		return atualizacao;
	}
	@SuppressWarnings("unchecked")
	public List<Atualizacao> listarPorCliente(Cliente cliente) {
		Criteria crit= this.session.createCriteria(Atualizacao.class);
		crit.add(Restrictions.eq("cliente.pesId", cliente.getPesId()));
		crit.add(Restrictions.isNotNull("release"));
		crit.add(Restrictions.isNotNull("atuDataAtualizada"));
		crit.addOrder(Order.asc("atuDataAtualizada"));
		List<Atualizacao> lista= crit.list();
		return lista;
	}
	@SuppressWarnings("unchecked")
	public List<Atualizacao> listarPorClienteNaoAtualizado(Cliente cliente) {
		Criteria crit= this.session.createCriteria(Atualizacao.class);
		crit.add(Restrictions.eq("cliente.pesId", cliente.getPesId()));
		crit.add(Restrictions.isNull("atuDataAtualizada"));
		List<Atualizacao> lista= crit.list();
		return lista;
	}
	@SuppressWarnings("unchecked")
	public List<Atualizacao> listarTodasAtualizacoesAgendadas() {
		Criteria crit= this.session.createCriteria(Atualizacao.class);
		crit.add(Restrictions.isNull("release"));
		crit.add(Restrictions.isNull("atuDataAtualizada"));
		crit.addOrder(Order.asc("atuDataAtualizada"));
		List<Atualizacao> lista= crit.list();
		return lista;
	}

}
