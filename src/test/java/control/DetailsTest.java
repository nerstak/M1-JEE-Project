package control;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import control.session_beans.*;
import models.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

import static utils.Constants.*;

@RunWith(MockitoJUnitRunner.class)
public class DetailsTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    InternshipSessionBean internshipsSB;

    @Mock
    KeywordsSessionBean keywordsSB;

    @Mock
    SkillsSessionBean skillsSB;

    @Mock
    StudentSessionBean studentSB;

    @Mock
    MarksSessionBean marksSB;

    @Mock
    VisitSessionBean visitSB;

    @Mock
    FinalReportSessionBean finalReportSB;

    @Mock
    InternshipEntity internshipEntity;

    @Mock
    StudentEntity studentEntity;

    @Mock
    MarksEntity marksEntity;

    @Mock
    VisitEntity visitEntity;

    @Mock
    FinalReportEntity finalReportEntity;

    @InjectMocks
    Details details;

    @Test
    public void doGetTest() throws ServletException, IOException {
        //Given

        //When
        details.doGet(request, response);

        //Then
        then(response).should().sendRedirect(CONTROLLER_HOMEPAGE);
    }

    @Test
    public void doPostDetailsTest() throws ServletException, IOException {
        //Given
        String internshipSubmit = "details";
        String internshipId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        given(request.getParameter("internshipSubmit")).willReturn(internshipSubmit);
        given(request.getParameter("internshipId")).willReturn(internshipId);
        given(internshipsSB.find(UUID.fromString(internshipId))).willReturn(internshipEntity);
        given(request.getRequestDispatcher(MISSION_PAGE)).willReturn(requestDispatcher);

        //When
        details.doPost(request, response);

        //Then
        then(request).should().setAttribute("internshipData", internshipEntity);
        then(request).should().setAttribute("listOfSkills", skillsSB.getSkills());
        then(request).should().setAttribute("listOfKeywords", keywordsSB.getKeywords());
        then(requestDispatcher).should().forward(request, response);
    }

    @Test(expected=NullPointerException.class) //We just want to test if the method calls updateAllData and forwards the user
    public void doPostModifyTest() throws ServletException, IOException {
        //Given
        String internshipSubmit = "modify";
        String internshipId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        given(request.getParameter("internshipSubmit")).willReturn(internshipSubmit);
        given(request.getParameter("internshipId")).willReturn(internshipId);
        given(internshipsSB.find(UUID.fromString(internshipId))).willReturn(internshipEntity);
        given(request.getRequestDispatcher(MISSION_PAGE)).willReturn(requestDispatcher);

        //When
        details.doPost(request, response);

        //Then

    @Test
    public void updateInternshipSuccessTest() {
        //Given
        String beginningDate = "2020-10-10";
        String endDate = "2020-11-11";
        String supervisor = "jean pierre";
        Boolean defense = true;
        Boolean webSurvey = true;
        Boolean companyEval = true;
        Boolean cdc = true;
        given(request.getParameter("beginningDate")).willReturn(beginningDate);
        given(request.getParameter("endDate")).willReturn(endDate);
        given(request.getParameter("supervisor")).willReturn(supervisor);
        given(request.getParameter("defense")).willReturn(defense.toString());
        given(request.getParameter("webSurvey")).willReturn(webSurvey.toString());
        given(request.getParameter("companyEval")).willReturn(companyEval.toString());
        given(request.getParameter("cdc")).willReturn(cdc.toString());

        //When
        boolean result = details.updateInternship(request, internshipEntity);

        //Then
        then(internshipEntity).should().setDefense(defense);
        then(internshipEntity).should().setCompanyEval(companyEval);
        then(internshipEntity).should().setWebSurvey(webSurvey);
        then(internshipEntity).should().setCdc(cdc);
        then(internshipEntity).should().setInternSupervisor(supervisor);
        then(internshipEntity).should().setBeginning(Date.valueOf(beginningDate));
        then(internshipEntity).should().setEnding(Date.valueOf(endDate));
        then(internshipsSB).should().save(internshipEntity);
        assertThat(result, is(true));
    }

    @Test
    public void updateStudentSuccessTest() {
        //Given
        String studentGroup = "M1";
        String studentFirstname = "Elon";
        String studentName = "Musk";
        given(request.getParameter("studentGroup")).willReturn(studentGroup);
        given(request.getParameter("studentFirstname")).willReturn(studentFirstname);
        given(request.getParameter("studentName")).willReturn(studentName);
        given(internshipEntity.getStudent()).willReturn(studentEntity);

        //When
        boolean result = details.updateStudent(request, internshipEntity);

        //Then
        then(studentEntity).should().setStudentGroup(studentGroup);
        then(studentEntity).should().setFirstname(studentFirstname);
        then(studentEntity).should().setName(studentName);
        then(studentSB).should().save(studentEntity);
        assertThat(result, is(true));
    }

    @Test
    public void updateMarksSuccessTest() {
        //Given
        String commMark = "16";
        String techMark = "20";
        given(request.getParameter("commMark")).willReturn(commMark);
        given(request.getParameter("techMark")).willReturn(techMark);
        given(internshipEntity.getMarks()).willReturn(marksEntity);

        //When
        boolean result = details.updateMarks(request, internshipEntity);

        //Then
        then(marksEntity).should().setCommunication(Integer.valueOf(commMark));
        then(marksEntity).should().setTech(Integer.valueOf(techMark));
        then(marksSB).should().save(marksEntity);
        assertThat(result, is(true));
    }
    }
}