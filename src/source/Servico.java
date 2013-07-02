package source;

public class Servico {

	//Artributos
	String s_nomeServico;
	double d_preco;

	public Servico(){
		s_nomeServico = "";
		d_preco = 0;
	}
	
	public Servico cadastraServico(String s_nomeServico, double d_preco){
		Servico novoServico = new Servico();
		
		novoServico.setS_nomeServico(s_nomeServico);
		novoServico.setD_preco(d_preco);
		
		return novoServico;
	}
	
	//Get's and Set's
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

