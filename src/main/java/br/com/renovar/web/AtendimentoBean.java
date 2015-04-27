package br.com.renovar.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import lombok.Getter;
import lombok.Setter;
import br.com.renovar.modelo.Atendimento;
import br.com.renovar.modelo.Cliente;
import br.com.renovar.modelo.Funcionario;
import br.com.renovar.rn.AtendimentoRN;
import br.com.renovar.rn.ClienteRN;
import br.com.renovar.util.GenericBean;

@ManagedBean(name = "atendimentoBean")
@ViewScoped
public class AtendimentoBean extends GenericBean implements Serializable{

	/**
	 * 
	 */
	@Getter private static final long serialVersionUID = 26901200415311296L;
	@Getter @Setter private Atendimento atendimento;
	@Getter @Setter private Funcionario funcionario;
	@Getter @Setter private Cliente cliente;
	
	@Getter @Setter private List<Atendimento> listaAtendimentos;
	
	@PostConstruct
	public void init(){
	String paginaAtual = super.getPaginaAtual();
		
		if (paginaAtual.contains("restrito/atendimento/cadastro")) {
			this.cliente=new Cliente();
			this.atendimento=new Atendimento();
			this.funcionario=super.getFuncionarioLogado();
		}
		
		if (paginaAtual.contains("restrito/atendimento/consulta")) {
			this.listaAtendimentos=new AtendimentoRN().listarPorFuncionario(super.getFuncionarioLogado());
		}
		
		if (paginaAtual.contains("restrito/atendimento/consulta-todos")) {
			this.listaAtendimentos=new AtendimentoRN().listar();
		}
	}
	
	public List<Cliente> pesquisarCliente(String query){
		
		ClienteRN clienteRN = new ClienteRN();
		
		List<Cliente> cli=new ArrayList<Cliente>();
		
		for(Cliente c: clienteRN.listar()){

			//passando a string para minuscula e verificando se existe nome inicial com a variavel query (* seleciona todas as string)
			if((c.getPessoa().getPesNome().toLowerCase().startsWith(query.toLowerCase()))||(query.equals("*"))){
					cli.add(c);
			}
		}
		// Em ordem crescente 
        Collections.sort (cli, new Comparator<Cliente>() {  
            public int compare(Cliente o1, Cliente o2) {  
            	Cliente p1 = (Cliente) o1;  
            	Cliente p2 = (Cliente) o2;  
                return p1.getPesId() < p2.getPesId() ? -1 : (p1.getPesId() > p2.getPesId() ? +1 : 0);  
            }  
        });  
		return cli;
	}
	
	public void handleSelect(SelectEvent event) {  
		this.cliente=new Cliente();
		this.cliente=((Cliente) event.getObject());
		super.mostrarAviso("Pessoa selecionada: "+this.cliente.getPessoa().getPesNome()); 
	}
	
	public void salvar(){
		if(this.cliente.getPesId()==0){
			super.mostrarErro("Cliente não selecionado para registrar atendimento");
			return;
		}
		
		if(this.atendimento.getAteDescricao().equals("")){
			super.mostrarErro("Descrição do atendimento não preenchida");
			return;
		}
		this.atendimento.setAteDataCadastro(new Date());
		this.atendimento.setCliente(this.cliente);
		this.atendimento.setFuncionario(super.getFuncionarioLogado());
		new AtendimentoRN().salvar(this.atendimento);
		super.redirecionarParaPagina("restrito/atendimento/consulta.jsf");
	}



}
