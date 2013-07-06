package source;

public class Agenda {
	
	//Artributos
	private String s_nomeCliente;
	private String s_nomeServico;
	private String s_data;
	private String s_hora;
	
	public Agenda(){
		s_nomeCliente = "";
		s_nomeServico = "";
		s_data = "";
		s_hora = "";
	}

	//Agendamento de cliente
	public Agenda agendarCliente(String s_nomeCliente, String s_nomeServico, String s_data, String s_hora){
		
		Agenda novoAgendamento = new Agenda();
		
		novoAgendamento.setS_nomeCliente(s_nomeCliente);
		novoAgendamento.setS_nomeServico(s_nomeServico);
		novoAgendamento.setS_data(s_data);
		novoAgendamento.setS_hora(s_hora);
		
		return novoAgendamento;
	}
	
	
	// Get's and Set's
	public String getS_nomeCliente() {
		return s_nomeCliente;
	}

	public void setS_nomeCliente(String s_nomeCliente) {
		this.s_nomeCliente = s_nomeCliente;
	}

	public String getS_nomeServico() {
		return s_nomeServico;
	}

	public void setS_nomeServico(String s_nomeServico) {
		this.s_nomeServico = s_nomeServico;
	}

	public String getS_data() {
		return s_data;
	}

	public void setS_data(String s_data) {
		this.s_data = s_data;
	}

	public String getS_hora() {
		return s_hora;
	}

	public void setS_hora(String s_hora) {
		this.s_hora = s_hora;
	}
	
	
}
