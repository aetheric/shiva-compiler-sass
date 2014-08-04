package nz.co.aetheric.shiva.compiler.sass;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Maintains all the static functions useful for this package.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@Slf4j
public final class Functions {

	public static File getFile(String fileName) {
		URL filePath = Thread.currentThread().getContextClassLoader().getResource(fileName);
		if (filePath == null) {
			log.warn("File {} not found on classpath.", fileName);
			return null;
		}

		return new File(filePath.getPath());
	}

	public static File createTempFile(String suffix) throws IOException {
		return File.createTempFile(String.valueOf(System.currentTimeMillis()), suffix);
	}

	/** This class is non-instantiable. */
	private Functions() {
	}

}
