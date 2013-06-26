package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	
	//Componentes do frame
	JLabel lblTitle;
	JPanel panelOpcoes;
	JButton btnAtendimento;
	JButton btnAgenda;
	JButton btnConsulta;
	JButton btnSair;
	
	public Login() {
		
		setTitle("Ana Mesquita Cabeleireiros (ver 1.0)");
		getContentPane().setLayout(null);
		
		//Definição do tamanho da tela e posicionamento no centro
		setBounds(100, 100, 450, 320);
		setLocationRelativeTo(null);
		
		//Titulo
		JLabel lblTitle = new JLabel("Ana Mesquita Cabeleireiros");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitle.setFont(new Font("Segoe Script", Font.BOLD, 26));
		lblTitle.setBounds(10, 11, 414, 40);
		getContentPane().add(lblTitle);
		
		//Panel principal
		JPanel panelGerenciamento = new JPanel();
		panelGerenciamento.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Gerenciamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGerenciamento.setBounds(10, 62, 164, 124);
		getContentPane().add(panelGerenciamento);
		panelGerenciamento.setLayout(null);
		
		//Botão Atendimento
		JButton btnAtendimento = new JButton("Atendimento");
		btnAtendimento.setBounds(10, 22, 144, 23);
		panelGerenciamento.add(btnAtendimento);
		
		//Botao Agenda
		JButton btnAgenda = new JButton("Agenda");
		btnAgenda.setBounds(10, 56, 144, 23);
		panelGerenciamento.add(btnAgenda);
		
		//Botao Consulta
		JButton btnConsulta = new JButton("Consulta");
		btnConsulta.setBounds(10, 90, 144, 23);
		panelGerenciamento.add(btnConsulta);
		
		//Painel Cadastro
		JPanel panelCadastro = new JPanel();
		panelCadastro.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Cadastro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCadastro.setBounds(10, 187, 164, 95);
		getContentPane().add(panelCadastro);
		panelCadastro.setLayout(null);
		
		//Botão Cadastrar Serviço
		JButton btnCadastrarServio = new JButton("Cadastrar Servi\u00E7o");
		btnCadastrarServio.setBounds(10, 61, 144, 23);
		panelCadastro.add(btnCadastrarServio);
		
		//Botão Cadastrar Clientes
		JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroCliente novaTelaCadastroCliente = new CadastroCliente();
				novaTelaCadastroCliente.setVisible(true);
			}
		});
		btnCadastrarCliente.setBounds(10, 27, 144, 23);
		panelCadastro.add(btnCadastrarCliente);
		
		//Painel do botão Sair
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(268, 234, 164, 48);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		//Botao Sair
		JButton btnSair_1 = new JButton("Sair");
		btnSair_1.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Você deseja realmente sair do Programa?", "Sair do Programa", JOptionPane.YES_NO_OPTION) == 0){
					setVisible(false);	
					dispose();
				} else{
					return;
				}
				
			}
		});
		btnSair_1.setBounds(10, 11, 144, 23);
		panel.add(btnSair_1);
	}
}
