package Joc;

import Estructura.ArbreB;
import Keyboard.Keyboard;

import java.util.Objects;

public class Joc {

	public static void main(String[] args) throws Exception {
		//ArbreB fileTest_root = new ArbreB("PROVA.TXT");

		ArbreB aYN = new ArbreB(null, null, "gat");
		ArbreB aYY = new ArbreB(null, null, "gos");
		ArbreB aY = new ArbreB(aYY, aYN, "lladra?");
		ArbreB aN = new ArbreB(null, null, "medusa");
		ArbreB root = new ArbreB(aY, aN, "mamifer?");

		root.visualitzarAnimals();
		System.out.println(root.quantsAnimals());

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

			System.out.println("Vols tornar a jugar? ");
			String play = Keyboard.readString();
			if(play.equals("no")) playing = false;
			else root.rewind();

		}
	}


}
