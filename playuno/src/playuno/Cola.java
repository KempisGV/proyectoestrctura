package playuno;

public class Cola<T> {
	private T[] cola;
	private int frente;
	private int posterior;
	private int largo;
	
	public Cola(int tamano){
		assert tamano > 0 : tamano;			
		this.cola = (T[])new Object[tamano];
		this.frente = 0;
		this.posterior = 0;
		this.largo = tamano;
		
	}
	
	
	public boolean estaLlena() {
		return (frente%this.largo == posterior%this.largo) 
				&& this.cola[this.posterior] != null;
	}
	
	
	public boolean isEmpty() {
		return (frente%this.largo == posterior%this.largo) && (!estaLlena());
	}
	
	
	public int getTamano() {
		if (estaLlena()) {
			return this.largo;
		}else{
			return (this.largo-this.frente+this.posterior)%this.largo;
		}
	}
	
	
	public void enqueue (T dato){
		if ( getTamano() == this.largo) {			
			throw new Error("error: cola llena, no se puede agregar");
		}else{
			this.cola[this.posterior] = dato;
			this.posterior = (this.posterior+1) % this.largo;
		}
	}
	
	
	public T dequeue (){
		if (isEmpty()){							
			throw new Error("cola vacia");
		}else{
			T x = this.cola[this.frente];
			this.frente = this.frente%this.largo + 1; 
			return x;
		}
	}
	
	public String toString(){
		return this.cola.toString();
	}
	
	
}
