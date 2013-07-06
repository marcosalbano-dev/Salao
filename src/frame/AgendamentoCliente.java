package frame;

import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import java.util.ArrayList;
import java.util.List;

import source.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgendamentoCliente extends JFrame{
	
	//Componentes do Frame
	private JFormattedTextField txtData;
	private JFormattedTextField txtHora;
	private JComboBox<String> cboxCliente;
	private JComboBox<String> cboxServico;
	
	public AgendamentoCliente() {
		
		//Criando Conexão com BD
		final ConnectionFactory conexaoBanco = new ConnectionFactory();
		
		//Clientes e Serviços do BD
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<Servico> servicos = new ArrayList<Servico>();
		
		//Configurações gerais do Frame
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Agendar Cliente");
		getContentPane().setLayout(null);
		
		setBounds(100, 100, 450, 230);
		setLocationRelativeTo(null);
		
		//Labels
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(10, 11, 65, 14);
		getContentPane().add(lblCliente);
		
		JLabel lblServico = new JLabel("Servi\u00E7o:");
		lblServico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblServico.setBounds(10, 42, 65, 14);
		getContentPane().add(lblServico);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblData.setBounds(10, 76, 65, 14);
		getContentPane().add(lblData);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHora.setBounds(10, 107, 65, 14);
		getContentPane().add(lblHora);
		
		//Combobox Clientes
		final JComboBox<String> cboxCliente = new JComboBox<String>();
		cboxCliente.setBounds(85, 11, 326, 20);
		getContentPane().add(cboxCliente);
		
		//Populando comboboc Clientes
		try {
			clientes = conexaoBanco.clientesToList();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro:" +e1.getMessage());
		}
		
		cboxCliente.addItem("-");
		
		for(Cliente cliente: clientes){
			cboxCliente.addItem(cliente.getS_nomeCliente());
		}
		
		//Combobox Serviços
		final JComboBox<String> cboxServico = new JComboBox<String>();
		cboxServico.setBounds(85, 42, 326, 20);
		getContentPane().add(cboxServico);
		
		//Populando combobox Servico
		try {
			servicos = conexaoBanco.servicosToList();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro:" +e1.getMessage());
		}
		
		cboxServico.addItem("-");
		
		for(Servico servico: servicos){
			cboxServico.addItem(servico.getS_nomeServico());
		}
		
		
		//Field Data
		txtData = new JFormattedTextField();
		txtData.setBounds(85, 73, 86, 20);
		getContentPane().add(txtData);
		txtData.setColumns(10);
		
		try {
			txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
		}
		
		//Field Hora
		txtHora = new JFormattedTextField();
		txtHora.setBounds(85, 104, 86, 20);
		getContentPane().add(txtHora);
		txtHora.setColumns(10);
		
		try {
			txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
		}
		
		//Panel Opções
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(85, 141, 280, 33);
		getContentPane().add(panel);
		
		//Botão agendar cliente
		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String testeCliente = (String) cboxCliente.getSelectedItem();
				String testeServico = (String) cboxServico.getSelectedItem();
				if(checkField(testeCliente, testeServico)){
					if(JOptionPane.showConfirmDialog(null, "Deseja agendar o cliente?", "Agendamento de Cliente", JOptionPane.YES_NO_OPTION) == 0){
						source.Agenda novoAgendamento = new source.Agenda();
						
						String nomeCliente = (String) cboxCliente.getSelectedItem();
						String nomeServico = (String) cboxServico.getSelectedItem();
						String data = txtData.getText();
						String hora = txtHora.getText();
						
						novoAgendamento = novoAgendamento.agendarCliente(nomeCliente, nomeServico, data, hora);
						
						try {
							conexaoBanco.insereAgendamento(novoAgendamento);
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Erro:" +e1.getMessage());
						}
					}
				}
			}
		});
		panel.add(btnAgendar);
		
		//Botão Limpar
		JButton btnLimpar = new JButton("Limpar");
		panel.add(btnLimpar);
		
		//Botão sair
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		panel.add(btnVoltar);
	}
	
	public boolean checkField(String testeCliente, String testeServico){
		try{
			String testeData = txtData.getText();
			testeData = testeData.replace("/", "");
			testeData = testeData.replaceAll(" ", "");
			
			String testeHora = txtHora.getText();
			testeHora = testeHora.replace(":", "");
			testeHora = testeHora.replaceAll(" ", "");
			
			if(testeCliente.equals("-")){
				JOptionPane.showMessageDialog(null, "Escolha o cliente");
				return false;
			}
			else if(testeServico.equals("-")){
				JOptionPane.showMessageDialog(null, "Escolha o serviço");
				return false;
			}
			else if(testeData.equals("")){
				JOptionPane.showMessageDialog(null, "Digite a data");
				return false;
			}
			else if(testeHora.equals("")){
				JOptionPane.showMessageDialog(null, "Digite a hora");
				return false;
			}
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Erro:" +e.getMessage());
		}
		
		return true;
	}
}
