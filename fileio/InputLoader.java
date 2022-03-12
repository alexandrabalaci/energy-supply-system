package fileio;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Class that reads the input from the json files
 *
 * <p>
 */
public final class InputLoader {
  /** The path to the input file */
  private final String inputPath;
  /** The File of the input file */
  private File inputFile;

  public InputLoader(final String inputPath) {
    this.inputPath = inputPath;
    inputFile = new File(inputPath);
  }

  /**
   * method that reads the input
   *
   * @retur Input class
   */
  public Input readData() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper.readValue(inputFile, Input.class);
  }
}
