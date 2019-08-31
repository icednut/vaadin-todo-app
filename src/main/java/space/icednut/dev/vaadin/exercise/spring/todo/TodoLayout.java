package space.icednut.dev.vaadin.exercise.spring.todo;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoInput;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoList;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.IMediator;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.TodoMediator;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoLayout extends VerticalLayout {

    private final IMediator mediator = new TodoMediator();

    public TodoLayout() {
        final TodoInput todoInput = new TodoInput((source) -> source.execute(), mediator);
        final TodoList todoList = new TodoList(mediator);

        add(todoList, todoInput);
    }
}
