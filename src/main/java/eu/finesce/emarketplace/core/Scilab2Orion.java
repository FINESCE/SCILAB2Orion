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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import eu.finesce.emarketplace.context.PowerVoltageContextElement;
import eu.finesce.emarketplace.context.PredictionsContextElement;
import eu.finesce.emarketplace.domain.PowerVoltageData;
import eu.finesce.emarketplace.domain.Powerlosses;
import eu.finesce.emarketplace.domain.Predictions;
import eu.finesce.emarketplace.domain.WeatherForecast;
import eu.fiware.ngsi.official.ContextElement;
import eu.fiware.ngsi.official.ContextElementList;
import eu.fiware.ngsi.official.UpdateActionType;
import eu.fiware.ngsi.official.UpdateContextRequest;

/**
 * @author LL .ENG
 * 
 */
public class Scilab2Orion extends TimerTask {
	private static final Log LOGGER = LogFactory.getLog(Scilab2Orion.class);
	Properties prop;

	private String REGISTER_CONTEXT_PATH;
	private String ORION_SERVER_URL;
	WeatherForecast weather = null;
	private String mappingFileName;
	private String currentTimeCostructor;
	private static String fileInputPowerVoltage;
	private static String fileInputPredictions;

	public Scilab2Orion(String mappingFileName, String currentTime) {
		this.mappingFileName = mappingFileName;
		this.currentTimeCostructor = currentTime;

	}

	@Override
	public void run() {
		exec(mappingFileName, currentTimeCostructor);
	}

	public void exec(String mappingFileName, String currentTime) {

		getPropertiesData(mappingFileName);
		
		List<PowerVoltageData> listPowerVoltageData = loadDataForPowerVoltage();
		
		List<Predictions> listPredictions = loadDataForPredictions();

		try {
			for (PowerVoltageData powerVoltage : listPowerVoltageData) {
				Response sendResponse = this
						.sendPowerVoltageCtxEvDataToOrion(powerVoltage);
				LOGGER.info(sendResponse.readEntity(String.class));
			}
		} catch (Exception e) {
			LOGGER.error("Oopssss!!!!!!!No Response by Orion! May be a connection problem! I try to send power and voltage data next time!!!",e);
		}

		try {
			for (Predictions predictions : listPredictions) {
				Response sendResponse = this
						.sendPredictionsCtxEvDataToOrion(predictions);
				LOGGER.info(sendResponse.readEntity(String.class));
			}
		} catch (Exception e) {
			LOGGER.error("Oopssss!!!!!!!No Response by Orion! May be a connection problem! I try to send predictions data next time!!!",e);
		}
	}

	/**
	 * Method to retrieve properties data from NAME.properties
	 * 
	 * @param mappingFileName
	 * @return
	 */
	public final String getPropertiesData(String mappingFileName) {
		prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader()
					.getResourceAsStream(mappingFileName));
			fileInputPowerVoltage = prop.getProperty("scilab2orion.fileInputPowerVoltage");
			fileInputPredictions = prop.getProperty("scilab2orion.fileInputPredictions");
			REGISTER_CONTEXT_PATH = prop.getProperty("scilab2orion.registerContexPath");
			ORION_SERVER_URL = prop.getProperty("scilab2orion.orionServerUrl");

		} catch (IOException e) {
			LOGGER.error("Error during get properties data by :"
					+ mappingFileName, e);
		}
		return null;
	}

	private static List<PowerVoltageData> loadDataForPowerVoltage() {
		List<PowerVoltageData> list =  new ArrayList<PowerVoltageData>();;
		try {
			InputStream is = new FileInputStream(fileInputPowerVoltage);
			//DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			//sdf.setLenient(false);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));

			String line = reader.readLine();// header
			//line = reader.readLine();
			//list = new ArrayList<PowerVoltageData>();
			while (line != null) {
				Iterator<String> iterator = Arrays.asList(line.split(";"))
						.iterator();

				PowerVoltageData powerVoltage = new PowerVoltageData();
				String dataStr = iterator.next();
				powerVoltage.setDateLine(Long.valueOf(dataStr));
				powerVoltage.setEntityId(iterator.next());
				powerVoltage.setCurrentTime(new Date().getTime() / 1000);				
				powerVoltage.setCurrentVoltageDrops(parseDouble(iterator.next()));
				powerVoltage.setCurrentPowerLosses(parseDouble(iterator.next()));
				
				powerVoltage.setAfter1hVoltageDrops(parseDouble(iterator.next()));
				powerVoltage.setAfter1hPowerLosses(parseDouble(iterator.next()));
				
				powerVoltage.setAfter3hVoltageDrops(parseDouble(iterator.next()));
				powerVoltage.setAfter3hPowerLosses(parseDouble(iterator.next()));
				
				powerVoltage.setAfter6hVoltageDrops(parseDouble(iterator.next()));
				powerVoltage.setAfter6hPowerLosses(parseDouble(iterator.next()));

				powerVoltage.setAfter12hVoltageDrops(parseDouble(iterator.next()));
				powerVoltage.setAfter12hPowerLosses(parseDouble(iterator.next()));
				
				powerVoltage.setAfter24hVoltageDrops(parseDouble(iterator.next()));
				powerVoltage.setAfter24hPowerLosses(parseDouble(iterator.next()));
//				try {
//					Date dateLine = sdf.parse(dataStr);
//					powerVoltage.setDateLine(dateLine.getTime() / 1000);
//				} catch (ParseException e) {
//					powerVoltage.setDateLine(0);
//					LOGGER.error("Error parsing date line by input file, the date is set to zero value");
//				}

				list.add(powerVoltage);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			LOGGER.error("Error IO in loadDataForPowerVoltage ", e);
		} catch (Exception e) {
			LOGGER.error("Error in loadDataForPowerVoltage ", e);
		}
		return list;
	}

	private static List<Predictions> loadDataForPredictions() {

		List<Predictions> list =  new ArrayList<Predictions>();;

		try {
			InputStream is = new FileInputStream(fileInputPredictions);
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			sdf.setLenient(false);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));

			String line = reader.readLine();// header
			line = reader.readLine();
			//list = new ArrayList<Predictions>();
			while (line != null) {
				Iterator<String> iterator = Arrays.asList(line.split(";"))
						.iterator();

				Predictions predictions = new Predictions();
								
				//String dataStr = iterator.next();

				predictions.setLoadPredictionsTime(parseLong(iterator.next()));
				
//				try {
//					Date loadTimePrediction = sdf.parse(dataStr);
//					predictions.setLoadPredictionsTime(loadTimePrediction
//							.getTime() / 1000);
//				} catch (ParseException e) {
//					predictions.setLoadPredictionsTime(0);
//					LOGGER.error("Error parsing loadTimePrediction by input file, the date is set to zero value");
//				}
//
				predictions.setMeterId(iterator.next());
				predictions.setIdUser(iterator.next());
				
				predictions.setCurrentTime(new Date().getTime() / 1000);
				
				predictions.setAfter1hUpstreamActivePower(parseDouble(iterator.next()));
				predictions.setAfter1hUpstreamReactivePower(parseDouble(iterator.next()));
				predictions.setAfter1hDownstreamActivePower(parseDouble(iterator.next()));
				predictions.setAfter1hDownstreamReactivePower(parseDouble(iterator.next()));
				
				predictions.setAfter3hUpstreamActivePower(parseDouble(iterator.next()));
				predictions.setAfter3hUpstreamReactivePower(parseDouble(iterator.next()));
				predictions.setAfter3hDownstreamActivePower(parseDouble(iterator.next()));
				predictions.setAfter3hDownstreamReactivePower(parseDouble(iterator.next()));

				predictions.setAfter6hUpstreamActivePower(parseDouble(iterator.next()));
				predictions.setAfter6hUpstreamReactivePower(parseDouble(iterator.next()));
				predictions.setAfter6hDownstreamActivePower(parseDouble(iterator.next()));
				predictions.setAfter6hDownstreamReactivePower(parseDouble(iterator.next()));
				
				predictions.setAfter12hUpstreamActivePower(parseDouble(iterator.next()));
				predictions.setAfter12hUpstreamReactivePower(parseDouble(iterator.next()));
				predictions.setAfter12hDownstreamActivePower(parseDouble(iterator.next()));
				predictions.setAfter12hDownstreamReactivePower(parseDouble(iterator.next()));
				
				predictions.setAfter24hUpstreamActivePower(parseDouble(iterator.next()));
				predictions.setAfter24hUpstreamReactivePower(parseDouble(iterator.next()));
				predictions.setAfter24hDownstreamActivePower(parseDouble(iterator.next()));
				predictions.setAfter24hDownstreamReactivePower(parseDouble(iterator.next()));
				
				list.add(predictions);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			LOGGER.error("Error IO in loadDataForPredictions ", e);
		}catch (Exception e) {
			LOGGER.error("Error in loadDataForPredictions ", e);
		}
		return list;
	}

	/**
	 * @param powerLosses
	 *            and voltageDrops
	 * @return
	 */
	public final Response sendPowerVoltageCtxEvDataToOrion(
			PowerVoltageData powerVoltage) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ORION_SERVER_URL);
		WebTarget resourceWebTarget = webTarget.path(REGISTER_CONTEXT_PATH);

		UpdateContextRequest updContextRequest = new UpdateContextRequest();
		updContextRequest.setUpdateAction(UpdateActionType.APPEND);
		ContextElement element = new PowerVoltageContextElement(powerVoltage);
		ContextElementList elementList = new ContextElementList();
		elementList.getContextElements().add(element);
		updContextRequest.setContextElementList(elementList);

		Entity<UpdateContextRequest> sendXml = Entity.xml(updContextRequest);

		Response responseEntity = resourceWebTarget.request(
				MediaType.APPLICATION_XML).post(sendXml);

		return responseEntity;
	}

	/**
	 * @param predictions
	 * @return
	 */
	public final Response sendPredictionsCtxEvDataToOrion(
			Predictions predictions) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ORION_SERVER_URL);
		WebTarget resourceWebTarget = webTarget.path(REGISTER_CONTEXT_PATH);
		UpdateContextRequest updContextRequest = new UpdateContextRequest();
		updContextRequest.setUpdateAction(UpdateActionType.APPEND);
		ContextElement element = new PredictionsContextElement(predictions);
		ContextElementList elementList = new ContextElementList();
		elementList.getContextElements().add(element);
		updContextRequest.setContextElementList(elementList);

		Entity<UpdateContextRequest> sendXml = Entity.xml(updContextRequest);

		Response responseEntity = resourceWebTarget.request(
				MediaType.APPLICATION_XML).post(sendXml);

		return responseEntity;
	}

	private static double parseDouble(String next) {
		if (next == null || "".equals(next)) {
			return 0;
		}
		return Double.parseDouble(next);
	}
	
	private static long parseLong(String next) {
		if (next == null || "".equals(next)) {
			return 0;
		}
		return Long.parseLong(next);
	}
}
