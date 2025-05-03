package Structs;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintStream;
import Data_Structures.Data_Structures.Que;

public class app {

    private Punch start;
    private Punch stop;
    private String fileDirectory;
    private String tempFileDirectory;

    public app(){
        this.start = null;
        this.stop = null;
	    this.fileDirectory = "files/week_hours.txt";
        this.tempFileDirectory = "files/temp.txt";
    }

    public static void main(String args[]) throws IOException{
        app ap = new app();
        if(!ap.isWeekend()){
            File temp = new File("files/temp.txt");
            if(temp.length() == 0){
                ap.start = new Punch();
                ap.start.save(temp);
            }
            else {
                ap.start = new Punch(temp);
                ap.stop = new Punch();
                ap.changeFileDirectory();
                File destinationFile = new File(ap.fileDirectory);
                Que<String> newData = new Que<>();
                if(destinationFile.length() != 0){
                    newData = ap.getNewFileData(destinationFile);
                    boolean deleted = destinationFile.delete();
                    if(deleted){
                        destinationFile = new File(ap.fileDirectory);
                    }
                    else {
                        System.out.println("Error creating a new Destination file");
                    }
                }
                else {
                    TimeDiff timeDiffData = new TimeDiff(ap.start, ap.stop);
                    newData.push(ap.start.getDayStr() + " : (" + timeDiffData.getTimesStr() + ")");
                    newData.push("Total Time ; " + 
                            timeDiffData.getTotalHoursForTimeDiff()[0] + 
                                    " Hours : " + timeDiffData.getTotalHoursForTimeDiff()[1] + 
                                            " Minutes : " + 
                                                    timeDiffData.getTotalHoursForTimeDiff()[2] + 
                                                            " Seconds");
                    
                }
                ap.writeToFile(destinationFile, newData);
                temp.deleteOnExit();
            }
        }
        else {
            System.out.println("\nWe don't work on the weekends :)\n\n");
        }
    }

    public void runCode() throws IOException{
        if(!this.isWeekend()){
            File temp = new File(this.tempFileDirectory);
            if(temp.length() == 0){
                this.start = new Punch();
                this.start.save(temp);
            }
            else {
                this.start = new Punch(temp);
                this.stop = new Punch();
                this.changeFileDirectory();
                File destinationFile = new File(this.fileDirectory);
                Que<String> newData = new Que<>();
                if(destinationFile.length() != 0){
                    newData = this.getNewFileData(destinationFile);
                    boolean deleted = destinationFile.delete();
                    if(deleted){
                        destinationFile = new File(this.fileDirectory);
                    }
                    else {
                        System.out.println("Error creating a new Destination file");
                    }
                }
                else {
                    TimeDiff timeDiffData = new TimeDiff(this.start, this.stop);
                    newData.push(this.start.getDayStr() + " : (" + timeDiffData.getTimesStr() + ")");
                    newData.push("Total Time ; " + 
                            timeDiffData.getTotalHoursForTimeDiff()[0] + 
                                    " Hours : " + timeDiffData.getTotalHoursForTimeDiff()[1] + 
                                            " Minutes : " + 
                                                    timeDiffData.getTotalHoursForTimeDiff()[2] + 
                                                            " Seconds");
                    
                }
                this.writeToFile(destinationFile, newData);
                temp.deleteOnExit();
            }
        }
        else {
            System.out.println("\nWe don't work on the weekends :)\n\n");
        }
    }

    public String getTempDirectory(){
        return this.tempFileDirectory;
    }

    public String getFileDirectory(){
        return this.fileDirectory;
    }

    public Punch getStart() {
        return this.start;
    }

    public Punch getStop(){
        return this.stop;
    }

    public String getTimeDifference(){
        return new TimeDiff(this.start, this.stop).getTimeDiffStr();
    }

    private boolean isWeekend(){
        boolean theAnswer = false;
        if(this.start == null){
            this.start = new Punch();
        }
        if((this.start.getDayInt() == 0) || (this.start.getDayInt() == 6)){
            theAnswer = true;
        }
        return theAnswer;
    }

    private void writeToFile(File file, Que<String> data) throws IOException {
        PrintStream print = new PrintStream(file);
        while(!data.empty()){
            print.println(data.pull());
        }
        print.close();
    }

    private Que<String> getNewFileData(File file) throws IOException{
        Que<String> lines = this.readOfFile(file);
        int totalIndex = this.indexWith(lines, "Total");
        if(totalIndex != -1){
            lines.remove(totalIndex);
        }
        int dayIndex = this.indexWith(lines, this.start.getDayStr());
        TimeDiff timeDifference = new TimeDiff(this.start, this.stop);
        if(dayIndex != -1){
            String curDay = lines.peek(dayIndex);
            if(curDay.indexOf("&") != -1){
                curDay = curDay.replaceAll(", & ", ", ");
            }
            curDay = curDay + ", & (" + timeDifference.getTimesStr() + ")";
            lines.changeAt(curDay, dayIndex);
        }
        else {
            lines.push(this.start.getDayStr() + " : (" + timeDifference.getTimesStr() + ")");
        }
        lines.push("Total Time : " + this.getTotalTime(lines));
        return lines;
    }

    private String getTotalTime(Que<String> lines){
        String theAnswer = "No times to add";
        if(lines.getSize() != 0){
            TimeDiff totalTime = this.addUpQueTimes(this.getTimesOfDay(lines.peek(0)));
            for(int curIndx = 1; curIndx < lines.getSize(); curIndx = curIndx + 1){
                String thisDay =  lines.peek(curIndx);
                totalTime.addTimeDiff(this.addUpQueTimes(this.getTimesOfDay(thisDay)).getTimeDifference());
            }
            theAnswer = Integer.toString(totalTime.getTotalHoursForTimeDiff()[0]) + " Hours : " + Integer.toString(totalTime.getTotalHoursForTimeDiff()[1]) + " Minutes : " + Integer.toString(totalTime.getTotalHoursForTimeDiff()[2]) + " Seconds";
        }
        return theAnswer;
    }

    private Que<TimeDiff> getTimesOfDay(String day){
        Que<TimeDiff> theAnswer = new Que<TimeDiff>();
        String dayCopy = day;
        while(!dayCopy.equalsIgnoreCase("")){
            String thisTime = dayCopy.substring(dayCopy.indexOf("("), dayCopy.indexOf(")") + 1);
            String startTime = thisTime.substring(thisTime.indexOf("(") + 1, thisTime.indexOf("-->") - 1);
            String stopTime = thisTime.substring(thisTime.indexOf("-->") + 4, thisTime.indexOf(")"));
            theAnswer.push(new TimeDiff(startTime, stopTime));
            if(dayCopy.indexOf(")") + 1 != dayCopy.length()){
                dayCopy = dayCopy.substring(dayCopy.indexOf(")") + 2);
            }
            else {
                dayCopy = "";
            }
        }
        return theAnswer;
    }

    private TimeDiff addUpQueTimes(Que<TimeDiff> day){
        TimeDiff theAnswer = day.pull();
        for(int curIndx = 0; curIndx < day.getSize(); curIndx = curIndx + 1){
            TimeDiff thisTimeDiff = day.peek(curIndx);
            theAnswer.addTimeDiff(thisTimeDiff.getTimeDifference());
        }
        return theAnswer;
    }

    private Que<String> readOfFile(File file) throws IOException{
        Que<String> theAnswer = new Que<String>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        while((line = reader.readLine()) != null){
            theAnswer.push(line);
        }
        reader.close();
        return theAnswer;
    }

    private void changeFileDirectory(){
        boolean response = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter 'true' to change the directory. 'false' otherwise");
        // TODO: this will be used when the graphical user interface is complete scan.nextBoolean();
        if(response){
            this.fileDirectory = "files/" + scan.next() + scan.nextLine();
        }
        else {
            int startDate = this.getStartDate();
            int stopDate = this.getStopDate();
            if(stopDate > this.stop.daysInMonth()){
                stopDate = Math.abs(stopDate - this.stop.daysInMonth());
            }
            this.fileDirectory = "files/" + this.start.getMonthStr().substring(0, 3) + " " + Integer.toString(startDate) + this.getIntEnding(startDate) + "-" + this.stop.getMonthStr().substring(0, 3) + " " + Integer.toString(stopDate) + this.getIntEnding(stopDate) + "_week_hours.txt";
        }
        scan.close();
    }

    private int getStartDate(){
        int theAnswer = this.start.getDayOfMonth() - this.start.getDayInt() + 1;
        if(theAnswer < 0){
            if(this.start.getMonthInt() == 0){
                theAnswer = 31 - theAnswer;
            }
            else{
                theAnswer = this.start.getDaysOfMonthsAllYear().peek(this.start.getMonthInt() - 1) + theAnswer;
            }
        }
        return theAnswer;
    }

    private int getStopDate(){
        int theAnswer = this.start.getDayOfMonth() - this.start.getDayInt();
        int adder = 5;
        if(theAnswer < 0){
            theAnswer = adder + theAnswer;
        }
        else {
            theAnswer = theAnswer + adder;
        }
        return theAnswer;
    }

    private String getIntEnding(int integer){
        String theAnswer = "th";
        if(Math.abs(integer) == 1){
            theAnswer = "st";
        }
        else if(Math.abs(integer) == 2){
            theAnswer = "nd";
        }
        else if(Math.abs(integer) == 3){
            theAnswer = "rd";
        }
        return theAnswer;
    }

    private int indexWith(Que<String> que, String data){
        int theAnswer = -1;
        for(int curIndx = 0; curIndx < que.getSize(); curIndx = curIndx + 1){
            if(this.containsSameData(que.peek(curIndx), data)){
                theAnswer = curIndx;
                break;
            }
        }
        return theAnswer;
    }

    private boolean containsSameData(String original, String toFind){
        boolean theAnswer = false;
        for(int curIndx = 0; curIndx < original.length(); curIndx = curIndx + 1){
            if(original.substring(curIndx).length() < toFind.length()){
                break;
            }
            else {
                if(original.substring(curIndx, curIndx + toFind.length()).equalsIgnoreCase(toFind)){
                    theAnswer = true;
                    break;
                }
            }
        }
        return theAnswer;
    }

}