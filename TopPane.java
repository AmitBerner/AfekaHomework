import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TopPane {

	private final static int ZERO = 0;
	private final int SEARCH_W = 600;
	private final int SPACE = 10;
	private TextField search = new TextField();
	private Button go = new Button("Go!");
	HBox topPane = new HBox();

	public void getTopPane(BorderPane mainMenu) {

		//Text Field
		search.setPrefWidth(SEARCH_W);
		search.setPromptText("Search...");

		
		// Search Action
		go.setOnAction(e -> {
			searchWasClicked();

		});
		topPane.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)

				searchWasClicked();

		});

		// Set as top and spacing
		topPane.setAlignment(Pos.TOP_CENTER);
		topPane.getChildren().addAll(search, go);
		topPane.setPadding(new Insets(SPACE, SPACE, SPACE, SPACE));
		topPane.setSpacing(SPACE);
		mainMenu.setTop(topPane);
	}
	
	public void searchWasClicked() {

		if (search.getText().isEmpty()) {
			InventoryActions.setIndex(ZERO);
			InventoryActions.switchToFull();
			MainMenu.setTextDisplay(InventoryActions.getInventory().get(InventoryActions.getIndex()));
			return;
		}
		InventoryActions.searchInstrument(search.getText());

	}
}
