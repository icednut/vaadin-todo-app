package space.icednut.dev.vaadin.exercise.spring.todo.command;

import space.icednut.dev.vaadin.exercise.spring.todo.TodoMessage;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public interface TodoAction {

    TodoMessage doIt();

    void undoIt();
}
