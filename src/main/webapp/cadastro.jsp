<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.github.gabrielsilper.model.City" %>
<%@ page import="java.util.List" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cadastro de pessoas - JSP</title>
</head>
<body>
    <h1>Cadastro de pessoas com JSP e Servlet</h1>

    <form method="POST" action="/pessoas?action=save">
        <label for="name">Nome:</label>
        <input type="text" id="name" name="name">

        <label for="email">Email:</label>
        <input type="text" id="email" name="email">

        <label for="phone">Telefone:</label>
        <input type="text" id="phone" name="phone">

        <label for="city">Cidade:</label>
        <select id="city" name="city" required>

        <%
            List<City> cityList = (List<City>) request.getAttribute("cityList");

            for(int i = 0; i < cityList.size(); i++){
                City city = cityList.get(i);
        %>

            <option value="<%= i %>">
                <%= city.getName() %>
            </option>

        <%
            }
        %>
        </select>

        <button type="submit">Salvar</button>
    </form>
</body>
</html>