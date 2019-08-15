package playuno;

public class Cartas{	

private String color;
private int numero;
private boolean esCartaEspecial = false;
private boolean esSacarDos = false;
private boolean esReversa = false;
private boolean esSalto = false;
private boolean esSacarCuatroSalvaje = false;



public Cartas(String color, int numero){
	this.color = color;
	this.numero = numero;
}

public Cartas(String color, boolean sacarDos, boolean reversa, boolean salto){
	
	if (sacarDos && (reversa || salto) || (reversa && salto)){
		throw new IllegalStateException("Una carta no puede tener mas de una propiedad especial");
	}
	this.numero = -1;
	this.color = color;
	this.esCartaEspecial = true;
	this.esSacarDos = sacarDos;
	this.esReversa = reversa;
	this.esSalto = salto;
}

public Cartas(boolean sacarCuatro){
	this.numero = -1;
	this.color = "salvaje";
	this.esCartaEspecial = true;
	this.esSacarCuatroSalvaje = sacarCuatro;
}

public String getColor() {
	return color;
}

public int getNumero() {
	return numero;
}

public boolean esEspecial() {
	return esCartaEspecial;
}

public boolean isDrawTwo() {
	return esSacarDos;
}

public boolean isReversa() {
	return esReversa;
}

public boolean isSkip() {
	return esSalto;
}

public boolean isSalvaje(){
	return this.getColor().equals("wild");
}
public boolean isWildDrawFour() {
	return esSacarCuatroSalvaje;
}

public boolean canBePlacedOn(Cartas otro){
	return (this.getNumero()!=-1 && this.getNumero()==otro.getNumero()) ||
			(this.getColor().equals(otro.getColor())) ||
			(this.isDrawTwo() && this.isDrawTwo()==otro.isDrawTwo()) ||
			(this.isReversa() && this.isReversa()==otro.isReversa()) ||
			(this.isSkip()  && this.isSkip()==otro.isSkip()) ||
			this.isSalvaje() || otro.isSalvaje();
}

public String toString(){
	if (this.esEspecial()){
		if (this.isSalvaje()){
			if (this.isWildDrawFour()){
				return "sacar cuatro cartas";
			}
			else{
				return "carta salvaje";
			}
		}
		else if (this.isDrawTwo()){
			return this.getColor()+" coje dos";
		}
		else if (this.isReversa()){
			return this.getColor()+" reversa";
		}
		else if (this.esSalto){
			return this.getColor()+" salto";
		}
		else{
			return "";
		}
	}
	else{
		return this.getColor()+" "+this.getNumero();
	}
}

public boolean equals(Cartas other){
	return this==other;
}

}