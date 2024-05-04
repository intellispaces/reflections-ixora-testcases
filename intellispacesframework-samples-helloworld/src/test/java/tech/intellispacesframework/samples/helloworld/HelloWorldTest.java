package tech.intellispacesframework.samples.helloworld;

import tech.intellispacesframework.core.IntellispacesFramework;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for HelloWorld samples.
 */
public class HelloWorldTest {

  @ParameterizedTest
  @ValueSource(classes = {
      HelloWorld1.class,
      HelloWorld2.class
  })
  void testHelloWorld(Class<?> moduleClass) {
    // Given
    var os = new ByteArrayOutputStream();
    var ps = new PrintStream(os, true, StandardCharsets.UTF_8);
    System.setOut(ps);

    // When
    IntellispacesFramework.createModule(moduleClass).start();

    // Then
    String output = os.toString(StandardCharsets.UTF_8);
    assertThat(output).isEqualTo("Hello, world!" + System.lineSeparator());
  }
}
