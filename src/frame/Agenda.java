package frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import source.*;
import java.awt.Toolkit;


public class Agenda extends JFrame{
	
	//Componentes do Frame
	private JTable tablePrincipal;
	
	public Agenda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Marcus\\Documents\\GitHub\\Extras\\iconeCabelereiro.gif"));
		
		//Configurações gerais do Frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Agenda");
		getContentPane().setLayout(null);
		
		setBounds(100, 100, 550, 380);
		setLocationRelativeTo(null);
		
		//ScrollPane Principal
		JScrollPane crpPrincipal = new JScrollPane();
		crpPrincipal.setBounds(10, 11, 514, 268);
		getContentPane().add(crpPrincipal);
		
		//Tabela Principal
		tablePrincipal = new JTable();
		crpPrincipal.setViewportView(tablePrincipal);
		
		//Panel de Opções
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(157, 290, 217, 40);
		getContentPane().add(panel);
		
		//Botão Voltar
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConnectionFactory pesquisaBD = new ConnectionFactory();
				List<source.Agenda> Agendamentos = new ArrayList<source.Agenda>();
				
				try {
					Agendamentos = pesquisaBD.agendaToList();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Erro:");
				}
				
				consultaAgenda(Agendamentos);
				
			}
		});
		panel.add(btnAtualizar);
		panel.add(btnVoltar);
	}
	
	//Popular a tabela principal com Agendamentos
	public void consultaAgenda(List<source.Agenda> Agendamentos){
		
		String[] colunas = new String[4];
		String[][] dados = new String[Agendamentos.size()][4];
		
		colunas[0] = "Nome do Cliente";
		colunas[1] = "Serviço";
		colunas[2] = "Data";
		colunas[3] = "Hora";
	
		
		int contador = 0;
		
		for(source.Agenda a : Agendamentos){
			
			dados[contador][0] = a.getS_nomeCliente();
			dados[contador][1] = a.getS_nomeServico();
			dados[contador][2] = a.getS_data();
			dados[contador][3] = a.getS_hora();
			
			contador ++;
		}
		
		tablePrincipal.setModel(new DefaultTableModel(dados, colunas));
		tablePrincipal.getColumnModel().getColumn(0).setPreferredWidth(300);
		tablePrincipal.getColumnModel().getColumn(1).setPreferredWidth(300);
		tablePrincipal.getColumnModel().getColumn(2).setPreferredWidth(200);
		tablePrincipal.getColumnModel().getColumn(3).setPreferredWidth(200);
	}
}
