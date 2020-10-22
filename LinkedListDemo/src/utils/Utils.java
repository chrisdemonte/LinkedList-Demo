package utils;

import app.App;
import gui.ArrowObject;
import gui.LinkObject;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import models.Link;

public class Utils {

	public static void labelForm (double width, double height, Labeled label, Pos pos ) {
		label.setFont(Settings.mainFont);	
		label.setPrefSize(width, height);
		label.setMinSize(width, height);
		label.setMaxSize(width, height);
		label.setAlignment(pos);
	}
	public static void nodeForm (double x, double y, double width, double height, Color color, Region region) {
		region.setPrefSize(width, height);
		region.setMinSize(width, height);
		region.setMaxSize(width, height);
		region.setLayoutX(x);
		region.setLayoutY(y);
		region.setBackground(new Background(new BackgroundFill(color, new CornerRadii(10), new Insets(0,0,0,0))));
		region.setBorder(new Border(new BorderStroke(Settings.borderColor, BorderStrokeStyle.SOLID,new CornerRadii(9),new BorderWidths(2), new Insets(0,0,0,0))));
	}
	public static void controlForm (double x, double y, double width, double height, Control control) {
		control.setPrefSize(width, height);
		control.setLayoutX(x);
		control.setLayoutY(y);
	}
	
	public static ArrowObject getArrowFromLink(LinkObject link, LinkObject nextLink) {
		double startX = link.getPrefWidth() + link.getLayoutX();
		double startY = (link.getPrefHeight() * .5) + link.getLayoutY();
		double endX;
		double endY;
		Direction d = Direction.DOWN;
		if (nextLink != null) {
			endX = (nextLink.getPrefWidth() * .8 )+ nextLink.getLayoutX();
			endY = nextLink.getLayoutY();
		}
		else {
			endX = (link.getPrefWidth() * .8)+ link.getLayoutX() + (Settings.width * .1);
			endY = startY;
			d = Direction.RIGHT;
		}
		return new ArrowObject(startX, startY, endX, endY, d);
	}
	public static ArrowObject getArrowNewNext(LinkObject link, LinkObject nextLink) {
		double startX = link.getPrefWidth() + link.getLayoutX();
		double startY = (link.getPrefHeight() * .5) + link.getLayoutY();
		double endX = nextLink.getLayoutX();
		double endY = nextLink.getLayoutY() + nextLink.getPrefHeight() * .25;
		Direction d = Direction.RIGHT;
		return new ArrowObject(startX, startY, Settings.width * .55, endX, endY, d);
	}
	public static ArrowObject getArrowNewFirst(LinkObject link, LinkObject nextLink) {
		double startX = link.getPrefWidth() + link.getLayoutX();
		double startY = (link.getPrefHeight() * .5) + link.getLayoutY();
		double endX = nextLink.getLayoutX();
		double endY = nextLink.getLayoutY() + nextLink.getPrefHeight() * .25;
		Direction d = Direction.RIGHT;
		return new ArrowObject(startX, startY, Settings.width * .6, endX, endY, d);
	}
	private static ArrowObject getArrowNewLast(LinkObject link) {
		
		double startX = App.window.linkedListObject.getLayoutX() + App.window.linkedListObject.getPrefWidth();
		double startY = App.window.linkedListObject.getLayoutY() + App.window.linkedListObject.getPrefHeight() * .75;
		double endX = link.getLayoutX();
		double endY = (link.getPrefHeight() * .75) + link.getLayoutY();
		Direction d = Direction.RIGHT;
		return new ArrowObject(startX, startY, Settings.width * .575, endX, endY, d);
	}
	public static ArrowObject getArrowNewNextPrepend(LinkObject link, LinkObject nextLink) {
		double startX = link.getLayoutX();
		double startY = (link.getPrefHeight() * .5) + link.getLayoutY();
		double endX;
		double endY;
		Direction d = Direction.LEFT;
		if (nextLink != null) {
			endX = nextLink.getPrefWidth() + nextLink.getLayoutX();
			endY = (nextLink.getPrefHeight()) * .25 + nextLink.getLayoutY();
		}
		else {
			endX = (link.getPrefWidth() * .8)+ link.getLayoutX() + (Settings.width * .1);
			endY = startY;
			
		}
		return new ArrowObject(startX, startY, Settings.width * .55, endX, endY, d);
	}
	public static void nextActions(int i) {
		switch (i) {
		case 1 :
			App.list.append(App.window.entry.getText());
			Link last = App.list.getLastLink();
			App.window.tempLink = new LinkObject(
					Settings.width * .7, 
					Settings.height * .55, 
					Settings.width * .175, 
					Settings.height * .1,
					last);
			App.window.getChildren().add(App.window.tempLink);
			App.window.tempArrow = getArrowFromLink(App.window.tempLink, null);
			App.window.getChildren().addAll(App.window.tempArrow.shapes);
			App.window.codeOutput.populateComments(
					"newLink takes String data as a parameter.", 
					"newLink also contains a Link next.",
					"next is the address of the next link in the",
					"chain, but newLink hasnt been connected to the",
					"Linked List yet, so it is equal to null.");
			break;
		case 2 :
			ArrowObject arrow = App.window.arrows.get(App.window.arrows.size() - 3);
			App.window.getChildren().removeAll(arrow.shapes);
			App.window.arrows.set(
					App.window.arrows.size() - 3, 
					getArrowNewNext(App.window.linkObjects.get(App.window.linkObjects.size() - 1), App.window.tempLink));
			App.window.linkObjects.get(App.window.linkObjects.size() - 1).modNext(App.window.tempLink.getLink());
			App.window.getChildren().addAll(App.window.arrows.get(App.window.arrows.size() - 3).shapes);
			App.window.codeOutput.populateComments(
					"To append newLink to the chain we connect", 
					"it to the last Link in the Link List.",
					"The variable last in the Link List points",
					"to the last Link, \"this.last.next = newLink\"",
					"is how we connect the newLink to the end list.");
			break;
		case 3 :
			ArrowObject arrow2 = App.window.arrows.get(App.window.arrows.size() - 1);
			App.window.getChildren().removeAll(arrow2.shapes);
			App.window.arrows.set(
					App.window.arrows.size() - 1, 
					getArrowNewLast(App.window.tempLink));
			App.window.linkedListObject.lastLabel.setText("\tlast = " + App.window.tempLink.getLink().toString().replace("models.Link", ""));
			App.window.getChildren().addAll(App.window.arrows.get(App.window.arrows.size() - 1).shapes);
			App.window.codeOutput.populateComments(
					"Now that newLink is connected, the ", 
					"the \"last\" Link in the Link List",
					"is not actually the last Link anymore.",
					"\"this.last. = newLink\" updates \"last\"",
					"in the Linked List so it points to newLink");
			break;
		case 4 :
			App.window.linkedListObject.sizeLabel.setTextFill(Color.GREEN);
			App.window.linkedListObject.modSizeLabel(App.list.size());
			App.window.codeOutput.populateComments(
					"At this point, newLink has been successfully",
					"added to the Linked List, but we cannot",
					"forget to add to the Link counter.");
			break;
		case 5 :
			if (App.list.size() == 1) {
				ArrowObject arrow3 = App.window.arrows.get(App.window.arrows.size() - 1);
				App.window.getChildren().removeAll(arrow3.shapes);
				App.window.arrows.set(
						App.window.arrows.size() - 1, 
						getArrowNewLast(App.window.tempLink));
				App.window.linkedListObject.lastLabel.setText("\tlast = " + App.window.tempLink.getLink().toString().replace("models.Link", ""));
				App.window.getChildren().addAll(App.window.arrows.get(App.window.arrows.size() - 1).shapes);
			}
			App.window.codeOutput.populateComments(
					"Adding to the Front is more complicated than",
					"adding to the End. First we must check if the",
					"list is empty. \"first\" is the dummy, so we ask ",
					"if \"first.next\" is null. If so, the list is empty.",
					"newLink will become last, as well as the first.");
			break;
		case 6 :
			
			if (App.window.linkObjects.size() > 1) {
				App.window.getChildren().removeAll(App.window.tempArrow.shapes);
				App.window.tempArrow = getArrowNewNextPrepend(App.window.tempLink, App.window.linkObjects.get(1));
				App.window.getChildren().addAll(App.window.tempArrow.shapes);
				App.window.tempLink.link.next = App.window.linkObjects.get(1).link;
				App.window.tempLink.modNext(App.window.linkObjects.get(1).link);
			}
			App.window.codeOutput.populateComments(
					"If the list is empty, first.next (dummy's next)",
					"equals null, and this line of code does nothing.",
					"If first.next is a valid Link, this line of code",
					"connects the rest of the chain to the back of newLink.",
					"This is only half of the required code though.");

			break;
		case 7 :
			App.window.getChildren().removeAll(App.window.arrows.get(0).shapes);
			App.window.arrows.set(0, getArrowNewFirst(App.window.linkObjects.get(0), App.window.tempLink));
			App.window.linkObjects.get(0).link.next = App.window.tempLink.link;
			App.window.linkObjects.get(0).modNext(App.window.tempLink.link);
			App.window.getChildren().addAll(App.window.arrows.get(0).shapes);
			App.window.codeOutput.populateComments(
					"The final step to Prepending a Link to Linked List",
					"is connecting the whole chain to the dummy Link.",
					"We set \"first.next\" to newLink, and this",
					"connects the whole Linked List back together.",
					"Notice how the arrows lead all the way to the end.");
			break;
		case 8 :
			App.list.prepend(App.window.entry.getText());
			Link first = App.list.getFirstLink().next;
			first.next = null;
			App.window.tempLink = new LinkObject(
					Settings.width * .7, 
					Settings.height * .55, 
					Settings.width * .175, 
					Settings.height * .1,
					first);
			App.window.getChildren().add(App.window.tempLink);
			App.window.tempArrow = getArrowFromLink(App.window.tempLink, null);
			App.window.getChildren().addAll(App.window.tempArrow.shapes);
			App.window.codeOutput.populateComments(
					"newLink takes String data as a parameter.", 
					"newLink also contains a Link next.",
					"next is the address of the next link in the",
					"chain, but newLink hasnt been connected to the",
					"Linked List yet, so it is equal to null.");
			break;
		case -1 : 
			App.window.linkedListObject.sizeLabel.setTextFill(Color.BLACK);
			App.window.clearWindow();
			App.window.generateObjects();
			App.window.populateWindow();
			App.window.appendBtn.setDisable(false);
			App.window.prependBtn.setDisable(false);
			App.window.popBtn.setDisable(false);
			App.window.nextBtn.setDisable(true);
			App.window.codeOutput.populateComments(
					"A Linked List is different from an Array. In an",
					"array of strings, the adresses for each string",
					"is stored in the same place in memory. In a",
					"Linked List, Links are scattered everywhere. The",
					"structure comes from how Links point to each other.");
			
		}
	}
	
}
