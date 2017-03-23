package br.com.teclogica.roskowski.sbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.model.Usuario;
import br.com.teclogica.roskowski.to.TOUsuario;
import br.com.teclogica.roskowski.util.Conn;

@Stateless
public class ManterUsuarioSBean extends Conn implements IManterUsuarioSBean {

	public void salvar(TOUsuario u) {
		em.merge(u.toUsuario());
	}

	public List<TOUsuario> listar(String query) {
		return null;
	}

	public TOUsuario carregar(String id) {
		return new TOUsuario().toTOUsuario(em.find(Usuario.class, Long.parseLong(id)));
	}

	public void deletar(TOUsuario u) {
		em.remove(u.toUsuario());
	}

	public TOUsuario carregarUsuario(String str) {
		Query query = em.createQuery("SELECT a FROM Usuario a WHERE a.usuario='" + str + "'");
		if (query.getResultList().size() > 0)
			return (new TOUsuario().toTOUsuario((Usuario) query.getResultList().get(0)));
		else
			return null;
	}

	public TOUsuario carregarEmail(String email) {
		Query query = em.createQuery("SELECT a FROM Usuario a WHERE a.email='" + email + "'");
		if (query.getResultList().size() > 0)
			return (new TOUsuario().toTOUsuario((Usuario) query.getResultList().get(0)));
		else
			return null;
	}

}
