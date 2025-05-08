package tech.intellispaces.ixora.testcases.rdb.fetch;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import javax.sql.DataSource;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.yaml.YamlDataSet;
import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.LoggerFactory;

import tech.intellispaces.reflections.Jaquarius;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for fetch book.
 */
public class FetchBookTest extends DataSourceBasedDBTestCase {

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
    return new YamlDataSet(FetchBookTest.class.getResourceAsStream("/book_data.yaml"));
  }

  @Override
  public void setUp() throws Exception {
    var lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    lc.getLogger("ROOT").setLevel(Level.ERROR);
    super.setUp();
  }

  public void testFetchBookSample1() {
    test(FetchBookTestcase1.class);
  }

  public void testFetchBookSample2() {
    test(FetchBookTestcase2.class);
  }

  public void testFetchBookSample3() {
    test(FetchBookTestcase3.class);
  }

  public void testFetchBookSample4() {
    test(FetchBookTestcase4.class);
  }

  public void testFetchBookSample5() {
    test(FetchBookTestcase5.class);
  }

  public void testFetchBookSample6() {
    test(FetchBookTestcase6.class);
  }

  private void test(Class<?> moduleClass) {
    // Given
    var os = new ByteArrayOutputStream();
    var ps = new PrintStream(os, true, StandardCharsets.UTF_8);
    System.setOut(ps);

    // When
    Jaquarius.createStartAndStopModule(moduleClass);

    // Then
    String output = os.toString(StandardCharsets.UTF_8);
    assertThat(output).isEqualTo(
        "Book title: The Hobbit" + System.lineSeparator() +
        "Book author: J. R. R. Tolkien" + System.lineSeparator()
    );
  }
}
