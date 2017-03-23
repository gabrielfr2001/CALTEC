package br.com.teclogica.roskowski.mbean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.model.StreamedContent;

import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.model.Refeicao;
import br.com.teclogica.roskowski.model.Unidade;
import br.com.teclogica.roskowski.sbean.ManterUsuarioSBean;
import br.com.teclogica.roskowski.viewModel.MainMBeanViewModel;

@ManagedBean(name = MainMBean.MBEAN)
@SessionScoped
public class MainMBean extends AbstractCommonMBean implements Serializable {

	private static final long serialVersionUID = -9047022416096648915L;
	public static final String MBEAN = "mainMBean";
	public static final String BUNDLE = MAIN_BUNDLE + "mainPage";

	private Date data;
	
	private Unidade unidade;

	private Refeicao r1;
	private Refeicao r2;
	private Refeicao r3;
	private Refeicao r4;
	private Refeicao r5;
	private Refeicao r6;

	@EJB
	private IManterUsuarioSBean sBean = new ManterUsuarioSBean();
	private MainMBeanViewModel lmbvw;

	public MainMBean() {
		lmbvw = new MainMBeanViewModel();
	}

	public void cadastrarCafeDaManha() {
		
	}

	public void cadastrarLancheDaManha() {

	}

	public void cadastrarAlmoco() {

	}

	public void cadastrarLancheDaTarde() {

	}

	public void cadastrarJanta() {

	}

	public void cadastrarLancheDaMadrugada() {

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

	public Refeicao getR1() {
		return r1;
	}

	public void setR1(Refeicao r1) {
		this.r1 = r1;
	}

	public Refeicao getR2() {
		return r2;
	}

	public void setR2(Refeicao r2) {
		this.r2 = r2;
	}

	public Refeicao getR3() {
		return r3;
	}

	public void setR3(Refeicao r3) {
		this.r3 = r3;
	}

	public Refeicao getR4() {
		return r4;
	}

	public void setR4(Refeicao r4) {
		this.r4 = r4;
	}

	public Refeicao getR6() {
		return r6;
	}

	public void setR6(Refeicao r6) {
		this.r6 = r6;
	}

	public Refeicao getR5() {
		return r5;
	}

	public void setR5(Refeicao r5) {
		this.r5 = r5;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

}
