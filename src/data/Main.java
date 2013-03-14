package data;

import java.util.ArrayList;

public class Main {
	
	Server server = new Server();
	
	public ArrayList<User> getPersons(){
		return server.getPersons();
		
	}

}
