package space.icednut.dev.vaadin.exercise.spring.todo.event;

import space.icednut.dev.vaadin.exercise.spring.todo.TodoAppParticipant;

/**
 * @author will.109
 * @date 31/08/2019
 **/
@FunctionalInterface
public interface TodoAppEventListener<SOURCE extends TodoAppParticipant> {

    void handle(SOURCE source);
}
