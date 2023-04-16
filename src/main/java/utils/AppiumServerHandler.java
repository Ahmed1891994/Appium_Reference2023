package utils;

import java.io.IOException;
import java.net.ServerSocket;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumServerHandler {
	private static AppiumDriverLocalService server;

	public void startserver(int port) {
		server = AppiumDriverLocalService.buildDefaultService();
		try 
		{
			FreePort(port);
			server.start();
			// server.clearOutPutStreams(); // -> Comment this if you want to see server
			// logs in the console
			MyLogger.info("Appium server started");
		}
		catch(Exception e)
		{
			MyLogger.error("Appium server Failed to start");
		}
	}
	
	public void stopserver() {
			server.stop();
			MyLogger.info("Appium server stopped");
	}

	public void FreePort(int port) throws Exception {
		try {
			// Check if the port is already in use
			ServerSocket serverSocket = new ServerSocket(port);
			serverSocket.close();
			MyLogger.info("Port " + port + " is available.");
		} catch (IOException e) {
			// If the port is already in use, kill the process using the port
			ProcessBuilder processBuilder = new ProcessBuilder();
			processBuilder.command("cmd.exe", "/c", "netstat -ano | findstr :" + port);
			try {
				Process process = processBuilder.start();
				String output = new String(process.getInputStream().readAllBytes());
				String[] lines = output.split("\\r?\\n");

				if (lines.length > 0) {
					String[] tokens = lines[0].trim().split("\\s+");
					int pid = Integer.parseInt(tokens[tokens.length - 1]);
					Runtime.getRuntime().exec("taskkill /F /PID " + pid);
					MyLogger.info("Killed process with PID " + pid + ".");
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			MyLogger.info("Port " + port + " was busy and has been released.");
		}
	}
}
