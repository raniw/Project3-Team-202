import java.util.Comparator;

/**
 * <h2>Media class</h2>
 * 
 * <p>
 * This Class works as a general identifier of all entertainment mediums.
 * <p>
 * For every medium, there is a Title and ReleaseYear, and these are the basic
 * variables in which all media can be sorted or compared to. This is why Media
 * implements both Comparator and Comparable, to allow all specific Media
 * classes, such as Movie, Series and Episode, to compare to each other when in
 * a large Media Database.
 * </p>
 * 
 * 
 * 
 * @version 1.0
 */
public class Media implements Comparator<Media>, Comparable<Media> {

	/** Stores the title of the Media */
	public String title;
	/** Stores the release year of the Media */
	public Integer releaseYear;

	/**
	 * This method accesses the title of Media and returns it.
	 * 
	 * @return a String of the Title
	 */
	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	/**
	 * <p>
	 * This method returns the Release Year in an Integer Object.
	 * </p>
	 * 
	 * <p>
	 * If the Media has an unknown Year ( i.e. "????"), it will return the value
	 * null.
	 * </p>
	 * 
	 * @return an Integer object of the releaseYear
	 */
	public Integer getReleaseYear() {
		// TODO Auto-generated method stub
		return this.releaseYear;
	}

	/**
	 * This method sets the title of a Media Object.
	 * 
	 * @param title
	 *            - a String of the title
	 */
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		this.title = title;
	}

	/**
	 * This method sets the releaseYear of a Media Object.
	 * 
	 * @param releaseYear
	 *            - an Integer object of the releaseYear
	 * 
	 */
	public void setReleaseYear(Integer releaseYear) {
		// TODO Auto-generated method stub
		this.releaseYear = releaseYear;
	}

	public static Comparator<Media> media = new Comparator<Media>() {

		/**
		 * <p>
		 * This method compares the titles of the Media, then if the titles are
		 * equal, compares them by releaseYear.
		 * </p>
		 * <p>
		 * The method will return a negative integer the first release Year is
		 * numerically lower.
		 * </p>
		 * <p>
		 * The method will return zero release years are equal.
		 * </p>
		 * <p>
		 * The method will return a positive integer the release year is
		 * numerically higher.
		 * </p>
		 */
		@Override
		public int compare(Media o1, Media o2) {
			String obj1 = o1.getTitle().toUpperCase();
			String obj2 = o2.getTitle().toUpperCase();
			return obj1.compareTo(obj2);
		}
	};

	/**
	 * <p>
	 * This method compares the titles of the Media, then if the titles are
	 * equal, compares them by releaseYear.
	 * </p>
	 * <p>
	 * The method will return a negative integer if the first title is first
	 * alphabetically.
	 * </p>
	 * <p>
	 * The method will return zero if the titles and release years are equal.
	 * </p>
	 * <p>
	 * The method will return a positive integer if the first title is last
	 * alphabetically.
	 * </p>
	 */
	@Override
	public int compareTo(Media o) {
		// TODO Auto-generated method stub

		return this.releaseYear.compareTo(o.getReleaseYear());
	}

	@Override
	public int compare(Media o1, Media o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}