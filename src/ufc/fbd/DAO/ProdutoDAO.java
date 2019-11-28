package ufc.fbd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Produto;

public class ProdutoDAO {
	private Connection com;
	private FbdConnection baseCom;
	
	public ProdutoDAO(FbdConnection fbdConnection) {
		// TODO Auto-generated constructor stub
		this.baseCom = fbdConnection;
	}
	public ArrayList<Produto> find(){
		String  query = "select * from produto";
		ArrayList<Produto> allProduto = new ArrayList<Produto>();
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			
			while (response.next()) {
				Produto produto = new Produto();
				
				produto.setIdProduto(response.getInt("id_produto"));
				produto.setPreco(response.getDouble("preco"));
				produto.setNome(response.getString("nome"));
				
				allProduto.add(produto);
			}
			response.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allProduto;
	}
	public Produto findOne(int id) {
		// TODO Auto-generated method stub
		String  query = "select * from produto where id_produto = ?";
		Produto produto = null;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			stmt.setInt(1,id);
			ResultSet response = stmt.executeQuery();
			
			if (response.next()) {
				produto = new Produto();
				
				produto.setIdProduto(response.getInt("id_produto"));
				produto.setPreco(response.getDouble("preco"));
				produto.setNome(response.getString("nome"));
				
			}
			response.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return produto;
	}
	public void create(Produto produto) {
		String  query = "insert into Produto values (?,?,?)";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1,produto.getIdProduto());
			stmt.setDouble(2,produto.getPreco());
			stmt.setString(3,produto.getNome());
			
			int response = stmt.executeUpdate();		
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update(Produto produto) {
		String  query = "update produto set preco = ?, nome = ? where id_pruduto = ?";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(3,produto.getIdProduto());
			stmt.setDouble(1,produto.getPreco());
			stmt.setString(2,produto.getNome());
			
			int response = stmt.executeUpdate();		
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void delete(Produto produto) {
		String  query = "delete from produto where id_produto = ? or (preco = ? and nome = ?)";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1,produto.getIdProduto());
			stmt.setDouble(2,produto.getPreco());
			stmt.setString(3,produto.getNome());
			
			int response = stmt.executeUpdate();		
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
