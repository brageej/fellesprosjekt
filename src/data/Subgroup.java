package data;


public class Subgroup {
	
	private Group parentGroup;
	private Group subGroup;

	public Subgroup(Group parentGroup, Group subGroup){
		this.parentGroup = parentGroup;
		this.subGroup = subGroup;
		
		// legge relasjonen i liste over subgrupper og parentgrupper, men skal de da castes om?
		//subGroup.addParentGroup(parentGroup);
		//
	}
}
