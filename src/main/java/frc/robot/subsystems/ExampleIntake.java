package frc.robot.subsystems;

import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Motor;
import frc.robot.util.MotorGroup;
import frc.robot.util.MotorUtil;
import frc.robot.util.TalonFxMotor;

/// Example class for lesson 3
public class ExampleIntake extends SubsystemBase {
    private static final int MOTOR_ID = 4;

    private final MotorGroup<Motor> motorGroup;

    private double targetSpeed;

    public ExampleIntake() {
        Motor motor = new TalonFxMotor(MotorUtil.initTalonFX(MOTOR_ID, NeutralModeValue.Coast));
        motorGroup = new MotorGroup<Motor>(motor);
    }

    @Override
    public void periodic() {
        motorGroup.setSpeed(targetSpeed);
    }

    public void setTargetSpeed(double speed) {
        //using setters instead of public fields is useful because you can intercept the value and modify it or make sure it is correct.
        //in this case, I am clamping the input number so that target speed stays between -1 and 1, no matter what
        targetSpeed = MotorUtil.clampPercent(speed);
    }
}
