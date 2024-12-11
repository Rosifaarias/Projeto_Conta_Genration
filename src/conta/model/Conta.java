package conta.model;

public class Conta {

	// **ATRIBUITOS**** SAO "VARIAVEIS"

	private int numero;
	private int agencia;
	private int tipo;
	private String titular;
	private float saldo;

	// 3 MODIFICADORES DE DE ACESSO
	// PRIVATE : SOMENTE ESSA // CELULAR PARTICULAR
	// PROTECTED: CLASSES DENTRO DO PACOTE / TELEFONE RESIDENCIAL
	// PUBLIC- ORELHAO
	//

	// METODO ESPECIAL- METODO CONSTRUTOR
	// COMPORTAMENTOS/ MÉTODOS
	// METODOS DE ACESSO
	// CONTA.NUMERO = NUMERO
	// PARAMETRO
	public Conta(int numero, int agencia, int tipo, String titular, float saldo) {
		this.numero = numero;
		this.agencia = agencia;
		this.tipo = tipo;
		this.titular = titular;
		this.saldo = saldo;
	}

	// METODO DE ACESSO / GET -> PEGAR

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) { // COLOCAR
		this.numero = numero;
	}

	public int getAgencia() {
		return agencia;
	}

	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	// SACAR

	public boolean sacar(float valor) {

		if (this.getSaldo() < valor) {
			System.out.println("\n Saldo Insuficiente!");
			return false;

		}
		this.setSaldo(this.getSaldo() - valor);
		return true;
	}

	// DEPOSITAR
	public void depositar(float valor) {
		this.setSaldo(this.getSaldo() + valor);

	}		
		
	// VISULAIZAR

	public	void visualizar() {  // CRIOU SEM PARAMENTRO

		String tipo = ""; // TIPO CONCORRENTE OU POUPANCA 

		switch (this.tipo) {
		case 1:
			tipo = "Conta Corrente";
			break;
		case 2:
			tipo = "Conta Poupança";
			break;
		}

		System.out.println("\n\n***********************************************************");
		System.out.println("Dados da Conta:");
		System.out.println("***********************************************************");
		System.out.println("Numero da Conta: " + this.numero);
		System.out.println("Agência: " + this.agencia);
		System.out.println("Tipo da Conta: " + tipo);
		System.out.println("Titular: " + this.titular);
		System.out.println("Saldo: " + this.saldo);

	}
}
