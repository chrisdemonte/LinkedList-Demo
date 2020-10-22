package gui;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import utils.Direction;
import utils.Settings;

public class ArrowObject {
	
	public Shape[] shapes;
	
	public ArrowObject(double startX, double startY, double endX, double endY, Direction d) {
		shapes = new Shape[3];
		
		shapes[0] = new Line(startX, startY, endX, startY);
		shapes[1] = new Line(endX, startY, endX, endY);
		shapes[2] = getArrowHead(endX, endY, d);

	}
	public ArrowObject(double startX, double startY, double mid, double endX, double endY, Direction d) {
		shapes = new Shape[4];
		
		shapes[0] = new Line(startX, startY, mid, startY);
		shapes[1] = new Line(mid, startY, mid, endY);
		shapes[2] = new Line(mid, endY, endX, endY);
		shapes[3] = getArrowHead(endX, endY, d);

	}
	private Polygon getArrowHead(double x, double y, Direction d) {
		if (d == Direction.UP) {
			return  new Polygon(
					x, y - (Settings.height * .01), 
					x + (Settings.width * .01), y + (Settings.height * .02), 
					x - (Settings.width * .01), y + (Settings.height * .02));
		} 
		else if (d == Direction.DOWN) {
			return  new Polygon(
					x, y+ (Settings.height * .01), 
					x + (Settings.width * .01), y- (Settings.height * .02), 
					x - (Settings.width * .01), y - (Settings.height * .02));
		}
		else if (d == Direction.LEFT) {
			return  new Polygon(
					x - (Settings.width * .01), y, 
					x + (Settings.width * .01), y + (Settings.width * .01), 
					x + (Settings.width * .01), y - (Settings.width * .01));
		}
		else {
			return  new Polygon(
					x + (Settings.width * .01), y , 
					x - (Settings.width * .01), y + (Settings.width * .01), 
					x - (Settings.width * .01), y - (Settings.width * .01));
		}
	}

}
