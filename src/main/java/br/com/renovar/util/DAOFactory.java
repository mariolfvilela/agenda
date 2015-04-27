package br.com.renovar.util;
import br.com.renovar.dao.AtendimentoDAO;
import br.com.renovar.dao.AtendimentoDAOHibernate;
import br.com.renovar.dao.AtualizacaoDAO;
import br.com.renovar.dao.AtualizacaoDAOHibernate;
import br.com.renovar.dao.BairroDAO;
import br.com.renovar.dao.BairroDAOHibernate;
import br.com.renovar.dao.CidadeDAO;
import br.com.renovar.dao.CidadeDAOHibernate;
import br.com.renovar.dao.ClienteDAO;
import br.com.renovar.dao.ClienteDAOHibernate;
import br.com.renovar.dao.EMailDAO;
import br.com.renovar.dao.EMailDAOHibernate;
import br.com.renovar.dao.EnderecoDAO;
import br.com.renovar.dao.EnderecoDAOHibernate;
import br.com.renovar.dao.FuncionarioDAO;
import br.com.renovar.dao.FuncionarioDAOHibernate;
import br.com.renovar.dao.HistoricoDAO;
import br.com.renovar.dao.HistoricoDAOHibernate;
import br.com.renovar.dao.PessoaDAO;
import br.com.renovar.dao.PessoaDAOHibernate;
import br.com.renovar.dao.PessoaFisicaDAO;
import br.com.renovar.dao.PessoaFisicaDAOHibernate;
import br.com.renovar.dao.PessoaJuridicaDAO;
import br.com.renovar.dao.PessoaJuridicaDAOHibernate;
import br.com.renovar.dao.ReleaseDAO;
import br.com.renovar.dao.ReleaseDAOHibernate;
import br.com.renovar.dao.TelefoneDAO;
import br.com.renovar.dao.TelefoneDAOHibernate;
import br.com.renovar.dao.VersaoSistemaDAO;
import br.com.renovar.dao.VersaoSistemaDAOHibernate;
import br.com.renovar.dao.VisitaDAO;
import br.com.renovar.dao.VisitaDAOHibernate;


public class DAOFactory {

	public static PessoaDAO criarPessoaDAO() {
		PessoaDAOHibernate pessoaDAO = new PessoaDAOHibernate();
		pessoaDAO.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return pessoaDAO;
	}
	
	public static FuncionarioDAO criarFuncionarioDAO() {
		FuncionarioDAOHibernate funcionarioDAOHibernate = new FuncionarioDAOHibernate();
		funcionarioDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return funcionarioDAOHibernate;
	}
	
	public static PessoaFisicaDAO criarPessoaFisicaDAO() {
		PessoaFisicaDAOHibernate pessoaFisicaDAOHibernate  = new PessoaFisicaDAOHibernate();
		pessoaFisicaDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return pessoaFisicaDAOHibernate;
	}
	
	public static PessoaJuridicaDAO criarPessoaJuridicaDAO() {
		PessoaJuridicaDAOHibernate pessoaJuridicaDAOHibernate  = new PessoaJuridicaDAOHibernate();
		pessoaJuridicaDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return pessoaJuridicaDAOHibernate;
	}
	
	public static BairroDAO criarBairroDAO() {
		BairroDAOHibernate bairroDAODAOHibernate  = new BairroDAOHibernate();
		bairroDAODAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return bairroDAODAOHibernate;
	}
	
	public static CidadeDAO criarCidadeDAO() {
		CidadeDAOHibernate cidadeDAOHibernate  = new CidadeDAOHibernate();
		cidadeDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return cidadeDAOHibernate;
	}
	
	public static EnderecoDAO criarEnderecoDAO() {
		EnderecoDAOHibernate enderecoDAOHibernate  = new EnderecoDAOHibernate();
		enderecoDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return enderecoDAOHibernate;
	}
	
	public static ClienteDAO criarClienteDAO() {
		ClienteDAOHibernate clienteDAOHibernate  = new ClienteDAOHibernate();
		clienteDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return clienteDAOHibernate;
	}
	
	public static HistoricoDAO criarHistoricoDAO() {
		HistoricoDAOHibernate historicoDAOHibernate  = new HistoricoDAOHibernate();
		historicoDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return historicoDAOHibernate;
	}
	
	public static TelefoneDAO criarTelefoneDAO() {
		TelefoneDAOHibernate telefoneDAOHibernate  = new TelefoneDAOHibernate();
		telefoneDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return telefoneDAOHibernate;
	}
	
	public static EMailDAO criarEMailDAO() {
		EMailDAOHibernate mailDAOHibernate  = new EMailDAOHibernate();
		mailDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return mailDAOHibernate;
	}
	
	public static VisitaDAO criarVisitaDAO() {
		VisitaDAOHibernate visitaDAOHibernate  = new VisitaDAOHibernate();
		visitaDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return visitaDAOHibernate;
	}
	
	public static VersaoSistemaDAO criarVersaoSistemaDAO() {
		VersaoSistemaDAOHibernate versaoSistemaDAOHibernate  = new VersaoSistemaDAOHibernate();
		versaoSistemaDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return versaoSistemaDAOHibernate;
	}
	
	public static ReleaseDAO criarReleaseDAO() {
		ReleaseDAOHibernate releaseDAOHibernate= new ReleaseDAOHibernate();
		releaseDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return releaseDAOHibernate;
	}
	
	public static AtualizacaoDAO criarAtualizacaoDAO() {
		AtualizacaoDAOHibernate atualizacaoDAOHibernate= new AtualizacaoDAOHibernate();
		atualizacaoDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return atualizacaoDAOHibernate;
	}	
	
	public static AtendimentoDAO criarAtendimentoDAO() {
		AtendimentoDAOHibernate atendimentoDAOHibernate= new AtendimentoDAOHibernate();
		atendimentoDAOHibernate.setSession(HibernateUtil.getSessionFactory()
				.getCurrentSession());
		return atendimentoDAOHibernate;
	}
}
