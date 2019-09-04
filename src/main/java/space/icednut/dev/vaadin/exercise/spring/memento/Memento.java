package space.icednut.dev.vaadin.exercise.spring.memento;

import java.util.List;

/**
 * @author will.109
 * @date 03/09/2019
 **/
public class Memento {

    private final List<String> todoMessageListState;

    public Memento(List<String> todoMessageListState) {
        this.todoMessageListState = todoMessageListState;
    }

    public List<String> getSavedMemento() {
        return todoMessageListState;
    }
}
