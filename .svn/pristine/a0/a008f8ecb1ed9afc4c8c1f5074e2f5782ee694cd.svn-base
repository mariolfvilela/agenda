package br.com.renovar.dao;

import java.util.List;



import br.com.renovar.modelo.EMail;
import br.com.renovar.modelo.Pessoa;

public interface EMailDAO {
	public void salvar(EMail eMail);

	public void atualizar(EMail eMail);

	public void excluir(EMail eMail);

	public EMail carregar(Integer eMail);

	public EMail buscarPorEMail(String eMail);

	public List<EMail> listar();
	
	public List<EMail> carregarEmailsPorPessoa(Pessoa pessoa);
	
	public boolean dependecias(EMail eMail);
}
