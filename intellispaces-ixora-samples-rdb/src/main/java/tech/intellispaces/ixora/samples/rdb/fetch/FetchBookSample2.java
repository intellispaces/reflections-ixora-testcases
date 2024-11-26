package tech.intellispaces.ixora.samples.rdb.fetch;

import tech.intellispaces.ixora.cli.CliConfiguration;
import tech.intellispaces.ixora.cli.MovableConsole;
import tech.intellispaces.ixora.data.association.IxoraPropertiesToDataGuide;
import tech.intellispaces.ixora.hikary.HikariConfiguration;
import tech.intellispaces.ixora.rdb.RdbConfiguration;
import tech.intellispaces.ixora.rdb.Transactions;
import tech.intellispaces.ixora.rdb.annotation.Transactional;
import tech.intellispaces.ixora.samples.rdb.Book;
import tech.intellispaces.ixora.samples.rdb.BookCrudGuide;
import tech.intellispaces.ixora.samples.rdb.DefaultBookCrudGuide;
import tech.intellispaces.ixora.snakeyaml.SnakeyamlGuide;
import tech.intellispaces.jaquarius.annotation.AutoGuide;
import tech.intellispaces.jaquarius.annotation.Inject;
import tech.intellispaces.jaquarius.annotation.Module;
import tech.intellispaces.jaquarius.annotation.Startup;
import tech.intellispaces.jaquarius.system.Modules;

@Module({
    CliConfiguration.class,
    RdbConfiguration.class,
    HikariConfiguration.class,
    SnakeyamlGuide.class,
    IxoraPropertiesToDataGuide.class,
    DefaultBookCrudGuide.class
})
public abstract class FetchBookSample2 {

  /**
   * Book CRUD auto guide.
   */
  @Inject
  @AutoGuide
  abstract BookCrudGuide bookCrudGuide();

  /**
   * This method will be invoked automatically after the module is started.<p/>
   *
   * The values of method arguments will be injected automatically.
   *
   * @param console value of the projection named 'console'.
   */
  @Startup
  @Transactional
  public void startup(@Inject MovableConsole console) {
    int bookId = 2;
    Book book = bookCrudGuide().getById(Transactions.current(), bookId);

    console.print("Book title: ");
    console.println(book.title());

    console.print("Book author: ");
    console.println(book.author());
  }

  /**
   * In the main method, we load and run the IntelliSpaces framework module.
   */
  public static void main(String[] args) {
    Modules.get(FetchBookSample2.class, args).start();
  }
}
