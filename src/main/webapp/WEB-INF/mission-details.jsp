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
            <p>John Doe</p>
            <p>Logout</p>
        </div>
    </header>
    <main class="main-info">
        <div class="info-company">
            <h2>Company</h2>
            <form class="info-company-form">
                <div class="div-input-half">
                    <div class="input-left">
                        <label for="cName">Name</label>
                        <input type="text" id="cName">
                    </div>
                    <div class="input-right">
                        <label>M.d.S</label>
                        <input type="text">
                    </div>
                </div>
                <div class="div-input-half">
                    <div class="input-left">
                        <label>Begin</label>
                        <input type="tdext">
                    </div>
                    <div class="input-right">
                        <label>End</label>
                        <input type="text">
                    </div>
                </div>
                <div class="div-input-full">
                    <label>Adress</label>
                    <input type="text">
                </div>
                <input type="submit" value="Modify">
            </form>
        </div>

        <div class="info-students">
            <h2>Student</h2>
            <form class="info-company-form">
                <div class="div-input-half">
                    <div class="input-left">
                        <label>ID</label>
                        <input type="text" disabled>
                    </div>
                    <div class="input-right">
                        <label>Promo</label>
                        <input type="text">
                    </div>
                </div>
                <div class="div-input-half">
                    <div class="input-left">
                        <label>First Name</label>
                        <input type="tdext">
                    </div>
                    <div class="input-right">
                        <label>Last Name</label>
                        <input type="text">
                    </div>
                </div>
                <div class="div-input-full">
                    <label>Linkedin Profile</label>
                    <input type="text">
                </div>
                <input type="submit" value="Modify">
            </form>
        </div>

        <div class="info-internship">
            <h2>Internship</h2>
            <form class="info-company-form" id="info-company-form">
                <div class="div-input-half">
                    <div class="input-left">
                        <label>Report's title</label>
                        <input type="text">
                    </div>
                </div>
                <div class="div-input-third">
                    <div class="input-left">
                        <label>Description</label>
                        <textarea form="info-company-form"></textarea>
                    </div>
                    <div class="input-right">
                        <label>Tutor's comment</label>
                        <textarea form="info-company-form"></textarea>
                    </div>
                    <div class="input-right">
                        <label>Student's comments</label>
                        <textarea form="info-company-form"></textarea>
                    </div>
                </div>
                <input type="submit" value="Modify">
            </form>

        </div>

        <div class="info-skills">
            <h2>Skills</h2>
            <form class="info-company-form">
                <div class="div-input-button">
                    <div class="input-left">
                        <label>Skills</label>
                        <input type="text">
                    </div>
                    <input type="submit" value="Add">
                </div>
            </form>
            <div class="list-data">
                <p>CSS</p>
                <p>Java</p>
                <p>HTML</p>
                <p>Leadership</p>
                <p>JEE</p>
                <p>JS</p>
                <p>Git</p>
                <p>Jenkins</p>
            </div>
        </div>

        <div class="info-keywords">
            <h2>Keyword</h2>
            <form class="info-company-form">
                <div class="div-input-button">
                    <div class="input-left">
                        <label>Keywords</label>
                        <input type="text">
                    </div>
                    <input type="submit" value="Add">
                </div>
            </form>
            <div class="list-data">
                <p>Happy</p>
                <p>Bad</p>
                <p>Bold</p>
                <p>Leadership</p>
                <p>Well explained</p>
            </div>
        </div>
    </main>
</body>
</html>
