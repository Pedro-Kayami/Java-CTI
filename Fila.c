#include <stdio.h>
#include <stdlib.h>

Struc Caixa()
{
private:
    Caixax *Cxn, *Inicio, *Aux, *Fim;

public
    Fila()
    {
        Cxn = null;
        Inicio = null;
        Aux = null;
        Fim = null;
    }

    void Add(int x)
    {
        Cxn = new Caixax;
        Cxn->valor = x;
        Cxn->prox = null;
        if (Inicio == null)
        {
            Inicio = Cxn;
        }
        else
        {
            Fim->prox = Cxn;
        }
        Fim = Cxn;
    }

    void MostraFila()
    {
        printf("\n fila de valores");
        for (Aux = Inicio; Aux != null; Aux = Aux->Prox)
        {
            printf("\n valor %d", Aux->valor);
        }
    }

    int Remove()
    {
        if (Inicio == null)
        {
            printf("\n fila vazia");
            return 0;
        }
        int volta = 0;
        volta = Inicio->valor;
        Aux = Inicio;
        Inicio = Inicio->prox;
        Free(Aux);
        return volta;
    }

    void alterar(int x, int z)
    {
        for (Aux = inicio; Aux != null; Aux = Aux->Prox)
        {
            Aux->valor = z;
        }
    }
}