package Joc;

import Estructura.ArbreB;
import Keyboard.Keyboard;

import java.security.Key;
import java.sql.SQLOutput;
import java.util.Objects;

public class Joc {

	public static void main(String[] args) throws Exception {
		ArbreB root;

		System.out.print("Vols carregar un fixer? ");
		boolean cf = Keyboard.readString().equals("si");
		if(cf) {
			System.out.print("Nom del fixer: ");
			String filename = Keyboard.readString();
			root = new ArbreB(filename);

			System.out.println("Has carregat un fixer!");
			System.out.println("L'arbre conte els seguents animals:");
			root.visualitzarAnimals();
			System.out.println("En total te " + root.quantsAnimals() + " animals");
			System.out.println("i una alçada de " + root.alsada());

			System.out.println();
			System.out.print("Vols visualitzar les preguntes? ");
			boolean mostrarQ = Keyboard.readString().equals("si");
			if(mostrarQ) root.visualitzarPreguntes();
		} else {
			System.out.println("Per començar cal introduir la primera pregunta amb dues respostes\n");
			System.out.print("Indica la pregunta de l'arrel: ");
			String q = Keyboard.readString() + "?";
			System.out.println();
			System.out.print("Indica el nom de l'animal de la resposta afirmativa: ");
			String y = Keyboard.readString();
			System.out.print("Indica el nom de l'animal de la resposta negativa: ");
			String n = Keyboard.readString();

			root = new ArbreB(new ArbreB(null, null, y), new ArbreB(null, null, n), q);
		}

		System.out.println("\nJuguem!!\n");


		boolean playing = true;

		while(playing) {
			while (!root.atAnswer()) {
				System.out.print(root.getContents() + " ");
				String resposta = Keyboard.readString();
				if (Objects.equals(resposta, "si"))
					root.moveToYes();
				else
					root.moveToNo();
			}

			System.out.format("Em sembla que ja ho se! Podrias ser un/a %s\n", root.getContents().toUpperCase());
			System.out.print("Es correcte? ");
			String resposta = Keyboard.readString();
			if (Objects.equals(resposta, "si"))
				System.out.println("He guanyat!");
			else {
				System.out.println("Ups he fallat!");
				System.out.print("Quin animal estabes pensant? ");
				String answer = Keyboard.readString();
				System.out.print("Quina pregunta correspon a aquest animal? ");
				String question = Keyboard.readString();
				root.improve(question, answer);
			}

			System.out.println("Vols tornar a jugar? ");
			String play = Keyboard.readString();
			if(play.equals("no")) playing = false;
			else root.rewind();

		}

		if(!cf) {
			System.out.print("Vols guardar el fixer? ");
			if(Keyboard.readString().equals("si")) {
				System.out.print("Nom del fixer: ");
				String filename = Keyboard.readString();
				root.save(filename);
			}
		}

		System.out.println("\nAdeu!");
	}


}
