package frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import source.ConnectionFactory;
import source.Servico;
import java.awt.Toolkit;

public class CadastroServico extends JFrame{
	
	//Componentes do Frame
	private JLabel lblNomeDoServico;
	private JLabel lblPreco;
	private JTextField txtNomeServico;
	private JTextField txtPreco;
	private JButton btnCadastrar;
	private JButton btnLimpar;
	private JButton btnSair;
	private JPanel panelOpcoes;
	
	public CadastroServico(){
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Marcus\\Documents\\GitHub\\Extras\\iconeCabelereiro.gif"));
		
		setTitle("Cadastro de Servi�o");
		getContentPane().setLayout(null);
		
		//Define posi��o da tela
		setBounds(100, 100, 450, 200);
		setLocationRelativeTo(null);
		
		//Labels	
		JLabel lblNomeDoServico = new JLabel("Nome do Servi\u00E7o:");
		lblNomeDoServico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomeDoServico.setBounds(10, 14, 120, 14);
		getContentPane().add(lblNomeDoServico);
		
		JLabel lblPreco = new JLabel("Pre\u00E7o (R$):");
		lblPreco.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreco.setBounds(10, 39, 120, 14);
		getContentPane().add(lblPreco);
		
		//Field Nome do Servi�o
		txtNomeServico = new JTextField();
		txtNomeServico.setBounds(140, 11, 284, 20);
		getContentPane().add(txtNomeServico);
		txtNomeServico.setColumns(10);
		
		//Field Pre�o do Servi�o
		txtPreco = new JTextField();
		txtPreco.setBounds(140, 36, 284, 20);
		getContentPane().add(txtPreco);
		txtPreco.setColumns(10);
		
		//Panel de Op��es
		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(67, 78, 310, 51);
		getContentPane().add(panelOpcoes);
		panelOpcoes.setLayout(null);
		
		//Bot�o Cadastrar
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkField()){
					if(JOptionPane.showConfirmDialog(null, "Deseja cadastrar o Servi�o?", "Cadastro de Servi�o", JOptionPane.YES_NO_OPTION) == 0){
						
						Servico novoServico = new Servico();

						String novoNomeServico = txtNomeServico.getText();
						Double novoPreco = Double.parseDouble(txtPreco.getText());
						
						//Monta o Objeto servi�o
						novoServico = novoServico.cadastraServico(novoNomeServico, novoPreco);	
						
						//Insere o servi�o no BD
						ConnectionFactory adicionarServico = new ConnectionFactory();
						try {
							
							adicionarServico.insereServico(novoServico);
							
						} catch (SQLException e1) {
							
							JOptionPane.showMessageDialog(null, "N�o foi poss�vel cadastrar o servi�o" +e1.getMessage());
						
						}
					}
				}
			}
		});
		btnCadastrar.setBounds(10, 11, 89, 23);
		panelOpcoes.add(btnCadastrar);
		
		//Bot�o Limpar
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNomeServico.setText("");
				txtPreco.setText("");
			}
		});
		btnLimpar.setBounds(109, 11, 89, 23);
		panelOpcoes.add(btnLimpar);
		
		//Bot�o Sair
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		btnVoltar.setBounds(208, 11, 89, 23);
		panelOpcoes.add(btnVoltar);
		
	}
	
	public boolean checkField(){
		try{
			if(txtNomeServico.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Digite o nome do Servi�o");
				return false;
			}
			else if(txtPreco.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Digite o valor do Servi�o");
				return false;
			}	
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
		}
		return true;
	}
}
