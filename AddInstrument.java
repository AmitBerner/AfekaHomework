import java.util.Arrays;
import java.util.InputMismatchException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AddInstrument {

	private final int MAX_W = 300;
	private final int MAX_H = 300;
	private final int SPACE = 10;
	private final String GUITAR = "Guitar";
	private final String BASS = "Bass";
	private final String FLUTE = "Flute";
	private final String SAXOPHONE = "Saxophone";
	private ObservableList<String> instrumentTypesList = FXCollections
			.observableList(Arrays.asList("Guitar", "Bass", "Flute", "Saxophone"));
	private ObservableList<String> materialsList = FXCollections
			.observableList(Arrays.asList("Wood", "Metal", "Plastic"));
	private ObservableList<String> guitarsList = FXCollections
			.observableList(Arrays.asList("Classic", "Acoustic", "Electric"));
	private ObservableList<String> fluteTypeNames = FXCollections.observableList(Arrays.asList("Recorder", "Bass"));
	private ComboBox<String> guitars = new ComboBox<>(guitarsList);
	private ComboBox<String> flutes = new ComboBox<>(fluteTypeNames);
	private ComboBox<String> materials = new ComboBox<>(materialsList);
	private ComboBox<String> instrumentTypes = new ComboBox<>(instrumentTypesList);
	private CheckBox isFretless = new CheckBox();
	private TextField textBrand = new TextField();
	private TextField textPrice = new TextField();
	private TextField textNumOfStrings = new TextField();

	public void displayWindow() {

		// PrimaryStage
		Stage chooseStage = new Stage();

		// Main Pane
		BorderPane addPane = new BorderPane();
		addPane.getChildren().clear(); // Clear the pane

		chooseStage.setHeight(MAX_H);
		chooseStage.setWidth(MAX_W);

		// AddButton + ComboBox
		Button addButton = new Button("Add");

		instrumentTypes.setOnAction(e -> {

			secondLook(addButton, addPane);

		});
		addButton.setOnAction(e -> {

			addWasClicked();

		});

		// First look view
		HBox chooseTypePane = firstLook();
		addPane.setCenter(chooseTypePane);
		chooseTypePane.setAlignment(Pos.CENTER);
		Scene chooseScene = new Scene(addPane);
		chooseStage.setScene(chooseScene);
		chooseStage.show();

	}

	private void addWasClicked() {
		Number price = priceParser(textPrice.getText());
		
		try {

			String addBox = instrumentTypes.getValue();

			switch (addBox) {

			case GUITAR:

				Guitar guitar = new Guitar(textBrand.getText(), price,
						Integer.parseInt(textNumOfStrings.getText()), guitars.getValue());
				InventoryActions.getSearchHolder().add(guitar);
				InventoryActions.switchToFull();
				break;

			case BASS:
				Bass bass = new Bass(textBrand.getText(), price,
						Integer.parseInt(textNumOfStrings.getText()), isFretless.isSelected());
				InventoryActions.getSearchHolder().add(bass);
				InventoryActions.switchToFull();
				break;

			case FLUTE:

				Flute flute = new Flute(textBrand.getText(), price,
						materials.getValue(), flutes.getValue());
				InventoryActions.getSearchHolder().add(flute);
				InventoryActions.switchToFull();

				break;
			case SAXOPHONE:
				Saxophone saxophone = new Saxophone(textBrand.getText(), price);
				InventoryActions.getSearchHolder().add(saxophone);
				InventoryActions.switchToFull();
				break;

			}

		} catch (InputMismatchException | IllegalArgumentException ex) {

			MainMenu.searchErrorMsg(ex.getMessage());

		}
		InventoryActions.setIndex(InventoryActions.getInventory().size() - 1);
		MainMenu.setTextDisplay(InventoryActions.getInventory().get(InventoryActions.getIndex()));
		
	}

	private Number priceParser(String price) {
		for (int i = 0; i < price.length(); i++) {
			if (!Character.isDigit(price.charAt(i)) && price.charAt(i) == '.') {
				double priceDouble = Double.parseDouble(price);
				return priceDouble;
			} else if ((!Character.isDigit(price.charAt(i)) && price.charAt(i) != '-')) {
				throw new InputMismatchException("Price can not be anything but numbers!\nPlease try again\n");

			}
		}
		int priceInteger = Integer.parseInt(price);
		return priceInteger;
	}

	private void secondLook(Button addButton, BorderPane addPane) {

		// Panes
		HBox topPane = new HBox();
		topPane.getChildren().add(instrumentTypes);
		GridPane centerPane = changeCenterLayout();
		HBox bottomPane = new HBox();
		bottomPane.getChildren().add(addButton);

		centerPane.setPadding(new Insets(SPACE, SPACE, SPACE, SPACE));
		bottomPane.setSpacing(SPACE);
		bottomPane.setPadding(new Insets(SPACE, SPACE, SPACE, SPACE));
		topPane.setPadding(new Insets(SPACE, SPACE, SPACE, SPACE));
		bottomPane.setAlignment(Pos.BOTTOM_CENTER);
		topPane.setAlignment(Pos.CENTER);

		addPane.setTop(topPane);
		addPane.setCenter(centerPane);
		addPane.setBottom(bottomPane);

	}

	private GridPane changeCenterLayout() {
		GridPane centerLayout = new GridPane();
		// Labels
		Label brand = new Label("Brand");
		Label price = new Label("Price");

		textBrand.setPromptText("Ex: Gibson");
		textPrice.setPromptText("Ex: 7500");

		// Set GridPane
		GridPane.setConstraints(brand, 0, 0);
		GridPane.setConstraints(textBrand, 1, 0);
		GridPane.setConstraints(price, 0, 1);
		GridPane.setConstraints(textPrice, 1, 1);

		// Set Spacing
		centerLayout.setAlignment(Pos.TOP_CENTER);
		centerLayout.setVgap(SPACE);
		centerLayout.setHgap(SPACE);
		centerLayout.setPadding(new Insets(SPACE, SPACE, SPACE, SPACE));

		centerLayout.getChildren().addAll(brand, textBrand, price, textPrice);

		chosenTypeParameters(centerLayout);
		return centerLayout;
	}

	private void chosenTypeParameters(GridPane centerLayout) {

		switch (instrumentTypes.getValue()) {

		case GUITAR:

			addStringVars(centerLayout);

			// Label
			Label guitarTypeLabel = new Label("Guitar Type");

			// ComboBox

			guitars.setPromptText("Type");

			GridPane.setConstraints(guitarTypeLabel, 0, 3);
			GridPane.setConstraints(guitars, 1, 3);

			centerLayout.getChildren().addAll(guitarTypeLabel, guitars);

			break;

		case BASS:
			// Bass Layout

			addStringVars(centerLayout);

			// Label
			Label fretless = new Label("Fretless");

			// CheckBox

			isFretless = new CheckBox();

			// Buttons

			GridPane.setConstraints(fretless, 0, 3);
			;
			GridPane.setConstraints(isFretless, 1, 3);

			centerLayout.getChildren().addAll(fretless, isFretless);

			break;

		case FLUTE:

			// Flute Layout

			addWindVars(centerLayout);

			// Label
			Label fluteTypeLabel = new Label("Flute Type");

			// ComboBox

			flutes.setPromptText("Type");

			GridPane.setConstraints(fluteTypeLabel, 0, 3);
			GridPane.setConstraints(flutes, 1, 3);
			centerLayout.getChildren().addAll(fluteTypeLabel, flutes);

			break;

		}

	}

	private void addWindVars(GridPane centerLayout) {
		// Label
		Label material = new Label("Material");

		// Material ComboBox

		materials.setPromptText("Material");

		GridPane.setConstraints(material, 0, 2);
		GridPane.setConstraints(materials, 1, 2);

		centerLayout.getChildren().addAll(material, materials);

	}

	private void addStringVars(GridPane centerLayout) {
		// Label
		Label numOfStrings = new Label("Number of Strings");

		// TextField
		textNumOfStrings.setPromptText("Ex 6");
		GridPane.setConstraints(numOfStrings, 0, 2);
		GridPane.setConstraints(textNumOfStrings, 1, 2);
		centerLayout.getChildren().addAll(numOfStrings, textNumOfStrings);

	}

	private HBox firstLook() {
		HBox firstChoise = new HBox();
		instrumentTypes.setPromptText("Choose instrument type here:");
		firstChoise.getChildren().add(instrumentTypes);
		firstChoise.setAlignment(Pos.CENTER);

		return firstChoise;

	}
}
