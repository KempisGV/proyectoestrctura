package playuno;

import java.util.*;
public class Mazo {
	private static final String[] COLORES_REGULARES = {"rojo", "amarillo", "azul", "verde"};
	private ListaEnlazada<Cartas> mazo;
	private ListaEnlazada<Cartas> descartar;
	private Cartas ultimaDescartada = null;
	
	
	public Mazo(){
		
		mazo = new ListaEnlazada<Cartas>();
		descartar = new ListaEnlazada<Cartas>();
		
		
		for (String color : COLORES_REGULARES){
			this.mazo.insertarRandom(new Cartas(color, 0));
			for (int i = 0; i<2; i++){
				
				for (int numeroCarta = 1; numeroCarta<=9; numeroCarta++){
					this.mazo.insertarRandom(new Cartas(color, numeroCarta));
				}
				
				this.mazo.insertarRandom(new Cartas(color, true, false, false));
				this.mazo.insertarRandom(new Cartas(color, false, true, false));
				this.mazo.insertarRandom(new Cartas(color, false, false, true));
			}
			
		}
		
		for (int i = 0; i<4; i++){
			this.mazo.insertarRandom(new Cartas(false)); // add to deck
			this.mazo.insertarRandom(new Cartas(true)); // add to deck
		}
	}
	
	
	public Cartas getLastDiscarded() {
		return this.ultimaDescartada;
	}
	
	
	public Cartas drawCard() {
		if (this.mazo.size() != 0){	
			NodoListaenlazada<Cartas> actual = this.mazo.obtenerCabeza();
			this.mazo.remover(0);
			return actual.getDato();
		}else{						
			
			if(this.descartar.size()== 0){			
				throw new Error("error: no hay carta para sacar");
			}
			
			NodoListaenlazada<Cartas> actual = this.descartar.obtenerCabeza();
			this.descartar.eliminar(actual.getDato());
			return actual.getDato();
		}
	}
	
	
	public void discardCard(Cartas c){
		
		
		if (this.ultimaDescartada !=null && !c.canBePlacedOn(this.ultimaDescartada)) {
			throw new Error ("failed: illegal card to place down.");
		}
		this.descartar.insertarRandom(c);
		this.ultimaDescartada = c;
	}

	
	public String toString(){
		return "mazo: " + this.mazo.toString() + "\n descartar: " + this.descartar.toString();
	}

}

