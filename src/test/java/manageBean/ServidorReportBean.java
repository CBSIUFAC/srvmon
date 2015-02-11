package manageBean;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entity.Servidor;
import entity.ServidorReport;
import DAO.ServidorReportDAO;

@ManagedBean(name="servidorReportBean")
@SessionScoped
public class ServidorReportBean {
	// TODO: option to remove individual reports / remove all reports when removing a server / limit number of reports stored
	private ServidorReportDAO servidorReportDAO = new ServidorReportDAO();
	private ServidorReport servidorReport;
	private Servidor servidor;
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public String inserirServidorReport(){
		//System.out.println("ServidorReport: "+getServidorReport().getServidor());
		long timeLong = (new Date()).getTime();
		String timeString = Long.toString(timeLong);
		servidorReport.setDescServidorReport(timeString);

		int result;
		boolean responseError = false;
		DatagramSocket clientSocket;
		try {
			clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName(servidorReport.getServidor().getAddress());
			//InetAddress IPAddress = InetAddress.getByName("localhost");
			System.out.println(servidorReport.getServidor().getAddress());
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];

			// should be big-endian (network byte order) but the engine doesn't do the conversion yet (FIXME)
			sendData[0] = (byte) 0x5A; // id
			sendData[1] = (byte) 0xA5; // id
			sendData[2] = (byte) 0x04; // len
			sendData[3] = (byte) 0x00; // len
			sendData[4] = (byte) 0x00; // ack
			sendData[5] = (byte) 0x00; // ack
			sendData[6] = (byte) 0x00; // reliableid
			sendData[7] = (byte) 0x00; // reliableid
			// data from now on
			sendData[8] = (byte) 0x02; // protocol
			sendData[9] = (byte) 0x00; // procotol
			sendData[10] = (byte) 0x88; // magic
			sendData[11] = (byte) 0x19; // magic

			DatagramPacket sendPacket = new DatagramPacket(sendData, 12, IPAddress, 26260);
			clientSocket.send(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.setSoTimeout(10000);
			clientSocket.receive(receivePacket);
			receiveData = receivePacket.getData();
			System.out.println(bytesToHex(sendData));
			System.out.println(bytesToHex(receiveData));

			// see if header is valid and we only got 4 bytes (32-bit int) of data
			if (receiveData[0] != (byte) 0x5A)
				responseError = true;
			if (receiveData[1] != (byte) 0xA5)
				responseError = true;
			if (receiveData[2] != (byte) 0x04)
				responseError = true;
			if (receiveData[3] != (byte) 0x80) // reliable bit
				responseError = true;
			if (receiveData[4] != (byte) 0x00)
				responseError = true;
			if (receiveData[5] != (byte) 0x00)
				responseError = true;
			if (receiveData[6] != (byte) 0x01) // ack id
				responseError = true;
			if (receiveData[7] != (byte) 0x00)
				responseError = true;
			
			if (!responseError)
			{
				result = 0;
				for (int i = 0; i < 4; i++)
					result += (receiveData[8 + i] & 0xFF) << (8 * i);
			}
			else
				result = -4;
			
			clientSocket.close();
		} catch (SocketException e1) {
			result = -1;
			System.out.println(e1.getMessage());
		} catch (UnknownHostException e2) {
			result = -2;
			System.out.println(e2.getMessage());
		} catch (IOException e3) {
			result = -3;
			System.out.println(e3.getMessage());
		}

		servidorReport.setNumPlayers(result);

		servidorReportDAO.inserirServidorReport(servidorReport);
		return "listaservidorReports";
	}

	public ServidorReportDAO getServidorReportDAO() {
		return servidorReportDAO;
	}

	public void setServidorReportDAO(ServidorReportDAO servidorReportDAO) {
		this.servidorReportDAO = servidorReportDAO;
	}

	public ServidorReport getServidorReport() {
		if(servidorReport == null){
			setServidor(new Servidor());
			servidorReport = new ServidorReport();
		}
		return servidorReport;
	}

	public void setServidorReport(ServidorReport servidorReport) {
		this.servidorReport = servidorReport;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
	
	private List<ServidorReport> listaServidorReport = null;
	
	private List<ServidorReport> filtroListaReport = null;
	
	public List<ServidorReport> getFiltroListaReport() {
		return filtroListaReport;
	}

	public void setFiltroListaReport(List<ServidorReport> filtroListaReport) {
		this.filtroListaReport = filtroListaReport;
	}
	
	public void setListaServidorReport(List<ServidorReport> listaServidorReport) {
		this.listaServidorReport = listaServidorReport;
	}

	public List<ServidorReport> getListaServidorReports(){
		if(listaServidorReport == null)
			listaServidorReport = servidorReportDAO.getListaServidorReport();
		return listaServidorReport;
	}
}
