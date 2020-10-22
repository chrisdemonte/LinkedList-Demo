package gui;

import java.util.ArrayList;
import app.App;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import models.Link;
import utils.Settings;
import utils.Utils;

public class MainWindow extends Pane{

	public Pane mainCanvas;
	public Pane controlCanvas;
	
	public ArrayList<LinkObject> linkObjects;
	public ArrayList<ArrowObject> arrows;
	
	public LinkListObject linkedListObject;
	
	public CodeOutput codeOutput;
	public LinkObject tempLink;
	public ArrowObject tempArrow;
	public int[] eventCodes;
	
	public TextField entry;
	public Button appendBtn;
	public Button prependBtn;
	public Button popBtn;
	public Button nextBtn;
	
	public MainWindow() {
		
		linkObjects = new ArrayList<LinkObject>();
		arrows = new ArrayList<ArrowObject>();
		
		this.generateObjects();
		
		mainCanvas = new Pane();
		mainCanvas.setPrefSize(Settings.width, Settings.height * .85);
		mainCanvas.setBackground(new Background(new BackgroundFill(Settings.canvasColor, new CornerRadii(10), new Insets(0,0,0,0))));
		mainCanvas.setBorder(new Border(new BorderStroke(Settings.borderColor, BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(2), new Insets(0,0,0,0))));
		
		controlCanvas = new Pane();
		controlCanvas.setPrefSize(Settings.width, Settings.height * .15);
		controlCanvas.setLayoutY(Settings.height * .85);
		controlCanvas.setBackground(new Background(new BackgroundFill(Settings.controlColor, new CornerRadii(10), new Insets(0,0,0,0))));
		controlCanvas.setBorder(new Border(new BorderStroke(Settings.borderColor, BorderStrokeStyle.SOLID,new CornerRadii(10),new BorderWidths(2), new Insets(0,0,0,0))));
		
		entry = new TextField();
		Utils.controlForm(Settings.width * .05, Settings.height * .9, Settings.width * .2, Settings.height * .05, entry);
		entry.setText("Sample Text");
		entry.setFont(Settings.mainFont);
		
		appendBtn = new Button("APPEND");
		Utils.controlForm(Settings.width * .3, Settings.height * .9, Settings.width * .1, Settings.height * .05, appendBtn);
		appendBtn.setFont(Settings.mainFont);
		appendBtn.setOnAction(e->{
			if (App.list.getNumElements() < App.list.getMaxSize()) {
				this.codeOutput.populateLines(
						"public void append(String data) {\n" + 
						"\tLink newLink = new Link(data);\n" + 
						"\tthis.lastLink.next = newLink;\n" + 
						"\tthis.lastLink = newLink;\n" + 
						"\tthis.numElements++;\n" + 
						"}");
				this.appendBtn.setDisable(true);
				this.prependBtn.setDisable(true);
				this.popBtn.setDisable(true);
				this.nextBtn.setDisable(false);
				this.eventCodes = new int[]{1,2,3,4,-1};
			}
		});
		prependBtn = new Button("PREPEND");
		
		Utils.controlForm(Settings.width * .45, Settings.height * .9, Settings.width * .1, Settings.height * .05, prependBtn);
		prependBtn.setFont(Settings.mainFont);
		prependBtn.setOnAction(e->{
			if (App.list.getNumElements() < App.list.getMaxSize()) {
				this.codeOutput.populateLines(
						"public void prepend (String data) {\n" + 
						"\tLink newLink = new Link(data);\n" + 
						"\tif (firstLink.next == null) {\r\t\tlastLink = newLink;\r\t{\n" + 
						"\tnewLink.next = this.firstLink.next;\n" + 
						"\tthis.firstLink.next = newLink;\n" + 
						"\tthis.numElements++;\n" + 
						"}");
				this.appendBtn.setDisable(true);
				this.prependBtn.setDisable(true);
				this.popBtn.setDisable(true);
				this.nextBtn.setDisable(false);
				this.eventCodes = new int[]{8,5,6,7,4,-1};
			}
		});
		
		popBtn = new Button("POP");
		Utils.controlForm(Settings.width * .6, Settings.height * .9, Settings.width * .1, Settings.height * .05, popBtn);
		popBtn.setFont(Settings.mainFont);
		popBtn.setOnAction(e->{
			/*
			this.codeOutput.populateLines(
					"public Link pop() {\n" + 
					"\tif (numElements > 0) {\n" + 
					"\t\tLink link = firstLink.next;\n" + 
					"\t\tthis.firstLink.next = link.next;\n" + 
					"\t\tthis.numElements--;\n" + 
					"\t\tif (numElements == 0) { \r\t\t\tthis.lastLink = this.firstLink;\r\t\t{\n" + 
					"\t\treturn link;\r\t}\n" + 
					"\treturn null;\n" + 
					"}");
			this.appendBtn.setDisable(true);
			this.prependBtn.setDisable(true);
			this.popBtn.setDisable(true);
			this.nextBtn.setDisable(false);
			this.eventCodes = new int[]{1,5,6,7,4,-1}
			*/
			if (App.list.size() > 0) {
				App.list.pop();
				App.window.clearWindow();
				App.window.generateObjects();
				App.window.populateWindow();
			}
		});
		
		nextBtn = new Button("NEXT");
		Utils.controlForm(Settings.width * .75, Settings.height * .9, Settings.width * .1, Settings.height * .05, nextBtn);
		nextBtn.setFont(Settings.mainFont);
		nextBtn.setDisable(true);
		nextBtn.setOnAction(e->{
			this.nextEventHandler();
		});
		
		codeOutput = new CodeOutput(Settings.width * .675, Settings.height * .05, Settings.width * .3, Settings.height * .5);
		
		this.getChildren().addAll(mainCanvas, controlCanvas, entry, appendBtn, prependBtn, popBtn, nextBtn,codeOutput);
		this.populateWindow();
		
	}
	
	private void nextEventHandler() {
	
		Utils.nextActions(this.eventCodes[this.codeOutput.currentLine++]);
		this.codeOutput.updateLayout();
		
	}

	public void generateObjects() {
		
		linkObjects.clear();
		arrows.clear();
			
		Link[] arr = App.list.toArray();
		for (int i = 1; i <= arr.length; i++) {
			linkObjects.add(
					new LinkObject( 
							i * (Settings.width * .05) - (Settings.width * 0.0), //layout X
							i * (Settings.height * .1) - (Settings.height * .075), //layout y
							Settings.width * .175, //width
							Settings.height * .1,  //height
							arr[i - 1])); //link
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (i + 1 < arr.length) {
				arrows.add(Utils.getArrowFromLink(linkObjects.get(i), linkObjects.get(i+1)));
			}
			else {
				arrows.add(Utils.getArrowFromLink(linkObjects.get(i), null));
			}
		}
		this.linkedListObject = new LinkListObject(
				Settings.width *.05,
				Settings.height * .6,
				Settings.width * .2,
				Settings.height * .2,
				this.linkObjects.get(0),
				this.linkObjects.get(this.linkObjects.size() - 1));
		
		this.arrows.add(linkedListObject.firstArrow);
		this.arrows.add(linkedListObject.lastArrow);
	}
	public void clearWindow() {
		this.getChildren().removeAll(linkObjects);
		this.getChildren().remove(linkedListObject);
		for (int i = 0; i < arrows.size(); i++) {
			this.getChildren().removeAll(arrows.get(i).shapes);
		}
		this.getChildren().removeAll(tempLink);
		this.getChildren().removeAll(tempArrow.shapes);
		this.codeOutput.clear();
	}
	public void populateWindow() {
		this.getChildren().addAll(linkObjects);
		this.getChildren().add(linkedListObject);
		for (int i = 0; i < arrows.size(); i++) {
			this.getChildren().addAll(arrows.get(i).shapes);
		}
	}
}
