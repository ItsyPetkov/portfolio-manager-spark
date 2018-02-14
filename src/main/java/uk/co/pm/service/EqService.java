package uk.co.pm.service;

import uk.co.pm.model.Equity;
import uk.co.pm.model.EquityModel;

public class EqService {

	
	public static EquityModel getEq(final Equity eq){
		if(eq == null){
			throw new IllegalArgumentException("Equity can't be null");
		}
		else if(eq.getCompanyName() == null || "".equals(eq.getCompanyName())){
			throw new IllegalArgumentException("Equity Must have a NAME" );
		}
		
		return new EquityModel(eq.getCompanyName());
	}
}
