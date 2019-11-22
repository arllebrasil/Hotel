package ufc.fbd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.HospedeReserva;

public class HospedeReservaDAO {
	private Connection com;
	private FbdConnection baseCom;
	public HospedeReservaDAO(FbdConnection fbdConnection) {
		// TODO Auto-generated constructor stub
		this.baseCom = fbdConnection;
	}
	public ArrayList<HospedeReserva> find(){
		String query = "select * from hospede_reserva";
		ArrayList<HospedeReserva> allHospedeReservas = new ArrayList<HospedeReserva>();
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			
			while (response.next()){
				HospedeReserva aux = new HospedeReserva();
				aux.setIdReserva(response.getInt("id_reserva"));
				aux.setCpfHospede(response.getString("cpf_hospede"));
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(response.getDate("data_inicio"));
				aux.setInicioData(calendar);
				
				Calendar calendar2  = Calendar.getInstance();
				calendar2.setTime(response.getDate("data_fim"));
				aux.setFimData(calendar2);
				
				allHospedeReservas.add(aux);
			}
			response.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERRO"+e);
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allHospedeReservas;
	}
	public void create(HospedeReserva hospedeReserva){
		String query = "insert into hospede_reserva values (?,?,?,?)";
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, hospedeReserva.getIdReserva());
			stmt.setString(2, hospedeReserva.getCpfHospede());
			stmt.setDate(3, new java.sql.Date(hospedeReserva.getInicioData().getTimeInMillis()));
			stmt.setDate(4, new java.sql.Date(hospedeReserva.getFimData().getTimeInMillis()));
			
			int response = stmt.executeUpdate();
			
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERRO"+e);
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
