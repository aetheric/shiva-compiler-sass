package nz.co.aetheric.shiva.compiler.sass;

import junitx.framework.FileAssert;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

/**
 * Tests for {@link SassCompilerService} functionality.
 * <p>Author: <a href="http://gplus.to/tzrlk">Peter Cummuskey</a></p>
 */
@Slf4j
public class SassCompilerServiceTest {

	private SassCompilerService service;
	private File testInput;
	private File expectedOutput;
	private TemporaryFolder tempDir;

	private File testOutput;

	public SassCompilerServiceTest() throws IOException {
		service = new SassCompilerService();
		testInput = Functions.getFile("testInput.scss");
		expectedOutput = Functions.getFile("expectedOutput.css");
		tempDir = new TemporaryFolder(Functions.getFile("."));
		tempDir.create();
	}

	@Before
	public void setUp() throws Exception {
		String testOutputName = "testOutput_" + System.currentTimeMillis() + ".css";

		log.info("Creating temp file for output: \"{}\".", testOutputName);
		testOutput = tempDir.newFile(testOutputName);

	}

	@Test
	public void testCompile() throws Exception {

		log.info("Compiling the test files.");
		service.compile(testInput, testOutput);

		log.info("Checking the output is as expected.");
		FileAssert.assertEquals(expectedOutput, testOutput);

	}

}
