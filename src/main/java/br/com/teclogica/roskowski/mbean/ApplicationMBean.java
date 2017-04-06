package br.com.teclogica.roskowski.mbean;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.teclogica.roskowski.interfaces.IManterUsuarioSBean;
import br.com.teclogica.roskowski.sbean.ManterUsuarioSBean;
import br.com.teclogica.roskowski.to.TOUsuario;

@ManagedBean

@SessionScoped
public class ApplicationMBean implements Serializable {
	private static final long serialVersionUID = 1928874812743376585L;

	private String lingua = "pt";
	private Locale locale;
	@EJB
	IManterUsuarioSBean imusb = new ManterUsuarioSBean();

	public void alterarIdioma() {
		locale = new Locale(lingua);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		session.setAttribute("locale", lingua);
		TOUsuario user = imusb.carregar(getUsuarioSessao());
		user.setLocale(lingua);
		
		imusb.deletar(user);
		imusb.salvar(user);
		
		session.setAttribute("user", user.getId());
		session.setAttribute("user_tipo", user.getTipo());

	}

	public String getLingua() {
		return lingua;
	}

	public void setLingua(String lingua) {
		this.lingua = lingua;
	}

	public Locale getLocale() {
		return locale != null ? locale : new Locale(lingua);
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getUsuarioSessao() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		return sessionMap.get("user").toString();
	}
}
