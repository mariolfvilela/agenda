package br.com.renovar.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

import br.com.renovar.util.HibernateUtil;

public class ConexaoHibernateFilter implements Filter{
	private SessionFactory sf;

	public void init(FilterConfig confg) throws ServletException {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	//Referências:
	//	www.guj.com.br/java/294296-aplicacao-dando-erro-no-tomcat/2 (Reposta: jpmpassos)
	//	stackoverflow.com/questions/13971460
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
		try {  
			try {  
				this.sf.getCurrentSession().beginTransaction();  
				chain.doFilter(request, response);  
				this.sf.getCurrentSession().getTransaction().commit();  
				this.sf.getCurrentSession().close();  
			} catch (Throwable ex) {  
				try {  
					if (this.sf.getCurrentSession().getTransaction().isActive())  
						this.sf.getCurrentSession().getTransaction().rollback(); 
				} catch (Throwable t) {  
					t.printStackTrace();  
				}  
				throw new ServletException(ex);  
			}  
		} catch (Exception e) {  
			this.sf.getCurrentSession().close();  
			this.sf.getCurrentSession().beginTransaction();  
			chain.doFilter(request, response);  
			this.sf.getCurrentSession().getTransaction().commit();  
			this.sf.getCurrentSession().close();  
			//FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");  
		}
	}
	
	public void destroy() {

	}
	
//  Código do MARIO de acordo com o livro:
//
//	@Override
//	public void doFilter(ServletRequest servletRequest,
//			ServletResponse servletResponse, FilterChain chain)
//			throws ServletException {
//		try {
//			this.sf.getCurrentSession().beginTransaction();
//			chain.doFilter(servletRequest, servletResponse);
//			this.sf.getCurrentSession().getTransaction().commit();
//			this.sf.getCurrentSession().close();
//		} catch (Throwable ex) {
//			try {
//				if (this.sf.getCurrentSession().getTransaction().isActive()) {
//					this.sf.getCurrentSession().getTransaction().rollback();
//				}
//			} catch (Throwable t) {
//				t.printStackTrace();
//			}
//			throw new ServletException(ex);
//		}
//	}

}
