package control;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import control.session_beans.FinalReportSessionBean;
import control.session_beans.InternshipSessionBean;
import control.session_beans.KeywordsSessionBean;
import control.session_beans.MarksSessionBean;
import control.session_beans.SkillsSessionBean;
import control.session_beans.StudentSessionBean;
import control.session_beans.TutorSessionBean;
import control.session_beans.VisitSessionBean;
import models.TutorEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

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
    TutorEntity tutor;

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
}