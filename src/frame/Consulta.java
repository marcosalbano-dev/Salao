package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import source.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Consulta extends JFrame{
	
	//Componentes do frame
	private JTable tablePrincipal;
	
	public Consulta(){
		
		//Config geral do Frame
		setTitle("Consulta de Clientes-Servi\u00E7os");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setBounds(100, 100, 540, 410);
		setLocationRelativeTo(null);
		
		//Label
		JLabel lblOQueDeseja = new JLabel("O que deseja consultar:");
		lblOQueDeseja.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOQueDeseja.setBounds(95, 14, 135, 14);
		getContentPane().add(lblOQueDeseja);
		
		//ComcboBox
		final JComboBox<String> cboxOpcoes = new JComboBox<String>();
		cboxOpcoes.setBounds(240, 11, 143, 20);
		getContentPane().add(cboxOpcoes);
		
		cboxOpcoes.addItem("-");
		cboxOpcoes.addItem("Serviços");
		cboxOpcoes.addItem("Clientes");
		cboxOpcoes.addItem("Atendimentos");
		
		//Panel Principal
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(189, 321, 178, 38);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Botão Consultar
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConnectionFactory pesquisaBD = new ConnectionFactory();
				
				if(cboxOpcoes.getSelectedItem().equals("Clientes")){
					
					List<Cliente> clientesObtidos = new ArrayList<Cliente>();
					
					try {
						
						clientesObtidos = pesquisaBD.clientesToList();
						consultaCliente(clientesObtidos);
						
					} catch (SQLException exc) {
						JOptionPane.showMessageDialog(null, "Não foi possível realizar a pesquisa." +exc.getMessage());
						
					}
					
				}
				else if(cboxOpcoes.getSelectedItem().equals("Serviços")){
					
					List<Servico> servicosObtidos = new ArrayList<Servico>();
					
					try{
						servicosObtidos = pesquisaBD.servicosToList();
						consultaServicos(servicosObtidos);
					} catch (SQLException exc){
						JOptionPane.showMessageDialog(null, "Não foi possível realizar a pesquisa." +exc.getMessage());
					}
				} 
				else if(cboxOpcoes.getSelectedItem().equals("Atendimentos")){
					
					List<Atende> atendimentosObtidos = new ArrayList<Atende>();
					
					try {
						atendimentosObtidos = pesquisaBD.atendimentosToList();
						consultaAtendimento(atendimentosObtidos);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Não foi possível realizar a pesquisa." +e1.getMessage());
					}
					
				}
				else if(cboxOpcoes.getSelectedItem().equals("-")){
					JOptionPane.showMessageDialog(null, "Selecione o que você dejesa vizualizar");
				}
				
			}
		});
		panel.add(btnConsultar);
		
		//Botão Sair
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		panel.add(btnVoltar);
		
		JScrollPane scpPrincipal = new JScrollPane();
		scpPrincipal.setBounds(20, 36, 494, 274);
		getContentPane().add(scpPrincipal);
		
		tablePrincipal = new JTable();
		scpPrincipal.setViewportView(tablePrincipal);
		
	}
	
	//Popula a tabela com Clientes
	public void consultaCliente(List<Cliente> clientes){
		
		String[] colunas = {"Nome do Cliente","Endereço","Sexo","Telefone","Celular"};
		String[][] dados = new String[clientes.size()][5];

		int contador = 0;
		
		for(Cliente c : clientes){
			
			dados[contador][0] = c.getS_nomeCliente();
			dados[contador][1] = c.getS_endereco();
			dados[contador][2] = c.getS_sexo();
			dados[contador][3] = "" + c.getI_telefoneFixo();
			dados[contador][4] = "" + c.getI_telefoneCelular();
			
			contador ++;
		}
		
		tablePrincipal.setModel(new DefaultTableModel(dados, colunas));
		tablePrincipal.getColumnModel().getColumn(0).setPreferredWidth(300);
		tablePrincipal.getColumnModel().getColumn(1).setPreferredWidth(300);
		tablePrincipal.getColumnModel().getColumn(2).setPreferredWidth(200);
		tablePrincipal.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablePrincipal.getColumnModel().getColumn(4).setPreferredWidth(200);
	}
	
	//Popula a tabela com Servicos
	public void consultaServicos(List<Servico> servicos){
		
		String[] colunas = {"Nome do Serviço", "Preço"};
		String[][] dados = new String[servicos.size()][2];
		
		int contador = 0;
		
		for(Servico s : servicos){
			
			dados[contador][0] = s.getS_nomeServico();
			dados[contador][1] = "R$ " + s.getD_preco();
			
			contador ++;
		}
		
		tablePrincipal.setModel(new DefaultTableModel(dados, colunas));
		tablePrincipal.getColumnModel().getColumn(0).setPreferredWidth(700);
		tablePrincipal.getColumnModel().getColumn(1).setPreferredWidth(300);
		
	}

	//Popular a tableca com Atendimentos
	public void consultaAtendimento(List<Atende> atendimentos){
		
		String[] colunas = {"Nome do Cliente", "Nome do Serviço", "Preço"};
		String[][] dados = new String[atendimentos.size()][3];
		
		int contador = 0;
		
		for(Atende a: atendimentos){
			
			dados[contador][0] = a.getS_nomeCliente();
			dados[contador][1] = a.getS_nomeServico();
			dados[contador][2] = "R$ " + a.getD_preco();
			
			contador ++;
		}
		
		tablePrincipal.setModel(new DefaultTableModel(dados, colunas));
		tablePrincipal.getColumnModel().getColumn(0).setPreferredWidth(500);
		tablePrincipal.getColumnModel().getColumn(1).setPreferredWidth(500);
		tablePrincipal.getColumnModel().getColumn(2).setPreferredWidth(200);
		
	}
	
	
	
}

