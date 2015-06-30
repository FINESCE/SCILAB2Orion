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
import eu.fiware.ngsi.official.ContextAttribute;
import eu.fiware.ngsi.official.ContextAttributeList;
import eu.fiware.ngsi.official.ContextElement;
import eu.fiware.ngsi.official.EntityId;

public class PowerVoltageContextElement extends ContextElement {

	private static final String ENTITY_TIPE = "PowLosAndVoltDrops";
	private static final String POWLOS_AND_VOLTDROPOS_DATE = "PowLosAndVoltDropsDate";
	private static final String CURRENT_TIME = "currentTime";
	private static final String CURRENT_POWER_LOSSES = "currentPowerLosses";
	private static final String CURRENT_VOLTAGE_DROPS = "currentVoltageDrops";
	private static final String AFTER1H_POWER_LOSSES = "after1hPowerLosses";
	private static final String AFTER1H_VOLTAGE_DROPS = "after1hVoltageDrops";
	private static final String AFTER3H_POWER_LOSSES = "after3hPowerLosses";
	private static final String AFTER3H_VOLTAGE_DROPS = "after3hVoltageDrops";
	private static final String AFTER6H_POWER_LOSSES = "after6hPowerLosses";
	private static final String AFTER6H_VOLTAGE_DROPS = "after6hVoltageDrops";
	private static final String AFTER12H_POWER_LOSSES = "after12hPowerLosses";
	private static final String AFTER12H_VOLTAGE_DROPS = "after12hVoltageDrops";
	private static final String AFTER24H_POWER_LOSSES = "after24hPowerLosses";
	private static final String AFTER24H_VOLTAGE_DROPS = "after24hVoltageDrops";
	

	public PowerVoltageContextElement(PowerVoltageData powerVoltage) {
		this.contextAttributeList = new ContextAttributeList();

		EntityId id = new EntityId();
		id.setId(powerVoltage.getEntityId());
		id.setType(ENTITY_TIPE);
		id.setIsPattern(false);
		this.setEntityId(id);
		ContextAttribute attribute = new ContextAttribute();

		// attributes
		attribute = new ContextAttribute();
		attribute.setName(CURRENT_TIME);
		attribute.setType("sec");
		attribute.setContextValue(powerVoltage.getCurrentTime());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(CURRENT_POWER_LOSSES);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getCurrentPowerLosses());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(CURRENT_VOLTAGE_DROPS);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getCurrentVoltageDrops());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(POWLOS_AND_VOLTDROPOS_DATE);
		attribute.setType("date");
		attribute.setContextValue(powerVoltage.getDateLine());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(AFTER1H_POWER_LOSSES);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getAfter1hPowerLosses());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER1H_VOLTAGE_DROPS);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getAfter1hVoltageDrops());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(AFTER3H_POWER_LOSSES);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getAfter3hPowerLosses());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER3H_VOLTAGE_DROPS);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getAfter3hVoltageDrops());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(AFTER6H_POWER_LOSSES);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getAfter6hPowerLosses());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER6H_VOLTAGE_DROPS);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getAfter6hVoltageDrops());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(AFTER12H_POWER_LOSSES);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getAfter12hPowerLosses());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER12H_VOLTAGE_DROPS);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getAfter12hVoltageDrops());
		this.getContextAttributeList().getContextAttributes().add(attribute);
		
		attribute = new ContextAttribute();
		attribute.setName(AFTER24H_POWER_LOSSES);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getAfter24hPowerLosses());
		this.getContextAttributeList().getContextAttributes().add(attribute);

		attribute = new ContextAttribute();
		attribute.setName(AFTER24H_VOLTAGE_DROPS);
		attribute.setType("double");
		attribute.setContextValue(powerVoltage.getAfter24hVoltageDrops());
		this.getContextAttributeList().getContextAttributes().add(attribute);
	}

}
