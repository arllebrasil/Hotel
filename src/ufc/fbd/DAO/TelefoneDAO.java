package ufc.fbd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Telefone;

public class TelefoneDAO {
	private Connection com;
	private FbdConnection baseCom;
	
	public TelefoneDAO(FbdConnection fbdConnection) {
		// TODO Auto-generated constructor stub
		this.baseCom = fbdConnection;
	}
	public int create(Telefone telefone) {
		String query  = "insert into telefone values (?,?)";
		int response = 0;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setString(1, telefone.getCpf());
			stmt.setString(2, telefone.getTelefone());
			
			response = stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERRO "+e);
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response;
	}
	
	public void update(Telefone telefone, String newTelefone) {
		String query  = "update telefone set cpf = ?, telefone = ? where telefone  = ?";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setString(1, telefone.getCpf());
			stmt.setString(2,newTelefone);
			stmt.setString(3,telefone.getTelefone());
			
			int response = stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERRO "+e);
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void delete(Telefone telefone) {
		String query  = "delete from telefone where cpf = ? or telefone  = ?";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setString(1, telefone.getCpf());
			stmt.setString(2,telefone.getTelefone());

			int response = stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERRO "+e);
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Telefone> find() {
		String query  = "select * from showTelefone";
		ArrayList<Telefone> allTelefone = new ArrayList<Telefone>();
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			
			while(response.next()){
				Telefone auxTelefone = new Telefone();
				auxTelefone.setNome(response.getString("nome"));
				auxTelefone.setCpf(response.getString("cpf"));
				auxTelefone.setTelefone(response.getString("telefone"));
				allTelefone.add(auxTelefone);
			}	
			response.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERRO "+e);
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allTelefone;
	}

}
