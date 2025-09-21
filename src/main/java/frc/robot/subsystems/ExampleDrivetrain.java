package frc.robot.subsystems;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Motor;
import frc.robot.util.MotorGroup;
import frc.robot.util.MotorUtil;
import frc.robot.util.SparkMaxMotor;

import java.util.function.DoubleSupplier;

/// Example class for lesson 2
public class ExampleDrivetrain extends SubsystemBase {
    //Motor IDs. Normally these are declared in a constants file
    private static final int LEFT_MOTOR_1_ID = 0;
    private static final int LEFT_MOTOR_2_ID = 1;
    private static final int RIGHT_MOTOR_1_ID = 2;
    private static final int RIGHT_MOTOR_2_ID = 3;

    //Declare motor groups to allow motors to be moved together
    private MotorGroup<Motor> leftMotors;
    private MotorGroup<Motor> rightMotors;

    //Declare target speed variables
    private double leftTargetSpeed;
    private double rightTargetSpeed;

    public ExampleDrivetrain() {
        //Initialize motors
        Motor leftMotor1 = new SparkMaxMotor(MotorUtil.initSparkMax(LEFT_MOTOR_1_ID, IdleMode.kBrake));
        Motor leftMotor2 = new SparkMaxMotor(MotorUtil.initSparkMax(LEFT_MOTOR_2_ID, IdleMode.kBrake));
        Motor rightMotor1 = new SparkMaxMotor(MotorUtil.initSparkMax(RIGHT_MOTOR_1_ID, IdleMode.kBrake));
        Motor rightMotor2 = new SparkMaxMotor(MotorUtil.initSparkMax(RIGHT_MOTOR_2_ID, IdleMode.kBrake));

        //Add motors to group
        leftMotors = new MotorGroup<>(leftMotor1, leftMotor2);
        rightMotors = new MotorGroup<>(rightMotor1, rightMotor2);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

        //sets the speed of each sides' motors to the target speed
        leftMotors.setSpeed(leftTargetSpeed);
        rightMotors.setSpeed(rightTargetSpeed);
    }

    //We are inputting the controller stick inputs for left and right speed
    public Command tankDrive(DoubleSupplier leftSpeed, DoubleSupplier rightSpeed) {
        return Commands.run(() -> { //every loop, the code in here will run, setting the speed to the stick input
            leftTargetSpeed = leftSpeed.getAsDouble();
            rightTargetSpeed = rightSpeed.getAsDouble();
        }, this);
    }
}
