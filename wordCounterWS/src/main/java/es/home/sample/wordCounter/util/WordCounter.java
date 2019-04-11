package es.home.sample.wordCounter.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class WordCounter {

    private WordCounter() {
        super();
    }

    private static final Log LOGGER = LogFactory.getLog(WordCounter.class);

    private final static String CHARS_TO_REPLACE = ".</p>[\r|]";

    public static void calculateTextResponse(final String textToCount, final List<Long> apSizeList,
            final Map<String, Integer> wordCounter) throws IOException {
        LOGGER.debug("Calculate Response from: " + textToCount);
        String textReplaced = textToCount.replaceAll(CHARS_TO_REPLACE, StringUtils.EMPTY);
        LOGGER.debug("Calculate Response replaced: " + textReplaced);

        Supplier<Stream<String>> sParagraphs = () -> Pattern.compile("<p>").splitAsStream(textReplaced);

        Function<String, Integer> saver = (wordToCount) -> wordCounter.containsKey(wordToCount)
                ? wordCounter.put(wordToCount, wordCounter.get(wordToCount) + 1)
                : wordCounter.put(wordToCount, 1);
        Consumer<String> countWords = (paragraph) -> apSizeList
                .add(Pattern.compile(StringUtils.SPACE).splitAsStream(paragraph).map(saver).count());
        sParagraphs.get().filter(StringUtils::isNotBlank).forEach(countWords);

        LOGGER.debug("apSizeList");
        apSizeList.stream().forEach((paragraphSize) -> LOGGER.debug(Long.toString(paragraphSize)));
        LOGGER.debug("wordCounter");
        wordCounter.forEach((word, count) -> LOGGER.debug("word: " + word + " count: " + count));
    }
}
