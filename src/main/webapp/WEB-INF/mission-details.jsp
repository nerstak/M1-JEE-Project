<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 07/11/2020
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="height=device-height,
                      width=device-width, initial-scale=1.0,
                      minimum-scale=1.0, maximum-scale=1.0,
                      user-scalable=yes">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/mission-details.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/all.css" type="text/css">

    <title>Mission details</title>
</head>
<body>
    <header class="navbar">
        <img class="logo-efrei" src="${pageContext.request.contextPath}/Image/Logo-Efrei-2017-Fr-Web.png" alt="efrei">
        <div class="navbar-menu">
            <jsp:useBean id="tutor" class="model.Tutor" scope="session"/>
            <p>
                ${tutor.firstName}${" "}${tutor.name}
            </p>
            <p>Logout</p>
        </div>
    </header>

<%--    TODO
    create arraylist woth internshipData + name du boutton
    add it to button value
--%>

    <main class="main-info">
        <jsp:useBean id="internshipData" class="model.InternshipData" scope="request"/>
        <div class="info-company">
            <h2>Company</h2>
            <form class="info-company-form" action="UpdateDetails" method="post">
                <div class="div-input-half">
                    <div class="input-left">
                        <label for="companyName">Name</label>
                        <input type="text" id="companyName" name="companyName" value="${internshipData.company.name}">
                    </div>
                    <div class="input-right">
                        <label for="mds">M.d.S</label>
                        <input type="text" name="mds" id="mds" value="${internshipData.internship.internSupervisor}">
                    </div>
                </div>
                <div class="div-input-half">
                    <div class="input-left">
                        <label for="begin">Begin</label>
                        <input type="date" id="begin" name="begin" value="${internshipData.internship.begining}">
                    </div>
                    <div class="input-right">
                        <label for="end">End</label>
                        <input type="date" id="end" name="end" value="${internshipData.internship.end}">
                    </div>
                </div>
                <div class="div-input-full">
                    <label for="companyAddress">Address</label>
                    <input type="text" id="companyAddress" name="companyAddress" value="${internshipData.company.address}" >
                </div>
                <button type="submit" value="company" name="updateDetails">Modify</button>
            </form>
        </div>

        <div class="info-students">
            <h2>Student</h2>
            <form class="info-company-form" action="UpdateDetails" method="post" name="updateDetails">
                <div class="div-input-half">
                    <div class="input-left">
                        <label for="studentId">ID</label>
                        <input type="text" id="studentId" name="studentId" value="${internshipData.student.studentId}" readonly>
                    </div>
                    <div class="input-right">
                        <label for="group">Group</label>
                        <input type="text" name="group" id="group" value="${internshipData.student.group}">
                    </div>
                </div>
                <div class="div-input-half">
                    <div class="input-left">
                        <label for="firstName">First Name</label>
                        <input type="text" id="firstName" name="firstName" value="${internshipData.student.firstName}">
                    </div>
                    <div class="input-right">
                        <label for="lastName">Last Name</label>
                        <input type="text" id="lastName" name="lastName" value="${internshipData.student.name}">
                    </div>
                </div>
                <div class="div-input-full">
                    <label for="email">Email</label>
                    <input type="text" id="email" name="email" value="${internshipData.student.email}">
                </div>
                <div class="div-input-full">
                    <label for="linkedin">Linkedin Profile</label>
                    <input type="text" id="linkedin" name="linkedin" value="${internshipData.student.linkedinProfile}">
                </div>
                <div>
                    <input type="hidden" name="internshipId" value="${internshipData.internship.internship}">
                </div>
                <c:if test="${not empty message}">
                    <p>${message}</p>
                </c:if>
                <button type="submit" value="student" name="updateDetails">Modify</button>
            </form>
        </div>

        <div class="info-internship">
            <h2>Internship</h2>
            <form class="info-company-form" id="info-company-form" action="UpdateDetails" method="post" name="updateDetails">
                <div class="div-input-half">
                    <div class="input-left">
                        <label for="reportTitle">Report's title</label>
                        <input type="text" name="reportTitle" id="reportTitle" value="${internshipData.finalReport.title}">
                    </div>
                </div>
                <div class="div-input-third">
                    <div class="input-left">
                        <label for="description">Description</label>
                        <textarea name="description" id="description" form="info-company-form">${internshipData.internship.desciption}"</textarea>
                    </div>
                    <div class="input-right">
                        <label for="tutorComments">Tutor's comment</label>
                        <textarea name="tutorComments" id="tutorComments" form="info-company-form">${internshipData.comments.supervisorComment}</textarea>
                    </div>
                    <div class="input-right">
                        <label for="studentComments">Student's comments</label>
                        <textarea name="studentComments" id="studentComments" form="info-company-form">${internshipData.comments.studentComm}</textarea>
                    </div>
                </div>
                <button type="submit" value="internship" name="updateDetails">Modify</button>
            </form>
        </div>

        <div class="info-skills">
            <h2>Skills</h2>
            <form class="info-company-form" action="UpdateDetails" method="post" name="updateDetails">
                <div class="div-input-button">
                    <div class="input-left">
                        <label>Skills</label>
                        <input type="text">
                    </div>
                    <button type="submit" value="skills" name="updateDetails">Add</button>
                </div>
            </form>
            <div class="list-data">
                <c:choose>
                    <c:when test="${empty listOfStudentSkills}">
                        <h2>No skills registered</h2>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${listOfStudentSkills}" var="skill">
                            <p>${skill.skill}</p>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="info-keywords">
            <h2>Keyword</h2>
            <form class="info-company-form" action="UpdateDetails" method="post" name="updateDetails">
                <div class="div-input-button">
                    <div class="input-left">
                        <label>Keywords</label>
                        <input type="text">
                    </div>
                    <button type="submit" value="keywords" name="updateDetails">Keywords</button>
                </div>
            </form>
            <div class="list-data">
                <p>Etranger</p>
                <p>Remunéré</p>
                <p>Labo</p>
                <p>Long</p>
                <p>Cool</p>
                <p>Bonus</p>
            </div>
        </div>
    </main>
</body>
</html>
