package br.com.teclogica.roskowski.viewModel;

import java.util.List;

import br.com.teclogica.roskowski.interfaces.IManterAlimentoSBean;
import br.com.teclogica.roskowski.to.TOAlimento;

public class AlimentoMBeanViewModel {

	public List<TOAlimento> listar(IManterAlimentoSBean sBean) {
		return sBean.listar("SELECT alimento FROM Alimento alimento");
	}

	public List<TOAlimento> pesquisar(String statement, IManterAlimentoSBean sBean) {
		List<TOAlimento> lista = sBean.listar("SELECT a FROM Alimento a WHERE a.nome LIKE '%" + statement + "%'");
		if (lista == null) {
			lista = sBean.listar("SELECT a FROM Alimento a WHERE a.calorias = " + statement);
		}
		return lista;
	}

	public void adicionar(TOAlimento alimento, IManterAlimentoSBean ssBean) {
		ssBean.atualizar(alimento);
	}

}
