ev3 = legoev3('usb');
beep(ev3)
clearLCD(ev3)
gyro = gyroSensor(ev3);
angle = readRotationAngle(gyro);
resetRotationAngle(gyro)

motor1 = motor(ev3, 'A');
motor2 = motor(ev3, 'D');

motor1.Speed = 0;
motor2.Speed = 0;

start(motor1)
start(motor2)

dt = 0.000001;
Kp = 3; Ki = 10; Kd = .00001;
setpoint = 0;
previous_error = 0;
integral = 0; 

while ~readButton(ev3, 'up')
    
    measured_value = readRotationAngle(gyro);
    error = setpoint - measured_value;
    integral = integral + error*dt;
    derivative = (error - previous_error)/dt;
    output = Kp*error + Ki*integral + Kd*derivative;
    motor1.Speed = -output;
    motor2.Speed = -output;
    previous_error = error;
    pause(dt)
    
end

stop(motor1)
stop(motor2)
clear
  
  