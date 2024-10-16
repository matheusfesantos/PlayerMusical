import javafx.animation.FadeTransition;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

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

        Musicas musicaAtual = listaMusicas.get(musicaAtualIndex);
        Musicas musicAnterior = getMusicaAnterior();
        Musicas proximaMusica = getProximaMusica();

        BorderPane player = new BorderPane();
        player.setStyle("-fx-background-image: url('" + getClass().getResource("/Styles/Imagens/Space.gif").toExternalForm() + "');" +
                "-fx-background-size: cover;");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setId("Vbox");

        Label artistaLabel = new Label("Artista: " + musicaAtual.getArtista());
        artistaLabel.setId("Artista");
        Label albumLabel = new Label("Álbum: " + musicaAtual.getAlbum());
        albumLabel.setId("Album-Label");
        Label musicaLabel = new Label("Música: " + musicaAtual.getNomeMusica());
        musicaLabel.setId("Musica-Label");
        vbox.getChildren().addAll(artistaLabel, albumLabel, musicaLabel);

        ImageView anteriorAlbumImageView = new ImageView();
        Image anteriorAlbumImage = new Image("file:" + musicAnterior.getCaminhoFotoAlbum());
        anteriorAlbumImageView.setImage(anteriorAlbumImage);
        anteriorAlbumImageView.setFitWidth(150);
        anteriorAlbumImageView.setPreserveRatio(true);
        anteriorAlbumImageView.setId("AnteriorAlbum");

        ImageView albumImageView = new ImageView();
        Image albumImage = new Image("file:" + musicaAtual.getCaminhoFotoAlbum());
        albumImageView.setImage(albumImage);
        albumImageView.setFitWidth(200);
        albumImageView.setPreserveRatio(true);
        albumImageView.setId("AlbumImage");

        ImageView proximaAlbumImageView = new ImageView();
        Image proximaAlbumImage = new Image("file:" + proximaMusica.getCaminhoFotoAlbum());
        proximaAlbumImageView.setImage(proximaAlbumImage);
        proximaAlbumImageView.setFitWidth(150);
        proximaAlbumImageView.setPreserveRatio(true);
        proximaAlbumImageView.setId("ProximoAlbum");

        HBox albumsBox = new HBox(20);
        albumsBox.setAlignment(Pos.CENTER);
        albumsBox.getChildren().addAll(anteriorAlbumImageView, albumImageView, proximaAlbumImageView);

        vbox.getChildren().add(albumsBox);

        HBox controlsBox = new HBox(20);
        controlsBox.setAlignment(Pos.CENTER);

        Button musicaAnterior = new Button("Música Anterior");
        musicaAnterior.setId("musicaAnterior");
        musicaAnterior.setOnAction(e -> {
            if (musicaAtualIndex > 0) {
                musicaAtualIndex--;
                atualizarMusica(vbox);
            } else {
                System.out.println("Não há música anterior.");
            }
        });

        Button iniciar = new Button("Tocar");
        iniciar.setId("player-button");
        iniciar.setOnAction(e -> tocarMusica(musicaAtual));

        Button proximaMusicaButton = new Button("Próxima Música");
        proximaMusicaButton.setId("proximaMusica");
        proximaMusicaButton.setOnAction(e -> {
            if (musicaAtualIndex < listaMusicas.size() - 1) {
                musicaAtualIndex++;
                atualizarMusica(vbox);
            } else {
                System.out.println("Não há próxima música.");
            }
        });

        controlsBox.getChildren().addAll(musicaAnterior, iniciar, proximaMusicaButton);
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

    private void atualizarMusica(VBox vbox) {
        Musicas musicaAtual = listaMusicas.get(musicaAtualIndex);
        Musicas musicaAnterior = getMusicaAnterior();
        Musicas proximaMusica = getProximaMusica();

        ((Label) vbox.getChildren().get(0)).setText("Artista: " + musicaAtual.getArtista());
        ((Label) vbox.getChildren().get(1)).setText("Álbum: " + musicaAtual.getAlbum());
        ((Label) vbox.getChildren().get(2)).setText("Música: " + musicaAtual.getNomeMusica());

        Image anteriorAlbumImage = new Image("file:" + musicaAnterior.getCaminhoFotoAlbum());
        ((ImageView) ((HBox) vbox.getChildren().get(3)).getChildren().get(0)).setImage(anteriorAlbumImage);

        // Animar a troca da imagem do álbum atual
        Image albumImage = new Image("file:" + musicaAtual.getCaminhoFotoAlbum());
        ImageView albumImageView = (ImageView) ((HBox) vbox.getChildren().get(3)).getChildren().get(1);
        animarTrocaAlbum(albumImageView, albumImage);

        Image proximaAlbumImage = new Image("file:" + proximaMusica.getCaminhoFotoAlbum());
        ((ImageView) ((HBox) vbox.getChildren().get(3)).getChildren().get(2)).setImage(proximaAlbumImage);

        tocarMusica(musicaAtual);
    }

    private void animarTrocaAlbum(ImageView albumImageView, Image novaImagem) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), albumImageView);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(event -> {
            albumImageView.setImage(novaImagem);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(300), albumImageView);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });

        fadeOut.play();
    }

    private Musicas getMusicaAnterior() {
        if (musicaAtualIndex > 0) {
            return listaMusicas.get(musicaAtualIndex - 1);
        } else {
            return listaMusicas.get(musicaAtualIndex);
        }
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
