//cmd C:\javacti\Sqlite>javac -cp .;".\lib\*" BancoxSql.java
//cmd C:\javacti\Sqlite>java -cp .;".\lib\*" BancoxSql
//dentro do geany javac -cp .;".\lib\*" %f
//dentro do geany java -cp ".;.\lib\*" "%e" 

import java.util.*;//scanner, arraylist, formatter
import java.io.*; //file
import javax.swing.*;//JOptionPane
import java.sql.*;//sql
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

class BancoSql {
    private File arqtest;
    private Scanner arqler, tecla;
    private ArrayList Lista;
    // id , c1=nome_pet, c2=foto_pet, c3=servico, c4=idade_pet, c5=tipoanimal;
    // long ,c1=varchar(50), c2=blob , c3=varchar(20), c4=integer , c5=varchar(20);

    private String id, nome, estudio, categoria, preco;
    private String nomearq, nomebanco, nometabela;
    private static byte[] fotojogo;

    private Connection con;
    private String url, drive, fsql;
    private ResultSet rs;
    private PreparedStatement pstmt;// sera usado com frase sql

    BancoSql(String nomeban)// construtor
    {
        con = null;
        rs = null;
        pstmt = null;

        // url = "jdbc:sqlite:.\\bancosql\\" + nomeban; Com Pasta
        url = "jdbc:sqlite:" + nomeban;
        drive = "org.sqlite.JDBC";// drive de conexao

        Lista = new ArrayList<>();
        // nomebanco = ".\\bancosql\\" + nomeban; Com pasta
        nomebanco = "" + nomeban;
        nometabela = "tabjogos";
    }

    public static void main(String args[]) {
        BancoSql b = new BancoSql("Jogos.db");
        b.conectar();
        // b.gravandotxt();
        b.lerTexto();

        // Código para listar
        // ArrayList<Jogos> listar = new ArrayList<Jogos>();
        // listar = b.getLista();
        // if (listar == null)
        // return;

        // for (int index = 0; index < listar.size(); index++) {
        // System.out.println("" + listar.get(index).getId());
        // System.out.println("" + listar.get(index).getNome());
        // System.out.println("" + listar.get(index).getEstudio());
        // System.out.println("" + listar.get(index).getCategoria());
        // System.out.println("" + listar.get(index).getPreco());
        // System.out.println("" + listar.get(index).getFoto());
        // }
        // Jogos jogos = new Jogos();
        // Adiciona Código
        // jogos.setNome("Pokemon");
        // jogos.setEstudio("Bandai");
        // jogos.setCategoria("RPG");
        // jogos.setPreco(780.00);
        // fotojogo = imageToByte("Pokemon.png");
        // jogos.setFoto(fotojogo);
        // b.adiciona(jogos);

        // Altera Código
        // jogos.setPreco(980.00);
        // jogos.setId(11);
        // jogos.setCategoria("Castro");
        // b.altera(jogos);

        // Remove Código
        // jogos.setId(11);
        // b.remove(jogos);
        b.desconectar();
    }// main

    public void conectar() {
        try {
            Class.forName(drive);
            con = DriverManager.getConnection(url);
            JOptionPane.showMessageDialog(null, "Conexão realizada !!!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro.conexao=" + e);
            return;
        }
    }// fim conectar

    public void desconectar() {
        try {
            if (this.con.isClosed() == false) {
                this.con.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erroaodesconectar" + e);
            return;
        }
        JOptionPane.showMessageDialog(null, "desconectou!!!");
    }// fim desconectar

    private static Jogos listar(int index) {
        return null;
    }

    public static byte[] imageToByte(String image) {
        InputStream is = null;
        byte[] buffer = null;
        try {
            is = new FileInputStream(image);
            buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            JOptionPane.showMessageDialog(null, "Deu certo");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "erro bytes foto=" + e);
        }
        return buffer;
    }/////////////////////////////////////////////////////

    public void adiciona(Jogos jogos) {
        String sql = "insert into tabjogos " +
                "(nome,estudio,categoria,preco,foto)" +
                " values (?,?,?,?,?)";
        try {
            // prepared statement para inserção
            PreparedStatement pstmt = con.prepareStatement(sql);
            Integer conta = 1;
            // seta os valores
            pstmt.setString(1, jogos.getNome());
            pstmt.setString(2, jogos.getEstudio());
            pstmt.setString(3, jogos.getCategoria());
            pstmt.setDouble(4, jogos.getPreco());
            pstmt.setBytes(5, jogos.getFoto());
            // executa
            pstmt.execute();
            pstmt.close();
            JOptionPane.showMessageDialog(null, "Adicionado com Sucesso ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro Inclusao!!!" + e);
            return;
        }
    }// Fim do adiciona

    public void altera(Jogos jogos) {
        fsql = "update tabjogos set nome=?, estudio=?, categoria=?," +
                "preco=?, foto=? where id_jogo=?";
        try {
            pstmt = con.prepareStatement(fsql);
            Integer conta = 1;
            pstmt.setString(conta++, jogos.getNome());
            pstmt.setString(conta++, jogos.getEstudio());
            pstmt.setString(conta++, jogos.getCategoria());
            pstmt.setDouble(conta++, jogos.getPreco());
            pstmt.setBytes(conta++, jogos.getFoto());
            pstmt.setLong(conta++, jogos.getId());
            pstmt.execute();
            pstmt.close();
            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi alterado!");
            return;
        }
    }

    public void remove(Jogos jogos) {
        fsql = "delete from tabjogos where id_jogo=?";
        if (procura(jogos) == false) {
            JOptionPane.showMessageDialog(null, "Não existe!");
            return;
        }
        try {
            Integer conta = 1;
            pstmt = con.prepareStatement(fsql);
            pstmt.setLong(conta++, jogos.getId());
            pstmt.execute();
            pstmt.close();
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi removido!");
        }
    }

    public boolean procura(Jogos jogos) {
        boolean flag = false;
        fsql = "select * from tabjogos where id_jogo=?";
        try {
            Integer conta = 1;
            pstmt = con.prepareStatement(fsql);
            pstmt.setLong(conta++, jogos.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) // achou
            {
                JOptionPane.showMessageDialog(null, "Achou Id=" + rs.getLong("id_jogo"));
                flag = true;
            } else {
                JOptionPane.showMessageDialog(null, " nao Achou Id=" + jogos.getId());
            }
            rs.close();
            pstmt.close();

            return flag;
        } catch (Exception el) {
            JOptionPane.showMessageDialog(null, "Erro procura=" + el);
            return false;
        }
    }

    public ArrayList<Jogos> getLista() {
        try {
            ArrayList<Jogos> vetor = new ArrayList<Jogos>();
            pstmt = con.prepareStatement("select * from tabjogos");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // criando o objeto Contato
                Jogos jogos = new Jogos();
                jogos.setId(rs.getLong("id_jogo"));
                jogos.setNome(rs.getString("nome"));
                jogos.setEstudio((rs.getString("estudio")));
                jogos.setCategoria(rs.getString("categoria"));
                jogos.setPreco(rs.getDouble("preco"));
                jogos.setFoto(rs.getBytes("foto"));
                // montando a data através do Calendar
                // adicionando o objeto à lista
                vetor.add(jogos);
            }
            rs.close();
            pstmt.close();
            JOptionPane.showMessageDialog(null, "Feita leittura Banco");
            return vetor;
        } catch (Exception el) {
            JOptionPane.showMessageDialog(null, "Erro Arraylist" + el);
            return null;
        }
    }

    public void ByteArrayToFileImage(byte[] bbb, String xid) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bbb);
            BufferedImage bImagex = ImageIO.read(bis);
            ImageIO.write(bImagex, "png", new File("foto" + xid + ".png"));
            System.out.println("image created" + xid);
        } catch (Exception erroi) {
            JOptionPane.showMessageDialog(null, "Erro imagem= " + erroi);
            return;
        }
    }

    public void gravandotxt() {
        try {
            pstmt = con.prepareStatement("select * from tabjogos");
            rs = pstmt.executeQuery();
            Formatter arquivo = new Formatter("Jogos.txt");
            while (rs.next()) {
                // criando o objeto Contato
                id = rs.getString("id_jogo");
                nome = rs.getString("nome");
                estudio = rs.getString("estudio");
                categoria = rs.getString("categoria");
                preco = rs.getString("preco");
                fotojogo = rs.getBytes("foto");
                arquivo.format("%s,%s,%s,%s,%s,%s,\n",
                        id,
                        nome,
                        estudio,
                        categoria,
                        preco,
                        "foto" + id + ".png");
                ByteArrayToFileImage(fotojogo, id);
                System.out.println("Gravando registro=" + id);
                // montando a data através do Calendar
            }
            rs.close();
            arquivo.close();
            pstmt.close();
            JOptionPane.showMessageDialog(null, "Feita leittura Banco");
            return;
        } catch (Exception el) {
            JOptionPane.showMessageDialog(null, "Erro Arraylist" + el);
            return;
        }
    }

    public static void gravaTexto() {
        try {
            Formatter arquivo = new Formatter("Jogos.txt");
            for (int i = 1; i <= 5; i++) {
                arquivo.format("%s,%s,%s,%s,\n", i, "texto", 20, "73a");
            } // for
            arquivo.close();
        } catch (Exception e) {
            System.out.println("Erro gravacao=" + e);
        }
    }

    public void lerTexto() {
        try {
            File arquivo = new File("Jogos.txt");
            if (!arquivo.exists()) {
                System.out.println("Arquivo nao existe!!!!!");
                return;
            }
            Scanner sc = new Scanner(arquivo);
            String nomefoto = "";
            sc.useDelimiter("\\s*,\\s*");
            // sc.useDelimiter("\\s*,\\s*|\\R");
            while (sc.hasNext()) {
                id = sc.next();
                nome = sc.next();
                estudio = sc.next();
                categoria = sc.next();
                preco = sc.next();
                nomefoto = sc.next();
                System.out.println("lendo=" + id + nome);

                Jogos jogos = new Jogos();
                jogos.setId(Integer.parseInt(id));
                jogos.setNome(nome);
                jogos.setEstudio(estudio);
                jogos.setCategoria(categoria);
                double dpreco = Double.parseDouble(preco);
                jogos.setPreco(dpreco);
                fotojogo = imageToByte(nomefoto);
                jogos.setFoto(fotojogo);
                if (procura(jogos)) {
                    altera(jogos);
                } else {
                    adiciona(jogos);
                }
            } /// while
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro leitura=" + e);
        }
    }

}
// class