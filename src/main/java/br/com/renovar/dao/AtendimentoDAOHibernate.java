package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.renovar.modelo.Atendimento;
import br.com.renovar.modelo.Funcionario;

public class AtendimentoDAOHibernate implements AtendimentoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Atendimento atendimento) {
		this.session.save(atendimento);
	}

	public void atualizar(Atendimento atendimento) {
		this.session.update(atendimento);
	}

	public void excluir(Atendimento atendimento) {
		this.session.delete(atendimento);
	}

	public Atendimento carregar(Integer ateId) {
		return (Atendimento) this.session.get(Atendimento.class, ateId);
	}

	public Atendimento buscarPorAtendimento(String atendimento) {
		String hql = "select c from Atendimento c where c.ateDescricao = :atendimento";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("atendimento", atendimento);		
		return (Atendimento) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> listar() {
		return this.session.createCriteria(Atendimento.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Atendimento> listarPorFuncionario(Funcionario funcionario) {
		Criteria crit= this.session.createCriteria(Atendimento.class);
		crit.add(Restrictions.eq("funcionario.pesId", funcionario.getPesId()));
		crit.addOrder(Order.desc("ateDataCadastro"));
		List<Atendimento> lista= crit.list();
		return lista;
	}

}
