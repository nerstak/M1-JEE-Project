package control;

import model.InternshipData;
import utils.database.InternshipDataServices;
import utils.database.SkillsDataServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.Constants.HOME_PAGE;
import static utils.Constants.MISSION_PAGE;

public class UpdateDetails extends ServletModel{


    @Override
    public void init() {
        super.init();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
