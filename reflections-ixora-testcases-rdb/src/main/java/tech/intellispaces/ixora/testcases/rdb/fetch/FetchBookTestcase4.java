package tech.intellispaces.ixora.testcases.rdb.fetch;

import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.cli.configuration.CliConfiguration;
import tech.intellispaces.ixora.data.association.SimplePropertiesSetToDataGuide;
import tech.intellispaces.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.ixora.hikaricp.configuration.HikariCpConfiguration;
import tech.intellispaces.ixora.rdb.configuration.RdbConfiguration;
import tech.intellispaces.ixora.rdb.transaction.MovableTransactionFactory;
import tech.intellispaces.ixora.rdb.transaction.TransactionFunctions;
import tech.intellispaces.ixora.testcases.rdb.Book;
import tech.intellispaces.ixora.testcases.rdb.DefaultBookCrudGuide;
import tech.intellispaces.reflections.Jaquarius;
import tech.intellispaces.reflections.annotation.Inject;
import tech.intellispaces.reflections.annotation.Module;
import tech.intellispaces.reflections.annotation.Startup;

/**
 * This testcase demonstrates getting a persisted entity from the database.
 * <p>
 * The default implementation of CRUD guide is used for this.
 */
@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariCpConfiguration.class,
    SnakeyamlGuide.class,
    SimplePropertiesSetToDataGuide.class,
    DefaultBookCrudGuide.class
})
public abstract class FetchBookTestcase4 {

  /**
   * The book default implementation of the CRUD guide.
   */
  @Inject
  abstract DefaultBookCrudGuide bookCrudGuide();

  /**
   * This method returns projection named 'transactionFactory'.<p/>
   *
   * Implementation of this method will be auto generated.
   */
  @Inject
  abstract MovableTransactionFactory transactionFactory();

  /**
   * This method will be invoked automatically after the module is started.
   * <p>
   * The transaction factory {@link #transactionFactory()} is used to create a transaction.
   * <p>
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the module projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableConsole console) {
    MovableTransactionFactory transactionFactory = transactionFactory();
    TransactionFunctions.transactional(transactionFactory, tx -> {
      int bookId = 2;
      Book book = bookCrudGuide().getById(tx, bookId);

      console.print("Book title: ");
      console.println(book.title());

      console.print("Book author: ");
      console.println(book.author());
    });
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Jaquarius.createModule(FetchBookTestcase4.class, args).start();
  }
}
