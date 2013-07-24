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
import java.awt.Toolkit;
		
public class ApagarServico extends JFrame {
	
	//Componentes do frame
	private JTable tablePrincipal;

	public ApagarServico(){
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Marcus\\Documents\\GitHub\\Extras\\iconeCabelereiro.gif"));
		
		//Declarações
		List<Servico> servicosObtidos = new ArrayList<Servico>();
		final ConnectionFactory conexaoBD = new ConnectionFactory();
		
		//Comfigurações gerais
		setTitle("Apagar Servi\u00E7o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setBounds(100, 100, 550, 370);
		setLocationRelativeTo(null);
		
		//Labels
		JLabel lblSelecioneOServico = new JLabel("Selecione o servi\u00E7o para apagar:");
		lblSelecioneOServico.setBounds(10, 11, 514, 14);
		getContentPane().add(lblSelecioneOServico);
		
		//Scroll pane principal
		JScrollPane scpPrincipal = new JScrollPane();
		scpPrincipal.setBounds(10, 36, 514, 248);
		getContentPane().add(scpPrincipal);
		
		//Table principal
		tablePrincipal = new JTable();
		scpPrincipal.setViewportView(tablePrincipal);
		
		try {
			servicosObtidos = conexaoBD.servicosToList();
			popularTabela(servicosObtidos);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível obter serviços no banco de dados. Erro:" +e.getMessage());
		}
		
		//Panel Opções
		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setBounds(159, 295, 200, 33);
		getContentPane().add(panelOpcoes);
		
		//Panel Sair
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(null, "Deseja realmente apagar os serviços selecionados?", "Apagar Clientes", JOptionPane.YES_NO_OPTION) == 0){
					
					List<Servico> servicos = new ArrayList<Servico>();
					
					for(int contador = 0; contador < tablePrincipal.getRowCount(); contador ++){
						
						if(tablePrincipal.getValueAt(contador, 2)!= null){
							
							if((Boolean)tablePrincipal.getValueAt(contador, 2) == true){

								String nomeServicoSelecionado;
								
								nomeServicoSelecionado = (String) tablePrincipal.getValueAt(contador, 0);
								
								if(conexaoBD.apagarServico(nomeServicoSelecionado) == true);
									JOptionPane.showMessageDialog(null, "Serviço apagado com sucesso!");
								
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
	
	//Popular a tabela
	private void popularTabela(List<Servico> servicos){
		
		Object[] colunas = {"Nome do Serviço","Preço","Check"};
		Object[][] dados = new String [servicos.size()][3];
		
		int contador = 0;
		
		for(Servico s: servicos){
			
			dados[contador][0] = new String (s.getS_nomeServico());
			dados[contador][1] = new String ("" + s.getD_preco());
			
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
			tablePrincipal.getColumnModel().getColumn(0).setPreferredWidth(300);
			tablePrincipal.getColumnModel().getColumn(1).setPreferredWidth(200);
	}
}
