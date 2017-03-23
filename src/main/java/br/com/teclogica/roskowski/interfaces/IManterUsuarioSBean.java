package br.com.teclogica.roskowski.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.teclogica.roskowski.to.TOUsuario;

@Local
public interface IManterUsuarioSBean {
	public void salvar(TOUsuario u);

	public List<TOUsuario> listar(String query);

	public TOUsuario carregar(String id);

	public void deletar(TOUsuario u);

	public TOUsuario carregarUsuario(String str);

	public TOUsuario carregarEmail(String usuario);

}
