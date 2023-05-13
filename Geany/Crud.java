import java.util.Scanner;
import java.util.ArrayList;

class Crud {
    static Scanner teclado = new Scanner(System.in);
    static ArrayList lista = new ArrayList();
    static Jogos j;
    static String xnom, xestudio, xcategoria, xpreco, xconf;

    public static void main(String[] args) {
        int opcao = 0;
        while (true) {
            System.out.println("Menu de opcões - Sistema Jogos");
            System.out.println("1 - Incluir Jogo");
            System.out.println("2 - Alterar Jogo");
            System.out.println("3 - Excluir Jogo");
            System.out.println("4 - Listar todos Jogos");
            System.out.println("5 - Fim processametno");
            System.out.println("Opcao(1 - 5)?");
            try {
                opcao = teclado.nextInt();
                if (opcao == 5) {
                    break;
                }

            }

            catch (Exception e) {
                teclado.next();
            }
            if (opcao == 4) {
                Listar();
            }
            if (opcao == 3) {
                Excluir();
            }
            if (opcao == 2) {
                Alterar();
            }
            if (opcao == 1) {
                Incluir();
            }

        }

    }

    public static int GerarId() {
        if (lista.isEmpty()) {
            return 1;
        }
        int ultimo = lista.size() - 1;
        Jogos Aux = (Jogos) lista.get(ultimo);
        return Aux.getId() + 1;
    }

    public static String Digita(String txt) {
        String texto = "";
        System.out.println(txt);
        texto = teclado.next();
        return texto;
    }

    public static void Incluir() {
        System.out.println("\n Incluindo Jogos");
        System.out.println("\n Novo ID= " + GerarId());
        xnom = Digita(("\n Nome Jogo= "));
        xestudio = Digita(("\n Estudio do Jogo= "));
        xcategoria = Digita(("\n Categoria do Jogo= "));
        xpreco = Digita(("\n Preco do Jogo= "));
        System.out.println(xnom);
        System.out.println(xestudio);
        System.out.println(xcategoria);
        System.out.println(xpreco);
        xconf = Digita("\n Confirmar os dados para incluir (S/N)");
        if (xconf.equals("S") || xconf.equals("s")) {
            j = new Jogos();
            System.out.println("\n Incluindo Jogos");
            j.setId(GerarId());
            j.setNome(xnom);
            j.setEstudio(xestudio);
            j.setCategoria(xcategoria);
            Double npreco = Double.parseDouble(xpreco);
            j.setPreco(npreco);
            lista.add(j);
            System.out.println("Adicionado Jogo");
        } else {
            System.out.println("Não faz nada");
        }

    }

    static void Listar() {
        System.out.println("Listando Jogos");
        if (lista.isEmpty()) {
            System.out.println("Lista Vazia");
            return;
        }
        for (int index = 0; index < lista.size(); index++) {
            Jogos jj = (Jogos) lista.get(index);
            System.out.println(" " +
                    jj.getId() + " , " +
                    jj.getNome() + " , " +
                    jj.getCategoria() + " , " +
                    jj.getEstudio() + " , " +
                    jj.getPreco());
        }
    }

    public static void Excluir() {

    }

    public static void Alterar() {

    }
}
