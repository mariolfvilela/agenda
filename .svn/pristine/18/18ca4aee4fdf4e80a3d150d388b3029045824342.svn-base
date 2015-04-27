package br.com.renovar.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Getter;
import lombok.Setter;
import br.com.renovar.modelo.Funcionario;
import br.com.renovar.modelo.Pessoa;
import br.com.renovar.rn.FuncionarioRN;
import br.com.renovar.util.GenericBean;

@ManagedBean(name = "funcionarioBean")
@ViewScoped
public class FuncionarioBean extends GenericBean implements Serializable{

	/**
	 * 
	 */
	@Getter private static final long serialVersionUID = 8618463921451389645L;
	@Getter @Setter	private Pessoa pessoa=new Pessoa();
	@Getter @Setter	private Funcionario funcionario=new Funcionario();
	@Getter @Setter	private String confirmaSenha;
	@Getter @Setter	private String senha;
	@Getter @Setter	private String loguin;
	@Getter @Setter boolean alteracao=false;
	
	@Getter @Setter	private List<Pessoa> listaFuncionarios;
	@Getter @Setter	private List<Pessoa> listaPessoasNaoFuncionarios;
	@Getter @Setter	private List<String> permissoes;
	
	@PostConstruct
	public void init(){
		FuncionarioRN funcionarioRN = new FuncionarioRN();
		this.funcionario=new Funcionario();
		this.pessoa=new Pessoa();
		
		String paginaAtual = super.getPaginaAtual();

		if (paginaAtual.contains("admin/funcionario/consulta")) {
			this.listaFuncionarios=funcionarioRN.listarFuncionariosPessoas();
		}

		if (paginaAtual.contains("admin/funcionario/cadastro")) {
			this.permissoes=new ArrayList<String>();
			
			this.listaPessoasNaoFuncionarios=funcionarioRN.listaPessoasNaoFuncionarios();
			
			int funcionarioID = super.getParametro("id", -1);

			if (funcionarioID <= 0) {
				setAlteracao(false);
			} else {
				setAlteracao(true);
				Funcionario funcionarioCarregado = funcionarioRN.carregar(funcionarioID);
				if(funcionarioCarregado!=null){
					this.funcionario=funcionarioCarregado;
					Set<String> lista=this.funcionario.getPermissao();
					for(String per: lista)
						this.permissoes.add(per);
					this.pessoa=this.funcionario.getPessoa();
					this.loguin=this.funcionario.getFunLoguin();
					this.senha=new String();
					this.confirmaSenha=new String();
				}
			}
		}
	}
	
	public void salvar(){
		if((this.pessoa==null)||(this.pessoa.getPesId()<1)){
			this.senha=new String();
			this.confirmaSenha=new String();
			super.mostrarErro("Selecione o funcionário ");
			return ;
		}
		
		if(this.funcionario.getFunCargo()==null || this.funcionario.getFunCargo()==""){
			super.mostrarErro("Campo Cargo obrigatorio");
			return ;
		}
		
		if (!this.senha.equals(this.confirmaSenha)) {
			this.senha=new String();
			this.confirmaSenha=new String();
			super.mostrarErro("A senha não foi confirmada corretamente");
			return ;
		}

		FuncionarioRN funcionarioRN = new FuncionarioRN();

		Funcionario f=funcionarioRN.buscarPorFuncionario(loguin);
		if(f!=null){
			if(this.alteracao){
				if(f.getPesId()!=this.funcionario.getPesId()){
					super.mostrarErro("Esse login já existe no sistema. Pertence ao Sr."+f.getPessoa().getPesNome());
					return ;
				}else{
					//eliminado objeto da session
					super.evict(f);
				}
			}else{
				super.mostrarErro("Esse login já existe no sistema. Pertence ao Sr."+f.getPessoa().getPesNome());
				return ;
			}
		}

		this.funcionario.setPermissao(new HashSet<String>());
		for(String per: this.permissoes){
			this.funcionario.getPermissao().add(per);
		}
		this.funcionario.setFunLoguin(loguin);
		this.funcionario.setFunSenha(this.senha);
		this.funcionario.setPessoa(this.pessoa);
		this.funcionario.setFunAtivo(true);
		funcionarioRN.salvar(this.funcionario);
		
		super.redirecionarParaPagina("admin/funcionario/consulta.jsf");
	}
}
