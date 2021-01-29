package SimulateDFA;
//* Simulate DFA that accepts {w |w has length of at least 3 and its 3rd symbol is 0}
//    Jade Cabral & Gwynne Eichman 2/12/2020
//** expanded from:
// * CIS 361: Models of Computation
// * @author Haiping Xu
// * Created on February 8, 2016 at the CIS Department, UMass Dartmouth
//**/

import java.util.HashMap;

public class SimulateDFA {

    private HashMap<String, String> hm = new HashMap<String, String>(); //create new hashmap called hm
    private String[] finalState = {"q3"}; // q3 is the final state
    private String curState = "q0"; // start with state q0 

    public SimulateDFA() {
        initDFA(); //constructor, when obj created calls initTransitionFucntion()
    }

    private void initDFA() { //initialized the DFA
        hm.put("q0" + "1", "q1"); // q0 takes 1 and connects to q1 
        hm.put("q0" + "0", "q1"); // q0 takes 0 and connects to q1 
        hm.put("q1" + "1", "q2"); // q1 takes 1 and connects to q2
        hm.put("q1" + "0", "q2"); // q1 takes 0 and connects to q2
        hm.put("q2" + "0", "q3"); // q2 takes 0 and connects to q3
        hm.put("q2" + "1", "q4"); // q2 takes 0 and connects to q4
        hm.put("q4" + "1", "q4"); // q4 takes 1 and connects to q4
        hm.put("q4" + "0", "q4"); // q4 takes 0 and connects to q4
        hm.put("q3" + "1", "q3"); // q3 takes 1 and connects to q3
        hm.put("q3" + "0", "q3"); // q3 takes 0 and connects to q3
    }

    public boolean computeString(String inputString) {
        for (int i = 0; i < inputString.length(); i++) {
            transit(inputString.charAt(i));//go through the string and send each character to transit function
        }

        if (isFinal(curState)) {
            return true;
        } else {
            return false;
        }
    }//computeString()

    private boolean isFinal(String state) {
        for (String fs : finalState) {
            if (fs.equals(state)) { //check if state is a final state 
                return true;
            }
        }
        return false;
    }//isFinal

    private void transit(char symbol) {
        String next = hm.get(curState + symbol);
        if (next != null) { //if the next state is not null (there is a transition)
            curState = next; //current state is moved to the next state in the transition
        } else {
            curState = hm.get(curState + "*"); //curState = null
        }
    }//transit()

    public static void main(String[] args) {
        String inputString = "1101111";
        //input each trial string here, recompile and run to test
        //case 110 is accepted, 11 is rejected, 11011 is accepted, 001000 is rejected, 1101111

        SimulateDFA dfa = new SimulateDFA(); // create a new SimulateDFA object called dfa

        if (dfa.computeString(inputString)) { // if computeString returns true (is accepted)
            System.out.println("This DFA accepts \"" + inputString + "\"");
        } else { //if compute string returns false (not accepted)
            System.out.println("This DFA rejects \"" + inputString + "\" This DFA only accepts {w |w has length of at least 3 and its 3rd symbol is 0}");
        }

    }//main()
}// public class SimulateDFA
