package it.unibo.es3;

import java.util.Set;

public interface Logics {

	/**
	 * 
	 * @return the set of the activated buttons
	 */
	Set<Point> getActivated();
	
	/**
	 * @return compute the action after clicking arrow
	 */
	void hitKey();
	
	/**
	 * @return whether it is time to quit
	 */
	Boolean toQuit();
}
