<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.github.gabrielsilper.model.Person" %>
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

    <form method="GET" action="/pessoas?action=save">
        <label for="name">Nome:</label>
        <input type="text" id="name" name="name">

        <label for="email">Email:</label>
        <input type="text" id="email" name="email">

        <label for="phone">Telefone:</label>
        <input type="text" id="phone" name="phone">

        <button type="submit">Salvar</button>
    </form>
</body>
</html>