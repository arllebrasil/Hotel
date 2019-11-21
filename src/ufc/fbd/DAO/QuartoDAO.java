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
}
