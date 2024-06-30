package tech.intellispaces.framework.samples.rdb;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.yaml.YamlDataSet;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;
import tech.intellispaces.framework.core.IntellispacesFramework;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Tests for RDB samples.
 */
public class RdbSampleTest extends DataSourceBasedDBTestCase {
  @Override
  protected DataSource getDataSource() {
    var ds = new JdbcDataSource();
    ds.setURL("jdbc:h2:mem:rdb_sample;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:rdb_sample_schema.sql'");
    ds.setUser("sa");
    ds.setPassword("sa");
    return ds;
  }

  @Override
  public void setUp() throws Exception {
//    var lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//    lc.getLogger("ROOT").setLevel(Level.ERROR);
    super.setUp();
  }

//  @Override
//  protected DatabaseOperation getSetUpOperation() {
//    return DatabaseOperation.REFRESH;
//  }

//  @Override
//  protected DatabaseOperation getTearDownOperation() {
//    return DatabaseOperation.DELETE_ALL;
//  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return new YamlDataSet(RdbSampleTest.class.getResourceAsStream("/rdb_sample_data.yaml"));
  }

  @Test
  public void testOutput() {
    Class<?> moduleClass = RdbSample1.class;

    // Given
    var os = new ByteArrayOutputStream();
    var ps = new PrintStream(os, true, StandardCharsets.UTF_8);
    System.setOut(ps);

    // When
    IntellispacesFramework.loadModule(moduleClass).run();

    // Then
    String output = os.toString(StandardCharsets.UTF_8);

  }
}
