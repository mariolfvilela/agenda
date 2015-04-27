package br.com.renovar.web;

import java.io.Serializable;
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
import br.com.renovar.modelo.Cliente;
import br.com.renovar.modelo.Historico;
import br.com.renovar.modelo.Pessoa;
import br.com.renovar.modelo.Release;
import br.com.renovar.modelo.VersaoSistema;
import br.com.renovar.rn.AtualizacaoRN;
import br.com.renovar.rn.ClienteRN;
import br.com.renovar.rn.HistoricoRN;
import br.com.renovar.rn.PessoaRN;
import br.com.renovar.rn.ReleaseRN;
import br.com.renovar.rn.VersaoSistemaRN;
import br.com.renovar.util.GenericBean;

@ManagedBean(name = "atualizacaoBean")
@ViewScoped
public class AtualizacaoBean extends GenericBean implements Serializable{

	/**
	 * 
	 */
	@Getter private static final long serialVersionUID = 460812069180393599L;
	@Getter @Setter private Pessoa pessoa;
	@Getter @Setter private Cliente cliente;
	@Getter @Setter private Historico historico;
	@Getter @Setter private VersaoSistema versaoSistema;
	@Getter @Setter private Release release;
	@Getter @Setter Atualizacao atualizacao;
	@Setter private String versaoCliente;
	
	@Getter @Setter Boolean alterar=false;
	
	@Getter @Setter List<VersaoSistema> listaVersoes;
	@Getter @Setter List<Release> listaReleases;
	@Getter @Setter List<Atualizacao> listaAtualizacoes;
	
	@Getter @Setter private ScheduleModel eventModel;
	@Getter @Setter private ScheduleEvent event = new DefaultScheduleEvent();
	
	@Getter @Setter private TreeNode root;
	@Getter @Setter private VersaoTemp versaoTemp;
	@Getter @Setter private VersaoTemp versaoTempEscolhida;
	
	@PostConstruct
	public void init(){
		this.versaoCliente=new String();
		this.versaoSistema=new VersaoSistema();
		this.release=new Release();
		
		VersaoSistemaRN versaoSistemaRN = new VersaoSistemaRN();
		AtualizacaoRN atualizacaoRN = new AtualizacaoRN();
		
		String paginaAtual = super.getPaginaAtual();
		
		if (paginaAtual.contains("admin/versao/consulta")) {
			this.listaVersoes=versaoSistemaRN.listar();
		}
		
		if (paginaAtual.contains("admin/atualizacao/confirmar")) {
			//this.atualizacao=new Atualizacao();
			this.versaoTempEscolhida=new VersaoTemp();
			this.listaAtualizacoes=new AtualizacaoRN().listarTodasAtualizacoesAgendadas();
			this.root=criarTreeNode();
		}
		
		if (paginaAtual.contains("restrito/atualizacao")) {
			eventModel = new DefaultScheduleModel();
			this.listaAtualizacoes=new AtualizacaoRN().listarTodasAtualizacoesAgendadas();
			for(Atualizacao at: this.listaAtualizacoes){
				eventModel.addEvent(new DefaultScheduleEvent("-"+at.getCliente().getPessoa().getPesNome(), at.getAtuDataAgendadaInicio(),at.getAtuDataAgendadaFim(),at));
			}
		}
		
		if (paginaAtual.contains("restrito/cliente/atualizacao")) {
			int pessoaID = super.getParametro("id", -1);

			this.pessoa=new PessoaRN().carregar(pessoaID);

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
					this.pessoa.setCliente(this.cliente);
				}
					eventModel = new DefaultScheduleModel();
					this.listaAtualizacoes=atualizacaoRN.listarPorClienteNaoAtualizado(this.pessoa.getCliente());
					for(Atualizacao at: this.listaAtualizacoes){
						eventModel.addEvent(new DefaultScheduleEvent("-"+at.getAtuTipo(), at.getAtuDataAgendadaInicio(),at.getAtuDataAgendadaFim(),at));
					}				
			}
		}
		
		if (paginaAtual.contains("admin/versao/cadastro")) {
			this.listaReleases=new ArrayList<Release>();
			int versaoID = super.getParametro("id", -1);
			this.versaoSistema=versaoSistemaRN.carregar(versaoID);
			if(this.versaoSistema!=null){
				this.alterar=true;
				this.listaReleases= new ReleaseRN().listarPorVersao(this.versaoSistema);
			}else{
				this.versaoSistema=new VersaoSistema();
			}
		}
	}
	
	public void salvar(){
		this.versaoSistema.setVerSisDataCadastro(new Date());
		new VersaoSistemaRN().salvar(this.versaoSistema);
		
		this.release=new Release();
		this.release.setRelDataCadastro(new Date());
		this.release.setVersaoSistema(this.versaoSistema);
		this.release.setRelNome("RELEASE 0");
		new ReleaseRN().salvar(this.release);
		
		super.redirecionarParaPagina("admin/versao/consulta.jsf");
	}
	
	public void salvarRelease(){
		if(this.versaoSistema!=null){
			this.release.setRelDataCadastro(new Date());
			this.release.setVersaoSistema(this.versaoSistema);
			ReleaseRN releaseRN = new ReleaseRN();
			releaseRN.salvar(this.release);
			this.release=new Release();
			this.listaReleases=releaseRN.listarPorVersao(this.versaoSistema);
		}else{
			super.mostrarErro("Versão não carregada em memoria para armazenamento do release");
		}
	}
	
	public String buscarReleases(int id){
		String release=new String();
		if(id > 0){
			VersaoSistema ver=new VersaoSistema();
			ver.setVerSisId(id);
			List<Release> lista=new ReleaseRN().listarPorVersao(ver);
			for(Release rel: lista){
				release+=rel.getRelNome()+". ";
			}
		}
		return release;
	}
	
	@SuppressWarnings("deprecation")
	public void onDateSelect(SelectEvent selectEvent) {
		this.atualizacao=new Atualizacao();
		Date data=(Date) selectEvent.getObject();
		Calendar calendar= Calendar.getInstance();
		calendar.setTime(data);
		Date dat=new Date();
		calendar.add(Calendar.HOUR, dat.getHours());
		calendar.add(Calendar.MINUTE, dat.getMinutes());
		this.atualizacao.setAtuDataAgendadaInicio(calendar.getTime());
		this.atualizacao.setAtuDataAgendadaFim(calendar.getTime());
	}
	
	public void onEventSelect(SelectEvent selectEvent) {
		this.atualizacao=new Atualizacao();
		event = (ScheduleEvent) selectEvent.getObject();
		this.atualizacao=(Atualizacao) event.getData();
		if(this.atualizacao==null){
			this.atualizacao=new Atualizacao();
			super.mostrarAviso("Tente novamente ");
			return;
		}		
	}
	
	public void onEventMove(ScheduleEntryMoveEvent event) {
		this.atualizacao=new Atualizacao();
		AtualizacaoRN atualizacaoRN = new AtualizacaoRN();		
		this.atualizacao =((Atualizacao) event.getScheduleEvent().getData());	
		if(this.atualizacao==null){
			super.mostrarAviso("Tente novamente ");
			return;
		}
		java.text.SimpleDateFormat day = new java.text.SimpleDateFormat(
				"dd/MM/yyyy");
		java.text.SimpleDateFormat hor = new java.text.SimpleDateFormat(
				"hh:mm");
		atualizacaoRN.salvar(this.atualizacao);
		
		super.mostrarAviso("Agenda Modificada. Dia:"+day.format(this.atualizacao.getAtuDataAgendadaInicio())+" .Horario:" + hor.format(this.atualizacao.getAtuDataAgendadaInicio())+"hs até " + hor.format(this.atualizacao.getAtuDataAgendadaFim())+"hs, \n OBS: "
						+ this.atualizacao.getAtuTipo());

	}
	
	
	public void onEventResize(ScheduleEntryResizeEvent event) {
		this.atualizacao=new Atualizacao();
		AtualizacaoRN atualizacaoRN = new AtualizacaoRN();		
		this.atualizacao =((Atualizacao) event.getScheduleEvent().getData());	
		if(this.atualizacao==null){
			super.mostrarAviso("Tente novamente ");
			return;
		}
		java.text.SimpleDateFormat day = new java.text.SimpleDateFormat(
				"dd/MM/yyyy");
		java.text.SimpleDateFormat hor = new java.text.SimpleDateFormat(
				"hh:mm");
		atualizacaoRN.salvar(this.atualizacao);
		
		super.mostrarAviso("Agenda Modificada. Dia:"+day.format(this.atualizacao.getAtuDataAgendadaInicio())+" .Horario:" + hor.format(this.atualizacao.getAtuDataAgendadaInicio())+"hs até " + hor.format(this.atualizacao.getAtuDataAgendadaFim())+"hs, \n OBS: "
						+ this.atualizacao.getAtuTipo());
	
	}
	
	public void salvarAtualizacao(){
		if((this.atualizacao.getAtuTipo()==null)||(this.atualizacao.equals(""))){
			super.mostrarErro("Tipo da Atualização obrigatorio");
			return;
		}
		this.atualizacao.setCliente(this.pessoa.getCliente());
		this.atualizacao.setFuncionario(super.getFuncionarioLogado());
		AtualizacaoRN atualizacaoRN = new AtualizacaoRN();
		atualizacaoRN.salvar(this.atualizacao);
		this.atualizacao=new Atualizacao();
		
		eventModel = new DefaultScheduleModel();
		this.listaAtualizacoes=atualizacaoRN.listarPorClienteNaoAtualizado(this.pessoa.getCliente());
		for(Atualizacao at: this.listaAtualizacoes){
			eventModel.addEvent(new DefaultScheduleEvent("-"+at.getAtuTipo(), at.getAtuDataAgendadaInicio(),at.getAtuDataAgendadaFim(),at));
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
	public String getVersaoCliente() {
		if((this.atualizacao==null)){
			this.versaoCliente= "<li>"+
					"<a class='todo-actions' href='javascript:void(0)' style='font-size:12px'>"+
					"<i class='fa fa-chevron-right'></i>"+
					"  "+
					"<span class='label label-danger' style='opacity: 1;font-size:12px'> "+"Versão não encontrada</span>"+
					"</a>"+
					"</li>";		
		}else{
			Atualizacao at=new AtualizacaoRN().buscarPorCliente(this.atualizacao.getCliente());
			this.versaoCliente= "<li>"+
							"<a class='todo-actions' href='javascript:void(0)' style='font-size:12px'>"+
							"<i class='fa fa-chevron-right'></i>"+
							"  "+
							"<span class='label label-success' style='opacity: 1;font-size:12px'> "+at.getRelease().getVersaoSistema().getVerSisNome()+"-"+at.getRelease().getRelNome()+"</span>"+
							"</a>"+
							"</li>";
			super.evict(at);
		}
		return versaoCliente;
	}
	
	public String versaoEscolhida(){
		if(this.versaoTempEscolhida.getIdRelese()==0){
			return "<li>"+
					"<a class='todo-actions' href='javascript:void(0)' style='font-size:12px'>"+
					"<i class='fa fa-chevron-right'></i>"+
					"  "+
					"<span class='label label-danger' style='opacity: 1;font-size:12px'> "+"Versão não escolhida</span>"+
					"</a>"+
					"</li>";		
		}
		Release rel=new ReleaseRN().carregar(this.versaoTempEscolhida.getIdRelese());
		super.evict(rel);
		return "<li>"+
		"<a class='todo-actions' href='javascript:void(0)' style='font-size:12px'>"+
		"<i class='fa fa-chevron-right'></i>"+
		"  "+
		"<span class='label label-info' style='opacity: 1;font-size:12px'> "+rel.getVersaoSistema().getVerSisNome()+"-"+rel.getRelNome()+"</span>"+
		"</a>"+
		"</li>";
	}
	
	public void confirmarAtualizacao(){
		if(this.versaoTempEscolhida.getIdRelese()==0){
			super.mostrarErro("Escolha de uma nova versão para o cliente obrigatoria, operação não realizada");
			return;			
		}
		this.release= new ReleaseRN().carregar(this.versaoTempEscolhida.getIdRelese());
		this.atualizacao.setAtuDataAtualizada(new Date());
		this.atualizacao.setRelease(this.release);
		AtualizacaoRN atualizacaoRN = new AtualizacaoRN();
		atualizacaoRN.salvar(this.atualizacao);
		this.listaAtualizacoes=atualizacaoRN.listarTodasAtualizacoesAgendadas();
		this.atualizacao=new Atualizacao();
		super.evict(this.release);
		this.release=new Release();
	}

}
