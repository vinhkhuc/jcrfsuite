package native_loader;

import java.util.HashSet;
import java.util.Set;

/**
 * Adapted from SnappyNativeLoader.java (Snappy Java 1.0.4.1)
 */
public class CrfSuiteNativeLoader {

	private static Set<String> loadedLibFiles = new HashSet<String>();
	private static Set<String> loadedLibNames = new HashSet<String>();
	
	public static synchronized void loadLibByFile(String libFile) {
		if (!loadedLibFiles.contains(libFile)) {
			try {
				System.load(libFile);
				loadedLibFiles.add(libFile);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static synchronized void loadLibrary(String libName) {
		if (!loadedLibNames.contains(libName)) {
			try {
				System.loadLibrary(libName);
				loadedLibNames.add(libName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
