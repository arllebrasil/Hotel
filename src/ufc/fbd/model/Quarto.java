package ufc.fbd.model;

public class Quarto {
	public int numero;
	public double diaria;
	public int capacidade;
	public String descricao;
	public Quarto(int numero, double diaria, int capacidade, String descricao) {
		super();
		this.numero = numero;
		this.diaria = diaria;
		this.capacidade = capacidade;
		this.descricao = descricao;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public double getDiaria() {
		return diaria;
	}
	public void setDiaria(double diaria) {
		this.diaria = diaria;
	}
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Quarto() {
		super();
	}
}
