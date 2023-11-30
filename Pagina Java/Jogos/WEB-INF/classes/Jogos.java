package database;

public class Jogos {
    private static long id_jogo;
    private static String nome_jogo;
    private static String categoria;
    private static String estudio;
    private static double preco;
    private static byte[] foto;

    
    public Jogos() {
        setId(0);
        setNome("");
        setCategoria("");
        setEstudio("");
        setPreco(0.0);
        setFoto(null);
    }

    public static void setId(long i) {
        id_jogo = i;
    }

    public static void setNome(String n) {
        nome_jogo = n;
    }

    public static void setCategoria(String n) {
        categoria = n;
    }

    public static void setEstudio(String n) {
        estudio = n;
    }

    public static void setPreco(double p) {
        preco = p;
    }

    public static void setFoto(byte[] f) {
        foto = f;
    }

    public static long getId() {
        return id_jogo;
    }

    public static String getNome() {
        return nome_jogo;
    }

    public static byte[] getFoto() {
        return foto;
    }

    public static String getCategoria() {
        return categoria;
    }

    public static String getEstudio() {
        return estudio;
    }

    public static double getPreco() {
        return preco;
    }

}
