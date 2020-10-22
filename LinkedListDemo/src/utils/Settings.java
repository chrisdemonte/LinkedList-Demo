package utils;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Settings {
	public static double width;
	public static double height;
	public static int fontSize;
	public static Font mainFont;
	public static Color canvasColor;
	public static Color borderColor;
	public static Color controlColor;
	public static Color linkColor;
	public static Color arrowColor;
	public static Color linkedListColor;

	public static void init(double w, double h) {
		width = w;
		height = h;
		fontSize = ((int)(h * .01) * 2) + 2;
		mainFont = new Font(fontSize);
		canvasColor = Color.AZURE;
		borderColor = Color.WHITE;
		controlColor = Color.ANTIQUEWHITE;
		linkColor = Color.LIGHTBLUE;
		arrowColor = Color.PALEVIOLETRED;
		linkedListColor = Color.MEDIUMPURPLE;
	}
}
