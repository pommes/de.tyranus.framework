package de.tyranus.framework.data;

import java.lang.reflect.Method;

/**
 * Special {@link TAbstractData} implementation that uses keys generated on the
 * getter and setter names.
 * 
 * @see {@link TAbstractDataKey#key()}.
 * 
 * @author tim
 * 
 */
public abstract class TAbstractDataKey extends TAbstractData {

	/**
	 * Returns a key depending on the method it was called in. The key is the
	 * uppercase method name
	 * without its first three characters. This ensures that a getter and a
	 * setter are using the
	 * same key.<br/>
	 * <br/>
	 * For example the getter <code>getIndex()</code> and the setter
	 * <code>setIndex(int idx)</code> will generate the same key
	 * <code>INDEX</code>.
	 * 
	 * @return the generated key.
	 */
	protected String key() {
		final int UPPER_CALLER_DEPTH = 1;
		// Remove get/set from the method name and upper case the result.
		return getMethodName(UPPER_CALLER_DEPTH).substring(3).toUpperCase();
	}

	/****** STATIC *******/

	// save it static to have it available on every call
	private static Method stackTraceMethod;

	static {
		try {
			stackTraceMethod = Throwable.class.getDeclaredMethod("getStackTraceElement", int.class);
			stackTraceMethod.setAccessible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getMethodName(final int depth) {
		try {
			StackTraceElement element = (StackTraceElement) stackTraceMethod.invoke(new Throwable(), depth + 1);
			return element.getMethodName();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
