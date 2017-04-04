package br.com.teclogica.roskowski.mbean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.teclogica.roskowski.interfaces.IManterAlimentoSBean;
import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.sbean.ManterAlimentoSBean;
import br.com.teclogica.roskowski.sbean.ManterUsuarioSBean;
import br.com.teclogica.roskowski.to.TOAlimento;
import br.com.teclogica.roskowski.viewModel.AlimentoMBeanViewModel;

@ManagedBean(name = AlimentoMBean.MBEAN)
@SessionScoped
public class AlimentoMBean extends AbstractCommonMBean implements Serializable {

	private static final long serialVersionUID = -907022416096648915L;
	public static final String MBEAN = "alimentoMBean";
	public static final String BUNDLE = MAIN_BUNDLE + "alimentoPage";

	@EJB
	private IManterUsuarioSBean sBean = new ManterUsuarioSBean();
	@EJB
	private IManterAlimentoSBean ssBean = new ManterAlimentoSBean();
	private AlimentoMBeanViewModel lmbvw;

	private TOAlimento alimento;
	private String statement;
	private List<TOAlimento> lista;

	public AlimentoMBean() {
		lmbvw = new AlimentoMBeanViewModel();
		alimento = new TOAlimento();
	}

	public void excluir() {

	}

	public void adicionar() {
		lmbvw.adicionar(alimento, ssBean);
		alimento = new TOAlimento();
	}

	public void pesquisar() {
		setLista(lmbvw.pesquisar(getStatement(), ssBean));
	}

	public String redirectGraphs() {
		return "/pages/user/graphs?faces-redirect=true";
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

	public TOAlimento getAlimento() {
		return alimento;
	}

	public void setAlimento(TOAlimento alimento) {
		this.alimento = alimento;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public List<TOAlimento> getLista() {
		return lista;
	}

	public void setLista(List<TOAlimento> lista) {
		this.lista = lista;
	}

}
