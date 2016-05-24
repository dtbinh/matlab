clear
ev3 = legoev3;
beep(ev3)
gyro = gyroSensor(ev3);
resetRotationAngle(gyro)

motor1 = motor(ev3, 'A');
motor2 = motor(ev3, 'D');
motor1.Speed = 0;
motor2.Speed = 0;
start(motor1)
start(motor2)

Kp = 5; Ki = 00; Kd = 0;
dt = 0.001;
setpoint = readRotationAngle(gyro);
previous_error = 0;
integral = 0; 

while ~readButton(ev3, 'up')
    
    measured_value = readRotationAngle(gyro);
    error = setpoint - measured_value;
    integral = integral + error*dt;
    derivative = (error - previous_error)/dt;
    output = Kp*error + Ki*integral + Kd*derivative;
    previous_error = error;
    disp([measured_angle output])
   
    motor1.Speed = -output;
    motor2.Speed = -output;
    pause(dt)
    
end

stop(motor1)
stop(motor2)
