package entity;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ServidorReport implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idServidorReport;
	
	private String descServidorReport;
	private int numPlayers;
	private String timeString;
	private String playersString;
	
	@OneToMany(mappedBy="servidorReport")
	private List<Carro> carros;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="idServidor",name="fkServidor")
	private Servidor servidor;
	
	
	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public int getIdServidorReport() {
		return idServidorReport;
	}

	public void setIdServidorReport(int idServidorReport) {
		this.idServidorReport = idServidorReport;
	}

	public String getDescServidorReport() {
		return descServidorReport;
	}

	public void setDescServidorReport(String descServidorReport) {
		this.descServidorReport = descServidorReport;
		Date now = new Date(Long.parseLong(descServidorReport));
		this.timeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ").format(now);
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
		if (numPlayers < 0)
			this.playersString = "Offline: " + numPlayers;
		else
			this.playersString = "Online: " + numPlayers + " jogadores";
	}
	
	public String getTimeString() {
		return timeString;
	}
	
	public void setTimeString(String str) {
		this.timeString = str;
	}
	
	public String getPlayersString() {
		return playersString;
	}
	
	public void setPlayersString(String str) {
		this.playersString = str;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idServidorReport == null) ? 0 : idServidorReport.hashCode());
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
		ServidorReport other = (ServidorReport) obj;
		if (idServidorReport == null) {
			if (other.idServidorReport != null)
				return false;
		} else if (!idServidorReport.equals(other.idServidorReport))
			return false;
		return true;
	}

	
	
}
