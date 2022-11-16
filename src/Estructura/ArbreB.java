package Estructura;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ArbreB {
	private class NodeA {
		String contents;
		ArbreB yes, no;	
	
		NodeA(String contents) {
			//Constructor 1. Inicialitza als atributys yes i no a null
			this.contents =contents.toUpperCase();
			this.yes = null;
			this.no = null;
		}
		NodeA(String pregunta, ArbreB a1, ArbreB a2) {
			//Constructor 2. Crea el node i l'inicialitza amb els par�metres
			this.contents = pregunta.toUpperCase();
			this.yes = a1;
			this.no = a2;

		}

		public int depth() {
			if (this.yes != null && this.no != null) {
				return 1 + Math.max(this.yes.root[0].depth(),this.no.root[0].depth());
			}
			return 1;
		}

		public int getAnimalCount() {
			int count = 0;
			if (this.yes==null && this.no==null) count++;
			else {
				count += this.yes.root[0].getAnimalCount();
				count += this.no.root[0].getAnimalCount();
			}
			return count;
		}
	}
	// Atributs: Taula de 2 posicions
	private NodeA[] root;

	/* CONSTRUCTORS */
	public ArbreB(ArbreB a1, ArbreB a2, String pregunta) {
		//Constructor 1. Crea un arbre amb una pregunta i dos respostes
		this.root = new NodeA[2];

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
		//El par�metre indica el nom del fitxer
		this.root = new NodeA[2];
		this.loadFromFile(filename);
	}

	/* PUBLIC METHODS */
	public boolean isEmpty() {
		return this.root[0] == null && this.root[1] == null;
	}
	public void rewind() {
		//COMPLETE
		this.root[1] = this.root[0];
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
		return this.root[1].contents; //COMPLETE
	}
	 /* Substituir la informaci� del node actual
	 * per la pregunta donada pel jugador. Previament crear el node que ser� el
	 * seu fill dret, resposta no encertada, amb la informaci� del node actual.
	 */
	public void improve(String question, String answer) {
		if(!question.endsWith("?")) question += "?";
		if(answer.endsWith("?")) answer = answer.substring(0, answer.length()-1);
		question = question.toUpperCase();
		answer = answer.toUpperCase();

		root[1].yes = new ArbreB(null, null, answer);
		root[1].no = new ArbreB(null, null, root[1].contents);
		root[1].contents = question;
	}

	private void preorderWrite(BufferedWriter buw) throws Exception {
		//Imprescindible que la implementaci� sigui recursiva
		NodeA current = this.root[1];
		buw.write(current.contents + "\n");

		if(current.yes == null && current.no == null) return;
		this.root[1] = current.yes.root[0];
		this.preorderWrite(buw);
		this.root[1] = current.no.root[0];
		this.preorderWrite(buw);
	}
	/* Saves contents of tree in a text file */
	public void save(String filename) throws Exception {
		BufferedWriter buw = null;
		try {
			buw = new BufferedWriter(new FileWriter(filename));
			this.rewind();
			this.preorderWrite(buw);
			buw.close();

		} catch (IOException e) {
			System.err.println("saveToTextFile failed: " + e);
			System.exit(0);
		}
	}
	private NodeA loadFromFile(String filename) {
		//Imprescindible implementaci� recursiva
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			ArbreB r = loadNext(reader);
			this.root[0] = r.root[0];
			this.root[1] = r.root[0];
		} catch(IOException e) {
			System.err.println("No s'ha trobat l'arxiu indicat");
		}
		return null;
	}

	private ArbreB loadNext(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		if(line == null) return null;
		line = line.toUpperCase();
		boolean isQuestion = line.endsWith("?");
		ArbreB response = new ArbreB(null, null, line);

		if(!isQuestion) return response;

		response.root[0].yes = this.loadNext(reader);
		response.root[0].no = this.loadNext(reader);

		return response;
	}

	public void visualitzarAnimals() {
		/* La implementaci� s�ha de fer, obligat�riament, invocant a un
			m�tode de la classe NodeA. �s irrellevant l�ordre de
			visualitzaci�*/

		/* COMPLETE */
		NodeA temp = this.root[0];
		if (temp.yes==null && temp.no==null)
			System.out.println(temp.contents);
		else {
			temp.yes.visualitzarAnimals();
			temp.no.visualitzarAnimals();
		}
	}
	public int quantsAnimals() {
		/* La implementaci� s�ha de fer, obligat�riament, invocant a un
			m�tode de la classe NodeA */
		/* COMPLETE */
		return this.root[0].getAnimalCount();
	}
	public int alsada() {
		/* COMPLETE */
		// Imprescindible invocar a un m�tode la classe NodeA
		if(this.isEmpty()) return 0;
		return this.root[0].depth()-1;
	}
	public void visualitzarPreguntes() {
		/* COMPLETE */
		/*La implementaci� s�ha de fer, obligat�riament, invocant a un
			m�tode de la classe NodeA. �s irrellevant l�ordre de
			visualitzaci�*/
		NodeA temp = this.root[0];
		if (!(temp.yes==null && temp.no==null)) {
			System.out.println(temp.contents);
			temp.yes.visualitzarPreguntes();
			temp.no.visualitzarPreguntes();
		}
	}
}
