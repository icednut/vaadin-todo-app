package space.icednut.dev.vaadin.exercise.spring.memento;

import space.icednut.dev.vaadin.exercise.spring.observer.TodoRestoreRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author will.109
 * @date 03/09/2019
 **/
public class Originator {

    private final TodoRestoreRenderer todoRestoreRenderer;
    private List<String> todoMessageListState = new ArrayList<>();

    public Originator(TodoRestoreRenderer todoRestoreRenderer) {
        this.todoRestoreRenderer = todoRestoreRenderer;
    }

    public void addState(String todoMessage) {
        this.todoMessageListState.add(todoMessage);
    }

    public Memento saveToMemento() {
        System.out.println("save: " + totoMessageListString(todoMessageListState));
        return new Memento(new ArrayList<>(todoMessageListState));
    }

    public void restoreFromMemento(Memento memento) {
        this.todoMessageListState = memento.getSavedMemento();
        System.out.println("restore: " + totoMessageListString(todoMessageListState));
        todoRestoreRenderer.render(todoMessageListState);
    }

    private String totoMessageListString(List<String> todoMessageListState) {
        return todoMessageListState.stream()
                .collect(Collectors.joining(","));
    }
}
