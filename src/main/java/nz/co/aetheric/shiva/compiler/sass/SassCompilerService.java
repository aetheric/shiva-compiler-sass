package nz.co.aetheric.shiva.compiler.sass;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jruby.embed.ScriptingContainer;

import java.io.*;

/**
 * {@inheritDoc}.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@Slf4j
public class SassCompilerService implements SassCompiler {

	private final ScriptingContainer scripting;

	@Getter
	private File scriptConfig;

	@Getter
	private boolean initialised;

	@Getter
	@Setter
	private File scriptInit;

	@Getter
	@Setter
	private File scriptCompile;

	public SassCompilerService() {
		this.scripting = new ScriptingContainer();
		this.setScriptConfig(Functions.getFile("sass/config.rb"));
		this.setScriptInit(Functions.getFile("sass/init.rb"));
		this.setScriptCompile(Functions.getFile("sass/compile.rb"));
		initialised = false;
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

		if (!initialised) {
			final Object initResult = Functions.runScript(scripting, scriptInit);
			if (initResult == null) {
				log.warn("Something went wrong during compiler initialisation.");
				return;
			}

			initialised = true;
		}

		final Object compileResult = Functions.runScript(scripting, scriptCompile);
		if (compileResult == null) {
			log.warn("Something went wrong during compiler operation.");
			return;
		}

		log.debug("Compilation of {} to {} succeeded.", input, output);
	}

	public void setScriptConfig(File scriptConfig) {
		this.scriptConfig = scriptConfig;
		scripting.put("configLocation", scriptConfig.getPath());
	}

}
