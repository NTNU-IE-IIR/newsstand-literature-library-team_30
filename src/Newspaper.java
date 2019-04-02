/**
 * <h1>Newspaper.</h1>
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

public class Newspaper extends Literature {

  // The author of the book.
  private String name;

  /**
   * This constructs a book and sets the details and includes the functionality to add a name to the
   * series.
   *
   * @param name The name for the newspaper.
   * @param releaseDate The releaseDate for the newspaper.
   * @param publisher The publisher for the newspaper.
   */
  public Newspaper(String name, String publisher, String releaseDate, int price) {
    super(publisher, releaseDate, price);
    setName(name);
  }

  private void setName(String name) {
    if (name != null) {
      this.name = name;
    } else {
      //TODo learn about throw.
      this.name = "DEFINITELY_INVALID_AUTHOR";
    }
  }

  public String getName() {
    return this.name;
  }
}