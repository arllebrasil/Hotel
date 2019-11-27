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
		String query = "select * from reserva_servico";
		ArrayList<ReservaServico> allReservaServ = new ArrayList<ReservaServico>();
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			
			while (response.next()) {
				ReservaServico reservaServ = new ReservaServico();

				reservaServ.setIdReserva(response.getInt("id_reserva"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(response.getDate("data"));
				reservaServ.setData(data);
				
				reservaServ.setCpfHospede(response.getString("cpf_hospede"));
				reservaServ.setIdServico(response.getInt("id_servico"));
				allReservaServ.add(reservaServ);
			}
			response.close();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro na Leitura dos dados:"+e);
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
	public void create(ReservaServico reservaServico) {
		String query = "insert into reserva_servico values (?,?,?,?)";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, reservaServico.getIdReserva());
			stmt.setDate(2,new java.sql.Date(reservaServico.getData().getTimeInMillis()));
			stmt.setString(3,reservaServico.getCpfHospede());		
			stmt.setInt(4,reservaServico.getIdServico());
			
			int response = stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro na inserção dos dados:"+e);
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void update(ReservaServico oldReservaServico,ReservaServico newReservaServico) {
		String setQuery = "update reserva_servico set  id_reserva = ? , cpf_hospede = ?, data = ?, id_servico = ? ";
		String whereQuery = "where id_reserva = ? and cpf_hospede = ? and data = ? and id_servico = ?";
		String query = setQuery+whereQuery;
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
			
			int response = stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro na atualização dos dados:"+e);
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void delete(ReservaServico oldReservaServico) {
		String query = "delete from reserva_servico where id_reserva = ? and cpf_hospede = ? and data = ? and id_servico = ? ";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, oldReservaServico.getIdReserva());
			stmt.setString(2,oldReservaServico.getCpfHospede());
			stmt.setDate(3,new java.sql.Date(oldReservaServico.getData().getTimeInMillis()));
			stmt.setInt(4,oldReservaServico.getIdServico());
			
			int response = stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Erro na remoção dos dados:"+e);
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
