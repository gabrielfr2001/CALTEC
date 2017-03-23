package br.com.teclogica.roskowski.sbean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.teclogica.roskowski.interfaces.IManterAlimentoSBean;
import br.com.teclogica.roskowski.model.Alimento;
import br.com.teclogica.roskowski.to.TOAlimento;
import br.com.teclogica.roskowski.util.Conn;

@Stateless
public class ManterAlimentoSBean extends Conn implements IManterAlimentoSBean {

	public void salvar(TOAlimento u) {
		em.merge(u.toAlimento());
	}

	public List<TOAlimento> listar(String query) {
		return null;
	}

	public TOAlimento carregar(String id) {
		return new TOAlimento().toTOAlimento(em.find(Alimento.class, Long.parseLong(id)));
	}

	public void deletar(TOAlimento u) {
		em.remove(u.toAlimento());
	}

	public TOAlimento carregarAlimento(String str) {
		Query query = em.createQuery("SELECT a FROM Alimento a WHERE a.usuario='" + str + "'");
		if (query.getResultList().size() > 0)
			return (new TOAlimento().toTOAlimento((Alimento) query.getResultList().get(0)));
		else
			return null;
	}

	@Override
	public List<TOAlimento> listarPattern(String pattern) {
		Query query = em.createQuery("SELECT a FROM Alimento a WHERE lower(a.nome) LIKE :pParam");
		query.setParameter("pParam", "%" + pattern.toLowerCase() + "%");
		if (query.getResultList().size() > 0)
			return new TOAlimento().toTOAlimento((Alimento) query.getResultList().get(0));
		else
			return null;
	}
}
