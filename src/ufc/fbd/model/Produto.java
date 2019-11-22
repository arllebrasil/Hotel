package ufc.fbd.model;

public class Produto {
	public int idProduto;
	public double preco;
	public String nome;
	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Produto(int idProduto, double preco, String nome) {
		super();
		this.idProduto = idProduto;
		this.preco = preco;
		this.nome = nome;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", preco=" + preco + ", nome=" + nome + "]";
	}
	
}
