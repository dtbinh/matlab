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
		final float k = (float) ((180*L)/(Math.PI*r));
		
		//Motor set-up
		RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.D);
		motorA.setSpeed(1000000);
		motorD.setSpeed(1000000);
		
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
			Delay.msDelay(1);
			gyroSensor.getAngleAndRateMode().fetchSample(gyroValues2, 0);
			double alpha = 1000*(gyroValues2[1] - gyroValues[1])/1;
			
			double acceleration = (alpha*Math.cos(Math.toRadians(gyroValues2[0]))-gyroValues2[1]*gyroValues2[1]*Math.sin(Math.toRadians(gyroValues2[0])));

			motorA.setAcceleration((int) acceleration);
			motorD.setAcceleration((int) acceleration);
			
			if (gyroValues2[0] < 0){
				
				motorA.synchronizeWith(new RegulatedMotor[] {motorD});
				motorA.forward();
				motorD.forward();
				motorA.endSynchronization();

			}else{
				
				motorA.synchronizeWith(new RegulatedMotor[] {motorD});
				motorA.backward();
				motorD.backward();
				motorA.endSynchronization();

			}
			
			gLCD.clear();
			gLCD.drawString(gyroValues2[0] + " " + gyroValues2[1] + " " + acceleration, 0, 0, 0);
			gLCD.refresh();
			
			touchSensor.fetchSample(touchValue, 0);

		}
		
		gyroSensor.close();
		touchSensor.close();
		
		motorA.synchronizeWith(new RegulatedMotor[] {motorD});
		motorA.setAcceleration(10000000);
		motorD.setAcceleration(10000000);
		motorA.setSpeed(0);
		motorD.setSpeed(0);
		motorA.stop();
		motorD.stop();
		motorA.endSynchronization();

		
		motorA.close();
		motorD.close();
		
	}
	
}
