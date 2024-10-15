public class Musicas {
    private String caminhoMusica;
    private String artista;
    private String album;
    private String nomeMusica;
    private String caminhoFotoAlbum;

    public Musicas() {
        this.caminhoMusica = "C:\\Users\\matheus.fgs\\Desktop\\PlayerMusical\\Musicas\\Micheal_Jackson\\Xcape\\Chicago.mp3";
        this.artista = "Michael Jackson";
        this.album = "Xcape";
        this.nomeMusica = "Chicago";
        this.caminhoFotoAlbum = "C:\\Users\\matheus.fgs\\Desktop\\PlayerMusical\\Musicas\\Micheal_Jackson\\Xcape\\XcapeAlbumImage.png";
    }

    public static Musicas criarMusicaDontStopTilYouGetEnough() {
        Musicas musica = new Musicas();
        musica.caminhoMusica = "C:\\Users\\matheus.fgs\\Desktop\\PlayerMusical\\Musicas\\Micheal_Jackson\\OffTheWall\\Don_t _Stop _Til You_Get_Enough.mp3";
        musica.artista = "Michael Jackson";
        musica.album = "Off the Wall";
        musica.nomeMusica = "Don't Stop 'Til You Get Enough";
        musica.caminhoFotoAlbum = "C:\\Users\\matheus.fgs\\Desktop\\PlayerMusical\\Musicas\\Micheal_Jackson\\OffTheWall\\OffTheWallImage.png";
        return musica;
    }

    public static Musicas criarMusicaSaudadesMil() {
        Musicas musica = new Musicas();
        musica.caminhoMusica = "C:\\Users\\matheus.fgs\\Desktop\\PlayerMusical\\Musicas\\Dexter\\Proverbios13\\Saudades_Mill.mp3";
        musica.artista = "Dexter";
        musica.album = "Proverbios 13";
        musica.nomeMusica = "Saudades Mil";
        musica.caminhoFotoAlbum = "C:\\Users\\matheus.fgs\\Desktop\\PlayerMusical\\Musicas\\Dexter\\Proverbios13\\AlbumImage.png";
        return musica;
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

    public String getNomeMusica() {
        return nomeMusica;
    }

    public String getCaminhoFotoAlbum() {
        return caminhoFotoAlbum;
    }
}