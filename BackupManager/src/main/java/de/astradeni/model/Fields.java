package de.astradeni.model;

public class Fields {
	
	public String field;
	
	public Fields() {
		
	}

	public String getField() {
		return field;
	}
	
	public void setField (String field) {
		this.field = field;
	}
	
	@Override
	public String toString() {
		return "Fields [field =" + field + "]";
	}
}
