package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Album;

public class AlbumDAO {
	private Connection connection;
	
	public AlbumDAO() {
		connection = new FabricaConexoes().getConnection();
	}
	
	public int inserir(Album a) {
		int inseriu = 0;
		PreparedStatement stmt;
		String sql = "INSERT INTO Album (codigo_produto, nome, estilo, ano, distribuidora, gravadora, lote) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, a.getCodigo_produto());
			stmt.setString(2, a.getNome());
			stmt.setString(3, a.getEstilo());
			stmt.setInt(4, a.getAno());
			stmt.setString(5, a.getDistribuidora());
			stmt.setString(6, a.getGravadora());
			stmt.setInt(7, a.getLote());
			inseriu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inseriu;
	}
	
	public int remover(Album a) {
		int removeu = 0;
		String sql = "DELETE FROM Album WHERE codigo_produto = ?";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setInt(1, a.getCodigo_produto());
			removeu = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return removeu;
	}
	
	public int alterar(Album a) {
		int alterou = 0;
		PreparedStatement stmt;
		String sql = "UPDATE Album SET nome=?, estilo=?, ano=?, distribuidora=?, gravadora=?, lote=? WHERE codigo_produto = ?";
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setString(1, a.getNome());
			stmt.setString(2, a.getEstilo());
			stmt.setInt(3, a.getAno());
			stmt.setString(4, a.getDistribuidora());
			stmt.setString(5, a.getGravadora());
			stmt.setInt(6, a.getLote());
			stmt.setInt(7, a.getCodigo_produto());
			alterou = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alterou;
	}
	
	public ArrayList<Album> getAlbuns() {
		String sql = "SELECT * FROM Album";
		PreparedStatement stmt;
		Album a;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Album> albuns = new ArrayList<>();
			while (rs.next()) {
				a = new Album();
				a.setCodigo_produto(rs.getInt("codigo_produto"));
				a.setNome(rs.getString("nome"));
				a.setEstilo(rs.getString("estilo"));
				a.setAno(rs.getInt("ano"));
				a.setDistribuidora(rs.getString("distribuidora"));
				a.setGravadora(rs.getString("gravadora"));
				a.setLote(rs.getInt("lote"));
				albuns.add(a);
			}
			rs.close();
			stmt.close();
			return albuns;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Integer> getCodigos() {
		String sql = "SELECT codigo FROM Album";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Integer> codigos = new ArrayList<>();
			while (rs.next()) {
				codigos.add(rs.getInt("codigo_produto"));
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
