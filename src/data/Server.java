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

		try {
			socket = new Socket("192.168.1.2", 50039);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			int port = Integer.parseInt(reader.readLine());
			socket.close();
			socket = new Socket("192.168.1.2", port);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			new Thread(this).start();
			writer.println("select");
		} catch (Exception e) {
			try {
				socket.close();
			} catch (Exception f) {
				f.printStackTrace();
			}
			main.connectionLost();
			e.printStackTrace();
		}
		
//		bare for offline testing!

//		User t = new User("torgeha", "lol", "Torgeir");
//		users.put("torgeha", t);
//		rooms.put("rom1", new Room("rom1", 50));
//		groups.put("gr1", new Group("gr1", t));


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
						} else if(objects.get(i) instanceof Group) {
							groups.put(((Group) objects.get(i)).getGroupName(), (Group) objects.get(i));
						} else if(objects.get(i) instanceof Member) {
							members.add((Member) objects.get(i));
						} else if (objects.get(i) instanceof Participant) {
							participants.add((Participant) objects.get(i));
						} else if(objects.get(i) instanceof Room) {
							rooms.put(((Room) objects.get(i)).getRoomNumber(), (Room) objects.get(i));
						} else if(objects.get(i) instanceof Subgroup) {
							subgroups.add((Subgroup) objects.get(i));
						} else if(objects.get(i) instanceof User) {
							users.put(((User) objects.get(i)).getUsername(), (User) objects.get(i));
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
							newAppointment.remove();
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
							newParticipant.remove();
						}
					}
				} else if (cmd.equals("delete")) {
					ArrayList<Object> objects = ConvertXML.XmlToObjects(xml, this);
					for (int i = 0; i < objects.size(); i++) {
						if (objects.get(i) instanceof Appointment) {
							Appointment newAppointment = (Appointment) objects.get(i);
							Appointment appointment = appointments.get(newAppointment.getAppointmentId());
							appointments.remove(appointment.getAppointmentId());
							appointment.remove();
							newAppointment.remove();
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
							participant.remove();
							newParticipant.remove();
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
	

	public void insertAppointment(Appointment appointment) {
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.add(appointment);
		writer.println("insert" + ConvertXML.ObjectsToXml(objects));
		objects = new ArrayList<Object>(appointment.getParticipants());
		writer.println("insert" + ConvertXML.ObjectsToXml(objects));
	}
	
	public void updateAppointment(Appointment newAppointment, Appointment oldAppointment) {
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.add(newAppointment);
		writer.println("update" + ConvertXML.ObjectsToXml(objects));
		objects = new ArrayList<Object>();
		for (int i = 0; i < oldAppointment.getParticipants().size(); i++) {
			if (!newAppointment.getParticipants().contains(oldAppointment.getParticipants().get(i))) {
				objects.add(oldAppointment.getParticipants().get(i));
			}
		}
		writer.println("delete" + ConvertXML.ObjectsToXml(objects));
		objects = new ArrayList<Object>();
		for (int i = 0; i < newAppointment.getParticipants().size(); i++) {
			if (!oldAppointment.getParticipants().contains(newAppointment.getParticipants().get(i))) {
				objects.add(newAppointment.getParticipants().get(i));
			}
		}
		writer.println("insert" + ConvertXML.ObjectsToXml(objects));
	}
	
	public void deleteAppointment(Appointment appointment) {
		ArrayList<Object> objects = new ArrayList<Object>();
		for (int i = 0; i < appointment.getParticipants().size(); i++) {
			Participant participant = appointment.getParticipants().get(i);
			objects.add(new Participant(participant.getAppointment(), participant.getUser(), participant.getAlarm().getTimeInMillis(), "Canceled"));
		}
		writer.println("update" + ConvertXML.ObjectsToXml(objects));
		for (int i = 0; i < objects.size(); i++) {
			((Participant) objects.get(i)).remove();
		}

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
	

	public void updateParticipant(Participant participant) {
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.add(participant);
		writer.println("update" + ConvertXML.ObjectsToXml(objects));

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
