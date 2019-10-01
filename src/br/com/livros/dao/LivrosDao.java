package br.com.livros.dao;

import br.com.livros.model.Livros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LivrosDao {

    public boolean cadastrarLivros(Livros e) {
        int resultado = 0;
        Connection con = Conexao.getConexao();
        String sql = "INSERT INTO livros (nome) VALUES (?)";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, e.getNome());
            resultado = stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso");
            
            listarLivros();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Livro NÃO castrado");
            throw new RuntimeException("Erro na conexão com o banco");
        }
        return resultado > 0;
    }

    public List<Livros> listarLivros() {
        List<Livros> lista = new ArrayList<Livros>();
        
        Connection con = Conexao.getConexao();

        PreparedStatement stm = null;
        try {
            String sql = "SELECT * FROM livros";
            stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(sql);
            
            while (rs.next()) {
                Livros livro = new Livros();
                
                livro.setId(rs.getInt("id"));
                livro.setNome(rs.getString("nome"));
                
                lista.add(livro);
                

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar livros");
            throw new RuntimeException("Erro na conexão com o banco");
        }
        return lista;
    }
    
    public boolean excluirLivro(int id){
        int resultado = 0;
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM livros WHERE id = ?";
        
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1,id);
            
            resultado = stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Livro Excluido com sucesso");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir livros");
            throw new RuntimeException("Erro na conexão com o banco");
        }
        return resultado > 0;
    }
    
}
