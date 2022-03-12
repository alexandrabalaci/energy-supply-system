package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Class that writes to output in json format
 *
 * <p>
 */
public final class Writer {
  /** The path to the output file */
  private final String outputPath;

  private final File outputFile;

  public Writer(final String outputPath) {
    this.outputPath = outputPath;
    outputFile = new File(outputPath);
  }

  /**
   * method that writes the output
   */
  public void writeData(final Output output) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(outputFile, output);
  }
}
