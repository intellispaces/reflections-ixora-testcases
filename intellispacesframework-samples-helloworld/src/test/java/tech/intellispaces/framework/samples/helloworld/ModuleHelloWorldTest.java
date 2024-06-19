package tech.intellispaces.framework.samples.helloworld;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.LoggerFactory;
import tech.intellispaces.framework.core.IntellispacesFramework;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for HelloWorld module samples.
 */
public class ModuleHelloWorldTest {

  @BeforeAll
  public static void disableLogging() {
    var lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    lc.getLogger("ROOT").setLevel(Level.ERROR);
  }

  @ParameterizedTest
  @ValueSource(classes = {
      ModuleHelloWorldSample1.class,
      ModuleHelloWorldSample2.class,
      ModuleHelloWorldSample3.class,
      ModuleHelloWorldSample4.class
  })
  void testHelloWorld(Class<?> moduleClass) {
    // Given
    var os = new ByteArrayOutputStream();
    var ps = new PrintStream(os, true, StandardCharsets.UTF_8);
    System.setOut(ps);

    // When
    IntellispacesFramework.loadModule(moduleClass).run();

    // Then
    String output = os.toString(StandardCharsets.UTF_8);
    assertThat(output).isEqualTo("Hello, world!" + System.lineSeparator());
  }
}
