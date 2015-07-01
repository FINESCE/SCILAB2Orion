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
package eu.finesce.emarketplace.core;

import java.util.Timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class Scilab2OrionTimer {

	private static final Log LOGGER = LogFactory.getLog(Scilab2OrionTimer.class);
	
	public static void main(String[] args) throws InterruptedException {
		String currentTime = null;
		//
		Scilab2Orion rtlab2Orion = new Scilab2Orion("scilab2orion.properties", currentTime);
		//
		Timer timer = new Timer();
		//to schedule every 15mins
		//		
		timer.schedule(rtlab2Orion, 0, 900000); //call every 15 min
	}

	private Scilab2OrionTimer() {
		super();
	}
}
