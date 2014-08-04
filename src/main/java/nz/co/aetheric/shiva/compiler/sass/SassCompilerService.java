package nz.co.aetheric.shiva.compiler.sass;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * {@inheritDoc}.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@Slf4j
@Setter
public class SassCompilerService implements SassCompiler {

	protected File scriptConfig;
	protected File scriptInit;
	protected File scriptCompile;

	public SassCompilerService() {
		this.scriptConfig = Functions.getFile("sass/config.rb");
		this.scriptInit = Functions.getFile("sass/init.rb");
		this.scriptCompile = Functions.getFile("sass/compile.rb");
	}

	/** {@inheritDoc} */
	@Override
	public String compile(File input)
			throws IOException {

		log.trace("Create a temp file for the output.");
		File output = Functions.createTempFile(".css");

		log.trace("Compile the sources to the temp file.");
		this.compile(input, output);

		log.trace("Read the temp file out to a string.");
		return FileUtils.readFileToString(output);

	}

	/** {@inheritDoc} */
	@Override
	public void compile(File input, File output) {
		//TODO: Implement this.
	}

}
