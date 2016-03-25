import java.util.ArrayList;

/**
 * <p>
 * The Series method contains the general information about a TV series.
 * </p>
 * <p>
 * Outside the general Media Title and ReleaseYear methods, the Series class
 * details the endYear of a series (if there is one), whether it was canceled
 * before airing, and all of it episodes (not necessarily sorted).
 * </p>
 * 
 * 
 * @version 1.0
 *
 */
public class Series extends Media {

	/** Stores the endYear of the Series */
	private Integer endYear;
	/** Stores an Array of all Episodes in a Series */
	private ArrayList<Episodes> allEpisodes;
	/** Stores ???? if the year is unknown */
	private String unspecified;
	/** Stores a String if the Series never aired or canceled */
	private String suspended;

	/**
	 * <p>
	 * This constructor takes all the information possible for a Series and
	 * stores it. For any information not available, the value will be stored as
	 * null.
	 * </P>
	 * <p>
	 * For example, if an endYear is unknown, the endYear will be null, and
	 * unspecified will store ???? instead, but the value is still available for
	 * comparison.
	 * </p>
	 * 
	 * @param title-
	 *            A String of the title of the Series
	 * @param releaseYear
	 *            - An Integer of the first year the Series aired.
	 * @param endYear
	 *            - an Integer of the last year a Series aired, if available.
	 *            Null otherwise
	 * @param allEpisodes-
	 *            an ArrayList of All episodes in a Series, suspended or
	 *            otherwise.
	 * @param unspecified-
	 *            a String of ???? if an endYear is unknown
	 * @param suspended-
	 *            A string of SUSPENDED if the series was canceled or never made
	 */

	public Series(String title, Integer releaseYear, Integer endYear, String unspecified, String suspended) {
		this.title = title;
		this.releaseYear = releaseYear;
		this.endYear = endYear;
		this.allEpisodes = null;
		this.unspecified = unspecified;
		this.suspended = suspended;
	}

	/**
	 * This method will take a string and parse it into a Series object
	 * 
	 * @param String
	 *            to be parsed with format "title" (year) year-year
	 * @return void
	 */
	public static Series parseNewSeries(String line) {
		Series series = new Series(parseSeriesTitle(line), parseSeriesRelease(line), parseSeriesEnd(line),
				parseSeriesUnspecified(line), parseSeriesSuspended(line));
		return series;
	}

	/**
	 * This method will take a string and parse the title sans ""
	 * 
	 * @param String
	 *            to be parsed with format "title" (year) year-year
	 * @return void
	 */
	public static String parseSeriesTitle(String line) {
		return line.substring(line.indexOf('"') + 1, line.lastIndexOf('"'));
	}

	/**
	 * This method will take a string and parse the release year of a series
	 * 
	 * @param String
	 *            to be parsed with the format "title" (year) year-year
	 * @return void
	 */
	public static Integer parseSeriesRelease(String line) {
		String release = line.substring(line.lastIndexOf("(") + 1, line.lastIndexOf(")"));
		return Integer.decode(release);
	}

	/**
	 * This method will take a string and parse the end year of a series
	 * 
	 * @param String
	 *            to be parsed with the format "title" (year) year-year
	 * @return void
	 */
	public static Integer parseSeriesEnd(String line) {
		String end = line.substring(line.lastIndexOf('-') + 1);
		if (end.equals("????")) {
			return 0;
		} else {
			return Integer.decode(end);
		}
	}

	/**
	 * This method will take a string and parse whether ????(unspecified) occurs
	 * 
	 * @param String
	 *            to be parsed with the format "title" (year) year-????
	 * @return String "unspecified" or ""
	 */
	public static String parseSeriesUnspecified(String line) {
		String end = line.substring(line.lastIndexOf('-') + 1);
		if (end.equals("????")) {
			return "Unspecified";
		} else {
			return "";
		}
	}

	/**
	 * This method will take a string and parse whether or not a series has been
	 * suspended
	 * 
	 * @param String
	 *            to be parsed that may contain {{suspended}}
	 * @return void
	 */
	public static String parseSeriesSuspended(String line) {
		if (line.contains("SUSPENDED")) {
			return "SUSPENDED";
		}
		return "";
	}

	/**
	 * This will return all stored Episodes, sorted or not, into an ArrayList of
	 * Episodes
	 * 
	 * @return an unsorted (most likely) ArrayList of Episodes
	 */
	public ArrayList<Episodes> getAllEpisodes() {
		return this.allEpisodes;
	}

	/**
	 * This method will return the last Year a Series aired on TV
	 * 
	 * @return an Integer representing the final year of a Series
	 */
	public Integer getEndYear() {
		return this.endYear;
	}

	/**
	 * <P>
	 * This method will return a string of ???? if the releaseYear is null.
	 * 
	 * @return a String of ????
	 */
	public String getUnspecified() {
		return this.unspecified;
	}

	/**
	 * <p>
	 * This method returns a String of SUSPENDED if the Series was indeed
	 * suspended.
	 * </p>
	 * 
	 * @return a String of SUSPENDED
	 */
	public String getSuspended() {
		return this.suspended;
	}

	/**
	 * This method will set the ArrayList of episodes to a series object
	 * 
	 * @param arraylist
	 *            to be added
	 * @return void
	 */
	public void setAllEpisodes(ArrayList<Episodes> allEpisodes) {
		this.allEpisodes = allEpisodes;
		// TODO set up how to get the episodes out of the master ArrayList
		// TODO put AllEpisodes into matching Series object
		// TODO find out if series or episodes are parsed first
	}

	/**
	 * This method will set the endYear, the last year an episode aired, for a
	 * Series.
	 * 
	 * @param year
	 *            an Integer value of the final year of a show
	 */
	public void setEndYear(Integer year) {
		this.endYear = year;
	}

	/**
	 * This method will set a String of ???? if the end Year of a Series is
	 * unknown.
	 * 
	 * @param unspecified
	 *            a String of ????
	 */
	public void setUnspecified(String unspecified) {
		this.unspecified = unspecified;
	}

	/**
	 * <p>
	 * This method will set a String value of SUSPENDED to the suspended
	 * variable.
	 * 
	 * @param suspended
	 *            - a String value of SUSPENDED
	 */
	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}

	/**
	 * This method will return a String representation of all relevant
	 * information in a series.
	 * 
	 * @return a String of all relevant information in a Series
	 */
	@Override
	public String toString() {
		String str = "SERIES: \"" + this.getTitle() + "\" (" + Integer.toString(this.getReleaseYear()) + ") "
				+ Integer.toString(this.getReleaseYear()) + "-";
		if (Integer.hashCode(this.getEndYear()) != 0) {
			return str + Integer.toString(this.getEndYear()) + " " + this.getSuspended();
		} else {
			return str + this.getUnspecified() + " " + this.getSuspended();
		}
	}
}