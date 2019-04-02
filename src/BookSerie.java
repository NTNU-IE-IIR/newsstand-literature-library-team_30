import java.util.ArrayList;
import java.util.Iterator;

/**
 * <h1>Book.</h1>
 * This is a object that defines a book, it includes:
 * <ul>
 * <li> a title for the magazine.
 * <li> a name for the magazine
 * <li> the publisher of the magazine.
 * <li> the magazine number.
 * </ul>
 * You can do different changes to the object:
 * <ul>
 * <li> set an author.
 * <li> get an author.
 * <li> get an title.
 * <li> decide whether the book belongs to a series or not.
 * <li> set the name of a series.
 * <li> get the name of a series.
 * </ul>
 *
 * @author Sophus.
 * @version 1.1
 **/

public class BookSerie extends Literature implements Iterable{

  // an list that contains a series.
  private ArrayList<Book> books;
  // the title of the series.
  private String title;

  /**
   * This constructs the book series with a title and series of books.
   */
  public BookSerie(String title, String publisher) {
    super(publisher, "1231", 1337);
    this.title = title;
    books = new ArrayList<>();

  }

  /**
   * ads a book to the BookSeries.
   * @param book the book that you want to add.
   */
  public void addBook(Book book) {
    books.add(book);
  }

  public String getTitle() {
    return title;
  }

  /**
   * removes a book form the list.
   * @param book the book to remove.
   */
  public void remove(Book book){
    this.books.remove(book);
  }

  /**
   * returns an iterator of the series.
   * @return an iterator of the series.
   */
  public Iterator<Book> iterator(){
    return books.iterator();
  }
  /**
   *  returns an ArrayList with the books in the BookSeries.
   * @return an ArrayList with the books in the BookSeries.
   */
  public ArrayList<Book> getBooks() {
    return books;
  }

  /**
   * returns true if the series is empty.
   * @return true if the series is empty, false otherwise.
   */
  public boolean isEmpty(){
    return books.isEmpty();
  }

  public int size() {
    return books.size();
  }
}