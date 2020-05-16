package sdt.action;

import sdt.SDTAnalyzerState;

public class BaseAction implements Action {

	protected String description;

	/**
	 * @param description
	 */
	public BaseAction(String description) {
		super();
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "{" + description + "}";
	}

	@Override
	public void execute(SDTAnalyzerState sdtAnalyzerState) {
		// TODO Auto-generated method stub

	}

}
