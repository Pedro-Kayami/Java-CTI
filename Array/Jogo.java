class Jogos implements Comparable<Jogos> {
    private long id_jogo;
    private String nome_jogo;
    private String categoria;
    private String estudio;
    private double preco;
    private byte[] foto;

    public int compareTo(Jogos outra) {
        if (this.nome_jogo.compareTo(outra.getNome()) < 0)
            return -1;
        else if (this.nome_jogo.compareTo(outra.getNome()) > 0)
            return 1;
        else
            return 0;
    }

    Jogos() {
        setId(0);
        setNome("");
        setCategoria("");
        setEstudio("");
        setPreco(0.0);
        setFoto(null);
    }

    public void setId(long i) {
        this.id_jogo = i;
    }

    public void setNome(String n) {
        this.nome_jogo = n;
    }

    public void setCategoria(String n) {
        this.categoria = n;
    }

    public void setEstudio(String n) {
        this.estudio = n;
    }

    public void setPreco(double p) {
        this.preco = p;
    }

    public void setFoto(byte[] f) {
        this.foto = f;
    }

    public long getId() {
        return this.id_jogo;
    }

    public String getNome() {
        return this.nome_jogo;
    }

    public byte[] getFoto() {
        return this.foto;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public String getEstudio() {
        return this.estudio;
    }

    public double getPreco() {
        return this.preco;
    }

}