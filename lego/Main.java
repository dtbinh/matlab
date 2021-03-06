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
		final float k = (float) ((180*L)/(Math.PI*r));
		
		//Motor set-up
		RegulatedMotor motorA = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor motorD = new EV3LargeRegulatedMotor(MotorPort.D);
		motorA.setSpeed(100);
		motorA.setAcceleration(0);
		motorA.forward();
		
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
	
		while (touchValue[0] == 0){
			
			gyroSample.fetchSample(gyroValues, 0);
			Delay.msDelay(1);
			gyroSample.fetchSample(gyroValues2, 0);
			float alpha = gyroValues2[1] - gyroValues[1];
			
			
			
			gLCD.clear();
			gLCD.drawString(gyroValues[0] + " " + gyroValues[1], 0, 0, 0);
			gLCD.refresh();
			
			motorA.setAcceleration((int) alpha);
			
			if (gyroValues[0] > 0){
				motorA.forward();
				
			}else{
				motorA.backward();
			}
			
			touchSensor.fetchSample(touchValue, 0);
		}
		
		gyroSensor.close();
		touchSensor.close();
		
		
		motorA.close();
		motorD.close();
		
	}
	
}
