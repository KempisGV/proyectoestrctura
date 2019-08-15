package playuno;

import java.util.Arrays;
import java.util.Random;

public class ListaEnlazada<T> {
	private int tamano;   
	private NodoListaenlazada<T> cabeza;   
	private NodoListaenlazada<T> cola;   
	
	public ListaEnlazada(){
		this.cabeza = null;		
		this.cola = null;
		this.tamano = 0;
	}
	
	
	public NodoListaenlazada<T> obtenerCabeza(){
		return this.cabeza;
	}
	
	public void setCabeza(NodoListaenlazada<T> siguiente){
		this.cabeza = siguiente;
	}
	
	public void insertar(T dato){
		NodoListaenlazada<T> nuevoNodo = new NodoListaenlazada<T>(dato);	
		if (this.tamano==0){		
			this.cabeza= nuevoNodo; 
			this.cola = nuevoNodo;
		}else{
			this.cola.setSiguiente(nuevoNodo);		
			this.cola = nuevoNodo; 			
		}
		this.tamano ++;
	}
	
	
	public void insertarRandom(T dato){
		NodoListaenlazada<T> nuevoNodo = new NodoListaenlazada<T>(dato);
		Random rand = new Random();
		int ubicacion = rand.nextInt(this.tamano+1);   
		
		if (ubicacion == 0){		
			nuevoNodo.setSiguiente(this.cabeza);
			this.cabeza = nuevoNodo;
		}else if (ubicacion == this.tamano+1){		
			this.cola.setSiguiente(nuevoNodo);
			this.cola = nuevoNodo;
		}else{								
			NodoListaenlazada<T> actual = this.cabeza;
			for (int i=0; i < ubicacion-1; i++){
				actual = actual.getSiguiente();
			}
			nuevoNodo.setSiguiente(actual.getSiguiente());
			actual.setSiguiente(nuevoNodo);
		}
		this.tamano++;		
	}
	
	
	public void eliminar(T dato){
		NodoListaenlazada<T> actual = this.cabeza;
		NodoListaenlazada<T> anterior = null;
		int pos = 0;
		while(actual != null){				
			if (actual.getDato() == dato){
				if (pos==0){ 	
					this.cabeza = actual.getSiguiente();
					this.tamano --;
					break;
				}else if (pos == this.tamano-1){		 
					anterior.setSiguiente(null);
					this.cola = anterior;
					this.tamano --;
					break;
				}else{			
					anterior.setSiguiente(actual.getSiguiente());
					this.tamano --;
					break;
				}
			}
			anterior = actual;
			actual = actual.getSiguiente();
			pos++;
		}
		if (pos >= this.tamano){		
			throw new Error("error: no hay datos, lista vacia.");
		}
	}
	
	
	public NodoListaenlazada<T> remover(int i){
		assert i < tamano : i;    
		assert i >= 0: i;
		
		if (this.tamano == 0){     
			throw new Error ("error: lista vacia");
		}
		
		int indice = i;
		NodoListaenlazada<T> actual = this.cabeza;
		NodoListaenlazada<T> anterior = null;
		while (actual != null && indice != 0){ 	
			anterior = actual;
			actual = actual.getSiguiente();
			indice --;
		}
		if (i == 0){
			this.cabeza = this.cabeza.getSiguiente();
			this.tamano --;
		}else if (i == tamano-1){
			anterior.setSiguiente(null);
			this.cola = anterior;
			this.tamano --;
		}else{
			anterior.setSiguiente(actual.getSiguiente());
			this.tamano --;
		}
		
		return actual;
	}
	
	public int size() {
		return this.tamano;
	}
	
	
	public String toString() {
		String[] dato = new String[this.tamano];
		NodoListaenlazada<T> actual = this.cabeza;
		int i =0;
		while (actual != null){
			dato[i] = actual.toString();
			actual = actual.getSiguiente();
			i++;
		}
		return Arrays.toString(dato);
	}
	
	
	public int indiceDe(String data){
		
		int i = 0;
		NodoListaenlazada<T> actual = this.cabeza;
		while (actual != null && !actual.getDato().toString().equals(data)){ 	
			actual = actual.getSiguiente();
			i ++;
		}
		if (i >= this.tamano){   		
			throw new Error ("error: dato no encontrado.");
		}
		
		return i;
	}

}
