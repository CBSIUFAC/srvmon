package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.ServidorReport;

public class ServidorReportDAO extends MasterDAO{
	public void inserirServidorReport(ServidorReport servidorReport){
		inserirObjeto(servidorReport);
	}
	
	public void deletarServidorReport(ServidorReport servidorReport){
		deletarObjeto(servidorReport);
	}
	
	public void atualizarServidorReport(ServidorReport servidorReport){
		atualizarObjeto(servidorReport);
	}
	
	public ServidorReport getServidorReport(int idServidorReport){
		return getObjeto(ServidorReport.class, idServidorReport);
	}
	
	public List<ServidorReport> getListaServidorReport(){
		// sort so that the last is the newest (descServidorReport is a ms time) 
		return getLista(ServidorReport.class, "from ServidorReport m ORDER BY descServidorReport");
	}
	
	public List<ServidorReport> getListaServidorReportById(int id){
		// sort so that the last is the newest (descServidorReport is a ms time)
		// ids are auto-generated, so I guess there's no harm in not being injection-safe
		return getLista(ServidorReport.class, "from ServidorReport m WHERE fkServidor = " + id + " ORDER BY descServidorReport");
	}
	
	public List<ServidorReport> buscaServidorReport(String str){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from ServidorReport m where m.descServidorReport like :mo");
		qr.setParameter("mo","%"+str+"%");
		List<ServidorReport> listaServidorReport = qr.list();
		s.getTransaction().commit();
		s.close();
		return listaServidorReport;
	}
	
}