package test.Notifications;
/**
 * Notification interface is inherited by all notification types
 * e.g. Email, SMS, Whatsapp
 * @author kevin
 *
 */
public interface Notification {
	/**
	 * sends Notification
	 */
	public void sendNotification();
}
