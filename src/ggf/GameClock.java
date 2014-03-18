package ggf;

public class GameClock {
    
    public static final int NANOSEC_IN_MILLISEC = 1000000;
    public static final int MILLISEC_IN_SEC = 1000;
    
    private final int startTime;
    private int runTime;
    private int updateTime;
    private int frameTime;
    
    public GameClock(int framesPerSecond) {
        startTime = (int)(System.nanoTime()/NANOSEC_IN_MILLISEC);
        runTime = 0;
        frameTime = MILLISEC_IN_SEC/framesPerSecond;
    }
    
    public void setFPS(int framesPerSecond) {
        frameTime = MILLISEC_IN_SEC/framesPerSecond;
    }
    
    public void update() {
        int newRunTime = (int)(System.nanoTime()/NANOSEC_IN_MILLISEC) - startTime;
        updateTime = newRunTime - runTime;
        runTime = newRunTime;
    }
    
    public int startTime() {
        return startTime();
    }
    
    public int updateTime() {
        return updateTime;
    }
    
    public int runTime() {
        return runTime;
    }
    
    public int frameTimeLeft() {
        return startTime + runTime + frameTime - (int)(System.nanoTime()/NANOSEC_IN_MILLISEC);
    }
    
    public int getTrueFPS() {
        return MILLISEC_IN_SEC/updateTime;
    }
}