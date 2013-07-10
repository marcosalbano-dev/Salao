package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import source.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
		
public class ApagarCliente extends JFrame {
	
	//Componentes do frame
	private JTable tablePrincipal;

	public ApagarCliente(){
		
		//Declarações 
		final ConnectionFactory conexaoBD = new ConnectionFactory();
		List<Cliente> clientesObtidos = new ArrayList<Cliente>();
		
		//Comfigurações gerais
		setTitle("Apagar Cliente");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setBounds(100, 100, 550, 370);
		setLocationRelativeTo(null);
		
		//Labels
		JLabel lblSelecioneOCliente = new JLabel("Selecione o cliente para apagar:");
		lblSelecioneOCliente.setBounds(10, 11, 514, 14);
		getContentPane().add(lblSelecioneOCliente);
		
		//Scroll pane principal
		JScrollPane scpPrincipal = new JScrollPane();
		scpPrincipal.setBounds(10, 36, 514, 248);
		getContentPane().add(scpPrincipal);
		
		//Table principal
		tablePrincipal = new JTable();
		scpPrincipal.setViewportView(tablePrincipal);
		
		try {
			clientesObtidos = conexaoBD.clientesToList();
			popularTabela(clientesObtidos);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível obter clientes do banco de dados. Erro:"+e.getMessage());
		}
		
		//Panel Opções
		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setBounds(159, 295, 200, 33);
		getContentPane().add(panelOpcoes);
		
		//Panel Sair
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(null, "Deseja realmente apagar os clientes selecionados?", "Apagar Clientes", JOptionPane.YES_NO_OPTION) == 0){
					
					List<Cliente> clientes = new ArrayList<Cliente>();
					
					for(int contador = 0; contador < tablePrincipal.getRowCount(); contador ++){
						
						if(tablePrincipal.getValueAt(contador, 5)!= null){
							
							if((Boolean)tablePrincipal.getValueAt(contador, 5) == true){

								String nomeClienteSelecionado;
								
								nomeClienteSelecionado = (String) tablePrincipal.getValueAt(contador, 0);
								
								if(conexaoBD.apagarCliente(nomeClienteSelecionado) == true);
									JOptionPane.showMessageDialog(null, "Cliente apagado com sucesso!");
								
							}
						}
					}
				}
			}
		});
		panelOpcoes.add(btnApagar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		panelOpcoes.add(btnVoltar);
		
	}
	
	private void popularTabela(List<Cliente> clientes){
		
		Object[] colunas = {"Nome do Cliente","Endereço","Sexo","Telefone","Celular","Check"};
		Object[][] dados = new String [clientes.size()][6];
		
		int contador = 0;
		
		for(Cliente c: clientes){
			
			dados[contador][0] = new String (c.getS_nomeCliente());
			dados[contador][1] = new String (c.getS_endereco());
			dados[contador][2] = new String (c.getS_sexo());
			dados[contador][3] = new String ("" + c.getI_telefoneFixo());
			dados[contador][4] = new String ("" + c.getI_telefoneCelular());

			contador ++;
		}
		
			tablePrincipal.setModel(new DefaultTableModel(dados,colunas){
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, Boolean.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			tablePrincipal.getColumnModel().getColumn(0).setPreferredWidth(300);
			tablePrincipal.getColumnModel().getColumn(1).setPreferredWidth(200);
			tablePrincipal.getColumnModel().getColumn(2).setPreferredWidth(80);
			tablePrincipal.getColumnModel().getColumn(3).setPreferredWidth(150);
			tablePrincipal.getColumnModel().getColumn(4).setPreferredWidth(150);
	}
}
