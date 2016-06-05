//Necessary imports
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;

public class LEGO {

	public static void main(String[] args) {
		
	  //System constants
	  final float r = (float) 9;
		final float R = (float) 10;

		//Motor set-up
		RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.D);
		
		
		motorA.setAcceleration(R/r);
		motorB.setAcceleration(r/R);
		motorA.forward();
		motorB.forward();
		
		motorA.close();
		motorD.close();
		
	}
	
}
