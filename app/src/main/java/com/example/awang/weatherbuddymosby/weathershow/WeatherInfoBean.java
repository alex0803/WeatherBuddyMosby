package com.example.awang.weatherbuddymosby.weathershow;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by awang on 2016-08-09.
 */
public class WeatherInfoBean {
    private City city;
    private String cod;
    private double message;
    private int cnt;

    @SerializedName("list")
    private List<WeatherList> weatherLists ;

    public void setCity(City city){
        this.city = city;
    }
    public City getCity(){
        return this.city;
    }
    public void setCod(String cod){
        this.cod = cod;
    }
    public String getCod(){
        return this.cod;
    }
    public void setMessage(double message){
        this.message = message;
    }
    public double getMessage(){
        return this.message;
    }
    public void setCnt(int cnt){
        this.cnt = cnt;
    }
    public int getCnt(){
        return this.cnt;
    }
    public void setList(List<WeatherList> weatherLists){
        this.weatherLists = weatherLists;
    }
    public List<WeatherList> getList(){
        return this.weatherLists;
    }

    public class City {
        private int id;

        private String name;

        private Coord coord;

        private String country;

        private int population;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setCoord(Coord coord){
            this.coord = coord;
        }
        public Coord getCoord(){
            return this.coord;
        }
        public void setCountry(String country){
            this.country = country;
        }
        public String getCountry(){
            return this.country;
        }
        public void setPopulation(int population){
            this.population = population;
        }
        public int getPopulation(){
            return this.population;
        }

    }
    public class Coord {
        private double lon;

        private double lat;

        public void setLon(double lon){
            this.lon = lon;
        }
        public double getLon(){
            return this.lon;
        }
        public void setLat(double lat){
            this.lat = lat;
        }
        public double getLat(){
            return this.lat;
        }

    }
    public class WeatherList {
        private int dt;
        private Temp temp;
        private double pressure;
        private int humidity;
        private List<Weather> weather ;
        private double speed;
        private int deg;
        private int clouds;
        private double rain;

        public void setDt(int dt){
            this.dt = dt;
        }
        public int getDt(){
            return this.dt;
        }
        public void setTemp(Temp temp){
            this.temp = temp;
        }
        public Temp getTemp(){
            return this.temp;
        }
        public void setPressure(double pressure){
            this.pressure = pressure;
        }
        public double getPressure(){
            return this.pressure;
        }
        public void setHumidity(int humidity){
            this.humidity = humidity;
        }
        public int getHumidity(){
            return this.humidity;
        }
        public void setWeather(List<Weather> weather){
            this.weather = weather;
        }
        public List<Weather> getWeather(){
            return this.weather;
        }
        public void setSpeed(double speed){
            this.speed = speed;
        }
        public double getSpeed(){
            return this.speed;
        }
        public void setDeg(int deg){
            this.deg = deg;
        }
        public int getDeg(){
            return this.deg;
        }
        public void setClouds(int clouds){
            this.clouds = clouds;
        }
        public int getClouds(){
            return this.clouds;
        }
        public void setRain(double rian){
            this.rain = rain;
        }
        public double getRain(){
            return this.rain;
        }
    }
    public class Temp {
        private double day;
        private double min;
        private double max;
        private double night;
        private double eve;
        private double morn;

        public void setDay(double day){
            this.day = day;
        }
        public double getDay(){
            return this.day;
        }
        public void setMin(double min){
            this.min = min;
        }
        public double getMin(){
            return this.min;
        }
        public void setMax(double max){
            this.max = max;
        }
        public double getMax(){
            return this.max;
        }
        public void setNight(double night){
            this.night = night;
        }
        public double getNight(){
            return this.night;
        }
        public void setEve(double eve){
            this.eve = eve;
        }
        public double getEve(){
            return this.eve;
        }
        public void setMorn(double morn){
            this.morn = morn;
        }
        public double getMorn(){
            return this.morn;
        }

    }
    public class Weather{
        private int id;
        private String main;
        private String description;
        private String icon;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setMain(String main){
            this.main = main;
        }
        public String getMain(){
            return this.main;
        }
        public void setDescription(String description){
            this.description = description;
        }
        public String getDescription(){
            return this.description;
        }
        public void setIcon(String icon){
            this.icon = icon;
        }
        public String getIcon(){
            return this.icon;
        }

    }
}
