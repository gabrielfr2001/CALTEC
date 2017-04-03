package br.com.teclogica.roskowski.mbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import br.com.teclogica.roskowski.enumeration.TiposRefeicoes;
import br.com.teclogica.roskowski.interfaces.IManterAlimentoSBean;
import br.com.teclogica.roskowski.interfaces.IManterRefeicaoSBean;
import br.com.teclogica.roskowski.interfaces.IManterUnidadeSBean;
import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.sbean.ManterAlimentoSBean;
import br.com.teclogica.roskowski.sbean.ManterRefeicaoSBean;
import br.com.teclogica.roskowski.sbean.ManterUnidadeSBean;
import br.com.teclogica.roskowski.sbean.ManterUsuarioSBean;
import br.com.teclogica.roskowski.to.TOAlimento;
import br.com.teclogica.roskowski.to.TORefeicao;
import br.com.teclogica.roskowski.to.TOUnidade;
import br.com.teclogica.roskowski.viewModel.MainMBeanViewModel;

@ManagedBean(name = MainMBean.MBEAN)
@SessionScoped
public class MainMBean extends AbstractCommonMBean implements Serializable {

	private static final long serialVersionUID = -9047022416096648915L;
	public static final String MBEAN = "mainMBean";
	public static final String BUNDLE = MAIN_BUNDLE + "mainPage";

	private Date data;

	private TOUnidade unidade;

	private TORefeicao r1;
	private TORefeicao r2;
	private TORefeicao r3;
	private TORefeicao r4;
	private TORefeicao r5;
	private TORefeicao r6;

	@EJB
	private IManterUsuarioSBean sBean = new ManterUsuarioSBean();
	@EJB
	private IManterAlimentoSBean ssBean = new ManterAlimentoSBean();
	@EJB
	private IManterUnidadeSBean sssBean = new ManterUnidadeSBean();
	@EJB
	private IManterRefeicaoSBean ssssBean = new ManterRefeicaoSBean();

	private MainMBeanViewModel lmbvw;

	public MainMBean() {
		lmbvw = new MainMBeanViewModel();
		unidade = new TOUnidade();

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		try {
			date = (Date) dateFormat.parse(dateFormat.format(date));
			r1 = lmbvw.carregarRefeicao(TiposRefeicoes.CAFE_DA_MANHA, ssssBean, date);
			if (r1 == null) {
				r1 = new TORefeicao();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		data = date;
	}

	public void updateDate() {
		// r1 = lmbvw.carregarRefeicao(TiposRefeicoes.CAFE_DA_MANHA, ssssBean,
		// data);
	}

	public void deletar() {
		lmbvw.deletar(unidade, sssBean);
		unidade = new TOUnidade();
	}

	public void cadastrarCafeDaManha() {

	}

	public void cadastrarLancheDaManha() {

	}

	public List<TOUnidade> carregarUnidades(String str) {
		if (r1 == null) {
			r1 = new TORefeicao();
		}
		r1.setData(data);
		Long l = Long.parseLong(getUsuarioSessao());
		r1.setUserid(l);
		r1.setTipo(TiposRefeicoes.CAFE_DA_MANHA);
		if (lmbvw.carregarRefeicao(TiposRefeicoes.CAFE_DA_MANHA, ssssBean, data) == null) {
			try {
				lmbvw.salvar(r1, ssssBean);
				unidade = new TOUnidade();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lmbvw.carregarUnidades(lmbvw.carregarRefeicao(TiposRefeicoes.CAFE_DA_MANHA, ssssBean, data).getId(),
				sssBean);
	}

	public void adicionarCafe() {

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			data = ((Date) df.parse(df.format(data)));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		r1 = lmbvw.carregarRefeicao(TiposRefeicoes.CAFE_DA_MANHA, ssssBean, data);

		if (r1 == null) {
			r1 = new TORefeicao();
		}
		r1.setData(data);
		unidade.setCal(
				unidade.getQuantidade() * unidade.getAlimento().getCalorias() / unidade.getAlimento().getGramas());
		r1.setTotalCal(r1.getTotalCal() + unidade.getCal());
		Long l = Long.parseLong(getUsuarioSessao());
		r1.setUserid(l);
		r1.setCorTotal(Integer
				.toString(Integer.parseInt(r1.getCorTotal()) + Integer.parseInt(unidade.getAlimento().getColor())));
		r1.setTotalUnit(r1.getTotalUnit() + 1);
		r1.setTipo(TiposRefeicoes.CAFE_DA_MANHA);
		try {
			lmbvw.salvar(r1, ssssBean);
		} catch (IOException e) {
			e.printStackTrace();
		}
		unidade.setAlimento(lmbvw.carregarComida(unidade.getAlimento().getNome(), ssBean));
		unidade.setRefeicaoId(r1.getId());
		try {
			lmbvw.salvar(unidade, sssBean);
		} catch (IOException e) {
			e.printStackTrace();
		}
		unidade = new TOUnidade();

	}

	public String redirectGraphs() {
		return "/pages/user/graphs?faces-redirect=true";
	}

	public void excluirCafe() {

	}

	public void cadastrarAlmoco() {

	}

	public void cadastrarLancheDaTarde() {

	}

	public void cadastrarJanta() {

	}

	public void cadastrarLancheDaMadrugada() {

	}

	public List<TOAlimento> complete(String str) {
		return lmbvw.complete(str, ssBean);
	}

	public StreamedContent getPerfilImage() {
		return lmbvw.getPerfilImage(getUsuarioSessao(), sBean);
	}

	public String getNomeUsuario() {
		return lmbvw.getNomeUsuario(getUsuarioSessao(), sBean);
	}

	public String getUsuarioSessao() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		return sessionMap.get("user").toString();
	}

	@Override
	public String getBundleDir() {
		return BUNDLE;
	}

	public String loadBundle(String str) {
		return getLabel(str);
	}

	public TORefeicao getR1() {
		return r1;
	}

	public void setR1(TORefeicao r1) {
		this.r1 = r1;
	}

	public TORefeicao getR2() {
		return r2;
	}

	public void setR2(TORefeicao r2) {
		this.r2 = r2;
	}

	public TORefeicao getR3() {
		return r3;
	}

	public void setR3(TORefeicao r3) {
		this.r3 = r3;
	}

	public TORefeicao getR4() {
		return r4;
	}

	public void setR4(TORefeicao r4) {
		this.r4 = r4;
	}

	public TORefeicao getR6() {
		return r6;
	}

	public void setR6(TORefeicao r6) {
		this.r6 = r6;
	}

	public TORefeicao getR5() {
		return r5;
	}

	public void setR5(TORefeicao r5) {
		this.r5 = r5;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TOUnidade getUnidade() {
		return unidade;
	}

	public void setUnidade(TOUnidade unidade) {
		this.unidade = unidade;
	}

}
