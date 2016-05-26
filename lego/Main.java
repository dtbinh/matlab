import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class LEGO {

	public static void main(String[] args) {
		
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
		
	    //System constants
	    final int L = 1;
		final int r = 1;
		
		while (touchValue[0] == 0){
			
			gyroSensor.getAngleAndRateMode().fetchSample(gyroValues, 0);
			float rate = (float) (-180*L*gyroValues[1]*gyroValues[0]/(Math.PI*r));
			int round = Math.round(rate);
			gLCD.clear();
			gLCD.drawString("dtheta/dt = " + round, SW/2, SH/2, GraphicsLCD.BOTTOM | GraphicsLCD.HCENTER);
			gLCD.refresh();
			
			touchSensor.fetchSample(touchValue, 0);

		}
		
		gyroSensor.close();
		touchSensor.close();
		
		}
	
	}
