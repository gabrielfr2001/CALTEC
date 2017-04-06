package br.com.teclogica.roskowski.mbean;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public abstract class AbstractCommonMBean {

	protected static final String MAIN_BUNDLE = "br/com/teclogica/roskowski/locale/";
	private ResourceBundle bundle;

	public AbstractCommonMBean() {
	}

	public String getLabel(String labelName) {
		this.loadBundle();
		return this.bundle.getString(labelName);
	}

	public void addMessage(String message, FacesMessage.Severity type) {
		FacesMessage facesMessage = new FacesMessage(message);
		facesMessage.setSeverity(type);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

	private void loadBundle() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		if (session.getAttribute("locale") != null) {
			this.bundle = ResourceBundle.getBundle(this.getBundleDir(),
					new Locale(session.getAttribute("locale").toString()));
		} else {
			this.bundle = ResourceBundle.getBundle(this.getBundleDir(),
					FacesContext.getCurrentInstance().getViewRoot().getLocale());
		}

	}

	public void abc() {

	}

	public abstract String getBundleDir();
}
