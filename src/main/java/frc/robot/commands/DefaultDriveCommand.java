package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDriveCommand extends CommandBase {
    private final DriveSubsystem m_driveSubsystem;
    private final XboxController m_driveController;
    private final boolean m_fieldRelative;

    private final SlewRateLimiter m_xSpeedLimiter = new SlewRateLimiter(3);
    private final SlewRateLimiter m_ySpeedLimiter = new SlewRateLimiter(3);
    private final SlewRateLimiter m_rotLimiter = new SlewRateLimiter(3);

    public DefaultDriveCommand(DriveSubsystem driveSubsystem, XboxController driveController, boolean fieldRelative) {
        this.m_driveSubsystem = driveSubsystem;
        this.m_driveController = driveController;
        this.m_fieldRelative = fieldRelative;
        addRequirements(driveSubsystem);
        setName("Default Drive Command");
    }


    /**
     * The main body of a command.  Called repeatedly while the command is scheduled.
     * (That is, it is called repeatedly until {@link #isFinished()}) returns true.)
     */
    @Override
    public void execute() {
        final var ySpeed = m_ySpeedLimiter.calculate(m_driveController.getY(Hand.kRight));
        final var xSpeed = -m_xSpeedLimiter.calculate(m_driveController.getX(Hand.kLeft));
        final var rot = -m_rotLimiter.calculate(m_driveController.getX(Hand.kRight));

        SmartDashboard.putNumber("X Value",  xSpeed);
        SmartDashboard.putNumber("Y Value", ySpeed);
        SmartDashboard.putNumber("Rotation", rot);

        m_driveSubsystem.drive(ySpeed, xSpeed, rot, m_fieldRelative);
    }

    /**
     * <p>
     * Returns whether this command has finished. Once a command finishes -- indicated by
     * this method returning true -- the scheduler will call its {@link #end(boolean)} method.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Hard coding this command to always
     * return true will result in the command executing once and finishing immediately. It is
     * recommended to use * {@link edu.wpi.first.wpilibj2.command.InstantCommand InstantCommand}
     * for such an operation.
     * </p>
     *
     * @return whether this command has finished.
     */
    @Override
    public boolean isFinished() {
        return false;
    }

    /**
     * The action to take when the command ends. Called when either the command
     * finishes normally -- that is it is called when {@link #isFinished()} returns
     * true -- or when  it is interrupted/canceled. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the command.
     *
     * @param interrupted whether the command was interrupted/canceled
     */
    @Override
    public void end(boolean interrupted) {
        m_driveSubsystem.stopMotors();
    }
}
