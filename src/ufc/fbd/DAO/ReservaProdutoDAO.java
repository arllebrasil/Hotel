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
		String query = "select * from venda_produto";
		ArrayList<ReservaProduto> allReservaPro = new ArrayList<ReservaProduto>();
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			
			while (response.next()) {
				ReservaProduto reservaPro = new ReservaProduto();

				reservaPro.setIdReserva(response.getInt("id_reserva"));
				reservaPro.setHospede(response.getString("hospede"));
				reservaPro.setCpfHospede(response.getString("cpf"));
				reservaPro.setProduto(response.getString("produto"));
				reservaPro.setIdProduto(response.getInt("id_produto"));
				reservaPro.setPreco(response.getDouble("preco"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(response.getDate("data_compra"));
				reservaPro.setData(data);
				
				allReservaPro.add(reservaPro);
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
		return allReservaPro;
	}
	public ArrayList<ReservaProduto> show(String cpf, String nome){
		String query = "select * from venda_produto where cpf = ? or hospede = ?";
		ArrayList<ReservaProduto> allReservaPro = new ArrayList<ReservaProduto>();
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			stmt.setString(1,cpf);
			stmt.setString(2,nome);
			ResultSet response = stmt.executeQuery();
			
			while (response.next()) {
				ReservaProduto reservaPro = new ReservaProduto();

				reservaPro.setIdReserva(response.getInt("id_reserva"));
				reservaPro.setHospede(response.getString("hospede"));
				reservaPro.setCpfHospede(response.getString("cpf"));
				reservaPro.setProduto(response.getString("produto"));
				reservaPro.setIdProduto(response.getInt("id_produto"));
				reservaPro.setPreco(response.getDouble("preco"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(response.getDate("data_compra"));
				reservaPro.setData(data);
				
				allReservaPro.add(reservaPro);
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
		return allReservaPro;
	}
	public ReservaProduto findOne( ReservaProduto oldReservaPro){
		String query = "select * from venda_produto where id_reserva = ? and cpf = ? and id_produto = ?";
		ReservaProduto reservaPro = null;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			stmt.setInt(1, oldReservaPro.getIdReserva());
			stmt.setString(2, oldReservaPro.getCpfHospede());
			stmt.setInt(3, oldReservaPro.getIdProduto());
			
			ResultSet response = stmt.executeQuery();
			
			if (response.next()) {
				reservaPro = new ReservaProduto();

				reservaPro.setIdReserva(response.getInt("id_reserva"));
				reservaPro.setHospede(response.getString("hospede"));
				reservaPro.setCpfHospede(response.getString("cpf"));
				reservaPro.setProduto(response.getString("produto"));
				reservaPro.setIdProduto(response.getInt("id_produto"));
				reservaPro.setPreco(response.getDouble("preco"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(response.getDate("data_compra"));
				reservaPro.setData(data);
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
		return reservaPro;
	}
	public int create(ReservaProduto reservaProduto) {
		String query = "insert into reserva_produto values (?,?,?,?)";
		int response = 0;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, reservaProduto.getIdReserva());
			stmt.setDate(2,new java.sql.Date(reservaProduto.getData().getTimeInMillis()));
			stmt.setString(3,reservaProduto.getCpfHospede());		
			stmt.setInt(4,reservaProduto.getIdProduto());
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
	public int update(ReservaProduto oldReservaProduto,ReservaProduto newReservaPro) {
		String setQuery = "update reserva_produto set  id_reserva = ? , cpf_hospede = ?, data = ?, id_produto = ? ";
		String whereQuery = "where id_reserva = ? and cpf_hospede = ? and data = ? and id_produto = ?";
		String query = setQuery+whereQuery;
		int response = 0;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, newReservaPro.getIdReserva());
			stmt.setString(2,newReservaPro.getCpfHospede());
			stmt.setDate(3,new java.sql.Date(newReservaPro.getData().getTimeInMillis()));
			stmt.setInt(4,newReservaPro.getIdProduto());
			
			stmt.setInt(5, oldReservaProduto.getIdReserva());
			stmt.setString(6,oldReservaProduto.getCpfHospede());
			stmt.setDate(7,new java.sql.Date(oldReservaProduto.getData().getTimeInMillis()));
			stmt.setInt(8,oldReservaProduto.getIdProduto());
			

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
	public int delete(ReservaProduto oldReservaProduto) {
		String query = "delete from reserva_produto where id_reserva = ? and cpf_hospede = ? and data = ? and id_produto = ? ";
		int response = 0;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt = this.com.prepareStatement(query);
			
			stmt.setInt(1, oldReservaProduto.getIdReserva());
			stmt.setString(2,oldReservaProduto.getCpfHospede());
			stmt.setDate(3,new java.sql.Date(oldReservaProduto.getData().getTimeInMillis()));
			stmt.setInt(4,oldReservaProduto.getIdProduto());
			
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
