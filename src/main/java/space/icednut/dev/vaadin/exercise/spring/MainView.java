package space.icednut.dev.vaadin.exercise.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import space.icednut.dev.vaadin.exercise.spring.component.TodoListElement;
import space.icednut.dev.vaadin.exercise.spring.memento.Memento;
import space.icednut.dev.vaadin.exercise.spring.memento.Originator;
import space.icednut.dev.vaadin.exercise.spring.observer.TodoRestoreRenderer;

import java.util.*;
import java.util.stream.Collectors;

@Route
@PWA(name = "Design Pattern Exercise Memento Pattern", shortName = "Memento Pattern Exercise")
public class MainView extends VerticalLayout {

    final VerticalLayout todosList = new VerticalLayout();
    final TextField todoField = new TextField();
    final Button addButton = new Button("Add");

    final Button saveButton = new Button("Save");
    final Button restoreButton = new Button("Restore");
    final TodoRestoreRenderer todoRestoreRenderer = new TodoRestoreRenderer(restoredTodoMessageList -> {
        if (CollectionUtils.isEmpty(restoredTodoMessageList)) {
            return;
        }
        todosList.removeAll();
        restoredTodoMessageList.stream()
                .forEach(restoredTodoMessage -> {
                    final TodoListElement todoListElement = new TodoListElement(restoredTodoMessage, targetElement -> {
                        todosList.remove(targetElement);
                    });
                    todosList.add(todoListElement);
                });
    });
    final Originator originator = new Originator(todoRestoreRenderer);
    final List<String> lastState = new ArrayList<>();
    final LinkedList<Memento> savedStates = new LinkedList<>();

    public MainView() {
        saveButton.addClickListener(event -> {
            savedStates.add(originator.saveToMemento());
        });
        restoreButton.addClickListener(event -> {
            originator.restoreFromMemento(savedStates.getLast());
        });
        addButton.addClickListener(event -> {
            final String todoMessage = todoField.getValue();

            if (StringUtils.hasText(todoMessage)) {
                lastState.add(todoMessage);
                originator.setState(new ArrayList<>(lastState));

                final TodoListElement todoListElement = new TodoListElement(todoMessage, targetElement -> {
                    todosList.remove(targetElement);
                });
                todosList.add(todoListElement);
                todoField.setValue("");
            }
        });

        add(controlComponent(), todosList);
    }

    private HorizontalLayout controlComponent() {
        final Icon icon = new Icon(VaadinIcon.ELLIPSIS_DOTS_V);
        icon.setSize("14px");

        final HorizontalLayout horizontalLayout = new HorizontalLayout(
                new HorizontalLayout(todoField, addButton),
                icon,
                new HorizontalLayout(saveButton, restoreButton)
        );

        horizontalLayout.setAlignItems(Alignment.CENTER);
        return horizontalLayout;
    }
}
