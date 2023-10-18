package com.example.myapplication.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaderboardScore {
    private String name;
    private double score;
    private String datetime;

    public LeaderboardScore(String name, double score) {
        this.name = name;
        this.score = score;
        // Create a Date object to represent the current date and time
        Date currentDate = new Date();
        // Create a SimpleDateFormat object to specify the format of the output
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Use the format method to convert the Date to a formatted string
        String currentDateTimeString = dateFormat.format(currentDate);

        this.datetime = currentDateTimeString;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public String getDatetime() {
        return datetime;
    }
}
