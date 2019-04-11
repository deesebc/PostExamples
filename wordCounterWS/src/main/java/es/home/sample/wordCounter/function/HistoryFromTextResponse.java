package es.home.sample.wordCounter.function;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import es.home.sample.wordCounter.entity.History;
import es.home.sample.wordCounter.pojo.TextResponse;

public enum HistoryFromTextResponse implements Function<TextResponse, History> {
    INSTANCE;

    @Override
    public History apply(final TextResponse input) {
        History output = new History();
        if (input != null) {
            ModelMapper model = new ModelMapper();
            model.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            output = model.map(input, History.class);
        }
        return output;
    }
}
