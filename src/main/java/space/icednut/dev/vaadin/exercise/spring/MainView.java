package space.icednut.dev.vaadin.exercise.spring;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import space.icednut.dev.vaadin.exercise.spring.todo.TodoLayout;

@Route
@PWA(name = "Design Pattern Exercise", shortName = "DPE")
public class MainView extends VerticalLayout {

    public MainView() {
        add(new TodoLayout());
    }
}
