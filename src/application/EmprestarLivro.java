package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EmprestarLivro extends Application {
    private Biblioteca biblioteca;

    public EmprestarLivro(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void start(Stage primaryStage) {
        // Busca os títulos disponíveis no acervo e lista para o usuário
        ComboBox<String> comboBox = new ComboBox<>();
        biblioteca.getLivros().stream()
            .filter(Livro::isDisponivel)
            .map(livro -> livro.getTitulo() + " - " + livro.getAutor())
            .forEach(comboBox.getItems()::add);

        // Reserva o título selecionado
        Button btnEmprestar = new Button("Emprestar");
        btnEmprestar.setOnAction(e -> {
            String selected = comboBox.getValue();
            if (selected != null) {
                biblioteca.getLivros().stream()
                    .filter(livro -> (livro.getTitulo() + " - " + livro.getAutor()).equals(selected))
                    .findFirst()
                    .ifPresent(livro -> {
                        biblioteca.emprestarLivro(livro);
                        primaryStage.close();
                        gerarRelatorio();
                    });
            }
        });

        Text titulo = new Text("SGB UEPA");
        titulo.setFont(Font.font("Arial", 24));

        Text subtitulo = new Text("Sistema de Gerenciamento de Biblioteca da UEPA");
        subtitulo.setFont(Font.font("Arial", 16));

        // Fecha a janela e volta ao menu principal
        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setOnAction(e -> primaryStage.close());

        // Organização dos elementos na interface
        HBox botoesContainer = new HBox(10, btnEmprestar, botaoVoltar);
        botoesContainer.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(titulo, subtitulo, comboBox, botoesContainer);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SGB - UEPA");
        primaryStage.show();
    }

    // Método para gerar o relatório e salvar em um arquivo
    private void gerarRelatorio() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("relatorio_emprestimos.txt"))) {
            writer.println("Relatório de Livros Emprestados:");
            biblioteca.getLivros().stream()
                .filter(livro -> !livro.isDisponivel())
                .forEach(livro -> writer.println(livro.getTitulo() + " - " + livro.getAutor()));
            System.out.println("Relatório gerado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gerar o relatório: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
