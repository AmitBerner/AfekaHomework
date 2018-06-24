import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CenterPane {

	private final int SPACE = 10;
	private TextField textType = new TextField();
	private TextField textBrand = new TextField();
	private TextField textPrice = new TextField();
	private GridPane centerPane = new GridPane();

	public void getCenterPane(BorderPane mainMenu) {
		// Not Editable TextFields
		textType.setEditable(false);
		textBrand.setEditable(false);
		textPrice.setEditable(false);
		
		//Labels For The TextFields
		Label type = new Label("Type:");
		Label brand = new Label("Brand:");
		Label price = new Label("Price:");
		
		
		//Spacing and Grid
		centerPane.setAlignment(Pos.CENTER);
		centerPane.setVgap(SPACE);
		centerPane.setHgap(SPACE);
		setAsGrid(type, textType, brand, textBrand, price, textPrice);
		centerPane.getChildren().addAll(type, textType, brand, textBrand, price, textPrice);
		centerPane.setPadding(new Insets(SPACE, SPACE, SPACE, SPACE));
		mainMenu.setCenter(centerPane);

	}
	
	private void setAsGrid(Label type, TextField textType, Label brand, TextField textBrand, Label price,
			TextField textPrice) {

		GridPane.setConstraints(type, 0, 0);
		GridPane.setConstraints(textType, 1, 0);
		GridPane.setConstraints(brand, 0, 1);
		GridPane.setConstraints(textBrand, 1, 1);
		GridPane.setConstraints(price, 0, 2);
		GridPane.setConstraints(textPrice, 1, 2);

	}
	
	public TextField getTextType() {
		return textType;
	}

	public void setTextType(TextField textType) {
		this.textType = textType;
	}

	public TextField getTextBrand() {
		return textBrand;
	}

	public void setTextBrand(TextField textBrand) {
		this.textBrand = textBrand;
	}

	public TextField getTextPrice() {
		return textPrice;
	}

	public void setTextPrice(TextField textPrice) {
		this.textPrice = textPrice;
	}


}
