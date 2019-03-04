package edu.pooh_farmer;

public interface Subject {

    public void registerListener(Listener listener);
    public void removeListener(Listener listener);
    public void notifyListeners();

} // **** end Subject interface ****