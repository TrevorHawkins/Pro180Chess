package Pro180_FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessReadFile { 

	public static void main(String[] args) {

		File commands = new File("chess.txt");
		FileReader in;

		try {
			in = new FileReader(commands);
			BufferedReader br = new BufferedReader(in);
			String line;
			Pattern placement = Pattern
					.compile("^(?<piece>[pknqbr])(?<color>[ld])(?<place>[a-h][1-8])$");
			Pattern Move = Pattern
					.compile("^(?<place>[a-h][1-8])(?<place2nd>[ ][a-h][1-8])$");
			Pattern capture = Pattern
					.compile("^(?<place>[a-h][1-8])(?<place2nd>[ ][a-h][1-8])([*])$");
			Pattern castle = Pattern
					.compile("^(?<place>[a-h][1-8][ ])(?<place2nd>[a-h][1-8][ ])(?<place3rd>[a-h][1-8][ ])(?<place4th>[a-h][1-8])$");

			while (br.ready()) {
				line = br.readLine();
				System.out.println(line);
				Matcher matchPlacement = placement.matcher(line);
				Matcher matchMove = Move.matcher(line);
				Matcher matchCapture = capture.matcher(line);
				Matcher matchCastle = castle.matcher(line);

				if (matchPlacement.find()) {
					String piece = null;
					String pieceSign = matchPlacement.group("piece");
					if (pieceSign.equalsIgnoreCase("k")) {
						piece = "King";
					} else if (pieceSign.equalsIgnoreCase("q")) {
						piece = "Queen";
					} else if (pieceSign.equalsIgnoreCase("p")) {
						piece = "Pawn";
					} else if (pieceSign.equalsIgnoreCase("r")) {
						piece = "Rook";
					} else if (pieceSign.equalsIgnoreCase("b")) {
						piece = "Bishop";
					} else if (pieceSign.equalsIgnoreCase("n")) {
						piece = "Knight";
					}
					System.out.println("places "
							+ piece
							+ " "
							+ matchPlacement.group("color")
							.replaceAll("l", "light")
							.replaceAll("d", "dark") + " on "
							+ matchPlacement.group("place"));
				}
				else if (matchMove.find()) {
					System.out.println("moves the piece on "
							+ matchMove.group("place") + " to "
							+ matchMove.group("place2nd"));
				}
				else if (matchCapture.find()) {
					System.out.println("moves the piece on "
							+ matchCapture.group("place") + " to "
							+ matchCapture.group("place2nd")
							+ " and captures the piece on "
							+ matchCapture.group("place2nd"));
				}
				else if (matchCastle.find()) {
					System.out.println("moves the piece on "
							+ matchCastle.group("place") + " to "
							+ matchCastle.group("place2nd")
							+ " and the piece from  "
							+ matchCastle.group("place3rd") + " to "
							+ matchCastle.group("place4th"));
				} else {
					System.out.println("Invalid Move");
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
