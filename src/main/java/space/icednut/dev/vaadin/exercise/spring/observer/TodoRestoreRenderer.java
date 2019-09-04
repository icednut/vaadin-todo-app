package space.icednut.dev.vaadin.exercise.spring.observer;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author will.109
 * @date 04/09/2019
 **/
public class TodoRestoreRenderer {

    private Consumer<List<String>> renderProcessor;

    public TodoRestoreRenderer(Consumer<List<String>> renderProcessor) {
        this.renderProcessor = renderProcessor;
    }

    public void render(List<String> todoMessageListState) {
        renderProcessor.accept(todoMessageListState);
    }
}
