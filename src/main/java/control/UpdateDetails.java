package control;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class UpdateDetails extends ServletModel{


    @Override
    public void init() {
        super.init();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String detailsSubmitButton = request.getParameter("updateDetails");
        switch (detailsSubmitButton){
            case "company":
                String companyName = request.getParameter("companyName");
                String mds = request.getParameter("mds");
                String begin =  request.getParameter("begin");
                String end = request.getParameter("end");
                String companyAddress = request.getParameter("companyAddress");
                break;
            case "student":
                String studentId = request.getParameter("studentId");
                String group = request.getParameter("group");
                String firstName =  request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String linkedin = request.getParameter("linkedin");
                break;
            case "internship":
                String description = request.getParameter("description");
                String tutorComments = request.getParameter("tutorComments");
                String studentComments = request.getParameter("studentComments");
                break;
            case "keywords":
//                TODO to be done
                break;
            case "skills":
//                TODO to be done
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
