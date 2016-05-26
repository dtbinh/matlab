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
	    final float L = (float) 16.5;
		final float r = (float) 4.7;
		
		//Motor set-up
		RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.D);
		motorA.synchronizeWith(new RegulatedMotor[] {motorD});
		
		//Touch sensor set-up
		EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S1);
		SampleProvider touchSample = touchSensor.getTouchMode();
		float[] touchValue = new float[touchSample.sampleSize()];
		touchSensor.fetchSample(touchValue, 0);

		//Gyro sensor set-up
		EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S4);
		SampleProvider gyroSample = gyroSensor.getAngleAndRateMode();
		float[] gyroValues = new float[gyroSample.sampleSize()];
		float[] gyroValues2 = new float[gyroSample.sampleSize()];

		gyroSensor.fetchSample(gyroValues, 0);

		//LCD set-up
		GraphicsLCD gLCD = LocalEV3.get().getGraphicsLCD();
		final int SW = gLCD.getWidth();
	    final int SH = gLCD.getHeight();
	
		while (touchValue[0] == 0){
			
			//Calculate angular acceleration
			gyroSensor.getAngleAndRateMode().fetchSample(gyroValues, 0);
			long startTime = System.nanoTime();
			gyroSensor.getAngleAndRateMode().fetchSample(gyroValues2, 0);
			long endTime = System.nanoTime();
			double alpha = 1000000000*(gyroValues2[1] - gyroValues[1])/(endTime - startTime);
			int alphaInt = (int) Math.round(alpha);
			
			int xAcceleration = (int) Math.round((-L*(alpha*Math.sin(Math.toRadians(gyroValues2[0])) + gyroValues2[1]*Math.cos(Math.toRadians(gyroValues2[0])))));
			int xVelocity = (int) Math.round((-L*Math.sin(Math.toRadians(gyroValues2[0]))*gyroValues2[1]));
			int x = (int) Math.round((L*Math.cos(Math.toRadians(gyroValues2[0]))));
			
			gLCD.clear();
			gLCD.drawString(gyroValues[0] + " " + gyroValues[1] + " " + alphaInt, 0, 0, 0);
			gLCD.drawString(x + " " + xVelocity + " " + xAcceleration, SW/2, SH/2, GraphicsLCD.BOTTOM|GraphicsLCD.HCENTER);
			gLCD.refresh();
			
			touchSensor.fetchSample(touchValue, 0);

		}
		
		gyroSensor.close();
		touchSensor.close();
		motorA.close();
		motorD.close();
		
	}
	
}
