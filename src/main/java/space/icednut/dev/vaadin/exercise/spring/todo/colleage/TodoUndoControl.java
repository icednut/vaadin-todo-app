package space.icednut.dev.vaadin.exercise.spring.todo.colleage;

import com.vaadin.flow.component.button.Button;
import space.icednut.dev.vaadin.exercise.spring.todo.TodoAppParticipant;
import space.icednut.dev.vaadin.exercise.spring.todo.event.TodoAppEvent;
import space.icednut.dev.vaadin.exercise.spring.todo.event.TodoAppEventListener;
import space.icednut.dev.vaadin.exercise.spring.todo.mediator.IMediator;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public class TodoUndoControl extends Button implements TodoAppParticipant {

    private final IMediator mediator;

    public TodoUndoControl(IMediator mediator, TodoAppEventListener<? extends TodoAppParticipant> actionListener) {
        this.mediator = mediator;

        setText("undo");
        addClickListener(event -> actionListener.handle(TodoAppEvent.of(this)));
    }

    @Override
    public void execute() {
        mediator.undo();
    }
}
