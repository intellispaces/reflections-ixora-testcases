package tech.intellispaces.ixora.samples.rdb.query;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import tech.intellispaces.jaquarius.system.Modules;
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
 * Tests for query book sales samples.
 */
public class QueryBookSalesSampleTest extends DataSourceBasedDBTestCase {

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
    return new YamlDataSet(QueryBookSalesSampleTest.class.getResourceAsStream("/book_data.yaml"));
  }

  @Override
  public void setUp() throws Exception {
    var lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    lc.getLogger("ROOT").setLevel(Level.ERROR);
    super.setUp();
  }

  public void testQueryBookSalesSample1() {
    test(QueryBookSalesSample1.class);
  }

  public void testQueryBookSalesSample2() {
    test(QueryBookSalesSample2.class);
  }

  private void test(Class<?> moduleClass) {
    // Given
    var os = new ByteArrayOutputStream();
    var ps = new PrintStream(os, true, StandardCharsets.UTF_8);
    System.setOut(ps);

    // When
    Modules.get(moduleClass).start();

    // Then
    String output = os.toString(StandardCharsets.UTF_8);
    assertThat(output).isEqualTo(
        "Book title: Winnie-the-Pooh. Sales: 10" + System.lineSeparator() +
        "Book title: The Hobbit. Sales: 50" + System.lineSeparator() +
        "Book title: The Bridges of Madison County. Sales: 0" + System.lineSeparator() +
        "Book title: The Eye of the Moon. Sales: 15" + System.lineSeparator()
    );
  }
}
