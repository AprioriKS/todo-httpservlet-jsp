package todo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import todo.dao.TodoDao;
import todo.dao.TodoDaoImpl;
import todo.model.Todo;

@WebServlet("/")
public class TodoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TodoDao todoDao;

    public void init() {
        todoDao = new TodoDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
                insertTodo(request, response);
                break;
            case "/delete":
                deleteTodo(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateTodo(request, response);
                break;
            case "/list":
                listTodo(request, response);
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/views/login"
                    + "/login.jsp").forward(request, response);
                break;
        }
    }

    private void listTodo(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        List<Todo> listTodo = todoDao.selectAllTodos();
        request.setAttribute("listTodo", listTodo);
        request.getRequestDispatcher("/WEB-INF/views/todo"
            + "/todo-list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/todo"
            + "/todo-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Todo existingTodo = todoDao.selectTodo(id);
        request.setAttribute("todo", existingTodo);
        request.getRequestDispatcher("/WEB-INF/views/todo/todo"
            + "-form.jsp").forward(request, response);
    }

    private void insertTodo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String title = request.getParameter("title");
        String username = request.getParameter("username");
        String description = request.getParameter("description");

        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Todo newTodo = new Todo(title, username, description, LocalDate.now(), isDone);
        todoDao.insertTodo(newTodo);
        response.sendRedirect("/list");
    }

    private void updateTodo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        String title = request.getParameter("title");
        String username = request.getParameter("username");
        String description = request.getParameter("description");
        LocalDate targetDate = LocalDate.parse(request.getParameter("targetDate"));

        boolean isDone = Boolean.valueOf(request.getParameter("isDone"));
        Todo updateTodo = new Todo(id, title, username, description, targetDate, isDone);

        todoDao.updateTodo(updateTodo);
        response.sendRedirect("/list");
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        todoDao.deleteTodo(id);
        response.sendRedirect("/list");
    }
}
