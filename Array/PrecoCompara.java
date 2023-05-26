import java.util.*;

class PrecoCompara implements Comparator {

    public int compare(Object obj1, Object obj2) {
        Jogos e1 =(Jogos)obj1;
        Jogos e2 =(Jogos)obj2;
        if (e1.getPreco() == e2.getPreco())
            return 0;
        else if (e1.getPreco() > e2.getPreco())
            return 1;
        else
            return -1;
    }

}
