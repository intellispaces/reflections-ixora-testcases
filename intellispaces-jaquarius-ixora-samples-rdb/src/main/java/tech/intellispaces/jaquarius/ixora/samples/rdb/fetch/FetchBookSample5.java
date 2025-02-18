package tech.intellispaces.jaquarius.ixora.samples.rdb.fetch;

import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.ixora.cli.CliConfiguration;
import tech.intellispaces.jaquarius.ixora.cli.MovableConsoleHandle;
import tech.intellispaces.jaquarius.ixora.data.association.IxoraDictionaryToDataGuide;
import tech.intellispaces.jaquarius.ixora.data.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.jaquarius.ixora.hikaricp.configuration.HikariCpConfiguration;
import tech.intellispaces.jaquarius.ixora.rdb.configuration.RdbConfiguration;
import tech.intellispaces.jaquarius.ixora.rdb.transaction.MovableTransactionFactoryHandle;
import tech.intellispaces.jaquarius.ixora.rdb.transaction.TransactionFunctions;
import tech.intellispaces.jaquarius.ixora.samples.rdb.BookHandle;
import tech.intellispaces.jaquarius.ixora.samples.rdb.DefaultBookCrudGuide;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariCpConfiguration.class,
    SnakeyamlGuide.class,
    IxoraDictionaryToDataGuide.class,
    DefaultBookCrudGuide.class
})
public abstract class FetchBookSample5 {

  /**
   * Book CRUD default guide.
   */
  @Inject
  abstract DefaultBookCrudGuide bookCrudGuide();

  /**
   * This method returns projection named 'transactionFactory'.<p/>
   *
   * Implementation of this method will be auto generated.
   */
  @Inject
  abstract MovableTransactionFactoryHandle transactionFactory();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  public void startup(@Inject MovableConsoleHandle console) {
    MovableTransactionFactoryHandle transactionFactory = transactionFactory();
    TransactionFunctions.transactional(transactionFactory, tx -> {
      int bookId = 2;
      BookHandle book = bookCrudGuide().getById(tx, bookId);

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
    Modules.load(FetchBookSample5.class, args).start();
  }
}
