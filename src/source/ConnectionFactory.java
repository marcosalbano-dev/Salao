package source;

/**
 * ConnectionFactory
 * Classe para conexão com Banco de Dados  utilizando PostgreeSQL
 * Autor: Marcus Vinicius de Oliveira Mesquita
 * 
 */

import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;

public class ConnectionFactory{
	final private String url = "jdbc:postgresql://localhost:5432/salao";
	final private String usuario = "postgres";
	final private String senha = "ua79oapq";
	
	public Connection conexao;
	
	//Inicia a conexão ao Banco de Dados
	public boolean getConnection(){
		try{
			//Carregando a classe JDBC
			Class.forName("org.postgresql.Driver");
			//Criando a conexão entre Java e PostgreSQL
			this.conexao = DriverManager.getConnection(url, usuario, senha);
			return true;
		}
		catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null, "Erro na calsse:"+e.getMessage());
			return false;
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Erro SQL:"+e.getMessage());
			return false;
		}
	}
	
	//Fecha a conexão ao Banco de Dados
	public void closeConnection() throws SQLException{
		this.conexao.close();
	}
	
	//Adiciona dados ao Banco de Dados
	public void insertDada() throws SQLException{
		this.getConnection();
		try{
			PreparedStatement adicionarVeiculo = this.conexao.prepareStatement("INSERT INTO PATIO VALUES(?,?,?)");
			adicionarVeiculo.setString(1,v.getModelo());
			adicionarVeiculo.setInt(2, v.getAno());
			adicionarVeiculo.setDouble(3, v.getPreco());
			adicionarVeiculo.executeUpdate();
			JOptionPane.showMessageDialog(null, "Veículo registrado com sucesso");
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o veiculo. Erro:"+e.getMessage());
		}
		this.closeConnection();
	}
	
	//Pesquisa no Banco de Dados e retorna um dado
	
	
}

