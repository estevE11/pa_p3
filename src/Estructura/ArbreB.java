package Estructura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;


public class ArbreB {
	private class NodeA {
		String contents;
		ArbreB yes, no;	
	
		NodeA(String contents) {
			//Constructor 1. Inicialitza als atributys yes i no a null
			this.contents = contents;
			this.yes = null;
			this.no = null;
		}
		NodeA(String pregunta, ArbreB a1, ArbreB a2) {
			//Constructor 2. Crea el node i l'inicialitza amb els par�metres
			this.contents = pregunta;
			this.yes = a1;
			this.no = a2;

		}
	}
	// Atributs: Taula de 2 posicions
	private NodeA[] root;

	/* CONSTRUCTORS */
	public ArbreB(ArbreB a1, ArbreB a2, String pregunta) {
		//Constructor 1. Crea un arbre amb una pregunta i dos respostes
	}
	public ArbreB() {
		//Constructor 2. Crea un arbre buit
	}	
	public ArbreB(String filename) throws Exception{
		//Constructor 3. Crea l'arbre amb el contingut donat en un fitxer
		//El par�metre indica el nom del fitxer
	}

	/* PUBLIC METHODS */
	public boolean isEmpty() {
		return false; //COMPLETE
	}
	public void rewind() {
		//COMPLETE
	}
	/* True if the current node is an answer (a leaf) */
	public boolean atAnswer() {
		return false; //COMPLETE
	}
	/* move current to yes-descendant of itself */
	public void moveToYes() {
		//COMPLETE
	}
	/* move current to yes-descendant of itself */
	public void moveToNo() {
		//COMPLETE
	}
	/* get the contents of the current node */
	public String getContents() {
		return ""; //COMPLETE
	}
	 /* Substituir la informaci� del node actual
	 * per la pregunta donada pel jugador. Previament crear el node que ser� el
	 * seu fill dret, resposta no encertada, amb la informaci� del node actual.
	 */
	public void improve(String question, String answer) {
		//COMPLETE
	}
	private void preorderWrite(BufferedWriter buw) throws Exception {
		//Imprescindible que la implementaci� sigui recursiva
	}
	/* Saves contents of tree in a text file */
	public void save(String filename) throws Exception {
		BufferedWriter buw = null;
		try {
			buw = new BufferedWriter(new FileWriter(filename));
			this.preorderWrite(buw);
			buw.close();

		} catch (IOException e) {
			System.err.println("saveToTextFile failed: " + e);
			System.exit(0);
		}
	}
	private NodeA loadFromFile(String filename){
		//Imprescindible implementaci� recursiva
	}
	public void visualitzarAnimals() {
		/* La implementaci� s�ha de fer, obligat�riament, invocant a un
			m�tode de la classe NodeA. �s irrellevant l�ordre de
			visualitzaci�*/

		/* COMPLETE */
	}
	public int quantsAnimals() {
		/* La implementaci� s�ha de fer, obligat�riament, invocant a un
			m�tode de la classe NodeA */
		/* COMPLETE */
		return -1;
	}
	public int alsada() {
		/* COMPLETE */
		// Imprescindible invocar a un m�tode la classe NodeA
		return -1;
	}
	public void visualitzarPreguntes() {
		/* COMPLETE */
		/*La implementaci� s�ha de fer, obligat�riament, invocant a un
			m�tode de la classe NodeA. �s irrellevant l�ordre de
			visualitzaci�*/
	}
}
