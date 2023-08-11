import javax.swing.*;
import java.sql.*;
//import java.awt.Image;

import java.util.HashMap;
//import java.util.Map;
import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRResultSetDataSource;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;

import net.sf.jasperreports.view.JasperViewer;

// criar uma pasta \lib dentro da pasta do programa e colocar as bibliotecas do arquivo lib.rar

// para compilar programa javac -cp .;".\lib\*" %f
// para executar o programa  java -cp .;".\lib\*" "%e" 

class BancoRel {
    public static void main(String xx[])
    // public bancorel()
    {
        Connection con;
        Statement stmt;
        ResultSet rs;
        String url = "", sql = "";

        JasperReport report;
        JasperPrint print;
        JasperViewer viewer;

        JRResultSetDataSource jrRS;

        HashMap parametros;

        try {
            url = "jdbc:sqlite:C:\\Users\\pedro\\Documents\\Castro\\Java-CTI\\3BM\\Jogos.db";

            Class.forName("org.sqlite.JDBC");

            con = DriverManager.getConnection(url);
            System.out.println("Conexão realizada !!!!");

            stmt = con.createStatement();
            sql = "select * from tabjogos order by nome";

            rs = stmt.executeQuery(sql);// result set

            System.out.println("select realizada !!!!");

            jrRS = new JRResultSetDataSource(rs);

            parametros = new HashMap();
            JasperPrint impressao = JasperFillManager.fillReport(

                    show();

            // outro jeito de mostrar o relatorio
            // System.out.println("jrRS realizada !!!!");
            // report =
            // JasperCompileManager.compileReport("C:\\javabanco\\73C\\desenhosql.jrxml");
            // System.out.println("compila report !!!!");
            // print = JasperFillManager.fillReport(report, null, jrRS);
            // System.out.println("imprime print !!!!");
            // viewer = new JasperViewer(print, false);
            // System.out.println("mostra viewer!!!!");

            // viewer.setVisible(true);

            // fechando o banco
            stmt.close();
            con.close();

        } // try
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO relatorio=" + e);

        } // catch

    } // main

}// class
