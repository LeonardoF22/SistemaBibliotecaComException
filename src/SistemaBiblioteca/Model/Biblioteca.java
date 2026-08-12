package SistemaBiblioteca.Model;

import SistemaBiblioteca.Exception.DevolverLivroException;
import SistemaBiblioteca.Exception.EmprestarLivroException;
import SistemaBiblioteca.Exception.LivroInvalidoException;
import SistemaBiblioteca.Exception.UsuarioInvalidoException;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;

    public Biblioteca(){
        livros = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void cadastrarLivro(Livro livro) throws LivroInvalidoException{
        if(livro == null){
            throw new LivroInvalidoException("Cadastro de livro não efetuado!");
        }
        livros.add(livro);
    }

    public void cadastrarUsuario(Usuario usuario) throws UsuarioInvalidoException{
        if(usuario == null){
            throw new UsuarioInvalidoException("Cadastro de usuario não efetuado!");
        }
        usuarios.add(usuario);
    }

    public Livro buscarLivro(int id) throws LivroInvalidoException{
        if(livros.isEmpty()){
            throw new LivroInvalidoException("Nenhum livro cadastrado no sistema!");
        }
        for(Livro livro : livros){
            if(livro.getId() == id){
                return livro;
            }
        }
        throw new LivroInvalidoException("Livro não cadastrado no sistema");
    }

    public Usuario buscarUsuario(int id) throws UsuarioInvalidoException{
        if(usuarios.isEmpty()){
            throw new UsuarioInvalidoException("Nenhum usuario cadastrado no sistema!");
        }
        for(Usuario usuario : usuarios){
            if(usuario.getId() == id){
                return usuario;
            }
        }
        throw new UsuarioInvalidoException("Usuario não cadastrado no sistema!");
    }

    public void listarLivros() throws LivroInvalidoException{
        if(livros.isEmpty()){
            throw new LivroInvalidoException("Nenhum livro cadastrado no sistema!");
        }
        for(Livro livro : livros){
            System.out.println(livro);
        }
    }

    public void listarUsuarios() throws UsuarioInvalidoException{
        if(usuarios.isEmpty()){
            throw new UsuarioInvalidoException("Nenhum usuario cadastrado no sistema");
        }
        for(Usuario usuario : usuarios){
            System.out.println(usuario);
        }
    }

    public void emprestarLivro(int idLivro, int idUsuario) throws LivroInvalidoException, UsuarioInvalidoException, EmprestarLivroException {
        Livro livro = buscarLivro(idLivro);
        Usuario usuario = buscarUsuario(idUsuario);

        if(usuario.getLivrosEmprestados().size() >= 3 || !livro.isDisponivel()){
            throw new EmprestarLivroException("Erro ao emprestar livro");
        }
        livro.emprestarLivro();
        usuario.emprestarLivro(livro);
    }

    public void devolverLivro(int idLivro, int idUsuario) throws LivroInvalidoException, UsuarioInvalidoException, DevolverLivroException {
        Livro livro = buscarLivro(idLivro);
        Usuario usuario = buscarUsuario(idUsuario);

        if(!usuario.getLivrosEmprestados().contains(livro)){
            throw new DevolverLivroException("Esse livro não foi emprestado atualmente por esse usuario!");
        }
        livro.devolverLivro();
        usuario.devolverLivro(livro);
    }
}
