/*
 * Copyright 2013 Rocky Mountain High School Technical Student Association
 *
 * This file is a part of alarm-clock-quiz
 *
 * alarm-clock-quiz is free software licensed under the GNU General Public License version 3.
 * You may redistribute and/or modify it under the terms of the GNU General Public License
 * version 3 as published by the Free Software Foundation.updater
 *
 * alarm-clock-quiz comes without a warranty; without even the implied warranty of merchantability
 * or fitness for a particular purpose. See the GNU General Public License version 3 for more
 * details.
 *
 * You should have received the GNU General Public License version 3 with this in GPL.txt
 * if not it is available at <http://www.gnu.org/licenses/gpl.txt>.
 *
 * All resources for alarm-clock-quiz including: source code, compiled jar, documentation, and source
 * notes are available at <https://code.google.com/p/alarm-clock-quiz/>.
 */
package alarmClockQuiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**The class that will be run and actually do the things
 *
 * @author Rocky Mountain Technical student Association (Cesar Cortez, Micah Gale micah.gale@gmail.com, Brad Whitesell) 
 * @version 0.10
 * @since 22 December 2012
 */
public class Main {
    /**The start function will pass off onto others
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        question[] input = parseQuizXML("/home/micah/Documents/netBeansProjects/AlarmClockQuiz/src/alarmClockQuiz/example.xml");     //parse it
        String[] toDisplay;
        for(int i=0;i<input.length;i++) { 
            toDisplay=input[i].getDisplayAnswers();
            System.out.println("Question: "+input[i].getQuestion());
            for(int j=0;j<toDisplay.length;j++) {
                System.out.println(toDisplay[j]);
            }
        }
    }
    /**Parses the XML file as questions and returns an array of the parsed information.
     * 
     * @param URL  the URL to the XML file
     * @return an array of the questions that were parsed.
     */
    public static question[] parseQuizXML(String URL) {
        BufferedReader reader;
         String input="";
        try {                                    //try to read the file
            reader= new BufferedReader(new FileReader(URL));
            String buffer;
            while((buffer=reader.readLine())!=null) {
                input = input+buffer;                    //combine the whole file to a single string
            }
            reader.close();                         //don't leak resources!!!!!!!!!
        } catch (FileNotFoundException ex ) {      //TODO create better logger
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }       
        ArrayList<question> storage = new ArrayList<>();
        input=input.replaceAll("<!--\\p{Print}*?-->","");              //take out all comments first
        Pattern pattern = Pattern.compile("<quiz>(\\p{Print}+?)</quiz>");  //match <quiz>as little other stuff</quiz>
        Matcher matcher= pattern.matcher(input);                          //compare it
        if(matcher.find()) {                         //if found stuff
            input=matcher.group(1);             //get the quiz subsection
            pattern = Pattern.compile("<question\\s*>(\\p{Print}+?)</question>");  //pattern to get question tags
            matcher = pattern.matcher(input);
            String correctAnswer="";
            while(matcher.find()) {
                ArrayList<String> incorrect=new ArrayList<>();
                String subInput=matcher.group(1);              //get the found string
                pattern = Pattern.compile("<answer\\s+correct=\"true\"\\s*>(\\p{Print}+?)</answer>");  //find the correct answer
                Matcher correct=pattern.matcher(subInput);
                pattern =Pattern.compile("<answer\\s*(?:correct=\"false\")*\\s*>(\\p{Print}+?)</answer>");  //find incorrect answer
                Matcher wrong=pattern.matcher(subInput);
                if(correct.find()) {            //if found the correct answer
                    correctAnswer=correct.group(1);    //get the correct answer
                }
                while(wrong.find()) {                 //get all the wrong answers
                    incorrect.add(wrong.group(1));    //get the answer
                }
                String[] incorrectAnswer=incorrect.toArray(new String[0]);   //switch it to a string array
                subInput=subInput.replaceAll("(<answer\\p{Print}*?>\\p{Print}+?</answer>)", "");
                //remove all the answers and new line characters, and leave the question juice
                pattern = Pattern.compile("\\S*?(\\w\\p{Print}+)");      //cut out white-space
                correct=pattern.matcher(subInput); 
                String question="";
                if(correct.find()) {
                    question=correct.group(1);                      //the remaining 
                }
                storage.add(new question(question,correctAnswer,incorrectAnswer));
            }
        }
        question[] output=storage.toArray(new question[0]);  //switch the question arraylist to an array
        return output;
    }
}
