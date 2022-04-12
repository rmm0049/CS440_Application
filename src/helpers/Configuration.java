package helpers;

/**
 * This server configuration is accurate
 * for the Oracle Database 19c instance
 * for CS 440 during Spring 2022 semester.
 */
public class Configuration {
	public static final String
		HOST = "lrp-csdb000.systems.wvu.edu",
		PORT = "2201",
		DATABASE = "cs440",
		JDBC_URL = String.format(
			"jdbc:oracle:thin:@//%s:%s/%s",
			HOST,
			PORT,
			DATABASE
		);
}
