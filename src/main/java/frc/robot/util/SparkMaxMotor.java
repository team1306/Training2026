package frc.robot.util;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public final class SparkMaxMotor implements Motor {
    private final SparkMax motor;

    public SparkMaxMotor(SparkMax motor) {
        this.motor = motor;
    }

    @Override
    public void set(double speed) {
        motor.set(speed);
    }

    @Override
    public Angle getPosition() {
        return Angle.ofBaseUnits(motor.getEncoder().getPosition(), Units.Rotations);
    }

    @Override
    public void setPosition(Angle position) {
        motor.getEncoder().setPosition(position.in(Units.Rotations));
    }

    @Override
    public AngularVelocity getVelocity() {
        return AngularVelocity.ofBaseUnits(motor.getEncoder().getVelocity(), Units.RPM);
    }

}
