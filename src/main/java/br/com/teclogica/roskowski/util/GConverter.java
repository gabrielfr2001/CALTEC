package br.com.teclogica.roskowski.util;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.teclogica.roskowski.interfaces.BaseInterface;

@FacesConverter(value = "gConverter")
public class GConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = -7763208684605919703L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !"".equals(value)) {

			BaseInterface entity = (BaseInterface) value;
			this.addAttribute(component, entity);

			String codigo = entity.getKey();
			return String.valueOf(codigo);
		}

		return (String) value;
	}

	protected void addAttribute(UIComponent component, BaseInterface o) {
		String key = o.getKey();
		if (key != null) {
			this.getAttributesFrom(component).put(key, o);
		}
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}