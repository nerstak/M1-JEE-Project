package control;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import control.sessionBeans.TutorSessionBean;

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
public class LoginTest {

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

    @InjectMocks
    Login login;

    @Test
    public void doGetNonNullTutorTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn("non null");

        //When
        login.doGet(request, response);

        //Then
        then(response).should().sendRedirect("Homepage");
    }

    @Test
    public void doGetNullTutorTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn(null);
        given(request.getRequestDispatcher(LOGIN_PAGE)).willReturn(requestDispatcher); //This has the double dunction of checking that this is called

        //When
        login.doGet(request, response);

        //Then
        then(requestDispatcher).should().forward(request, response);
    }

    @Test
    public void doPostNonNullTutorTest() throws ServletException, IOException {
        //Given
        given(request.getSession()).willReturn(session);
        given(session.getAttribute("tutor")).willReturn("non null");

        //When
        login.doPost(request, response);

        //Then
        then(response).should().sendRedirect("Homepage");
    }
}