package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFormattedTextField;

import source.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;

public class Atendimento extends JFrame {
	
	//Componentes do frame
	private JTable tablePrincipal;
	private JTextField txtValorFinal;
	private JComboBox<String> cboxClientes;
	private JFormattedTextField txtData;
	private JTextField txtAddDesc;

	
	public Atendimento(){
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Marcus\\Documents\\GitHub\\Extras\\iconeCabelereiro.gif"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//Declarações gerais
		final List<Servico> servicosSelecionados = new ArrayList<Servico>();
		List<Cliente> clientes = new ArrayList<Cliente>();
		final ConnectionFactory conexaoBD = new ConnectionFactory();
		
		//Configurações gerais
		setTitle("Atendimento");
		getContentPane().setLayout(null);
		
		setBounds(100, 100, 550, 424);
		setLocationRelativeTo(null);
		
		//Labels
		JLabel lblSelecioneOCliente = new JLabel("Selecione o Cliente:");
		lblSelecioneOCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelecioneOCliente.setBounds(10, 11, 106, 14);
		getContentPane().add(lblSelecioneOCliente);
		
		JLabel lblServicos = new JLabel("Servi\u00E7os:");
		lblServicos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblServicos.setBounds(10, 40, 106, 14);
		getContentPane().add(lblServicos);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(338, 335, 66, 14);
		getContentPane().add(lblTotal);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setHorizontalAlignment(SwingConstants.RIGHT);
		lblData.setBounds(10, 73, 106, 14);
		getContentPane().add(lblData);
		
		//Combobox Clientes
		final JComboBox<String> cboxClientes = new JComboBox<String>();
		cboxClientes.setBounds(126, 8, 214, 20);
		getContentPane().add(cboxClientes);
		
		//Populando o cboxClientes
		cboxClientes.addItem("-");
		
		try {
			clientes = conexaoBD.clientesToList();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Erro:" +e1.getMessage());
		}
		
		for(Cliente c: clientes){
			cboxClientes.addItem(c.getS_nomeCliente());
		}
		
		//Scroll panel principal
		JScrollPane scpPrincipal = new JScrollPane();
		scpPrincipal.setBounds(10, 101, 514, 195);
		getContentPane().add(scpPrincipal);
		
		//Tabela principal
		tablePrincipal = new JTable();
		scpPrincipal.setViewportView(tablePrincipal);
		
		//Field valor final
		txtValorFinal = new JTextField();
		txtValorFinal.setText("R$0.0");
		txtValorFinal.setFont(new Font("Tahoma", Font.BOLD, 20));
		txtValorFinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtValorFinal.setEditable(false);
		txtValorFinal.setBounds(414, 317, 110, 50);
		getContentPane().add(txtValorFinal);
		txtValorFinal.setColumns(10);
		
		//Field data
		txtData = new JFormattedTextField();
		txtData.setBounds(126, 70, 86, 20);
		getContentPane().add(txtData);
		txtData.setColumns(10);
		
		try {
			txtData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
		}
		
		//PanelOpções
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 329, 281, 38);
		getContentPane().add(panel);
		
		//Botão Adicionar Serviço
		JButton btnAdicionarServico = new JButton("Adicionar Servi\u00E7o");
		btnAdicionarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdicionarServico novaTelaAdicionarServico = new AdicionarServico(servicosSelecionados);
				novaTelaAdicionarServico.setVisible(true);
				
			}
		});
		btnAdicionarServico.setBounds(126, 36, 148, 23);
		getContentPane().add(btnAdicionarServico);
		
		
		//Botão Registrar
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int contador = 0;
				
				String testeCliente = (String)cboxClientes.getSelectedItem();
				
				if(checkField(testeCliente)){
				
					if(JOptionPane.showConfirmDialog(null, "Deseja realmente cadastrar o atendimento?", "Registro de Atendimento", JOptionPane.YES_NO_OPTION) == 0){
						for(Servico s: servicosSelecionados){
							
							Atende novoAtendimento = new Atende();
							
							String nomeCliente = (String) cboxClientes.getSelectedItem();
							String nomeServico = s.getS_nomeServico();
							double preco = s.getD_preco();
							String data = (String) txtData.getText();
							
							novoAtendimento = novoAtendimento.registrarAtendimento(nomeCliente, nomeServico, preco, data);
							
							try {
								conexaoBD.insereAtendimento(novoAtendimento);
							} catch (SQLException e1) {
								JOptionPane.showMessageDialog(null, "Erro ao registrar atendimento");
							}
							
						}
						
						JOptionPane.showMessageDialog(null, "Atendimento registrado com sucesso!");	
					}
					
				}
				
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(btnRegistrar);
		
		//Botão Limpar
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				cboxClientes.setSelectedIndex(0);
				
				servicosSelecionados.clear();
				
				popularTabela(servicosSelecionados);
				
				txtValorFinal.setText("R$0.0");
				
				tablePrincipal.removeAll();
				
				txtData.setText("");
				
			}
		});
		panel.add(btnLimpar);
		
		//Botão Sair
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		panel.add(btnVoltar);
		
		//Botão Atualizar
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				double valorTotal = 0;
				valorTotal = popularTabela(servicosSelecionados);
				txtValorFinal.setText("R$" + valorTotal);
			}
		});
		btnAtualizar.setBounds(284, 36, 89, 23);
		getContentPane().add(btnAtualizar);
	}
	
	public double popularTabela(List<Servico> servicosSelecionados){
		
		String[] colunas = {"Nome do Serviço", "Preço"};
		String[][] dados = new String[servicosSelecionados.size()][2];
		
		int contador = 0;
		double valorTotal = 0;
		
		for(Servico s: servicosSelecionados){
			dados[contador][0] = s.getS_nomeServico();
			dados[contador][1] = "" + s.getD_preco();
			
			valorTotal = valorTotal + s.getD_preco();
			
			contador++;
		}
		
		tablePrincipal.setModel(new DefaultTableModel(dados, colunas));
		tablePrincipal.getColumnModel().getColumn(0).setPreferredWidth(500);
		tablePrincipal.getColumnModel().getColumn(1).setPreferredWidth(200);
		
		return(valorTotal);
	}
	
	public boolean checkField(String testeCliente){
		
		String testeData = txtData.getText();
		testeData = testeData.replace("/", "");
		testeData = testeData.replaceAll(" ", "");
		
		if(testeCliente.equals("-")){
			JOptionPane.showMessageDialog(null, "Selecione o cliente");
			return false;
		}
		else if(tablePrincipal.getRowCount() == 0){
			JOptionPane.showMessageDialog(null, "Selecione o(s) serviço(s) realizados");
			return false;
		}
		else if(testeData.equals("")){
			JOptionPane.showMessageDialog(null, "Digite a data do atendimento");
			return false;
		}
		
		return true;
		
	}
}
