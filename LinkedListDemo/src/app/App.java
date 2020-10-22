package app;

import gui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.LinkedList;
import utils.Settings;

public class App extends Application {

	public static LinkedList list;
	public static MainWindow window;
	
	@Override
	public void start(Stage mainStage) throws Exception {
		Settings.init(2000, 1200);
		//Settings.init(1400, 800);
		list = new LinkedList();
	//	list.append("cat");
	//	list.append("dog");
	//	list.append("fish");
		
		window = new MainWindow();
		
		Scene scene = new Scene(window);
		mainStage.setScene(scene);
		mainStage.setTitle("Linked List Demo");
		mainStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);

	}
}
