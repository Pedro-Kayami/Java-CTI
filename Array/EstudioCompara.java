import java.util.*;

class EstudioCompara implements Comparator {

    public int compare(Object obj1, Object obj2) {
        Jogos e1 = (Jogos) obj1;
        Jogos e2 = (Jogos) obj2;
        return e1.getNome().compareTo(e2.getNome());
    }

}
