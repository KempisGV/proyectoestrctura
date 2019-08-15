package playuno;

public class CirculoJugador {
	private int tamano = 0;
	private Jugador cabeza = null;		
	
	
	public void agregarAlCirculo(Jugador j){
		if (this.tamano == 0){		 
			this.cabeza = j;
			this.tamano ++;
			
		}else if (this.tamano == 1){	
			this.cabeza.setSiguienteJugador(j);
			this.cabeza.setAnteriorJugador(j);
			j.setSiguienteJugador(this.cabeza);
			j.setAnteriorJugador(this.cabeza);
			this.tamano ++;
			
		}else{   
			Jugador actual = this.cabeza;
			int i = 0;
			while (actual.nombre().compareTo(j.nombre()) < 0 && i < this.tamano){	
				actual = actual.getSiguienteJugador();
				i ++;
			}
			Jugador prev = actual.getpPrevJugador();
			prev.setSiguienteJugador(j);
			actual.setAnteriorJugador(j);
			j.setAnteriorJugador(prev);
			j.setSiguienteJugador(actual);
			this.tamano ++;
			
			if (i == 0){	
				this.cabeza = j;
				
			}
		}
	}
	

	public void eliminarJugador(Jugador j) {
		Jugador actual = this.cabeza;
		boolean comprobar = false;
		for (int i=0; i<this.tamano; i++){
			if (actual.nombre() == j.nombre()){
				actual.getpPrevJugador().setSiguienteJugador(actual.getSiguienteJugador());
				actual.getSiguienteJugador().setAnteriorJugador(actual.getpPrevJugador());
				this.tamano --;
				comprobar = true;
				break;
			}
		}
		if (!comprobar) {
			throw new Error ("Error: no existe el jugador.");
		}
	}
	
	public Jugador getPrimerJugador() {
		return this.cabeza;
	}
	
	public int getSize(){
		return this.tamano;
	}
	
	public String toString() {
		Jugador p = this.cabeza;
		String pl = "";
		for (int i=0; i < this.tamano; i++){
			pl += p.toString()+"\n";
			p = p.getSiguienteJugador();
		}
		return pl;
	}
}

