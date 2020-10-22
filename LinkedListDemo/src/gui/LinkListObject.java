package gui;

import app.App;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import models.Link;
import utils.Direction;
import utils.Settings;
import utils.Utils;

public class LinkListObject extends VBox{

	public Label label;
	public Label firstLabel;
	public Label lastLabel;
	public Label sizeLabel;
	
	public LinkObject first;
	public LinkObject last;
	
	public ArrowObject firstArrow;
	public ArrowObject lastArrow;
	
	public LinkListObject (double x, double y, double width, double height, LinkObject first, LinkObject last) {
		Utils.nodeForm(x, y, width, height, Settings.linkedListColor, this);
		
		this.first = first;
		this.last = last;
		
		this.label = new Label("Linked List");
		Utils.labelForm(width, height *.2, label, Pos.BASELINE_CENTER);
		this.firstLabel = new Label();
		Utils.labelForm(width, height *.2, firstLabel, Pos.BASELINE_LEFT);
		this.lastLabel = new Label();
		Utils.labelForm(width, height *.2, lastLabel, Pos.BASELINE_LEFT);
		this.sizeLabel = new Label();
		Utils.labelForm(width, height *.2, sizeLabel, Pos.BASELINE_LEFT);
		
		this.updateObject();
		
		Tooltip tt = new Tooltip();
		tt.setFont(Settings.mainFont);
		tt.setText("This is a LinkList object.\nThe memory address of this LinkList is " + App.list.toString().replace("models.LinkedList", "") +
				"\nThe variables of this LinkList are:\n\tLink " + firstLabel.getText() + 
				"\n\tLink " + lastLabel.getText() + "\n\tint " + sizeLabel.getText());
		tt.setAutoHide(false);
		
		this.label.setTooltip(tt);
		this.firstLabel.setTooltip(tt);
		this.lastLabel.setTooltip(tt);
		this.sizeLabel.setTooltip(tt);
		
		this.getChildren().addAll(label, firstLabel, lastLabel, sizeLabel);
	}
	
	public void updateObject() {
		
		this.firstArrow = new ArrowObject(
				first.getLayoutX() + first.getPrefWidth() * .1,
				this.getLayoutY(),
				first.getLayoutX() + first.getPrefWidth() * .1,
				first.getLayoutY() + first.getPrefHeight(),
				Direction.UP);
		if (App.list.size() < 4) {
			this.lastArrow = new ArrowObject(
					last.getLayoutX() + last.getPrefWidth() * .25,
					this.getLayoutY(),
					last.getLayoutX() + last.getPrefWidth() * .25,
					last.getLayoutY() + last.getPrefHeight(),
					Direction.UP);
		}
		else if (App.list.size() < 6) {
			this.lastArrow = new ArrowObject(
					this.getLayoutX() + this.getPrefWidth(),
					this.getLayoutY() + this.getPrefHeight() * .5,
					last.getLayoutX() + last.getPrefWidth() * .25,
					last.getLayoutY() + last.getPrefHeight(),
					Direction.UP);
		}
		else {
			this.lastArrow = new ArrowObject(
					this.getLayoutX() + this.getPrefWidth(),
					this.getLayoutY() + this.getPrefHeight() * .5,
					this.getLayoutX() + this.getPrefWidth() + Settings.width * .05,
					last.getLayoutX(),
					last.getLayoutY() + last.getPrefHeight() * .5,
					Direction.RIGHT);
		}
		this.firstLabel.setText("\tfirst = " + App.list.getFirstLink().toString().replace("models.Link", ""));
		this.lastLabel.setText("\tlast = " + App.list.getLastLink().toString().replace("models.Link", ""));
		this.sizeLabel.setText("\tsize = " + App.list.getNumElements());
	}
	public void modFirstLabel(Link link) {
		this.firstLabel.setText("\tfirst = " + link.toString().replace("models.Link", ""));
	}
	public void modLastLabel(Link link) {
		this.lastLabel.setText("\tlast = " + link.toString().replace("models.Link", ""));
	}
	public void modSizeLabel(int num) {
		this.sizeLabel.setText("\tsize = " + num);
	}
}
