
package frc.robot.util;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public interface Motor {
    void set(double speed);

    void setPosition(Angle position);

    AngularVelocity getVelocity();

    Angle getPosition();
}
