clear
ev3 = legoev3('usb');
beep(ev3)
clearLCD(ev3)
gyro = gyroSensor(ev3);
angle = readRotationAngle(gyro);
resetRotationAngle(gyro)

SPEED = 30;

motor1 = motor(ev3, 'A');
motor2 = motor(ev3, 'D');

motor1.Speed = 0;
motor2.Speed = 0;

start(motor1)
start(motor2)

while ~readButton(ev3, 'up')
    
    SPEED = readRotationAngle(gyro);
    writeLCD(ev3, num2str(angle))
    if SPEED >= 0
        motor1.Speed = SPEED;
        motor2.Speed = SPEED;
    else
        motor1.Speed = -SPEED;
        motor2.Speed = -SPEED;
    end
end

stop(motor1)
stop(motor2)
clear
