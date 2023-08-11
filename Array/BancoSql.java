//cmd C:\javacti\Sqlite>javac -cp .;".\lib\*" BancoxSql.java
//cmd C:\javacti\Sqlite>java -cp .;".\lib\*" BancoxSql
//dentro do geany javac -cp .;".\lib\*" %f
//dentro do geany java -cp ".;.\lib\*" "%e" 

import java.util.*;//scanner, arraylist, formatter
import java.io.*; //file
import javax.swing.*;//JOptionPane
import java.sql.*;//sql

class BancoSql {
    private File arqtest;
    private Scanner arqler, tecla;
    private ArrayList Lista;
    // id , c1=nome_pet, c2=foto_pet, c3=servico, c4=idade_pet, c5=tipoanimal;
    // long ,c1=varchar(50), c2=blob , c3=varchar(20), c4=integer , c5=varchar(20);

    private String id, nome, estudio, categoria, preco;
    private String nomearq, nomebanco, nometabela;
    private byte[] fotopet;

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

    public static void main(String args[]) {
        BancoSql b = new BancoSql("Jogos.db");
        b.conectar();
        b.desconectar();
    }// main

}// class
