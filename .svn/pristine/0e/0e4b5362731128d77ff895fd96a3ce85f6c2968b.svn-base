package br.com.renovar.dao;

import java.text.Normalizer;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.renovar.modelo.Bairro;

public class BairroDAOHibernate implements BairroDAO {
	private Session session;
	public void setSession(Session session) {
	this.session = session;
}
	public void salvar(Bairro bairro) {
		//if(buscarPorBairro(bairro.getBaiNome())==null){
		bairro.setBaiNome(removerAcentos(bairro.getBaiNome()));
		this.session.save(bairro);//aqui era somente o metodo saveOrUpdate 
		/*}else{
			System.out.println("j� existe o Bairro \n");
		}*/
	}

	public void atualizar(Bairro bairro) {
		bairro.setBaiNome(removerAcentos(bairro.getBaiNome()));
		this.session.update(bairro);
		
	}

	public void excluir(Bairro bairro) {
		this.session.delete(bairro);
	}

	public Bairro carregar(Integer baiId) {
		return (Bairro)this.session.get(Bairro.class, baiId);
	}

	public Bairro buscarPorBairro(String bairro) {
		bairro=removerAcentos(bairro);
		String hql="select b from Bairro b where b.baiNome = :bairro";
		Query consulta= this.session.createQuery(hql);
		consulta.setString("bairro", bairro);
		return (Bairro) consulta.uniqueResult();

	}
	@SuppressWarnings("unchecked")
	public List<Bairro> listar() {
		Criteria crit= this.session.createCriteria(Bairro.class);
		crit.addOrder(Order.asc("baiNome"));
		return crit.list();
	}
	@SuppressWarnings("unchecked")
	public boolean dependecias(Bairro bairro) {
		int cont=0;
		
		Criteria crit= this.session.createCriteria(Bairro.class);		
		crit.createAlias("enderecos", "bai").add(Restrictions.eq("bai.bairro.baiId", bairro.getBaiId()));
		/*crit.createAlias("enderecoImovelDesejados", "baiDesejado").add(Restrictions.eq("baiDesejado.bairro.baiId", bairro.getBaiId()));
		Disjunction ou=Restrictions.disjunction();// representar uma expressÃ£o OR
		Conjunction e = Restrictions.conjunction();// representar uma expressÃ£o AND
		crit.add(Restrictions.eq("bai.bairro.baiId", bairro.getBaiId()));
		ou.add(Restrictions.eq("baiDesejado.bairro.baiId", bairro.getBaiId()));
		//crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.add(ou);*/
		List<Bairro> lista=crit.list();
	//	Bairro b=(Bairro) crit.uniqueResult();
		cont=lista.size();
		
		if(cont==0){
		return true;
		}
		return false;
	}
	
	public  String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
}
