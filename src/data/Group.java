package data;

import java.util.ArrayList;


public class Group {
	
	private String groupName;
	
	private User leader;
	
	private ArrayList<Member> members;
	private ArrayList<Subgroup> subGroups;
	private ArrayList<Subgroup> parentGroups;
	
	public Group(String groupName, User leader){
		this.groupName = groupName;
		this.leader = leader;
		
		members = new ArrayList<Member>();
		subGroups = new ArrayList<Subgroup>();
		parentGroups = new ArrayList<Subgroup>();
	}
	
	public void addMember(Member newMember){
		if (!this.members.contains(newMember)){
			this.members.add(newMember);
		}
	}
	
	public void removeMember(Member delMember){
		this.members.remove(delMember);
	}
	
	public ArrayList<Member> getMembers(){
		return this.members;
	}
	
	public void addSubGroup(Subgroup newSubGroup){
		if (!this.subGroups.contains(newSubGroup)){
			this.subGroups.add(newSubGroup);
		}
	}
	
	public void removeSubGroup(Subgroup delSubGroup){
		this.subGroups.remove(delSubGroup);
	}

	public ArrayList<Subgroup> getSubGroups(){
		return this.subGroups;
	}
	
	public void addParentGroup(Subgroup newParentGroup){
		if (!this.parentGroups.contains(newParentGroup)){
			this.parentGroups.add(newParentGroup);
		}
	}
	
	public void removeParentGroup(Subgroup delParentGroup){
		this.parentGroups.remove(delParentGroup);
	}

	public ArrayList<Subgroup> getParentGroups(){
		return this.parentGroups;
	}
	
	//velger forel¿pig Œ ikke kunne endre leder eller gruppenavn etter opprettelse
	public User getLeader(){
		return this.leader;
	}
	public String getGroupName(){
		return this.groupName;
	}

}
