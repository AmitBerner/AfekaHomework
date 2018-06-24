import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class InventoryActions {

	private final static int ZERO = 0;
	private static Scanner userFileScanner;
	private static ArrayList<MusicalInstrument> inventory = new ArrayList<>();
	private static ArrayList<MusicalInstrument> searchHolder = new ArrayList<>();
	private static int index = 0;

	public static void getInstrumentsFileFromUser() {
		boolean stopLoop = true;
		File file = null;
		TextInputDialog userInput = new TextInputDialog("");
		userInput.setTitle("Text Input Dialog");
		userInput.setHeaderText("Load Instruments From File");
		userInput.setContentText("Please enter file name:");
		Alert noFile = new Alert(AlertType.ERROR);
		noFile.setTitle("Error");
		noFile.setHeaderText("File Error!");
		noFile.setContentText("cannot read from file, please try again");

		do {
			Optional<String> result = userInput.showAndWait();
			result.ifPresent(e -> {
				if (!checkFile(result.get()))
					noFile.showAndWait();
				else {
					setInventory(getMusicalInstrument(file));
					setSearchHolder(getInventory());
					MainMenu.setTextDisplay(inventory.get(index));

				}
			});
			if (!result.isPresent()) {
				System.exit(0);
			}
		} while (!stopLoop);

	}

	public static boolean checkFile(String path) {
		try {
			userFileScanner = new Scanner(new File(path));
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}

	}

	public static ArrayList<MusicalInstrument> getMusicalInstrument(File file) {

		addAllInstruments(inventory, loadGuitars(userFileScanner));

		addAllInstruments(inventory, loadBassGuitars(userFileScanner));

		addAllInstruments(inventory, loadFlutes(userFileScanner));

		addAllInstruments(inventory, loadSaxophones(userFileScanner));

		userFileScanner.close();

		return inventory;

	}

	public static ArrayList<Guitar> loadGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Guitar> guitars = new ArrayList<>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			guitars.add(new Guitar(scanner));

		return guitars;
	}

	public static ArrayList<Bass> loadBassGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Bass> bassGuitars = new ArrayList<>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			bassGuitars.add(new Bass(scanner));

		return bassGuitars;
	}

	public static ArrayList<Flute> loadFlutes(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Flute> flutes = new ArrayList<>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			flutes.add(new Flute(scanner));

		return flutes;
	}

	public static ArrayList<Saxophone> loadSaxophones(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Saxophone> saxophones = new ArrayList<>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			saxophones.add(new Saxophone(scanner));

		return saxophones;
	}

	public static <T extends MusicalInstrument> void addAllInstruments(ArrayList<MusicalInstrument> instruments,
			ArrayList<T> moreInstruments) {
		for (int i = 0; i < moreInstruments.size(); i++) {
			instruments.add(moreInstruments.get(i));
		}

	}

	public static void clearAll() {

		inventory.clear();
		searchHolder.clear();
		MainMenu.setNoItems();
		setIndex(ZERO);

	}

	public static void deleteInstrument(int index) {
		if (index == inventory.size() - 1) {
			searchHolder.remove(inventory.get(index));
			inventory.remove(inventory.get(index));
			setIndex(ZERO);
			MainMenu.setTextDisplay(inventory.get(index));
		} else if (inventory.isEmpty()) {
			MainMenu.setNoItems();
			setIndex(ZERO);
			;
		} else if (inventory.size() == 1) {
			searchHolder.remove(inventory.get(index));
			inventory.remove(index);
			MainMenu.setNoItems();
			setIndex(ZERO);
		} else {
			inventory.remove(inventory.get(index));
			MainMenu.setTextDisplay(inventory.get(index));

		}
	}

	public static void searchInstrument(String searchText) {
		ArrayList<MusicalInstrument> searched = new ArrayList<>();
		for (int i = 0; i < getInventory().size(); i++) {
			if (getInventory().get(i).toString().toLowerCase().contains(searchText.toLowerCase())) {
				searched.add(getInventory().get(i));
			}
		}
		if (searched.isEmpty()) {
			MainMenu.searchErrorMsg(searchText + " does not exists in the inventory");

		} else {
			setIndex(ZERO);
			setInventory(searched);
			MainMenu.setTextDisplay(getInventory().get(getIndex()));
		}
	}

	public static void switchToFull() {
		setInventory(getSearchHolder());
	}

	public static ArrayList<MusicalInstrument> getSearchHolder() {
		return searchHolder;
	}

	public static void setSearchHolder(ArrayList<MusicalInstrument> searchHolder) {
		InventoryActions.searchHolder = searchHolder;
	}

	public static ArrayList<MusicalInstrument> getInventory() {
		return inventory;
	}

	public static void setInventory(ArrayList<MusicalInstrument> inventory) {
		InventoryActions.inventory = inventory;
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		InventoryActions.index = index;
	}
}
