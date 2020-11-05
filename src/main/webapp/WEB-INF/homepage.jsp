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
        <p>John Doe</p>
        <p>Logout</p>
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
        <table class="homepage-table">
            <tr>
                <th>#</th>
                <th>Group</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>C.d.C</th>
                <th>Company eval</th>
                <th>Web poll</th>
                <th>Released report</th>
                <th>Defense</th><!-- Soutenance -->
                <th>Planned Visit</th>
                <!-- Todo complete with the other categories -->
            </tr>
            <tr>
                <td><input type="radio"></td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
            </tr>
            <tr>
                <td><input type="radio"></td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
                <td>dlskf</td>
            </tr>
        </table>
        <div class="homepage-button">
            <button>Add</button>
            <button>Validate</button>
            <button>Details</button>
        </div>
    </div>
</main>
</body>
</html>