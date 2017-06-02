package it.polimi.ingsw.GC_43.controller;

import java.util.HashMap;
import java.util.Map;

public class Controller implements IController {
	    private final Map<String, String> userPassword = new HashMap<String, String>();


	    public boolean login(String username, String password) {
	        String pw = userPassword.get(username);

	        if (pw == null) {
	            userPassword.put(username, password);
	            return true;
	        }

	        return password.equals(pw);
	    }

}
