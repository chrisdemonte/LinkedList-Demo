package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utils.Settings;
import utils.Utils;

public class CodeOutput extends VBox {
	
	public Label[] lines;
	public Label comments;
	int totalLines;
	int currentLine;
	double width;
	double height;
	
	public CodeOutput (double x, double y, double width, double height) {
		this.width = width;
		this.height = height;
		this.setPrefSize(width, height);
		this.setLayoutX(x);
		this.setLayoutY(y);
		lines = null;
		this.comments = new Label();
		Utils.labelForm(width + Settings.width * .04, Settings.height * .25, this.comments, Pos.BASELINE_LEFT);
		this.comments.setLayoutX(x - Settings.width * .02);
		this.comments.setLayoutY(Settings.height * .625);
		this.comments.setTextFill(Color.CORNFLOWERBLUE);
	}
	
	public boolean containsElements() {
		if (lines != null) {
			if (lines.length > 0) {
				return true;
			}
		}
		return false;
	}
	
	public void clear() {
		this.getChildren().clear();
		lines = null;
		totalLines = 0;
		currentLine = 0;
	}
	
	public void populateComments(String ... comments) {
		String out = new String();
		for (int i = 0; i < comments.length; i++) {
			if (i == 0) {
				out += "/**\t" + comments[0];
			}
			else if (i == comments.length - 1) {
				out += "\n*/\t" + comments[comments.length - 1];
			}
			else {
				out += "\n*\t" + comments[i];
			}
		}
		this.comments.setText(out);
		/**
		 * 
		 */
	}
	
	public void populateLines(String sourceCode) {
		String[] srcLines = sourceCode.split("\n");
		
		this.getChildren().clear();
		this.clear();
		
		totalLines = srcLines.length;

		lines = new Label[totalLines];
		for (int i = 0; i < totalLines; i++) {
			double mod = .05;
			for (int j = 0; j < srcLines[i].length(); j++) {
				if (srcLines[i].charAt(j) == '\r') {
					mod += .05;
				}
			}
			lines[i] = new Label(srcLines[i]);
			Utils.labelForm(width, Settings.height * mod, lines[i], Pos.BASELINE_LEFT);
		}
		this.getChildren().addAll(lines);
		this.updateLayout();
		
	}
	public void updateLayout() {
		if (this.containsElements()) {
			for (int i = 0; i < lines.length; i++) {
				if (i == currentLine) {
					lines[i].setTextFill(Color.GREEN);
				}
				else {
					lines[i].setTextFill(Color.BLACK);
				}
			}
		}
	}

}
