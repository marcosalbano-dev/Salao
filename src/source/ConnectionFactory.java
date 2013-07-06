package source;

/**
 * ConnectionFactory
 * Classe para conexão com Banco de Dados  utilizando PostgreeSQL
 * Autor: Marcus Vinicius de Oliveira Mesquita
 * 
 */

import java.sql.*;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;

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
	
	//Adiciona serviçoes na tabela tab_servicos
	public void insereServico(Servico novoServico) throws SQLException{
		
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
		
		this.closeConnection();
	}
	
	//Adiciona agendamentos na tabela tab_agenda
	public void insereAgendamento(Agenda novoAgendamento) throws SQLException{
		
		this.getConnection();
		
		try{
			PreparedStatement adicionarAgendamento = this.conexao.prepareStatement("INSERT INTO tab_agenda VALUES(?,?,?,?)");
			adicionarAgendamento.setString(1, novoAgendamento.getS_nomeCliente());
			adicionarAgendamento.setString(2, novoAgendamento.getS_nomeServico());
			adicionarAgendamento.setString(3, novoAgendamento.getS_data());
			adicionarAgendamento.setString(4, novoAgendamento.getS_hora());
			
			adicionarAgendamento.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Agendamento feito com sucesso!");
		} 
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,"Não foi possível fazer o agendamento. Erro:"+e.getMessage());
		}	
		
		this.closeConnection();
	}
	
	//Adiciona atendimentos na tabela tab_atendimento
	public void insereAtendimento(Atende novoAtendimento) throws SQLException{
		
		this.getConnection();
		
		try{
			PreparedStatement adicionarAtendimento = this.conexao.prepareStatement("INSERT INTO tab_atendimento VALUES(?,?,?)");
			adicionarAtendimento.setString(1, novoAtendimento.getS_nomeCliente());
			adicionarAtendimento.setString(2, novoAtendimento.getS_nomeServico());
			adicionarAtendimento.setDouble(3, novoAtendimento.getD_preco());
		
			
			adicionarAtendimento.executeUpdate();
		} 
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,"Não foi possível registrar o atendimento. Erro:"+e.getMessage());
		}	
		
		this.closeConnection();
	}
	
	//Lista todos clientes registrados no BD
	public List<Cliente> clientesToList() throws SQLException{
		
		this.getConnection();
		
		PreparedStatement pesquisaClientes = this.conexao.prepareStatement("SELECT * FROM tab_clientes");
		ResultSet resultadoPesquisa = pesquisaClientes.executeQuery();
		
		List<Cliente> Clientes = new ArrayList<Cliente>();
		
		while(resultadoPesquisa.next()){
			
			Cliente clienteObtido = new Cliente();
			
			clienteObtido.setS_nomeCliente(resultadoPesquisa.getString("nome_cliente"));
			clienteObtido.setS_endereco(resultadoPesquisa.getString("endereco"));
			clienteObtido.setS_sexo(resultadoPesquisa.getString("sexo"));
			clienteObtido.setI_telefoneFixo(resultadoPesquisa.getInt("telefone"));
			clienteObtido.setI_telefoneCelular(resultadoPesquisa.getInt("celular"));
			
			Clientes.add(clienteObtido);
		}
		
		resultadoPesquisa.close();
		pesquisaClientes.close();
		this.closeConnection();
		
		return Clientes;
	}
	
	//Lista todos os servicos registrados no BD
	public List<Servico> servicosToList() throws SQLException{
		
		this.getConnection();
		
		PreparedStatement pesquisaServicos = this.conexao.prepareStatement("SELECT * FROM tab_servicos");
		ResultSet resultadoPesquisa = pesquisaServicos.executeQuery();
		
		List<Servico> Servicos = new ArrayList<Servico>();
		
		while(resultadoPesquisa.next()){
			
			Servico servicoObtido = new Servico();
			
			servicoObtido.setS_nomeServico(resultadoPesquisa.getString("nome_servico"));
			servicoObtido.setD_preco(resultadoPesquisa.getDouble("preco"));
			
			Servicos.add(servicoObtido);
		}
		
		resultadoPesquisa.close();
		pesquisaServicos.close();
		this.closeConnection();
		
		return Servicos;
	}
	
	//Lista de todos os agendamentos no BD
	public List<Agenda> agendaToList() throws SQLException{
	
		this.getConnection();
		
		PreparedStatement pesquisaAgenda = this.conexao.prepareStatement("SELECT * FROM tab_agenda");
		ResultSet resultadoPesquisa = pesquisaAgenda.executeQuery();
		
		List<Agenda> Agendamentos = new ArrayList<Agenda>();
		
		while(resultadoPesquisa.next()){
			
			Agenda agendamentosObtidos = new Agenda();
			
			agendamentosObtidos.setS_nomeCliente(resultadoPesquisa.getString("nome_cliente"));
			agendamentosObtidos.setS_nomeServico(resultadoPesquisa.getString("nome_servico"));
			agendamentosObtidos.setS_data(resultadoPesquisa.getString("dia"));
			agendamentosObtidos.setS_hora(resultadoPesquisa.getString("hora"));
			
			Agendamentos.add(agendamentosObtidos);
		}
		
		resultadoPesquisa.close();
		pesquisaAgenda.close();
		this.closeConnection();
		
		return Agendamentos;
	}

	//Lista de todos os atendimentos no BD
	public List<Atende> atendimentosToList() throws SQLException{
		
		this.getConnection();
		
		PreparedStatement pesquisaAtendimentos = this.conexao.prepareStatement("SELECT * FROM tab_atendimento");
		ResultSet resultadoPesquisa = pesquisaAtendimentos.executeQuery();
		
		List<Atende> Atendimentos = new ArrayList<Atende>();
		
		while(resultadoPesquisa.next()){
			
			Atende atendimentoObtido = new Atende();
			
			atendimentoObtido.setS_nomeCliente(resultadoPesquisa.getString("nome_cliente"));
			atendimentoObtido.setS_nomeServico(resultadoPesquisa.getString("nome_servico"));
			atendimentoObtido.setD_preco(resultadoPesquisa.getDouble("preco"));
			
			Atendimentos.add(atendimentoObtido);
		}
		
		resultadoPesquisa.close();
		pesquisaAtendimentos.close();
		this.closeConnection();
		
		return Atendimentos;
	}
}

