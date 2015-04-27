package br.com.renovar.web.layout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.renovar.util.MenuUtil;
import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "menuBean")
@ViewScoped
public class MenuBean extends MenuUtil implements  Serializable {
	
	@Getter private static final long serialVersionUID = -2954567199177420579L;
	//@Getter @Setter private static Funcionario funcionarioLogado = new Funcionario();
	@Getter @Setter private Set<String> permissoes=new HashSet<String>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void configurarMenu() {		
		permissoes=new HashSet<String>();
		List<GrantedAuthority> auth = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(GrantedAuthority p: auth){
			String i=p.toString();
			permissoes.add(i);
		}
		
		/*FacesContext facesContext = FacesContext.getCurrentInstance();
		ContextoBean contextoBean = ContextoUtil.getContextoBean();

		String login=new String();
		if (contextoBean == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext external = context.getExternalContext();			 
			login = external.getRemoteUser();	
			funcionarioLogado = new FuncionarioRN().buscarPorLogin(login);
		}else{
			*//** Grava o funcionário logado em um várivel estática *//*
			funcionarioLogado = contextoBean.getFuncionarioLogado();
		}


		*//** Bloqueia a página de login caso um funcionário esteja logado*//*
		if (facesContext.getViewRoot().getViewId().contains("login") && funcionarioLogado != null) {

		}*/

		addMenu(new MenuItem("Principal", "restrito/principal.jsf"));
		menuAdministracao();
		/*menuCadastros();
		menuLocacao();
		menuFinanceiro();
		menuTestes();*/
		
		//iconis : http://fontawesome.info/website-lists-and-examples-using-font-awesome-icon-css/
		
		addMenu(new MenuItem("Atendimento", "restrito/atendimento/cadastro.jsf", "fa fa-phone fa-1x fa-spin"));//fa fa-phone fa-fw
		/*addMenu(new MenuItem("Pesquisar Imóvel", "restrito/consulta/pesquisaimovel.jsf", "fa-search"));
		addMenu(new MenuItem("Pesquisar Imóvel 2", "v2/pesquisa/index.jsp", "fa-search"));
		addMenu(new MenuItem("Pendências do Usuário", "restrito/pendencias/consulta.jsf", "fa-search"));*/
	}
	
	
	private void menuAdministracao() {
		Set<MenuItem> itens = new HashSet<MenuItem>();
		
		sairFor:
		for(String p: this.permissoes){
			if(p.equalsIgnoreCase("ROLE_ADMIN")){
				itens = new HashSet<MenuItem>();				
				itens.add( new MenuItem("FUNCIONARIOS","admin/funcionario/consulta.jsf"));
				itens.add( new MenuItem("VERSÕES DO SISTEMA","admin/versao/consulta.jsf"));
				itens.add( new MenuItem("PESSOA","restrito/pessoa/consulta.jsf"));
				itens.add( new MenuItem("CONTATO COM CLIENTE","restrito/cliente/consulta.jsf"));
				break sairFor;
			}
			
			if(p.equalsIgnoreCase("ROLE_USER")){
				itens.add( new MenuItem("PESSOA","restrito/pessoa/consulta.jsf"));
				itens.add( new MenuItem("CONTATO COM CLIENTE","restrito/cliente/consulta.jsf"));
			}

			
		}
		
		ArrayList<MenuItem> array=new ArrayList<MenuItem>();		
		for(MenuItem menu:itens)
			array.add(menu);
		addMenu( new MenuItem("Administração", array) );
	}


}