package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.UIManager;




public class Inicio extends JFrame{

	public Inicio(){
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Marcus\\Documents\\GitHub\\Extras\\iconeCabelereiro.gif"));
		
		//Configurações gerais do Frame
		setTitle("Ana Mesquita Cabeleireiros (ver.1.0)");
		getContentPane().setLayout(null);
		
		setBounds(100, 100, 550, 400);
		setLocationRelativeTo(null);
		
		///////Componentes do Menu/////////
		
		//Menu bar principal
		JMenuBar menuBarPrincipal = new JMenuBar();
		menuBarPrincipal.setBounds(0, 0, 534, 21);
		getContentPane().add(menuBarPrincipal);
		
		//Menu Atendimento
		JMenu menuAtendimento = new JMenu("Atendimento");
		menuBarPrincipal.add(menuAtendimento);
		
		//Item Atendimento
		JMenuItem mitemNovoAtendimento = new JMenuItem("Novo Atendimento");
		menuAtendimento.add(mitemNovoAtendimento);
		mitemNovoAtendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Atendimento novaTelaAtendimento = new Atendimento();
				novaTelaAtendimento.setVisible(true);
			}
		});
		
		//Submenu Agenda
		JMenu menuAgenda = new JMenu("Agenda");
		menuAtendimento.add(menuAgenda);
		
		//Item consultar agenda
		JMenuItem mitemConsultarAgenda = new JMenuItem("Consultar Agenda");
		menuAgenda.add(mitemConsultarAgenda);
		mitemConsultarAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agenda novaTelaAgenda = new Agenda();
				novaTelaAgenda.setVisible(true);
			}
		});
		
		//Item Agendar Cliente
		JMenuItem mitemAgendarCliente = new JMenuItem("Agendar Cliente");
		menuAgenda.add(mitemAgendarCliente);
		mitemAgendarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgendamentoCliente novaTelaAgendamentoCliente = new AgendamentoCliente();
				novaTelaAgendamentoCliente.setVisible(true);
			}
		});
		
		//Menu Cadastro
		JMenu menuCadastro = new JMenu("Cadastro");
		menuBarPrincipal.add(menuCadastro);
		
		//Item Cadastro de Clientes
		JMenuItem mitemCadastrarCliente = new JMenuItem("Cadastrar Cliente");
		menuCadastro.add(mitemCadastrarCliente);
		mitemCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCliente novaTelaCadastroCliente = new CadastroCliente();
				novaTelaCadastroCliente.setVisible(true);
			}
		});
		
		//Item Cadastrar Serviço
		JMenuItem mitemCadastrarServico = new JMenuItem("Cadastrar Servi\u00E7o");
		menuCadastro.add(mitemCadastrarServico);
		mitemCadastrarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroServico novaTelaCadastroServico = new CadastroServico();
				novaTelaCadastroServico.setVisible(true);
			}
		});
		
		//Menu Consulta
		JMenu mnConsulta = new JMenu("Consulta");
		menuBarPrincipal.add(mnConsulta);
		
		//Item Consultar Cadastros
		JMenuItem mitemConsultarCadastros = new JMenuItem("Consultar Cadastros");
		mnConsulta.add(mitemConsultarCadastros);
		mitemConsultarCadastros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consulta novaTelaConsulta = new Consulta();
				novaTelaConsulta.setVisible(true);
			}
		});
		
		//Menu Administrativo
		JMenu mnAdministrativo = new JMenu("Administrativo");
		menuBarPrincipal.add(mnAdministrativo);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Deseja realmente sair do sistema?", "Sair do sistema", JOptionPane.YES_NO_OPTION) == 0){
					setVisible(false);
					dispose();
				}
					
			}
		});
		btnSair.setIcon(new ImageIcon(Inicio.class.getResource("/javax/swing/plaf/metal/icons/ocean/error.png")));
		btnSair.setBounds(413, 309, 111, 41);
		getContentPane().add(btnSair);
		
		JLabel lblVerso = new JLabel("Vers\u00E3o 1.0 , por Marcus Mesquita");
		lblVerso.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblVerso.setBounds(10, 336, 197, 14);
		getContentPane().add(lblVerso);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.light"));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 32, 514, 270);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		
		
		/////////////////////////////////////////
		
		JLabel lblAnaMesquitaCabeleireiros = new JLabel("Ana Mesquita Cabeleireiros");
		lblAnaMesquitaCabeleireiros.setBounds(10, 108, 494, 62);
		panel.add(lblAnaMesquitaCabeleireiros);
		lblAnaMesquitaCabeleireiros.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnaMesquitaCabeleireiros.setFont(new Font("Gabriola", Font.BOLD | Font.ITALIC, 36));
	}
}
