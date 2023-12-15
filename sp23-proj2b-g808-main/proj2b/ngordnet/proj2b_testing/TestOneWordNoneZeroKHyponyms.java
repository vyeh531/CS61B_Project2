package ngordnet.proj2b_testing;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
public class TestOneWordNoneZeroKHyponyms {
    public static final String WORDS_FILE = "data/ngrams/top_14377_words.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    private static final NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymHandler(
            WORDS_FILE, TOTAL_COUNTS_FILE, SYNSET_FILE, HYPONYM_FILE);

    @Test
    public void testDashK0() {
        List<String> words = List.of("dash");

        NgordnetQuery nq = new NgordnetQuery(words, 2019, 2019, 0);
        String actual = studentHandler.handle(nq);
        String expected = "[bolt, break, dah, dash, elan, fast_break, flair, hyphen, panache, sprint, style]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDashK1() {
        List<String> words = List.of("dash");

        NgordnetQuery nq = new NgordnetQuery(words, 2019, 2019, 1);
        String actual = studentHandler.handle(nq);
        String expected = "[break]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDashK3() {
        List<String> words = List.of("dash");

        NgordnetQuery nq = new NgordnetQuery(words, 2020, 2020, 3);
        String actual = studentHandler.handle(nq);
        String expected = "[]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDashK31600() {
        List<String> words = List.of("dash");

        NgordnetQuery nq = new NgordnetQuery(words, 1600, 2000, 3);
        String actual = studentHandler.handle(nq);
        String expected = "[bolt, break, style]";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testDogK1() {
        List<String> words = List.of("dog");

        NgordnetQuery nq = new NgordnetQuery(words, 2019, 2019, 1);
        String actual = studentHandler.handle(nq);
        String expected = "[dog]";
        assertThat(actual).isEqualTo(expected);
    }
}
