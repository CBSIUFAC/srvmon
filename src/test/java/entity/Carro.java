package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Carro implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCarro;
	@Column(nullable=false)
	private int ano;
	@Column(length=8)
	private String placa;
	@Column
	private String combustivel;
	@Column
	private float quilometragem;
	@Column
	private String chassi;
	@Column
	private String RENAVAN;
	@Column
	private double valorLocacao;
	
	private double preco;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="idServidorReport",name="fkServidorReport")
	private ServidorReport servidorReport;
	
	public int getIdCarro() {
		return idCarro;
	}
	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public float getQuilometragem() {
		return quilometragem;
	}
	public void setQuilometragem(float quilometragem) {
		this.quilometragem = quilometragem;
	}
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public String getRENAVAN() {
		return RENAVAN;
	}
	public void setRENAVAN(String rENAVAN) {
		RENAVAN = rENAVAN;
	}
	public double getValorLocacao() {
		return valorLocacao;
	}
	public void setValorLocacao(double valorLocacao) {
		this.valorLocacao = valorLocacao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public String toString(){
		return "id: "+getIdCarro()+", placa: "+getPlaca()+", ano:"+getAno() ;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCarro;
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
		Carro other = (Carro) obj;
		if (idCarro != other.idCarro)
			return false;
		return true;
	}
	
	
}
