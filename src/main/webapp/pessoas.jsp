<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.github.gabrielsilper.model.Person" %>
<%@ page import="java.util.List" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Gerenciamento de pessoas - JSP</title>
</head>
<body>
    <h1>Gerenciamento de pessoas com JSP e Servlet</h1>

    <table border="1" cellPadding"5">
        <caption>Tabela de pessoas</caption>
        <thead>
            <tr>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Email</th>
                <th>Cidade</th>
                <th>Estado</th>
                <th>País</th>
            </tr>
        </thead>
    <%
        // Scriptlet
        List<Person> personList = (List<Person>) request.getAttribute("personList");

        if (personList != null) {
            for(Person person : personList){
    %>

        <tbody>
            <tr>
                <td><% out.println(person.getName()); %></td>
                <td><% out.println(person.getPhone()); %></td>
                <td><% out.println(person.getEmail()); %></td>
                <td><% out.println(person.getCity().getName()); %></td>
                <td><% out.println(person.getCity().getState().getName()); %></td>
                <td><% out.println(person.getCity().getState().getCountry().getName()); %></td>
            </tr>
        </tbody>

    <%
            }
        }
    %>
    </table>

    <a href="pessoas?action=register">Cadastrar Pessoa</a>
</body>
</html>