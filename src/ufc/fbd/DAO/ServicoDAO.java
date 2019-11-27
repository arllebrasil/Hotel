package ufc.fbd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Servico;

public class ServicoDAO {
	private Connection com;
	private FbdConnection baseCom;
	
	public ServicoDAO(FbdConnection fbdConnection) {
		// TODO Auto-generated constructor stub
		this.baseCom = fbdConnection;
	}
	public ArrayList<Servico> find(){
		String  query = "select * from servico";
		ArrayList<Servico> allServico = new ArrayList<Servico>();
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			
			while (response.next()) {
				Servico servico = new Servico();
				
				servico.setIdServico(response.getInt("id_servico"));
				servico.setPreco(response.getDouble("preco"));
				servico.setDescricao(response.getString("descricao"));
				
				allServico.add(servico);
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
		return allServico;
	}
	public void create(Servico servico) {
		String  query = "insert into servico values (?,?,?)";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1,servico.getIdServico());
			stmt.setDouble(2,servico.getPreco());
			stmt.setString(3,servico.getDescricao());
			
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
	public void update(Servico servico) {
		String  query = "update servico set preco = ?, descricao = ? where id_servico = ?";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(3,servico.getIdServico());
			stmt.setDouble(1,servico.getPreco());
			stmt.setString(2,servico.getDescricao());
			
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
	public void delete(Servico servico) {
		String  query = "delete from servico where id_servico = ? or (preco = ? and descricao = ?)";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1,servico.getIdServico());
			stmt.setDouble(2,servico.getPreco());
			stmt.setString(3,servico.getDescricao());
			
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
