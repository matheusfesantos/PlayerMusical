import java.io.File;

public class Musicas {
    private final String caminhoMusica;
    private final String artista;
    private final String album;
    private final String caminhoFotoAlbum;
    private final String nomeMusica;

    public Musicas() {
        this("C:\\Users\\matheus.fgs\\Desktop\\PlayerMusical\\Musicas\\MichealJackson\\Xcape\\Chicago.mp3",
                "Micheal Jackson", "Xscape",
                "C:\\Users\\matheus.fgs\\Desktop\\PlayerMusical\\Musicas\\MichealJackson\\Xcape\\Xcape_Album_Image.png",
                "Chicago");
    }

    public Musicas(String caminhoMusica, String artista, String album, String caminhoFotoAlbum, String nomeMusica) {
        if (new File(caminhoMusica).exists() && new File(caminhoFotoAlbum).exists()) {
            this.caminhoMusica = caminhoMusica;
            this.artista = artista;
            this.album = album;
            this.caminhoFotoAlbum = caminhoFotoAlbum;
            this.nomeMusica = nomeMusica;
        } else {
            throw new IllegalArgumentException("IMAGEM AVISANDO QUE NÃO FOI ENCONTRADA A IMAGEM DO ALBUM");
        }
    }

    public static Musicas criarMusicaDontStopTilYouGetEnough() {
        return new Musicas(
                "C:\\Users\\matheus.fgs\\Desktop\\PlayerMusical\\Musicas\\MichealJackson\\OffTheWall\\Don_t Stop _Til You Get Enough(MP3_320K).mp3",
                "Micheal Jackson",
                "Off The Wall",
                "C:\\Users\\matheus.fgs\\Desktop\\PlayerMusical\\Musicas\\MichealJackson\\OffTheWall\\OffTheWallAlbumImage.png",
                "Don't Stop 'Til You Get Enough"
        );
    }

    public String getCaminhoMusica() {
        return caminhoMusica;
    }

    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public String getCaminhoFotoAlbum() {
        return caminhoFotoAlbum;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    @Override
    public String toString() {
        return "Música: " + nomeMusica + " | Artista: " + artista + " | Álbum: " + album;
    }
}