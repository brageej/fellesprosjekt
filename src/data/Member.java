package data;


public class Member {
	
	private Group group;
	private User user;
	
	/* 
	 * Constructor oppretter selve relasjonen mellom group og user, samt sørger for at
	 * relasjonen er lagt til i group og user's egne lister.
	 * 
	 * Metode for å slette relasjon som også fjerner den fra listene til user og group.
	 * 
	 */
	
	public Member(Group memberGroup, User memberUser){
		this.group = memberGroup;
		this.user = memberUser;
		
		if (memberGroup != null) {
			memberGroup.addMember(this);
		}
		if (memberUser != null) {
			memberUser.addGroup(this);
		}
	}
	
	public void remove(){
		if (group != null) {
			group.removeMember(this);
		}
		if (user != null) {
			user.removeGroup(this);
		}
	}

	public Group getGroup() {
		return group;
	}
	
	public User getUser() {
		return user;
	}

}
