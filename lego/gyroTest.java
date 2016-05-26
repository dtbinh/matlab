import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;

public class LEGO {

	public static void main(String[] args) {
		
		GraphicsLCD gLCD = LocalEV3.get().getGraphicsLCD();
		final int SW = gLCD.getWidth();
	    	final int SH = gLCD.getHeight();
		gLCD.setFont(Font.getDefaultFont());
		int L = 1;
		int r = 1;
		EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S1);
		SampleProvider touch = touchSensor.getMode("Touch");
		float[] sample = new float[touch.sampleSize()];
		touch.fetchSample(sample, 0);
		EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S4);
		gyro.reset();
		float[] angleAndRate = new float[2];
		gyro.getAngleAndRateMode().fetchSample(angleAndRate, 0);
		
		while (sample[0] == 0){
			
			gyro.getAngleAndRateMode().fetchSample(angleAndRate, 0);
			
			float rate = (float) (-180*L*angleAndRate[1]*angleAndRate[0]/(3.14*r));
			int round = Math.round(rate);
			LCD.clear();
			gLCD.drawString("dtheta/dt = " + round, SW/2, SH/2, GraphicsLCD.BOTTOM | GraphicsLCD.HCENTER);
			LCD.refresh();
			
			
			touch.fetchSample(sample, 0);

		}
		
		gyro.close();
		touchSensor.close();
		
		}
	
	}
