package br.com.teclogica.roskowski.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.teclogica.roskowski.to.TOAlimento;

@Local
public interface IManterAlimentoSBean {
	public void salvar(TOAlimento a);

	public List<TOAlimento> listar(String query);

	public List<TOAlimento> listarPattern(String pattern);

	public TOAlimento carregar(String id);

	public void deletar(TOAlimento a);

	public TOAlimento carregarAlimento(String str);

	public void atualizar(TOAlimento alimento);

	public TOAlimento carregarNome(String nome);
}
