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
/**This object holds the questions the alarm clock displays. 
 * @author  Rocky Mountain Technical student Association (Cesar Cortez, Micah Gale micah.gale@gmail.com, Brad Whitesell) 
 * @version 0.05
 * @since 21 December 2012
 */
public class question {
    /** The Actual question to be asked*/
    private String question;   //the actual question
    /** All displayed answers*/
    private String[] answers;  //all possible answers
    /** A reference to the correct answer in the array based on it's index*/
    private short correct;     //reference to the correct answer
    /** An array of the displayed answers, references the actual ones by index*/
    private short[] displays;  //reference to the order of the displayed answers
    /** the correct answer, based on it's place in the displayed answer*/
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
        this.displays[random]=this.correct;                   //sets up the link for the correct answer
        do {
            random=(int)(Math.random()*display.length);  //get another random which isn't the correct answer
        } while(random==this.displayCorrect);
        display[random]= this.answers[1];           //put the first wrong answer in the random slot
        this.displays[random]=1;                    //stores the reference
        int index =0;                //the index in the display array
        for(short i=2;i<this.answers.length;i++) {   //fill in the remaining blanks
            while(display[index]!=null) {      //if the current index is full then skip 1 find next open one
                index++;    //go up 1
            }
            if(index<display.length) {
                display[index]=this.answers[i];      //fill the display that's next open from the next answers
                this.displays[index]=i;                //store the reference
            }
        }
        return display;
    }
    /**Checks if the provided answer is correct
     * 
     * @param answered the index of the answer selected, based on the display array
     * @return true if correct, false otherwise
     */
    public boolean checkAnswer(short answered) {
        if(answered==this.displayCorrect) {   //if corresponds to the stored correct display return true
            return true; 
        } else {
            return false;
        }
    }
    public void display() {
        System.out.println("Question:"+this.question);
        System.out.println("Correct:"+this.answers[this.correct]);
        for(int i=1;i<this.answers.length;i++) {
            System.out.println("Answer:"+this.answers[i]);
        }
    }
}