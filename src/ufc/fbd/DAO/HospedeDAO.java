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

	public Hospede findOne(String cpfH,String nome){
		String fintStr = "select * from hospede where cpf = ? or nome = ?";
		Hospede cliente = null;
		try {
			this.com = baseCom.getConnection();
			PreparedStatement stmt = com.prepareStatement(fintStr);
			stmt.setString(1,cpfH);
			stmt.setString(2, nome);
			ResultSet response = stmt.executeQuery();
			
			if(response.next()){
				cliente = new Hospede();
				cliente.setCpf(response.getString("cpf"));
				cliente.setNome(response.getString("nome"));
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(response.getDate("data_nasc"));
				cliente.setDataNascimento(calendar);
				
				cliente.setEndNumero(response.getInt("end_numero"));
				cliente.setEndBairro(response.getString("bairro"));
				cliente.setEndRua(response.getString("rua"));
				cliente.setEndCidade(response.getString("cidade"));
				cliente.setEndCEP(response.getInt("cep"));		
			}
			response.close();
		} catch (SQLException e) {
			System.out.println("ERRO "+e);
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
		String query = "select * from hospede";
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt  = this.com.prepareStatement(query);
			ResultSet response = stmt.executeQuery();
			while (response.next()) {
				Hospede cliente = new Hospede();
				cliente.setCpf(response.getString("cpf"));
				cliente.setNome(response.getString("nome"));
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(response.getDate("data_nasc"));
				cliente.setDataNascimento(calendar);
				
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
			System.out.println("ERRO"+e);
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

	public int delete(Hospede cliente) {
		// TODO Auto-generated method stub
		String  query = "delete from hospede where cpf = ?";
		int response = 0;
		
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt1  = this.com.prepareStatement(query);
			stmt1.setString(1, cliente.getCpf());
			
			response = stmt1.executeUpdate();
			stmt1.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRO "+e);
			e.printStackTrace();
		}finally {
			try {
				com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response;
	}

	public int update(Hospede old,Hospede new0) {
		// TODO Auto-generated method stub
		String  query = "update hospede set nome = ?, data_nasc = ?,end_numero = ?, bairro= ?, rua= ?,cidade= ?, cep= ? where cpf = ?  and nome = ?";
		int response = 0;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt  = this.com.prepareStatement(query);
			stmt.setString(8, old.getCpf());
			stmt.setString(9,old.getNome());
			stmt.setString(1, new0.getNome());
			
			stmt.setDate(2,new java.sql.Date(new0.getDataNascimento().getTimeInMillis()));
			stmt.setInt(3,new0.getEndNumero());
			stmt.setString(4,new0.getEndBairro());
			stmt.setString(5,new0.getEndRua());
			stmt.setString(6,new0.getEndCidade());
			stmt.setInt(7,new0.endCEP);
			
			response = stmt.executeUpdate();
			System.out.println(response);
			stmt.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRO "+e);
			e.printStackTrace();
		}finally {
			try {
				com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response;
	}

	public int create(Hospede cliente) {
		// TODO Auto-generated method stub
		String query = "insert into hospede values (?,?,?,?,?,?,?,?)";
		int response = 0;
		try {
			this.com = this.baseCom.getConnection();
			PreparedStatement stmt  = this.com.prepareStatement(query);
			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, cliente.getNome());
			
			stmt.setDate(3,new java.sql.Date(cliente.getDataNascimento().getTimeInMillis()));
			stmt.setInt(4,cliente.getEndNumero());
			stmt.setString(5,cliente.getEndBairro());
			stmt.setString(6,cliente.getEndRua());
			stmt.setString(7,cliente.getEndCidade());
			stmt.setInt(8,cliente.getEndCEP());
			
			response = stmt.executeUpdate();
			stmt.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERRO "+e);
			e.printStackTrace();
		}finally {
			try {
				com.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response;
	}
}
