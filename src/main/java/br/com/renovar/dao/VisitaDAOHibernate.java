package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.renovar.modelo.Cliente;
import br.com.renovar.modelo.Visita;

public class VisitaDAOHibernate implements VisitaDAO {
	private Session session;
	public void setSession(Session session) {
	this.session = session;
}
	public void salvar(Visita visita) {
		this.session.save(visita);
	}

	public void atualizar(Visita visita) {
		this.session.update(visita);
	}

	public void excluir(Visita visita) {
		this.session.delete(visita);
	}

	public Visita carregar(Integer pesId) {
		return (Visita)this.session.get(Visita.class, pesId);
	}

	public Visita buscarPorVisita(String visita) {
		String hql="select b from Visita b where b.visId = :pesId";
		Query consulta= this.session.createQuery(hql);
		consulta.setString("pesId", visita);
		return (Visita) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Visita> listar() {
		Criteria crit= this.session.createCriteria(Visita.class);
		crit.addOrder(Order.asc("visId"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Visita> listarVisitasPorCliente(Cliente cliente) {
		Criteria crit= this.session.createCriteria(Visita.class);
		crit.add(Restrictions.eq("cliente.pesId", cliente.getPesId()));
		crit.add(Restrictions.isNull("visDataVisita"));
		crit.addOrder(Order.asc("visDataAgendadaInicio"));
		return crit.list();
	}
	@SuppressWarnings("unchecked")
	public List<Visita> listarNaoVisitadas() {
		Criteria crit= this.session.createCriteria(Visita.class);
		crit.add(Restrictions.isNull("visDataVisita"));
		crit.addOrder(Order.asc("visDataAgendadaInicio"));
		List<Visita> lista= crit.list();
		return  lista;
	}

}
