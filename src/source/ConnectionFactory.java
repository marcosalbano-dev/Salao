package source;

/**
 * ConnectionFactory
 * Classe para conexão com Banco de Dados  utilizando PostgreeSQL
 * Autor: Marcus Vinicius de Oliveira Mesquita
 * 
 */

import java.sql.*;

import javax.swing.JOptionPane;

public class ConnectionFactory{
	final private String url = "jdbc:postgresql://localhost:5432/postgres";
	final private String usuario = "salao_adm";
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
	
	//Adiciona Clientes na tabela tab_clientes
	public void insereCliente(Cliente novoCliente) throws SQLException{
		
		this.getConnection();
		
		try{
			PreparedStatement adicionarCliente = this.conexao.prepareStatement("INSERT INTO tab_clientes VALUES(?,?,?,?,?)");
			adicionarCliente.setString(1,novoCliente.getS_nomeCliente());
			adicionarCliente.setString(2, novoCliente.getS_endereco());
			adicionarCliente.setString(3, novoCliente.getS_sexo());
			adicionarCliente.setInt(4, novoCliente.getI_telefoneFixo());
			adicionarCliente.setInt(5, novoCliente.getI_telefoneCelular());
			
			adicionarCliente.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Cliente registrado com sucesso!");
		} 
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o Cliente. Erro:"+e.getMessage());
		}
		this.closeConnection();
	}
	
	public void insereServico(Servico novoServico){
		
		this.getConnection();
		
		try{
			PreparedStatement adicionarServico = this.conexao.prepareStatement("INSERT INTO tab_servicos VALUES(?,?)");
			adicionarServico.setString(1, novoServico.getS_nomeServico());
			adicionarServico.setDouble(2, novoServico.getD_preco());
			
			adicionarServico.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Serviço registrado com sucesso!");
		} 
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,"Não foi possível cadastrar o Serviço. Erro:"+e.getMessage());
		}		
	}
}

