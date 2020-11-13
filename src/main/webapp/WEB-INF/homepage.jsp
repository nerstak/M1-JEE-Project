<%--
  Created by IntelliJ IDEA.
  User: Vincent
  Date: 05/11/2020
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="height=device-height,
                      width=device-width, initial-scale=1.0,
                      minimum-scale=1.0, maximum-scale=1.0,
                      user-scalable=yes">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/homepage.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/all.css" type="text/css">

    <title>Homepage</title>
</head>
<body class="main-body">
    <header class="navbar">
        <img class="logo-efrei" src="${pageContext.request.contextPath}/Image/Logo-Efrei-2017-Fr-Web.png" alt="efrei">
        <div class="navbar-menu">
            <jsp:useBean id="tutor" class="model.Tutor" scope="session"/>
            <p>
                    ${tutor.firstName}${" "}${tutor.name}
            </p>
            <a href="Logout">Logout</a>
        </div>
    </header>
    <main class="homepage-main">
        <div class="homepage-main-content">
            <!--Todo replace 12 by the real number -->
            <h1>Extranet myEfrei - 12 student assigned</h1>
            <form class="homepage-selector" >
                <div class="homepage-selector-select">
                    <label for="year">Year</label>
                    <select name="year" id="year">
                        <c:forEach var="year" begin="2015" end="2020" step="1">
                            <option value="${year}">${year}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="homepage-selector-select">
                    <label for="keywords">Keywords</label>
                    <select name="keywords" id="keywords">
                        <c:forEach items="${listOfSkill}" var="skill">
                            <option value="${skill.skill}">${skill.skill}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="homepage-selector-select">
                    <label for="search-name">Name</label>
                    <input type="text" id="search-name">
                </div>
                <input type="submit" value="Search">
            </form>

            <%-- todo replace false by ${empty listOfInternship} --%>
            <c:choose>
                <c:when test="${empty listOfInternship}">
                    <div>
                        <h2>No data ! </h2>
                        <p>It seems that no internship have been found with your criteria</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="homepage-table-wrapper">
                        <div class="homepage-table">
                            <div class="homepage-row">
                                <span class="homepage-cell"></span>
                                <span class="homepage-cell">#</span>
                                <span class="homepage-cell">Group</span>
                                <span class="homepage-cell">First name</span>
                                <span class="homepage-cell">Last name</span>
                                <span class="homepage-cell">C.d.C</span>
                                <span class="homepage-cell">Company eval</span>
                                <span class="homepage-cell">Web survey</span>
                                <span class="homepage-cell">Released report</span>
                                <span class="homepage-cell">Defense</span>
                                <span class="homepage-cell">Planned visit</span>
                                <span class="homepage-cell">Visit done</span>
                                <span class="homepage-cell">Begin date</span>
                                <span class="homepage-cell">End date</span>
                                <span class="homepage-cell">M.d.S</span>
                                <span class="homepage-cell">Address</span>
                                <span class="homepage-cell">Tech note</span>
                                <span class="homepage-cell">Comm note</span>
                            </div>
                            <c:forEach items="${listOfInternship}" var="Internship" >
                                <form class="homepage-row" name="form" action="Details" method="post">
                                    <span class="homepage-cell">
                                        <div>
                                            <input type="submit" value="details" name="internshipSubmit"/>
                                            <input type="submit" value="modify" name="internshipSubmit"/>
                                        </div>
                                    </span>
                                    <span class="homepage-cell"><input name="internshipId" type="text" value="${Internship.internship.internship}" readonly/></span>
                                    <span class="homepage-cell"><input name="studentGroup"  type="text" value="${Internship.student.group}"/></span>
                                    <span class="homepage-cell"><input name="studentFirstname" type="text" value="${Internship.student.firstName}"/></span>
                                    <span class="homepage-cell"><input name="studentName"  type="text" value="${Internship.student.name}"/></span>
                                    <span class="homepage-cell"><input name="cdc" type="checkbox" ${Internship.internship.cdc == true ? 'checked' : ''} /></span>
                                    <span class="homepage-cell"><input name="companyEval" type="checkbox" ${Internship.internship.companyEval == true ? 'checked' : ''}/></span>
                                    <span class="homepage-cell"><input name="webSurvey" type="checkbox" ${Internship.internship.webSurvey == true ? 'checked' : ''}/></span>
                                    <span class="homepage-cell"><input name="releasedReport" type="checkbox" ${Internship.finalReport.report == true ? 'checked' : ''}/></span>
                                    <span class="homepage-cell"><input name="defense" type="checkbox" ${Internship.internship.defense == true ? 'checked' : ''} /></span>
                                    <span class="homepage-cell"><input name="visitPlanned" type="checkbox" ${Internship.visit.planned == true ? 'checked' : ''}/></span>
                                    <span class="homepage-cell"><input name="visitDone" type="checkbox" ${Internship.visit.done == true ? 'checked' : ''}/></span>
                                    <span class="homepage-cell"><input name="beginingDate" type="date" value="${Internship.internship.begining}" /></span>
                                    <span class="homepage-cell"><input name="endDate" type="date" value="${Internship.internship.end}"/></span>
                                    <span class="homepage-cell"><input name="supervisor" type="text" value="${Internship.internship.internSupervisor}"/></span>
                                    <span class="homepage-cell"><input name="companyAddress" type="text" value="${Internship.company.address}"/></span>
                                    <span class="homepage-cell"><input name="techMark" type="text" value="${Internship.marks.tech}"/></span>
                                    <span class="homepage-cell"><input name="commMark" type="text" value="${Internship.marks.communication}"/></span>
                                </form>
                            </c:forEach>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>



        </div>

    </main>
</body>
</html>