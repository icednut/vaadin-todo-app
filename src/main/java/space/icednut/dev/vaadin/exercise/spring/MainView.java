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
import org.springframework.util.StringUtils;

@Route
@PWA(name = "Design Pattern Exercise Command Pattern", shortName = "Command Pattern Exercise")
public class MainView extends VerticalLayout {

    public MainView() {
        final VerticalLayout todosList = new VerticalLayout();
        final TextField todoField = new TextField();
        final Button addButton = new Button("Add");

        addButton.addClickListener(event -> {
            final String todoValue = todoField.getValue();

            if (StringUtils.hasText(todoValue)) {
                final Checkbox checkbox = new Checkbox(todoValue);
                final Icon deleteButton = new Icon(VaadinIcon.CLOSE_CIRCLE);
                final HorizontalLayout todoElementLayout = new HorizontalLayout(checkbox, deleteButton);

                deleteButton.setSize("15px");
                deleteButton.addClickListener(deleteEvent -> todosList.remove(todoElementLayout));
                todoElementLayout.setAlignItems(Alignment.CENTER);
                todosList.add(todoElementLayout);
                todoField.setValue("");
            }
        });

        add(todosList, new HorizontalLayout(todoField, addButton));
    }
}
