package br.com.renovar.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.renovar.modelo.Funcionario;
import br.com.renovar.modelo.Pessoa;

public class FuncionarioDAOHibernate implements FuncionarioDAO{

	private Session session;
	public void setSession(Session session) {
	this.session = session;
}
	
	public void salvar(Funcionario funcionario) {
		this.session.save(funcionario);

	}

	public void atualizar(Funcionario funcionario) {
		this.session.update(funcionario);

	}

	public void excluir(Funcionario funcionario) {
		this.session.delete(funcionario);

	}

	public Funcionario carregar(Integer pesId) {
		return (Funcionario)this.session.get(Funcionario.class, pesId);
	}

	public Funcionario buscarPorFuncionario(String funcionario) {
		String hql="select b from Funcionario b where b.funLoguin = :funcionario";
		Query consulta= this.session.createQuery(hql);
		consulta.setString("funcionario", funcionario);
		Funcionario fun= (Funcionario) consulta.uniqueResult();
		return fun;
	}
	@SuppressWarnings("unchecked")
	public List<Funcionario> listar() {
		Criteria crit= this.session.createCriteria(Funcionario.class);
		crit.addOrder(Order.asc("funLoguin"));
		return crit.list();
	}

	public boolean dependecias(Funcionario funcionario) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listarFuncionariosPessoas() {
		List<Pessoa> lista=new ArrayList<Pessoa>();
		Criteria crit= this.session.createCriteria(Pessoa.class);
		List<Pessoa> l= crit.list();
		for(Pessoa lis: l){
			if(lis.getFuncionario()!=null)
				lista.add(lis);
		}
		
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listaPessoasNaoFuncionarios() {
		List<Pessoa> lista=new ArrayList<Pessoa>();
		Criteria crit= this.session.createCriteria(Pessoa.class);
		List<Pessoa> l= crit.list();
		for(Pessoa lis: l){
			if((lis.getFuncionario()==null)&&(lis.getPessoaFisica()!=null))
				lista.add(lis);
		}
		
		return lista;
	}

}
