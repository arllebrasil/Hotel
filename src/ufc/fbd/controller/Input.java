package ufc.fbd.controller;

import java.util.ArrayList;
import java.util.Scanner;

import ufc.fbd.model.Hospede;
import ufc.fbd.model.HospedeReserva;
import ufc.fbd.model.Produto;
import ufc.fbd.model.Quarto;
import ufc.fbd.model.Reserva;
import ufc.fbd.model.ReservaCompleta;
import ufc.fbd.model.ReservaProduto;
import ufc.fbd.model.ReservaServico;
import ufc.fbd.model.Servico;
import ufc.fbd.model.Telefone;

public class Input {
    public Hospede createHospede(){
    	System.out.println("### PARA CANCELAR DIGITE #sair");
    	
        String cpf;
        do {
            System.out.print("Digite o cpf do hóspede: ");
            Scanner cpfs = new Scanner(System.in);
            cpf = cpfs.nextLine();
            if(cpf.equals("#sair")) {
            	Hospede hospede = null;
            	return hospede;
            }
            if(cpf.trim().isEmpty()) {
                System.out.println("Fail::> O cpf não pode ser vazio!");
            }
        }while(cpf.trim().isEmpty());

        String nome;
        do {
            System.out.print("Digite o nome do hóspede: ");
            Scanner nomes = new Scanner(System.in);
            nome = nomes.nextLine();
            if(nome.equals("#sair")) {
            	Hospede hospede = null;
            	return hospede;
            }
            if(nome.trim().isEmpty()) {
                System.out.println("Fail::> O nome não pode ser vazio!");
            }
        }while(nome.trim().isEmpty());

        String data_nasc;
        // data de nascimento do hóspede
        do {
            System.out.print("Digite a data de nascimento do hóspede: (Deve seguir o modelo dia/mês/ano) ");
            Scanner data_nascs = new Scanner(System.in);
            data_nasc = data_nascs.nextLine();
            if(data_nasc.equals("#sair")) {
            	Hospede hospede = null;
            	return hospede;
            }
            if(data_nasc.trim().isEmpty()) {
                System.out.println("Fail::> A data de nascimento não pode ser vazio!");
            }else if(data_nasc.charAt(2) != '/' || data_nasc.charAt(5) != '/'){
                System.out.println("Fail::> Por favor siga o modelo dia/mês/ano. Ex: 01/03/2000");
            }
        }while(data_nasc.trim().isEmpty() || (data_nasc.charAt(2) != '/' || data_nasc.charAt(5) != '/'));

        // Endereço - número
        int end_numero;
        System.out.print("Digite o número da casa onde moras:");
        Scanner end_numeros = new Scanner(System.in);
        String end_numerov = end_numeros.nextLine();
        if(end_numerov.equals("#sair")) {
        	Hospede hospede = null;
        	return hospede;
        }
        if(end_numerov.trim().isEmpty()){
            end_numero = 0;
        }else{
            end_numero = Integer.parseInt(end_numerov);
        }

        // Endereço - bairro
        String bairro;
        System.out.print("Digite o bairro onde moras:");
        Scanner bairros = new Scanner(System.in);
        bairro = bairros.nextLine();
        if(bairro.equals("#sair")) {
        	Hospede hospede = null;
        	return hospede;
        }
        
        // Endereço - rua
        String rua;
        System.out.print("Digite a rua onde moras:");
        Scanner ruas = new Scanner(System.in);
        rua = ruas.nextLine();
        if(rua.equals("#sair")) {
        	Hospede hospede = null;
        	return hospede;
        }
        
        // Endereço - cidade
        String cidade;
        System.out.print("Digite a cidade onde moras:");
        Scanner cidades = new Scanner(System.in);
        cidade = cidades.nextLine();
        if(cidade.equals("#sair")) {
        	Hospede hospede = null;
        	return hospede;
        }
        
        // Endereço - cep
        int cep;
        System.out.print("Digite o CEP onde moras:");
        Scanner ceps = new Scanner(System.in);
        String cepv = ceps.nextLine();
        if(cepv.equals("#sair")) {
        	Hospede hospede = null;
        	return hospede;
        }
        if(cepv.trim().isEmpty()){
            cep = 0;
        }else{
            cep = Integer.parseInt(cepv);
        }

        Hospede hospede = new Hospede(cpf,nome,data_nasc,end_numero,bairro,rua,cidade,cep);

        return hospede;
    }
    public ArrayList<Telefone> createTelefone() {
    	System.out.println("### PARA CANCELAR DIGITE #sair");
    	
    	ArrayList<Telefone> telefones = new ArrayList<Telefone>();
    	
    	String telefone;
    	String cpf;
    	do {
    		System.out.print("Digite o número do TELEFONE e o CPF do Hospede: (Para sair apenas pressione enter)\n");
    		Scanner input = new Scanner(System.in);
    		telefone =input.nextLine();
    		cpf = input.nextLine();
    		if(cpf.equals("#sair")) {
            	telefones = null;
            	return telefones;
            }
    		if(telefone.equals("#sair")) {
            	telefones = null;
            	return telefones;
            }
    		if(!telefone.trim().isEmpty() && !cpf.trim().isEmpty()) {
	    		Telefone t = new Telefone(cpf, telefone);
	    		telefones.add(t);
    		}
    	}while(!telefone.trim().isEmpty() && !cpf.trim().isEmpty());
    	
    	return telefones;
    }
    public Reserva createReserva(){
    	System.out.println("### PARA CANCELAR DIGITE #sair");
        String id_res;
        int id = 0;
        do {
            System.out.print("Digite o id da reserva: ");
            Scanner input_id = new Scanner(System.in);
            id_res = input_id.nextLine();
            if(id_res.equals("#sair")) {
            	Reserva reserva = null;
            	return reserva;
            }
            if(id_res.trim().isEmpty()){
                System.out.print("Fail::> O id não pode ser vazio!");
            }else{
                id = Integer.parseInt(id_res);
            }
        }while(id_res.trim().isEmpty());
        
        String cpf_hospede;
        do {
            System.out.print("Digite o cpf do hóspede que fará a reserva: ");
            Scanner input_cpf = new Scanner(System.in);
            cpf_hospede = input_cpf.nextLine();
            if(cpf_hospede.equals("#sair")) {
            	Reserva reserva = null;
            	return reserva;
            }
            if(cpf_hospede.trim().isEmpty()) {
                System.out.println("Fail::> O cpf não pode ser vazio!");
            }
        }while(cpf_hospede.trim().isEmpty());
        
        int id_quarto = 0;
        String q;
        do {
        	System.out.println("Digite o número do quarto, para qual será feita a reserva: ");
        	Scanner input_quarto = new Scanner(System.in);
        	q = input_quarto.nextLine();
        	if(q.equals("#sair")) {
            	Reserva reserva = null;
            	return reserva;
            }
        	if(q.trim().isEmpty()) {
        		System.out.println("Fail::>O número do quarto não pode ser vazio!");
        	}else {
        		id_quarto = Integer.parseInt(q);
        	}
        }while(q.trim().isEmpty());
        
        Reserva reservar = new Reserva(id, cpf_hospede, id_quarto);
        
        // Hospede_reserva
        //createHospedeReserva(id, cpf_hospede);
        
        return reservar;
    }
    public HospedeReserva createHospedeReserva(int idReserva, String cpf) {
    	System.out.println("### PARA CANCELAR DIGITE #sair");
    	String datai, dataf;
    	boolean t;
    	do {
	    	System.out.print("Digite a data para o checkin: (dia/mês/ano)");
	    	Scanner input_init = new Scanner(System.in);
	    	datai = input_init.nextLine();
	    	if(datai.equals("#sair")) {
            	HospedeReserva r = null;
            	return r;
            }
	    	t = !data_valida(datai);
	    	if(t) {
	    		System.out.println("Fail::>Data informada é inválida, por favor siga o modelo (dia/mês/ano) Ex: 01/02/2000");
	    	}
    	}while(t);
    	
    	do {
	    	System.out.print("Digite a data para o checkout: (dia/mês/ano)");
	    	Scanner input_fim = new Scanner(System.in);
	    	dataf = input_fim.nextLine();
	    	if(dataf.equals("#sair")) {
            	HospedeReserva r = null;
            	return r;
            }
	    	t = !data_valida(dataf);
	    	if(t) {
	    		System.out.println("Fail::>Data informada é inválida, por favor siga o modelo (dia/mês/ano) Ex: 01/02/2000");
	    	}
    	}while(t);
    	HospedeReserva hr = new HospedeReserva(idReserva, cpf, datai, dataf);
    	
    	return hr;
    }
    public boolean data_valida(String data) {
    	if(data.length() < 10) {
    		return false;
    	}
    	return data.charAt(2) == '/' && data.charAt(5) == '/';
    }    
    public Produto createProduto() {
    	System.out.println("### PARA CANCELAR DIGITE #sair");
    	int id = 0;
    	String ids;
    	do {
    		System.out.print("Digite o id do produto: ");
    		Scanner id_produto = new Scanner(System.in);
    		ids = id_produto.nextLine();
    		if(ids.equals("#sair")) {
            	Produto p = null;
            	return p;
            }
    		if(ids.trim().isEmpty()) {
    			System.out.println("Fail::>O id do produto não pode ser vazio!");
    		}else {
    			id = Integer.parseInt(ids);
    		}
    	}while(ids.trim().isEmpty());
    	
    	float preco;
    	String precos;
    	do {
    		System.out.print("Digite o preço do produto: ");
    		Scanner precoscan = new Scanner(System.in);
    		precos = precoscan.nextLine();
    		if(precos.equals("#sair")) {
            	Produto p = null;
            	return p;
            }
    		if(precos.trim().isEmpty()) {
    			preco = 0;
    		}else {
    			preco = Float.parseFloat(precos);
    		}
    		if(preco < 0){
    			System.out.println("Fail::>O preco não pode ser negativo!");
    		}
    	}while(preco < 0);
    	
    	String nome;
    	do {
    		System.out.print("Digite o nome do produto: ");
    		Scanner nomescan = new Scanner(System.in);
    		nome = nomescan.nextLine();
    		if(nome.equals("#sair")) {
            	Produto p = null;
            	return p;
            }
    		if(nome.trim().isEmpty()) {
    			System.out.println("Fail::>O nome do produto não pode ser vazio!");
    		}
    	}while(nome.trim().isEmpty());
    	
    	return new Produto(id, preco, nome);
    }

    public Quarto createQuarto() {
    	System.out.println("### PARA CANCELAR DIGITE #sair");
    	int numero = 0;
    	String numerostr;
    	do {
    		System.out.print("Digite o número do quarto: ");
    		Scanner numeroscan = new Scanner(System.in);
    		numerostr = numeroscan.nextLine();
    		if(numerostr.equals("#sair")) {
            	Quarto q = null;
            	return q;
            }
    		if(numerostr.trim().isEmpty()) {
    			System.out.println("Fail::>O número do quarto não pode ser vazio!");
    		}else {
    			numero = Integer.parseInt(numerostr);
    		}
    	}while(numerostr.trim().isEmpty());
    
    	double diaria;
    	String diarias;
    	do {
    		System.out.print("Digite a diária do quarto: ");
    		Scanner diariascan = new Scanner(System.in);
    		diarias = diariascan.nextLine();
    		if(diarias.equals("#sair")) {
            	Quarto q = null;
            	return q;
            }
    		if(diarias.trim().isEmpty()) {
    			diaria = 0;
    		}else {
    			diaria = Double.parseDouble(diarias);
    		}
    		if(diaria < 0){
    			System.out.println("Fail::>A diária não pode ser negativa!");
    		}
    	}while(diaria < 0);
    	
    	int capacidade;
    	String capacidades;
    	do {
    		System.out.print("Digite a capacidade do quarto: ");
    		Scanner capacidadescan = new Scanner(System.in);
    		capacidades = capacidadescan.nextLine();
    		if(capacidades.equals("#sair")) {
            	Quarto q = null;
            	return q;
            }
    		if(capacidades.trim().isEmpty()) {
    			capacidade = 0;
    		}else {
    			capacidade = Integer.parseInt(capacidades);
    		}
    		if(capacidade < 0){
    			System.out.println("Fail::>A capacidade não pode ser negativa!");
    		}
    	}while(capacidade < 0);
    	
    	String descricao;
    	System.out.print("Digite a descrição do quarto: ");
    	Scanner descricaoscan = new Scanner(System.in);
    	descricao = descricaoscan.nextLine();
    	if(descricao.equals("#sair")) {
        	Quarto q = null;
        	return q;
        }
    	
    	return new Quarto(numero, diaria, capacidade, descricao);
    }

    public ReservaProduto createReservaProduto() {
    	System.out.println("### PARA CANCELAR DIGITE #sair");
    	int id = 0;
    	String idstr;
    	do {
    		System.out.print("Digite o id da reserva: ");
    		Scanner idscan = new Scanner(System.in);
    		idstr = idscan.nextLine();
    		if(idstr.equals("#sair")) {
            	ReservaProduto rp = null;
            	return rp;
            }
    		if(idstr.trim().isEmpty()) {
    			System.out.println("Fail::>O id da reserva não pode ser vazio!");
    		}else {
    			id = Integer.parseInt(idstr);
    		}
    	}while(idstr.trim().isEmpty());
    
    	String data;
    	boolean v;
    	do {
    		System.out.print("Digite a data em que está sendo comprado o produto: ");
    		Scanner datascan = new Scanner(System.in);
    		data = datascan.nextLine();
    		if(data.equals("#sair")) {
            	ReservaProduto rp = null;
            	return rp;
            }
    		v = data_valida(data); 
    		if(!v) {
    			System.out.println("Fail::>A data informada é inválida!");
    		}
    	}while(!v);
    	
    	String cpf;
    	do {
    		System.out.print("Digite o cpf do hóspede que está comprando o produto: ");
    		Scanner cpfscan = new Scanner(System.in);
    		cpf = cpfscan.nextLine();
    		if(cpf.equals("#sair")) {
            	ReservaProduto rp = null;
            	return rp;
            }
    		if(cpf.trim().isEmpty()) {
    			System.out.println("Fail::>O cpf do hóspede não pode ser vazio!");
    		}
    	}while(cpf.trim().isEmpty());
    	
    	int id_produto = 0;
    	String id_produtostr;
    	do {
    		System.out.print("Digite o id do produto: ");
    		Scanner id_produtoscan = new Scanner(System.in);
    		id_produtostr = id_produtoscan.nextLine();
    		if(id_produtostr.equals("#sair")) {
            	ReservaProduto rp = null;
            	return rp;
            }
    		if(id_produtostr.trim().isEmpty()) {
    			System.out.println("Fail::>O id do produto não pode ser vazio!");
    		}else {
    			id_produto = Integer.parseInt(id_produtostr);
    		}
    	}while(id_produtostr.trim().isEmpty());
    	
    	return new ReservaProduto(id, data, cpf, id_produto);
    }
    
    public ReservaServico createReservaServico() {
    	System.out.println("### PARA CANCELAR DIGITE #sair");
    	int id = 0;
    	String idstr;
    	do {
    		System.out.print("Digite o id da reserva: ");
    		Scanner idscan = new Scanner(System.in);
    		idstr = idscan.nextLine();
    		if(idstr.equals("#sair")) {
            	ReservaServico rp = null;
            	return rp;
            }
    		if(idstr.trim().isEmpty()) {
    			System.out.println("Fail::>O id da reserva não pode ser vazio!");
    		}else {
    			id = Integer.parseInt(idstr);
    		}
    	}while(idstr.trim().isEmpty());
    	
    	String data;
    	boolean v;
    	do {
    		System.out.print("Digite a data em que está sendo comprado o produto: ");
    		Scanner datascan = new Scanner(System.in);
    		data = datascan.nextLine();
    		if(data.equals("#sair")) {
            	ReservaServico rp = null;
            	return rp;
            }
    		v = data_valida(data); 
    		if(!v) {
    			System.out.println("Fail::>A data informada é inválida!");
    		}
    	}while(!v);
    	
    	String cpf;
    	do {
    		System.out.print("Digite o cpf do hóspede que está comprando o produto: ");
    		Scanner cpfscan = new Scanner(System.in);
    		cpf = cpfscan.nextLine();
    		if(cpf.equals("#sair")) {
            	ReservaServico rp = null;
            	return rp;
            }
    		if(cpf.trim().isEmpty()) {
    			System.out.println("Fail::>O cpf do hóspede não pode ser vazio!");
    		}
    	}while(cpf.trim().isEmpty());
    	
    	int id_servico = 0;
    	String id_servicostr;
    	do {
    		System.out.print("Digite o id do servico: ");
    		Scanner id_servicoscan = new Scanner(System.in);
    		id_servicostr = id_servicoscan.nextLine();
    		if(id_servicostr.equals("#sair")) {
            	ReservaServico rp = null;
            	return rp;
            }
    		if(id_servicostr.trim().isEmpty()) {
    			System.out.println("Fail::>O id do servico não pode ser vazio!");
    		}else {
    			id_servico = Integer.parseInt(id_servicostr);
    		}
    	}while(id_servicostr.trim().isEmpty());
    	
    	return new ReservaServico(id, data, cpf, id_servico);
    }
    public Servico createServico() {
    	System.out.println("### PARA CANCELAR DIGITE #sair");
    	int id_servico = 0;
    	String id_servicostr;
    	do {
    		System.out.print("Digite o id do serviço: ");
    		Scanner id_servicoscan = new Scanner(System.in);
    		id_servicostr = id_servicoscan.nextLine();
    		if(id_servicostr.equals("#sair")) {
            	Servico rp = null;
            	return rp;
            }
    		if(id_servicostr.trim().isEmpty()) {
    			System.out.println("Fail::>O id do serviço não pode ser vazio!");
    		}else {
    			id_servico = Integer.parseInt(id_servicostr);
    		}
    	}while(id_servicostr.trim().isEmpty());
    	
    	double preco;
    	String precos;
    	do {
    		System.out.print("Digite o preço do serviço: ");
    		Scanner precoscan = new Scanner(System.in);
    		precos = precoscan.nextLine();
    		if(precos.equals("#sair")) {
            	Servico rp = null;
            	return rp;
            }
    		if(precos.trim().isEmpty()) {
    			preco = 0;
    		}else {
    			preco = Double.parseDouble(precos);
    		}
    		if(preco < 0){
    			System.out.println("Fail::>O preco não pode ser negativo!");
    		}
    	}while(preco < 0);
    	
    	String descricao;
    	System.out.print("Digite a descrição do serviço: ");
    	Scanner descricaoscan = new Scanner(System.in);
    	descricao = descricaoscan.nextLine();
    	if(descricao.equals("#sair")) {
        	Servico rp = null;
        	return rp;
        }
    	
    	return new Servico(id_servico,preco,descricao);
    }
}