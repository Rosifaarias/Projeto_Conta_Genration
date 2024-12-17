package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {

	public static void main(String[] args) {

		Scanner leia = new Scanner(System.in);

		ContaController contas = new ContaController();

		int opcao = 0, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;

		Conta cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000f, 100.0f);
		Conta cc2 = new ContaCorrente(contas.gerarNumero(), 124, 1, "Maria da Silva", 2000.0f, 100.0f);
		contas.cadastrar(cc1);
		contas.cadastrar(cc2);

		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 123, 2, "Mariana dos Santos", 4000.0f, 12);
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 123, 2, "Juliana Ramos", 8000.0f, 15);
		contas.cadastrar(cp1);
		contas.cadastrar(cp2);

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println(Cores.TEXT_WHITE + "\nBanco do Brazil com Z - O seu futuro começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "\n Criar Conta");
				System.out.println(" Digite o Numero da Agencia: ");
				agencia = leia.nextInt();
				System.out.println(" Digite o nome do titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();

				do {
					System.out.println("\n Digite o tipo da Conta (1-CC OU 2- CP): ");
					tipo = leia.nextInt();

				} while (tipo < 1 && tipo > 2);

				System.out.println("\nDigite o saldo da conta(R$): ");
				saldo = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o limite de crédito (R$): ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do aniversario da conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}

				}

				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE + "\n Listar todas as Contas: ");

				contas.listarTodas();

				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "\n Consultar dados da Conta - por número\n\n");
				System.out.println(" Digite o número da conta: ");
				numero = leia.nextInt();

				contas.procurarPorNumero(numero);

				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "\n Atualizar dados da Conta");
				System.out.println("Digite o número da conta:  ");
				numero = leia.nextInt();

				var buscaConta = contas.bucarNaCollection(numero);
				if (buscaConta != null) {

					tipo = buscaConta.getTipo();

					System.out.println("Digite o número da Agência :  ");
					agencia = leia.nextInt();
					System.out.println("Digite o Nome do Titular:  ");
					leia.skip("\\R?");
					titular = leia.nextLine();

					System.out.println("Digite o Saldo da conta (R$) :   ");
					saldo = leia.nextFloat();

					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o limite de crédito (R$) : ");
						limite = leia.nextFloat();

						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));

					}
					case 2 -> {

						System.out.println("Digite o dia do aniversário da conta:   ");
						aniversario = leia.nextInt();

						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));

					}
					default -> {

						System.out.println(" Tipo de conta inválido!!!!   ");

					}

					}

				} else {
					System.out.println(" A conta não foi encontrada!   ");

				}

				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE + "\n Apagar Conta");

				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE + "\n Sacar");
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();

				do {
					System.out.println("Digite o valor  do saque (R$):   ");
					valor = leia.nextFloat();

				} while (valor <= 0);
				contas.sacar(numero, valor); // numero saque e valor a ser debitado

				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE + "\n Depósito\n\n");

				System.out.println(" Digite o número da conta: ");
				numero = leia.nextInt();

				do {
					System.out.println(" Digite o valor do depósito (R$) :  ");
					valor = leia.nextFloat();

				} while (valor <= 0);

				contas.depositar(numero, valor);

				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE + "\n Transferência entre contas \n\n");

				System.out.println(" Digite o número da conta de Origem:  ");
				numero = leia.nextInt();
				System.out.println(" Digite o número da conta de Destino:  ");
				numeroDestino = leia.nextInt();

				do {
					System.out.println(" Digite o valor da transferência (R$):   ");
					valor = leia.nextFloat();

				} while (valor <= 0);
				contas.transferir(numero, numeroDestino, valor);

				keyPress();
				break;
			default:
				System.out.println("\nOpção Inválida " + Cores.TEXT_RESET);

				keyPress();
				break;
			}
		}
	}

	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Rosilene Farias Domingues ");
		System.out.println("Generation Brasil - rosilene.farias00@gmail.com");
		System.out.println("https://github.com/Rosifaarias");
		System.out.println("********************************************************");
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}
}