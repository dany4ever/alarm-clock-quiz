package alarmClockQuiz;

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
/**This object holds the questions the alarm clock displays. 
 * @author Micah Gale micah.gale@gmail.com
 * @version 0.05
 * @since 21 December 2012
 */
public class question {
    private String question;   //the actual question
    private String[] answers;  //all possible answers
    private short correct;     //reference to the correct answer
    private short[] displays;  //reference to the order of the displayed answers
    private short displayCorrect; //references the correct answer displayed, based on the display
    /** The main constructor of this object
     * 
     * @param question the String for the question that will be asked.
     * @param Correct  the String that is the correct answer
     * @param otherAnswers the array of the other answers that are incorrect
     */
    public question(String question, String Correct, String[] otherAnswers) {
        this.question=question;   //get the question.   
        this.answers= new String[otherAnswers.length+1];  //set the array size
        this.correct=(short)0;                           //just put the correct one at the beginning of the array
        this.answers[this.correct]=Correct; //insert the correct answer
        for(int i=1;i<this.answers.length;i++) {         //insert the otheranswers 
            this.answers[i]=otherAnswers[i-1];         //pull the other answer and put it in the array
        }
    }
    /**Gets just the Question.
     * 
     * @return the actual question.
     */
    public String getQuestion() {
        return this.question;
    }
    /**Generates the answer display array, puts it in random order.
     * 
     * @return An array of the answers in a random order.
     */
    public String[] getDisplayAnswers() {
        String[] display= new String[this.answers.length]; //sets up the return based on the answer length.
        this.displays = new short[display.length];           //sets up the display referencial array
        int random = (int)(Math.random()*display.length);  //randomly generates a location for the correct answer
        this.displayCorrect = (short)random;                      //stores the display of the correct answer
        display[random]= this.answers[this.correct];      //inserts the correct answer
        this.displays[random]=correct;                   //sets up the link for the correct answer
        return display;
    }
}