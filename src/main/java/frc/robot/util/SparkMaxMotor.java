package frc.robot.util;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;

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
        return Units.Revolutions.of(motor.getEncoder().getPosition());
    }

    @Override
    public void setPosition(Angle position) {
        throw new UnsupportedOperationException("Spark Max does not support setting position");
    }

}
