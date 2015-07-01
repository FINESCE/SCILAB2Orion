# SCILAB2Orion
A Java TIMER client which, every fifteen minutes, reads:
(from powerLosses_VoltageDrops.csv) - PowerLosses/VoltageDrops data for each line of Terni trial grid,
(from predictions.cvs) - Load Prediction data for each meter in the trial grid, and then writes/updates as many ORION Context Broker GE entities as the rows in each .csv file.
