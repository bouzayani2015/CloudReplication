package aec.replication;

import java.util.Properties;

public class PropertiesParser {

	private final Properties properties;
	private String internal_host;
	private String external_host;
	private static final String PROPERTIES_FILE = "ip_assignment.properties";

	public PropertiesParser(Properties properties) {
		this.properties = properties;
	}

	public void parseAndInitProperties() {
		internal_host = properties.getProperty("private_ip_nodeA", "127.0.0.1").trim();
		external_host = properties.getProperty("public_ip_nodeA", "127.0.0.1").trim();
		
	}

	public String getInternal_host() {
		return internal_host;
	}

	public void setInternal_host(String internal_host) {
		this.internal_host = internal_host;
	}

	public String getExternal_host() {
		return external_host;
	}

	public void setExternal_host(String external_host) {
		this.external_host = external_host;
	}
}
