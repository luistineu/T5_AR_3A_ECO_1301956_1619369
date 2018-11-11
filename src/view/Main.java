package view;

import java.util.Date;
import java.io.Console;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dal.UsuarioDAO;
import model.ConnectionFactory;
import model.Usuario;

public class Main {

	public static void main(String[] args) throws ParseException, SQLException {
		String op = "";
		Usuario u = new Usuario();
		Scanner sc = new Scanner(System.in);
		
		while(!op.equals("0")) {
			System.out.println("\n\nSISTEMA DE LOGIN");
			System.out.println("1 - CADASTRAR USUÁRIO");
			System.out.println("2 - FAZER LOGIN");
			System.out.println("0 - SAIR  ");
			
			op = sc.nextLine();
			
			switch(op) {
				case "1":
					System.out.println("-- CADASTRO DE USUÁRIO --");
					u = new Usuario();
							
					System.out.println("Nome:");
					u.setNome(sc.nextLine());
					System.out.println("Sobrenome:");
					u.setSobrenome(sc.nextLine());
					System.out.println("Sexo:");
					u.setSexo(sc.nextLine());
					System.out.println("E-mail:");
					u.setEmail(sc.nextLine());
					System.out.println("Login:");
					String login = sc.nextLine();
					while(u.getLogin() == null) {
						if(!UsuarioDAO.ConsultaLogin(login)) {
							u.setLogin(login);
						} else {
							System.out.println("Esse login já existe!");
							System.out.println("Insira outro login:");
							login = sc.nextLine();
						}
					}
					System.out.println("Senha:");
					u.setSenha(sc.nextLine());
					
					if(UsuarioDAO.CadastrarUsuario(u)) {
						System.out.println("Usuário cadastrado com sucesso!");
					}
					else {
						System.out.println("Não foi possível cadastrar o usuário!");
					}
					
					break;
					
				case "2":
					System.out.println("-- LOGIN --");
					System.out.println("Login:");
					String usuarioLogin = sc.nextLine();
					System.out.println("Senha:");
					String senha = sc.nextLine();
					if(UsuarioDAO.Logar(senha, usuarioLogin)) {
						Usuario uLogado = UsuarioDAO.BuscarUsuario(usuarioLogin);
						System.out.println("\n--BEM VINDO!--");
						System.out.println("\n--INFORMAÇÕES DO USUÁRIO--");
						System.out.println("Nome: " + uLogado.getNome());
						System.out.println("Sobrenome: " + uLogado.getSobrenome());
						System.out.println("E-mail: " + uLogado.getEmail());
						System.out.println("Sexo: " + uLogado.getSexo());
					}
					else {
						System.out.println("Usuário e/ou senha incorretos!");
					}
					break;
				case "0":
					System.out.println("Saindo...");
					op = "0";
					break;
				default:
					System.out.println("Opção Inválida!");
					break;
			}
		}
		
	}

}
