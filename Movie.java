/**
 * <p>
 * This class covers all the specifics of a singular Movie Object.
 * </p>
 * 
 * <p>
 * It contains multiple constructors for the various types of movies.
 * </p>
 * 
 * <p>
 * This class uses multiple data types, including Strings, Integers and int
 * values.
 * </p>
 * 
 * 
 * @version 1.0
 *
 */

public class Movie extends Media {

	/** Stores ???? if releaseYear is unknown */
	private String unknownYear;
	/** Stores the duplicate/version of the Movie */
	private int duplicate;
	/** Stores the format of the Movie */
	private String format;

	// TODO change doc comments to show the null and why?
	/**
	 * <p>
	 * First Standard Movie constructor
	 * </p>
	 * 
	 * <p>
	 * Used when the Title and releaseYear are the only available info in the
	 * file.
	 * </p>
	 * 
	 * @param title
	 *            - a String of the full title
	 * @param releaseYear
	 *            - an Integer object of the year released
	 * 
	 */
	public Movie(String title, Integer releaseYear) {
		/*
		 * Assigns all the given values, and also gives empty or null values to
		 * not used values, so if a user tries to get information not given, an
		 * error will not occur.
		 */
		this.title = title;
		this.releaseYear = releaseYear;
		this.unknownYear = "";
		this.duplicate = 0;
		this.format = "";
	}// end first constructor

	/**
	 * Secondary constructor, only if the releaseDate unknown
	 * 
	 * @param title
	 *            - a String of the full title
	 * @param unknown
	 *            - another String of "????" specifically
	 */
	public Movie(String title, String unknown) {
		/*
		 * Assigns all the given values, and also gives empty or null values to
		 * not used values, so if a user tries to get information not given, an
		 * error will not occur.
		 */
		this.title = title;
		this.unknownYear = unknown;
		this.releaseYear = 0;
		this.format = "";
		this.duplicate = 0;
	}// end Constructor

	/**
	 * <p>
	 * Secondary constructor if in addition to Title and ReleaseYear, there are
	 * two films or the same name are released in the same year.
	 * </p>
	 * 
	 * <p>
	 * Assigns a duplicate number (int) from a Roman Numeral to the Movie Object
	 * </p>
	 * 
	 * @param title
	 *            - a String of the full title
	 * @param releaseYear
	 *            - an Integer object;
	 * @param duplicate
	 *            - an int
	 */
	public Movie(String title, Integer releaseYear, int duplicate) {
		/*
		 * Assigns all the given values, and also gives empty or null values to
		 * not used values, so if a user tries to get information not given, an
		 * error will not occur.
		 */

		this.title = title;
		this.releaseYear = releaseYear;
		this.duplicate = duplicate;
		this.unknownYear = "";
		this.format = "";

	}// end Constructor

	/**
	 * <p>
	 * Secondary constructor if in addition to Title and ReleaseYear, the film
	 * was NOT released in theaters.
	 * </p>
	 * <p>
	 * Example formats:
	 * </p>
	 * 
	 * <p>
	 * (TV) for television
	 * </p>
	 * <p>
	 * (V) for direct-to-video release
	 * </p>
	 * 
	 * @param title
	 *            - a String of the full title
	 * @param releaseYear
	 *            - an Integer object,
	 * @param format
	 *            - a String of the format given
	 */
	public Movie(String title, Integer releaseYear, String format) {
		/*
		 * Assigns all the given values, and also gives empty or null values to
		 * not used values, so if a user tries to get information not given, an
		 * error will not occur.
		 */

		this.title = title;
		this.releaseYear = releaseYear;
		this.format = format;
		this.unknownYear = "";
		this.duplicate = 0;

	}// end Constructor

	/**
	 * <p>
	 * Most complicated constructor of the class.
	 * </p>
	 * <p>
	 * This constructor is created, if the film shares a duplicate name with
	 * another film of the same ReleaseYear, and was released in either TV or
	 * Video.
	 * </p>
	 * 
	 * @param title
	 *            - a String of the full title
	 * @param releaseYear
	 *            - an Integer object
	 * @param duplicate
	 *            - an int
	 * @param format
	 *            - a String
	 */

	public Movie(String title, Integer releaseYear, int duplicate, String format) {
		/*
		 * Assigns all the given values, and also gives empty or null values to
		 * not used values, so if a user tries to get information not given, an
		 * error will not occur.
		 */

		this.title = title;
		this.releaseYear = releaseYear;
		this.duplicate = duplicate;
		this.format = format;
		this.unknownYear = "";

	}

	/**
	 * <p>
	 * This method retrieves the version of movie if more than one title of same
	 * name in a year.
	 * </p>
	 * <p>
	 * If the Movie does not have a duplicate, it returns 0.
	 * </p>
	 * 
	 * @return an int value representing the version, or zero if none is
	 *         available.
	 */

	public int getDuplicate() {

		if (this.duplicate > 0) {

			return this.duplicate;
		} else {

			System.out.println("This film has no duplicates.");
			return 0;
		}
	}

	/**
	 * This method retrieves the Format if the Movie wasn't released in
	 * theaters, and returns null if there is not a format available.
	 * 
	 * 
	 * @return a String of the format, or null if none available
	 */

	public String getFormat() {

		if (this.format.equals("(TV)") || this.format.equals("(V)")) {

			return this.format;
		} else {

			return null;
		}

	}

	/**
	 * This method returns ???? if releaseYear is unknown, or an empty String if
	 * the year is known.
	 *
	 * @return a String of ????, or an empty String if year is known
	 * 
	 */

	public String getUnknownYear() {

		if (this.unknownYear.equals("????")) {

			return "????";
		} else {

			return "";
		}
	}

	/**
	 * This method prints all available information, including title,
	 * duplicate(if applicable), format(if applicable), release Year and returns
	 * a String of all the information.
	 * 
	 * @return a String of all of a Movie's available information.
	 */
	public String toString() {
		// TODO: write print method for all available info
		String all = "MOVIE";
		if (!this.format.equals("")) {
			all += "(" + this.format + ")";
		}
		all += ": " + this.title + " (" + this.releaseYear + ")";
		return all;
	}

	/**
	 * This method sets an int number if there is a movie of the same title and
	 * release year.
	 * 
	 * @param number
	 *            - an int representing the version of this movie
	 * 
	 */

	public void setDuplicate(int number) {

		duplicate = number;
	}

	/**
	 * This method sets a format version of (TV) or (V) string if a film is NOT
	 * released in theaters.
	 * 
	 * @param videoType
	 *            - a String representing the video format of the film.
	 * 
	 */

	public void setFormat(String videoType) {

		format = videoType;

	}

	/**
	 * This method sets a String of questions marks to the unknownYear.
	 * 
	 * @param unknownYear
	 *            - a String of ????
	 */
	public void setUnknownYear(String unknownYear) {

		this.unknownYear = unknownYear;
	}
}