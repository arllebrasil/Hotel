package ufc.fbd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Quarto;

public class QuartoDAO {
	private Connection com;
	private FbdConnection baseCom;
	
	public QuartoDAO(FbdConnection fbdConnection) {
		// TODO Auto-generated constructor stub
		this.baseCom = fbdConnection;
	}
	public ArrayList<Quarto> find(){
		String query = "select * from quarto";
		ArrayList<Quarto> allQuarto  = new ArrayList<Quarto>();
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			
			while (response.next()) {
				Quarto auxQrt = new Quarto();
				auxQrt.setNumero(response.getInt("num_quarto"));
				auxQrt.setDiaria(response.getDouble("diaria"));
				auxQrt.setCapacidade(response.getInt("capacidade"));
				auxQrt.setDescricao(response.getString("descricao"));
				allQuarto.add(auxQrt);	
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
		return allQuarto;
	}
	public Quarto findOne(int id){
		String query = "select * from quarto where num_quarto = ?";
		Quarto quarto = null;	
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			stmt.setInt(1,id);
			ResultSet response = stmt.executeQuery();
			
			if(response.next()) {
				quarto = new Quarto();
				quarto.setNumero(response.getInt("num_quarto"));
				quarto.setDiaria(response.getDouble("diaria"));
				quarto.setCapacidade(response.getInt("capacidade"));
				quarto.setDescricao(response.getString("descricao"));
				
				response.close();
				stmt.close();
			}
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
		return quarto;
	}
	public ArrayList<Quarto> find(Quarto quarto){
		String query = "select * from quarto where num_quarto = ? or diaria = ? or capacidade = ? or descricao = ?";
		ArrayList<Quarto> allQuarto  = new ArrayList<Quarto>();
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			
			stmt.setInt(1,quarto.getNumero());
			stmt.setDouble(2,quarto.getDiaria());
			stmt.setInt(3,quarto.getCapacidade());
			stmt.setString(4,quarto.getDescricao());
			ResultSet response = stmt.executeQuery();
			
			while (response.next()) {
				Quarto auxQrt = new Quarto();
				auxQrt.setNumero(response.getInt("num_quarto"));
				auxQrt.setDiaria(response.getDouble("diaria"));
				auxQrt.setCapacidade(response.getInt("capacidade"));
				auxQrt.setDescricao(response.getString("descricao"));
				allQuarto.add(auxQrt);	
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
		return allQuarto;
	}
	public void delete(Quarto quarto){
		String query = "delete from quarto where num_quarto = ? or (diaria = ? and capacidade = ? and descricao = ?)";	
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			
			stmt.setInt(1,quarto.getNumero());
			stmt.setDouble(2,quarto.getDiaria());
			stmt.setInt(3,quarto.getCapacidade());
			stmt.setString(4,quarto.getDescricao());
			
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
	public void create(Quarto quarto) {
		String query = "insert into quarto values (?,?,?,?)";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			
			stmt.setInt(1,quarto.getNumero());
			stmt.setDouble(2,quarto.getDiaria());
			stmt.setInt(3,quarto.getCapacidade());
			stmt.setString(4,quarto.getDescricao());
			
			int response = stmt.executeUpdate();
			String ms = (response <= 0)?"quarto ja existe":"cadatros realizados:"+response;
			System.out.println(ms);
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
	public void update(Quarto quarto) {
		String query = "update quarto set diaria = ?, capacidade = ?,descricao = ? where num_quarto = ?";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			
			stmt.setInt(4,quarto.getNumero());
			stmt.setDouble(1,quarto.getDiaria());
			stmt.setInt(2,quarto.getCapacidade());
			stmt.setString(3,quarto.getDescricao());
			
			int response = stmt.executeUpdate();
			String ms = (response <= 0)?"quarto ja existe":"cadatros atualizados:"+response;
			System.out.println(ms);
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
