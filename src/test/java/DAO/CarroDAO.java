package DAO;

import java.io.Serializable;
import java.util.List;





import org.hibernate.Query;
import org.hibernate.Session;

import entity.Carro;
import util.HibernateUtil;

public class CarroDAO extends MasterDAO{
	//inserir
	public void inserirCarro(Carro carro){
		inserirObjeto(carro);
	}
	//atualizar
	public void atualizarCarro(Carro carro){
		Session s = getSession();
		s.beginTransaction();
		s.update(carro);
		s.getTransaction().commit();
		s.close();
	}
	//deletar
	public void deletarCarro(Carro carro){
		deletarObjeto(carro);
	}
	//listar todos os carros
	public List<Carro> listarCarro(){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Carro c");
		List<Carro> listaCarro = qr.list();
		s.close();
		return listaCarro;
	}
	
	public Carro getCarro(int idCarro){
		Session s = getSession();
		s.beginTransaction();
		Carro c = (Carro) s.get(Carro.class, idCarro);
		s.getTransaction().commit();
		s.close();
		return c;
	}
	
	public List<Carro> buscarPorPlaca(String placa){
		Session s = getSession();
		s.beginTransaction();
		Query qr = s.createQuery("from Carro c where c.placa like :pa");
		qr.setParameter("pa","%"+placa+"%");
		List<Carro> listaCarros = qr.list();
		s.close();
		return listaCarros;
	}
	

	
}



