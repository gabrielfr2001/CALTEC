package br.com.teclogica.roskowski.viewModel;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.teclogica.roskowski.enumeration.TiposRefeicoes;
import br.com.teclogica.roskowski.interfaces.IManterAlimentoSBean;
import br.com.teclogica.roskowski.interfaces.IManterRefeicaoSBean;
import br.com.teclogica.roskowski.interfaces.IManterUnidadeSBean;
import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.to.TOAlimento;
import br.com.teclogica.roskowski.to.TORefeicao;
import br.com.teclogica.roskowski.to.TOUnidade;
import br.com.teclogica.roskowski.util.Conversor;
import br.com.teclogica.roskowski.util.Filer;

public class MainMBeanViewModel {

	public List<TOAlimento> loadFood(String str, IManterAlimentoSBean imusb) {
		return imusb.listarPattern(str);
	}

	public String salvar(TOAlimento u, IManterAlimentoSBean imusb) throws IOException {

		if (imusb.carregar(u.getNome()) == null) {
			imusb.salvar(u);
			return "/pages/user/main";
		} else {
			return "/pages/repeated";
		}

	}

	public String salvar(TOUnidade u, IManterUnidadeSBean imusb) throws IOException {
		imusb.salvar(u);
		return null;

	}

	public String salvar(TORefeicao u, IManterRefeicaoSBean imusb) throws IOException {
		imusb.salvar(u);
		return null;

	}

	public StreamedContent getPerfilImage(String id, IManterUsuarioSBean imusb) {
		return new DefaultStreamedContent(Conversor.toInputStream(Filer.loadImage(imusb.carregar(id).getImageLink())),
				"image/png");
	}

	public String getNomeUsuario(String id, IManterUsuarioSBean imusb) {
		return imusb.carregar(id).getNome();
	}

	public List<TOAlimento> complete(String str, IManterAlimentoSBean ssBean) {
		return loadFood(str, ssBean);
	}

	public TOAlimento carregarComida(String nome, IManterAlimentoSBean ssBean) {
		return ssBean.carregarAlimento(nome);
	}

	public TORefeicao carregarRefeicao(TiposRefeicoes cafeDaManha, IManterRefeicaoSBean ssssBean, Date data) {
		return ssssBean.carregarData(getUsuarioSessao(), data, TiposRefeicoes.CAFE_DA_MANHA.getNome());
	}

	public void deletar(TORefeicao r1, IManterRefeicaoSBean sssBean) {
		sssBean.deletar(r1);
	}

	public String getUsuarioSessao() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		return sessionMap.get("user").toString();
	}

	public List<TOUnidade> carregarUnidades(long id, IManterUnidadeSBean sssBean) {
		return sssBean.carregarRefeicao(id);
	}

}
