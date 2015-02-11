package util;

import java.util.List;

import entity.Carro;
import DAO.CarroDAO;

public class BuscaCarro {

	public static void main(String[] args) {
		CarroDAO cDAO = new CarroDAO();
		String str = "91";
		List<Carro> listaCarro = cDAO.buscarPorPlaca(str);
		for (Carro carro : listaCarro) {
			System.out.println(carro);
		}
	}

}
