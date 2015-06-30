/*
Copyright (C) 2014 FINESCE-WP4 
 
This file is part of Social2Orion.

Social2Orion is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Social2Orion is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Nome-Programma.  If not, see <http://www.gnu.org/licenses/>. */
package eu.finesce.emarketplace.context;

import eu.finesce.emarketplace.domain.PowerVoltageData;
import eu.finesce.emarketplace.domain.Predictions;
import eu.fiware.ngsi.official.ContextAttribute;
import eu.fiware.ngsi.official.ContextAttributeList;
import eu.fiware.ngsi.official.ContextElement;
import eu.fiware.ngsi.official.EntityId;

public class PredictionsContextElement extends ContextElement {

	private static final String ENTITY_TIPE = "LoadPrediction";
	private static final String CURRENT_TIME = "currentTime";
	private static final String LOADPREDICTIONTIME = "loadPredictionTime";
	
	private static final String AFTER1HDOWNSTREAMACTIVEPOWER = "after1hDownstreamActivePower";	
	private static final String AFTER1HUPSTREAMACTIVEPOWER = "after1hUpstreamActivePower";
	private static final String AFTER1HDOWNSTREAMREACTIVEPOWER = "after1hDownstreamReactivePower";	
	private static final String AFTER1HUPSTREAMREACTIVEPOWER = "after1hUpstreamReactivePower";
	
	private static final String AFTER3HDOWNSTREAMACTIVEPOWER = "after3hDownstreamActivePower";	
	private static final String AFTER3HUPSTREAMACTIVEPOWER = "after3hUpstreamActivePower";
	private static final String AFTER3HDOWNSTREAMREACTIVEPOWER = "after3hDownstreamReactivePower";	
	private static final String AFTER3HUPSTREAMREACTIVEPOWER = "after3hUpstreamReactivePower";
	
	private static final String AFTER6HDOWNSTREAMACTIVEPOWER = "after6hDownstreamActivePower";	
	private static final String AFTER6HUPSTREAMACTIVEPOWER = "after6hUpstreamActivePower";
	private static final String AFTER6HDOWNSTREAMREACTIVEPOWER = "after6hDownstreamReactivePower";	
	private static final String AFTER6HUPSTREAMREACTIVEPOWER = "after6hUpstreamReactivePower";
	
	private static final String AFTER12HDOWNSTREAMACTIVEPOWER = "after12hDownstreamActivePower";	
	private static final String AFTER12HUPSTREAMACTIVEPOWER = "after12hUpstreamActivePower";
	private static final String AFTER12HDOWNSTREAMREACTIVEPOWER = "after12hDownstreamReactivePower";	
	private static final String AFTER12HUPSTREAMREACTIVEPOWER = "after12hUpstreamReactivePower";
	
	private static final String AFTER24HDOWNSTREAMACTIVEPOWER = "after24hDownstreamActivePower";	
	private static final String AFTER24HUPSTREAMACTIVEPOWER = "after24hUpstreamActivePower";
	private static final String AFTER24HDOWNSTREAMREACTIVEPOWER = "after24hDownstreamReactivePower";	
	private static final String AFTER24HUPSTREAMREACTIVEPOWER = "after24hUpstreamReactivePower";
	

	public PredictionsContextElement(Predictions predictions) {
		this.contextAttributeList = new ContextAttributeList();

		EntityId id = new EntityId();
		id.setId(predictions.getMeterId());
		id.setType(ENTITY_TIPE);
		id.setIsPattern(false);
		this.setEntityId(id);
		ContextAttribute attribute = new ContextAttribute();

		// attributes
		attribute = new ContextAttribute();
		attribute.setName(CURRENT_TIME);
		attribute.setType("sec");
		attribute.setContextValue(predictions.getCurrentTime());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(LOADPREDICTIONTIME);
		attribute.setType("sec");
		attribute.setContextValue(predictions.getLoadPredictionsTime());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER1HDOWNSTREAMACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter1hDownstreamActivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER1HUPSTREAMACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter1hUpstreamActivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		

		attribute = new ContextAttribute();
		attribute.setName(AFTER3HDOWNSTREAMACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter3hDownstreamActivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER3HUPSTREAMACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter3hUpstreamActivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER6HDOWNSTREAMACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter6hDownstreamActivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER6HUPSTREAMACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter6hUpstreamActivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER12HDOWNSTREAMACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter12hDownstreamActivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER12HUPSTREAMACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter12hUpstreamActivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		

		attribute = new ContextAttribute();
		attribute.setName(AFTER24HDOWNSTREAMACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter24hDownstreamActivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER24HUPSTREAMACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter24hUpstreamActivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		
		attribute = new ContextAttribute();
		attribute.setName(AFTER1HDOWNSTREAMREACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter1hDownstreamReactivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER1HUPSTREAMREACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter1hUpstreamReactivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER3HDOWNSTREAMREACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter3hDownstreamReactivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER3HUPSTREAMREACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter3hUpstreamReactivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(AFTER6HDOWNSTREAMREACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter6hDownstreamReactivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER6HUPSTREAMREACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter6hUpstreamReactivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(AFTER12HDOWNSTREAMREACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter12hDownstreamReactivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER12HUPSTREAMREACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter12hUpstreamReactivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(AFTER24HDOWNSTREAMREACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter24hDownstreamReactivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER24HUPSTREAMREACTIVEPOWER);
		attribute.setType("double");
		attribute.setContextValue(predictions.getAfter24hUpstreamReactivePower());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
	}

}
