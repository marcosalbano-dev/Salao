package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

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
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		
		//Titulo
		JLabel lblTitle = new JLabel("Ana Mesquita Cabeleireiros");
		lblTitle.setFont(new Font("Segoe Script", Font.BOLD, 26));
		lblTitle.setBounds(10, 11, 414, 40);
		getContentPane().add(lblTitle);
		
		//Panel principal
		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setBorder(new TitledBorder(null, "Op\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelOpcoes.setBounds(260, 75, 164, 175);
		getContentPane().add(panelOpcoes);
		panelOpcoes.setLayout(null);
		
		//Botão Atendimento
		JButton btnAtendimento = new JButton("Atendimento");
		btnAtendimento.setBounds(10, 23, 144, 23);
		panelOpcoes.add(btnAtendimento);
		
		//Botao Agenda
		JButton btnAgenda = new JButton("Agenda");
		btnAgenda.setBounds(10, 57, 144, 23);
		panelOpcoes.add(btnAgenda);
		
		//Botao Consulta
		JButton btnConsulta = new JButton("Consulta");
		btnConsulta.setBounds(10, 94, 144, 23);
		panelOpcoes.add(btnConsulta);
		
		//Botao Sair
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(10, 141, 144, 23);
		panelOpcoes.add(btnSair);
	}
}
