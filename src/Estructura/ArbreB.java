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
			//Constructor 2. Crea el node i l'inicialitza amb els paràmetres
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
		super();

		NodeA first = new NodeA(pregunta, a1, a2);
		this.root[0] = first;
		this.root[1] = first;
	}
	public ArbreB() {
		//Constructor 2. Crea un arbre buit
		this.root = new NodeA[2];
	}
	public ArbreB(String filename) throws Exception{
		//Constructor 3. Crea l'arbre amb el contingut donat en un fitxer
		//El paràmetre indica el nom del fitxer

	}

	/* PUBLIC METHODS */
	public boolean isEmpty() {
		return this.root[0] == null && this.root[1] == null;
	}
	public void rewind() {
		//COMPLETE
	}
	/* True if the current node is an answer (a leaf) */
	public boolean atAnswer() {
		return this.root[1].no == null && this.root[1].yes == null; //COMPLETE
	}
	/* move current to yes-descendant of itself */
	public void moveToYes() {
		this.root[1] = this.root[1].yes.root[0];
	}
	/* move current to yes-descendant of itself */
	public void moveToNo() {
		this.root[1] = this.root[1].no.root[0];
	}
	/* get the contents of the current node */
	public String getContents() {
		return ""; //COMPLETE
	}
	 /* Substituir la informació del node actual
	 * per la pregunta donada pel jugador. Previament crear el node que serà el
	 * seu fill dret, resposta no encertada, amb la informació del node actual.
	 */
	public void improve(String question, String answer) {
		ArbreB arbre_yes = new ArbreB(null, null, answer);
		ArbreB arbre_no = new ArbreB(null, null, this.root[1].contents);
		ArbreB nArbre = new ArbreB(arbre_yes, arbre_no, question);

	}
	private void preorderWrite(BufferedWriter buw) throws Exception {
		//Imprescindible que la implementació sigui recursiva
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
		//Imprescindible implementació recursiva
		return null;
	}
	public void visualitzarAnimals() {
		/* La implementació s’ha de fer, obligatòriament, invocant a un
			mètode de la classe NodeA. És irrellevant l’ordre de
			visualització*/

		/* COMPLETE */
	}
	public int quantsAnimals() {
		/* La implementació s’ha de fer, obligatòriament, invocant a un
			mètode de la classe NodeA */
		/* COMPLETE */
		return -1;
	}
	public int alsada() {
		/* COMPLETE */
		// Imprescindible invocar a un mètode la classe NodeA
		return -1;
	}
	public void visualitzarPreguntes() {
		/* COMPLETE */
		/*La implementació s’ha de fer, obligatòriament, invocant a un
			mètode de la classe NodeA. És irrellevant l’ordre de
			visualització*/
	}
}
