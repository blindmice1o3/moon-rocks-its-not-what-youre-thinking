package com.pooh.moonrocks.states;

public class StateManager {

    // Initialized to null (other parts of the program checks IF currentState is null or not).
    private static State currentState = null;

    public static void setCurrentState(State state) {
        currentState = state;
    }

    public static State getCurrentState() {
        return currentState;
    }

} // **** end StateManager class ****