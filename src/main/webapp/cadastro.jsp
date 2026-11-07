<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.github.gabrielsilper.model.City" %>
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
    <%
        Person personToEdit = (Person) request.getAttribute("personToEdit");
        String personToEditIndex = (String) request.getAttribute("personToEditIndex");

        boolean isEdit = personToEdit != null;

        String action = isEdit ? "/pessoas?action=save&person=" + personToEditIndex : "/pessoas?action=save";

        String startTitle = isEdit ? "Editar" : "Cadastro de";
        String nameValue = isEdit ? personToEdit.getName() : "";
        String emailValue = isEdit ? personToEdit.getEmail() : "";
        String phoneValue = isEdit ? personToEdit.getPhone() : "";
    %>

    <h1><%= startTitle %> pessoa com JSP e Servlet</h1>

    <form method="POST" action="<%= action %>">
        <label for="name">Nome:</label>
        <input type="text" id="name" name="name" value="<%= nameValue %>">

        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="<%= emailValue %>">

        <label for="phone">Telefone:</label>
        <input type="text" id="phone" name="phone" value="<%= phoneValue %>">

        <label for="city">Cidade:</label>
        <select id="city" name="city" required>

        <%
            List<City> cityList = (List<City>) request.getAttribute("cityList");

            for(int i = 0; i < cityList.size(); i++){
                City city = cityList.get(i);
                boolean selected = isEdit
                    && personToEdit.getCity() != null
                    && personToEdit.getCity().getName().equals(city.getName());
        %>

            <option value="<%= i %>"  <%= selected ? "selected" : "" %>>
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