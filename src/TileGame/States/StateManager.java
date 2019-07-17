package TileGame.States;

public class StateManager {

	private static State currentState = null;

	public StateManager() {

	}

	public static void setState(State state) {
		currentState = state;
	}

	public static State getState() {
		return currentState;
	}
}
