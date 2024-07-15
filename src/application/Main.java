package application;

// Kalebe Silva de Vasconcelos
// Engenharia de Software - Forma Pará
// Programação Orientada a Objetos II

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Main extends Application {
    private Biblioteca biblioteca;

    @Override
    public void start(Stage primaryStage) {
       
    	
        biblioteca = new Biblioteca();
        biblioteca.carregarLivros("acervo.dat");
        
        // Cria os botões e define suas ações
        Button btnAdicionar = new Button("Adicionar Livro");
        btnAdicionar.setOnAction(e -> new AdicionarLivro(biblioteca).start(new Stage()));

        Button btnListar = new Button("Listar Livros");
        btnListar.setOnAction(e -> new ListarLivros(biblioteca).start(new Stage()));

        Button btnEmprestar = new Button("Emprestar Livro");
        btnEmprestar.setOnAction(e -> new EmprestarLivro(biblioteca).start(new Stage()));

        Button btnDevolver = new Button("Devolver Livro");
        btnDevolver.setOnAction(e -> new DevolverLivro(biblioteca).start(new Stage()));
        

        // Cria um HBox para os botões com espaçamento
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(btnAdicionar, btnListar, btnEmprestar, btnDevolver);
        hbox.setAlignment(Pos.CENTER);

        // Cria um VBox para o título e subtítulo
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        Text titulo = new Text("SGB UEPA");
        titulo.setFont(Font.font("Arial", 24));

        Text subtitulo = new Text("Sistema de Gerenciamento de Biblioteca da UEPA");
        subtitulo.setFont(Font.font("Arial", 16));

        vbox.getChildren().addAll(titulo, subtitulo, hbox);

        Scene scene = new Scene(vbox, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SGB - UEPA");
        primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
