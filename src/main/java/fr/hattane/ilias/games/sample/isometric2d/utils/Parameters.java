package fr.hattane.ilias.games.sample.isometric2d.utils;

import java.util.HashMap;
import java.util.Map;

public class Parameters<T> {
	
	private Map<T, Object> properties;

	public Parameters() {
		properties = new HashMap<>();
	}
	
	public void addProperty(T key, Object value) {
		
		if (!properties.containsKey(key))
			properties.put(key, value);
		else
			properties.replace(key, value);
		
	}
	
	public Object getProperty(T key) {
		
		if (properties.containsKey(key))
			return properties.get(key);
		return null;
		
	}
	
	public Map<T, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<T, Object> properties) {
		this.properties = properties;
	}
	
}
