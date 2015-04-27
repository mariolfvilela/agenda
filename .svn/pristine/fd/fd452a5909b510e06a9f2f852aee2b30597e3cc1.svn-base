package br.com.renovar.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.TreeNode;

import lombok.Getter;
import lombok.Setter;
import br.com.renovar.modelo.Atualizacao;
import br.com.renovar.modelo.Bairro;
import br.com.renovar.modelo.Cidade;
import br.com.renovar.modelo.Cliente;
import br.com.renovar.modelo.Endereco;
import br.com.renovar.modelo.Historico;
import br.com.renovar.modelo.Pessoa;
import br.com.renovar.modelo.Release;
import br.com.renovar.modelo.VersaoSistema;
import br.com.renovar.modelo.Visita;
import br.com.renovar.rn.AtualizacaoRN;
import br.com.renovar.rn.ClienteRN;
import br.com.renovar.rn.HistoricoRN;
import br.com.renovar.rn.PessoaRN;
import br.com.renovar.rn.ReleaseRN;
import br.com.renovar.rn.VersaoSistemaRN;
import br.com.renovar.rn.VisitaRN;
import br.com.renovar.util.GenericBean;

@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean extends GenericBean implements Serializable{

	/**
	 * 
	 */
	@Getter private static final long serialVersionUID = 7178116138020731713L;
	@Getter @Setter private Pessoa pessoa;
	@Getter @Setter private Endereco endereco;
	@Getter @Setter private Bairro bairro;
	@Getter @Setter private Cidade cidade;
	@Getter @Setter	private Character tipoPessoa=new Character('F');
	
	@Getter @Setter private Cliente cliente;
	@Getter @Setter private Historico historico;
	@Getter @Setter private Visita visita;
	@Getter @Setter private Atualizacao atualizacao;
	@Getter @Setter private Release release;
	@Getter @Setter private VersaoSistema versaoSistema;
	@Getter @Setter private String versaoCliente;
	
	@Getter @Setter private List<Pessoa> listaClientes;
	@Getter @Setter private List<Historico> listaHistorico;
	@Getter @Setter private List<Visita> listaVisitas;
	@Getter @Setter private List<String> listaProspeccao;
	@Getter @Setter private List<String> listaVersaoReleases;
	
	@Getter @Setter private ScheduleModel eventModel;
	@Getter @Setter private ScheduleEvent event = new DefaultScheduleEvent();
	@Getter @Setter private TreeNode root;
	@Getter @Setter private VersaoTemp versaoTemp;
 	
	@PostConstruct
	public void init(){
		this.pessoa=new Pessoa();
		this.historico=new Historico();
		this.visita=new Visita();
		this.release=new Release();
		this.versaoSistema=new VersaoSistema();
		this.atualizacao=new Atualizacao();
		this.listaVersaoReleases=new ArrayList<String>();
		this.versaoCliente=new String();
		
		String paginaAtual = super.getPaginaAtual();
		
		PessoaRN pessoaRN=new PessoaRN();
		
		if (paginaAtual.contains("restrito/principal")) {
			this.listaClientes=new ArrayList<Pessoa>();
			List<Pessoa> lista=pessoaRN.listar();			
			for(Pessoa pes: lista){
				if(pes.getCliente()!=null){
					this.listaClientes.add(pes);
				}
			}
			
			if(this.listaClientes!=null){
				verificarProspeccao();
			}else{
				this.listaProspeccao=new ArrayList<String>();
			}
		}
		
		if (paginaAtual.contains("admin/visita/confirmar")) {
			VisitaRN visitaRN = new VisitaRN();
			this.listaVisitas= visitaRN.listarNaoVisitadas();
			this.visita=new Visita();
		}
		
		if (paginaAtual.contains("restrito/visita")) {
			eventModel = new DefaultScheduleModel();
			this.listaVisitas=new VisitaRN().listarNaoVisitadas();
			for(Visita vis: this.listaVisitas){
				eventModel.addEvent(new DefaultScheduleEvent("-"+vis.getCliente().getPessoa().getPesNome(), vis.getVisDataAgendadaInicio(),vis.getVisDataAgendadaFim(),vis));
			}
		}

		if (paginaAtual.contains("restrito/cliente/consulta")) {
			this.listaClientes=new ArrayList<Pessoa>();
			List<Pessoa> lista=pessoaRN.listar();			
			for(Pessoa pes: lista){
				if(pes.getFuncionario()==null){
					this.listaClientes.add(pes);
				}
			}
			
		}
		
		if (paginaAtual.contains("restrito/cliente/cadastro")) {
			int pessoaID = super.getParametro("id", -1);
			
			this.pessoa=pessoaRN.carregar(pessoaID);
			
			if(this.pessoa != null){
				if(this.pessoa.getPessoaFisica() != null){
					this.tipoPessoa='F';
				}				
				if(this.pessoa.getPessoaJuridica() != null){
					this.tipoPessoa='J';
				}
				
				if(this.pessoa.getCliente() == null){
					this.cliente=new Cliente();
					this.listaHistorico=new ArrayList<Historico>();
				}else{
					this.cliente=this.pessoa.getCliente();
					this.listaHistorico=new HistoricoRN().listaPorCliente(this.cliente);
				}
				
				Atualizacao at=new AtualizacaoRN().buscarPorCliente(this.cliente);
				if(at!=null){
					Release rel=new ReleaseRN().carregar(at.getRelease().getRelId());
					this.versaoCliente= "<li>"+
									"<a class='todo-actions' href='javascript:void(0)' style='font-size:12px'>"+
									"<i class='fa fa-chevron-right'></i>"+
									"  "+
									"<span class='label label-success' style='opacity: 1;font-size:18px'> "+rel.getVersaoSistema().getVerSisNome()+"-"+rel.getRelNome()+"</span>"+
									"</a>"+
									"</li>";
					super.evict(at);
					super.evict(rel);
				}else{
					this.versaoCliente= "<li>"+
							"<a class='todo-actions' href='javascript:void(0)' style='font-size:12px'>"+
							"<i class='fa fa-chevron-right'></i>"+
							" "+""+" "+
							"<span class='label label-danger' style='opacity: 1;font-size:18px'> Não há versão no cliente</span>"+
							"</a>"+
							"</li>";
				}
			}
			
			this.versaoTemp=new VersaoTemp();
			this.root=criarTreeNode();
		}
		
		if (paginaAtual.contains("restrito/cliente/vistoria")) {
			int pessoaID = super.getParametro("id", -1);
			
			this.pessoa=pessoaRN.carregar(pessoaID);
			
			if(this.pessoa != null){
				if(this.pessoa.getCliente()==null){
					this.cliente=new Cliente();
					this.cliente.setPessoa(this.pessoa);
					this.cliente.setCliSituacao("Inicio da utilização do sistema");
					this.cliente.setCliModulo("");
					new ClienteRN().salvar(this.cliente);
					this.historico=new Historico();
					this.historico.setCliente(this.cliente);
					this.historico.setHisDataContato(new Date());
					HistoricoRN historicoRN = new HistoricoRN();
					historicoRN.salvar(this.historico);
					this.historico=new Historico();
					this.listaHistorico=historicoRN.listaPorCliente(this.cliente);
					this.pessoa.setCliente(this.cliente);
				}
					eventModel = new DefaultScheduleModel();
					this.listaVisitas=new VisitaRN().listarVisitasPorCliente(this.pessoa.getCliente());
					for(Visita vis: this.listaVisitas){
						eventModel.addEvent(new DefaultScheduleEvent("-"+vis.getVisMotivo(), vis.getVisDataAgendadaInicio(),vis.getVisDataAgendadaFim(),vis));
					}				
			}
		}

	}
	
	public void salvarInformacoes() {

		if(this.pessoa.getCliente() == null){
			this.cliente.setPessoa(this.pessoa);
		}
		
		new ClienteRN().salvar(this.cliente);

		this.historico.setCliente(this.cliente);
		this.historico.setHisDataContato(new Date());
		HistoricoRN historicoRN = new HistoricoRN();
		historicoRN.salvar(this.historico);
		this.historico=new Historico();
		this.listaHistorico=historicoRN.listaPorCliente(this.cliente);
	}
	
	public String historicoCliente(int id){
		Cliente cli=new Cliente();
		cli.setPesId(id);
		Historico his=new HistoricoRN().ultimoHistoricoPorCliente(cli);
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yy"); 
		if(his != null){
			if(!((his.getHisSituacao()==null)||(his.getHisDataContato()==null))){
				String hist=new String();
				for(int i=0;i<his.getHisSituacao().length();i++){
					hist+=his.getHisSituacao().charAt(i);
					if(i>28){
						hist+="...";
						break;
					}
				}
				return hist+"- DATA: "+dt.format(his.getHisDataContato());
			}else {
				return "";
			}
		}else{
			return "";
		}
	}
	
	public String style(String situacao){
		if(situacao.equals("BOM")){
			return "color:blue; font-size:12px";
		}else if(situacao.equals("RUIM")){
			return "color:red; font-size:12px ";
		} else if(situacao.equals("ÓTIMO")){
			return "color:green; font-size:12px";
		}

		return "color:blue; font-size:12px";
	}
	
	
	@SuppressWarnings("deprecation")
	public void onDateSelect(SelectEvent selectEvent) {
		this.visita=new Visita();
		Date data=(Date) selectEvent.getObject();
		Calendar calendar= Calendar.getInstance();
		calendar.setTime(data);
		Date dat=new Date();
		calendar.add(Calendar.HOUR, dat.getHours());
		calendar.add(Calendar.MINUTE, dat.getMinutes());
		this.visita.setVisDataAgendadaInicio(calendar.getTime());
		this.visita.setVisDataAgendadaFim(calendar.getTime());
	}
	
	public void onEventSelect(SelectEvent selectEvent) {
		this.visita=new Visita();
		event = (ScheduleEvent) selectEvent.getObject();
		this.visita=(Visita) event.getData();
		if(this.visita==null){
			this.visita=new Visita();
			super.mostrarAviso("Tente novamente ");
			return;
		}		
	}
	
	public void onEventMove(ScheduleEntryMoveEvent event) {
		this.visita=new Visita();
		VisitaRN visitaRN = new VisitaRN();		
		this.visita =((Visita) event.getScheduleEvent().getData());	
		if(this.visita==null){
			super.mostrarAviso("Tente novamente ");
			return;
		}
		/*		this.visita=visitaRN.carregar(this.visita.getVisId());
		Date inicio = this.visita.getVisDataAgendadaInicio();
		inicio.setMinutes(inicio.getMinutes() + event.getMinuteDelta());
		inicio.setDate((this.visita.getVisDataAgendadaInicio().getDate()) + (event.getDayDelta()));
		
		Date fim = this.visita.getVisDataAgendadaFim();
		fim.setMinutes(fim.getMinutes() + event.getMinuteDelta());
		fim.setDate((this.visita.getVisDataAgendadaFim().getDate()) + (event.getDayDelta()));*/
		
		java.text.SimpleDateFormat day = new java.text.SimpleDateFormat(
				"dd/MM/yyyy");
		java.text.SimpleDateFormat hor = new java.text.SimpleDateFormat(
				"hh:mm");
	//	this.visita.setVisDataAgendadaInicio(inicio);
	//	this.visita.setVisDataAgendadaFim(fim);
		visitaRN.salvar(this.visita);
		
		super.mostrarAviso("Agenda Modificada. Dia:"+day.format(this.visita.getVisDataAgendadaInicio())+" .Horario:" + hor.format(this.visita.getVisDataAgendadaInicio())+"hs até " + hor.format(this.visita.getVisDataAgendadaFim())+"hs, \n OBS: "
						+ this.visita.getVisMotivo());

	}
	
	
	public void onEventResize(ScheduleEntryResizeEvent event) {
		this.visita=new Visita();
		VisitaRN visitaRN = new VisitaRN();		
		this.visita =((Visita) event.getScheduleEvent().getData());	
		if(this.visita==null){
			super.mostrarAviso("Tente novamente ");
			return;
		}
		/*this.visita=visitaRN.carregar(this.visita.getVisId());
		Date fim = this.visita.getVisDataAgendadaInicio();
		fim.setMinutes(fim.getMinutes() + event.getMinuteDelta());
		fim.setDate((this.visita.getVisDataAgendadaFim().getDate()) + (event.getDayDelta()));*/
		java.text.SimpleDateFormat day = new java.text.SimpleDateFormat(
				"dd/MM/yyyy");
		java.text.SimpleDateFormat hor = new java.text.SimpleDateFormat(
				"hh:mm");
	//	this.visita.setVisDataAgendadaFim(fim);
		visitaRN.salvar(this.visita);
		
		super.mostrarAviso("Agenda Modificada. Dia:"+day.format(this.visita.getVisDataAgendadaInicio())+" .Horario:" + hor.format(this.visita.getVisDataAgendadaInicio())+"hs até " + hor.format(this.visita.getVisDataAgendadaFim())+"hs, \n OBS: "
						+ this.visita.getVisMotivo());
	
	}
	
	public void salvarVisita(){
		if((this.visita.getVisDataAgendadaInicio()==null)||(this.visita.getVisMotivo()==null)
		||(this.visita.getVisMotivo().equals(""))){
			super.mostrarErro("Falta campos para cadastro de visita");
		}
		
		if(this.pessoa==null){
			super.redirecionarParaPagina("restrito/cliente/consulta.jsf");
		}
		
		this.visita.setCliente(this.pessoa.getCliente());
		//this.visita.setVisDataAgendadaFim(this.visita.getVisDataAgendadaInicio());
		this.visita.setVisDataCadastrada(new Date());
		this.visita.setFuncionario(super.getFuncionarioLogado());
		this.visita.setVisPosVisita("");
		VisitaRN visitaRN = new VisitaRN();
		visitaRN.salvar(this.visita);
		this.visita=new Visita();
		this.listaVisitas=visitaRN.listarVisitasPorCliente(this.pessoa.getCliente());
		eventModel = new DefaultScheduleModel();
		for(Visita vis: this.listaVisitas){
			eventModel.addEvent(new DefaultScheduleEvent("-"+vis.getVisMotivo(), vis.getVisDataAgendadaInicio(),vis.getVisDataAgendadaFim(), vis));
		}
	}
	
	private void verificarProspeccao(){
		this.listaProspeccao=new ArrayList<String>();
		this.listaVisitas=new VisitaRN().listarNaoVisitadas();
		Calendar dataHoje=Calendar.getInstance();
		Calendar dataVisita= Calendar.getInstance();
		for(Visita vis: this.listaVisitas){
			dataVisita.setTime(vis.getVisDataAgendadaInicio());
			if(dataVisita.getTimeInMillis()<=dataHoje.getTimeInMillis()){
				this.listaProspeccao.add("<li>"+
						"<a class='todo-actions' href='javascript:void(0)' style='font-size:11px'>"+
						"<i class='fa fa-chevron-right'></i>"+
						" "+vis.getCliente().getPessoa().getPesNome()+" "+
						"<span class='label label-danger' style='opacity: 1;font-size:11px'> Atrasada</span>"+
						"</a>"+
						"</li>");
			}else if(dataVisita.get(Calendar.DAY_OF_YEAR)==dataHoje.get(Calendar.DAY_OF_YEAR)){
				this.listaProspeccao.add("<li>"+
						"<a class='todo-actions' href='javascript:void(0)' style='font-size:11px'>"+
						"<i class='fa fa-chevron-right'></i>"+
						" "+vis.getCliente().getPessoa().getPesNome()+" "+
						"<span class='label label-warning' style='opacity: 1;'> Hoje</span>"+
						"</a>"+
						"</li>");
			}else if((dataVisita.get(Calendar.DAY_OF_YEAR))==(dataHoje.get(Calendar.DAY_OF_YEAR)+1)){
				this.listaProspeccao.add("<li>"+
						"<a class='todo-actions' href='javascript:void(0)' style='font-size:11px'>"+
						"<i class='fa fa-chevron-right'></i>"+
						" "+vis.getCliente().getPessoa().getPesNome()+" "+
						"<span class='label label-success' style='opacity: 1;'> Amanhã</span>"+
						"</a>"+
						"</li>");
			}else if(dataVisita.get(Calendar.WEEK_OF_YEAR)==dataHoje.get(Calendar.WEEK_OF_YEAR)){
				this.listaProspeccao.add("<li>"+
						"<a class='todo-actions' href='javascript:void(0)' style='font-size:11px'>"+
						"<i class='fa fa-chevron-right'></i>"+
						" "+vis.getCliente().getPessoa().getPesNome()+" "+
						"<span class='label label-info' style='opacity: 1;'> Esta semana</span>"+
						"</a>"+
						"</li>");
			}
		}
	}
			
	public void registrarVisita(){
		this.visita.setVisDataVisita(new Date());
		VisitaRN visitaRN = new VisitaRN();
		visitaRN.salvar(this.visita);
		this.visita=new Visita();
		this.listaVisitas=visitaRN.listarNaoVisitadas();
	}
	
	public String versaoClienteTelaPrincipal(int id){
		if(id>0){
			Cliente cli=new Cliente();
			cli.setPesId(id);
			Atualizacao at=new AtualizacaoRN().buscarPorCliente(cli);
			//aqui tava com o erro : com.sun.jdi.invocationexception occurred invoking method. na anotação @OneToMany dai coloquei o @Transient  e deu certo !
			if(at==null){
				return "Cliente sem versão do sistema";
			}
			
			super.evict(at);
			return at.getRelease().getVersaoSistema().getVerSisNome()+"-"+at.getRelease().getRelNome();

		}else{
			return "";
		}

	}
	
	public TreeNode criarTreeNode(){
		TreeNode root=new DefaultTreeNode(new VersaoTemp(0, 0, ""), null);
		List<VersaoSistema> listaVersoesSis=new VersaoSistemaRN().listar();
		for(VersaoSistema vers: listaVersoesSis){
			TreeNode topo  = new DefaultTreeNode(new VersaoTemp(vers.getVerSisId(), 0, vers.getVerSisNome()), root);
			List<Release> listaRel=new ReleaseRN().listarPorVersao(vers);
			for(Release rel: listaRel){
				@SuppressWarnings("unused")
				TreeNode raiz = new DefaultTreeNode(new VersaoTemp(vers.getVerSisId(), rel.getRelId(), rel.getRelNome()), topo);
			}        
		}
        
        return root;
	}
	/* ######################################################################################################
	 * 
	 * CLASSES USADAS SOMENTE NESTE BEAN
	 * 
	 * ###################################################################################################### */
	
	/**
	 * Classe Temporaria usada para armazenar as versões do sistema
	 */	
	public class VersaoTemp implements Serializable {
		 private static final long serialVersionUID = 1842833206160058579L;
		@Getter @Setter private int idVersao = 0;
		@Getter @Setter private int idRelese= 0;
		@Getter @Setter private String nome;
		
		public VersaoTemp() {		}
		public VersaoTemp(int idVersao, int idRelease, String nome) {		
		this.idVersao=idVersao;
		this.idRelese=idRelease;
		this.nome=nome;}
	}
	
	public void registrarVersao(){
		if((this.cliente==null)||(this.cliente.getPesId()==0)){			
			this.cliente.setPessoa(this.pessoa);
			this.cliente.setCliSituacao("Inicio da utilização do sistema");
			this.cliente.setCliModulo("");
			new ClienteRN().salvar(this.cliente);
			
			this.historico=new Historico();
			this.historico.setCliente(this.cliente);
			this.historico.setHisDataContato(new Date());
			this.historico.setHisSituacao("Inicio do sistema");
			HistoricoRN historicoRN = new HistoricoRN();
			historicoRN.salvar(this.historico);
			this.historico=new Historico();
			this.listaHistorico=historicoRN.listaPorCliente(this.cliente);
		}
		this.atualizacao=new Atualizacao();
		this.atualizacao.setAtuDataAgendadaFim(new Date());
		this.atualizacao.setAtuDataAgendadaInicio(new Date());
		this.atualizacao.setAtuDataAtualizada(new Date());
		this.atualizacao.setAtuDescricao("Atualizada no cadastro do contato");
		this.atualizacao.setAtuTipo("");
		this.atualizacao.setFuncionario(super.getFuncionarioLogado());
		this.atualizacao.setCliente(this.cliente);
		
		this.release=new ReleaseRN().carregar(this.versaoTemp.getIdRelese());
		
		this.atualizacao.setRelease(this.release);
		
		new AtualizacaoRN().salvar(this.atualizacao);
		
		this.versaoCliente="<li>"+
				"<a class='todo-actions' href='javascript:void(0)' style='font-size:12px'>"+
				"<i class='fa fa-chevron-right'></i>"+
				"  "+
				"<span class='label label-success' style='opacity: 1;font-size:18px'> "+this.release.getVersaoSistema().getVerSisNome()+"-"+this.release.getRelNome()+"</span>"+
				"</a>"+
				"</li>";
	}
	
	public String versoesPorCliente(int id){
		String versoes=new String();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		ReleaseRN releaseRN = new ReleaseRN();
		if(id>0){
			Cliente cli=new Cliente();
			cli.setPesId(id);
			List<Atualizacao> listaAtualizacao=new AtualizacaoRN().listarPorCliente(cli);
			for(Atualizacao atu: listaAtualizacao){
				Release rel=releaseRN.carregar(atu.getRelease().getRelId());
				versoes+="Func."+atu.getFuncionario().getPessoa().getPesNome()+" ("+"Data: "+sdf.format(atu.getAtuDataAtualizada())+"["+rel.getVersaoSistema().getVerSisNome()+"-"+rel.getRelNome()+"]"+")";
				super.evict(rel);
			}
		}
		return versoes;
	}
}
