package br.com.renovar.rn;

import java.util.List;

import br.com.renovar.dao.BairroDAO;
import br.com.renovar.modelo.Bairro;
import br.com.renovar.util.DAOFactory;
import br.com.renovar.util.UtilException;

public class BairroRN {
	private BairroDAO bairroDAO;

	public BairroRN() {
		this.bairroDAO = DAOFactory.criarBairroDAO();
	}

	public Bairro carregar(Integer baiId) {
		return this.bairroDAO.carregar(baiId);

	}

	public Bairro buscarPorBairro(String bairro) {
		return this.bairroDAO.buscarPorBairro(bairro);
	}

	public void salvar(Bairro bairro) {
		Integer codigo = bairro.getBaiId();
		if (codigo == null || codigo == 0) {
			this.bairroDAO.salvar(bairro);
		} else {
			this.bairroDAO.atualizar(bairro);
		}

	}

	public boolean excluir(Bairro bairro) {
		if(this.bairroDAO.dependecias(bairro)){
		this.bairroDAO.excluir(bairro);
		return true;
	}else{
		try {
			throw new UtilException("Erro ao excluir. Esse bairro tem ligações com outros endereços e não pode ser deletado.");
		} catch (UtilException e) {
			/*FacesContext facesContext = FacesContext.getCurrentInstance();  
			facesContext.addMessage("ERRO",   
			        new FacesMessage(FacesMessage.SEVERITY_ERROR,   
			                               "Bairro não excluido",   
			                               "Bairro não excluido"));*/
			return false;
		}
	}
	}

	public List<Bairro> listar() {
		return this.bairroDAO.listar();
	}
}
