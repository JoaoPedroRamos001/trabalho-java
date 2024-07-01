import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Universidade universidade = new Universidade();

    public static void main(String[] args) {
        universidade.connect();
        universidade.createTables();

        boolean executar = true;
        while (executar) {
            imprimirMenu();
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    cadastrarAluno();
                    break;
                case "2":
                    cadastrarProfessor();
                    break;
                case "3":
                    listarAlunosDeAula();
                    break;
                case "4":
                    visualizarCadastro();
                    break;
                case "5":
                    excluirCadastro();
                    break;
                case "6":
                    executar = false;
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida, escolha outra opção.");
                    break;
            }
        }

        universidade.close();
    }

    public static void imprimirMenu() {
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Cadastrar Professor");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void cadastrarAluno() {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do aluno: ");
        String cpf = scanner.nextLine();

        universidade.cadastrarAluno(nome, cpf);
    }

    public  static void cadastrarProfessor() {
        System.out.print("Nome do professor: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do professor: ");
        String cpf = scanner.nextLine();

        universidade.cadastrarProfessor(nome, cpf);
    }}

