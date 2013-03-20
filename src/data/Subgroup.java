package data;


public class Subgroup {
	
	private Group parentGroup;
	private Group subGroup;
	
	/* 
	 * Constructor oppretter selve relasjonen mellom sub og parent, samt sørger for at
	 * relasjonen er lagt til i sub/parents egne lister.
	 * 
	 * Metode for å slette relasjon som også fjerner den fra listene til sub og parent.
	 * 
	 */

	public Subgroup(Group parentGroup, Group subGroup){
		this.parentGroup = parentGroup;
		this.subGroup = subGroup;
		
		// legge relasjonen i liste over subgrupper og parentgrupper
		// add- og sub- metodene i Group bør sørge for at dette ikke skjer gjentagende
		if(subGroup != null){
			subGroup.addParentGroup(this);
		}
		if(parentGroup != null){
			parentGroup.addSubGroup(this);
		}
		
	}
	
	public void remove(){
		if(parentGroup != null){
			parentGroup.removeSubGroup(this);
		}
		if(subGroup != null){
			subGroup.removeParentGroup(this);
		}
		
	}
	
	
	public Group getParentGroup() {
		return parentGroup;
	}
	
	public Group getSubgroup() {
		return subGroup;
	}
}
