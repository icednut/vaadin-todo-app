package space.icednut.dev.vaadin.exercise.spring.todo;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoInput;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoList;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoRedoControl;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoUndoControl;
import space.icednut.dev.vaadin.exercise.spring.todo.event.TodoAppEventListener;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.IMediator;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.TodoMediator;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoLayout extends VerticalLayout {

    private final IMediator mediator = new TodoMediator();

    public TodoLayout() {
        TodoAppEventListener<? extends TodoAppParticipant> actionListener = event -> {
            final TodoAppParticipant todoAppParticipant = event.getSource();
            todoAppParticipant.execute();
        };

        final TodoInput todoInput = new TodoInput(mediator, actionListener);
        final TodoUndoControl todoUndoControl = new TodoUndoControl(mediator, actionListener);
        final TodoRedoControl todoRedoControl = new TodoRedoControl(mediator, actionListener);
        final TodoList todoList = new TodoList(mediator, actionListener);

        add(new HorizontalLayout(todoUndoControl, todoRedoControl, todoInput), todoList);
    }
}
