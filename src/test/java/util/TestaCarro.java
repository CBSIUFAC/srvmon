package util;

import java.util.List;

import org.hibernate.Session;

import DAO.CarroDAO;
import entity.Carro;

public class TestaCarro {

	public static void main(String[] args) {
//		criação da sessão
		/*teste antigo
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		Carro c = new Carro();
		c.setAno(2016);
		c.setCombustivel("TESTE2");
		c.setPlaca("MBB-8888");
		s.save(c);
		s.getTransaction().commit();
		s.close();
		*/
		//teste novo
		Carro c = new Carro();
//		c.setAno(2016);
//		c.setCombustivel("TESTE2");
//		c.setPlaca("NNN-0000");
		//Inserindo carro
		CarroDAO carroDAO = new CarroDAO();
//		carroDAO.inserirCarro(c);
	    //Listando todos os carros
		List<Carro> carros = carroDAO.listarCarro();
		for (Carro carro : carros) {
			System.out.println(carro);
		}
		//Deletando um carro
//		c.setIdCarro(3);
//		carroDAO.deletarCarro(c);
		//Listando novamente
		carros = carroDAO.listarCarro();
		for (Carro carro : carros) {
			System.out.println(carro);
		}
		//atualizar um carro
		c.setIdCarro(4);
		c.setPlaca("MZN-9158");
		carroDAO.atualizarCarro(c);
		//Listando novamente
		carros = carroDAO.listarCarro();
		for (Carro carro : carros) {
			System.out.println(carro);
		}
	}

}
