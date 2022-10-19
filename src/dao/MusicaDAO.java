package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Musica;

public class MusicaDAO {
	private Connection connection;
	
	public MusicaDAO() {
		connection = new FabricaConexoes().getConnection();
	}
	
	public int inserir(Musica m) {
		int inseriu = 0;
		String sql = "INSERT INTO Musica VALUES (?, ?, ?, ?, ?);";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, m.getCodigo());
			stmt.setString(2, m.getNome());
			stmt.setTime(3, m.getDuracao());
			stmt.setInt(4, m.getAno());
			stmt.setString(5, m.getCreditos());
			inseriu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}
	
	public int remover(Musica m) {
		int removeu = 0;
		String sql = "DELETE FROM Musica WHERE codigo = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, m.getCodigo());
			removeu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}
	
	public int alterar(Musica m) {
		int alterou = 0;
		String sql = "UPDATE Musica SET nome = ?, duracao = ?, ano = ?, creditos = ? WHERE codigo = ?;";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, m.getNome());
			stmt.setTime(2, m.getDuracao());
			stmt.setInt(3, m.getAno());
			stmt.setString(4, m.getCreditos());
			stmt.setInt(5, m.getCodigo());
			alterou = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alterou;
	}
	
	public ArrayList<Musica> getMusicas() {
		String sql = "SELECT * FROM Musica";
		PreparedStatement stmt;
		Musica m;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Musica> musicas = new ArrayList<>();
			while (rs.next()) {
				m = new Musica();
				m.setNome(rs.getString("nome"));
				m.setDuracao(rs.getTime("duracao"));
				m.setAno(rs.getInt("ano"));
				m.setCreditos(rs.getString("nome"));
				musicas.add(m);
			}
			stmt.close();
			rs.close();
			return musicas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Integer> getCodigos() {
		String sql = "SELECT codigo FROM Musica";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Integer> codigos = new ArrayList<>();
			while (rs.next()) {
				codigos.add(rs.getInt("codigo"));
			}
			rs.close();
			stmt.close();
			return codigos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
