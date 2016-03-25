/**
 * Project #2 CS 2334, Section 12 February 19, 2016
 * <P>
 * The BroadcastSeries class contains all the needed information in order to
 * change, access and compare the class information.
 * </P>
 * @ version 1.1
 */
public class Episodes extends Media {
	/** Stores the series of the episode as a string */
	private String series;

	/** Stores the starting year the episode was made as a String. */
	private String startingYear;

	/** Stores the season the episode was released in as a String. */
	private String season;

	/** Stores the episode number the episode was released in as a String. */
	private String episodeNumber;

	/**
	 * Stores "UNSPECIFIED" if the episode release year is ????, otherwise
	 * contains ""
	 */
	private String unspecified;

	/** Stores "SUSPENDED" if the episode was suspended */
	private String suspended;

	/**
	 * Default constructor for the class
	 */
	public Episodes() {
		series = "";
		startingYear = "";
		releaseYear = 0;
		title = "";
		season = "";
		episodeNumber = "";
		unspecified = "";
		suspended = "";
	}

	/**
	 * Accessor gets the specified series
	 * <P>
	 * 
	 * @return series of which the episode belongs to
	 */
	public String getSeries() {
		return this.series;
	}

	/**
	 * Gets the specified starting year of an series
	 * <P>
	 * 
	 * @return startingYear of a series
	 */
	public String getStartingYear() {
		return this.startingYear;
	}

	/**
	 * Gets the specified season of an series
	 * <P>
	 * 
	 * @return season of a series
	 */
	public String getSeason() {
		return this.season;
	}

	/**
	 * Gets the specified episode number of an series
	 * <P>
	 * 
	 * @return the episode number of a series
	 */
	public String getEpisodeNumber() {
		return this.episodeNumber;
	}

	/**
	 * Gets either "UNSPECIFIED" or "" depending on whether the year of the
	 * episode is unspecified or not
	 * 
	 * @return "UNSPECIFIED" or ""
	 */
	public String getUnspecified() {
		return this.unspecified;
	}

	/**
	 * Gets either "SUSPENDED" or "" depending on whether the episode was
	 * suspended
	 * 
	 * @return "SUSPENDED" or ""
	 */
	public String getSuspended() {
		return this.suspended;
	}

	/**
	 * Mutator sets the specified series
	 * <P>
	 */
	public void setSeries(String series) {
		this.series = series;
		return;
	}

	/**
	 * Sets the specified starting year of an series
	 * <P>
	 * 
	 * @param year
	 */
	public void setStartingYear(String year) {
		this.startingYear = year;
		return;
	}

	/**
	 * Sets the specified released year of an series
	 * <P>
	 * 
	 * @param year
	 *            the year a series was released
	 */

	// Unnecessary with media Extension
	/*
	 * public void setReleaseYear(Integer year) { this.releaseYear = year;
	 * return; }
	 */

	/**
	 * Sets the specified title of an series
	 * <P>
	 * 
	 * @param title
	 *            the title of the episode
	 */

	// unnecessary with meida extension
	/*
	 * public void setEpisodeTitle(String title) { this.title = title; return; }
	 */

	/**
	 * Sets the specified season of an series
	 * <P>
	 * 
	 * @param season
	 *            which season the episode was aired in
	 */
	public void setSeason(String season) {
		this.season = season;
		return;
	}

	/**
	 * Sets the specified episode number of an series
	 * <P>
	 * 
	 * @param episodeNumber
	 *            where the episode was aired within the season
	 */
	public void setEpisodeNumber(String episodeNumber) {
		this.episodeNumber = episodeNumber;
		return;
	}

	/**
	 * Sets either "UNSPECIFIED" or "" to episode's unspecified field
	 * <P>
	 * 
	 * @param String
	 *            "UNSPECIFIED" or ""
	 */
	public void setUnspecified(String unspecified) {
		this.unspecified = unspecified;
	}

	/**
	 * sets either "SUSPENDED" or "" to episodes suspended field
	 * <P>
	 * 
	 * @param String
	 *            "SUSPENDED" or ""
	 */
	public void setSuspended(String suspended) {
		this.suspended = suspended;
	}

	/**
	 * Compares two starting years to each other
	 * <P>
	 * 
	 * @return
	 */
	public int compareSYear(String year) {
		return this.startingYear.compareToIgnoreCase(year);
	}

	/**
	 * Compares two release years to each other
	 * <P>
	 * 
	 * @param year
	 *            the year a series was released
	 * @return
	 */
	public int compareRYear(Integer year) {
		return this.releaseYear.compareTo(year);
	}

	/**
	 * Compares two episode titles to each other
	 * <P>
	 * 
	 * @param title
	 *            the title of the episode
	 * @return
	 */
	public int compareETitle(String title) {
		return this.title.compareToIgnoreCase(title);
	}

	/**
	 * Compares two seasons to each other
	 * <P>
	 * 
	 * @param season
	 *            the season of a series
	 * @return
	 */
	public int compareSeason(String season) {
		return this.season.compareToIgnoreCase(season);
	}

	/**
	 * Compares two episode numbers to each other
	 * <P>
	 * 
	 * @param episodeNumber
	 *            where the episode was aired within the season
	 * @return
	 */
	public int compareENumber(String episodeNumber) {
		return this.episodeNumber.compareToIgnoreCase(episodeNumber);
	}

	// use only to test, not needed in final form
	/*
	 * public String toString() { String str = "\n Series: " + this.series +
	 * "\n Year the series was first released: " + this.startingYear +
	 * "\n Episode Title: " + this.title;
	 * if(this.unspecified.equals("unspecified")){ str = str +
	 * "\n Year the episode was released: " + this.unspecified +
	 * "\n Season the episode comes from: " + this.season +
	 * "\n Episode number from the season: " + this.episodeNumber; }else{ str =
	 * str + "\n Year the episode was released: " + this.releaseYear +
	 * "\n Season the episode comes from: " + this.season +
	 * "\n Episode number from the season: " + this.episodeNumber; } return str;
	 * }
	 */

	/**
	 * This returns all info relevant to an episode as a string
	 * 
	 * @return EPISODE: seriesTitle title (year)
	 */
	@Override
	public String toString() {
		String str = "EPISODE: " + this.title  + " \"" + this.series + "\" ";

		if (this.unspecified.equals("UNSPECIFIED")) {
			str +=  "(" + this.unspecified + ") " + this.suspended;
		} else {
			str +=  "(" + this.releaseYear + ") " + this.suspended;
		}
		return str;
	}
}
