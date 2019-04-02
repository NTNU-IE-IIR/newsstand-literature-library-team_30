import java.util.ArrayList;
import java.util.Iterator;

/**
 * Ein del av eit større gruppeprosjekt.
 *
 * //TODO Should consider looking into how to store books and add a Publishing date to these books.
 *
 * <p>This could be valuable for the future, and perhaps even make it into a constructor that takes
 * a specified book and adds it as published in todays date. There should also be a way in \Java (or
 * a method) to instantly add a dato (showing number of the month, what month in characters and what
 * year in numbers) thru parameters like 11,11,1990.</p>
 *
 * @author Sophus, Kevin.
 * @version 1.1
 */
public class Register implements Iterable {
  // An ArrayList for storing literature.
  private ArrayList<Literature> literature;

  /**
   * Constructor for objects of class Kiosk //TODO - Going to rename this class to "Register" - This
   * change is because of the change in understanding - the true concept of "how" this kiosk is
   * going to work.
   */
  public Register() {
    // initialise instance variables
    literature = new ArrayList<>();
  }

  /**
   * This method adds a work of literature to the register collection.
   *
   * @param literature The literature to add to the register.
   */
  public void addLiterature(Literature literature) {
    this.literature.add(literature);
  }


  /**
   * the iterator class returns an iterator if the register.
   *
   * @return an iterator of the register.
   */
  public Iterator iterator() {
    return literature.iterator();
  }

  /**
   * the number of elements stored in the register is returned.
   *
   * @return the number of elements in the register.
   */
  public int size() {
    return literature.size();
  }

  /**
   * checks if the register is empty.
   *
   * @return true if the register is empty, false otherwise.
   */
  public boolean isEmpty() {
    boolean isEmpty = true;
    if (size() > 0) {
      isEmpty = false;
    }
    return isEmpty;
  }

  /**
   * checks if the Register contains an object.
   *
   * @return true if the register has te object false otherwice.
   *
   * @paran object the object that you want to check if the listeraturelist contains.
   */
  public boolean contains(Literature object) {
    return literature.contains(object);
  }

  /**
   * removes an object form the Register.
   *
   * @param object the object that you want to remove.
   */
  public void remove(Object object) {
    literature.remove(object);
  }

  /**
   * returns an array of the Register.
   *
   * @return an array of the register.
   */
  public ArrayList<Literature> getArray() {
    return literature;
  }

  /**
   * replaces a sertitian Piece of literature with another piece of literature.
   *
   * @param oldLit the literature to be replaced.
   * @param replacementLit the literature that will replace the old literature.
   */
  public void replace(Literature oldLit, Literature replacementLit) {
    Iterator iterator = literature.iterator();
    int index = 0;
    while (iterator.hasNext()) {
      index++;
      Literature lit = (Literature) iterator.next();
      if (lit.equals(oldLit)) {
        this.literature.remove(index);
        this.literature.add(index, replacementLit);
      }
    }
  }

  /**
   * cheks if the register has a piece of literature that equals the one provided.
   *
   * @param literatureToCheck the work of literature that you want to check.
   *
   * @return true if it has This Literature false othervice.
   */
  public boolean hasThisLiterature(Literature literatureToCheck) {
    boolean returnBool = false;
    for (Literature lit : this.literature) {
      if (literatureToCheck.equals(lit)) {
        returnBool = true;
      }
    }
    return returnBool;
  }


}