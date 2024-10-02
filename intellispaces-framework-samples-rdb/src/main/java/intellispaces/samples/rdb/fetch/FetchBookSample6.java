package intellispaces.samples.rdb.fetch;

import intellispaces.framework.core.IntellispacesFramework;
import intellispaces.framework.core.annotation.Inject;
import intellispaces.framework.core.annotation.Module;
import intellispaces.framework.core.annotation.Startup;
import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.cli.Console;
import intellispaces.ixora.hikary.HikariConfiguration;
import intellispaces.ixora.rdb.RdbConfiguration;
import intellispaces.ixora.rdb.TransactionFactory;
import intellispaces.ixora.rdb.TransactionFunctions;
import intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import intellispaces.ixora.structures.association.IxoraPropertiesToDataGuide;
import intellispaces.samples.rdb.Book;
import intellispaces.samples.rdb.DefaultBookCrudGuide;
import intellispaces.samples.rdb.TransactionToBookByIdentifierChannel;

@Module(include = {
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    SnakeyamlGuide.class,
    IxoraPropertiesToDataGuide.class,
    DefaultBookCrudGuide.class
})
public abstract class FetchBookSample6 {

  /**
   * This method returns projection named 'transactionFactory'.<p/>
   *
   * Implementation of this method will be auto generated.
   */
  @Inject
  abstract TransactionFactory transactionFactory();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject Console console) {
    TransactionFactory transactionFactory = transactionFactory();
    TransactionFunctions.transactional(transactionFactory, tx -> {
      int bookId = 2;
      Book book = tx.mapThru(TransactionToBookByIdentifierChannel.class, bookId);

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
    IntellispacesFramework.loadModule(FetchBookSample6.class, args);
  }
}
