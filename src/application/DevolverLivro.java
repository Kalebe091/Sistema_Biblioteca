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

public class DevolverLivro extends Application {
    private Biblioteca biblioteca;

    public DevolverLivro(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void start(Stage primaryStage) {
        // Criação do ComboBox para exibir os livros não disponíveis
        ComboBox<String> comboBox = new ComboBox<>();
        biblioteca.getLivros().stream()
            .filter(livro -> !livro.isDisponivel())
            .map(livro -> livro.getTitulo() + " - " + livro.getAutor())
            .forEach(comboBox.getItems()::add);
        
        // Botão "Devolver" para marcar o livro como devolvido
        Button btnDevolver = new Button("Devolver");
        btnDevolver.setOnAction(e -> {
            String selected = comboBox.getValue();
            if (selected != null) {
                biblioteca.getLivros().stream()
                    .filter(livro -> (livro.getTitulo() + " - " + livro.getAutor()).equals(selected))
                    .findFirst()
                    .ifPresent(livro -> {
                        biblioteca.devolverLivro(livro);
                        primaryStage.close();
                    });
            }
        });
        
        // Título e subtítulo
        Text titulo = new Text("SGB UEPA");
        titulo.setFont(Font.font("Arial", 24));

        Text subtitulo = new Text("Sistema de Gerenciamento de Biblioteca da UEPA");
        subtitulo.setFont(Font.font("Arial", 16));

        Button botaoVoltar = new Button("Voltar");
        botaoVoltar.setOnAction(e -> primaryStage.close());
        
        // Organização dos elementos na interface
        HBox botoesContainer = new HBox(10, btnDevolver, botaoVoltar);
        botoesContainer.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(titulo, subtitulo, comboBox, botoesContainer);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SGB - UEPA");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
