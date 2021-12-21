package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import examples.model.Employee;
import examples.model.Evaluation;
import examples.stateless.EmployeeService;

@WebServlet(name="EmployeeServlet", 
            urlPatterns="/EmployeeServlet")
public class EmployeeServiceServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 10: Using Orphan Removal Example";
    
    private final String DESCRIPTION = 
        "This example demonstrates the basics of using orphan removal.";

    @EJB EmployeeService service;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);
        
        // process request
        String action = request.getParameter("action");
        if (action == null) {
            // do nothing if no action requested
        } else if (action.equals("Create")) {
            service.createEmployee(
                    parseInt(request.getParameter("empId")),
                    request.getParameter("name"));
        } else if (action.equals("AddEval")) {
            service.addEmployeeEvaluation(
                    parseInt(request.getParameter("addEmpId")),
                    parseInt(request.getParameter("addEvalId")),
                    request.getParameter("evalText"));
        } else if (action.equals("RemoveEval")) {
            service.removeEmployeeEvaluation(
                    parseInt(request.getParameter("removeEmpId")),
                    parseInt(request.getParameter("removeEvalId")));
        }
        
        out.println("Employees: </br>");
        for (Employee emp : service.findAllEmployees()) {
            out.print(emp + "<br/>");
        }
        
        out.println("</br>Evaluations: </br>");
        for (Evaluation eval : service.findAllEvaluations()) {
            out.print(eval + "<br/>");
        }

        printHtmlFooter(out);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
        
    private int parseInt(String intString) {
        try {
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    private long parseLong(String longString) {
        try {
            return Long.parseLong(longString);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
        out.println("<center><h1>" + TITLE + "</h1></center>");
        out.println("<p>" + DESCRIPTION + "</p>");
        out.println("<hr/>");
        out.println("<form action=\"EmployeeServlet\" method=\"POST\">");
        // form to create and Employee 
        out.println("<h3>Create an Employee </h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"empId\"/>(int)</td></tr>");
        out.println("<tr><td>Employee Name:</td><td><input type=\"text\" name=\"name\"/>(String)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"Create\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");

        out.println("<h3>Create New Employee Evaluation</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"addEmpId\"/>(int)</td></tr>");
        out.println("<tr><td>Evaluation Id:</td><td><input type=\"text\" name=\"addEvalId\"/>(int)</td></tr>");
        out.println("<tr><td>Evaluation Text:</td><td><input type=\"text\" name=\"evalText\"/>(String)</td></tr>");
        out.println("<tr><td><input name=\"action\" type=\"submit\" value=\"AddEval\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");

        out.println("<h3>Remove Employee Evaluation</h3>");
        out.println("<table><tbody>");
        out.println("<tr><td>Employee Id:</td><td><input type=\"text\" name=\"removeEmpId\"/>(int)</td></tr>");
        out.println("<tr><td>Evaluation Id:</td><td><input type=\"text\" name=\"removeEvalId\"/>(int)</td>" +
                    "<td><input name=\"action\" type=\"submit\" value=\"RemoveEval\"/></td></tr>");
        out.println("</tbody></table>");
        out.println("<hr/>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
