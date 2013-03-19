package data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server implements Runnable {
	
	private Map<Integer, Appointment> appointments;
	private Map<String, Group> groups = new HashMap<String,Group>();
	private ArrayList<Member> members;
	private ArrayList<Participant> participants;
	private Map<String, Room> rooms;
	private ArrayList<Subgroup> subgroups;
	private Map<String, User> users;
	
	private Socket socket;
	private Main main;
	
	private BufferedReader reader;
	private PrintWriter writer;
	
	public Server(Main main) {
		this.main = main;
		appointments = new HashMap<Integer, Appointment>();
		groups = new HashMap<String, Group>();
		members = new ArrayList<Member>();
		participants = new ArrayList<Participant>();
		rooms = new HashMap<String, Room>();
		subgroups = new ArrayList<Subgroup>();
		users = new HashMap<String, User>();
		
//		bare test!
		users.put("torgeha", new User("torgeha", "lol", "Torgeir"));
		
//		try {
//			socket = new Socket("127.0.0.1", 50039);
//			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			writer = new PrintWriter(socket.getOutputStream(), true);
//			new Thread(this).run();
//			writer.println("select");
//		} catch (Exception e) {
//			try {
//				socket.close();
//			} catch (Exception f) {
//				f.printStackTrace();
//			}
//			main.connectionLost();
//			e.printStackTrace();
//		}
	}
	
	public void run() {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			while (!socket.isClosed()) {
				String msg = reader.readLine();
				String cmd = msg.substring(0, 6);
				String xml = msg.substring(6);
				if (cmd.equals("select")) {
					ArrayList<Object> objects = ConvertXML.XmlToObjects(xml, this);
					for (int i = 0; i < objects.size(); i++) {
						if (objects.get(i) instanceof Appointment) {
							appointments.put(((Appointment) objects.get(i)).getAppointmentId(), (Appointment) objects.get(i));
						} else if (objects.get(i) instanceof Participant) {
							participants.add((Participant) objects.get(i));
						}
					}
				} else if (cmd.equals("insert")) {
					ArrayList<Object> objects = ConvertXML.XmlToObjects(xml, this);
					for (int i = 0; i < objects.size(); i++) {
						if (objects.get(i) instanceof Appointment) {
							appointments.put(((Appointment) objects.get(i)).getAppointmentId(), (Appointment) objects.get(i));
						} else if (objects.get(i) instanceof Participant) {
							participants.add((Participant) objects.get(i));
						}
					}
				} else if (cmd.equals("update")) {
					ArrayList<Object> objects = ConvertXML.XmlToObjects(xml, this);
					for (int i = 0; i < objects.size(); i++) {
						if (objects.get(i) instanceof Appointment) {
							Appointment newAppointment = (Appointment) objects.get(i);
							Appointment appointment = appointments.get(newAppointment.getAppointmentId());
							appointment.setTitle(newAppointment.getTitle());
							appointment.setDescription(newAppointment.getDescription());
							appointment.setStartTime(newAppointment.getStartTime());
							appointment.setFinishTime(newAppointment.getFinishTime());
							appointment.setRoom(newAppointment.getRoom());
							//newAppointment.remove();
						} else if (objects.get(i) instanceof Participant) {
							Participant newParticipant = (Participant) objects.get(i);
							Participant participant = null;
							for (int j = 0; j < participants.size(); j++) {
								if (participants.get(j).getAppointment() == newParticipant.getAppointment() && participants.get(j).getUser() == newParticipant.getUser()) {
									participant = participants.get(j);
									break;
								}
							}
							participant.setAlarmTime(newParticipant.getAlarm());
							participant.setStatus(newParticipant.getStatus());
							//newParticipant.remove();
						}
					}
				} else if (cmd.equals("delete")) {
					ArrayList<Object> objects = ConvertXML.XmlToObjects(xml, this);
					for (int i = 0; i < objects.size(); i++) {
						if (objects.get(i) instanceof Appointment) {
							Appointment newAppointment = (Appointment) objects.get(i);
							Appointment appointment = appointments.get(newAppointment.getAppointmentId());
							appointments.remove(appointment.getAppointmentId());
							//appointment.remove();
							//newAppointment.remove();
						} else if (objects.get(i) instanceof Participant) {
							Participant newParticipant = (Participant) objects.get(i);
							Participant participant = null;
							for (int j = 0; j < participants.size(); j++) {
								if (participants.get(j).getAppointment() == newParticipant.getAppointment() && participants.get(j).getUser() == newParticipant.getUser()) {
									participant = participants.get(j);
									break;
								}
							}
							participants.remove(participant);
							//participant.remove();
							//newParticipant.remove();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			main.connectionLost();
		}
	}
	
	Map<Integer, Appointment> getAppointments() {
		return appointments;
	}
	
	void insertAppointment(Appointment appointment) {
		
	}
	
	void updateAppointment(Appointment appointment) {
		
	}
	
	void deleteAppointment(Appointment appointment) {
		
	}
	
	Map<String, Group> getGroups() {
		return groups;
	}
	
	ArrayList<Member> getMembers() {
		return members;
	}
	
	ArrayList<Participant> getParticipants() {
		return participants;
	}
	
	void updateParticipant(Participant participant) {
		
	}
	
	Map<String, Room> getRooms() {
		return rooms;
	}
	
	ArrayList<Subgroup> getSubgroups() {
		return subgroups;
	}
	
	Map<String, User> getUsers() {
		return users;
	}
	
	void addUser(User user){
		users.put(user.getUserName(), user);
	}

}
