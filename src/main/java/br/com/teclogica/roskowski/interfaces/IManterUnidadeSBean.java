package br.com.teclogica.roskowski.interfaces;

import java.util.Date;
import java.util.List;

import br.com.teclogica.roskowski.to.TOUnidade;

public interface IManterUnidadeSBean {
	public void salvar(TOUnidade u);

	public List<TOUnidade> listar(String query);

	public TOUnidade carregar(String id);

	public void deletar(TOUnidade r);

	public TOUnidade carregarData(String username, Date str);

	public List<TOUnidade> carregarRefeicao(long id);
}
