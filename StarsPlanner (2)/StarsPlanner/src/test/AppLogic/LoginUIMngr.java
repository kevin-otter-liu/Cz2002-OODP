package test.AppLogic;

import test.MainApp;
import test.Enums.Mode;

/**
 * Controller Class for handling logic for LoginUI
 * @author kevin
 *
 */
public class LoginUIMngr {

	public boolean checkIfInputIsInDatabase(String userName, String passWord) {
		return MainApp.passWordModel.checkIfInputIsInDatabase(userName, passWord);
	}
	public Mode returnMode(String userName) {
		return MainApp.passWordModel.getMode(userName);
	}
}
