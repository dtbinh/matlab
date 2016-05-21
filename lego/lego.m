clear all

myev3 = legoev3;
beep(myev3)

mymotor1 = motor(myev3, 'B');
mymotor2 = motor(myev3, 'C');

mymotor1.Speed = -100;
mymotor2.Speed = -100;

start(mymotor1)
start(mymotor2)

mysensor = sonicSensor(myev3);

while 1
    dis = readDistance(mysensor);
    if dis <= .7
        mymotor1.Speed = 100;
        pause(.3)
        mymotor1.Speed = -100;
    end
end
