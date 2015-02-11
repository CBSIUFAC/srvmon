package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.FetchType;

import DAO.ServidorReportDAO;

@Entity
public class Servidor implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idServidor;
	
	private String address;
//	Atributo que não é persistido no banco
	@Transient
	private String msg;
	
	@OneToMany(mappedBy="servidor",fetch=FetchType.EAGER) // EAGER not good for big data sets! Use sessions for lazy fetching.
	private List<ServidorReport> servidorReports;
	
	public int getIdServidor() {
		return idServidor;
	}
	public void setIdServidor(int idServidor) {
		this.idServidor = idServidor;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ServidorReport getLastReport() {
		ServidorReportDAO srd = new ServidorReportDAO();
		List<ServidorReport> reports;
		reports = srd.getListaServidorReportById(idServidor); // TODO: limit 1 for faster queries
		if (reports.size() > 0)
		{
			return reports.get(reports.size() - 1);
		}
		else
		{
			ServidorReport blank = new ServidorReport();
			blank.setPlayersString("Desconhecido");
			blank.setTimeString("Nunca");
			return blank;
		}
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idServidor;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servidor other = (Servidor) obj;
		if (idServidor != other.idServidor)
			return false;
		return true;
	}
	
	
	
}
