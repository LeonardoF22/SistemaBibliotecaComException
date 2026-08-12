package SistemaBiblioteca.Model;

import SistemaBiblioteca.Exception.NomeInvalidoException;

import java.util.ArrayList;

public class Usuario {
    private int id;
    private static int proxId = 1;
    private String nome;
    private ArrayList<Livro> livrosEmprestados;

    public Usuario(String nome) throws NomeInvalidoException{
        if(nome == null || nome.isBlank()){
            throw new NomeInvalidoException("Nome invalido!");
        }

        id = proxId;
        proxId++;
        this.nome = nome;
        livrosEmprestados = new ArrayList<>();
    }

    public void emprestarLivro(Livro livro){
        livrosEmprestados.add(livro);
    }

    public void devolverLivro(Livro livro){
        livrosEmprestados.remove(livro);
    }

    public String stringLivrosEmprestados(){
        if(livrosEmprestados.isEmpty()){
            return "Nenhum livro emprestado";
        }
        String livrosString = "";
        for(Livro livro : livrosEmprestados){
            livrosString += livro.getTitulo() + "\n" + livro.getAutor() + "\n";
        }
        return livrosString;
    }

    @Override
    public String toString(){
        return "ID: " + id + "\nNome: " + nome + "\nLivros emprestados: \n" + stringLivrosEmprestados();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }
}
