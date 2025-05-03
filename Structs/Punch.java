package Structs;

import java.util.Calendar;
import java.util.Scanner;

import Data_Structures.Data_Structures.Que;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;


/**
 * A punch object is an object with the punch time.
 */
public class Punch {

    private Calendar punchTime;
    private LocalDate dateObj;
    private int year;
    private int monthInt;
    private String monthStr;
    private boolean leapYear;
    private int dayInt;
    private int dayOfMonth;
    private String dayStr;
    private int hour;
    private int minute;
    private int second;
    
   
    public String[] getMonths(){
        return new String[] {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    }


    public String[] getDays(){
        return new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    }


    private int getStringIndex(String[] array, String data, int index){
        if (array[index].equalsIgnoreCase(data)){
            return index;
        }
        else {
            return this.getStringIndex(array, data, index + 1);
        }
    }

    public Calendar getPunchTime(){
        return this.punchTime;
    }

    public LocalDate getDateObj(){
        return this.dateObj;
    }

    public void setDateObj(LocalDate date){
        this.dateObj = date;
    }

    public boolean isLeapYear(){
        return this.leapYear;
    }

    public void setLeapYear(boolean leap){
        this.leapYear = leap;
    }

    public void setPunchTime(Calendar punch){
        this.punchTime = punch;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getYear(){
        return this.year;
    }

    public void setMonth(int month){
        this.monthInt = month;
        this.monthStr = this.getMonths()[this.monthInt];
    }

    public void setMonth(String month){
        this.monthInt = this.getStringIndex(this.getMonths(), month, 0);
        this.monthStr = this.getMonths()[this.monthInt];
    }

    public int getMonthInt(){
        return this.monthInt;
    }

    public String getMonthStr(){
        return this.monthStr;
    }

    public void setDay(int day){
        this.dayInt = day;
        this.dayStr = this.getDays()[this.dayInt];
    }

    public void setDay(String day){
        this.dayInt = this.getStringIndex(this.getDays(), day, 0);
        this.dayStr = this.getDays()[this.dayInt - 1];
    }

    public void setDayOfMonth(int day){
        this.dayOfMonth = day;
    }

    public int getDayOfMonth(){
        return this.dayOfMonth;
    }

    public int getDayInt(){
        return this.dayInt;
    }

    public String getDayStr(){
        return this.dayStr;
    }

    public void setHour(int hour){
        this.hour = hour;
    }

    public int getHour(){
        return this.hour;
    }

    public void setMinute(int min){
        this.minute = min;
    }

    public int getMinute(){
        return this.minute;
    }

    public void setSecond(int sec){
        this.second = sec;
    }

    public int getSeconds(){
        return this.second;
    }

    public void save(File file){
        try {
            PrintStream print = new PrintStream(file);
            print.println("Year : " + this.year);
            print.println("Month : " + this.monthInt);
            print.println("Day of Month : " + this.dayOfMonth);
            print.println("Day : " + this.dayInt );
            print.println("Hour : " + this.hour);
            print.println("Minute : " + this.minute);
            print.println("Second : " + this.second);
            print.close();
        } catch (FileNotFoundException exception) {
            
            exception.printStackTrace();
        }
    }

    public int daysInMonth(){
        return this.getDaysOfMonth()[this.getMonthInt()];
    }

    public int daysInYear(){
        int theAnswer = 365;
        if (this.leapYear == true){
            theAnswer = theAnswer - 1;
        }
        return theAnswer;
    }

    public void addTimeDiff(TimeDiff extraTime){
        int carry = 0;
        this.second = extraTime.getTimeDifference()[5] + this.second;
        if (this.second >= 60){
            carry = 1;
            this.second = this.second - 60;
        }
        this.minute = carry + this.minute + extraTime.getTimeDifference()[4];
        if (this.minute >= 60){
            carry = 1;
            this.minute = this.minute - 60;
        }
        else {
            carry = 0;
        }
        this.hour = carry + this.minute + extraTime.getTimeDifference()[3];
        if (this.hour >= 24){
            carry = 1;
            this.hour = this.hour - 24;
        }
        else {
            carry = 0;
        }
        this.dayInt = carry + this.dayInt + extraTime.getTimeDifference()[2];
        if (this.dayInt >= 7){
            carry = 1;
            this.dayInt = this.dayInt - 7;
        }
        else {
            carry = 0;
        }
        this.dayStr = this.getDays()[this.dayInt - 1];
        this.monthInt = carry + this.monthInt + extraTime.getTimeDifference()[1];
        if (this.monthInt >= 12){
            carry = 1;
            this.monthInt = 12 - this.monthInt;
            this.monthStr = this.getMonths()[this.monthInt];
        }
        else {
            carry = 0;
        }
        this.year = carry + this.year + extraTime.getTimeDifference()[0];
        
    }

    private int[] getDaysOfMonth(){
        return new int[] {31, this.leapYear ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    }

    public Que<Integer> getDaysOfMonthsAllYear(){
        Que<Integer> theAnswer = new Que<Integer>();
        for (int curIndx = 0; curIndx < this.getDaysOfMonth().length; curIndx = curIndx + 1){
            theAnswer.push(this.getDaysOfMonth()[curIndx]);
        }
        return theAnswer;
    }

    public void getFromFile(File file) throws IOException{
        Scanner reader = new Scanner(file);
        this.year = Integer.parseInt(reader.nextLine().substring(("Year : ").length()));
        this.monthInt = Integer.parseInt(reader.nextLine().substring(("Month : ").length()));
        this.dayOfMonth = Integer.parseInt(reader.nextLine().substring(("Day of Month : ").length()));
        this.monthStr = this.getMonths()[this.monthInt];
        this.dayInt = Integer.parseInt(reader.nextLine().substring(("Day : ").length()));
        this.dayStr = this.getDays()[this.dayInt];
        this.hour = Integer.parseInt(reader.nextLine().substring(("Hour : ").length()));
        this.minute = Integer.parseInt(reader.nextLine().substring(("Minute : ").length()));
        this.second = Integer.parseInt(reader.nextLine().substring(("Second : ").length()));
        reader.close();
    }

    public Punch(File file) throws IOException{
        this.getFromFile(file);
    }

    public Punch(){
        this.punchTime = Calendar.getInstance();
        this.year = punchTime.get(Calendar.YEAR);
        this.monthInt = punchTime.get(Calendar.MONTH);
        this.monthStr = this.getMonths()[this.monthInt];
        this.dayInt = this.punchTime.get(Calendar.DAY_OF_WEEK) - 1;
        this.dayOfMonth = this.punchTime.get(Calendar.DAY_OF_MONTH);
        this.dateObj = LocalDate.now();
        this.leapYear = this.dateObj.isLeapYear();
        this.dayStr = this.getDays()[this.dayInt];
        this.hour = this.punchTime.get(Calendar.HOUR_OF_DAY);
        this.minute = this.punchTime.get(Calendar.MINUTE);
        this.second = this.punchTime.get(Calendar.SECOND);
    }
    
}
