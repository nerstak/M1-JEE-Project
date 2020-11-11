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
            <div class="homepage-selector">
                <div class="homepage-selector-select">
                    <div>
                        <h2>Year</h2>
                        <p>2020</p>
                    </div>
                    <div>
                        <h2>Keyword</h2>
                        <p>C++</p>
                    </div>
                    <div>
                        <h2>Keyword</h2>
                        <p>Smith</p>
                    </div>
                </div>
                <div class="homepage-selector-search">
                    <input type="text">
                </div>
            </div>
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
                    <form class="homepage-row" name="form-1" method="post" action="">
                        <span class="homepage-cell">
                            <div>
                                <input type="submit" value="modify"/>
                                <input type="submit" value="details">
                            </div>
                        </span>
                        <span class="homepage-cell"><input type="text" value="a807467a-12a8-46f7-a45c-0546117176f0"/></span>
                        <span class="homepage-cell"><input type="text" value="2022"/></span>
                        <span class="homepage-cell"><input type="text" value="John"/></span>
                        <span class="homepage-cell"><input type="text" value="Doe"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="date" /></span>
                        <span class="homepage-cell"><input type="date" /></span>
                        <span class="homepage-cell"><input type="text" value="Jacques Augustin"/></span>
                        <span class="homepage-cell"><input type="text" value="30 avenue de la République"/></span>
                        <span class="homepage-cell"><input type="text" value="12"/></span>
                        <span class="homepage-cell"><input type="text" value="14"/></span>
                    </form>
                    <form class="homepage-row" name="form-1" method="post" action="">
                        <span class="homepage-cell">
                            <div>
                                <input type="submit" value="modify"/>
                                <input type="submit" value="details">
                            </div>
                        </span>
                        <span class="homepage-cell"><input type="text" value="a807467a-12a8-46f7-a45c-0546117176f0"/></span>
                        <span class="homepage-cell"><input type="text" value="2022"/></span>
                        <span class="homepage-cell"><input type="text" value="John"/></span>
                        <span class="homepage-cell"><input type="text" value="Doe"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="checkbox"/></span>
                        <span class="homepage-cell"><input type="date" /></span>
                        <span class="homepage-cell"><input type="date" /></span>
                        <span class="homepage-cell"><input type="text" value="Jacques Augustin"/></span>
                        <span class="homepage-cell"><input type="text" value="30 avenue de la République"/></span>
                        <span class="homepage-cell"><input type="text" value="12"/></span>
                        <span class="homepage-cell"><input type="text" value="14"/></span>
                    </form>

                </div>
            </div>
        </div>
    </main>
</body>
</html>