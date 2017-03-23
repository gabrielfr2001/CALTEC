package br.com.teclogica.roskowski.mbean;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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
		if (this.bundle == null) {
			this.bundle = ResourceBundle.getBundle(this.getBundleDir(),
					FacesContext.getCurrentInstance().getViewRoot().getLocale());
		}
	}

	public abstract String getBundleDir();
}
