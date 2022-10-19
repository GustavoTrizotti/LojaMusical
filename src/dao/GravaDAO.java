package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.Grava;

public class GravaDAO {
	Connection connection;
	
	public GravaDAO() {
		connection = new FabricaConexoes().getConnection();
	}
	
	// Note: maybe the code doesn't compile cause there's a difference between the java's 'Date' and MySQL 'Date' type;
	// in this case, I'm using the java's 'Date' type in the parameter;
	
	// Note 2: change manually the data types from java's to MySQL in DAO;
	
	public int gravar(Grava g) {
		int gravou = 0;
		String sql = "INSERT INTO Grava VALUES (?, ?, ?, ?, ?);";
		PreparedStatement stmt;
		try {
			stmt = (PreparedStatement) connection.prepareStatement(sql);
			stmt.setDate(1, g.getData_gravacao());
			stmt.setString(2, g.getCpf_artista());
			stmt.setInt(3, g.getCodigo_produto());
			stmt.setInt(4, g.getCodigo_musica());
			stmt.setString(5, g.getEstilo());
			gravou = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gravou;
	}
}
