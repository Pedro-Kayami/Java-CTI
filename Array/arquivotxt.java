import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Formatter;

public class arquivotxt {
    
    public static void gravaTexto(String nomeArquivo) {
        try {
            Formatter arquivo = new Formatter(nomeArquivo);
            for (int i = 1; i <= 5; i++) {
                arquivo.format("%s,%s,%s,%s,\n", i, "texto", 20, "73a");
            } // for
            arquivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro gravacao=" + e);
        }
    }

    public static void lerTexto(String nomeArquivo) {
        Scanner tecla = new Scanner(System.in);
        try {
            File arquivo = new File(nomeArquivo);
            if (!arquivo.exists()) {
                System.out.println("Arquivo nao existe!!!!!");
                return;
            }
            Scanner sc = new Scanner(arquivo);
            sc.useDelimiter("\\s*,\\s*");
            while (sc.hasNext()) {
                System.out.println("lendo=" + sc.next());
                tecla.nextLine();
            } /// while
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro leitura=" + e);
        }
    }

    public static void main(String[] args) {
        String input = "1,2,red,blue";
        Scanner s = new Scanner(input).useDelimiter(",");
        System.out.println(s.next());
        System.out.println(s.next());
        System.out.println(s.next());
        System.out.println(s.next());
        s.close();

        gravaTexto("virgula2.txt");
        lerTexto("virgula2.txt"); // trocar pelo caminho do arquivo desejado.
    }
}