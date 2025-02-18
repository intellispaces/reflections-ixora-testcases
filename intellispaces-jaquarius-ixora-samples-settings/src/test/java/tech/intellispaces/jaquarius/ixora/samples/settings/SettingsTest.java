package tech.intellispaces.jaquarius.ixora.samples.settings;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.LoggerFactory;
import tech.intellispaces.jaquarius.system.Modules;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for module settings samples.
 */
public class SettingsTest {

  @BeforeAll
  public static void disableLogging() {
    var lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    lc.getLogger("ROOT").setLevel(Level.ERROR);
  }

  @ParameterizedTest
  @ValueSource(classes = {
      YamlSettingsSample1.class,
      YamlSettingsSample2.class,
      YamlSettingsSample3.class,
      YamlSettingsSample4.class,
      YamlSettingsSample5.MainUnit.class
  })
  void testOutput(Class<?> moduleClass) {
    // Given
    var os = new ByteArrayOutputStream();
    var ps = new PrintStream(os, true, StandardCharsets.UTF_8);
    System.setOut(ps);

    // When
    Modules.flare(moduleClass);

    // Then
    String output = os.toString(StandardCharsets.UTF_8);
    assertThat(output).isEqualTo("City: East Centerville" + System.lineSeparator() +
        "Street: 123 Tornado Alley" + System.lineSeparator());
  }
}
