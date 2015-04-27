package br.com.renovar.util;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.*;

import br.com.renovar.modelo.Funcionario;
import br.com.renovar.rn.FuncionarioRN;



@ManagedBean(name="contextoBean")
@SessionScoped
public class ContextoBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1124570040777405053L;
	private Funcionario funcionarioLogado = null;

	public Funcionario getFuncionarioLogado() {
		if (this.funcionarioLogado == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext external = context.getExternalContext();
			String login = external.getRemoteUser();
			if (this.funcionarioLogado == null|| !login.equals(this.funcionarioLogado.getFunLoguin())) {
				if (login != null) {
					FuncionarioRN funcionarioRN = new FuncionarioRN();
					this.funcionarioLogado = funcionarioRN.buscarPorFuncionario(login);
					//this.contaAtiva = null;
				}
			}
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
