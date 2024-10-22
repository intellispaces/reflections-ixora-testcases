package intellispaces.samples.rdb.fetch;

import intellispaces.framework.core.IntellispacesFramework;
import intellispaces.framework.core.annotation.AutoGuide;
import intellispaces.framework.core.annotation.Inject;
import intellispaces.framework.core.annotation.Module;
import intellispaces.framework.core.annotation.Startup;
import intellispaces.ixora.cli.CliConfiguration;
import intellispaces.ixora.cli.MovableConsole;
import intellispaces.ixora.hikary.HikariConfiguration;
import intellispaces.ixora.rdb.MovableTransactionFactory;
import intellispaces.ixora.rdb.RdbConfiguration;
import intellispaces.ixora.rdb.TransactionFunctions;
import intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import intellispaces.ixora.data.association.IxoraPropertiesToDataGuide;
import intellispaces.samples.rdb.Book;
import intellispaces.samples.rdb.BookCrudGuide;
import intellispaces.samples.rdb.DefaultBookCrudGuide;

@Module(include = {
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    SnakeyamlGuide.class,
    IxoraPropertiesToDataGuide.class,
    DefaultBookCrudGuide.class
})
public abstract class FetchBookSample4 {

  /**
   * Book CRUD auto guide.
   */
  @Inject
  @AutoGuide
  abstract BookCrudGuide bookCrudGuide();

  /**
   * This method returns projection named 'transactionFactory'.<p/>
   *
   * Implementation of this method will be auto generated.
   */
  @Inject
  abstract MovableTransactionFactory transactionFactory();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
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
    IntellispacesFramework.loadModule(FetchBookSample4.class, args);
  }
}
