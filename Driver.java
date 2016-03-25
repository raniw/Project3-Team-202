import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.lang.Iterable;

public class Driver {

	/**
	 * Film ArrayList allows the List of films to pass through all methods
	 */
	public static ArrayList<Movie> films = new ArrayList<Movie>();
	/**
	 * Series ArrayList allows the List of TV series to pass through all methods
	 */
	public static ArrayList<Series> series = new ArrayList<Series>();

	public static ArrayList<Episodes> episodes = new ArrayList<Episodes>();

	/**
	 * <p>
	 * This is the Main Method of the program. The code takes a file in the
	 * arguments of the project, and assigns it to a String fileName. The String
	 * is made into a FileReader (file), then BufferedReader for more reliable
	 * reading of lines.
	 * </p>
	 * 
	 * <p>
	 * Through a loop, the BufferedReader then reads a line of the text, makes
	 * it into a String, and then is parsed in the Parsing method, returning as
	 * a either a Movie or Series object.
	 * </p>
	 * 
	 * <p>
	 * The Objects are then added into their own ArrayLists, with episodes being
	 * stored in their own ArrayList in a different method. After all lines of
	 * text have been read and parsed, the Search method allows for a user to
	 * search through the file for specific Media, either by title, year, and/or
	 * medium .
	 * </p>
	 * 
	 * <p>
	 * Users may search multiple times.
	 * </p>
	 * 
	 * @param args
	 *            - arguments of the file
	 * @throws IOException
	 *             - thrown in case there is an error in the File
	 */
	public static void main(String[] args) throws IOException {
		// TODO: create general flow of the program,
		/*
		 * REQUIRED: File & Buffered Reader File & Buffered WRITER Lastly, the
		 * Input of User through another Buffered Reader and System.in
		 */
		// String dataFile = args[0];

		FileReader fileReader = new FileReader("StarTrekshow.txt");
		BufferedReader buffReader = new BufferedReader(fileReader);
		String line;
		while (buffReader.ready()) {
			line = buffReader.readLine();
			if (line.charAt(line.length() - 5) == '-') {
				Series addThis = parseSeries(line);
				series.add(addThis);
			} else {
				Episodes addThis = parseEpisode(line);
				episodes.add(addThis);
			}
		}
		buffReader.close();

		addEpsToSeries();

		FileReader fileReader2 = new FileReader("StarTrekFile.txt");
		BufferedReader buffReader2 = new BufferedReader(fileReader2);
		String line2;
		while (buffReader2.ready()) {
			line2 = buffReader2.readLine();
			Movie movieThing = parseMovie(line2);
			films.add(movieThing);
		}

		buffReader2.close();

		BufferedReader quitOption = new BufferedReader(new InputStreamReader(System.in));
		String all = search(quitOption);
		System.out.println(all);
		// BufferedReader quitOption = new BufferedReader(new
		// InputStreamReader(System.in) );
		System.out.println("Would you like to Search again? (y/n)");
		String quitInput = quitOption.readLine();

		while (quitInput.equalsIgnoreCase("y")) {
			all += "\n" + search(quitOption);
			System.out.println(all);
			System.out.println("Would you like to Search again? (y/n)");
			quitInput = quitOption.readLine();
		}
		System.out.println("Would you like to save your search(es)? (y/n)");
		quitInput = quitOption.readLine();
		if (quitInput.equalsIgnoreCase("y")) {
			FileWriter readToFile = new FileWriter("output.txt");
			BufferedWriter bw = new BufferedWriter(readToFile);
			bw.write(all);
			bw.newLine();
			bw.close();
			System.out.println("Your file is found at output.txt. Goodbye");
		}

		else {
			System.out.println("Goodbye.");
			quitOption.close();
		}

	}

	/**
	 * This method will read the BufferedReader into one individual line.
	 * 
	 * @param List
	 *            - a BufferedReader containing all movies to be parsed
	 * @return newLine - a String containing the information of one Movie
	 */
	/**
	 * This method will read the BufferedReader into one individual line.
	 * 
	 * @param movieList
	 *            - a BufferedReader containing all movies to be parsed
	 * @return newLine - a String containing the information of one Movie
	 * @throws IOException
	 *             - thrown in case there is an error in the File
	 */
	public static String makeLine(BufferedReader movieList) throws IOException {
		// TODO: method to create a string for Each line!
		String newLine = movieList.readLine();
		return newLine;
	}

	/**
	 * <p>
	 * This method will parse through a line of text containing information
	 * about movies, determining whether there are duplicates, if a line
	 * contains a non-theater release format, along with the title and release
	 * year.
	 * </p>
	 * 
	 * @param line
	 *            - a String containing all of the text information of a movie
	 * 
	 * @return Movie - a Movie Object with all information parsed into different
	 *         parameters
	 */

	public static Movie parseMovie(String line) {
		// TODO: use while and for methods to parse lines of File
		/*
		 * Declare all instance variable for use throughout the method.
		 */

		Movie film;// readies the Movie object to be returned

		String title = "";// an Empty string to be changed later

		Integer releaseYear = new Integer(0);// set to zero to be changed later

		String format = "";// set to an Empty string to possibly be changed
							// later

		int version = 0;// set to zero to possibly be changed later

		// StringBuilder takes the given line of String and creates a
		// StringBuilder Object
		// This allows for the String Line to be reversed, making the parsing
		// easier.

		StringBuilder reverseLine = new StringBuilder(line);

		reverseLine.reverse();// now line is reversed

		// Begin parsing

		// The first portion of code starts by asking if the line is an
		// Unknown Release Year movie, which is the easiest to identify

		if (reverseLine.substring(0, 4).equalsIgnoreCase("????")) {

			String unknownYear = reverseLine.substring(0, 4);

			// Ints of TitleIndexand determine the end and beginning of the
			// Title

			int endTitleIndex = reverseLine.length();

			int beginTitleIndex = reverseLine.lastIndexOf("(") + 1;

			/*
			 * Declaring a new StringBuilder to read the title left-to-right.
			 */
			StringBuilder titleReverse = new StringBuilder(reverseLine.substring(beginTitleIndex, endTitleIndex));

			title = titleReverse.reverse().toString();// creates title

			film = new Movie(title, unknownYear);// creates Movie Object

			return film;// returns Movie
		}

		/*
		 * Else-if goes into creating the more complicated Movie Objects.
		 * 
		 * First If-statement creates the releaseYear Integer.
		 */

		if (!reverseLine.substring(0, 4).equals("????") && Integer.valueOf(reverseLine.substring(0, 4)) > 0) {

			// First Identifies the release year, as all Movie objects will have
			StringBuilder reverseReleaseYear = new StringBuilder(reverseLine.substring(0, 4));

			// creates left-to-right releaseYear
			String actualYear = reverseReleaseYear.reverse().toString();

			releaseYear = Integer.valueOf(actualYear);// converts String to
														// Integer

			/*
			 * Now that releaseYear has been created, finding if there is a
			 * duplicate and non-theater format is feasible.
			 */

			// determines if duplicate is in the line of text

			if (reverseLine.indexOf("/") > 0) {

				// creates reversed duplicate
				StringBuilder reverseDuplicate = new StringBuilder(
						reverseLine.substring(reverseLine.lastIndexOf(")") + 1, reverseLine.indexOf("/")));

				reverseDuplicate.reverse();// read left-to-right now

				String duplicate = reverseDuplicate.toString();

				/*
				 * uses RomanNumerals Class to determine the int value of the
				 * Duplicate
				 */
				version = RomanNumerals.romanNumToInt(duplicate);

			}

			/*
			 * Now, we determine if there is a format other than in-theaters.
			 */

			if (reverseLine.indexOf(")V(") > 0 || reverseLine.indexOf(")VT(") > 0) {// determines
																					// if
																					// format
																					// is
																					// available.

				// checks specifically if the format is "V"
				if (reverseLine.indexOf(")V(") > 0) {

					StringBuilder reverseFormat = new StringBuilder(
							reverseLine.substring(reverseLine.indexOf(")V("), reverseLine.indexOf(")V(") + 3));

					// Now creates the actual format
					format = reverseFormat.reverse().toString();

				}
				/*
				 * Checks Specifically for "TV" Must use else-if for accuracy
				 */
				else if (reverseLine.indexOf(")VT(") > 0) {

					StringBuilder reverseFormat = new StringBuilder(
							reverseLine.substring(reverseLine.indexOf(")VT("), reverseLine.indexOf(")VT(") + 4));

					// Creates format
					format = reverseFormat.reverse().toString();

				}
			}

			/*
			 * Now that all other variables have been declared, the Title can
			 * now be created.
			 */
			int endTitleIndex = reverseLine.length();

			// '+ 2' removes the spaces between the parentheses in the String
			// title.
			int beginTitleIndex = reverseLine.lastIndexOf("(") + 2;

			// Create full title.
			StringBuilder titleReverse = new StringBuilder(reverseLine.substring(beginTitleIndex, endTitleIndex));

			title = titleReverse.reverse().toString();

			/*
			 * Checks all possibilities to create the right Movie Object.
			 * Utilizes format and version since those variables determine the
			 * object.
			 */

			if (!format.equals("") && version != 0) {

				film = new Movie(title, releaseYear, version, format);
				return film;
			} else if (!format.equals("") && version == 0) {

				film = new Movie(title, releaseYear, format);
				return film;

			} else if (format.equals("") && version != 0) {

				film = new Movie(title, releaseYear, version);
				return film;
			} else {

				film = new Movie(title, releaseYear);
				return film;
			}
		}

		return null;// only if all If-else methods fail
	}

	/**
	 * <p>
	 * This method is designed to parse through a line of text from a TV file,
	 * containing information about a television series. The line contains
	 * title, release year, and possibly episode information.
	 * </p>
	 * <p>
	 * When episode information is found, the method calls the parseEpisode
	 * method, and it adds the Episode to the Episodes ArrayList.
	 * 
	 * @param line
	 *            - a String containing all of the text information of a series,
	 *            including Episode text
	 * 
	 * @return show - a Series Object with all information parsed into different
	 *         parameters
	 */

	public static Series parseSeries(String line) {
		// TODO: use while and for methods to parse lines of File
		Series show = Series.parseNewSeries(line);
		// System.out.println(show.toString());
		return show;
	}

	/**
	 * <p>
	 * This method takes a substring created in the parseSeries method, if line
	 * of text contains episode information. It parses out season, episode
	 * number, and releaseYear of the episode.
	 * </p>
	 * 
	 * @param line
	 *            - a String containing all of the text information of an
	 *            episode
	 * 
	 * @return show - an Episodes Object with all information parsed into
	 *         different parameters
	 */

	public static Episodes parseEpisode(String line) {
		// TODO: use while and for methods to parse lines of File
		if (line.charAt(line.length() - 5) == '-') {
			System.out.println("The line of text that was passed in does not contain "
					+ "\nthe proper formatting to be episode information.");
			return null;
		}

		Episodes show = new Episodes();
		String restOfLine;

		/* Step 1 */
		String[] tempEpisode1 = line.split("\"", 3);

		String series = tempEpisode1[1];
		show.setSeries(series.trim());

		/* Step 2 */
		restOfLine = tempEpisode1[2];
		restOfLine = restOfLine.trim();

		String[] tempEpisode2 = restOfLine.split("\\)", 2);

		String year = tempEpisode2[0];
		year = year.substring(1, year.length());

		show.setStartingYear(year.trim());

		/* Step 3 */
		restOfLine = tempEpisode2[1];
		restOfLine = restOfLine.trim();

		String episodeTitle;
		if (restOfLine.contains("(")) {
			episodeTitle = restOfLine.substring(1, restOfLine.indexOf('('));
		} else {
			episodeTitle = restOfLine.substring(1, restOfLine.indexOf('}'));
		}

		show.setTitle(episodeTitle.trim());

		/* Step 4 */

		if (restOfLine.contains("#")) {

			restOfLine = restOfLine.trim();

			String[] tempEpisode4 = restOfLine.split("\\.", 2);

			String season = tempEpisode4[0];

			season = season.substring(1, season.length());

			show.setSeason(season.trim());
		} else {

			restOfLine = restOfLine.trim();

			show.setSeason("");
		}
		String[] tempEpisode4 = restOfLine.split("\\.", 2);

		/* Step 5 */
		if (restOfLine.contains(")")) {
			restOfLine = tempEpisode4[1];
			restOfLine = restOfLine.trim();

			String[] tempEpisode5 = restOfLine.split("\\)", 2);
			String episodeNumber = tempEpisode5[0];
			if (episodeNumber.length() > 0) {
				episodeNumber = episodeNumber.substring(0, episodeNumber.length());
				
				show.setEpisodeNumber(episodeNumber.trim());
			} else {
				show.setEpisodeNumber("");
			}
		} else {
			restOfLine = tempEpisode4[0];
			restOfLine = restOfLine.trim();
			
			show.setEpisodeNumber("");
		}

		/* Step 6 */
		
		String yearRelease = restOfLine.substring(restOfLine.length() - 4);
		if (yearRelease.indexOf('?') >= 0) {
			show.setUnspecified("UNSPECIFIED");
		} else {
			
			show.setReleaseYear(Integer.decode(yearRelease.trim()));
		}

		/* Step 7 */
		if (restOfLine.contains("{{")) {
			show.setSuspended("SUSPENDED");
		} else {
			show.setSuspended("");
		}


		return show;
	}

	public static void addEpsToSeries() {
		for (int i = 0; i < series.size(); ++i) {
			ArrayList<Episodes> addMe = findEpisodes(series.get(i).getTitle());
			series.get(i).setAllEpisodes(addMe);
		}
	}

	/**
	 * This method creates the arraylist to add to a series for the variable
	 * Arraylist of Episodes.
	 * 
	 * @param seriesTitle
	 *            the title of the series to compare
	 * @return arraylist of Episodes
	 */

	public static ArrayList<Episodes> findEpisodes(String seriesTitle) {
		// TODO: find all episodes from a static thing of episodes.
		ArrayList<Episodes> addThese = new ArrayList<Episodes>();
		for (int i = 0; i < episodes.size(); ++i) {
			if (episodes.get(i).getSeries().equals(seriesTitle)) {
				addThese.add(episodes.get(i));
			}
		}
		return addThese;
	}

	/**
	 * <p>
	 * This method creates an ArrayList of All the Movies, Series and Episodes
	 * so that all Media can be easily be compared and printed by title.
	 * </p>
	 * 
	 * @param allStuff
	 *            - an Unsorted array that contains all the Media that match the
	 *            parameters inputed and parsed in the search method
	 * 
	 * @return ArrayList - an ArrayList of all Media sorted by title.
	 */
	public static void sortByTitleList(ArrayList<Media> allStuff) {
		// TODO: add all media sorted by title
		Collections.sort(allStuff, Media.media);
	}

	/**
	 * <p>
	 * This method creates an ArrayList of All the Movies, Series and Episodes
	 * so that all Media can be easily be compared and printed by year.
	 * </p>
	 * 
	 * @param allStuff
	 *            - an Unsorted array that contains all the Media that match the
	 *            parameters inputed and parsed in the search method
	 * 
	 * @return ArrayList - an ArrayList of all Media sorted by year.
	 */
	public static void sortByYearList(ArrayList<Media> allStuff) {
		// TODO: add all media sorted by year: utilize binarySearch and Sort.

		Collections.sort(allStuff);

	}

	/**
	 * <p>
	 * This method prompts for the input of the users to specify how he/she
	 * wants to search the media. The search prompts the user to pick a medium
	 * (or all media), exact or partial title, the years searched(if specified),
	 * and how he/she wants it sorted.
	 * </p>
	 * 
	 * <p>
	 * Through this input, the program will use the specified sorted ArrayList,
	 * and it will search the ArrayList based on the other specified parameters.
	 * </p>
	 * 
	 * 
	 * @param inputs
	 *            - a Buffered Reader that allows the user to input date
	 * @return readToFile- a String containing every found Media plus
	 *         information on their search inputs.
	 * @throws IOException
	 */
	public static String search(BufferedReader inputs) throws IOException {
		// TODO: create various statements for searching MediaList
		// TODO: create various statements for searching MediaList
		String firstQ = "";
		String secondQ = "";
		String thirdQseries = "";
		String titleQ = "";
		String inputTitle = "";
		String inputYears = "";
		String sortPref = "";
		String readToFile = "";
		int index = 0;

		// BufferedReader inputs = new BufferedReader(new
		// InputStreamReader(System.in));
		// Prompt the user
		System.out.println("Search (m)ovies, (s)eries, or (b)oth?");
		firstQ = inputs.readLine();

		System.out.println("Search (t)itle, (y)ear, or (b)oth?");
		secondQ = inputs.readLine();

		if ((firstQ.equalsIgnoreCase("s") || firstQ.equalsIgnoreCase("b"))
				|| ((secondQ.equalsIgnoreCase("t") || secondQ.equalsIgnoreCase("b")))
						&& (!firstQ.equalsIgnoreCase("m"))) {

			System.out.println("Include episode titles in search and output (y/n)?");
			thirdQseries = inputs.readLine();
		}

		if ((secondQ.equalsIgnoreCase("t") || secondQ.equalsIgnoreCase("b"))) {
			System.out.println("Search for (e)xact or (p)artial matches");
			titleQ = inputs.readLine();
			if (titleQ.equalsIgnoreCase("p")) {

				System.out.println("PARTIAL TITLE:" + titleQ);
			} else if (titleQ.equalsIgnoreCase("e")) {
				System.out.println("EXACT TITLE:" + titleQ);
			}
		}
		readToFile += "SEARCHED ";
		if (firstQ.equalsIgnoreCase("m")) {
			readToFile += "Movies" + "\n";
		} else if (firstQ.equalsIgnoreCase("s")) {
			readToFile += "TV Series" + "\n";
			if (thirdQseries.equalsIgnoreCase("y")) {
				readToFile += "And TV Episodes" + "\n";
			}
		} else if (firstQ.equalsIgnoreCase("b")) {
			readToFile += "Movies, TV Series" + "\n";
			if (thirdQseries.equalsIgnoreCase("y")) {
				readToFile += "And TV Episodes \n";
			}
		}

		if (secondQ.equalsIgnoreCase("t")) {
			System.out.println("Title?");
			inputTitle = inputs.readLine();
			inputYears = "Any" + "\n";
			readToFile += "TITLE: " + inputTitle + "\n" + "Years:" + inputYears + "\n";
		}

		else if (secondQ.equalsIgnoreCase("y")) {
			System.out.println("Years?");
			inputYears = inputs.readLine();
			inputTitle = "Any" + "\n";
			readToFile += "TITLE: " + inputTitle + "\n" + "Years:" + inputYears + "\n";
		}

		else if (secondQ.equalsIgnoreCase("b")) {
			System.out.println("Title?");
			inputTitle = inputs.readLine();
			System.out.println("Years?");
			inputYears = inputs.readLine();
			readToFile += "TITLE: " + inputTitle + "\n" + "Years:" + inputYears + "\n";
		}

		System.out.println("Sort by (t)itle or (y)ear?");
		sortPref = inputs.readLine();
		// inputs.close();

		ArrayList<Media> list = new ArrayList<Media>();
		if (firstQ.equalsIgnoreCase("b")) {
			list.addAll(films);
			list.addAll(series);

			if (thirdQseries.equalsIgnoreCase("y")) {
				// while (list.get(index).getEpisodes() != null) {
				list.addAll(episodes);
				// ++index;
				// }
			}
		} else if (firstQ.equalsIgnoreCase("s")) {
			list.addAll(series);

			if (thirdQseries.equalsIgnoreCase("y")) {
				list.addAll(episodes);
			}
		} else if (firstQ.equalsIgnoreCase("m")) {

			list.addAll(films);

		}

		if (sortPref.equalsIgnoreCase("t")) {
			sortByTitleList(list);
			readToFile += "SORT BY TITLE" + "\n";
		} else if (sortPref.equalsIgnoreCase("y")) {
			sortByYearList(list);
			readToFile += "SORT BY YEAR" + "\n";
		}

		readToFile += "================================================================================" + "\n";
		if (secondQ.equalsIgnoreCase("t")) {
			if (titleQ.equalsIgnoreCase("e")) {
				for (int i = 0; i < list.size(); ++i) {
					if (inputTitle.equals(list.get(i).getTitle())) {
						if (list.get(i) instanceof Movie) {
							readToFile += list.get(i).toString() + "\n";
						}
						if (list.get(i) instanceof Series) {
							readToFile += list.get(i).toString() + "\n";
						}

						if (list.get(i) instanceof Episodes) {
							readToFile += list.get(i).toString() + "\n";
						}
					}
				}
			} else if (titleQ.equalsIgnoreCase("p")) {
				for (int i = 0; i < list.size(); ++i) {
					String mediaTitle = list.get(i).getTitle();
					if (mediaTitle.contains(inputTitle)) {
						if (list.get(i) instanceof Movie) {
							readToFile += list.get(i).toString() + "\n";
						} else if (list.get(i) instanceof Series) {

							readToFile += list.get(i).toString() + "\n";

						} else if (list.get(i) instanceof Episodes) {
							readToFile += list.get(i).toString() + "\n";
						}

					}

				}
			}

		}

		else if (secondQ.equalsIgnoreCase("y")) {
			// if (secondQ.equalsIgnoreCase("y"))
			// String[] inclusive = null;

			if (inputYears.indexOf("-") > 0) {
				String[] inclusives = inputYears.split("-");

				for (Integer inclusiveIndex = Integer.valueOf(inclusives[0]); inclusiveIndex <= Integer
						.valueOf(inclusives[1]); ++inclusiveIndex) {
					Integer year = inclusiveIndex;

					for (Media m : list) {
						if (year.equals(m.getReleaseYear())) {
							if (m instanceof Movie) {
								readToFile += m.toString() + "\n";
							} else if (m instanceof Series) {
								readToFile += m.toString() + "\n";
							}

							else if (m instanceof Episodes) {
								readToFile += m.toString() + "\n";

							}
						}

					}
				}
			}
			// change all String s and String t to readToFile +=
			else if (inputYears.indexOf(",") > 0) {
				String[] specifics = inputYears.split(", ");
				for (int specificsIndex = 0; specificsIndex < specifics.length; ++specificsIndex) {
					Integer year = Integer.valueOf(specifics[specificsIndex]);
					if (secondQ.equalsIgnoreCase("y")) {
						for (int i = 0; i < list.size(); ++i) {
							if (year.equals(list.get(i).getReleaseYear())) {
								if (list.get(i) instanceof Movie) {
									readToFile += list.get(i).toString() + "\n";
								}
								if (list.get(i) instanceof Series) {
									readToFile += list.get(i).toString() + "\n";
								}

								if (list.get(i) instanceof Episodes) {
									readToFile += list.get(i).toString() + "\n";

								}
							}
						}
					}
				}
			}

			else {

				Integer year = Integer.valueOf(inputYears);
				// System.out.println(list.get(0).toString());
				for (int i = 0; i < list.size(); ++i) {
					String mediaYear = list.get(i).getReleaseYear().toString();
					if (inputYears.equals(mediaYear)) {
						if (list.get(i) instanceof Movie) {
							readToFile += list.get(i).toString() + "\n";
						}
						if (list.get(i) instanceof Series) {
							readToFile += list.get(i).toString() + "\n";
						}

						if (list.get(i) instanceof Episodes) {
							readToFile += list.get(i).toString() + "\n";

						}
					}
				}

			}
		}

		if (secondQ.equalsIgnoreCase("b")) {
			int binary;

			Media m = new Media();
			m.setTitle(inputTitle);
			if (inputYears.contains("-")) {
				String[] inclusives = inputYears.split("-");

				for (Integer inclusiveIndex = Integer.valueOf(inclusives[0]); inclusiveIndex <= Integer
						.valueOf(inclusives[1]); ++inclusiveIndex) {
					Integer year = inclusiveIndex;
					m.setReleaseYear(year);

					ArrayList<Media> removed = new ArrayList<Media>();
					while ((binary = Collections.binarySearch(list, m)) > -1) {
						readToFile += list.get(binary).toString() + "\n";
						removed.add(list.get(binary));
						list.remove(binary);
					}
					// for(Media md: removed)list.add(md);
				}
			} else if (inputYears.contains(",")) {
				String[] specifics = inputYears.split(", ");
				for (int specificsIndex = 0; specificsIndex < specifics.length; ++specificsIndex) {

					Integer year = Integer.valueOf(specifics[specificsIndex]);
					ArrayList<Media> removed = new ArrayList<Media>();
					while ((binary = Collections.binarySearch(list, m)) > -1) {
						readToFile += list.get(binary).toString() + "\n";
						removed.add(list.get(binary));
						list.remove(binary);

					}

				}
			} else {

				Integer year = Integer.valueOf(inputYears);
				m.setReleaseYear(year);
				ArrayList<Media> removed = new ArrayList<Media>();

				while ((binary = Collections.binarySearch(list, m)) > -1) {
					System.out.println(binary);
					readToFile += list.get(binary).toString() + "\n";
					removed.add(list.get(binary));
					list.remove(binary);

				}

			}

		}

		return readToFile;
	}

}