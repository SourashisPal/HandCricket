package in.sourashis.handcricket;

/**
 * Provides access to the running environment
 */
public class Environment {
	/** Singleton object of the Environment class **/
	private static Environment environment;

	/**
	 * Initializes the environment object
	 */
	private Environment() {
	}

	/**
	 * Constructs and returns the singleton object of the Environment class
	 * @return Singleton instance
	 */
	public static Environment getEnvironment() {
		if (environment == null) {
			environment = new Environment();
		}
		return environment;
	}

	/**
	 * Checks whether the os is Windows or not
	 * @return Whether the os is Windows or not
	 */
	public boolean isWindows() {
		String osName = System.getProperty("os.name");
		return osName != null && osName.toLowerCase().contains("windows");
	}
}
