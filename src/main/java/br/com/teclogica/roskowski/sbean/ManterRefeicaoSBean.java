package br.com.teclogica.roskowski.sbean;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.teclogica.roskowski.enumeration.TiposRefeicoes;
import br.com.teclogica.roskowski.interfaces.IManterRefeicaoSBean;
import br.com.teclogica.roskowski.model.Refeicao;
import br.com.teclogica.roskowski.to.TORefeicao;
import br.com.teclogica.roskowski.util.Conn;

@Stateless
public class ManterRefeicaoSBean extends Conn implements IManterRefeicaoSBean {

	public void salvar(TORefeicao u) {
		em.merge(u.toRefeicao());
	}

	public List<TORefeicao> listar(String query) {
		return null;
	}

	public TORefeicao carregar(String id) {
		return new TORefeicao().toTORefeicao(em.find(Refeicao.class, Long.parseLong(id)));
	}

	public void deletar(TORefeicao u) {
		em.remove(u.toRefeicao());
	}

	public TORefeicao carregarRefeicao(String str) {
		Query query = em.createQuery("SELECT a FROM Refeicao a WHERE a.id='" + str + "'");
		if (query.getResultList().size() > 0)
			return (new TORefeicao().toTORefeicao((Refeicao) query.getResultList().get(0)));
		else
			return null;
	}

	public TORefeicao carregarData(String id, Date date, String nome) {
		try {
			Query query = em.createQuery("SELECT a FROM Refeicao a WHERE a.userid='" + id + "' and a.data='" + date
					+ "' and a.tipo='" + TiposRefeicoes.valueOf(nome).ordinal() + "'");
			System.out.println("SELECT a FROM Refeicao a WHERE a.userid='" + id + "' and a.data='" + date
					+ "' and a.tipo='" + TiposRefeicoes.valueOf(nome).ordinal() + "'");
			if (query.getResultList().size() > 0)
				return (new TORefeicao().toTORefeicao((Refeicao) query.getResultList().get(0)));
			else
				return null;
		} catch (Exception e) {
			return null;
		}
	}

}
