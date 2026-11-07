package com.github.gabrielsilper.servlet;

import com.github.gabrielsilper.model.City;
import com.github.gabrielsilper.model.Country;
import com.github.gabrielsilper.model.Person;
import com.github.gabrielsilper.model.State;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class PersonServlet extends HttpServlet {
    private final ArrayList<Person> personList;
    private final ArrayList<City> cityList;

    public PersonServlet() {
        this.cityList = new ArrayList<>();
        this.personList = new ArrayList<>();
    }

    public PersonServlet(ArrayList<Person> personList, ArrayList<City> cityList) {
        this.personList = personList;
        this.cityList = cityList;
    }

    @Override
    public void init() throws ServletException {
        Country countryBrazil = new Country("Brasil");

        State stateAmazonas = new State();
        stateAmazonas.setName("Amazonas");
        stateAmazonas.setStateCode("AM");
        stateAmazonas.setCountry(countryBrazil);

        State statePara = new State();
        statePara.setName("Pará");
        statePara.setStateCode("PA");
        statePara.setCountry(countryBrazil);

        City cityManaus = new City();
        cityManaus.setName("Manaus");
        cityManaus.setState(stateAmazonas);

        City cityIranduba = new City();
        cityIranduba.setName("Iranduba");
        cityIranduba.setState(stateAmazonas);

        City citySantarem = new City();
        citySantarem.setName("Santarém");
        citySantarem.setState(statePara);

        cityList.add(cityManaus);
        cityList.add(cityIranduba);
        cityList.add(citySantarem);

        Person person1 = new Person("Gabriel", "gabriel@test.com", "92 99999-9999", cityManaus);

        Person person2 = new Person("Fulano", "fulano@test.com", "92 99888-8888", cityManaus);

        Person person3 = new Person("Beltrana", "beltrana@test.com", "92 99777-7777", cityManaus);

        this.personList.add(person1);
        this.personList.add(person2);
        this.personList.add(person3);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("register".equals(action)) {
            req.setAttribute("cityList", this.cityList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("cadastro.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if ("delete".equals(action)) {
            String personIndex = req.getParameter("id");

            if (personIndex != null && !personIndex.isBlank()) {
                int index = Integer.parseInt(personIndex);
                personList.remove(index);
            }

            resp.sendRedirect("/pessoas");
            return;
        }

        if ("edit".equals(action)) {
            String personIndex = req.getParameter("id");
            if (personIndex != null && !personIndex.isBlank()) {
                int index = Integer.parseInt(personIndex);
                Person person = personList.get(index);

                req.setAttribute("personToEdit", person);
                req.setAttribute("personToEditIndex", String.valueOf(index));
                req.setAttribute("cityList", this.cityList);

                RequestDispatcher dispatcher = req.getRequestDispatcher("cadastro.jsp");
                dispatcher.forward(req, resp);
            }
        }

        req.setAttribute("personList", this.personList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("pessoas.jsp");
        dispatcher.forward(req, resp);
    }

    private void salvarPessoa(HttpServletRequest req) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String cityIndex = req.getParameter("city");
        if (name == null || name.isBlank()
                || email == null || email.isBlank()
                || phone == null || phone.isBlank()
                || cityIndex == null || cityIndex.isBlank()) {
            return;
        }

        int cityId = Integer.parseInt(cityIndex);
        City selectedCity = this.cityList.get(cityId);

        Person person = new Person(name, email, phone, selectedCity);

        this.personList.add(person);
    }

    private void editarPessoa(HttpServletRequest req) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String cityIndex = req.getParameter("city");
        String personIndex = req.getParameter("person");
        if (name == null || name.isBlank()
                || email == null || email.isBlank()
                || phone == null || phone.isBlank()
                || cityIndex == null || cityIndex.isBlank()
                || personIndex == null || personIndex.isBlank()) {
            return;
        }

        int cityId = Integer.parseInt(cityIndex);
        City selectedCity = this.cityList.get(cityId);

        Person person = new Person(name, email, phone, selectedCity);

        int personId = Integer.parseInt(personIndex);
        this.personList.set(personId, person);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("save".equals(action)) {
            salvarPessoa(req);
        }

        if ("saveEdit".equals(action)) {
            editarPessoa(req);
        }

        resp.sendRedirect("/pessoas");
    }
}
