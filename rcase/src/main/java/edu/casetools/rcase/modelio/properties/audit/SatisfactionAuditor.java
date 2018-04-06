package edu.casetools.rcase.modelio.properties.audit;


public class SatisfactionAuditor {

	public enum SATISFACTION {SATISFIED, PARTIALLY_SATISFIED, UNKNOWN, PARTIALLY_DISSATISFIED, DISSATISFIED, NOT_EVALUATED};
	
	private int satisfiedCount;
	private int partiallySatisfiedCount;
	private int unknownCount;
	private int partiallyDissatisfiedCount;
	private int dissatisfiedCount;
	
	
	public SatisfactionAuditor(){
		satisfiedCount 				= 0;
		partiallySatisfiedCount 	= 0;
		unknownCount  				= 0;
		partiallyDissatisfiedCount 	= 0;
		dissatisfiedCount 			= 0;
	}
	
	public int getTotalObjectiveNo() {
		return satisfiedCount + partiallySatisfiedCount + 
				unknownCount + unknownCount + partiallyDissatisfiedCount
				+ dissatisfiedCount;
	}

	public int getSatisfiedCount() {
		return satisfiedCount;
	}

	public void addSatisfied() {
		this.satisfiedCount++;
	}

	public int getPartiallySatisfiedCount() {
		return partiallySatisfiedCount;
	}

	public void addPartiallySatisfied() {
		this.partiallySatisfiedCount++;
	}

	public int getUnknownCount() {
		return unknownCount;
	}

	public void addUnknown() {
		this.unknownCount++;
	}

	public int getPartiallyDissatisfiedCount() {
		return partiallyDissatisfiedCount;
	}

	public void addPartiallyDissatisfied() {
		this.partiallyDissatisfiedCount++;
	}

	public int isDissatisfied() {
		return dissatisfiedCount;
	}

	public void addDissatisfied() {
		this.dissatisfiedCount++;
	}
	
	public void addResult(SATISFACTION result){
		switch(result){
			case DISSATISFIED:
				this.addDissatisfied();
				break;
			case PARTIALLY_DISSATISFIED:
				this.addPartiallyDissatisfied();
				break;
			case PARTIALLY_SATISFIED:
				this.addPartiallySatisfied();
				break;
			case SATISFIED:
				this.addSatisfied();
				break;
			case UNKNOWN:
				this.addUnknown();
				break;
			default:
				break;
		
		}
		
	}

	public SATISFACTION auditAND(){
		if(dissatisfiedCount > 0)
			return SATISFACTION.DISSATISFIED;
		else if(partiallySatisfiedCount > 0 || unknownCount > 0 || partiallyDissatisfiedCount > 0)
			return SATISFACTION.UNKNOWN;
		else if(satisfiedCount == this.getTotalObjectiveNo())
			return SATISFACTION.SATISFIED;
		return SATISFACTION.NOT_EVALUATED;
	}
	
	public SATISFACTION auditOR(){
		if(satisfiedCount > 0)
			return SATISFACTION.SATISFIED;
		else if(partiallySatisfiedCount > 0 || unknownCount > 0 || partiallyDissatisfiedCount > 0)
			return SATISFACTION.UNKNOWN;
		else if(dissatisfiedCount == this.getTotalObjectiveNo())
			return SATISFACTION.DISSATISFIED;
		return SATISFACTION.NOT_EVALUATED;
	}
	
	public SATISFACTION auditXOR(){
		if(satisfiedCount == 1 && (dissatisfiedCount == (this.getTotalObjectiveNo()-1)))
			return SATISFACTION.SATISFIED;
		else if( (partiallySatisfiedCount == 1 || unknownCount == 1 || partiallyDissatisfiedCount == 1) 
				&& (dissatisfiedCount == (this.getTotalObjectiveNo()-1)) )
			return SATISFACTION.UNKNOWN;
		else 
			return SATISFACTION.DISSATISFIED;
	}
	
	public SATISFACTION audit() {
		if(satisfiedCount > 0 && dissatisfiedCount == 0 && partiallyDissatisfiedCount == 0 && unknownCount == 0)
			return SATISFACTION.SATISFIED;
		else if (dissatisfiedCount > 0 && satisfiedCount == 0 && partiallySatisfiedCount == 0 && unknownCount == 0)
			return SATISFACTION.DISSATISFIED;
		else if (unknownCount > 0)
			return SATISFACTION.UNKNOWN;
		else if ((satisfiedCount > 0 || partiallySatisfiedCount > 0) && (dissatisfiedCount > 0 || partiallyDissatisfiedCount > 0))
			return SATISFACTION.UNKNOWN;
		else if (partiallySatisfiedCount > 0 && dissatisfiedCount == 0 && partiallyDissatisfiedCount == 0 && unknownCount == 0)
			return SATISFACTION.PARTIALLY_SATISFIED;
		else if (partiallyDissatisfiedCount > 0 && satisfiedCount > 0 && partiallySatisfiedCount > 0 && unknownCount > 0){
			return SATISFACTION.PARTIALLY_DISSATISFIED;
		} else if (satisfiedCount == 0 && partiallySatisfiedCount == 0 && unknownCount == 0 
						&& partiallyDissatisfiedCount == 0 && dissatisfiedCount == 0) 
			return SATISFACTION.DISSATISFIED;
		else return SATISFACTION.NOT_EVALUATED;
	}
	
}
