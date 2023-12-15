package pomodoro.ui;

import javafx.scene.Group;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;

public class TimerView
{
    private Group root;
    private Ellipse ellipse;
    private Arc arc;
    private final double angleOffset = -360;

    public TimerView()
    {
        this.root = new Group();

        this.ellipse = new Ellipse(256, 256, 256, 256);
        this.ellipse.setFill(UIConfig.DEFAULT_ELLIPSE_COLOR);
        setEllipse(this.ellipse);

        this.arc = new Arc(256, 256, 256, 256, 90, angleOffset);
        this.arc.setFill(UIConfig.DEFAULT_ARC_COLOR);
        this.arc.setType(UIConfig.DEFAULT_ARC_TYPE);
        setArc(this.arc);
        System.out.println(this.arc.getLength());
    }

    public Group getRoot()
    {
        return this.root;
    }

    public void setEllipse(Ellipse ellipse)
    {
        this.ellipse = ellipse;
        this.root.getChildren().add(this.ellipse);
    }

    public Ellipse getEllipse()
    {
        return this.ellipse;
    }

    public void setArc(Arc arc)
    {
        this.arc = arc;
        this.root.getChildren().add(this.arc);
    }

    public Arc getArc()
    {
        return this.arc;
    }

    public void updateAngle(double angle)
    {
        // TODO 引数で時間指定して更新できるようにする
        this.arc.setLength(angle + angleOffset);
    }
}
