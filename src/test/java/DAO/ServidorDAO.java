package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Carro;
import entity.Servidor;

public class ServidorDAO extends MasterDAO{
	//inserir
	public void inserirServidor(Servidor servidor){
		inserirObjeto(servidor);
	}
	//atualizar
	public void atualizarServidor(Servidor servidor){
		Session s = getSession();
		s.beginTransaction();
		s.update(servidor);
		s.getTransaction().commit();
		s.close();
	}
	//deletar
	public void deletarServidor(Servidor servidor){
		deletarObjeto(servidor);
	}
	//listar todos os carros
	public List<Servidor> listarServidor(){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Servidor s");
		List<Servidor> listaServidor = qr.list();
		s.close();
		return listaServidor;
	}
	
	public Servidor getServidor(int idServidor){
		Session s = getSession();
		s.beginTransaction();
		Servidor m = (Servidor) s.get(Servidor.class, idServidor);
		s.getTransaction().commit();
		s.close();
		return m;
	}
	
	public List<Servidor> buscarPorServidor(String address){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Servidor s where s.address like :sa");
		qr.setParameter("sa","%"+address+"%");
		List<Servidor> listaServidor = qr.list();
		s.close();
		return listaServidor;
	}
	

}
