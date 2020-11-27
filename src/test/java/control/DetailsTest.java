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
        then(details).should().updateAllData(request, internshipEntity);
        then(requestDispatcher).should().forward(request, response);
    }
}