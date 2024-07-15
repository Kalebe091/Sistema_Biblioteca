package application;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;

    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void salvarLivros(String caminhoArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarLivros(String caminhoArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            livros = (List<Livro>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void emprestarLivro(Livro livro) {
        if (livro.isDisponivel()) {
            livro.setDisponivel(false);
        }
    }

    public void devolverLivro(Livro livro) {
        if (!livro.isDisponivel()) {
            livro.setDisponivel(true);
        }
    }

    public void gerarRelatorio(String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo)) {
            for (Livro livro : livros) {
                if (!livro.isDisponivel()) {
                    writer.write(livro.getTitulo() + " - " + livro.getAutor() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
