package ufc.fbd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.ReservaProduto;

public class ReservaProdutoDAO {
	private Connection com;
	private FbdConnection baseCom;
	public ReservaProdutoDAO(FbdConnection fbdConnection) {
		this.baseCom = fbdConnection;
	}
	public ArrayList<ReservaProduto> find(){
		String query = "select * from reserva_produto";
		ArrayList<ReservaProduto> allReservaPro = new ArrayList<ReservaProduto>();
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			
			while (response.next()) {
				ReservaProduto reservaPro = new ReservaProduto();

				reservaPro.setIdReserva(response.getInt("id_reserva"));
				reservaPro.setCpfHospede(response.getString("cpf_hospede"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(response.getDate("data"));
				reservaPro.setData(data);
				
				reservaPro.setIdProduto(response.getInt("id_produto"));
				allReservaPro.add(reservaPro);
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
		return allReservaPro;
	}
	public void create(ReservaProduto reservaProduto) {
		String query = "insert into reserva_produto values (?,?,?,?)";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, reservaProduto.getIdReserva());
			stmt.setString(2,reservaProduto.getCpfHospede());
			stmt.setDate(3,new java.sql.Date(reservaProduto.getData().getTimeInMillis()));
			stmt.setInt(4,reservaProduto.getIdProduto());
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
	public void update(ReservaProduto oldReservaProduto,ReservaProduto newReservaPro) {
		String setQuery = "update reserva_produto set  id_reserva = ? , cpf_hospede = ?, data = ?, id_produto = ? ";
		String whereQuery = "where id_reserva = ? and cpf_hospede = ? and data = ? and id_produto = ?";
		String query = setQuery+whereQuery;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, newReservaPro.getIdReserva());
			stmt.setString(2,newReservaPro.getCpfHospede());
			stmt.setDate(3,new java.sql.Date(newReservaPro.getData().getTimeInMillis()));
			stmt.setInt(4,newReservaPro.getIdProduto());
			
			stmt.setInt(1, oldReservaProduto.getIdReserva());
			stmt.setString(2,oldReservaProduto.getCpfHospede());
			stmt.setDate(3,new java.sql.Date(oldReservaProduto.getData().getTimeInMillis()));
			stmt.setInt(4,oldReservaProduto.getIdProduto());
			

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
	public void delete(ReservaProduto oldReservaProduto) {
		String query = "delete from reserva_produto where id_reserva = ? and cpf_hospede = ? and data = ? and id_produto = ? ";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, oldReservaProduto.getIdReserva());
			stmt.setString(2,oldReservaProduto.getCpfHospede());
			stmt.setDate(3,new java.sql.Date(oldReservaProduto.getData().getTimeInMillis()));
			stmt.setInt(4,oldReservaProduto.getIdProduto());
			
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
