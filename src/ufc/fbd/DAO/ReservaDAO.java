package ufc.fbd.DAO;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Reserva;

public class ReservaDAO {
	private Connection com;
	private FbdConnection baseCom;
	public ReservaDAO(FbdConnection fbdConnection) {
		// TODO Auto-generated constructor stub
		this.baseCom = fbdConnection;
	}
	public ArrayList<Reserva> find(){
		String query = "select * from reserva";
		ArrayList<Reserva> allReserva = new ArrayList<Reserva>();
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			while (response.next()) {
				Reserva reserva = new Reserva();
				reserva.setIdReserva(response.getInt("id_reserva"));
				reserva.setCpfHospede(response.getString("cpf_hospede"));
				reserva.setIdReserva(response.getInt("id_quarto"));
				allReserva.add(reserva);
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
		
		return allReserva;	
	}
	public Reserva findOne(int id){
		String query = "select * from reserva  where id_reserva  = ?";
		Reserva reserva = null;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			stmt.setInt(1,id);
			ResultSet response = stmt.executeQuery();
			if (response.next()) {
				reserva = new Reserva();
				reserva.setIdReserva(response.getInt("id_reserva"));
				reserva.setCpfHospede(response.getString("cpf_hospede"));
				reserva.setIdReserva(response.getInt("id_quarto"));	
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
		
		return reserva;	
	}
	public void create(Reserva reserva){
		String query = "insert into reserva values (?,?,?)";	
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			
			stmt.setInt(1, reserva.getIdReserva());
			stmt.setString(2, reserva.getCpfHospede());
			stmt.setInt(3, reserva.getIdQuarto());
			
			int response = stmt.executeUpdate();
			
			System.out.println("Reservas Inseridas "+ response);
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
	public void update(Reserva reserva){
		String query = "update reserva set cpf_hospede = ?, id_quarto where id_reserva = ?";	
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			
			stmt.setInt(3, reserva.getIdReserva());
			stmt.setString(1, reserva.getCpfHospede());
			stmt.setInt(2, reserva.getIdQuarto());
			
			int response = stmt.executeUpdate();
			
			System.out.println("Reservas Inseridas "+ response);
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
	public void delete(Reserva reserva){
		String query = "delete from reserva where id_reserva = ? and cpf_hospede = ? and id_ quarto = ?";	
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			
			stmt.setInt(1, reserva.getIdReserva());
			stmt.setString(2, reserva.getCpfHospede());
			stmt.setInt(3, reserva.getIdQuarto());
			
			int response = stmt.executeUpdate();
			
			System.out.println("Reservas Inseridas "+ response);
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
