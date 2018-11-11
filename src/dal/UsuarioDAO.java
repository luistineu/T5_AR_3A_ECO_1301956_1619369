package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import controller.BCrypt;
import model.ConnectionFactory;
import model.Usuario;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class UsuarioDAO {
	
	public static boolean CadastrarUsuario(Usuario u) throws SQLException {
		
		try {
			//ConnectionFactory.testConnection();
			
			String sql = "INSERT INTO usuarios (NOME, SOBRENOME, SEXO, EMAIL, LOGIN, SENHA) VALUES (?,?,?,?,?,?)";
			PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql);
			statement.setString(1, u.getNome());
			statement.setString(2, u.getSobrenome());
			statement.setString(3, u.getSexo());
			statement.setString(4, u.getEmail());
			statement.setString(5, u.getLogin());
			statement.setString(6, BCrypt.hashpw(u.getSenha(), BCrypt.gensalt(10)));
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
	
	public static boolean ConsultaLogin(String login) throws SQLException {
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
		} finally {
			ConnectionFactory.getConnection().close();
		}
		
		
		return false;
	}
	
	public static boolean Logar(String senha, String login) {
		try {
			
			if(UsuarioDAO.BuscarUsuario(login) != null) {
				if (BCrypt.checkpw(senha, UsuarioDAO.BuscarUsuario(login).getSenha())) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static Usuario BuscarUsuario(String login) throws SQLException {
		try {
			String sql = "SELECT * FROM usuarios WHERE LOGIN = ?";
			PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql);
			statement.setString(1, login);
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSexo(rs.getString("sexo"));
				usuario.setSenha(rs.getString("senha"));
				
				return usuario;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.getConnection().close();
		}
		
		
		return null;
	}
	
}
