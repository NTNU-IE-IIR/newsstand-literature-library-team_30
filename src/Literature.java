/**
 * <h1>Literature.</h1>
 * This is a object that defines a work of literature, it includes:
 * <ul>
 * <li> the publisher of the literature.
 * <li> the price of the literature
 * </ul>
 * You can do different changes to the object:
 * <ul>
 * <li> set a publisher.
 * <li> get a publisher.
 * <li> set the price.
 * <li> get the price.
 * </ul>
 *
 * @author Sophus Fredborg Stokke.
 * @version 1
 **/
abstract class Literature {

  //the publisher of the work of literature.
  private String publisher;
  //the price of the work of literature.
  private int price;
  // The date the product was first published
  private String releaseDate;

  /**
   * creates a work of literature with a publisher and a class.
   *
   * @param publisher the publisher of the literature.
   * @param price the price of the literature.
   */
  public Literature(String publisher, String releaseDate, int price) {
    this.releaseDate = releaseDate;
    this.price = price;
    this.publisher = publisher;
  }

  /**
   * returns the publisher.
   *
   * @return a string that is the publisher.
   */
  public String getPublisher() {
    return this.publisher;
  }

  /**
   * returns the price.
   *
   * @return an integer that is the price of the literature.
   */
  public int getPrice() {
    return price;
  }

  /**
   * sets a new price for the literature.
   *
   * @param price the new price.-
   */
  private void setPrice(int price) {
    this.price = price;
  }

  /**
   * sets an new publisher for the literature.
   *
   * @param publisher the new publisher
   */
  private void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  /**
   * returns the release date of the product.
   *
   * @return an string that is the release date of the litrature.
   */
  public String getReleaseDate() {
    return releaseDate;
  }

  /**
   * sets an new release date.
   *
   * @param releaseDate the new realese date.
   */
  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }


}