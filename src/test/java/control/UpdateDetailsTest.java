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
public class UpdateDetailsTest {

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
    CommentsSessionBean commentsSB;

    @Mock
    CompanySessionBean companySB;

    @Mock
    InternshipEntity internshipEntity;

    @Mock
    CompanyEntity companyEntity;

    @Mock
    StudentEntity studentEntity;

    @Mock
    CommentsEntity commentsEntity;

    @Mock
    SkillsEntity skillsEntity;

    @Mock
    KeywordsEntity keywordsEntity;

    @InjectMocks
    UpdateDetails updateDetails;

    @Test
    public void doGetTest() throws ServletException, IOException {
        //Given

        //When
        updateDetails.doGet(request, response);

        //Then
        then(response).should().sendRedirect(CONTROLLER_HOMEPAGE);
    }

    @Test
    public void updateCompanySuccessTest() {
        //Given
        String companyName = "Framasoft";
        given(request.getParameter("companyName")).willReturn(companyName);
        String companyId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        given(request.getParameter("companyId")).willReturn(companyId);
        String companyAddress = "12 rue des paquerettes Paris";
        given(request.getParameter("companyAddress")).willReturn(companyAddress);
        String internshipId = "38400000-8cf0-11bd-b23e-10b96e4ef00d";
        given(request.getParameter("internshipId")).willReturn(internshipId);
        String begin = "2020-10-10";
        given(request.getParameter("begin")).willReturn(begin);
        String end = "2020-11-11";
        given(request.getParameter("end")).willReturn(end);
        String mds = "Jean Jacques";
        given(request.getParameter("mds")).willReturn(mds);
        given(internshipEntity.getCompany()).willReturn(companyEntity);

        //When
        boolean result = updateDetails.updateCompany(request, internshipEntity);

        //Then
        then(internshipEntity).should().setEnding(Date.valueOf(end));
        then(internshipEntity).should().setBeginning(Date.valueOf(begin));
        then(internshipEntity).should().setInternSupervisor(mds);
        then(companyEntity).should().setAddress(companyAddress);
        then(companyEntity).should().setName(companyName);
        then(companySB).should().save(companyEntity);
        then(internshipsSB).should().save(internshipEntity);
    }
}
