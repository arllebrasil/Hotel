package ufc.fbd.model;

public class Reserva {
	public int idReserva;
	public String cpfHospede;
	public int idQuarto;
	public Reserva(int idReserva, String cpfHospede, int idQuarto) {
		super();
		this.idReserva = idReserva;
		this.cpfHospede = cpfHospede;
		this.idQuarto = idQuarto;
	}
	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", cpfHospede=" + cpfHospede + ", idQuarto=" + idQuarto + "]";
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public String getCpfHospede() {
		return cpfHospede;
	}
	public void setCpfHospede(String cpfHospede) {
		this.cpfHospede = cpfHospede;
	}
	public int getIdQuarto() {
		return idQuarto;
	}
	public void setIdQuarto(int idQuarto) {
		this.idQuarto = idQuarto;
	}
	
}
