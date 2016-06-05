//Necessary imports
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class LEGO {

	public static void main(String[] args) {
		
		//System constants
		final float r = (float) 9;
		final float R = (float) 10;
		final float accelA = float (100);
		
		//Motor set-up
		RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.D);
		
		motorA.setSpeed(1000);
		motorD.setSpeed(1000);
		motorA.setAcceleration(accelA);
		motorD.setAcceleration((r/R)*accelA);
		motorA.forward();
		motorD.forward();
		
		
		motorA.close();
		motorD.close();
		
		
	}
	
}
