import java.util.Scanner;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import java.util.ArrayList;
import java.io.IOException;

class Crud {
    static Scanner teclado = new Scanner(System.in);
    static ArrayList lista = new ArrayList();
    static Jogos j;
    static String xnom, xestudio, xcategoria, xpreco, xconf, xop;

    public static void cls() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException ee) {
        } catch (InterruptedException eee) {
        }
    }

    public static void main(String[] args) {
        int opcao = 0;
        while (true) {
            cls();
            System.out.println("Menu de opcões - Sistema Jogos");
            System.out.println("1 - Incluir Jogo");
            System.out.println("2 - Alterar Jogo");
            System.out.println("3 - Excluir Jogo");
            System.out.println("4 - Listar todos Jogos");
            System.out.println("5 - Fim processametno");
            xop = Digita("Opcao(1 - 5)?");
            try {
                opcao = 0;
                opcao = Integer.parseInt(xop);
            } catch (Exception e) {
                Espera("Valor Invalido!!");
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
            if (opcao < 1 || opcao > 5) {
                Espera("Opção Invalida");
            }

            if (opcao == 5) {
                break;
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
        cls();
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
        xconf = Digita("\n Confirmar os dados para incluir (S/N) ");
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
        cls();
        Listar();
        int apaga = 0;
        if (lista.isEmpty())
            return;
        xconf = Digita("Qual vai Exluir(0-)" + (lista.size() - 1) + "): ");
        try {
            apaga = Integer.parseInt(xconf);
        } catch (Exception e) {
            Espera("Nenhuma foi Excluido!!");
        }
        if (apaga >= 0 && apaga < lista.size()) {
            lista.remove(apaga);
            Espera("Registro " + apaga + "Excluido com Sucesso..");
        } else {

        }

    }

    public static void Espera(String txt) {
        System.out.println(txt);
        teclado.nextLine();
    }

    public static void Alterar() {
        int reg = 0;
        cls();
        if (lista.isEmpty()) {
            return;
        }
        xconf = Digita("Qual vai Alterar (0-" + (lista.size() - 1 + "):"));

        try {
            reg = Integer.parseInt(xconf);
        } catch (Exception e) {
            Espera("Valor Invalido");
        }
        if (reg >= 0 && reg < lista.size()) {
            MostraDados(reg);
        }

    }

    public static void MostraDados(int j) {
        Jogos jj = (Jogos) lista.get(j);
        while (true) {
            System.out.println("Registro = " + j);
            System.out.println("Id=" + jj.getId());
            System.out.println("(1)Nome Jogo= " + jj.getNome());
            System.out.println("(2)Estudio= " + jj.getEstudio());
            System.out.println("(3)Categoria= " + jj.getCategoria());
            System.out.println("(4)Preco= " + jj.getPreco());
            System.out.println("(5)Fim");
            xconf = Digita("Alteracao-Qual Campo(1-5)?");
            if (xconf.equals("5")) {
                break;
            }

            if (xconf.equals("1")) {
                jj.setNome(Digita("Novo nome do Jogo= "));
                continue;
            }
            if (xconf.equals("2")) {
                jj.setEstudio(Digita("Novo Estudio="));
                continue;
            }
            if (xconf.equals("3")) {
                jj.setCategoria(Digita("Nova Categoria="));
                continue;
            }
            if (xconf.equals("4")) {
                xpreco = Digita("Novo Preco");
                try {
                    double npreco = Double.parseDouble(xpreco);
                    jj.setPreco(npreco);
                } catch (Exception e) {
                    Espera("Preco não alterado!!");
                }
                continue;
            }
            Espera("Opcao Invalida!!");
        }
    }
}