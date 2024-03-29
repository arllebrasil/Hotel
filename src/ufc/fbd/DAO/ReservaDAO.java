package ufc.fbd.DAO;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Reserva;
import ufc.fbd.model.ReservaCompleta;

public class ReservaDAO {
	private Connection com;
	private FbdConnection baseCom;
	public ReservaDAO(FbdConnection fbdConnection) {
		// TODO Auto-generated constructor stub
		this.baseCom = fbdConnection;
	}
	public ArrayList<ReservaCompleta> find(){
		String query = "select * from reserva_completa";
		ArrayList<ReservaCompleta> allReserva = new ArrayList<ReservaCompleta>();
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			while (response.next()) {
				ReservaCompleta reserva = new ReservaCompleta();
				reserva.setIdReserva(response.getInt("id_reserva"));
				reserva.setNome(response.getString("nome"));
				reserva.setCpfHospede(response.getString("cpf"));
				reserva.setQuartoId(response.getInt("num_quarto"));
				reserva.setDiaria(response.getDouble("diaria"));
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(response.getDate("data_inicio"));
				reserva.setInicioData(calendar);
				
				Calendar calendar2  = Calendar.getInstance();
				calendar2.setTime(response.getDate("data_fim"));
				reserva.setFimData(calendar2);
				
				allReserva.add(reserva);
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
		
		return allReserva;	
	}
	public ReservaCompleta findOne(int id_reserva, String cpf){
		String query = "select * from reserva_completa where id_reserva = ? and cpf = ?";
		ReservaCompleta reservaCompleta = null;
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			stmt.setInt(1, id_reserva);
			stmt.setString(2, cpf);
			ResultSet response = stmt.executeQuery();
			if (response.next()) {
				reservaCompleta = new ReservaCompleta();
				reservaCompleta.setIdReserva(response.getInt("id_reserva"));
				reservaCompleta.setNome(response.getString("nome"));
				reservaCompleta.setCpfHospede(response.getString("cpf"));
				reservaCompleta.setQuartoId(response.getInt("num_quarto"));
				reservaCompleta.setDiaria(response.getDouble("diaria"));
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(response.getDate("data_inicio"));
				reservaCompleta.setInicioData(calendar);
				
				Calendar calendar2  = Calendar.getInstance();
				calendar2.setTime(response.getDate("data_fim"));
				reservaCompleta.setFimData(calendar2);
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
		
		return reservaCompleta;	
	}
	public int create(Reserva reserva){
		String query = "insert into reserva values (?,?,?)";
		int response = 0;
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			
			stmt.setInt(1, reserva.getIdReserva());
			stmt.setString(2, reserva.getCpfHospede());
			stmt.setInt(3, reserva.getIdQuarto());
			
			response = stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERRO de inser��o:"+e);
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
	public int update(Reserva reserva){
		String query = "update reserva set cpf_hospede = ?, id_quarto = ? where id_reserva = ? and cpf_hospede = ?";
		int response = 0;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			
			stmt.setInt(3, reserva.getIdReserva());
			stmt.setString(4, reserva.getCpfHospede());
			stmt.setString(1, reserva.getCpfHospede());
			stmt.setInt(2, reserva.getIdQuarto());
			
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
	public int delete(Reserva reserva){
		String query = "delete from reserva where id_reserva = ? and cpf_hospede = ? and id_quarto = ?";
		int response = 0;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(query);
			
			stmt.setInt(1, reserva.getIdReserva());
			stmt.setString(2, reserva.getCpfHospede());
			stmt.setInt(3, reserva.getIdQuarto());
			
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
}
