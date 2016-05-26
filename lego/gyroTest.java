import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.utility.Delay;

public class LEGO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S4);
		Delay.msDelay(2000);
		gyro.reset();
		float[] angleAndRate = new float[2];
		gyro.getAngleAndRateMode().fetchSample(angleAndRate, 0);
		LCD.drawString(angleAndRate[0] + " " + angleAndRate[1], 0, 0);
		
		for(int i = 0; i < 1000; i += 1){
			
			gyro.getAngleAndRateMode().fetchSample(angleAndRate, 0);
			LCD.drawString(angleAndRate[0] + " " + angleAndRate[1], 0, 0);
			
		}
		
		gyro.close();
		
		}
	
	}


