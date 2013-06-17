package source;

public class Cliente {

	//Artributos
	private String s_nomeCliente;
	private String s_endereco;
	private String s_sexo;
	private int i_telefoneFixo;
	private int i_telefoneCelular;
	
	public Cliente(){
		s_nomeCliente = "";
		s_endereco = "";
		s_sexo = "";
		i_telefoneFixo = 0;
		i_telefoneCelular = 0;
	}

	public Cliente cadastraCliente(String s_nomeCliente, String s_endereco, String s_sexo, int i_telefoneFixo, int i_telefoneCelular){
		
		Cliente novoCliente = new Cliente();
		
		novoCliente.setS_nomeCliente(s_nomeCliente);
		novoCliente.setS_endereco(s_endereco);
		novoCliente.setS_sexo(s_sexo);
		novoCliente.setI_telefoneFixo(i_telefoneFixo);
		novoCliente.setI_telefoneCelular(i_telefoneCelular);
		
		return novoCliente;
	}

	//Get's and Set's
	public String getS_nomeCliente() {
		return s_nomeCliente;
	}

	public void setS_nomeCliente(String s_nomeCliente) {
		this.s_nomeCliente = s_nomeCliente;
	}

	public String getS_endereco() {
		return s_endereco;
	}

	public void setS_endereco(String s_endereco) {
		this.s_endereco = s_endereco;
	}

	public String getS_sexo() {
		return s_sexo;
	}

	public void setS_sexo(String s_sexo) {
		this.s_sexo = s_sexo;
	}

	public int getI_telefoneFixo() {
		return i_telefoneFixo;
	}

	public void setI_telefoneFixo(int i_telefoneFixo) {
		this.i_telefoneFixo = i_telefoneFixo;
	}

	public int getI_telefoneCelular() {
		return i_telefoneCelular;
	}

	public void setI_telefoneCelular(int i_telefoneCelular) {
		this.i_telefoneCelular = i_telefoneCelular;
	}
		
}
