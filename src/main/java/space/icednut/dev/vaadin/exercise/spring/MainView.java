package space.icednut.dev.vaadin.exercise.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.util.StringUtils;
import space.icednut.dev.vaadin.exercise.component.TodoListElement;

@Route
@PWA(name = "Plain Todo App", shortName = "PTA")
public class MainView extends VerticalLayout {

    final VerticalLayout todosList = new VerticalLayout();
    final TextField todoField = new TextField();
    final Button addButton = new Button("Add");

    public MainView() {
        addButton.addClickListener(event -> {
            final String todoMessage = todoField.getValue();

            if (StringUtils.hasText(todoMessage)) {
                final TodoListElement todoListElement = new TodoListElement(todoMessage);
                todoListElement.addDeleteClickListener(deleteClickEvent -> todosList.remove(todoListElement));
                todosList.add(todoListElement);
                todoField.setValue("");
            }
        });

        add(new HorizontalLayout(todoField, addButton), todosList);
    }
}
