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
import lejos.utility.Delay;

public class LEGO {

	public static void main(String[] args) {
		
	    //System constants
	    final float L = (float) 16.5;
		final float r = (float) 4.7;
		
		//Motor set-up
		RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.D);
		
		//Touch sensor set-up
		EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S1);
		SampleProvider touchSample = touchSensor.getTouchMode();
		float[] touchValue = new float[touchSample.sampleSize()];
		touchSensor.fetchSample(touchValue, 0);

		//Gyro sensor set-up
		EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S4);
		SampleProvider gyroSample = gyroSensor.getAngleAndRateMode();
		float[] gyroValues = new float[gyroSample.sampleSize()];
		gyroSensor.fetchSample(gyroValues, 0);

		//LCD set-up
		GraphicsLCD gLCD = LocalEV3.get().getGraphicsLCD();
		final int SW = gLCD.getWidth();
	    final int SH = gLCD.getHeight();
	
		while (touchValue[0] == 0){
			
			gyroSensor.getAngleAndRateMode().fetchSample(gyroValues, 0);
			float rate = (float) (180*L*Math.sin(Math.toRadians(gyroValues[1]))*gyroValues[0]/(Math.PI*r));
			
			gLCD.clear();
			gLCD.drawString(gyroValues[0] + " " + gyroValues[1], 0, 0, 0);
			gLCD.drawString("dtheta/dt = " + rate, SW/2, SH/2, GraphicsLCD.BOTTOM | GraphicsLCD.HCENTER);
			gLCD.refresh();
			
			motorA.setSpeed((int) rate);
			motorD.setSpeed((int) rate);
			if (gyroValues[1] > 0){
				
				motorA.backward();
				motorD.backward();
		
			}else {
				
				motorA.forward();
				motorD.forward();
				
			}
			
			touchSensor.fetchSample(touchValue, 0);

		}
		
		gyroSensor.close();
		touchSensor.close();
		motorA.close();
		motorD.close();
		
		}
	
	}
