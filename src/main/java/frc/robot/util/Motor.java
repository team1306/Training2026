
package frc.robot.util;

import edu.wpi.first.units.measure.Angle;

public interface Motor {
    void set(double speed);

    void setPosition(Angle position);

    Angle getPosition();
}
