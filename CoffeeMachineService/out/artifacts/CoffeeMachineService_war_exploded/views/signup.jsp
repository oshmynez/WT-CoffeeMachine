<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru" >
<head>
    <meta charset="UTF-8">
    <title>Coffee-MachineService</title>
    <link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Arimo' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Hind:300' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="../stylesCSS/signup.css">
</head>

<body>
<header class="headerContainer">
<div class="profileItem">
    <button class="profile" value="Refresh Page" onclick="window.location.reload();" >Coffee</button>
</div>
<div class="profileItem">
    <button class="profile" onclick="location.href='/orders'" >Заказы</button>
</div>
<div class="profileItem">
    <button class="profile" onclick="location.href='/exit'" >Выход</button>
</div>
<div class="language">
    <label>
        <select class="select " >
            <option value="1">ru</option>
            <option value="2">en</option>
        </select>
    </label>
</div>
</header>
<div id="sign-up-container">
    <h1>Sign Up</h1>
    <form method="post">
        <label>
            <input type="email" name="email" placeholder="E-mail">
        </label>
        <label>
            <input type="text" name="name" placeholder="Name">
        </label>
        <label>
            <input type="password" name="pass" placeholder="Password">
        </label>
        <button class="buttonSignUp" onclick="location.href='/signup'" >Sign Up</button>
    </form>
    <div  style="
                  margin-left: 24px;
                  margin-top: 15px;
                  color: white ">
        <% if (request.getAttribute("resultRegistration") != null)
            out.println(request.getAttribute("resultRegistration"));
        %>
    </div>

</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/gsap/1.16.1/TweenMax.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script src="../jsfiles/buttonAuthorization.js"></script>

</body>
</html>
