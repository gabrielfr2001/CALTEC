package br.com.teclogica.roskowski.mbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.sbean.ManterUsuarioSBean;
import br.com.teclogica.roskowski.to.TOUsuario;
import br.com.teclogica.roskowski.viewModel.LoginMBeanViewModel;

@ManagedBean(name = LoginMBean.MBEAN)
@SessionScoped
public class LoginMBean extends AbstractCommonMBean implements Serializable {

	private static final long serialVersionUID = -3481372012872928594L;
	public static final String MBEAN = "loginMBean";
	public static final String BUNDLE = MAIN_BUNDLE + "loginPage";

	private TOUsuario usuario;
	private UploadedFile file;

	@EJB
	private IManterUsuarioSBean sBean = new ManterUsuarioSBean();
	private LoginMBeanViewModel lmbvw;

	public LoginMBean() {
		setUsuario(new TOUsuario());
		lmbvw = new LoginMBeanViewModel();
	}

	public String cadastrar() {
		try {
			return lmbvw.salvar(usuario, file, sBean);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String redirectAdmin() {
		return "/pages/admin/alimento?faces-redirect=true";
	}

	public StreamedContent getPerfilImage() {
		return lmbvw.getPerfilImage(getUsuarioSessao(), sBean);
	}

	public String getNomeUsuario() {
		return lmbvw.getNomeUsuario(getUsuarioSessao(), sBean);
	}

	public String logar() {
		return lmbvw.logar(usuario, sBean);
	}

	public String getUsuarioSessao() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		return sessionMap.get("user").toString();
	}

	public boolean getUsuarioTipo() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		return session.getAttribute("user_tipo").toString().equals("user");
	}

	@Override
	public String getBundleDir() {
		return BUNDLE;
	}

	public String loadBundle(String str) {
		return getLabel(str);
	}

	public TOUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TOUsuario usuario) {
		this.usuario = usuario;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

}
