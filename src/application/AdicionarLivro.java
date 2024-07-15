package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class AdicionarLivro extends Application {
    private Biblioteca biblioteca;

    public AdicionarLivro(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void start(Stage primaryStage) {
        // Título centralizado
        Text titulo = new Text("Adicionar Livro");
        titulo.setFont(Font.font("Calibri", 20));

        // Campos de texto
        TextField txtNomeLivro = new TextField();
        txtNomeLivro.setPromptText("Nome do Livro");
        TextField txtAutor = new TextField();
        txtAutor.setPromptText("Autor");

        // Botões "Adicionar" e "Cancelar"
        Button btnAdicionar = new Button("Adicionar");
        Button btnCancelar = new Button("Cancelar");

     // Ação do botão "Adicionar"
        btnAdicionar.setOnAction(e -> {
            String nomeLivro = txtNomeLivro.getText();
            String autor = txtAutor.getText();
            if (!nomeLivro.isEmpty() && !autor.isEmpty()) {
                Livro livro = new Livro(nomeLivro, autor);
                biblioteca.adicionarLivro(livro);
                System.out.println("Livro adicionado com sucesso");

            }
        });

     // Ação do botão "Cancelar"
        btnCancelar.setOnAction(e -> {
            System.out.println("Operação cancelada");
            // Feche a janela atual
            primaryStage.close();

        });
        // Layout
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(titulo, txtNomeLivro, txtAutor);

        HBox botoesBox = new HBox(10);
        botoesBox.setAlignment(Pos.CENTER);
        botoesBox.getChildren().addAll(btnAdicionar, btnCancelar);

        vbox.getChildren().add(botoesBox);

        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SGB - UEPA");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
