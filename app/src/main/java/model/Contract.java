package model;

import java.io.Serializable;

/**
 * Created by Vu Khac Hoi on 10/2/2017.
 */

public class Contract implements Serializable {


    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getNumberChair() {
        return NumberChair;
    }

    public void setNumberChair(String numberChair) {
        NumberChair = numberChair;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Contract(String clientName, String numberChair, String route, String time, String driver, String variable, String telephone, String idcar) {
        ClientName = clientName;
        NumberChair = numberChair;
        Route = route;
        Time = time;
        Driver = driver;
        Variable = variable;
        Telephone = telephone;
        this.idcar = idcar;
    }

    String ClientName;
    String NumberChair;
    String Route;
    String Time;

    public String getDriver() {
        return Driver;
    }

    public void setDriver(String driver) {
        Driver = driver;
    }

    public String getVariable() {
        return Variable;
    }

    public void setVariable(String variable) {
        Variable = variable;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getIdcar() {
        return idcar;
    }

    public void setIdcar(String idcar) {
        this.idcar = idcar;
    }

    String Driver;
    String Variable;
    String Telephone;
    String idcar;

}
