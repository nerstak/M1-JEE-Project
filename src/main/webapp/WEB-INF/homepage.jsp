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
                        <span class="homepage-cell">#</span>
                        <span class="homepage-cell">Group</span>
                        <span class="homepage-cell">First name</span>
                        <span class="homepage-cell">Last name</span>
                        <span class="homepage-cell">Last name</span>
                        <span class="homepage-cell">Last gd df gdf g fname</span>
                        <span class="homepage-cell">Last name</span>
                        <span class="homepage-cell">Last name</span>
                        <span class="homepage-cell">Last name</span>
                        <span class="homepage-cell">Last name</span>
                        <span class="homepage-cell">Last name</span>
                        <span class="homepage-cell">Last name</span>
                    </div>
                    <form class="homepage-row" method="post" action="blah.html">
                        <span class="homepage-cell"><input type="radio" /></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                    </form>
                    <form class="homepage-row" method="post" action="blah.html">
                        <span class="homepage-cell"><input type="radio" /></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                    </form>

                    <form class="homepage-row" method="post" action="blah.html">
                        <span class="homepage-cell"><input type="radio" /></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                        <span class="homepage-cell"><input type="text" value="slkfjkld"/></span>
                    </form>
                </div>
            </div>
            <div class="homepage-button">
                <input type="submit" value="Add"/>
                <input type="submit" value="Validate"/>
                <input type="submit" value="Details"/>
            </div>
        </div>
    </main>
</body>
</html>