package tech.intellispaces.ixora.testcases.rdb.query;

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

import tech.intellispaces.jaquarius.Jaquarius;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for query book count.
 */
public class QueryBookCountTest extends DataSourceBasedDBTestCase {

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
    return new YamlDataSet(QueryBookCountTest.class.getResourceAsStream("/book_data.yaml"));
  }

  @Override
  public void setUp() throws Exception {
    var lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    lc.getLogger("ROOT").setLevel(Level.ERROR);
    super.setUp();
  }

  public void testQueryBookCountSample1() {
    test(QueryBookCountTestcase1.class);
  }

  public void testQueryBookCountSample2() {
    test(QueryBookCountTestcase2.class);
  }

  public void testQueryBookCountSample3() {
    test(QueryBookCountTestcase3.class);
  }

  public void testQueryBookCountSample4() {
    test(QueryBookCountTestcase4.class);
  }

  public void testQueryBookCountSample5() {
    test(QueryBookCountTestcase5.class);
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
    assertThat(output).isEqualTo("Number books: 4" + System.lineSeparator());
  }
}
