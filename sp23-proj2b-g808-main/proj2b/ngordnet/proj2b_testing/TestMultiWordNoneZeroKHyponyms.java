package ngordnet.proj2b_testing;
import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.util.List;
import static com.google.common.truth.Truth.assertThat;

/** Tests the case where the list of words is length greater than 1, but k is still zero. */
public class TestMultiWordNoneZeroKHyponyms {
    // this case doesn't use the NGrams dataset at all, so the choice of files is irrelevant
    public static final String WORDS_FILE = "data/ngrams/top_14377_words.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    @Before
    public void warnUser() {
        System.out.println("Note, this test uses top_14377_words.csv, not top_49887_words.csv!");
    }

    // Test Case 1
    @Test
    public void testMapFunctionK10() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SYNSET_FILE, HYPONYM_FILE);
        List<String> words = List.of("map", "route");

        NgordnetQuery nq = new NgordnetQuery(words, 1900, 2020, 10);
        String actual = studentHandler.handle(nq);
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);
    }

    // Test Case 2
    @Test
    public void testChildAnimalK0in2007() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SYNSET_FILE, HYPONYM_FILE);
        List<String> words = List.of("child", "parents");

        NgordnetQuery nq = new NgordnetQuery(words, 2007, 2007, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);
    }

    // Test Case 3
    @Test
    public void testChildAnimalK1in2007() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SYNSET_FILE, HYPONYM_FILE);
        List<String> words = List.of("child", "animal");

        NgordnetQuery nq = new NgordnetQuery(words, 2019, 2019, 1);
        String actual = studentHandler.handle(nq);
        String expected = "[baby]";
        assertThat(actual).isEqualTo(expected);
    }

    // Test Case 4
    @Test
    public void testChildAnimalK3in2007() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SYNSET_FILE, HYPONYM_FILE);
        List<String> words = List.of("child", "animal");

        NgordnetQuery nq = new NgordnetQuery(words, 2007, 2007, 3);
        String actual = studentHandler.handle(nq);
        String expected = "[baby, kid, monkey]";
        assertThat(actual).isEqualTo(expected);
    }

    // Test Case 5
    @Test
    public void testEnergyLightSparkleK1() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SYNSET_FILE, HYPONYM_FILE);
        List<String> words = List.of("energy", "sun");

        NgordnetQuery nq = new NgordnetQuery(words, 1800, 2020, 1);
        String actual = studentHandler.handle(nq);
        String expected = "[sun]";
        assertThat(actual).isEqualTo(expected);
    }

    // Test Case 6
    @Test
    public void testEnergyLightBeamK3() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SYNSET_FILE, HYPONYM_FILE);
        List<String> words = List.of("energy", "light");

        NgordnetQuery nq = new NgordnetQuery(words, 1800, 2020, 3);
        String actual = studentHandler.handle(nq);
        String expected = "[beam, light, sun]";
        assertThat(actual).isEqualTo(expected);
    }
}
