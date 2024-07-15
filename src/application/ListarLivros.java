package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ListarLivros extends Application {
    private Biblioteca biblioteca;

    public ListarLivros(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    @Override
    public void start(Stage primaryStage) {
        // Título centralizado
        Text titulo = new Text("Acervo de Livros - SGB UEPA");
        titulo.setFont(Font.font("Calibri", 20));

        // Gera uma lista com os títulos já adiconados
        ListView<String> listView = new ListView<>();
        biblioteca.getLivros().stream()
            .filter(Livro::isDisponivel)
            .forEach(livro ->
                listView.getItems().add(
                    livro.getTitulo() + " - " + livro.getAutor()
                )
            );

        // Fecha a janela e volta ao Menu Principal
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> primaryStage.close());

        // Organização dos elementos na interface
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER); // Centraliza o conteúdo verticalmente
        vbox.getChildren().addAll(titulo, listView, btnVoltar);

        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SGB - UEPA");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
