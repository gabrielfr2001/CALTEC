package br.com.teclogica.roskowski.sbean;

import java.util.ArrayList;
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

	public void atualizar(TOAlimento a) {
		Alimento e = a.toAlimento();
		em.find(Alimento.class, e.getId());
		e.setCalorias(a.getCalorias());
		e.setColor(a.getColor());
		e.setGramas(a.getGramas());
		e.setNome(a.getNome());
		em.merge(a);
	}

	public List<TOAlimento> listar(String query) {
		Query queryy = em.createQuery(query);

		@SuppressWarnings("unchecked")
		List<Object> obj = queryy.getResultList();
		List<TOAlimento> un = new ArrayList<TOAlimento>();

		for (Object o : obj) {
			un.add(new TOAlimento().toTOAlimento((Alimento) o));
		}

		if (un.size() > 0)
			return un;
		else
			return null;
	}

	public TOAlimento carregar(String id) {
		return new TOAlimento().toTOAlimento(em.find(Alimento.class, Long.parseLong(id)));
	}

	public void deletar(TOAlimento u) {
		em.remove(u.toAlimento());
	}

	public TOAlimento carregarAlimento(String str) {
		Query query = em.createQuery("SELECT a FROM Alimento a WHERE a.nome='" + str + "'");
		if (query.getResultList().size() > 0)
			return (new TOAlimento().toTOAlimento((Alimento) query.getResultList().get(0)));
		else
			return null;
	}

	@Override
	public List<TOAlimento> listarPattern(String pattern) {
		Query query = em.createQuery("SELECT a FROM Alimento a WHERE lower(a.nome) LIKE :pParam");
		query.setParameter("pParam", "%" + pattern.toLowerCase() + "%");

		@SuppressWarnings("unchecked")
		List<Object> list = query.getResultList();
		List<TOAlimento> list2 = new ArrayList<>();

		for (Object o : list) {
			list2.add(new TOAlimento().toTOAlimento((Alimento) o));
		}

		if (list2.size() > 0)
			return list2;
		else
			return null;
	}
}
