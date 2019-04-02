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
 * @author Sophus Fredborg Stokke.
 * @version 1.1
 **/

public class Book extends Literature {

  // The title of the book.
  private String title;
  // The author of the book.
  private String author;
  // the number of the edition of the book...
  private int edition;


  /**
   * This constructs a book and sets the details and includes the functionality to add a name to the
   * series.
   *
   * @param author The author for the book.
   * @param title The title for the book.
   * @param publisher The publisher for the book.
   * @param edition The current edition of the book.
   */
  public Book(String author, String title, String publisher, int edition) {
    // TODO remove Placeholder price.
    super(publisher, "1984", 420);
    setAuthor(author);
    this.title = title;
    this.edition = edition;
  }

  private void setAuthor(String author) {
    if (author != null) {
      this.author = author;
    } else {
      this.author = "NO_AUTHOR_AVAILABLE";
    }
  }

  /**
   * Return the author.
   *
   * @return The author.
   */
  public String getAuthor() {
    return this.author;
  }


  /**
   * Return the title.
   *
   * @return title returns the title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Return the edition.
   *
   * @return edition returns the edition
   */
  public int getEdition() {
    return this.edition;
  }

}