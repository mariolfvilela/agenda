package br.com.renovar.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.renovar.modelo.Cliente;

public class ClienteDAOHibernate implements ClienteDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Cliente cliente) {
		this.session.save(cliente);
	}

	public void atualizar(Cliente cliente) {
		this.session.update(cliente);
	}

	public void excluir(Cliente cliente) {
		this.session.delete(cliente);
	}

	public Cliente carregar(Integer pesId) {
		return (Cliente) this.session.get(Cliente.class, pesId);
	}

	public Cliente buscarPorCliente(String cliente) {
		String hql = "select c from Cliente c where c.pessoa.pesNome = :cliente";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("cliente", cliente);		
		return (Cliente) consulta.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> listar() {
		return this.session.createCriteria(Cliente.class).list();
	}

	public boolean dependecias(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

}
