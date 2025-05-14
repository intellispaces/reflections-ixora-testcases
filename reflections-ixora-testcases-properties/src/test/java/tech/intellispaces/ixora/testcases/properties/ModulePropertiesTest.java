package tech.intellispaces.ixora.testcases.properties;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.LoggerFactory;

import tech.intellispaces.reflections.framework.ReflectionsFramework;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for module properties samples.
 */
public class ModulePropertiesTest {

  @BeforeAll
  public static void disableLogging() {
    var lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    lc.getLogger("ROOT").setLevel(Level.ERROR);
  }

  @ParameterizedTest
  @ValueSource(classes = {
      ModulePropertiesTestcase1.class,
      ModulePropertiesTestcase2.class,
      ModulePropertiesTestcase3.class,
      ModulePropertiesTestcase4.class,
      ModulePropertiesTestcase5.MainUnit.class
  })
  void testOutputWhenDefaultPropertiesFile(Class<?> moduleClass) {
    // Given
    var os = new ByteArrayOutputStream();
    var ps = new PrintStream(os, true, StandardCharsets.UTF_8);
    System.setOut(ps);

    // When
    ReflectionsFramework.flashModule(moduleClass);

    // Then
    String output = os.toString(StandardCharsets.UTF_8);
    assertThat(output).isEqualTo("City: East Centerville" + System.lineSeparator() +
        "Street: 123 Tornado Alley" + System.lineSeparator());
  }

  @ParameterizedTest
  @ValueSource(classes = {
      ModulePropertiesTestcase6.class
  })
  void testOutputWhenSpecificPropertiesFile(Class<?> moduleClass) {
    // Given
    var os = new ByteArrayOutputStream();
    var ps = new PrintStream(os, true, StandardCharsets.UTF_8);
    System.setOut(ps);

    // When
    ReflectionsFramework.flashModule(moduleClass);

    // Then
    String output = os.toString(StandardCharsets.UTF_8);
    assertThat(output).isEqualTo("City: Port Carey" + System.lineSeparator() +
        "Street: 312 Leffler Forges" + System.lineSeparator());
  }
}
