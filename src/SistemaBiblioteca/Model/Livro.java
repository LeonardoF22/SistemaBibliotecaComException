package SistemaBiblioteca.Model;

import SistemaBiblioteca.Exception.AutorInvalidoException;
import SistemaBiblioteca.Exception.DevolverLivroException;
import SistemaBiblioteca.Exception.EmprestarLivroException;
import SistemaBiblioteca.Exception.TituloInvalidoException;

public class Livro {
    private int id;
    private static int proxId = 1;
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro(String titulo, String autor) throws TituloInvalidoException, AutorInvalidoException{
        if(titulo == null || titulo.isBlank()){
            throw new TituloInvalidoException("Titulo invalido!");
        }
        if(autor == null || autor.isBlank()){
            throw new AutorInvalidoException("Autor invalido!");
        }

        id = proxId;
        proxId++;
        this.titulo = titulo;
        this.autor = autor;
        disponivel = true;
    }

    @Override
    public String toString(){
        return "\nID: " + id + "\nTitulo: " + titulo + "\nAutor: " + autor + "\nDisponivel: " + disponivel;
    }

    public void emprestarLivro() throws EmprestarLivroException{
        if(!disponivel){
            throw new EmprestarLivroException("O livro já está emprestado!");
        }
        disponivel = false;
    }

    public void devolverLivro() throws DevolverLivroException{
        if(disponivel){
            throw new DevolverLivroException("O livro já foi devolvido!");
        }
        disponivel = true;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}
