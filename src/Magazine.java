/**
 * <h1>Magazine.</h1>
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

public class Magazine extends Literature {

  // The title of the book.
  private String title;
  // The name of the series.
  private String name;

  /**
   * This constructs a book and sets the details and includes the functionality to add a name to the
   * series.
   *
   * @param name The name of the book.
   * @param title The title for the book.
   * @param publisher The publisher for the book.
   */
  public Magazine(String name, String title, String publisher, String releaseDate, int price) {
    super(publisher, releaseDate, price);
    this.title = title;
    this.name = name;
  }

  /**
   * Return the author.
   *
   * @return The author.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Return the title.
   *
   * @return title returns the title
   */
  public String getTitle() {
    return this.title;
  }

}