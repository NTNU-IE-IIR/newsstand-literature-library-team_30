
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * <p>
 * Makes up the user interface (text based) of the application. Responsible for all user
 * interaction, like displaying the menu and receiving input from the user.
 * </p>
 *
 * @author Kevin, Sophus, (Bjørn).
 * @version 3.0
 */
public class ApplicationUI
{

  private Register bookRegister;
  private Scanner reader;

  // The menu tha will be displayed. Please edit/alter the menu
  // to fit your application (i.e. replace "product" with "literature"
  // etc.

  private String[] menuItems = {
      "0. Quit program",
      "1. List all products",
      "2. Add new product",
      "3. Find a product",
      "4. Remove a piece of literature",
      "5. Add a book to a series"
  };

  private String[] searchMenuItems = {
      "0. Main menu",
      "1. List by author",
      "2. List by title",
      "3. List by publisher",
      "4. List by the name of a series"
  };

  /**
   * Creates an instance of the ApplicationUI User interface.
   */
  public ApplicationUI()
  {
    this.reader = new Scanner(System.in);
    this.bookRegister = new Register();
    NEW_REGISTER_DEMO();
  }

  /**
   * Starts the application by showing the menu and retrieving input from the user.
   */
  public void start()
  {

    boolean quit = false;

    while (!quit)
    {
      try
      {
        int menuSelection = this.showMenu();
        switch (menuSelection)
        {
          case 0:
            System.out.print("\n> Thank you for using the Library v3.0. Bye!\n");
            quit = true;
            break;
          case 1:
            this.listAllProducts();
            break;

          case 2:
            this.addNewProduct();
            break;

          case 3:
            this.findLiteratureByUserInput();
            break;

          case 4:
            this.removeLiterature();
            break;

          case 5:
            this.addBookToSeries();
            break;

          default:
            break;
        }
      } catch (InputMismatchException ime)
      {
        System.out.print(
            "\nERROR: Please provide a number between 1 and " + (this.menuItems.length - 1)
                + "..\n");
      }

    }

  }

  /**
   * adds a book a series either a new book made form user input or an existing one
   */
  private void addBookToSeries()
  {
    boolean createBook = true;
    System.out.println("What series do you want to add a book to?");
    String seriesName = readerStringInputWithCommand("Name of series: ");
    BookSerie bookSerie = getBookSerie(seriesName);
    if (bookSerie == null)
    {
      System.out
          .print("This book series does not exist do you want to create it?\n1. YES\n2. NO\n> ");
      switch (getReaderInt(1, 2))
      {
        case 1:
          bookSerie = createBookSerieFormUserInput();
          System.out.print("Would you like to add this book to the register as well?" + "\n1. Yes!"
              + " Yes!\n2. No! No!\n> ");
          askIfYouWantToAddLiteratureToRegister(bookSerie);

          createBook = true;
          break;
        case 2:
          createBook = false;
          break;

      }
    }
    if (createBook)
    {
      Book book = null;
      System.out
          .print("Do you want to add an existing book or a new one?\n1. NEW\n2. EXISTING\n> ");
      switch (getReaderInt(1, 2))
      {
        case 1:
          book = createBookFormUserInput();
          System.out
              .print("Do you want to add this book to the register as well? \n1. YES\n2. NO\n> ");
          askIfYouWantToAddLiteratureToRegister(book);
          break;
        case 2:
          book = getBook();
          if (book == null)
          {
            System.out.print("This book does not exist do you want to create it?"
                + "(The following alternatives might be in russian) \n1. да"
                + "\n2. Нет.\n> ");
            switch (getReaderInt(1, 2))
            {
              case 1:
                book = createBookFormUserInput();
                System.out.print(
                    "Do you want to add this book to the register as well? \n1. YES\n2. NO\n> ");
                askIfYouWantToAddLiteratureToRegister(book);
                break;
              case 2:
                break;
            }
          }
          break;
      }
      if (book != null)
      {
        bookSerie.addBook(book);
      } else
      {
        System.out.println("No books was added to the series.");
      }
    }
    System.out.println("These books are now in this series: ");
    ArrayList<Book> books = bookSerie.getBooks();
    if (books.size() > 0)
    {
      bookSerie.getBooks().forEach(book -> printBookDetails(book));
    } else
    {
      System.out.println("This series is empty...");
    }
  }

  private void askIfYouWantToAddLiteratureToRegister(Literature literature)
  {
    switch (getReaderInt(1, 2))
    {
      case 1:
        bookRegister.addLiterature(literature);
        break;
      case 2:
        break;
      default:
        break;
    }
  }

  /**
   * Find and display a product based om name (title). As with the addNewProduct()-method, you have
   * to ask the user for the string (name/title/publisher) to search for, and then use this string
   * as input- parameter to the method in the register-object. Then, upon return from the register,
   * you need to print the details of the found item.
   */
  private void findLiteratureByUserInput()
  {
    boolean back = false;
    while (!back)
    {
      try
      {
        int searchMenuSelection = this.showSearchMenu();
        switch (searchMenuSelection)
        {
          case 1:
            this.listByAuthor();
            break;

          case 2:
            this.listByTitle();
            break;

          case 3:
            this.listByPublisher();
            break;

          case 4:
            this.listBySeriesName();
            break;

          case 0:
            System.out.print("\n> Going back to the main menu.");
            back = true;
            break;
          default:
        }
      } catch (InputMismatchException ime)
      {
        System.out.print(
            "\nERROR: Please provide a number between 1 and " + this.searchMenuItems.length
                + "..\n");
      }
    }
  }

  /**
   * Displays the menu to the user, and waits for the users input. The user is expected to input an
   * integer between 1 and the max number of menu items. If the user inputs anything else, an
   * InputMismatchException is thrown. The method returns the valid input from the user.
   *
   * @return the menu number (between 1 and max menu item number) provided by the user.
   */
  private int showMenu()
  {
    System.out.print("\n**** The Library v3.0 ****\n");
    // Display the menu
    return displayTheMenu(menuItems);
  }

  /**
   * Displays the menu to the user, and waits for the users input. The user is expected to input an
   * Integer between 1 and the max number of menu items. If the user inputs anything else, an
   * InputMismatchException is thrown. The method returns the valid input from the user.
   */
  private int showSearchMenu()
  {
    String subMenu = "Main menu";
    System.out.print("\nSEARCH MENU\n");
    // Display the menu
    return displayTheMenu(searchMenuItems);
  }

  /**
   * Displays the menu to the user through the terminal.
   *
   * @param menuType This parameter differs between two options. Either the parameter is
   *     "searchMenuItems", that shows the items in the search menu where the user can search for
   *     author, titles or publisher. The other option is that the user shows the regular menu.
   * @return Returns which menu was selected.
   */
  private int displayTheMenu(String[] menuType)
  {
    for (String menuItem : menuType)
    {
      System.out.println(menuItem);
    }
    int maxMenuItemNumber = menuType.length - 1;
    // Read input from user// Read input from user
    int menuSelection = readerIntInputNoPrint(0, maxMenuItemNumber);
    if ((menuSelection < 0) || (menuSelection > maxMenuItemNumber))
    {
      throw new InputMismatchException();
    }
    return menuSelection;
  }

  // ------ The methods below this line are "helper"-methods, used from the menu ----
  // ------ All these methods are made private, since they are only used by the menu ---

  /**
   * Lists all the products/literature in the register.
   */
  private void listAllProducts()
  {
    listAllLitterature();
  }

  /**
   * Add a new product/literature to the register. In this method you have to add code to ask the
   * user for the necessary information you need to create an instance of the product, which you
   * then send as a parameter to the addNewspaper()- method of the register. Remember to also handle
   * invalid input from the user!!
   */
  private void addNewProduct()
  {
    Literature newLiterature = createLiteratureFromUserInput();
    bookRegister.addLiterature(newLiterature);
  }

  /**
   * creates and returns an instance of literature based on the userimput provided.
   *
   * @return the literature that was created.
   */
  private Literature createLiteratureFromUserInput()
  {
    Literature returnLiterature = null;
    boolean back = false;
    while (!back)
    {
      System.out.println("What literature do you want to create?");
      try
      {
        System.out.print(
            "0. Main Menu"
                + "\n1. Book"
                + "\n2. Magazine"
                + "\n3. Book Series"
                + "\n4. Newspaper"
                + "\n> ");
        int readerint = getReaderInt(0, 4);
        switch (readerint)
        {
          case 1:
            returnLiterature = createBookFormUserInput();
            break;
          case 2:
            returnLiterature = createMagazineFormUserInput();
            break;
          case 3:
            returnLiterature = createBookSerieFormUserInput();
            break;
          case 4:
            returnLiterature = createNewspaperFormUserInput();
            break;

          case 0:
            System.out.println("Going back to Main Menu.");
            back = true;
            break;
          default:
        }
      } catch (InputMismatchException ime)
      {
        System.out.print(
            "\nERROR: Please provide a number between 1 and " + this.searchMenuItems.length
                + "..\n");
      }
    }
    return returnLiterature;
  }

  /**
   * creates a book based on user input.
   *
   * @return a book based on user input.
   */
  private Book createBookFormUserInput()
  {
    return new Book(readerStringInputWithCommand("Author: "),
        readerStringInputWithCommand("Title: "),
        readerStringInputWithCommand("Publisher: "),
        readerIntInput("Edition: ", 0, 100));
  }

  /**
   * creates a magazine based on user input.
   *
   * @return a magazine based on user input.
   */
  private Magazine createMagazineFormUserInput()
  {
    return new Magazine(readerStringInputWithCommand("Name: "),
        readerStringInputWithCommand("Title: "),
        readerStringInputWithCommand("Publisher: "),
        readerStringInputWithCommand("releaseDate:: "),
        readerIntInput("price: ", 0, 1000));
  }

  /**
   * creates a book Series based on user input.
   *
   * @return a book series based on user input.
   */
  private BookSerie createBookSerieFormUserInput()
  {
    return new BookSerie(readerStringInputWithCommand("Author: "),
        readerStringInputWithCommand("Title: "));
  }

  /**
   * creates a newspaper based on user input.
   *
   * @return a newspaper based on user input.
   */
  private Newspaper createNewspaperFormUserInput()
  {
    return new Newspaper(readerStringInputWithCommand("Name: "),
        readerStringInputWithCommand("Publisher: "),
        readerStringInputWithCommand("releaseDate: "),
        readerIntInput("Price: ", 0, 1000));
  }

  /**
   * prints a List of all the books currently stored in the register.
   */
  public void listAllLitterature()
  {
    Iterator<Literature> litIT = bookRegister.iterator();
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Magazine> magazines = new ArrayList<>();
    ArrayList<Newspaper> newspapers = new ArrayList<>();
    ArrayList<BookSerie> bookSeries = new ArrayList<>();

    while (litIT.hasNext())
    {
      Literature literature = litIT.next();
      if (literature instanceof Book)
      {
        books.add((Book) literature);
      } else if (literature instanceof Magazine)
      {
        magazines.add((Magazine) literature);
      } else if (literature instanceof BookSerie)
      {
        bookSeries.add((BookSerie) literature);
      } else if (literature instanceof Newspaper)
      {
        newspapers.add((Newspaper) literature);
      }
    }

    System.out.println("> Listing all literature:" + "\n" + "> There are currently "
        + (bookRegister.size() - bookSeries.size())
        + " registered here");

    if (!books.isEmpty())
    {
      System.out.print("\n--------------- BOOKS ---------------\n");
      for (Book book : books)
      {
        printBookDetails(book);
      }
    }
    if (!bookSeries.isEmpty())
    {
      System.out.println("\n--------------- BOOKSERIES ---------------\n");
      for (BookSerie bookSerie : bookSeries)
      {
        printBookSerieDetails(bookSerie);
      }
    }
    if (!magazines.isEmpty())
    {
      System.out.println("\n--------------- MAGAZINES ---------------\n");
      for (Magazine mag : magazines)
      {
        printMagazineDetails(mag);
      }
    }
    if (!newspapers.isEmpty())
    {
      System.out.println("\n--------------- NEWSPAPER ---------------\n");
      for (Newspaper newspaper : newspapers)
      {
        printNewspaperDetails(newspaper);
      }
    }
    System.out.println("\n--------------------------------------\n");
  }


  /**
   * prints the details of some litrature.
   *
   * @param lit the litrature that you want to print
   */
  private void printLiteratureDetails(Literature lit)
  {
    if (lit instanceof Book)
    {
      printBookDetails((Book) lit);
    } else if (lit instanceof Magazine)
    {
      printMagazineDetails((Magazine) lit);
    } else if (lit instanceof Newspaper)
    {
      printNewspaperDetails((Newspaper) lit);
    } else if (lit instanceof BookSerie)
    {
      printBookSerieDetails((BookSerie) lit);
    }
  }

  /**
   * prints newspaper details.
   *
   * @param lit the newspaper to print.
   */
  private void printNewspaperDetails(Newspaper lit)
  {
    System.out.println("Name: " + lit.getName()
        + " | Publisher: " + lit.getPublisher()
        + " | ReleaseDate: " + lit.getReleaseDate());
  }

  /**
   * prints magazine details.
   *
   * @param lit the magazine to print.
   */
  private void printMagazineDetails(Magazine lit)
  {
    System.out.println(">Title: " + lit.getTitle()
        + " | Name: " + lit.getName()
        + " | Publisher: " + lit.getPublisher()
        + " | ReleaseDate: " + lit.getReleaseDate());
  }

  /**
   * Find and display a product based om name (title). As with the addNewProduct()-method, you have
   * to ask the user for the string (name/title/publisher) to search for, and then use this string
   * as input- parameter to the method in the register-object. Then, upon return from the register,
   * you need to print the details of the found item.
   */
  private void listByTitle()
  {
    System.out.println("Type in the title you are looking for: ");
    String title = readerStringInput();
    HashSet<Book> titleBookList = findBooksByTitle(title);
    for (Book book : titleBookList)
    {
      printBookDetails(book);
    }
    if (titleBookList.isEmpty())
    {
      System.out.println(
          "> We are sorry to tell you that we do NOT now have any books with the following title: "
              + title
              + "\n> If you'd like us to order this book, please "
              + "ask the personnel if it is possible to order this book");
    }
  }

  /**
   * Searches through the register and lists up all books by the given author.
   */
  private void listByAuthor()
  {
    System.out.println("Type in the author you are looking for: ");
    String author = readerStringInput();
    HashSet<Book> authorBookList = findBooksByAuthor(author);
    for (Book book : authorBookList)
    {
      printBookDetails(book);
    }
    if (authorBookList.isEmpty())
    {
      System.out.println(
          "> We are sorry to tell you that we do NOT now have any book from the following author: "
              + author
              + "\n> Please ask the personnel if you'd like "
              + "us to order anything from this author.");
    }
  }

  /**
   * Searches through the register and lists up all books by the given author.
   */
  private void listByPublisher()
  {
    System.out.println("Type in the publisher you are looking for: ");
    String publisher = readerStringInput();
    HashSet<Book> publisherBookList = findBooksByPublisher(publisher);
    for (Book book : publisherBookList)
    {
      printBookDetails(book);
    }
    if (publisherBookList.isEmpty())
    {
      System.out.println(
          "> We are sorry to tell you that we do NOT now "
              + "have any books from the following publisher: "
              + publisher
              + "\n>  Please ask the personnel if you'd "
              + "like us to order anything from this publisher.");
    }
  }

  /**
   * Searches through the register and lists up all books by the given series.
   */
  private void listBySeriesName()
  {
    System.out.println("Type in the series you are looking for: ");
    String series = readerStringInput();
    BookSerie bookSerie = getBookSerie(series);
    bookSerie.forEach(book -> printBookDetails((Book) book));
    if (bookSerie.isEmpty())
    {
      System.out.println("> We currently do now have any books from the given series: " + series
          + "\n> If you want, you could ask the personnel if it is possible to order ");
    }
  }

  /**
   * This method removes titles that contains a specified name.
   */
  private void removeBookByAuthor()
  {
    System.out.println("Which author do you want to remove? ");
    String author = readerStringInput();
    boolean found = !findBooksByAuthor(author).isEmpty();
    getBooks().forEachRemaining(book -> removeBookByAuthor(book, author));
    if (found)
    {
      System.out.println("> Successfully removed books from the author: " + author);
    } else if (!found)
    {
      System.out.println("> We are sorry to inform you that we could"
          + " find any books from the author: " + author);
    }
  }

  /**
   * This method removes titles that contains a specified name.
   */
  private void removeBookByTitle()
  {
    System.out.println("Which title do you want to remove? ");
    String title = readerStringInput();
    boolean found = hasThisBook(title);
    getBooks().forEachRemaining(book -> removeBookByTitle(book, title));
    if (found)
    {
      System.out.println("> Successfully removed books from the title: " + title);
    } else if (!found)
    {
      System.out.println("> We are sorry to inform you that we could"
          + " find any books from the title: " + title);
    }
  }

  /**
   * Creates a menu where you decide which literature to remove, and then removes it.
   */
  private void removeLiterature()
  {
    Literature literature = getLiterature();
    if (literature != null)
    {
      printLiteratureDetails(literature);
      System.out.print("Are you sure you want to remove this literature?\n1. NO\n2. YES\n> ");
      int readerInt = getReaderInt(1, 2);
      switch (readerInt)
      {
        case 1:
          break;
        case 2:
          bookRegister.remove(literature);
          printLiteratureDetails(literature);
          System.out.print(" has been removed.");
          if (literature instanceof BookSerie)
          {
            System.out
                .print("Do you want to remove all the books in this series from the register? \n1. YESI \n2. NOI\n> ");
            switch (getReaderInt(1, 2))
            {
              case 1:
                ((BookSerie) literature).getBooks().forEach(book -> bookRegister.remove(book));
                System.out.println("All books withint he series has been removed from the register.");
                break;
              case 2:
                break;
            }
          }
          break;
      }
    } else
    {
      System.out.println("This literature does not exists.");
    }
  }

  /**
   * returns an string imput but prints a comand first.
   *
   * @param command the comand to print.
   * @return a string imput
   */
  private String readerStringInputWithCommand(String command)
  {
    System.out.print(command);
    return readerStringInput();
  }

  // Final migration of our codes

  /**
   * gets an input form the terminal window and then returns it as a string, but only if it has a
   * value.
   *
   * @return returns a string from the terminal window.
   */
  private String readerStringInput()
  {
    reader = new Scanner(System.in);
    String input = reader.nextLine();
    if (!input.isBlank())
    {
      return input;
    } else
    {
      System.out.println("Please enter a value.");
      return readerStringInput();
    }
  }

  /**
   * checks that an input is an integer, that its not empty, that it is between two predetermined
   * values if the system does not recognize the command or if it is outside the parameters the
   * method asks for the correct command. when the correct command is entered the system exits, if
   * the user enters "quit" the system returns 0.
   *
   * @param command a string to be printed when the system starts
   * @param min the minimum value that you can enter
   * @param max the maximum value that you can enter
   * @return an integer that's inside the parameters set. or in the case that the method is quit
   *     returns the minimum value
   */
  private int readerIntInput(String command, int min, int max)
  {
    int returnInt = min - 1;
    while (returnInt == min - 1)
    {
      System.out.print(command);
      returnInt = getReaderInt(min, max);
    }
    return returnInt;
  }

  /**
   * checks that an input is an integer, that its not empty, that it is between two predetermined
   * values if the system does not recognize the command or if it is outside the parameters the
   * method asks for the correct command. When the correct command is entered the system exits, if
   * the user enters "quit" the system returns 0.
   *
   * @param min the minimum value that you can enter
   * @param max the maximum value that you can enter
   * @return an integer that's inside the parameters set. or in the case that the method is quit
   *     returns the minimum value
   */
  private int readerIntInputNoPrint(int min, int max)
  {
    int returnInt = min - 1;
    while (returnInt == min - 1)
    {
      returnInt = getReaderInt(min, max);
    }
    return returnInt;
  }

  /**
   * returns a integer value that's between two numbers.
   *
   * @param min the minimum value
   * @param max the maximum value
   * @return returns a value between the minimum and maximum
   */
  private int getReaderInt(int min, int max)
  {
    int returnInt = min - 1;
    try
    {
      reader = new Scanner(System.in);
      int readerInt = reader.nextInt();
      if (readerInt > min - 1 && readerInt < max + 1)
      {
        returnInt = readerInt;
      } else
      {
        throw new InputMismatchException();
      }
    } catch (Exception e)
    {
      System.out.println(
          "Invalid input.\nThe input must be a number between " + min + " and " + max + ".");
    }
    return returnInt;
  }

  /**
   * Return details of the book: artist, title.
   *
   * @param book the book that you want the details of.
   */
  private void printBookDetails(Book book)
  {
    System.out.println(
        "> Author : " + book.getAuthor()
            + " | Title : " + book.getTitle()
            + " | Publisher: " + book.getPublisher()
            + " | Current edition: " + book.getEdition());
  }

  /**
   * print details of the BookSeries: artist, title, number of books.
   */
  private void printBookSerieDetails(BookSerie bookSerie)
  {
    System.out.println(">Title : " + bookSerie.getTitle()
        + " | Publisher: " + bookSerie.getPublisher()
        + " | Name of the series: " + bookSerie.getTitle()
        + " | number of books: " + bookSerie.size());

    for (Book book : bookSerie.getBooks())
    {
      System.out.println("       > Author : " + book.getAuthor()
          + " | Title : " + book.getTitle()
          + " | Publisher: " + book.getPublisher()
          + " | Current edition: " + book.getEdition());
    }
  }

  /**
   * returns an iterator containing all books in literature.
   *
   * @return an iterator containing all books in literature.
   */
  private Iterator<Book> getBooks()
  {
    HashSet<Book> books = new HashSet<>();
    bookRegister.forEach(lit ->
    {
      if (lit instanceof Book)
      {
        books.add((Book) lit);
      }
    });
    return books.iterator();
  }

  /**
   * returns an iterator containing all books in literature.
   *
   * @return an iterator containing all books in literature.
   */
  private Iterator<Magazine> getMagazines()
  {
    HashSet<Magazine> magazines = new HashSet<>();
    bookRegister.forEach(lit ->
    {
      if (lit instanceof Magazine)
      {
        magazines.add((Magazine) lit);
      }
    });
    return magazines.iterator();
  }

  /**
   * returns an iterator containing all books in literature.
   *
   * @return an iterator containing all books in literature.
   */
  private Iterator<Newspaper> getNewspapers()
  {
    HashSet<Newspaper> newspapers = new HashSet<>();
    bookRegister.forEach(lit ->
    {
      if (lit instanceof Newspaper)
      {
        newspapers.add((Newspaper) lit);
      }
    });
    return newspapers.iterator();
  }

  /**
   * returns an iterator containing all books in literature.
   *
   * @return an iterator containing all books in literature.
   */
  private Iterator<BookSerie> getBookSeries()
  {
    HashSet<BookSerie> bookseries = new HashSet<>();
    bookRegister.forEach(lit ->
    {
      if (lit instanceof BookSerie)
      {
        bookseries.add((BookSerie) lit);
      }
    });
    return bookseries.iterator();
  }

  /**
   * returns an specific book series.
   *
   * @param seriesName the book series you want.
   * @return the books series.
   */
  private BookSerie getBookSerie(String seriesName)
  {
    Iterator<BookSerie> bookSeries = getBookSeries();
    BookSerie returnBookSerie = null;
    boolean found = false;
    while (bookSeries.hasNext() && !found)
    {
      BookSerie bookSerie = bookSeries.next();
      if (bookSerie.getTitle().equalsIgnoreCase(seriesName))
      {
        found = true;
        returnBookSerie = bookSerie;
      }
    }
    return returnBookSerie;
  }

  /**
   * returns a specific peace of literature. that is asked for.
   *
   * @return the piece of literature that you want.
   */
  private Literature getLiterature()
  {
    boolean found = false;
    while (!found)
    {
      System.out.println("What kind of literature?");
      System.out.println("0. Main Menu" + "\n1. Book\n2. Magazine\n3. Book series\n4. Newspaper");
      int inputInt = getReaderInt(0, 4);
      switch (inputInt)
      {
        case 0:
          System.out.println("Going back to Main Menu");
          found = true;
          break;
        case 1:
          System.out.println("Type in book details.");
          return getBook();
        case 2:
          System.out.println("Type in magazine details.");
          return getMagazine();
        case 3:
          System.out.println("Type in book series details.");
          return getBookSerie(readerStringInputWithCommand("Series Name: "));
        case 4:
          System.out.println("Type in newspaper details.");
          return getNewspaper();
        default:
          break;
      }
    }
    return null;
  }

  /**
   * returns a specific newspaper, the function asks for a name and then gives you the possible
   * newspapers that you can get.
   *
   * @return the newspaper you want.
   */
  private Literature getNewspaper()
  {
    String name = readerStringInputWithCommand("Name: ");
    Iterator<Newspaper> newspapers = getNewspapers();
    boolean quit = false;
    while (newspapers.hasNext() && !quit)
    {
      Newspaper newspaper = newspapers.next();
      if (newspaper.getName().equalsIgnoreCase(name))
      {
        System.out.println("is this the newspaper you wanted?\n0. quit\n1. yes\n2. no\n>");
        int readerInt = getReaderInt(0, 2);
        switch (readerInt)
        {
          case 0:
            quit = true;
            break;
          case 1:
            return newspaper;
          case 2:
            break;
        }
      }
    }
    return null;
  }

  /**
   * returns a specific book, the function asks for a title and an author and then gives you the
   * possible books that you can get.
   *
   * @return the book you want.
   */
  private Book getBook()
  {
    String title = readerStringInputWithCommand("Title: ");
    String author = readerStringInputWithCommand("Author: ");
    Iterator<Book> books = getBooks();
    boolean quit = false;
    while (books.hasNext() && !quit)
    {
      Book book = books.next();
      if (book.getAuthor().equalsIgnoreCase(author) && book.getTitle().equalsIgnoreCase(title))
      {
        printBookDetails(book);
        System.out.println("Is this the book you wanted?\n0. QUIT\n1. YES\n2. NO\n> ");
        int readerInt = getReaderInt(0, 2);
        switch (readerInt)
        {
          case 0:
            quit = true;
            break;
          case 1:
            return book;
          case 2:
            break;
        }
      }
    }
    return null;
  }


  /**
   * returns a specific book, the function asks for a title and an author and then gives you the
   * possible books that you can get.
   *
   * @return the book you want.
   */
  private Magazine getMagazine()
  {
    String title = readerStringInputWithCommand("Title: ");
    String name = readerStringInputWithCommand("Name: ");
    Iterator<Magazine> magazines = getMagazines();
    boolean quit = false;
    while (magazines.hasNext() && !quit)
    {
      Magazine magazine = magazines.next();
      if (magazine.getName().equalsIgnoreCase(name) && magazine.getTitle()
          .equalsIgnoreCase(title))
      {
        System.out.println("is this the magazine you wanted?\n0. quit\n1. yes\n2. no\n>");
        int readerInt = getReaderInt(0, 2);
        switch (readerInt)
        {
          case 0:
            quit = true;
            break;
          case 1:
            return magazine;
          case 2:
            break;
        }
      }
    }
    return null;
  }

  /**
   * checks that the books array has a book with a given title.
   *
   * @param titleOfBook the name of the series.
   * @return true if the book exist, false otherwise.
   */
  private boolean hasThisBook(String titleOfBook)
  {
    boolean check = false;
    for (Literature lit : bookRegister.getArray())
    {
      if (lit instanceof Book)
      {
        Book book = (Book) lit;
        if (book.getTitle().equalsIgnoreCase(titleOfBook))
        {
          check = true;
        }
      }
    }
    return check;
  }

  /**
   * checks that the books array has a magazine with a given title.
   *
   * @param titleOfMagazine the name of the magazine.
   * @return true if the magazine exist, false otherwise.
   */
  private boolean hasThisMagazine(String titleOfMagazine)
  {
    boolean check = false;
    for (Literature lit : bookRegister.getArray())
    {
      if (lit instanceof Magazine)
      {
        Magazine magazine = (Magazine) lit;
        if (magazine.getTitle().equalsIgnoreCase(titleOfMagazine))
        {
          check = true;
        }
      }
    }
    return check;
  }

  /**
   * returns a set of books with a sertan title.
   *
   * @param title the title of the books you want have.
   * @return a set of books with the title you want.
   */
  private HashSet<Newspaper> findNewspaperByName(String title)
  {
    HashSet<Newspaper> newspaper = new HashSet<>();
    Iterator<Newspaper> newspaperIterator = getNewspapers();
    while (newspaperIterator.hasNext())
    {
      Newspaper it = newspaperIterator.next();
      if ((it.getName().toLowerCase().contains(title.toLowerCase())))
      {
        newspaper.add(it);
      }

    }
    return newspaper;
  }


  /**
   * returns a set of books with a sertan author.
   *
   * @param author the title of the books you want have.
   * @return a set of books with the author you want.
   */
  private HashSet<Book> findBooksByAuthor(String author)
  {
    HashSet<Book> books = new HashSet<Book>();
    Iterator<Book> bookIterator = getBooks();
    while (bookIterator.hasNext())
    {
      Book it = bookIterator.next();
      if ((it.getAuthor().toLowerCase().contains(author.toLowerCase())))
      {
        books.add(it);
      }
    }
    return books;
  }

  /**
   * returns a set of books with a sertan title.
   *
   * @param title the title of the books you want have.
   * @return a set of books with the title you want.
   */
  private HashSet<Book> findBooksByTitle(String title)
  {
    HashSet<Book> books = new HashSet<Book>();
    Iterator<Book> bookIterator = getBooks();
    while (bookIterator.hasNext())
    {
      Book it = bookIterator.next();
      if ((it.getTitle().toLowerCase().contains(title.toLowerCase())))
      {
        books.add(it);
      }
    }
    return books;
  }

  /**
   * returns a set of books by a sertan publisher.
   *
   * @param publisher the publisher of the books you want to have.
   * @return a set of books by the publisher you want.
   */
  private HashSet<Book> findBooksByPublisher(String publisher)
  {

    HashSet<Book> books = new HashSet<Book>();
    Iterator<Book> bookIterator = getBooks();
    while (bookIterator.hasNext())
    {
      Book it = bookIterator.next();
      if ((it.getPublisher().toLowerCase().contains(publisher.toLowerCase())))
      {
        books.add(it);
      }
    }
    return books;

  }

  /**
   * removes a book if the author provided matches the author of the book.
   *
   * @param lit the book to remove
   * @param author the author that you want to check if the book contains.
   */
  private void removeBookByAuthor(Literature lit, String author)
  {
    Book book = (Book) lit;
    if (book.getAuthor().equalsIgnoreCase(author))
    {
      bookRegister.remove(book);
    }
  }

  /**
   * removes a book if the title provided matches the title of the book.
   *
   * @param lit the book to remove
   * @param title the title that you want to check if the book contains.
   */
  private void removeBookByTitle(Literature lit, String title)
  {
    Book book = (Book) lit;
    if (book.getTitle().equalsIgnoreCase(title))
    {
      bookRegister.remove(book);
    }
  }

  /**
   * <p>
   * This is the third version of the demo that adds a bunch of books where some of these books are
   * series and some are not.
   * </p>
   * The following parameters are:
   * <ul>
   * <li>Author
   * <li>Title
   * <li>Publisher
   * <li>Name of the series (If given name)
   * </ul>
   */
  private void NEW_REGISTER_DEMO()
  {
    this.bookRegister
        .addLiterature(new Book("Kjellhaug", "Kjellen's Hevn", "BIBELSELSKAPET", 1));
    this.bookRegister.addLiterature(new Book("J.K. Rullings", "Harru Pott'æd", "ARNE ER BEST", 1));
    this.bookRegister.addLiterature(new Book("Soppløs", "Korleis gje Kevin blåauge", "Markus", 69));

    //  THE BOOKS BELOW SHOULD BE SERIES
    BookSerie arnegårmotdøra = new BookSerie("Arne Går Mot Døra", "CAPPEOGDAM");
    Book arne1 = new Book("Martin", "Arne går mot døra"
        , "CAPPEOGDAM", 1);
    Book arne2 = new Book("Martin", "Arne går mot døra 2"
        , "CAPPEOGDAM", 1);
    Book arne3 = new Book("Martin", "Arne går mot døra 3"
        , "CAPPEOGDAM", 1);
    Book arne4 = new Book("Martin", "Arne går mot døra 4"
        , "CAPPEOGDAM", 1);
    this.bookRegister.addLiterature(arne1);
    this.bookRegister.addLiterature(arne2);
    this.bookRegister.addLiterature(arne3);
    this.bookRegister.addLiterature(arne4);
    this.bookRegister.addLiterature(arnegårmotdøra);
    arnegårmotdøra.addBook(arne1);
    arnegårmotdøra.addBook(arne2);
    arnegårmotdøra.addBook(arne3);
    arnegårmotdøra.addBook(arne4);

    BookSerie vindusvaskeren = new BookSerie("Vindusvaskerens Frykt", "ARNE");
    Book vin1 = new Book("J.K. Rullings", "Skitne Vindauge", "ARNE", 1);
    Book vin2 = new Book("J.K. Rullings", "Det Skumle Vindauge", "ARNE", 1);
    Book vin3 = new Book("J.K. Rullings", "Det Reine Vindauge", "ARNE", 1);
    this.bookRegister.addLiterature(vin1);
    this.bookRegister.addLiterature(vin2);
    this.bookRegister.addLiterature(vin3);
    this.bookRegister.addLiterature(vindusvaskeren);
    vindusvaskeren.addBook(vin1);
    vindusvaskeren.addBook(vin2);
    vindusvaskeren.addBook(vin3);

    //  ADD MAGAZINES BELOW

    this.bookRegister.addLiterature(
        new Magazine("Bonnier Publications International A/S", "FORELSKELSE stresser hjernen",
            "Illustrert Vitenskap", "00.00.00", 420));
    this.bookRegister.addLiterature(
        new Magazine("ATEISTSELSKAPET", "DEMONSKE FORHANDLINGER", "Annulert Satanskap",
            "66.66.6666", 666));

    // ADD NEWSPAPERSE BELOW

    this.bookRegister.addLiterature(new Newspaper("VG", "MEGSELV", "18.81.no", 69));
    this.bookRegister.addLiterature(new Newspaper("AFTONPOSTÆN", "ARNE", "arnesNummerHer", 69));
  }
}