package playuno;

import java.util.*;

public class UnoGameMain {

	// obtener informacion de los jugadores, inicializa el circulo de los jugadores y empieza el juego
	public static void main(String[] args) {
		
		 //obtener informacion de los jugadores
		 Scanner consola = new Scanner(System.in);
		 System.out.println("Ingresar los nombres de los jugadores:(almenos 5, los nombres deben ser separados por un espacio)");
		 String data = consola.nextLine();
		 String[] plist = data.split("\\s+");		
		 
		 
		// arma el circulo de jugadores. agrega 5 jugadores al circulo
		 CirculoJugador playerCircle = new CirculoJugador();
		 for (int i=0; i<5; i++){
			 playerCircle.agregarAlCirculo(new Jugador(plist[i])); 		
		 }
		 
		// arma la cola de jugadores
		 Cola<Jugador> wl;
		 wl = new Cola<Jugador>(plist.length-5);
		 for (int j=5; j<plist.length; j++){
				 wl.enqueue(new Jugador(plist[j]));
			 }
		 
		 // Empieza el juego. 
		 // cambia entre el circulo de juegadores y la cola de jugadores
		 Jugador loser = start(playerCircle);
		 Jugador newPlayer;
		 while (!wl.isEmpty()){
			 
			 newPlayer = wl.dequeue();
			 wl.enqueue(loser);
			 playerCircle.agregarAlCirculo(newPlayer);
			 playerCircle.eliminarJugador(loser);
			 System.out.println("\n\n" + newPlayer.nombre()+ " (de la cola) DENTRO, " + loser.nombre() + " FUERA. \n\n");
			 System.out.println("=================> Listo para la siguiente ronda <============= \n\n");
			 loser = start(playerCircle);			 
		 }
	}
	
	
	
	/**
	 * Empieza el juego y se ejecuta hasta que exita un ganador y un perdedor en la cola de juegadores, luego el juego termina y muestra informacion de la partida.
	 */
	public static Jugador start(CirculoJugador playerCircle){
		
		// Set Up.
		// Inicializa el mazo, 7 cartas para cada jugador.
		
		Mazo deck = new Mazo();
		Jugador p = playerCircle.getPrimerJugador();
		System.out.println("Cada jugador obtiene 7 cartas.");
		for (int i=0; i < playerCircle.getSize(); i++){
			// le da 7 cartas a cada jugador
			for (int j=0; j<7; j++){
			 p.agregarAMano(deck.drawCard());
		 }
		 System.out.println(p.nombre() + ": " + p.getMano().toString());
		 p = p.getSiguienteJugador();
	     }	 
	     deck.discardCard(deck.drawCard());
		 System.out.println("\nEl sistema ubica la carta: " + deck.getLastDiscarded().toString() + "\n");
		 System.out.println("Inicio del juego.\n");
		 
		 
		 
		 // El juego empieza.
		 //juega en una direccion hasta que un jugador se quede sin cartas y gane
		 //en cada ronda se da sugerencia de que carta puede ser utilizada en su turno
		 //si no se puede usar ni una carta de la mano, automaticamente se sacara una carta del mazo y se la dara al jugador y le notificara que carta se le dio
		 //cuando la ronda termine se obtendra un ganador y un perdedor.		 
		 Jugador winner = null;
		 Jugador loser = null;
		 Jugador pp = playerCircle.getPrimerJugador();
		 Scanner console = new Scanner(System.in);
		 int most_num_cards = 0;    	// guarda el numero de cartas que tiene el jugador con mas cartas en la mano para identificar al perdedor
		 int dir = 1 ;         	// direccion en la que se juega, 1 para siguiente jugador y 0 para jugador anterior
		 int r = 0; 			//guarda el numero de rondas
		 		 
		 while (true){
			 System.out.println(pp.nombre() + ", es tu turno  ---------------------- " + "(" + pp.getMano().size() + ")");
			 		 			 
			 // guarda la carta especial usada en la ultima ronda
			 //por ejemplo coje 2 cartas, coje 4 cartas, carta de salto
			 int draw2;
			 int draw4;
			 draw2 = deck.getLastDiscarded().isDrawTwo()? 2:-1;
			 draw4 = deck.getLastDiscarded().isWildDrawFour()? 4:-1;
			 if (Math.max(draw2, draw4) > 0 ){
				 System.out.print("	carta especial confrontada. coje "+ Math.max(draw2, draw4) + " cartas del mazo :+ ");
				 for (int i=0; i< Math.max(draw2, draw4); i++){
						Cartas newCard = deck.drawCard();
						pp.agregarAMano(newCard);
						System.out.print(newCard.toString()+ ", "); 
					 }
			 }
			 if (deck.getLastDiscarded().isSkip()){ 
				 System.out.println("\n" + pp.nombre()+ " pierde el turno. \n");
				 pp = dir==1? pp.getSiguienteJugador():pp.getpPrevJugador();
				 System.out.println(pp.nombre() + ", es tu turno  ---------------------- " + "(" + pp.getMano().size() + ")");
			 }
			 System.out.println("");
			 
			 // eleccion del jugador
			 // genera una lista de todas las cartas que pueden ser usadas en el turno			 
			 String[] suggested = suggested(pp.getMano(), deck.getLastDiscarded()); 
			 
			 // no se puede utilizar ni una carta 
			 // coje una carta y pasa al siguiente jugador
			 if (suggested.length == 0){
				 Cartas newCard = deck.drawCard();
				 pp.agregarAMano(newCard);
				 System.out.println("	No puede ser usada ni una carta, se recoje una carta del mazo :+ " + newCard.toString() + "\n");
				 System.out.println("	ahora : " + pp.getMano().toString()+ " (" +pp.getMano().size() + ")\n");
		     //brinda una lista de sugerencias de las cartas que se pueden usar en el turno 
			 //el usuario escoje una carta escribiendo el nombre de ella
			 }else{
				 System.out.println("	sugeridas (selecciona escribiendo el nombre): "+ Arrays.toString(suggested));
				 String card = console.nextLine();
				 
				 // las cartas deben ser legales, osea las que se sugieren en la lista de sugerencias
				 while (! Arrays.asList(suggested).contains(card)){
					 System.out.println("	ilegal. escoje de nuevo (respeta los espacios)");
					 card = console.nextLine();
				 }
				 				 
				 System.out.println("	Ubicada: " + card);
				 Cartas discard = pp.removeFromHand(pp.getMano().indiceDe(card));
				 deck.discardCard(discard);
				 System.out.println("	ahora : " + pp.getMano().toString()+ " (" +pp.getMano().size() + ")\n");
				 
				 // Comprueba el estado
				 // Si un jugador gana, el juego para
				 if(pp.ganador()){
					 winner = pp;
					 break;
				 }
				 
				 // guarda carta especial, cambia el estado del juego
				 // por ejemplo reversa, carta salto.
				 if (card.contains("reversa")){
					 dir = dir==1? 0:1;
					 System.out.println("\n\n					-- dir cambiada -- \n\n");
				 }	 				 				 
			 }	 
			 pp = dir==1 ? pp.getSiguienteJugador(): pp.getpPrevJugador();
			 r++;
		}
		 
		 
		//Fin del juego
		// Busca al perdedor.
		loser = playerCircle.getPrimerJugador();
		for (int i=0; i<5; i++){
			if(loser.getMano().size() >= most_num_cards){
				most_num_cards = loser.getMano().size();
			}
			loser = loser.getSiguienteJugador();
		}
		// Reporte del juego
		System.out.println("\n********************************");
		System.out.println("Fin del juego. Reporte: ");
		System.out.println("numero de rondas: " + r/5);
		System.out.println("+ ganador: " + winner.nombre());
		System.out.println("+ perdedor:  " + loser.nombre() +" ("+most_num_cards+")");
		
		return loser; 		 
	} 
	
	
	/**
	 * From all cards in hand, find all legal cards that can be placed
	 * on against the last card being placed. suggested to user to pick.
	 * @param hand
	 * @param last
	 * @return string[]
	 * @runningtime O(n)
	 */
	public static String[] suggested(ListaEnlazada<Cartas> hand, Cartas last){
		
		String[] suggested = new String[hand.size()];
		NodoListaenlazada<Cartas> card = hand.obtenerCabeza();
		int i =0;
		
		while (card != null){
			if(last.canBePlacedOn(card.getDato())){
				suggested[i] = card.getDato().toString();
				i++;
			}
			card = card.getSiguiente();			
		}
		return Arrays.copyOfRange(suggested, 0, i);
	}		
}