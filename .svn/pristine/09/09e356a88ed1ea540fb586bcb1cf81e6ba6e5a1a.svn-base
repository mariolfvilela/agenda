package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.renovar.modelo.Cliente;
import br.com.renovar.modelo.Historico;

public class HistoricoDAOHibernate implements HistoricoDAO {
	private Session session;
	public void setSession(Session session) {
	this.session = session;
}
	public void salvar(Historico historico) {
		this.session.save(historico);
	}

	public void atualizar(Historico historico) {
		this.session.update(historico);
	}

	public void excluir(Historico historico) {
		this.session.delete(historico);
	}

	public Historico carregar(Integer hisId) {
		return (Historico)this.session.get(Historico.class, hisId);
	}

	public Historico buscarPorHistorico(String historico) {
		String hql="select b from Historico b where b.hisChamado = :historico";
		Query consulta= this.session.createQuery(hql);
		consulta.setString("historico", historico);
		return (Historico) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Historico> listar() {
		Criteria crit= this.session.createCriteria(Historico.class);
		crit.addOrder(Order.asc("hisId"));
		return crit.list();
	}

	public boolean dependecias(Historico historico) {
		// TODO Auto-generated method stub
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<Historico> listaPorCliente(Cliente cliente) {
		Criteria crit= this.session.createCriteria(Historico.class);
		crit.add(Restrictions.eq("cliente.pesId", cliente.getPesId()));
		crit.addOrder(Order.desc("hisDataContato"));
		List<Historico> lista=crit.list();
		
		return lista;
	}
	public Historico ultimoHistoricoPorCliente(Cliente cliente) {
		Criteria crit= this.session.createCriteria(Historico.class);
		crit.add(Restrictions.eq("cliente.pesId", cliente.getPesId()));
		crit.addOrder(Order.desc("hisDataContato"));
		@SuppressWarnings("unchecked")
		List<Historico> lista=crit.list();
		Historico historico=new Historico();
		if(lista.size() != 0){
		historico=lista.get(0);
		}
		return historico;
	}

}
