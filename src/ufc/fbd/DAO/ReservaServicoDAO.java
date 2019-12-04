package ufc.fbd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.ReservaProduto;
import ufc.fbd.model.ReservaServico;

public class ReservaServicoDAO {
	private Connection com;
	private FbdConnection baseCom;
	public ReservaServicoDAO(FbdConnection fbdConnection) {
		this.baseCom = fbdConnection;
	}
	public ArrayList<ReservaServico> find(){
		String query = "select * from venda_servico";
		ArrayList<ReservaServico> allReservaServ = new ArrayList<ReservaServico>();
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			
			while (response.next()) {
				ReservaServico reservaServ = new ReservaServico();

				reservaServ.setIdReserva(response.getInt("id_reserva"));
				reservaServ.setHospede(response.getString("hospede"));
				reservaServ.setCpfHospede(response.getString("cpf"));
				reservaServ.setServico(response.getString("servico"));
				reservaServ.setIdServico(response.getInt("id_servico"));
				reservaServ.setPreco(response.getDouble("preco"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(response.getDate("data_compra"));
				reservaServ.setData(data);
				
				allReservaServ.add(reservaServ);
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
		return allReservaServ;
	}
	public ReservaServico findOne( ReservaServico oldReservaServ){
		String query = "select * from venda_servico where id_reserva = ? and cpf = ? and id_servico = ?";
		ReservaServico reservaServ = null;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			stmt.setInt(1, oldReservaServ.getIdReserva());
			stmt.setString(2, oldReservaServ.getCpfHospede());
			stmt.setInt(3, oldReservaServ.getIdServico());
			
			ResultSet response = stmt.executeQuery();
			
			if (response.next()) {
				reservaServ = new ReservaServico();

				reservaServ.setIdReserva(response.getInt("id_reserva"));
				reservaServ.setHospede(response.getString("hospede"));
				reservaServ.setCpfHospede(response.getString("cpf"));
				reservaServ.setServico(response.getString("servico"));
				reservaServ.setIdServico(response.getInt("id_servico"));
				reservaServ.setPreco(response.getDouble("preco"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(response.getDate("data_compra"));
				reservaServ.setData(data);
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
		return reservaServ;
	}
	public int create(ReservaServico reservaServico) {
		String query = "insert into reserva_servico values (?,?,?,?)";
		int response = 0;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, reservaServico.getIdReserva());
			stmt.setDate(2,new java.sql.Date(reservaServico.getData().getTimeInMillis()));
			stmt.setString(3,reservaServico.getCpfHospede());		
			stmt.setInt(4,reservaServico.getIdServico());
			
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
	public int update(ReservaServico oldReservaServico,ReservaServico newReservaServico) {
		String setQuery = "update reserva_servico set  id_reserva = ? , cpf_hospede = ?, data = ?, id_servico = ? ";
		String whereQuery = "where id_reserva = ? and cpf_hospede = ? and data = ? and id_servico = ?";
		String query = setQuery+whereQuery;
		int response = 0;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, newReservaServico.getIdReserva());
			stmt.setString(2,newReservaServico.getCpfHospede());
			stmt.setDate(3,new java.sql.Date(newReservaServico.getData().getTimeInMillis()));
			stmt.setInt(4,newReservaServico.getIdServico());
			
			stmt.setInt(5, oldReservaServico.getIdReserva());
			stmt.setString(6,oldReservaServico.getCpfHospede());
			stmt.setDate(7,new java.sql.Date(oldReservaServico.getData().getTimeInMillis()));
			stmt.setInt(8,oldReservaServico.getIdServico());
			
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
	public int delete(ReservaServico oldReservaServico) {
		String query = "delete from reserva_servico where id_reserva = ? and cpf_hospede = ? and data = ? and id_servico = ? ";
		int response = 0;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, oldReservaServico.getIdReserva());
			stmt.setString(2,oldReservaServico.getCpfHospede());
			stmt.setDate(3,new java.sql.Date(oldReservaServico.getData().getTimeInMillis()));
			stmt.setInt(4,oldReservaServico.getIdServico());
			
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
