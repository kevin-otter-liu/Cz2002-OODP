package test.Models;

/**
 * Subject is an interface that Observers will subscribe to
 * @author kevin
 *
 */
public interface Subject {
	/**
	 * notifies all observers subscribed to Subject when a specifed event happens
	 */
	public void notifyObservers();
}
