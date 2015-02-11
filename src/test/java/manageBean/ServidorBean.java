package manageBean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import org.primefaces.event.RowEditEvent;

import DAO.ServidorDAO;
import entity.Servidor;
@ManagedBean(name="servidorBean")
@SessionScoped
public class ServidorBean {
	private Servidor servidor;
	private ServidorDAO servidorDAO = new ServidorDAO();
	
	public void prepararServidor(){
		servidor = new Servidor();
	}
	
	public String inserirServidor(){
		servidorDAO.inserirServidor(servidor);
		listaServidor = null;
		return "listaservidors";
	}
	
	public Servidor getServidor() {
		if(servidor == null)
			servidor = new Servidor();
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	
	private List<Servidor> listaServidor = null;
	
	private List<Servidor> filtroLista = null;
	
	public List<Servidor> getFiltroLista() {
		return filtroLista;
	}

	public void setFiltroLista(List<Servidor> filtroLista) {
		this.filtroLista = filtroLista;
	}
	
	public void setListaServidor(List<Servidor> listaServidor) {
		this.listaServidor = listaServidor;
	}

	public List<Servidor> getListaServidores(){
		if(listaServidor == null)
			listaServidor = servidorDAO.listarServidor();
		return listaServidor;
	}
	
	public String deletarServidor(){
		servidorDAO.deletarServidor(servidor);
		listaServidor = null;
		return "listaservidors";
	}
	
	public void atualizarServidor(RowEditEvent evento){
		servidor = (Servidor)evento.getObject();
		servidorDAO.atualizarServidor(servidor);
	}
	
	public void cancelarServidor(RowEditEvent evento){
		FacesMessage msg = new FacesMessage("Edição Cancelada");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}


}



