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
			this.contents =contents.toUpperCase();
			this.yes = null;
			this.no = null;
		}
		NodeA(String pregunta, ArbreB a1, ArbreB a2) {
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

		public void printQuestions() {
			if (this.yes != null && this.no != null) {
				System.out.println(this.contents);
				this.yes.root[0].printQuestions();
				this.no.root[0].printQuestions();
			}
		}

		public void printAnimals() {
			if(this.yes == null && this.no == null) {
				System.out.println(this.contents);
			} else {
				this.yes.root[0].printAnimals();
				this.no.root[0].printAnimals();
			}
		}
	}
	
	private NodeA[] root;

	public ArbreB(ArbreB a1, ArbreB a2, String pregunta) {
		this.root = new NodeA[2];

		NodeA first = new NodeA(pregunta, a1, a2);
		this.root[0] = first;
		this.root[1] = first;
	}
	
	public ArbreB() {
		this.root = new NodeA[2];
	}
	
	public ArbreB(String filename) throws Exception{
		this.root = new NodeA[2];
		this.loadFromFile(filename);
	}

	public boolean isEmpty() {
		return this.root[0] == null && this.root[1] == null;
	}
	
	public void rewind() {
		this.root[1] = this.root[0];
	}

	public boolean atAnswer() {
		return this.root[1].no == null && this.root[1].yes == null; //COMPLETE
	}

	public void moveToYes() {
		this.root[1] = this.root[1].yes.root[0];
	}

	public void moveToNo() {
		this.root[1] = this.root[1].no.root[0];
	}

	public String getContents() {
		return this.root[1].contents; //COMPLETE
	}

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
		NodeA current = this.root[1];
		buw.write(current.contents + "\n");

		if(current.yes == null && current.no == null) return;
		this.root[1] = current.yes.root[0];
		this.preorderWrite(buw);
		this.root[1] = current.no.root[0];
		this.preorderWrite(buw);
	}

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
		this.root[0].printAnimals();
	}
	
	public int quantsAnimals() {
		return this.root[0].getAnimalCount();
	}
	
	public int alsada() {
		if(this.isEmpty()) return 0;
		return this.root[0].depth()-1;
	}
	
	public void visualitzarPreguntes() {
		this.root[0].printQuestions();
	}
}
