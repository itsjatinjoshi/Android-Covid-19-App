package com.example.androidpracticeforconcepclearing.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class States {

    @SerializedName("state")
    @Expose
    private String stateName;

    @SerializedName("cases")
    @Expose
    private String stateCase;

    @SerializedName("todayCases")
    @Expose
    private String stateTodayCases;

    @SerializedName("deaths")
    @Expose
    private String stateDeaths;

    @SerializedName("active")
    @Expose
    private String stateActiveCases;

    @SerializedName("todayDeaths")
    @Expose
    private String stateTodayDeaths;

    @SerializedName("tests")
    @Expose
    private String stateTests;



    public States() {
    }

    public States(String stateName, String stateCase, String stateTodayCases, String stateDeaths,
                  String stateActiveCases, String stateTodayDeaths, String stateTests) {
        this.stateName = stateName;
        this.stateCase = stateCase;
        this.stateTodayCases = stateTodayCases;
        this.stateDeaths = stateDeaths;
        this.stateActiveCases = stateActiveCases;
        this.stateTodayDeaths = stateTodayDeaths;
        this.stateTests = stateTests;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCase() {
        return stateCase;
    }

    public void setStateCase(String stateCase) {
        this.stateCase = stateCase;
    }

    public String getStateTodayCases() {
        return stateTodayCases;
    }

    public void setStateTodayCases(String stateTodayCases) {
        this.stateTodayCases = stateTodayCases;
    }

    public String getStateDeaths() {
        return stateDeaths;
    }

    public void setStateDeaths(String stateDeaths) {
        this.stateDeaths = stateDeaths;
    }

    public String getStateActiveCases() {
        return stateActiveCases;
    }

    public void setStateActiveCases(String stateActiveCases) {
        this.stateActiveCases = stateActiveCases;
    }

    public String getStateTodayDeaths() {
        return stateTodayDeaths;
    }

    public void setStateTodayDeaths(String stateTodayDeaths) {
        this.stateTodayDeaths = stateTodayDeaths;
    }

    public String getStateTests() {
        return stateTests;
    }

    public void setStateTests(String stateTests) {
        this.stateTests = stateTests;
    }
}
