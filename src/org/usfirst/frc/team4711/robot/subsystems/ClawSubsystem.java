package org.usfirst.frc.team4711.robot.subsystems;

import org.usfirst.frc.team4711.robot.config.MotorSpeeds;
import org.usfirst.frc.team4711.robot.config.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClawSubsystem extends Subsystem {
	
	private WPI_TalonSRX LCMotor, RCMotor;
	
	private static ClawSubsystem instance;
	
	private ClawSubsystem() {
		super("clawSubsystem");
		
		LCMotor = new WPI_TalonSRX(RobotMap.LCTalon);
		RCMotor = new WPI_TalonSRX(RobotMap.RCTalon);
		
	}
	
	public static ClawSubsystem getInstance(){
		if(instance == null)
			instance = new ClawSubsystem();
		
		return instance;
	}

	/**
	 * Positive move values in-take; negative values eject
	 * @param moveValue
	 */
	
	public void setMotorSpeed(double moveValue){
		if(DriverStation.getInstance().isOperatorControl()) 
			moveValue *= MotorSpeeds.TELEOP_MULTIPLIER;
		else
			moveValue *= MotorSpeeds.AUTONOMOUS_MULTIPLIER;
		
		LCMotor.set(moveValue * MotorSpeeds.CLAW_SPEED);
		RCMotor.set(moveValue * MotorSpeeds.CLAW_SPEED);
		
	}
	
	public void stopMotors() {
		LCMotor.stopMotor();
		RCMotor.stopMotor();
	}
	
	@Override
	protected void initDefaultCommand() {
	}
}