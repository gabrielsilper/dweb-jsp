package com.github.gabrielsilper.model;

import java.util.Objects;

public class State {
    private String name;
    private String stateCode;
    private Country country;

    public State() {
    }

    public State(String name, String stateCode, Country country) {
        this.name = name;
        this.stateCode = stateCode;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(name, state.name)
                && Objects.equals(stateCode, state.stateCode)
                && Objects.equals(country, state.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stateCode, country);
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", stateCode='" + stateCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
