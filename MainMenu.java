import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainMenu extends Application {

	private final int MAX_H = 300;
	private final int MAX_W = 700;
	private static BorderPane mainMenu = new BorderPane();
	private static BottomPane bottom = new BottomPane();
	private static TopPane top = new TopPane();
	private static CenterPane center = new CenterPane();
	private static SidePanes sides = new SidePanes();
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		bottom.getBottomPane(mainMenu);
		top.getTopPane(mainMenu);
		center.getCenterPane(mainMenu);
		sides.getSidePane(mainMenu);
		
		InventoryActions.getInstrumentsFileFromUser();
		
		
		Scene scene = new Scene(mainMenu);
		primaryStage.setTitle("Afeka Instruments Music Store"); // Set the stage title
		primaryStage.setHeight(MAX_H);
		primaryStage.setWidth(MAX_W);
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
	}

	public static void setNoItems() {
		center.getTextType().setText("");
		center.getTextBrand().setText("");
		center.getTextPrice().setText("");
		center.getTextType().setPromptText("No item");
		center.getTextBrand().setPromptText("No item");
		center.getTextPrice().setPromptText("No item");

	}
	
	public static void setTextDisplay(MusicalInstrument instrument) {
			center.getTextType().setText(instrument.getClass().getCanonicalName());
			center.getTextBrand().setText(instrument.getBrand());
			center.getTextPrice().setText(instrument.getPrice().toString());

	}
	public static void searchErrorMsg(String searchText)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Error");
		alert.setContentText(searchText);
		alert.showAndWait();
	}
	
	public static BorderPane getMainMenu() {
		return mainMenu;
	}

	public static void setMainMenu(BorderPane mainMenu) {
		MainMenu.mainMenu = mainMenu;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

