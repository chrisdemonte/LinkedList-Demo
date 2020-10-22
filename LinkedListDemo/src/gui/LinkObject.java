package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import models.Link;
import utils.Settings;
import utils.Utils;

public class LinkObject extends VBox{
	
	public Link link;
	public Label label;
	public Label dataLabel;
	public Label nextLabel;
	
	public LinkObject(double x, double y, double width, double height, Link link) {
		
		this.link = link;
		
		Utils.nodeForm(x, y, width, height, Settings.linkColor, this);
		
		label = new Label("Link");
		Utils.labelForm(width, height * .3, label, Pos.BASELINE_CENTER);
		
		dataLabel = new Label("\tdata = " + link.data);
		Utils.labelForm(width, height * .3, dataLabel, Pos.BASELINE_LEFT );
		
		nextLabel = new Label();
		modNext(link.next);
		Utils.labelForm(width, height * .3, nextLabel, Pos.BASELINE_LEFT);
		
		Tooltip tt = new Tooltip();
		tt.setFont(Settings.mainFont);
		tt.setText("This is a Link object.\nThe memory address of this Link is " + link.toString().replace("models.Link", "") +
				"\nThe variables of this Link are:\n\tString " + dataLabel.getText() + 
				"\n\tLink " + nextLabel.getText());
		tt.setAutoHide(false);
		label.setTooltip(tt);
		dataLabel.setTooltip(tt);
		nextLabel.setTooltip(tt);
		this.getChildren().addAll(label, dataLabel, nextLabel);
	}

	public Link getLink() {
		return link;
	}
	
	public void modData(String str) {
		this.dataLabel.setText("\tdata = " + str);
	}
	public void modNext(Link link) {
		if (link == null) {
			this.nextLabel.setText("\tnext = null");
		}
		else {
			this.nextLabel.setText("\tnext = " + link.toString().replace("models.Link", ""));
		}
	}

}
