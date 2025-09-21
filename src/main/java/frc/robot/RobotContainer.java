// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ExampleDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.ExampleIntake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    ExampleDrivetrain drivetrain = new ExampleDrivetrain();
    ExampleIntake intake = new ExampleIntake();

    private final CommandXboxController controller = new CommandXboxController(0);

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();

        intake();
    }

    //Intro tutorial
    private void intake() {
        final int MOTOR_ID = 4;

        //Define a motor
        SparkMax motor = new SparkMax(MOTOR_ID, SparkLowLevel.MotorType.kBrushless);

        //Controller binding
        controller.a()
                .onTrue(new InstantCommand(() -> motor.set(1))) //when a is pressed, set speed to 1
                .onFalse(new InstantCommand(() -> motor.set(0))); //when a is released, set speed back to 0
    }

    private void configureBindings() {
        drivetrain.setDefaultCommand(drivetrain.tankDrive(() -> controller.getLeftY(), () -> controller.getLeftX()));
        controller.a()
                .onTrue(new InstantCommand(() -> intake.setTargetSpeed(1), intake)) //don't forget to add requirements
                .onFalse(new InstantCommand(() -> intake.setTargetSpeed(0), intake));
    }

    /**
    * Use this to pass the autonomous command to the main {@link Robot} class.
    *
    * @return the command to run in autonomous
    */
    public Command getAutonomousCommand() {
        return null;
    }
}
