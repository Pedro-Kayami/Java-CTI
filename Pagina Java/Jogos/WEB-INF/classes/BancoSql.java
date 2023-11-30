package database;

import java.util.*;//scanner, arraylist, formatter
import java.io.*; //file
import javax.swing.*;//JOptionPane
import java.sql.*;//sql
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class BancoSql {
    private static File arqtest;
    private static Scanner arqler, tecla;
    private static ArrayList Lista;
    private static String id, nome, estudio, categoria, preco;
    private static String nomearq, nomebanco, nometabela;
    private static byte[] fotojogo;

    private static Connection con = null;
    private static String url = "", drive = "", fsql = "", caminho = "";
    private static ResultSet rs = null;
    private static PreparedStatement pstmt = null;// sera usado com frase sql

    public static ArrayList pegaBanco() {
        try {
            Lista.clear();// limpa tudo
            pstmt = con.prepareStatement("select * from tabjogos");
            rs = pstmt.executeQuery();

            while (rs.next()) { // criando o objeto
                Lista.add(rs.getLong("id_jogo"));
                Lista.add(rs.getString("nome"));
                Lista.add(rs.getString("estudio"));
                Lista.add(rs.getString("categoria"));
                Lista.add(rs.getDouble("preco"));
                Lista.add(rs.getBytes("foto"));
                System.out.println("adicionado  =) ");
            } // while
            rs.close();
            pstmt.close();
            System.out.println("Feita leittura Banco");
            return Lista;
        } catch (Exception el) {

            System.out.println("Erro Arraylist" + el);
            return null;
        }
    }//////////////////////////////////////////

    public static void conectar() {
        con = null;
        rs = null;
        pstmt = null;

        // url = "jdbc:sqlite:.\\bancosql\\" + nomeban; Com Pasta
        caminho = "\\xampp\\tomcat\\webapps\\Jogos\\";
        url = "jdbc:sqlite:" + caminho + "banco\\Jogos.db";
        drive = "org.sqlite.JDBC";// drive de conexao
        Lista = new ArrayList<>();
        nomebanco = "Jogos.db";
        nometabela = "tabjogos";

        try {
            Class.forName(drive);
            con = DriverManager.getConnection(url);
            System.out.println("Conexão realizada !!!!");
        } catch (Exception e) {
            System.out.println("erro.conexao=" + e);
            return;
        }
    }// fim conectar

    public static void desconectar() {
        try {
            if (con.isClosed() == false) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("erroaodesconectar" + e);
            return;
        }
        System.out.println("desconectou!!!");
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
            System.out.println("Deu certo");
        } catch (Exception e) {
            System.out.println("erro bytes foto=" + e);
        }
        return buffer;
    }/////////////////////////////////////////////////////

    public static void adiciona(Jogos jogos) {
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
            System.out.println("Adicionado com Sucesso ");
        } catch (SQLException e) {
            System.out.println("Erro Inclusao!!!" + e);
            return;
        }
    }// Fim do adiciona

    public static void altera(Jogos jogos) {
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
            System.out.println("Alterado com Sucesso!");
        } catch (Exception e) {
            System.out.println("Não foi alterado!");
            return;
        }
    }

    public static void remove(Jogos jogos) {
        if (procura(jogos) == false) {
            System.out.println("Não existe!");
            return;
        }
        fsql = "delete from tabjogos where id_jogo=?";
        try {
            Integer conta = 1;
            pstmt = con.prepareStatement(fsql);
            pstmt.setLong(conta++, jogos.getId());
            pstmt.execute();
            pstmt.close();
            System.out.println("Excluido com Sucesso!");
        } catch (Exception e) {
            System.out.println("Não foi removido!");
        }
    }

    public static boolean procura(Jogos jogos) {
        boolean flag = false;
        fsql = "select * from tabjogos where id_jogo=?";
        try {
            Integer conta = 1;
            pstmt = con.prepareStatement(fsql);
            pstmt.setLong(conta++, jogos.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) // achou
            {
                System.out.println("Achou Id=" + rs.getLong("id_jogo"));
                flag = true;
            } else {
                System.out.println(" nao Achou Id=" + jogos.getId());
            }
            rs.close();
            pstmt.close();

            return flag;
        } catch (Exception el) {
            System.out.println("Erro procura=" + el);
            return false;
        }
    }

    public static ArrayList<Jogos> getLista() {
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
            System.out.println("Feita leittura Banco");
            return vetor;
        } catch (Exception el) {
            System.out.println("Erro Arraylist" + el);
            return null;
        }
    }

    public static void ByteArrayToFileImage(byte[] bbb, String xid) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bbb);
            BufferedImage bImagex = ImageIO.read(bis);
            ImageIO.write(bImagex, "png", new File("foto" + xid + ".png"));
            System.out.println("image created" + xid);
        } catch (Exception erroi) {
            System.out.println("Erro imagem= " + erroi);
            return;
        }
    }

    public static void gravandotxt() {
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
            System.out.println("Feita leittura Banco");
            return;
        } catch (Exception el) {
            System.out.println("Erro Arraylist" + el);
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

    public static void lerTexto() {
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
