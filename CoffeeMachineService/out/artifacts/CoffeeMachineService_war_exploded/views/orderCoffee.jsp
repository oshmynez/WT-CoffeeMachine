<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>CodePen - Custom Select Menu</title>
    <link rel="stylesheet" href="../stylesCSS/orderCoffee.css">
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
        <label for="language"></label><select id="language">
            <option value="1">ru</option>
            <option value="2">en</option>
        </select>
    </div>

</header>
<div style="text-align: center;"><h1>Make your Coffee</h1></div>
<div id="Types">
    <div style="text-align: center;">
        <form action="/orderCoffee" method="post">
        <select id="TypeCoffee" name="isTypeCoffee">
            <option value="Америкнао">Американо</option>
            <option value="Эспрессо">Эспрессо</option>
            <option value="Латте">Латте</option>
            <option value="Раф">Раф</option>
            <option value="Фраппе">Фраппе</option>
            <option value="Маккиато">Маккиато</option>
            <option value="Лунго">Лунго</option>
            <option value="Романо">Романо</option>
        </select>
        <select id="CoffeeBeans" name="isTypeBeans">
            <option value="hide">Зёрна</option>
            <option value="Арабика">Арабика</option>
            <option value="Робуста">Робуста</option>
            <option value="Либерика">Либерика</option>
            <option value="Эксцельза">Эксцельза</option>
        </select>

        <select id="Addities" name="isAdditives">
            <option value="hide">Добавки</option>
            <option value="Апельсиновый сироп">Апельсиновый сироп</option>
            <option value="Яблочный сироп">Яблочный сироп</option>
            <option value="Малиновый сироп">Малиновый сироп</option>
            <option value="Корица сироп">Корица сироп</option>
            <option value="Ваниль сироп">Ваниль сироп</option>
            <option value="Миндальный сироп"></option>
            <option value="Кедровый сироп">Кедровый сироп</option>
            <option value="Лесной орех сироп"></option>
            <option value="Шоколадный сироп">Шоколадный сироп</option>
            <option value="Тирамису сироп">Тирамису сироп</option>
            <option value="Пломбирный сироп">Пломбирный сироп</option>
            <option value="Виски">Виски</option>
            <option value="Молоко">Молоко</option>
            <option value="Сливки">Сливки</option>
            <option value="Сахар">Сахар</option>
            <option value="Без добавки">Без добавки</option>
        </select>
            <div style="text-align: center;">
                <button onclick="location.href='/orderCoffee'" class="addOrder" type="submit" >
                    Добавить
                </button>
            </div>
        </form>
    </div>
</div>

<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="../jsfiles/buttonOrder.js"></script>

</body>
</html>
