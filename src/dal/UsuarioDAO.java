package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.BCrypt;
import model.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO {
	
	public static boolean CadastrarUsuario(Usuario u) throws SQLException {
		
		try {
			ConnectionFactory.testConnection();
			
			String sql = "INSERT INTO usuarios (NOME, SOBRENOME, SEXO, EMAIL, LOGIN, SENHA) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql);
			statement.setString(1, u.getNome());
			statement.setString(2, u.getSobrenome());
			statement.setString(3, u.getSexo());
			statement.setString(4, u.getEmail());
			statement.setString(5, u.getLogin());
			statement.setString(6, "banana");
			java.lang.String salt = "aspdokasopd";
			//System.out.println(BCrypt.hashpw(u.getSenha(), salt));
			statement.execute();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.getConnection().close();
		}
		
		
		return false;
	}
	
	public static boolean ConsultaLogin(String login) {
		try {
			String sql = "SELECT * FROM usuarios WHERE LOGIN = ?";
			PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql);
			statement.setString(1, login);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString("login"));
				return true;
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	public static boolean Login(Usuario u) {
		return false;
	}
	
}
