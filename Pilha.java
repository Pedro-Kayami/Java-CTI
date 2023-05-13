class Pilha {

    public Pilha() {
        Topo = null;
        Base = null;
        Cxn = null;
        Aux = null;
    }

    public void Mostra(){
        System.out.println("Pilha Dinâmica");
        Aux=Topo;
        while(Aux!= null){
            System.out.println("\n Valor=" + Aux.getValor()){
                Aux=Aux.getProx();
            }
        }
    }

    public void add(int x) {
        Cxn = new Caixa(x, Topo);
        Topo = Cxn;
        if (Base == null) {
            Base = Cxn;
        }
    }

    public int Remove() {
        int R = 0;
        Aux = Top;
        if (Topo == null) {
            System.out.println("Pilha vazia");
        } else {
            R = Aux.getValor();
            if (Topo == null) {
                Base = null;
            }
            Topo = Aux.getProx();
        }
        return R;
    }

}