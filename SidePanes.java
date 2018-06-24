import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SidePanes {

	private final int ZERO = 0;
	private final int SPACE = 10;
	private Button left = new Button("<");
	private Button right = new Button(">");

	public void getSidePane(BorderPane mainMenu) {
		// Layouts
		VBox leftPane = new VBox();
		VBox rightPane = new VBox();
		
		// Buttons Actions
		right.setOnAction(e -> {

			rightButtonAction();

		});
		left.setOnAction(e -> {

			leftButtonAction();
		});
		
		leftPane.setAlignment(Pos.CENTER_LEFT);
		rightPane.setAlignment(Pos.CENTER_RIGHT);
		leftPane.getChildren().add(left);
		rightPane.getChildren().add(right);
		leftPane.setPadding(new Insets(SPACE, SPACE, SPACE, SPACE));
		rightPane.setPadding(new Insets(SPACE, SPACE, SPACE, SPACE));
		mainMenu.setRight(rightPane);
		mainMenu.setLeft(leftPane);
	}
	
	private void rightButtonAction() {

		if (InventoryActions.getInventory().isEmpty()) {
			return;

		} else if (InventoryActions.getIndex() == InventoryActions.getInventory().size() - 1) {
			InventoryActions.setIndex(ZERO - 1);
		}

		InventoryActions.setIndex(InventoryActions.getIndex() + 1);

		MainMenu.setTextDisplay(InventoryActions.getInventory().get(InventoryActions.getIndex()));

	}

	private void leftButtonAction() {

		if (InventoryActions.getInventory().isEmpty()) {
			return;

		} else if (InventoryActions.getIndex() == ZERO) {
			InventoryActions.setIndex(InventoryActions.getInventory().size());

		}

		InventoryActions.setIndex(InventoryActions.getIndex() - 1);

		MainMenu.setTextDisplay(InventoryActions.getInventory().get(InventoryActions.getIndex()));

	}

}
