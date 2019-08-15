package playuno;

public class NodoListaenlazada<T> {
	private T dato;
	private NodoListaenlazada<T> siguiente;
	
	public NodoListaenlazada(T dato){
		this.dato = dato;
		this.siguiente = null;
	}
	
	public T getDato(){
		return this.dato;
	}
	
	public void setSiguiente(NodoListaenlazada<T> siguienteNodo){
		this.siguiente = siguienteNodo;
		
	}
	
	
	public NodoListaenlazada<T> getSiguiente() {
		return this.siguiente;
	}
	
	public String toString() {
		return this.dato.toString();
	}
	
	
	
}