package nz.co.aetheric.shiva.compiler.sass;

import java.io.*;

/**
 * This is the API for SASS compilation.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
public interface SassCompiler {

	/**
	 * TODO: Document this.
	 * @param input The file to compile.
	 * @return The compilation results as a String.
	 */
	public String compile(File input)
			throws IOException;

	/**
	 * TODO: Document this.
	 * @param input The file to compile.
	 * @param output The file to compile the output to.
	 */
	public void compile(File input, File output);

}
