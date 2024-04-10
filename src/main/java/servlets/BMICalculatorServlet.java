package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BMICalculatorServlet")
public class BMICalculatorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        double weight = Double.parseDouble(request.getParameter("weight"));
        double height = Double.parseDouble(request.getParameter("height"));
        String gender = request.getParameter("gender");
        
        
        double bmi = calculateBMI(weight, height);
        
        
        String bmiCategory = calculateBMICategory(bmi);
        
        
        double idealMinBMI = 20;
        double idealMaxBMI = 25;
        
        String highlightColor = (bmi >= idealMinBMI && bmi <= idealMaxBMI) ? "#4caf50" : "#ff0000";

        double weightToLose = 0;
        double weightToGain = 0;
        if (bmi < idealMinBMI) {
            weightToGain = (idealMinBMI - bmi) * height * height;
        } else if (bmi > idealMaxBMI) {
            weightToLose = (bmi - idealMaxBMI) * height * height;
        }
        
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>BMI Calculator Result</title>");
        out.println("<style>");
        out.println("body { background-color: #f0f0f0; font-family: Arial, sans-serif; text-align: center; }");
        out.println("h1 { font-size: 28px; color: #333; margin-top: 30px; }");
        out.println("p { font-size: 18px; color: #666; }");
        out.println(".result { border: 2px solid #333; width: 50%; margin: 0 auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
        out.println(".highlight { background-color: " + highlightColor + "; color: white; padding: 5px 10px; border-radius: 5px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"result\">");
        out.println("<h1>BMI Calculator Result</h1>");
        out.println("<p>Weight: " + weight + " kg</p>");
        out.println("<p>Height: " + height + " m</p>");
        out.println("<p>Gender: " + gender + "</p>");
        out.println("<p>BMI: <span class=\"highlight\">" + bmi + "</span></p>");
        out.println("<p>BMI Category: " + bmiCategory + "</p>");
        out.println("<p>Ideal BMI Range: " + idealMinBMI + " - " + idealMaxBMI + "</p>");
        if (weightToLose > 0) {
            out.println("<p>You need to lose " + String.format("%.2f", weightToLose) + " kg to reach a fit BMI.</p>");
        } else if (weightToGain > 0) {
            out.println("<p>You need to gain " + String.format("%.2f", weightToGain) + " kg to reach a fit BMI.</p>");
        } else {
            out.println("<p>You are within the fit BMI range. Keep up the good work!</p>");
        }
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
    
    
    private double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }
    
    
    private String calculateBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal Weight";
        } else if (bmi >= 24.9 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
    
    
}
