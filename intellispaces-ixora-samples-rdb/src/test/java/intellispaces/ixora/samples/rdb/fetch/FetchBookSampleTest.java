package intellispaces.ixora.samples.rdb.fetch;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import intellispaces.jaquarius.IntellispacesFramework;
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
 * Tests for fetch book samples.
 */
public class FetchBookSampleTest extends DataSourceBasedDBTestCase {

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
    return new YamlDataSet(FetchBookSampleTest.class.getResourceAsStream("/book_data.yaml"));
  }

  @Override
  public void setUp() throws Exception {
    var lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    lc.getLogger("ROOT").setLevel(Level.ERROR);
    super.setUp();
  }

  public void testFetchBookSample1() {
    test(FetchBookSample1.class);
  }

  public void testFetchBookSample2() {
    test(FetchBookSample2.class);
  }

  public void testFetchBookSample3() {
    test(FetchBookSample3.class);
  }

  public void testFetchBookSample4() {
    test(FetchBookSample4.class);
  }

  public void testFetchBookSample5() {
    test(FetchBookSample5.class);
  }

  public void testFetchBookSample6() {
    test(FetchBookSample6.class);
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
        "Book title: The Hobbit" + System.lineSeparator() +
        "Book author: J. R. R. Tolkien" + System.lineSeparator()
    );
  }
}
