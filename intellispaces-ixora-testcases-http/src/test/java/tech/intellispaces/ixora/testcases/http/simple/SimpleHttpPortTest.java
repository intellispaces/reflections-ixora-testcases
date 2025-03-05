package tech.intellispaces.ixora.testcases.http.simple;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.LoggerFactory;
import tech.intellispaces.ixora.testcases.http.simple.testcase1.HttpSampleTestcase1;
import tech.intellispaces.ixora.testcases.http.simple.testcase2.HttpTestcase2;
import tech.intellispaces.jaquarius.system.Modules;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for simple port samples.
 */
public class SimpleHttpPortTest {

  @BeforeAll
  public static void disableLogging() {
    var lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    lc.getLogger("ROOT").setLevel(Level.ERROR);
  }

  @ParameterizedTest
  @ValueSource(classes = {
      HttpSampleTestcase1.class,
      HttpTestcase2.class
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
    assertThat(output).isEqualTo(
        "Current date: " + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + System.lineSeparator() +
        "Welcome message: Hello, John!" + System.lineSeparator()
    );
  }
}
