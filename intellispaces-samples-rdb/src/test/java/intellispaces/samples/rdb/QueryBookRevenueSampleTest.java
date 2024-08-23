package intellispaces.samples.rdb;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import intellispaces.core.IntellispacesFramework;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.yaml.YamlDataSet;
import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for query book revenue samples.
 */
public class QueryBookRevenueSampleTest extends DataSourceBasedDBTestCase {

  private static final String DATABASE_URL = "jdbc:h2:mem:sample;" +
      "DB_CLOSE_DELAY=-1;" +
      "INIT=RUNSCRIPT FROM 'classpath:book_schema.sql'";

  @Override
  protected DataSource getDataSource() {
    var ds = new JdbcDataSource();
    ds.setURL(DATABASE_URL);
    ds.setUser("sa");
    ds.setPassword("sa");
    return ds;
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new YamlDataSet(QueryBookRevenueSampleTest.class.getResourceAsStream("/book_data.yaml"));
  }

  @Override
  public void setUp() throws Exception {
    var lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    lc.getLogger("ROOT").setLevel(Level.ERROR);
    super.setUp();
  }

  public void testRowCountSample1() {
    test(QueryBookRevenueSample1.class);
  }

  public void testRowCountSample2() {
    test(QueryBookRevenueSample2.class);
  }

  private void test(Class<?> moduleClass) {
    // Given
    var os = new ByteArrayOutputStream();
    var ps = new PrintStream(os, true, StandardCharsets.UTF_8);
    System.setOut(ps);

    // When
    IntellispacesFramework.loadModule(moduleClass);

    // Then
    String output = os.toString(StandardCharsets.UTF_8);
    assertThat(output).isEqualTo(
        "Book title: Winnie-the-Pooh. Revenue: 10" + System.lineSeparator() +
        "Book title: The Hobbit. Revenue: 50" + System.lineSeparator() +
        "Book title: The Bridges of Madison County. Revenue: 0" + System.lineSeparator() +
        "Book title: The Eye of the Moon. Revenue: 15" + System.lineSeparator()
    );
  }
}
