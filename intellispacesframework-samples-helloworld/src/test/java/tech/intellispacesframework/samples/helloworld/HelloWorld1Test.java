package tech.intellispacesframework.samples.helloworld;

import tech.intellispacesframework.core.IntellispacesFramework;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link HelloWorld1} class.
 */
public class HelloWorld1Test {

  @ParameterizedTest
  @ValueSource(classes = {
      HelloWorld1.class,
  })
  void testModuleOutput(Class<?> moduleClass) {
    // Given
    var outputStream = new ByteArrayOutputStream();
    var printStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8);
    System.setOut(printStream);

    // When
    IntellispacesFramework.createSystemModule(HelloWorld1.class).start();

    // Then
    String output = outputStream.toString(StandardCharsets.UTF_8);
    assertThat(output).isEqualTo("Hello, world!" + System.lineSeparator());
  }
}
