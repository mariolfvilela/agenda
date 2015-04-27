package br.com.renovar.util;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.renovar.modelo.Funcionario;
import br.com.renovar.util.HibernateUtil;
import br.com.renovar.util.ContextoBean;

@ManagedBean(name = "genericBean")
@SessionScoped
public class GenericBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6953662389147414100L;
	@Getter @Setter private static Funcionario funcionarioLogado = new Funcionario();
	
	public GenericBean() { }
	
	public void verificaAutenticacao() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		
		if (contextoBean == null) {
			return;
		}
		
		if (contextoBean.getFuncionarioLogado() == null) {
			redirecionarParaPagina("publico/login.jsf");
			return;
		}

		/** Grava o funcionário logado em um várivel estática */
		/*String fun = contextoBean.getFuncionarioLogado().getFunLogin();
		funcionarioLogado = new FuncionarioRN().buscarPorLogin(fun);*/
		funcionarioLogado=contextoBean.getFuncionarioLogado();
		
		/** Bloqueia a página de login caso um funcionário esteja logado*/
		if (facesContext.getViewRoot().getViewId().contains("login") && funcionarioLogado != null) {
			
		}
	}
	
	public void mostrarAviso(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "")); 
	}
	
	public void mostrarErro(String mensagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "")); 
	}
	
	/**
	 * 
	 * 
	 * @param caminhoPagina Exemplo: http://www.google.com
	 */
	public void redirecionarParaPaginaExterna(String caminhoPagina) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(caminhoPagina);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("Problema ao redirecionar para " + caminhoPagina);
		}
	}
	
	/**
	 * Redireciona para uma página do sistema.
	 * 
	 * @param caminhoPagina Exemplo: restrito/imovel/cadastro.jsf
	 */
	public void redirecionarParaPagina(String caminhoPagina) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/" + caminhoPagina);
		} catch (IOException e) {
			System.out.println("Problema ao redirecionar para " + caminhoPagina);
		}
	}
	
	public void commit() {
		Transaction sessao = HibernateUtil.getSessionFactory().getCurrentSession().getTransaction();
		
		if (sessao != null && sessao.isActive())		
			sessao.commit();
	}
	
	/**
	 * Cancela todas alterações feitas no banco de dados na página
	 * @param msg mensagem de aviso para o úsuario
	 */
	public void rollback(String msg) {	
		/** TODO: O rollback não está funcionado corretamente */
		Transaction sessao = HibernateUtil.getSessionFactory().getCurrentSession().getTransaction();
		
		sessao.isParticipating();
		
		if (sessao.isActive() && !sessao.wasCommitted() && !sessao.isParticipating() && !sessao.wasRolledBack())
			sessao.rollback();

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
	}
	
	/**
	 * Eliminar objetos na session
	 * @param Eliminar objetos na session
	 */
	public void evict(Object object) {	
		Session sessao =  HibernateUtil.getSessionFactory()
				.getCurrentSession();
		sessao.evict(object);
	}
	
	public int getParametro(String nome, Integer valorPadrao) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
				
		try { 
			int valor = Integer.parseInt(facesContext.getExternalContext().getRequestParameterMap().get(nome));
	       	
			return valor;
	    } catch(NumberFormatException e) { 
	        return valorPadrao; 
	    }
	}

	public String getParametro(String nome, String valorPadrao) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		String valor = facesContext.getExternalContext().getRequestParameterMap().get(nome);
		
		return valor == null ? valorPadrao : valor;
	}
	
	public String getPaginaAtual() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		String viewId = facesContext.getViewRoot().getViewId();
		
		return viewId;
	}
	
	public double arredondarCasasDecimais(double value) {
	    long factor = (long) Math.pow(10, 2);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public Date primeiroDiaMes(Date date){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.set(Calendar.HOUR_OF_DAY, 0);
		 calendar.set(Calendar.MINUTE, 0);
		 calendar.set(Calendar.SECOND, 0);
		 calendar.set(Calendar.MILLISECOND, 0);
		 return calendar.getTime();
		}
	
	public Date ultimoDiaMes(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.DATE, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	public String formatarData(Date data){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
		return dateFormat.format(data);
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
}