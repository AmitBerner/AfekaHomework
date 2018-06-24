import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BottomPane {

	private final int SPACE = 10;
	private Timeline currentTime = new Timeline();
	private String saleMsg = "Afeka Instruments Music Store $$$ ON SALE!!! $$$ Guitars,Basses,Flutes,Saxophones and more!";

	public void getBottomPane(BorderPane mainMenu) {
		// Layouts
		HBox bottomMsg = new HBox();
		HBox actionButtons = new HBox();
		VBox bottomLayout = new VBox();

		// Path Transition
		Text displayTime = new Text("");
		PathTransition msgAnimation = new PathTransition();
		setBottomTextAnimation(msgAnimation, bottomMsg, displayTime);
		refrashTime(displayTime);

		// String font
		displayTime.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
		displayTime.setFill(Color.RED);

		// Buttons
		Button add = new Button("Add");
		Button delete = new Button("Delete");
		Button clear = new Button("Clear");

		// Buttons action
		clear.setOnAction(e -> InventoryActions.clearAll());
		add.setOnAction(e -> addWasClicked());
		delete.setOnAction(e -> {
			InventoryActions.deleteInstrument(InventoryActions.getIndex());

		});
		mainMenu.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.D) {
				deleteWasClicked();
			} else if (e.getCode() == KeyCode.A) {
				addWasClicked();
			}

		});
		
		// Set layout
		bottomMsg.getChildren().add(displayTime);
		actionButtons.getChildren().addAll(add, clear, delete);
		bottomLayout.getChildren().addAll(actionButtons, bottomMsg);
		actionButtons.setSpacing(SPACE);
		actionButtons.setPadding(new Insets(SPACE, SPACE, SPACE, SPACE));
		bottomLayout.setPadding(new Insets(SPACE, SPACE, SPACE, SPACE));
		actionButtons.setAlignment(Pos.BOTTOM_CENTER);
		bottomMsg.setAlignment(Pos.BOTTOM_CENTER);
		bottomLayout.setAlignment(Pos.BOTTOM_CENTER);

		mainMenu.setBottom(bottomLayout);


	}

	public void setBottomTextAnimation(PathTransition msgAnimation, HBox storeMsg, Text displayTime) {
		final int ENDX = 500;
		final int ENDY = 0;
		final int STARTX = 100;
		final int STARTY = 0;
		final int MAX = 10;

		Line line = new Line(STARTX, STARTY, ENDX, ENDY);
		msgAnimation.setNode(displayTime);
		msgAnimation.setPath(line);
		msgAnimation.setDuration(Duration.seconds(MAX));
		msgAnimation.setAutoReverse(true);
		msgAnimation.setCycleCount(Animation.INDEFINITE);
		storeMsg.setOnMouseEntered(e -> msgAnimation.pause());
		storeMsg.setOnMouseExited(e -> msgAnimation.play());
		msgAnimation.play();

	}

	public void refrashTime(Text timeDisplay) {
		currentTime.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
			timeDisplay
					.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss")) + saleMsg);
		}));
		currentTime.setCycleCount(Animation.INDEFINITE);
		currentTime.play();

	}

	public void deleteWasClicked() {

		InventoryActions.deleteInstrument(InventoryActions.getIndex());

	}

	public void addWasClicked() {
		AddInstrument addWindow = new AddInstrument();
		addWindow.displayWindow();

	}
}
