package Structs;


import Data_Structures.Data_Structures.*;

public class TimeDiff {
    
    private Punch start;
    private Punch stop;
    private int[] timeDifference;
    String[] units = {"Years", "Months", "Days", "Hours", "Minutes", "Seconds"};



    public Punch getStart(){
        return this.start;
    }



    public void setStart(Punch start){
        this.start = start;
    }



    public Punch getStop(){
        return this.stop;
    }



    public void setStop(Punch stop){
        this.stop = stop;
    }



    public void setTimeDifference(int[] diff){
        this.timeDifference = diff;
    }



    public int[] getTimeDifference(){
        return this.timeDifference;
    }



    public void addTimeDiff(int[] diff){
        //seconds
        this.timeDifference[5] = diff[5] + this.timeDifference[5];
        int carry = 0;
        if (this.timeDifference[5] >= 60){
            carry = 1;
            this.timeDifference[5] = this.timeDifference[5] - 60;
        }
        else {
            carry = 0;
        }
        //minute
        this.timeDifference[4] = carry + diff[4] + this.timeDifference[4];
        if (this.timeDifference[4] >= 60){
            carry = 1;
            this.timeDifference[4] = this.timeDifference[4] - 60;
        }
        else {
            carry = 0;
        }
        //hour
        this.timeDifference[3] = carry + diff[3] + this.timeDifference[3];
        if (this.timeDifference[3] >= 24){
            carry = 1;
            this.timeDifference[3] = this.timeDifference[3] - 24;
        }
        else {
            carry = 0;
        }
        //day
        this.timeDifference[2] = carry + diff[2] + this.timeDifference[2];
        if (this.timeDifference[2] >= this.stop.daysInMonth()){
            carry = 1;
            this.timeDifference[2] = this.timeDifference[2] - this.stop.daysInMonth();
        }
        else {
            carry = 0;
        }
        //month
        this.timeDifference[1] = carry + diff[1] + this.timeDifference[1];
        if (this.timeDifference[1] >= 12){
            carry = 1;
            this.timeDifference[1] = this.timeDifference[1] - 12;
        }
        else {
            carry = 0;
        }
        //year
        this.timeDifference[0] = carry + diff[0]+ this.timeDifference[0];
    }



    public void addTimeDiff(Que<Integer> diff){
        //seconds
        this.timeDifference[5] = diff.pull(5) + this.timeDifference[5];
        int carry = 0;
        if (this.timeDifference[5] >= 60){
            carry = 1;
            this.timeDifference[5] = this.timeDifference[5] - 60;
        }
        else {
            carry = 0;
        }
        //minute
        this.timeDifference[4] = carry + diff.pull(4) + this.timeDifference[4];
        if (this.timeDifference[4] >= 60){
            carry = 1;
            this.timeDifference[4] = 60 - this.timeDifference[4];
        }
        else {
            carry = 0;
        }
        //hour
        this.timeDifference[3] = carry + diff.pull(3) + this.timeDifference[3];
        if (this.timeDifference[3] >= 60){
            carry = 1;
            this.timeDifference[3] = 24 - this.timeDifference[3];
        }
        else {
            carry = 0;
        }
        //day
        this.timeDifference[2] = carry + diff.pull(2) + this.timeDifference[2];
        if (this.timeDifference[2] >= this.stop.daysInMonth()){
            carry = 1;
            this.timeDifference[2] = this.stop.daysInMonth() - this.timeDifference[2];
        }
        else {
            carry = 0;
        }
        //month
        this.timeDifference[1] = carry + diff.pull(1) + this.timeDifference[1];
        if (this.timeDifference[1] >= 12){
            carry = 1;
            this.timeDifference[1] = this.timeDifference[1] - 12;
        }
        else {
            carry = 0;
        }
        //year
        this.timeDifference[0] = carry + diff.pull() + this.timeDifference[0];
    }

    public int[] getTotalHoursForTimeDiff(){
        int[] theAnswer = new int[3];
        theAnswer[0] = this.timeDifference[3];
        theAnswer[1] = this.timeDifference[4];
        theAnswer[2] = this.timeDifference[5];
        int carry = 0;
        if(this.timeDifference[0] != 0){
            carry = carry + this.yearsToHours(this.timeDifference[0]);
        }
        if(this.timeDifference[1] != 0){
            carry = carry + this.monthsToHours(this.timeDifference[1]);
        }
        if(this.timeDifference[2] != 0){
            carry = carry + this.daysToHours(this.timeDifference[2]);
        }
        theAnswer[0] = carry + this.timeDifference[3];
        return theAnswer;
    }

    public String getTimeDiffStr(){
        String theAnswer = "";
        for (int curIndx = 0; curIndx < this.timeDifference.length; curIndx = curIndx + 1){
            if (this.timeDifference[curIndx] != 0){
                theAnswer = theAnswer + Integer.toString(this.timeDifference[curIndx]) + " " + this.units[curIndx];
                if (curIndx != this.timeDifference.length - 1){
                    // seconds
                    theAnswer = theAnswer + " : ";
                }
            }
            
        }
        return theAnswer;
    }

    public String getTimesStr(){
        String theAnswer = "";
        String theAnswerTwo = "";
        if(this.timeDifference[0] != 0){
            theAnswer = "[ Year: " + Integer.toString(this.start.getYear());
            theAnswerTwo = "[ Year: " + Integer.toString(this.stop.getYear());
        }
        else if(this.timeDifference[1] != 0){
            if(this.timeDifference[0] != 0){
                theAnswer = theAnswer + ", Month: ";
                theAnswerTwo = theAnswerTwo + ", Month: ";
            }
            else {
                theAnswer = theAnswer + "[ Month: ";
                theAnswerTwo = theAnswerTwo + "[ Month: ";
            }
            theAnswer = theAnswer + this.start.getMonthStr();
            theAnswerTwo = theAnswerTwo + this.stop.getMonthStr();
        }
        else if(this.timeDifference[2] != 0){
            if((this.timeDifference[0] != 0) || (this.timeDifference[1] != 0)){
                theAnswer = theAnswer + ", Day: ";
                theAnswerTwo = theAnswerTwo + ", Day: ";
            }
            else {
                theAnswer = theAnswer + "[ Day: ";
                theAnswerTwo = theAnswerTwo + "[ Day: ";
            }
            theAnswer = theAnswer + this.start.getDayStr() + ", the " + Integer.toString(this.start.getDayOfMonth()) + " ";
            theAnswerTwo = theAnswerTwo + this.stop.getDayStr() + ", the " + Integer.toString(this.stop.getDayOfMonth()) + " ";
            theAnswer = theAnswer + "], ";
            theAnswerTwo = theAnswerTwo + "], ";
        }
        theAnswer = theAnswer + Integer.toString(this.start.getHour()) + " : " + Integer.toString(this.start.getMinute()) + " : " + Integer.toString(this.start.getSeconds());
        theAnswerTwo = theAnswerTwo + Integer.toString(this.stop.getHour()) + " : " + Integer.toString(this.stop.getMinute()) + " : " + Integer.toString(this.stop.getSeconds());
        return theAnswer + " --> " + theAnswerTwo;
    }

    private int daysToHours(int days){
        return days * 24;
    }

    private int monthsToHours(int months){
        int theAnswer = this.stop.getMonthInt();
        for(int curIndx = this.stop.getMonthInt(); curIndx > Math.abs(this.stop.getMonthInt() + months); curIndx = curIndx - 1){
            theAnswer = theAnswer + (this.stop.getDaysOfMonthsAllYear().peek(curIndx) * 24);
        }
        return theAnswer;
    }

    private int yearsToHours(int years){
        return years * 365 * 24;
    }

    private void timeDiff(Punch past, Punch future){
        int[] theAnswer = new int[6];
        theAnswer[5] = future.getSeconds() - past.getSeconds();
        int carry = 0;
        if (theAnswer[5] < 0){
            carry = -1;
            theAnswer[5] = theAnswer[5] + 60;
        }
        else {
            carry = 0;
        }
        theAnswer[4] = carry + future.getMinute() - past.getMinute();
        if(theAnswer[4] < 0){
            carry = -1;
            theAnswer[4] = theAnswer[4] + 60;
        }
        else {
            carry = 0;
        }
        theAnswer[3] = carry + future.getHour() - past.getHour();
        if (theAnswer[3] < 0){
            carry = -1;
            theAnswer[3] = theAnswer[3] + 24;
        }
        else {
            carry = 0;
        }
        theAnswer[2] = carry + future.getDayInt() - past.getDayInt();
        if (theAnswer[2] < 0){
            carry = -1;
            theAnswer[2] = theAnswer[2] + future.daysInMonth();
        }
        else {
            carry = 0;
        }
        theAnswer[1] = carry + future.getMonthInt() - past.getMonthInt();
        if (theAnswer[1] < 0){
            carry = -1;
            theAnswer[1] = theAnswer[1] + 12;
        }
        theAnswer[0] = carry + future.getYear() - past.getYear();
        if (theAnswer[0] < 0){
            theAnswer[0] = 0;
        }
        this.timeDifference = theAnswer;
    }

    private Que<String> breakIntoQue(String input, char breakPoint){
        Que<String> theAnswer = new Que<String>();
        int curIndx = 0;
        String temp = input;
        while(curIndx < input.length()){
            int stopIndex = temp.indexOf(breakPoint);
            if(stopIndex == -1){
                stopIndex = temp.length();
            }
            String thisStr = temp.substring(0, stopIndex);
            thisStr = thisStr.replaceAll(" ", "");
            theAnswer.push(thisStr);
            if(stopIndex == temp.length()){
                break;
            }
            else {
                temp = temp.substring(stopIndex + 1, temp.length());
            }
        }
        return theAnswer;
    }

    private int[] getDayIndexes(String workBlock, boolean start){
        int[] theAnswer = {-1, -1};
        Punch timeUnit = this.start;
        if(!start){
            timeUnit = this.stop;
        }
        int dayStrIndx = workBlock.indexOf("Day:") + ("Day:").length() + 1;
        String dayStr = workBlock.substring(dayStrIndx, dayStrIndx + timeUnit.getDayStr().length());
        theAnswer[0] = dayStrIndx;
        theAnswer[1] = theAnswer[0] + dayStr.length();
        return theAnswer;
    }

    private int[] getMonthIndexes(String workBlock, boolean start){
        int[] theAnswer = {-1, -1};
        Punch timeUnit = this.start;
        if(!start){
            timeUnit = this.stop;
        }
        int monthIndx = workBlock.indexOf("Month") + ("Month: ").length() + 2;
        String month = workBlock.substring(monthIndx, monthIndx + timeUnit.getMonthStr().length());
        theAnswer[0] = workBlock.indexOf(month);
        theAnswer[1] = theAnswer[0] + month.length();
        return theAnswer;
    }

    private int[] getYearIndexes(String workBlock){
        int[] theAnswer = {-1, -1};
        int yearIndx = workBlock.indexOf("Year") + ("Year: ").length();
        String year = workBlock.substring(yearIndx , workBlock.indexOf(","));
        theAnswer[0] = workBlock.indexOf(year);
        theAnswer[1] = theAnswer[0] + year.length();
        return theAnswer;
    }

    /**
     * This method takes an
     * @param timeBlock
     * @param addTo
     * @return
     */
    private void getLargerTimeUnits(String timeBlock, Que<String> addTo, boolean start){
        int startIndx = timeBlock.indexOf("[");
        int stopIndx = timeBlock.indexOf("]");
        String day = "0";
        String month = "0";
        String year = "0";
        if((startIndx != -1) || (stopIndx != -1)){
            int[] dayIndexes = this.getDayIndexes(timeBlock.substring(startIndx, stopIndx + 1), start);
            int[] monthIndexes = this.getMonthIndexes(timeBlock.substring(startIndx, stopIndx + 1), start);
            int[] yearIndexes = this.getYearIndexes(timeBlock.substring(startIndx, stopIndx + 1));
            if((dayIndexes[0] != -1) && (dayIndexes[1] != -1)){
                for(int curIndx = 0; curIndx < this.start.getDays().length; curIndx = curIndx + 1){
                    if (this.start.getDays()[curIndx].equalsIgnoreCase(timeBlock.substring(dayIndexes[0], dayIndexes[1]))){
                        day = Integer.toString(curIndx);
                        break;
                    }
                }
            }
            if((monthIndexes[0] != -1) && (monthIndexes[1] != -1)){
                for(int curIndx = 0; curIndx < this.start.getDays().length; curIndx = curIndx + 1){
                    if(this.start.getMonths()[curIndx].equalsIgnoreCase(timeBlock.substring(monthIndexes[0], monthIndexes[1]))){
                        month = Integer.toString(curIndx);
                        break;
                    }
                }
            }
            if((yearIndexes[0] != -1) && (yearIndexes[1] != -1)){
                year = timeBlock.substring(yearIndexes[0], yearIndexes[1]);
            }
        }
        addTo.push(year, 0);
        addTo.push(month, 1);
        addTo.push(day, 2);
    }

    private void setPunch(Que<Integer> punchData, boolean start){
        if(start){
            this.start.setYear(punchData.pull());
            this.start.setMonth(punchData.pull());
            this.start.setDay(punchData.pull());
            this.start.setHour(punchData.pull());
            this.start.setMinute(punchData.pull());
            this.start.setSecond(punchData.pull());
        }
        else {
            this.stop.setYear(punchData.pull());
            this.stop.setMonth(punchData.pull());
            this.stop.setDay(punchData.pull());
            this.stop.setHour(punchData.pull());
            this.stop.setMinute(punchData.pull());
            this.stop.setSecond(punchData.pull());
        }
    }

    private Que<Integer> convertToInt(Que<String> stringQue){
        Que<Integer> theAnswer = new Que<Integer>();
        for(int curIndx = 0; curIndx < stringQue.getSize(); curIndx = curIndx + 1){
            theAnswer.push(Integer.parseInt(stringQue.peek(curIndx)));
        }
        return theAnswer;
    }

    public String[] getIndexes(){
        return new String[] {"Year", "Month", "Day", "Hour", "Minute", "Second"};
    }

    public TimeDiff(){
        this.start = null;
        this.stop = null;
        this.timeDifference = new int[]{0, 0, 0, 0, 0, 0};
    }

    public TimeDiff(Punch start, Punch stop){
        this.start = start;
        this.stop = stop;
        this.timeDiff(this.start, this.stop);
    }

    public TimeDiff(String start, String stop){
        this.start = new Punch();
        this.stop = new Punch();
        Que<String> startingStr = new Que<String>();
        Que<String> stopingStr = new Que<String>();
        this.getLargerTimeUnits(start.substring(0, start.indexOf("]") + 1), startingStr, true);
        this.getLargerTimeUnits(stop.substring(1, start.indexOf("]") + 2), stopingStr, false);
        int startingIndx = 0;
        if(start.indexOf("]") != -1){
            startingIndx = start.indexOf("]") + 3;
        }
        startingStr.attachQue(this.breakIntoQue(start.substring(startingIndx), ':'));
        if(stop.indexOf("]") != -1){
            startingIndx = stop.indexOf("]") + 3;
        }
        stopingStr.attachQue(this.breakIntoQue(stop.substring(startingIndx), ':'));
        Que<Integer> startingTime = this.convertToInt(startingStr);
        Que<Integer> stoppingTime = this.convertToInt(stopingStr);
        this.setPunch(startingTime, true);
        this.setPunch(stoppingTime, false);
        this.timeDiff(this.start, this.stop);
    }
}