package SistemaBiblioteca;

import SistemaBiblioteca.Exception.*;
import SistemaBiblioteca.Model.Biblioteca;
import SistemaBiblioteca.Model.Livro;
import SistemaBiblioteca.Model.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner sc = new Scanner(System.in);
        int op;
        do {
            exibirMenu();
            op = escolherOp(sc);
            sc.nextLine();
            switch (op) {
                case 0:
                    System.out.println("Encerrando programa!");
                    return;
                case 1:
                    System.out.println("Cadastrando livro");
                    cadastrarLivro(biblioteca, sc);
                    break;
                case 2:
                    System.out.println("Cadastrando usuario");
                    cadastrarUsuario(biblioteca, sc);
                    break;
                case 3:
                    System.out.println("Emprestando livro");
                    emprestarLivro(biblioteca, sc);
                    break;
                case 4:
                    System.out.println("Devolvendo livro");
                    devolverLivro(biblioteca, sc);
                    break;
                case 5:
                    System.out.println("Buscando livro");
                    buscarLivro(biblioteca, sc);
                    break;
                case 6:
                    System.out.println("Buscando usuario");
                    buscarUsuario(biblioteca, sc);
                    break;
                case 7:
                    System.out.println("Livros cadastrados");
                    exibirLivros(biblioteca);
                    break;
                case 8:
                    System.out.println("Usuarios cadastrados: ");
                    exibirUsuario(biblioteca);
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        } while (op != 0);
        System.out.println("Programa encerrado");
    }

    public static void exibirMenu() {
        System.out.println("========= BIBLIOTECA =========\n" +
                "\n" +
                "1 - Cadastrar livro\n" +
                "2 - Cadastrar usuário\n" +
                "3 - Emprestar livro\n" +
                "4 - Devolver livro\n" +
                "5 - Buscar livro\n" +
                "6 - Buscar usuário\n" +
                "7 - Listar livros\n" +
                "8 - Listar usuários\n" +
                "0 - Sair");
    }

    public static int escolherOp(Scanner sc) {
        while (true) {
            try {
                System.out.println("Opcao desejada: ");
                int op = sc.nextInt();
                return op;
            } catch (InputMismatchException e) {
                System.out.println("Opcao invalida!");
                sc.nextLine();
            }
        }
    }

    public static String obterTexto(Scanner sc, String mensagem) {
        while (true) {
            System.out.println("Digite o " + mensagem + ": ");
            String texto = sc.nextLine();
            while (texto == null || texto.isBlank()) {
                System.out.println(mensagem + " invalido");
                System.out.println("Digite o " + mensagem + ": ");
                texto = sc.nextLine();
            }
            return texto;
        }
    }

    public static void cadastrarLivro(Biblioteca biblioteca, Scanner sc) {
        try {
            String titulo = obterTexto(sc, "Titulo");
            String autor = obterTexto(sc, "Autor");
            Livro livro = new Livro(titulo, autor);
            biblioteca.cadastrarLivro(livro);
            System.out.println("Livro cadastrado com sucesso!");
        } catch (TituloInvalidoException | AutorInvalidoException | LivroInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void cadastrarUsuario(Biblioteca biblioteca, Scanner sc) {
        try {
            String nome = obterTexto(sc, "Nome");
            Usuario usuario = new Usuario(nome);
            biblioteca.cadastrarUsuario(usuario);
            System.out.println("Usuario cadastrado com sucesso!");
        } catch (NomeInvalidoException | UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int obterId(Scanner sc, String mensagem) {
        while (true) {
            try {
                System.out.println(mensagem);
                int id = sc.nextInt();
                return id;
            } catch (InputMismatchException e) {
                System.out.println("Id invalido!");
                sc.nextLine();
            }
        }
    }

    public static void emprestarLivro(Biblioteca biblioteca, Scanner sc) {
        try {
            int idLivro = obterId(sc, "ID Livro: ");
            int idUsuario = obterId(sc, "ID Usuario: ");
            biblioteca.emprestarLivro(idLivro, idUsuario);
            System.out.println("Livro emprestado com sucesso");
        } catch (LivroInvalidoException | UsuarioInvalidoException | EmprestarLivroException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void devolverLivro(Biblioteca biblioteca, Scanner sc) {
        try {
            int idLivro = obterId(sc, "ID Livro: ");
            int idUsuario = obterId(sc, "ID Usuario: ");
            biblioteca.devolverLivro(idLivro, idUsuario);
            System.out.println("Livro devolvido com sucesso");
        } catch (LivroInvalidoException | UsuarioInvalidoException | DevolverLivroException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscarLivro(Biblioteca biblioteca, Scanner sc) {
        try {
            int id = obterId(sc, "Id do livro: ");
            Livro livro = biblioteca.buscarLivro(id);
            System.out.println(livro);
        } catch (LivroInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void buscarUsuario(Biblioteca biblioteca, Scanner sc) {
        try {
            int id = obterId(sc, "Id do usuario: ");
            Usuario usuario = biblioteca.buscarUsuario(id);
            System.out.println(usuario);
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void exibirLivros(Biblioteca biblioteca) {
        try {
            biblioteca.listarLivros();
        } catch (LivroInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void exibirUsuario(Biblioteca biblioteca) {
        try {
            biblioteca.listarUsuarios();
        } catch (UsuarioInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }
}
