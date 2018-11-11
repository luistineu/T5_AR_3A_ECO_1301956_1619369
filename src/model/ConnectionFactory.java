package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection INSTANCE = null;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);// devemos logar esse erro
		}
	}

	private ConnectionFactory() {

	}

	public static Connection getConnection() {
		if (INSTANCE == null) {
			try {
				INSTANCE = DriverManager.getConnection("jdbc:mysql://localhost/sistema_login", "root", "");
			} catch (SQLException e) {
				throw new RuntimeException(e);// devemos logar esse erro
			}
		}
		return INSTANCE;
	}

	public static Connection testConnection() {
		try {

			// Carregando o JDBC Driver padr�o

			Connection connection = ConnectionFactory.getConnection();

			String status = "";

			String driverName = "com.mysql.jdbc.Driver";

			Class.forName(driverName);

			// Configurando a nossa conex�o com um banco de dados//

			String serverName = "localhost"; // caminho do servidor do BD

			String mydatabase = "mysql"; // nome do seu banco de dados

			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

			String username = "root"; // nome de um usu�rio de seu BD

			String password = ""; // sua senha de acesso

			connection = DriverManager.getConnection(url, username, password);

			// Testa sua conex�o//

			if (connection != null) {

				status = ("STATUS--->Conectado com sucesso!");

			} else {

				status = ("STATUS--->N�o foi possivel realizar conex�o");

			}
			System.out.println(status);

			return connection;

		} catch (ClassNotFoundException e) { // Driver n�o encontrado

			System.out.println("O driver expecificado nao foi encontrado.");

			return null;

		} catch (SQLException e) {

			// N�o conseguindo se conectar ao banco

			System.out.println("Nao foi possivel conectar ao Banco de Dados.");

			return null;
		}
	}
}
