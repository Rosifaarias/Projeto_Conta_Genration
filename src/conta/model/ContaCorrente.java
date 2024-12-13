package conta.model;

public class ContaCorrente extends Conta { // extends => herança/ herda

	private float limite;

	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) { // metodo

		super(numero, agencia, tipo, titular, saldo); //
		this.limite = limite;

	}

	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo) {
		super(numero, agencia, tipo, titular, saldo);
		this.limite = 5000.0f; // valor default
	}

//**********************************************************************************************************
	public float getLimite() {  // METODDO DE ACESSO
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;

	}
//**************************************************************************************************************
	// METODO SACAR ESSE METODO FOU SOBRESCRITO 
	public boolean sacar(float valor) {

		if (this.getSaldo() + this.getLimite() < valor) {
			System.out.println("\n Saldo Insuficiente!");
			return false;
		}

		this.setSaldo(this.getSaldo() - valor);
		return true;

	}

	public void visualizar() {
		super.visualizar();
		System.out.println("Limite de Crédito: " + this.limite);
	}

}
