package Joc;

import Estructura.ArbreB;
import Keyboard.Keyboard;

import java.security.Key;
import java.sql.SQLOutput;
import java.util.Objects;

public class Joc {

	public static void main(String[] args) throws Exception {

		ArbreB root = askYesNo("Vols carregar un fixer?") ? dialogLoadFile() : dialogNewTree();

		System.out.println("\nJuguem!!");


		boolean playing = true;

		while(playing) {
			System.out.println(""); // Salt de linia estetic
			while (!root.atAnswer()) {
				if (askYesNo(root.getContents()))
					root.moveToYes();
				else
					root.moveToNo();
			}

			System.out.format("Em sembla que ja ho se! Podrias ser un/a %s\n", root.getContents().toUpperCase());
			if (askYesNo("Es correcte?"))
				System.out.println("He guanyat!");
			else {
				System.out.println("Ups he fallat!");
				String answer = askQuestion("Quin animal estaves pensant?");
				String question = askQuestion("Quina pregunta correspon a aquest animal?");
				root.improve(question, answer);
			}

			if(!askYesNo("\nVols tornar a jugar?")) playing = false;
			else root.rewind();

		}

		System.out.print("\nVols guardar el fixer? ");
		if(Keyboard.readString().equals("si")) {
			System.out.print("Nom del fixer: ");
			String filename = Keyboard.readString();
			root.save(filename);
		}

		System.out.println("\nAdeu!");
	}

	private static ArbreB dialogLoadFile() throws Exception {
		ArbreB response;
		String filename = askQuestion("Quin es el nom del fixer?");
		response = new ArbreB(filename);

		System.out.println("\nHas carregat un fixer!");
		System.out.println("\nL'arbre conte els seguents animals:");
		response.visualitzarAnimals();
		System.out.println("\nEn total te " + response.quantsAnimals() + " animals");
		System.out.println("i una alçada de " + response.alsada());

		System.out.println();
		boolean mostrarQ = askYesNo("Vols visualitzar les preguntes?");
		if(mostrarQ) response.visualitzarPreguntes();
		return response;
	}

	private static ArbreB dialogNewTree() {
		System.out.println("Per començar cal introduir la primera pregunta amb dues respostes\n");
		String q = askQuestion("Indica la pregunta de l'arrel:") + "?";
		System.out.println();
		String y = askQuestion("Indica el nom de l'animal de la resposta afirmativa:");
		String n = askQuestion("Indica el nom de l'animal de la resposta negativa:");

		return new ArbreB(new ArbreB(null, null, y), new ArbreB(null, null, n), q);
	}

	private static String askQuestion(String question) {
		System.out.print(question + " ");
		return Keyboard.readString();
	}

	private static boolean askYesNo(String question) {
		String ans = askQuestion(question);
		return ans.equalsIgnoreCase("si");
	}
}
