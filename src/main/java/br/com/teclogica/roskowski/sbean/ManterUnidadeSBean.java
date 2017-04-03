package br.com.teclogica.roskowski.sbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.teclogica.roskowski.interfaces.IManterUnidadeSBean;
import br.com.teclogica.roskowski.model.Unidade;
import br.com.teclogica.roskowski.to.TOUnidade;
import br.com.teclogica.roskowski.util.Conn;

@Stateless
public class ManterUnidadeSBean extends Conn implements IManterUnidadeSBean {

	public void salvar(TOUnidade u) {
		em.merge(u.toUnidade());
	}

	public List<TOUnidade> listar(String query) {
		return null;
	}

	public TOUnidade carregar(String id) {
		return new TOUnidade().toTOUnidade(em.find(Unidade.class, Long.parseLong(id)));
	}

	public void deletar(TOUnidade u) {
		if (u.getId() != 0) {
			Unidade unidade = em.find(Unidade.class, u.getId());
			if (unidade != null) {
				em.remove(unidade);
			}
		}
	}

	public TOUnidade carregarUsuario(String str) {
		Query query = em.createQuery("SELECT a FROM " + Unidade.class.getName() + " a WHERE a.usuario='" + str + "'");
		if (query.getResultList().size() > 0)
			return (new TOUnidade().toTOUnidade((Unidade) query.getResultList().get(0)));
		else
			return null;
	}

	public TOUnidade carregarEmail(String email) {
		Query query = em.createQuery("SELECT a FROM Usuario a WHERE a.email='" + email + "'");
		if (query.getResultList().size() > 0)
			return new TOUnidade().toTOUnidade((Unidade) query.getResultList().get(0));
		else
			return null;
	}

	@Override
	public TOUnidade carregarData(String username, Date str) {
		return null;
	}

	@Override
	public List<TOUnidade> carregarRefeicao(long id) {
		Query query = em.createQuery("SELECT a FROM Unidade a WHERE a.refeicaoid='" + id + "'");

		@SuppressWarnings("unchecked")
		List<Object> obj = query.getResultList();
		List<TOUnidade> un = new ArrayList<TOUnidade>();

		for (Object o : obj) {
			un.add(new TOUnidade().toTOUnidade((Unidade) o));
		}

		if (un.size() > 0)
			return un;
		else
			return null;
	}

}
