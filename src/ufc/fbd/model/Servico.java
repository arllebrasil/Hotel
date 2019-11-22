package ufc.fbd.model;

public class Servico {
	public int idServico;
	public double preco;
	public String descricao;
	public Servico(int idServico, double preco, String descricao) {
		super();
		this.idServico = idServico;
		this.preco = preco;
		this.descricao = descricao;
	}
	public Servico() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdServico() {
		return idServico;
	}
	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Servico [idServico=" + idServico + ", preco=" + preco + ", descricao=" + descricao + "]";
	}
	
}
