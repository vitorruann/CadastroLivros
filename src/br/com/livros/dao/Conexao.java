package br.com.livros.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexao() {
        Connection conexao = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cadastrolivros", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
	    System.err.println("Erro ao carregar driver");
        } catch (SQLException e) {
            e.printStackTrace();
	    System.err.println("Erro ao conectar no banco de dados");
        }
        
        return conexao;
    }
}
