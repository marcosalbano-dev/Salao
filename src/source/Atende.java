package source;

public class Atende {

	//Artributos
	private String s_nomeCliente;
	private String s_nomeServico;
	private double d_preco;
	
	public Atende(){
		
		s_nomeCliente = "";
		s_nomeServico = "";
		d_preco = 0;
	}
	
	public Atende registrarAtendimento(String s_nomeCliente, String s_nomeServico, double d_preco){
		
		Atende novoAtendimento = new Atende();
		
		novoAtendimento.setS_nomeCliente(s_nomeCliente);
		novoAtendimento.setS_nomeServico(s_nomeServico);
		novoAtendimento.setD_preco(d_preco);
		
		return novoAtendimento;
		
	}

	
	//Get's e Set's
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

	public double getD_preco() {
		return d_preco;
	}

	public void setD_preco(double d_preco) {
		this.d_preco = d_preco;
	}
	
	
	
}
