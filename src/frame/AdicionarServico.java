package frame;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import source.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdicionarServico extends JFrame{
	
	//Componentes do Frame
	private JTable tablePrincipal;

	public AdicionarServico(final List<Servico> servicosSelecionados){
			
		//Configurações gerais
		setTitle("Adicionar Servi\u00E7o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setBounds(100, 100, 550, 400);
		setLocationRelativeTo(null);
		
		//Labels
		JLabel lblSelecioneOsServios = new JLabel("Selecione os servi\u00E7os:");
		lblSelecioneOsServios.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelecioneOsServios.setBounds(10, 11, 120, 14);
		getContentPane().add(lblSelecioneOsServios);
		
		//Scroll Panel Principal
		JScrollPane scpPrincipal = new JScrollPane();
		scpPrincipal.setBounds(10, 37, 514, 254);
		getContentPane().add(scpPrincipal);
		
		//Tabela Principal
		tablePrincipal = new JTable();
		scpPrincipal.setViewportView(tablePrincipal);
		
		//Populando a Tabela
		List<Servico> servicosObtidos = new ArrayList<Servico>();
		ConnectionFactory conexaoBD = new ConnectionFactory();
		
		try {
			servicosObtidos = conexaoBD.servicosToList();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro:" +e1.getMessage());
		}
		
		consultarServicos(servicosObtidos);
		
		//Panel Opções
		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setBounds(162, 303, 193, 33);
		getContentPane().add(panelOpcoes);
		
		//Botão Continuar
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int numeroLinhasTable = tablePrincipal.getRowCount();
				
				for(int contador = 0; contador < numeroLinhasTable; contador++){
					
					if(tablePrincipal.getValueAt(contador, 2) != null){
						
						if((Boolean)tablePrincipal.getValueAt(contador, 2) == true){
							Servico servicoSelecionado = new Servico();
							
							String nomeServico = (String)tablePrincipal.getValueAt(contador, 0);
							String preco = (String) tablePrincipal.getValueAt(contador, 1);
							preco = preco.replace("R", "");
							preco = preco.replace("$", "");
							preco = preco.replaceAll(" ", "");
									
							servicoSelecionado.setS_nomeServico(nomeServico);
							servicoSelecionado.setD_preco(Double.parseDouble(preco));
							
							servicosSelecionados.add(servicoSelecionado);
						}
						
					}
					
				}
				setVisible(false);
				dispose();
				return;
			
			}
		});
		panelOpcoes.add(btnContinuar);
		
		//Botão Voltar
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		panelOpcoes.add(btnVoltar);
	}
	
	//Popular tabela de Clientes Registrados
	@SuppressWarnings("serial")
	public void consultarServicos(List<Servico> servicos){
		
		Object[] colunas = {"Nome do Serviço","Preço","Check"};
		Object[][] dados = new String [servicos.size()][3];
		
		int contador = 0;
		
		for(Servico s: servicos){
			
			dados[contador][0] = new String (s.getS_nomeServico());
			dados[contador][1] = new String ("R$" + s.getD_preco()); 

			contador ++;
		}
		
			tablePrincipal.setModel(new DefaultTableModel(dados,colunas){
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
						String.class, String.class, Boolean.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			tablePrincipal.getColumnModel().getColumn(0).setPreferredWidth(400);
			tablePrincipal.getColumnModel().getColumn(1).setPreferredWidth(200);
	}
	
	
}
