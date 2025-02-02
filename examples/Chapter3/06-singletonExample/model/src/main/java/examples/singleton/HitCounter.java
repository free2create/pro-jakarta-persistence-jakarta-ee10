package examples.singleton;

import jakarta.ejb.Singleton;

@Singleton
public class HitCounter {
    int count;

    public void increment() { ++count; }
	
    public int getCount() { return count; }

    public void reset() { count = 0; }
}