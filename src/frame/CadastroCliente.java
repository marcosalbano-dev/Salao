package frame;

import source.Cliente;
import source.ConnectionFactory;

import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class CadastroCliente extends JFrame {
	
	//Componentes do frame
	private JTextField txtNomeCliente;
	private JTextField txtEndereco;
	private JFormattedTextField txtTelefone;
	private JFormattedTextField txtCelular;
	private final ButtonGroup groupSexo = new ButtonGroup();
	private JRadioButton rdbtnMasculino;
	private JRadioButton rdbtnFeminino;

	public CadastroCliente(){
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Marcus\\Documents\\GitHub\\Extras\\iconeCabelereiro.gif"));
		//Define tamanho e posição da tela
		setBounds(100, 100, 480, 240);
		setLocationRelativeTo(null);
		
		setTitle("Cadastro de Cliente");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//Labels
		JLabel lblNomeCliente = new JLabel("Nome do Cliente:");
		lblNomeCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeCliente.setBounds(10, 11, 107, 14);
		getContentPane().add(lblNomeCliente);
		
		JLabel lblTelefoneFixo = new JLabel("Telefone Fixo:");
		lblTelefoneFixo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefoneFixo.setBounds(10, 61, 107, 14);
		getContentPane().add(lblTelefoneFixo);
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCelular.setBounds(10, 86, 107, 14);
		getContentPane().add(lblCelular);
		
		JLabel lblEndereco = new JLabel("Endere\u00E7o:");
		lblEndereco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEndereco.setBounds(10, 36, 107, 14);
		getContentPane().add(lblEndereco);
		
		//Campo nome do Cliente
		txtNomeCliente = new JTextField();
		txtNomeCliente.setBounds(127, 8, 295, 20);
		getContentPane().add(txtNomeCliente);
		txtNomeCliente.setColumns(10);
		
		//Campo endereço
		txtEndereco = new JTextField();
		txtEndereco.setBounds(127, 33, 295, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);
		
		//Campo telefone
		txtTelefone = new JFormattedTextField();
		txtTelefone.setBounds(127, 58, 295, 20);
		getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);
		//Formato do campo telefone
		try {
			txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//Campo celular
		txtCelular = new JFormattedTextField();
		txtCelular.setBounds(127, 83, 295, 20);
		getContentPane().add(txtCelular);
		txtCelular.setColumns(10);
		//Formato do campo celular
		try{
			txtCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-####")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//Panel com opções de sexo
		JPanel panelSexo = new JPanel();
		panelSexo.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSexo.setBounds(10, 111, 107, 79);
		getContentPane().add(panelSexo);
		panelSexo.setLayout(null);
		
		//RadioButton Masculino
		final JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		groupSexo.add(rdbtnMasculino);
		rdbtnMasculino.setBounds(6, 27, 95, 23);
		panelSexo.add(rdbtnMasculino);
		rdbtnMasculino.setSelected(true);
		
		//RadioButton Feminino
		final JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
		groupSexo.add(rdbtnFeminino);
		rdbtnFeminino.setBounds(6, 53, 95, 23);
		panelSexo.add(rdbtnFeminino);
		
		//Panel de opções
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(127, 138, 327, 52);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Botão cadastrar
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(checkField() == true){
				
					if(JOptionPane.showConfirmDialog(null, "Deseja realmente cadastrar o novo Cliente?", "Cadastro de Cliente",  JOptionPane.YES_NO_OPTION) == 0){
					
						Cliente novoCliente = new Cliente();
				
						String novoNomeCliente = txtNomeCliente.getText();
						String novoEndereco = txtEndereco.getText();
						String novoSexo = "";
						String novoTelefone = txtTelefone.getText();
						novoTelefone = novoTelefone.replace("-", "");
						String novoCelular = txtCelular.getText();
						novoCelular = novoCelular.replace("-", "");
						
						if(rdbtnMasculino.isSelected()){
							novoSexo = "Masculino";
						} else if(rdbtnFeminino.isSelected()){
							novoSexo = "Feminino";
						}
						
						novoCliente = novoCliente.cadastraCliente(novoNomeCliente, novoEndereco, novoSexo, novoTelefone, novoCelular);
						
						ConnectionFactory adicionaCliente = new ConnectionFactory();
						try {
							adicionaCliente.insereCliente(novoCliente);
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
						}
						
					
					} else{
						return;
					}
					
				} else{
					return;
				}
				
			}		
		});
		btnCadastrar.setBounds(10, 11, 88, 23);
		panel.add(btnCadastrar);
		
		//Botão Limpar
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNomeCliente.setText(null);
				txtEndereco.setText(null);
				txtTelefone.setText(null);
				txtCelular.setText(null);
			}
		});
		btnLimpar.setBounds(108, 11, 88, 23);
		panel.add(btnLimpar);
		
		//Botão Voltar
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		btnVoltar.setBounds(206, 11, 88, 23);
		panel.add(btnVoltar);
		
	}
	
	public boolean checkField(){
		try{
			String novoTelefone = txtTelefone.getText();
			novoTelefone = novoTelefone.replace("-", "");
			novoTelefone = novoTelefone.replaceAll(" ", "");

			String novoCelular = txtCelular.getText();
			novoCelular = novoCelular.replace("-", "");
			novoCelular = novoCelular.replaceAll(" ", "");
			
			if (txtNomeCliente.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Digite o nome do Cliente"); 
				txtNomeCliente.requestFocus();
				return false;
			} 
			else if (txtEndereco.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Digite o endereço do Cliente");
				txtEndereco.requestFocus();
				return false;
			}
			else if (novoTelefone.equals("")){
				JOptionPane.showMessageDialog(null, "Digite o telefone do Cliente");
				txtTelefone.requestFocus();
				return false;
			}
			else if	(novoCelular.equals("")){
				JOptionPane.showMessageDialog(null, "Digite o Celular do Cliente");
				txtCelular.requestFocus();
				return false;
			}
			

		} catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage()) ;
		}
		
		return true;
	}
}
