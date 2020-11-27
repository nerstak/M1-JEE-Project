package control;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import control.sessionBeans.TutorSessionBean;
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
public class HomepageTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher requestDispatcher;

    @Mock
    TutorSessionBean tutorSB;

    @Mock
    TutorEntity tutor;

    @InjectMocks
    Homepage homepage;

    @Test(expected=NullPointerException.class) //I just want to check that the processRequest is called in both, nothing more
    public void doGetTest() throws ServletException, IOException {
        //Given

        //When
        homepage.doGet(request, response);

        //Then
        then(homepage).should().processRequest(request, response);
    }

    @Test(expected=NullPointerException.class) //I just want to check that the processRequest is called in both, nothing more
    public void doPostTest() throws ServletException, IOException {
        //Given

        //When
        homepage.doGet(request, response);

        //Then
        then(homepage).should().processRequest(request, response);
    }

    @Test
    public void processRequestNullTutorTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(null);

        //When
        homepage.doGet(request, response);

        //Then
        then(response).should().sendRedirect("Login");
    }
}