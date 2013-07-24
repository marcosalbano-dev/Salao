package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import source.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ApagarAtendimento extends JFrame {
	
	//Componentes do frame
	private JTable tablePrincipal;
	
	public ApagarAtendimento(){
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Marcus\\Documents\\GitHub\\Extras\\iconeCabelereiro.gif"));
		
		
		//Declarações
		List<Atende> atendimentosObtidos = new ArrayList<Atende>();
		final ConnectionFactory conexaoBD = new ConnectionFactory();
		
		//Comfigurações gerais
		setTitle("Apagar Atendimento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setBounds(100, 100, 550, 370);
		setLocationRelativeTo(null);
		
		//Labels
		JLabel lblSelecioneOCliente = new JLabel("Selecione o atendimento para apagar:");
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
			atendimentosObtidos = conexaoBD.atendimentosToList();
			popularTabela(atendimentosObtidos);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível obter os atendimentos do banco de dados"+e.getMessage());
			e.printStackTrace();
		}
		
		//Panel Opções
		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setBounds(159, 295, 200, 33);
		getContentPane().add(panelOpcoes);
		
		//Panel Sair
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(JOptionPane.showConfirmDialog(null, "Deseja realmente apagar os agendamentoss selecionados?", "Apagar Clientes", JOptionPane.YES_NO_OPTION) == 0){
					
					List<Atende> atendimento = new ArrayList<Atende>();
					
					for(int contador = 0; contador < tablePrincipal.getRowCount(); contador ++){
						
						if(tablePrincipal.getValueAt(contador, 4)!= null){
							
							if((Boolean)tablePrincipal.getValueAt(contador, 4) == true){

								String nomeCliente;
								String nomeServico;
								String preco;
								String data;
								
								nomeCliente = (String) tablePrincipal.getValueAt(contador, 0);
								nomeServico = (String) tablePrincipal.getValueAt(contador, 1);
								preco = (String) tablePrincipal.getValueAt(contador, 2);
								data = (String) tablePrincipal.getValueAt(contador, 3);
								
								if(conexaoBD.apagarAgendamento(nomeCliente, nomeServico, preco, data) == true);
									JOptionPane.showMessageDialog(null, "Atendimento apagado com sucesso!");
								
							}
						}
					}
				}
				
			}
		});
		panelOpcoes.add(btnApagar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		panelOpcoes.add(btnVoltar);
		
	}
	
	public void popularTabela(List<Atende> atendimentos){
		
		Object[] colunas = {"Nome do Cliente","Nome do Serviço","Preço","Hora","Check"};
		Object[][] dados = new String [atendimentos.size()][5];
		
		int contador = 0;
		
		for(Atende a: atendimentos){
			
			dados[contador][0] = new String (a.getS_nomeCliente());
			dados[contador][1] = new String (a.getS_nomeServico());
			dados[contador][2] = new String ("" + a.getD_preco());
			dados[contador][3] = new String (a.getS_data());
			
			contador ++;
		}
		
			tablePrincipal.setModel(new DefaultTableModel(dados,colunas){
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, Boolean.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			tablePrincipal.getColumnModel().getColumn(0).setPreferredWidth(300);
			tablePrincipal.getColumnModel().getColumn(1).setPreferredWidth(200);
			tablePrincipal.getColumnModel().getColumn(2).setPreferredWidth(100);
			tablePrincipal.getColumnModel().getColumn(3).setPreferredWidth(100);

	}
}
