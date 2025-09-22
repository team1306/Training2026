package frc.robot.subsystems;

import badgerlog.annotations.Entry;
import badgerlog.annotations.EntryType;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.util.Motor;
import frc.robot.util.MotorGroup;
import frc.robot.util.MotorUtil;
import frc.robot.util.TalonFxMotor;

/// Example class for lesson 4
public class ExampleArm extends SubsystemBase {
    private static final int MOTOR_ID = 0;

    private static final double MAX_VELOCITY = 2500;
    private static final double MAX_ACCELERATION = 4500;
    private static final Angle OFFSET = Angle.ofBaseUnits(0, Units.Degrees);
    private static final Angle MIN_ANGLE = Angle.ofBaseUnits(-35, Units.Degrees);
    private static final Angle MAX_ANGLE = Angle.ofBaseUnits(90, Units.Degrees);

    @Entry(EntryType.SUBSCRIBER)
    private static double kG = 0;
    @Entry(EntryType.SUBSCRIBER)
    private static double kV = 0;

    private final MotorGroup<Motor> motorGroup;
    private final TalonFxMotor motor = new TalonFxMotor(MotorUtil.initTalonFX(MOTOR_ID, NeutralModeValue.Brake));

    @Entry(EntryType.SENDABLE)
    private final ProfiledPIDController pidController = new ProfiledPIDController(0.001, 0, 0, new TrapezoidProfile.Constraints(MAX_VELOCITY, MAX_ACCELERATION));
    private final ArmFeedforward feedForward = new ArmFeedforward(0, kG, kV);

    @Entry(EntryType.PUBLISHER)
    private Angle targetAngle;

    public ExampleArm() {
        motorGroup = new MotorGroup<>(motor);
    }

    @Override
    public void periodic() {
        double pidOutput = pidController.calculate(getCurrentAngle().in(Units.Degrees), targetAngle.in(Units.Degrees));
        final State state = pidController.getSetpoint();
        double feedForwardOutput = feedForward.calculate(getCurrentAngle().in(Units.Degrees), Math.toDegrees(state.velocity));

        motorGroup.setSpeed(pidOutput + feedForwardOutput);
    }

    private Angle getCurrentAngle() {
        return motor.getPosition().plus(OFFSET);
    }

    public void setTargetAngle(Angle angle) {
        targetAngle = Angle.ofBaseUnits(MathUtil.clamp(angle.in(Units.Degrees), MIN_ANGLE.in(Units.Degrees), MAX_ANGLE.in(Units.Degrees)), Units.Degrees);
    }
}
