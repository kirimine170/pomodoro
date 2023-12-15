package pomodoro.timer;

import javafx.animation.AnimationTimer;

public class CustomableAnimationTimer extends AnimationTimer
{
    private long startTime;
    private final double period;
    private TimerListener timerListener;

    public CustomableAnimationTimer(double period)
    {
        this.period = period;
    }

    @Override
    public void start()
    {
        super.start();
        startTime = System.nanoTime();
    }

    @Override
    public void stop()
    {
        super.stop();
    }

    @Override
    public void handle(long now)
    {
        double elapsedSeconds = (now - startTime) / 1_000_000_000.0;
        double angle = (elapsedSeconds % period) / period * 360;

        timerListener.onTimerUpdate(angle);// HACK 本当はこの実装良くないかも
    }

    public void setTimerListener(TimerListener timerListener)
    {
        this.timerListener = timerListener;
    }
}
