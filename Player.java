import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Player extends Application {
    private List<Musicas> listaMusicas;
    private int musicaAtualIndex = 0;
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("PlayerMusical");

        listaMusicas = new ArrayList<>();
        listaMusicas.add(new Musicas());
        listaMusicas.add(Musicas.criarMusicaDontStopTilYouGetEnough());
        listaMusicas.add(Musicas.criarMusicaSaudadesMil());

        Musicas musica = listaMusicas.get(musicaAtualIndex);

        BorderPane player = new BorderPane();
        player.setStyle("-fx-background-image: url('" + getClass().getResource("/Styles/Imagens/Space.gif").toExternalForm() + "');" +
                "-fx-background-size: cover;");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setId("Vbox");

        Label artistaLabel = new Label("Artista: " + musica.getArtista());
        vbox.getChildren().add(artistaLabel);

        Label albumLabel = new Label("Álbum: " + musica.getAlbum());
        vbox.getChildren().add(albumLabel);

        Label musicaLabel = new Label("Música: " + musica.getNomeMusica());
        vbox.getChildren().add(musicaLabel);

        ImageView albumImageView = new ImageView();
        Image albumImage = new Image("file:" + musica.getCaminhoFotoAlbum());
        albumImageView.setId("AlbumImage");
        albumImageView.setImage(albumImage);
        albumImageView.setFitWidth(200);
        albumImageView.setPreserveRatio(true);
        vbox.getChildren().add(albumImageView);

        Label proximaMusicaLabel = new Label("Próxima Música: " + getProximaMusica().getNomeMusica());
        vbox.getChildren().add(proximaMusicaLabel);

        Region space1 = new Region();
        space1.setPrefHeight(50);
        vbox.getChildren().add(space1);

        HBox controlsBox = new HBox(20);
        controlsBox.setAlignment(Pos.CENTER);

        Button musicaAnterior = new Button("Música Anterior");
        musicaAnterior.setId("musicaAnterior");
        controlsBox.getChildren().add(musicaAnterior);
        musicaAnterior.setOnAction(e -> {
            if (musicaAtualIndex > 0) {
                musicaAtualIndex--;
                atualizarMusica(vbox, proximaMusicaLabel);
            } else {
                System.out.println("Não há música anterior.");
            }
        });

        Button iniciar = new Button("Tocar");
        iniciar.setId("player-button");
        controlsBox.getChildren().add(iniciar);
        iniciar.setOnAction(e -> {
            tocarMusica(musica);
        });

        Button proximaMusica = new Button("Próxima Música");
        proximaMusica.setId("proximaMusica");
        controlsBox.getChildren().add(proximaMusica);
        proximaMusica.setOnAction(e -> {
            if (musicaAtualIndex < listaMusicas.size() - 1) {
                musicaAtualIndex++;
                atualizarMusica(vbox, proximaMusicaLabel);
            } else {
                System.out.println("Não há próxima música.");
            }
        });

        vbox.getChildren().add(controlsBox);

        player.setCenter(vbox);

        HBox footer = new HBox(10);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(10));
        footer.setId("footer");

        Label rodapeLabel = new Label("By Matheus Ferreira");
        rodapeLabel.setId("rodape");

        footer.getChildren().addAll(rodapeLabel);
        player.setBottom(footer);

        Scene mainScene = new Scene(player, 900, 540);
        mainScene.getStylesheets().add(getClass().getResource("/Styles/Player.css").toExternalForm());

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void tocarMusica(Musicas musica) {
        String caminhoMusica = musica.getCaminhoMusica();
        System.out.println("Tocando: " + caminhoMusica);

        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            Media media = new Media(new File(caminhoMusica).toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            mediaPlayer.setOnError(() -> {
                System.err.println("Erro no MediaPlayer: " + mediaPlayer.getError().getMessage());
            });

            mediaPlayer.play();
        } catch (Exception ex) {
            System.err.println("Erro ao tentar tocar a música: " + ex.getMessage());
        }
    }

    private void atualizarMusica(VBox vbox, Label proximaMusicaLabel) {
        Musicas musica = listaMusicas.get(musicaAtualIndex);

        ((Label) vbox.getChildren().get(0)).setText("Artista: " + musica.getArtista());
        ((Label) vbox.getChildren().get(1)).setText("Álbum: " + musica.getAlbum());
        ((Label) vbox.getChildren().get(2)).setText("Música: " + musica.getNomeMusica());

        Image albumImage = new Image("file:" + musica.getCaminhoFotoAlbum());
        ((ImageView) vbox.getChildren().get(3)).setImage(albumImage);

        tocarMusica(musica);
        proximaMusicaLabel.setText("Próxima Música: " + getProximaMusica().getNomeMusica());
    }

    private Musicas getProximaMusica() {
        if (musicaAtualIndex < listaMusicas.size() - 1) {
            return listaMusicas.get(musicaAtualIndex + 1);
        } else {
            return listaMusicas.get(musicaAtualIndex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
