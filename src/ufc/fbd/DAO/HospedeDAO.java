package ufc.fbd.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Hospede;

public class HospedeDAO {
	private Connection com;
	private FbdConnection baseCom;
	
	public HospedeDAO(FbdConnection fbdConnection) {
		// TODO Auto-generated constructor stub
		this.baseCom = fbdConnection;
	}

	public Hospede findOne(String userId){
		String fintStr = "select * from hospede where cpf = ?";
		Hospede cliente = null;
		try {
			this.com = baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(fintStr);
			stmt.setString(1,userId);
			ResultSet response = stmt.executeQuery();
			
			if(response.next()){
				cliente = new Hospede();
				cliente.setCpf(response.getString("cpf"));
				cliente.setNome(response.getString("nome"));
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(response.getDate("data_nasc"));
				cliente.setDataNacimento(calendar);
				
				cliente.setEndNumero(response.getInt("end_numero"));
				cliente.setEndBairro(response.getString("bairro"));
				cliente.setEndRua(response.getString("rua"));
				cliente.setEndCidade(response.getString("cidade"));
				cliente.setEndCEP(response.getInt("cep"));		
			}
			response.close();
		} catch (SQLException e) {
			System.out.println("Erro SqlConnector");
			// TODO: handle exception
		}finally {
			try {
				this.com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cliente;	
	}
	public ArrayList<Hospede> find(){
		ArrayList<Hospede> allClientes = new ArrayList<Hospede>();
		Hospede cliente;
		String query = "select * from hospede";
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt  = this.com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			while (response.next()) {
				cliente = new Hospede();
				cliente.setCpf(response.getString("cpf"));
				cliente.setNome(response.getString("nome"));
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(response.getDate("data_nasc"));
				cliente.setDataNacimento(calendar);
				
				cliente.setEndNumero(response.getInt("end_numero"));
				cliente.setEndBairro(response.getString("bairro"));
				cliente.setEndRua(response.getString("rua"));
				cliente.setEndCidade(response.getString("cidade"));
				cliente.setEndCEP(response.getInt("cep"));

				allClientes.add(cliente);
			}
			response.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return allClientes;	
	}

	public void delete(Hospede cliente) {
		// TODO Auto-generated method stub
		String  query = "delete from hospede where cpf = ?";
		String  query2 = "delete from telefone where cpf = ?";
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt1  = this.com.prepareStatement(query);
			PreparedStatement stmt2  = this.com.prepareStatement(query2);
			
			stmt1.setString(1, cliente.getCpf());
			stmt2.setString(1, cliente.getCpf());
			
			int response2 = stmt2.executeUpdate();
			int response1 = stmt1.executeUpdate();
			System.out.println(response1);
			
			stmt2.close();	
			stmt1.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void update(Hospede cliente) {
		// TODO Auto-generated method stub
		String  query = "update hospede set nome = ?, data_nasc = ?,end_numero = ?, bairro= ?, rua= ?,cidade= ?, cep= ? where cpf = ?";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt  = this.com.prepareStatement(query);
			stmt.setString(8, cliente.getCpf());
			stmt.setString(1, cliente.getNome());
			
			stmt.setDate(2,new java.sql.Date(cliente.getDataNacimento().getTimeInMillis()));
			stmt.setInt(3,cliente.getEndNumero());
			stmt.setString(4,cliente.getEndBairro());
			stmt.setString(5,cliente.getEndRua());
			stmt.setString(6,cliente.getEndCidade());
			stmt.setInt(7,cliente.endCEP);
			
			int response = stmt.executeUpdate();
			System.out.println(response);
			stmt.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void create(Hospede cliente) {
		// TODO Auto-generated method stub
		String query = "insert into hospede values (?,?,?,?,?,?,?,?)";
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt  = this.com.prepareStatement(query);
			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, cliente.getNome());
			
			stmt.setDate(3,new java.sql.Date(cliente.getDataNacimento().getTimeInMillis()));
			stmt.setInt(4,cliente.getEndNumero());
			stmt.setString(5,cliente.getEndBairro());
			stmt.setString(6,cliente.getEndRua());
			stmt.setString(7,cliente.getEndCidade());
			stmt.setInt(8,cliente.endCEP);
			
			int response = stmt.executeUpdate();
			System.out.println(response);
			stmt.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
