package playuno;

public class NodoArbolbinario
{
    public NodoArbolbinario izquierda = null;
    public NodoArbolbinario derecha = null;
    public NodoArbolbinario padre=null;
    public int dato;

    public NodoArbolbinario(int dato)
    {
        this.dato=dato;
    }

    public void printNodo()
    {
        System.out.println(dato);
    }
    



}
