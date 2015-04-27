package br.com.renovar.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("deprecation")
public abstract class MenuUtil {

	@Getter private StringBuilder htmlBuilder = new StringBuilder();
	
	public MenuUtil() {
		configurarMenu();
	}

	public abstract void configurarMenu();
	
	public void addMenu(MenuItem menu) {
		htmlBuilder.append(menu.toHtml());
	}
	
	public String showMenu() {
		StringBuilder menu = new StringBuilder();
		
		menu.append( openMenu() );
		menu.append( htmlBuilder.toString() );
		menu.append( closeMenu() );
		
		return menu.toString();
	}
	
	public String openMenu() {
		StringBuilder builder = new StringBuilder();		
		builder.append("<div class='navbar-default navbar-static-side' role='navigation'>");
		builder.append("<div class='side-menu-renovar'>");
		builder.append("<ul class='nav' id='side-menu'>");
	        
		return builder.toString();
	}
	
	public String closeMenu() {
		StringBuilder builder = new StringBuilder();
		builder.append("</ul>");
		builder.append("</div>");
		builder.append("</nav>");
		
		return builder.toString();
	}
	
	public class MenuItem {
		
		                private StringBuilder       htmlBuilder = new StringBuilder();
		                private String              baseUrl;
		@Getter @Setter private String              titulo;
		@Getter @Setter private String              url;
		@Getter @Setter private String              icone;
		@Getter @Setter private List<MenuItem> itens;
		@Getter @Setter private boolean             lista;
		@Getter @Setter private boolean             secondLevelMenu;
		@Getter @Setter private boolean             thirdLevelMenu;
		
		public MenuItem(String titulo, String url) {
			this.titulo = titulo;
			this.url    = url;
			
			carregaBaseUrl();
		}
		
		public MenuItem(String titulo, String url, String icone) {
			this.titulo = titulo;
			this.url    = url;
			this.icone  = icone;
			
			carregaBaseUrl();
		}
		
		public MenuItem(String titulo, List<MenuItem> itens) {
			this.titulo = titulo;
			this.itens  = itens;
			this.lista  = true;
			
			//Colocar em ordem alfabetica ArrayList
			Collections.sort(this.itens,
	                 new Comparator<MenuItem>()
	                 {
	                     public int compare(MenuItem p1, MenuItem p2)
	                     {
	                         return p1.getTitulo().compareTo(p2.getTitulo());
	                     }        
	                 });
			
			for (MenuItem subMenu : this.itens)
				if (secondLevelMenu)
					subMenu.setThirdLevelMenu(true);
				else
					subMenu.setSecondLevelMenu(true);
			
			carregaBaseUrl();
		}
		
		public MenuItem(String titulo, String icone, ArrayList<MenuItem> itens) {
			this(titulo, itens);
			this.icone  = icone;
		}
		
		private void carregaBaseUrl() {
			FacesContext context = FacesContext.getCurrentInstance();  
			Application app = context.getApplication();
			
			ValueBinding b = app.createValueBinding("#{request.contextPath}"); 
			Object value = b.getValue(context);
			
			this.baseUrl = (String) value;
		}
		
		public String toHtml() {
			if (url != null && !url.contains("http://") && !url.contains("https://") && !url.endsWith("#") )
				url = baseUrl + "/" + url;
			
			/** Making the menu */

			if (thirdLevelMenu)
				htmlBuilder.append("<li class='subLevelMenuRenovar'>");
			else if (secondLevelMenu)
				htmlBuilder.append("<li class='subMenuRenovar'>");
			else
				htmlBuilder.append("<li>");
			
			if (lista) {		
				
				if (icone != null && icone.trim().length() != 0)
					htmlBuilder.append(String.format("<a href='%s'><span class='fa %s'></span> %s <span class='fa arrow'></span>", url, icone, titulo));
				else
					htmlBuilder.append(String.format("<a href='%s'>%s <span class='fa arrow'></span>", url ,titulo));
				
			} else {	
				
				if (icone != null && icone.trim().length() != 0)
					htmlBuilder.append(String.format("<a href='%s'><span class='fa %s'></span> %s", url, icone, titulo));
				else
					htmlBuilder.append(String.format("<a href='%s'>%s", url ,titulo));
				
			}
			

			htmlBuilder.append("</a>");
			
			if (itens != null) {
				
				if (secondLevelMenu)
					htmlBuilder.append("<ul class='nav nav-second-level'>");
				else
					htmlBuilder.append("<ul class='nav nav-third-level'>");
				
				for (MenuItem subMenu : itens) {
					if (secondLevelMenu)
						subMenu.setThirdLevelMenu(true);
					
					htmlBuilder.append( subMenu.toHtml() );
				}
						
				htmlBuilder.append("</ul>");
			}
			
			htmlBuilder.append("</li>");
			
			return htmlBuilder.toString();
		}
		
	}
	
}
