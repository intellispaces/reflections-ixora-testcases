package tech.intellispaces.ixora.testcases.rdb.fetch;

import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.SimplePropertiesSetToDataGuide;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.ixora.hikaricp.configuration.HikariCpConfiguration;
import tech.intellispaces.ixora.rdb.annotation.Transactional;
import tech.intellispaces.ixora.rdb.configuration.RdbConfiguration;
import tech.intellispaces.ixora.rdb.transaction.MovableTransaction;
import tech.intellispaces.ixora.testcases.rdb.Book;
import tech.intellispaces.ixora.testcases.rdb.BookCrudGuide;
import tech.intellispaces.ixora.testcases.rdb.DefaultBookCrudGuide;
import tech.intellispaces.reflections.framework.ReflectionsFramework;
import tech.intellispaces.reflections.framework.annotation.AutoGuide;
import tech.intellispaces.reflections.framework.annotation.Inject;
import tech.intellispaces.reflections.framework.annotation.Module;
import tech.intellispaces.reflections.framework.annotation.Startup;

/**
 * This testcase demonstrates getting a persisted entity from the database.
 * <p>
 * The CRUD auto guide is used for this. The specific guide will be selected automatically.
 */
@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariCpConfiguration.class,
    SnakeyamlGuide.class,
    SimplePropertiesSetToDataGuide.class,
    DefaultBookCrudGuide.class
})
public abstract class FetchBookTestcase2 {

  /**
   * The book CRUD auto guide.
   */
  @Inject
  @AutoGuide
  abstract BookCrudGuide bookCrudGuide();

  /**
   * This method will be invoked automatically after the module is started.
   * <p>
   * The method is executed inside a transaction, as the {@link Transactional} annotation is specified.
   * <p>
   * The values of method arguments will be injected automatically.
   *
   * @param tx the current transaction
   * @param console value of the module projection named 'console'.
   */
  @Startup
  @Transactional
  public void startup(@Inject MovableTransaction tx, @Inject MovableConsole console) {
    int bookId = 2;
    Book book = bookCrudGuide().getById(tx, bookId);

    console.print("Book title: ");
    console.println(book.title());

    console.print("Book author: ");
    console.println(book.author());
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    ReflectionsFramework.loadModule(FetchBookTestcase2.class, args).start();
  }
}
