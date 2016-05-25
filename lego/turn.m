function rate = turn(motor, power)
resetRotation(motor)
motor.Speed = power;
time = 0;
start(motor)
while true
    time = time + 1;
    pause(1)
    rate = readRotation(motor)/time
end
end

