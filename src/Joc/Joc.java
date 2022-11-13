package Joc;

import Estructura.ArbreB;
import Keyboard.Keyboard;

import java.util.Objects;

public class Joc {

	public static void main(String[] args) throws Exception {
		ArbreB root = new ArbreB("PROVA.TXT");

		/*
		ArbreB aYN = new ArbreB(null, null, "GAT");
		ArbreB aYY = new ArbreB(null, null, "GOS");
		ArbreB aY = new ArbreB(aYY, aYN, "Lladra?");
		ArbreB aN = new ArbreB(null, null, "MEDUSA");
		ArbreB root = new ArbreB(aY, aN, "Es un mamifer?");
		*/

		System.out.println("LListat de preguntes:");
		root.visualitzarPreguntes();
		System.out.println("");
		System.out.println("LListat de animals:");
		root.visualitzarAnimals();
		System.out.println("");
		System.out.println("Numero de animals:");
		System.out.println(root.quantsAnimals());
		System.out.println("");
		System.out.println("Alsada:");
		System.out.println(root.alsada());
		System.out.println("");

		boolean playing = true;

		while(playing) {
			while (!root.atAnswer()) {
				System.out.println(root.getContents());
				String resposta = Keyboard.readString();
				if (Objects.equals(resposta, "si"))
					root.moveToYes();
				else
					root.moveToNo();
			}

			System.out.format("Em sembla que ja ho se! Podrias ser un/a %s\n", root.getContents().toUpperCase());
			System.out.println("Es correcte? ");
			String resposta = Keyboard.readString();
			if (Objects.equals(resposta, "si"))
				System.out.println("He guanyat!");
			else {
				System.out.println("Ups he fallat!");
				System.out.println("Quin animal estabes pensant?");
				String answer = Keyboard.readString();
				System.out.println("Quina pregunta correspon a aquest animal? ");
				String question = Keyboard.readString();
				root.improve(question, answer);
			}

			root.save("PROVA.TXT");

			System.out.println("Vols tornar a jugar? ");
			String play = Keyboard.readString();
			if(play.equals("no")) playing = false;
			else root.rewind();

		}

		//root.save("PROVA.TXT");
	}


}
