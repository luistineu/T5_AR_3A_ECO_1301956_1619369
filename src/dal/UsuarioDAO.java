package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.BCrypt;
import model.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO {
	
	public static boolean CadastrarUsuario(Usuario u) {
		
		try {
			ConnectionFactory.testConnection();
			
			String sql = "INSERT INTO usuarios (NOME, SOBRENOME, DATA_NASCIMENTO, SEXO, EMAIL, LOGIN, SENHA) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql);
			statement.setString(1, u.getNome());
			statement.setString(2, u.getSobrenome());
			statement.setDate(3, u.getNascimento());
			statement.setString(4, u.getSexo());
			statement.setString(5, u.getEmail());
			statement.setString(6, u.getLogin());
			statement.setString(7, BCrypt.hashpw(u.getSenha(), BCrypt.gensalt(1, null)));
			
			
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public static boolean ConsultaLogin(String login) {
		try {
			String sql = "SELECT * FROM usuarios WHERE LOGIN = ?";
			PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql);
			statement.setString(1, login);
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	public static boolean Login(Usuario u) {
		return false;
	}
	
}
