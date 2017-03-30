package br.com.teclogica.roskowski.interfaces;

import java.util.List;

import br.com.teclogica.roskowski.to.TOAlimento;

public interface IManterAlimentoSBean {
	public void salvar(TOAlimento a);

	public List<TOAlimento> listar(String query);

	public List<TOAlimento> listarPattern(String pattern);

	public TOAlimento carregar(String id);

	public void deletar(TOAlimento a);

	public TOAlimento carregarAlimento(String str);
}
