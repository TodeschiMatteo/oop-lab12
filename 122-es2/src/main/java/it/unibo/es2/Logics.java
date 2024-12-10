package it.unibo.es2;

public interface Logics {
	
	/**
	 * @return the new value a button should show after being pressed
	 */
	Boolean hit(int row, int column);
	
	/**
	 * @return whether it is time to quit
	 */
	Boolean toQuit();
}
