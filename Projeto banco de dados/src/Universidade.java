import java.sql.*;

public class Universidade {
    private static final String URL = "jdbc:sqlite:C:\\Users\\LabInfo\\Desktop\\Trabalhos Java\\Trabalho Avaliativo\\banco.db";
    private Connection connection;

    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
            connection.setAutoCommit(false);
            System.out.println("Sucesso ao entrar no Banco de dados: " + URL);
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexão fechada.");
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }

    public void createTables() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Alunos (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nome VARCHAR, CPF VARCHAR)");
            statement.execute("CREATE TABLE IF NOT EXISTS Professores (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nome VARCHAR, CPF VARCHAR)");
            statement.execute("CREATE TABLE IF NOT EXISTS Aulas (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nome VARCHAR, ProfessorID INTEGER, FOREIGN KEY (ProfessorID) REFERENCES Professores(ID))");
            statement.execute("CREATE TABLE IF NOT EXISTS Matriculas (AlunoID INTEGER, AulaID INTEGER, FOREIGN KEY (AlunoID) REFERENCES Alunos(ID), FOREIGN KEY (AulaID) REFERENCES Aulas(ID))");







            connection.commit();
            System.out.println("Tabelas criadas.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }

    public void cadastrarAluno(String nome, String cpf) {
        try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Alunos(Nome, CPF) VALUES (?, ?)")) {
            insertStatement.setString(1, nome);
            insertStatement.setString(2, cpf);
            insertStatement.executeUpdate();
            connection.commit();
            System.out.println("Aluno cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    public void cadastrarProfessor(String nome, String cpf) {
        try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Professores(Nome, CPF) VALUES (?, ?)")) {
            insertStatement.setString(1, nome);
            insertStatement.setString(2, cpf);
            insertStatement.executeUpdate();
            connection.commit();
            System.out.println("Professor cadastrado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar professor: " + e.getMessage());
        }
    }
}
