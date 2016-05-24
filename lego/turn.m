function turn(motor, degrees, power, stopmode)

resetRotation(motor)
motor.Speed = power;
start(motor)

while abs(readRotation(motor)) <= degrees
end

stop(motor, stopmode)

end
