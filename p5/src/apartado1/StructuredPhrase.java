package apartado1;

import java.util.*;

/**
 * Implements the training phrases for a chatbot.
 * 
 * @author Miguel Lozano
 * @author Sergio Larriba
 */
public class StructuredPhrase {
	private String phrase;
	private String naturalLanguage;
	private Map<String, Object> parameters;

	public StructuredPhrase() {
		this.parameters = new HashMap<>();
	}

	/**
	 * Add a new phrase without parameters.
	 * 
	 * @param text a simple phrase
	 * @return the object itself
	 */
	public StructuredPhrase with(String text) {
		this.phrase = (this.phrase == null) ? text + " " : this.phrase + text + " ";
		this.naturalLanguage = (this.naturalLanguage == null) ? text + " " : this.naturalLanguage + text + " ";
		return this;
	}

	/**
	 * Add a new phrase with parameters.
	 * 
	 * @param <T>   parameter's class
	 * @param title parameter's title
	 * @param value parameter's value
	 * @return the object itself
	 */
	public <T> StructuredPhrase with(String title, T value) {
		if (title == null || value == null)
			return this;
		this.parameters.put(title, value);

		this.phrase += "[" + title + ":" + value.getClass().getSimpleName() + "(" + value.toString() + ")]" + " ";
		this.naturalLanguage += value.toString() + " ";
		return this;
	}

	/**
	 * Add the default value of a parameter.
	 * 
	 * @param <T>   parameter's class
	 * @param title parameter's title
	 * @param value parameter's value
	 * @return the object itself
	 */
	public <T> StructuredPhrase setting(String title, T value) {
		if (title == null || value == null)
			return this;
		this.parameters.put(title, value);
		return this;
	}

	/**
	 * Returns the value of a parameter.
	 * 
	 * @param key parameter's title
	 * @return parameter's value
	 */
	public Object getValue(String key) {
		Parameter<Object> param = new Parameter<Object>(this.parameters.get(key));
		return (key == null) ? null : param.getValue();
	}

	@Override
	public String toString() {
		return this.phrase.replaceAll("\\s+$", "");
	}

	public String getPhrase() {
		return this.phrase;
	}

	public Map<String, Object> getParameters() {
		return this.parameters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phrase == null) ? 0 : phrase.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StructuredPhrase other = (StructuredPhrase) obj;
		if (phrase == null) {
			if (other.phrase != null)
				return false;
		} else if (!phrase.equals(other.phrase))
			return false;
		return true;
	}

	public String getNaturalLanguage() {
		return this.naturalLanguage.replaceAll("\\s+$", "");
	}

}
