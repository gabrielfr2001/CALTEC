package br.com.teclogica.roskowski.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.teclogica.roskowski.to.TORefeicao;

@Local
public interface IManterRefeicaoSBean {
	public void salvar(TORefeicao u);

	public List<TORefeicao> listar(String query);

	public TORefeicao carregar(String id);

	public void deletar(TORefeicao r);

	public TORefeicao carregarData(String username, Date str);

}
