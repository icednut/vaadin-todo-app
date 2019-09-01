package space.icednut.dev.vaadin.exercise.spring.todo.mediator;

import space.icednut.dev.vaadin.exercise.spring.todo.TodoMessage;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoInput;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoList;
import space.icednut.dev.vaadin.exercise.spring.todo.colleage.TodoListElement;
import space.icednut.dev.vaadin.exercise.spring.todo.command.TodoInsertAction;

/**
 * @author will.109
 * @date 31/08/2019
 **/
public interface IMediator {

    void registerTodoInput(TodoInput todoInput);

    void registerTodoList(TodoList todoList);

    void registerTodoListElement(TodoListElement todoListElement);

    TodoMessage addTodo(String todoMessage);

    TodoMessage deleteTodo(Long todoId);

    void addTodoForUndo(String todoMessage);

    void deleteTodoForUndo(Long todoId);

    void undo();

    void redo();
}
