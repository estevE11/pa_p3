package Joc;

import Estructura.ArbreB;

public class Joc {

	public static void main(String[] args) {
		ArbreB aY = new ArbreB(null, null, "Ets gilipollas");
		ArbreB aN = new ArbreB(null, null, "Ets llest");
		ArbreB root = new ArbreB(aY, aN, "T'agrada la assignatura de soft?");

		while(!root.atAnswer()) {
			System.out.println(root.getContents());
			System.out.println("> Simulamos q el usuario dice que no");
			root.moveToNo();
		}

		System.out.format("Resposta: %s", root.getContents());

	}

}
