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
		ArbreB arbre_yes = new ArbreB(null, null, answer);
		ArbreB arbre_no = new ArbreB(null, null, this.root[1].contents);
		ArbreB nArbre = new ArbreB(arbre_yes, arbre_no, question);

		ArbreB parent = this.findTreeByChildContent(this.root[1].contents);
		System.out.println(parent.getContents());
		System.out.println(parent.root[0].yes == null);
		System.out.println(parent.root[0].no == null);
		if(parent.root[0].yes == null) {
			parent.root[0].yes = nArbre;
		} else if(parent.root[0].no == null) {
			parent.root[0].no = nArbre;
		}
	}

	// Busca el ArbreB que tingui un fill amb el contingut que se li passa com a argument
	private ArbreB findTreeByChildContent(String content) {
		ArbreB a_yes = this.root[0].yes;
		ArbreB a_no = this.root[0].no;
		if(a_yes == null || a_no == null) return null;
		if(a_yes.getContents().equals(content)) {
			//this.root[0].yes = null;
			return this;
		} else if(a_no.getContents().equals(content)) {
			//this.root[0].no = null;
			return this;
		}

		ArbreB ans_yes = a_yes.findTreeByChildContent(content);
		ArbreB ans_no = a_no.findTreeByChildContent(content);
		if(ans_yes != null) {
			ans_yes.root[0].no = null;
			return ans_yes;
		}
		if(ans_no != null) {
			ans_no.root[0].yes = null;
			return ans_no;
		}

		return null;
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
		NodeA temp = this.root[0];
		int count = 0;
		if (temp.yes==null && temp.no==null) count++;
		else {
			count += temp.yes.quantsAnimals();
			count += temp.no.quantsAnimals();
		}
		return count;
	}
	public int alsada() {
		/* COMPLETE */
		// Imprescindible invocar a un m�tode la classe NodeA
		NodeA temp = this.root[0];
		if (!(temp.yes==null && temp.no==null)) {
			return 1 + Math.max(temp.yes.alsada(),temp.no.alsada());
		}
		return 1;
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
