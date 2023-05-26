import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;

class Crud {
    static Scanner teclado = new Scanner(System.in);
    static List<Jogos> lista = new ArrayList<>();
    static Jogos j;
    static String xid, xnom, xestudio, xcategoria, xpreco, xconf, xop;

    public static void cls() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ignored) {
        }
    }

    public static void main(String[] args) {
        int opcao = 0;
        lerTexto();
        while (true) {
            cls();
            System.out.println("Menu de opções - Sistema Jogos");
            System.out.println("Tamanho da lista: " + lista.size());
            System.out.println("1 - Incluir Jogo");
            System.out.println("2 - Alterar Jogo");
            System.out.println("3 - Excluir Jogo");
            System.out.println("4 - Listar todos Jogos");
            System.out.println("5 - Fim processamento");
            System.out.println("6 - Classificar");
            xop = Digita("Opção (1 - 5):");
            try {
                opcao = Integer.parseInt(xop);
            } catch (NumberFormatException e) {
                Espera("Valor Inválido!!");
            }
            if (opcao == 4) {
                Listar();
            } else if (opcao == 3) {
                Excluir();
            } else if (opcao == 2) {
                Alterar();
            } else if (opcao == 1) {
                Incluir();
            } else if (opcao == 6) {
                ClassifNome();
            } else if (opcao == 5) {
                break;
            } else {
                Espera("Opção Inválida");
            }
        }
    }

    public static int GerarId() {
        if (lista.isEmpty()) {
            return 1;
        }
        int ultimo = lista.size() - 1;
        Jogos aux = lista.get(ultimo);
        return aux.getId() + 1;
    }

    public static String Digita(String txt) {
        System.out.println(txt);
        return teclado.nextLine().toUpperCase();
    }

    public static void Incluir() {
        cls();
        System.out.println("\nIncluindo Jogos");
        System.out.println("\nNovo ID: " + GerarId());
        xnom = Digita("\nNome do Jogo: ");
        xestudio = Digita("\nEstúdio do Jogo: ");
        xcategoria = Digita("\nCategoria do Jogo: ");
        xpreco = Digita("\nPreço do Jogo: ");
        System.out.println("Nome: " + xnom);
        System.out.println("Estúdio: " + xestudio);
        System.out.println("Categoria: " + xcategoria);
        System.out.println("Preço: " + xpreco);
        xconf = Digita("\nConfirmar os dados para incluir (S/N): ");
        if (xconf.equals("S")) {
            j = new Jogos();
            System.out.println("\nIncluindo Jogo");
            j.setId(GerarId());
            j.setNome(xnom);
            j.setEstudio(xestudio);
            j.setCategoria(xcategoria);
            double npreco = Double.parseDouble(xpreco);
            j.setPreco(npreco);
            lista.add(j);
            System.out.println("Jogo adicionado");
            gravaTexto();
        } else {
            System.out.println("Nada foi feito");
        }
    }

    static void Listar() {
        System.out.println("Listando Jogos");
        if (lista.isEmpty()) {
            System.out.println("Lista Vazia");
            return;
        }
        for (int index = 0; index < lista.size(); index++) {
            Jogos jj = lista.get(index);
            System.out.println(
                    "ID: " + jj.getId() +
                            ", Nome: " + jj.getNome() +
                            ", Categoria: " + jj.getCategoria() +
                            ", Estúdio: " + jj.getEstudio() +
                            ", Preço: " + jj.getPreco());
        }
    }

    public static void Excluir() {
        cls();
        Listar();
        int apaga = 0;
        if (lista.isEmpty())
            return;
        xconf = Digita("Qual jogo deseja excluir (0 - " + (lista.size() - 1) + "): ");
        try {
            apaga = Integer.parseInt(xconf);
        } catch (NumberFormatException e) {
            Espera("Nenhum registro foi excluído!!");
        }
        if (apaga >= 0 && apaga < lista.size()) {
            lista.remove(apaga);
            Espera("Registro " + apaga + " excluído com sucesso.");
        } else {
            Espera("Registro inválido.");
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
        xconf = Digita("Qual jogo deseja alterar (0 - " + (lista.size() - 1) + "): ");

        try {
            reg = Integer.parseInt(xconf);
        } catch (NumberFormatException e) {
            Espera("Valor inválido");
        }
        if (reg >= 0 && reg < lista.size()) {
            MostraDados(reg);
        }
    }

    public static void MostraDados(int j) {
        Jogos jj = lista.get(j);
        while (true) {
            System.out.println("Registro: " + j);
            System.out.println("ID: " + jj.getId());
            System.out.println("(1) Nome do Jogo: " + jj.getNome());
            System.out.println("(2) Estúdio: " + jj.getEstudio());
            System.out.println("(3) Categoria: " + jj.getCategoria());
            System.out.println("(4) Preço: " + jj.getPreco());
            System.out.println("(5) Fim");
            xconf = Digita("Alteração - Qual campo (1 - 5)?");
            if (xconf.equals("5")) {
                break;
            }

            if (xconf.equals("1")) {
                jj.setNome(Digita("Novo nome do Jogo: "));
                continue;
            }
            if (xconf.equals("2")) {
                jj.setEstudio(Digita("Novo Estúdio: "));
                continue;
            }
            if (xconf.equals("3")) {
                jj.setCategoria(Digita("Nova Categoria: "));
                continue;
            }
            if (xconf.equals("4")) {
                xpreco = Digita("Novo Preço: ");
                try {
                    double npreco = Double.parseDouble(xpreco);
                    jj.setPreco(npreco);
                } catch (NumberFormatException e) {
                    Espera("Preço não alterado!!");
                }
                continue;
            }
            Espera("Opção inválida!!");
        }
    }

    public static void gravaTexto() {
        try {
            Formatter arquivo = new Formatter("Jogos.txt");
            for (int i = 0; i < lista.size(); i++) {
                Jogos aux = lista.get(i);
                xid = String.valueOf(aux.getId());
                xnom = aux.getNome();
                xestudio = aux.getEstudio();
                xcategoria = aux.getCategoria();
                xpreco = String.valueOf(aux.getPreco());
                arquivo.format("%s,%s,%s,%s,%s,\n", xid, xnom, xestudio, xcategoria, xpreco);
                Espera(xnom + " Gravando = " + i);

            }
            arquivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro na gravação: " + e);
        }
    }

    public static void ClassifNome() {
        Listar();
        ArrayList aux = (ArrayList) lista;
        System.out.println("Escolha Classificacao");
        System.out.println("(1) Por Nome");
        System.out.println("(2) Por Estudio");
        System.out.println("(3) Por Categoria");
        System.out.println("(4) Por Preco");
        xop = Digita("Qual Opcao(1-4)");

        if (xop.equals("1")) {
            Collections.sort(lista, new NomeCompara());
        }

        if (xop.equals("2")) {
            Collections.sort(lista, new EstudioCompara());
        }

        if (xop.equals("3")) {
            Collections.sort(lista, new CategoriaCompara());
        }

        if (xop.equals("4")) {
            Collections.sort(lista, new PrecoCompara());
        }
        Listar();
        gravaTexto();
    }

    public static void lerTexto() {
        try {
            File arquivo = new File("Jogos.txt");
            if (!arquivo.exists()) {
                System.out.println("Arquivo não existe!");
                return;
            }
            Scanner sc = new Scanner(arquivo);
            sc.useDelimiter("\\s*,\\s*");
            if (!lista.isEmpty()) {
                lista.clear();
            }
            while (sc.hasNext()) {
                xid = sc.next();
                xnom = sc.next();
                xestudio = sc.next();
                xcategoria = sc.next();
                xpreco = sc.next();
                Jogos aux = new Jogos();
                aux.setId(Integer.parseInt(xid));
                aux.setNome(xnom);
                aux.setEstudio(xestudio);
                aux.setCategoria(xcategoria);
                double npreco = Double.parseDouble(xpreco);
                aux.setPreco(npreco);
                lista.add(aux);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro na leitura: " + e);
        }
    }
}
