package tech.intellispaces.ixora.testcases.rdb.create;

import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.SimplePropertiesSetToDataGuide;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.ixora.hikaricp.configuration.HikariCpConfiguration;
import tech.intellispaces.ixora.rdb.annotation.Transactional;
import tech.intellispaces.ixora.rdb.configuration.RdbConfiguration;
import tech.intellispaces.ixora.rdb.transaction.Transactions;
import tech.intellispaces.ixora.testcases.rdb.Book;
import tech.intellispaces.ixora.testcases.rdb.BookCrudGuide;
import tech.intellispaces.ixora.testcases.rdb.Books;
import tech.intellispaces.ixora.testcases.rdb.DefaultBookCrudGuide;
import tech.intellispaces.reflections.framework.ReflectionsFramework;
import tech.intellispaces.reflections.framework.annotation.AutoGuide;
import tech.intellispaces.reflections.framework.annotation.Inject;
import tech.intellispaces.reflections.framework.annotation.Module;
import tech.intellispaces.reflections.framework.annotation.Startup;

/**
 * This testcase demonstrates creating a persisted entity in the database.
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
public abstract class CreateBookTestcase1 {

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
   * Method {@link Transactions#current()} is used to get a current transaction.
   * <p>
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the module projection named 'console'.
   */
  @Startup
  @Transactional
  public void startup(@Inject MovableConsole console) {
    Book bookContract = Books.build()
        .title("To the Lighthouse")
        .author("Virginia Woolf")
        .genre("Romance")
        .getUnmovable();

    Book book = bookCrudGuide().create(Transactions.current(), bookContract);

    console.print("Book created. Identifier " + book.id());
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    ReflectionsFramework.loadModule(CreateBookTestcase1.class, args).start();
  }
}
