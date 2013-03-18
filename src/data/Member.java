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
		
		
		//add'ers bør sørge for at dette ikke skjer om og om igjen
		memberGroup.addMember(this);
		memberUser.addGroup(this);
	}
	
	public void removeMemberRelation(){
		group.removeMember(this);
		user.removeGroup(this);
	}

}
