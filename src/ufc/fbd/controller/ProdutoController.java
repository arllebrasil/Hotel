package ufc.fbd.controller;

import java.util.ArrayList;
import java.util.Scanner;

import ufc.fbd.DAO.ProdutoDAO;
import ufc.fbd.config.FbdConnection;
import ufc.fbd.model.Produto;

public class ProdutoController {
	private ProdutoDAO produtoDao = null;
	public ProdutoController() {
		this.produtoDao = new ProdutoDAO(new FbdConnection());
	}
	public void start() {
		boolean continuar = true;
		do {
			System.out.println("\n#################                    Controle de Produtos                    #################\n"
					+ "\n1- Listar Produtos, 2- Registrar Produtos, 3- Alterar Produtos, 4 - Remover Produtos, ENTER para sair");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			
			switch (option) {
			case "1":
				System.out.println("\n#################          Listar Produtos          #################\n");
				this.option1();
				break;
			case "2":
				System.out.println("\n#################          Cadatrar Produtos          #################\n");
				this.option2();
				break;
			case "3":
				System.out.println("\n#################          Alterar Produtos          #################\n");
				this.option3();
				break;
			case "4":
				System.out.println("\n#################          Remover Produtos          #################\n");
				this.option4();
				break;
			case "":
				continuar = false;
				System.out.println("################                     .................                    ################\n");
				break;
			default:
				break;
			}
		} while (continuar);
	}
	public void option1() {
		// TODO Auto-generated method stub
		ArrayList<Produto> allProduto = this.produtoDao.find();
		for (Produto produto : allProduto) {
			System.out.println(produto.toString());
		}
		
	}
	public void option2() {
		// TODO Auto-generated method stub
		Input input = new Input();
		System.out.println("Informe os dados do produto que deseja Registrar!");
		Produto newProduto = input.createProduto();
		if(newProduto != null) {
			int response = this.produtoDao.create(newProduto);
			System.out.println((response >= 1)?response+" produtos registrados!":"Um erro ocorreu, operação cancelada...");
		}else {
			System.out.println("Operação cancelada...");
		}
	}
	public void option3() {
		// TODO Auto-generated method stub
		Input input = new Input();
		
		System.out.println("Informe os dados (id_Produto) do produto que deseja Alterar!");
		Scanner scanner = new Scanner(System.in);
		String produtoId = scanner.nextLine();
		
		int oldId = Integer.parseInt(produtoId);
		Produto oldProduto = produtoDao.findOne(oldId);
		
		if(oldProduto != null) {
			System.out.println(oldProduto.toString()+" encontrado!\nInforme os novos dados para alteração. Obs: Mantenha id_Produto!");
			oldProduto = input.createProduto();
			System.out.println(oldProduto+" Deseja confirmar a atualização S/N?");
			
			String option = scanner.nextLine();
			if(option.equals("s")||option.equals("S")) {
				int response = this.produtoDao.update(oldProduto);
				System.out.println((response >= 1)?response+" produtos alterados!":"Um erro ocorreu, operação cancelada...");
			}else {
				System.out.println("Operação cancelada...");
			}	
		}else {
			System.out.println("Nenhum Produto com id = "+oldId+"foi encontrado. Operação cancelada...");
		}
	}
	public void option4() {
		// TODO Auto-generated method stub
Input input = new Input();
		
		System.out.println("Informe os dados do produto que deseja Remover!");
		Scanner scanner = new Scanner(System.in);
		String produtoId = scanner.nextLine();
		
		int oldId = Integer.parseInt(produtoId);
		Produto oldProduto = produtoDao.findOne(oldId);
		
		if(oldProduto != null) {
			System.out.println(oldProduto+" Deseja confirmar a remoção S/N?");
			String option = scanner.nextLine();
			
			if(option.equals("s")||option.equals("S")) {
				int response = this.produtoDao.delete(oldProduto);
				System.out.println((response >= 1)?response+" produtos removidos!":"Um erro ocorreu, operação cancelada...");
			}else {
				System.out.println("Operação cancelada...");
			}	
		}else {
			System.out.println("Nenhum Produto com id = "+oldId+"foi encontrado. Operação cancelada...");
		}
		
	}

}
