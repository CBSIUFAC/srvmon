package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

import org.omnifaces.converter.SelectItemsConverter;

import entity.Servidor;

@FacesConverter("servidorConverter")
public class ServidorConverter extends SelectItemsConverter {
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        Integer id = (value instanceof Servidor) ? ((Servidor) value).getIdServidor() : null;
        return (id != null) ? String.valueOf(id) : null;
    }
}
