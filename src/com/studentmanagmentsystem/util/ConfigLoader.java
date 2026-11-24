package com.studentmanagmentsystem.util;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ConfigLoader {

	private static final Properties properties = new Properties();

	static {
		try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {

			if (input == null) {
				throw new IllegalStateException("Config file not found");
			}

			properties.load(input);

		} catch (IOException e) {
			throw new IllegalStateException("Failed to load config", e);
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}