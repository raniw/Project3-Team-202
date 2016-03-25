import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class TEST {

	public static Episodes testEp = new Episodes("\"Star Trek Continues\"", null, 2016, 1, 7, null, null);
	public static Series testSeries = new Series("\"Star Trek Dark Armada\"", 2006, null, null, "????", null);
	public static Movie testMov = new Movie("'Star Trek: Deep Space Nine': Behind the Scenes", 1993, "(V)");
	public static ArrayList<Media> allThings = new ArrayList<Media>();
	public static String[] inputs = { "b", "t", "p", "y", "Star Trek", "t" };

	@Test
	public void test() {
		// fail("Not yet implemented");
		// testSearch();
		// testTitleList();
		testYearList();
		// testParseEpisode();
		// testParseSeries();
		// testParseMovie();

	}

	public void testSearch() {

		String returned = Driver.search();

		assertEquals("SEARCHED: MOVIES, TV SERIES, AND TV EPISODES \n" + "PARTIAL TITLE: STAR TREK \n" + "YEARS: Any \n"
				+ "SORTED BY TITLE \n" + "================================="
				+ "============================================= \n" + testEp.printAll() + "\n" + testSeries.printAll()
				+ "\n" + testMov.printAll() + "\n", returned);
	}

	/*
	 * createByTitleList
	 */
	public void testTitleList() {
		Episodes testEp = new Episodes("Star Trek Continues", "Come Not Between the Dragons", 2016, 1, 7, null, null);
		Series testSeries = new Series("Star Trek Dark Armada", 2006, null, null, "????", null);
		Movie testMov = new Movie("'Star Trek: Deep Space Nine': Behind the Scenes", 1993, "(V)");
		ArrayList<Media> allThings = new ArrayList<Media>();
		allThings.add(testEp);
		allThings.add(testSeries);
		allThings.add(testMov);

		for (int i = 0; i < allThings.size(); ++i) {
			System.out.println(allThings.get(i).getTitle());
		}
		System.out.println("");
		Driver.sortByTitleList(allThings);

		for (int i = 0; i < allThings.size(); ++i) {

			System.out.println(allThings.get(i).getTitle());
		}

		assertEquals("'Star Trek: Deep Space Nine': Behind the Scenes", allThings.get(0).getTitle());
		assertEquals("Come Not Between the Dragons", allThings.get(1).getTitle());
		assertEquals("Star Trek Dark Armada", allThings.get(2).getTitle());

	}

	/*
	 * createYearMovieList Create different arrayLists of 2 and sort them by
	 * title or by release year using Media gets
	 * 
	 */
	public void testYearList() {

		Episodes testEp = new Episodes("\"Star Trek Continues\"", "Come Not Between the Dragons", 2016, 1, 7, null,
				null);
		Series testSeries = new Series("\"Star Trek Dark Armada\"", 2006, null, null, "????", null);
		Movie testMov = new Movie("'Star Trek: Deep Space Nine': Behind the Scenes", 1993, "(V)");
		allThings.add(testEp);
		allThings.add(testSeries);
		allThings.add(testMov);

		for (int i = 0; i < allThings.size(); ++i) {
			System.out.println(allThings.get(i).getReleaseYear());
		}

		Driver.sortByYearList(allThings);

		assertEquals((Integer) 1993, allThings.get(0).getReleaseYear());
		assertEquals((Integer) 2006, allThings.get(1).getReleaseYear());
		assertEquals((Integer) 2016, allThings.get(2).getReleaseYear());
	}

	/*
	 * parseEpisodes Episodes test - all gets plus boolean
	 * 
	 */
	public void testParseEpisode() {
		String episode = "\"Star Trek Anthology\" (2015) {Another Door Opens (#1.1)}	2015";

		Episodes Ep = Driver.parseEpisode(episode);

		assertEquals("\"Star Trek Anthology\"", Ep.getSeriesTitle());
		assertEquals("Another Door Opens", Ep.getTitle());
		assertEquals((Integer) 2015, Ep.getReleaseYear());
		assertEquals((Integer) 1, Ep.getSeason());
		assertEquals(1, Ep.getEpisodeNumber());
		assertEquals("", Ep.getUnspecified());//
		assertEquals("", Ep.getSuspended());
		assertEquals(false, Ep.isSuspended());
		assertEquals("EPISODE: Star Trek Anthology: Another Door Opens (2015)", Ep.printAll());

	}

	/*
	 * parseSeries
	 * 
	 * Series test - all gets plus boolean
	 * 
	 */
	public void testParseSeries() {
		ArrayList<Episodes> episodes = new ArrayList<>();
		episodes.add(Driver
				.parseEpisode("\"Star Trek: Deep Space Nine\" " + "(1993) {You Are Cordially Invited... (#6.7)}	1997"));

		String series = "\"Star Trek: Deep Space Nine\"" + " (1993)			1993-1999";
		Series show = Driver.parseSeries(series);

		assertEquals("Star Trek: Deep Space Nine", show.getTitle());
		assertEquals((Integer) 1993, show.getReleaseYear());
		assertEquals((Integer) 1999, show.getEndYear());
		assertEquals(episodes, show.getAllEpisodes());
		assertEquals("", show.getUnspecified());
		assertEquals("", show.getSuspended());
		assertEquals(false, show.isSuspended());

	}

	/*
	 * parseMovie
	 * 
	 * Movie Test - all gets plus print
	 */
	public void testParseMovie() {

		String film = "'Star Trek: Deep Space Nine': Behind the Scenes (1993) (V)  1993";
		Movie testFilm = Driver.parseMovie(film);
		assertEquals("'Star Trek: Deep Space Nine': Behind the Scenes", testFilm.getTitle());
		assertEquals((Integer) 1993, testFilm.getReleaseYear());
		assertEquals(0, testFilm.getDuplicate());
		assertEquals("(V)", testFilm.getFormat());
		assertEquals("", testFilm.getUnknownYear());
	}

}
