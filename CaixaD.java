class caixaD{
    private Caixad;
    private Esq;
    private  Dir;
    private int Valor;

    public caixaD(int Valor){
        setEsq();
        setDir();
        setValor(Valor);
    }

    public void setEsq(caixaD cx){
        this.Esq = cx;
    }

    public void setDir(caixaD cx){
        this.Dir = cx;
    }

    public caixaD getEsq(){
        return this.Esq;
    }

    public caixaD getDir(){
        return this.Dir;
    }

    public void setValor(int v){
        this.Valor=v;
    }
    public int getValor(){
        return this.Valor;
    }
}