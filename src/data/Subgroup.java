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

	// legge relasjonen i liste over subgrupper og parentgrupper
	// add- og sub- metodene i Group bør sørge for at dette ikke skjer gjentagende
	subGroup.addParentGroup(this);
	parentGroup.addSubGroup(this);
		
	}
	
	public void removeSubgroupRelation(){
		parentGroup.removeSubGroup(this);
		subGroup.removeParentGroup(this);
	}
}
