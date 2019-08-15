package playuno;

public class Jugador {
	private String nombre;
	private Jugador siguienteJugador=null;
	private Jugador previoJugador=null;
	private ListaEnlazada<Cartas> mano;     

	
	public Jugador(String nombre){
		this.nombre = nombre;
		this.mano = new ListaEnlazada<Cartas>();
	}
	
	public void agregarAMano(Cartas c){
		this.mano.insertar(c);
	}
	
	public Cartas removeFromHand(int index){
		NodoListaenlazada<Cartas> remove = this.mano.remover(index);
		return remove.getDato();
	}
	
	public void removerDeMano(Cartas c){
		this.mano.eliminar(c);
	}
	
	
	public boolean ganador(){
		return this.mano.size()==0 ? true : false;
	}

	
	
	public Jugador getSiguienteJugador() {
		return siguienteJugador;
	}
	
	public void setSiguienteJugador(Jugador nextPlayer) {
		this.siguienteJugador = nextPlayer;
	}
	
	public Jugador getpPrevJugador() {
		return previoJugador;
	}
	
	public void setAnteriorJugador(Jugador prevPlayer) {
		this.previoJugador = prevPlayer;
	}


	public String toString() {
		return "Player [name=" + nombre + "]" + ", [card#=" + this.mano.size() + "], "
				+ "[prev/nex= " + this.previoJugador.nombre() + "/" + this.siguienteJugador.nombre() + "].";
	}
	
	
	public String nombre() {
		return this.nombre;
	}
	
	
	public ListaEnlazada<Cartas> getMano() {
		return this.mano;
	}
	
}