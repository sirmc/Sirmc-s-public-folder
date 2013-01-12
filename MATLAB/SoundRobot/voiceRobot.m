function [] = voiceRobot()
    COM_CloseNXT('all');
    close all;
    h = COM_OpenNXT();
    COM_SetDefaultNXT(h);
    global mA; % Motor Variables
    global mC;
    mA = NXTMotor('A'); % Right motor
    mA.Power = 50;
    mC = NXTMotor('C'); % Left motor
    mC.Power = 50;

    OpenSound(SENSOR_1, 'DB'); % Initialize sound sensor
    while (true)
        pause(0.5); % Short pause, to minimize motor noise
        middleVolume = getVolume2(); % Get volume of middle intersection path
        driveLeft90(mA, mC);
        pause(0.5);
        leftVolume = getVolume2(); % Get volume of left intersection path
        driveRight90(mA, mC);
        driveRight90(mA, mC);
        pause(0.5);
        rightVolume = getVolume2(); % Get volume of right intersection path
        if (rightVolume > middleVolume && rightVolume > leftVolume)
            %% Right volume is loudest
            %% Stay at this direction
        elseif (middleVolume > rightVolume && middleVolume > rightVolume)
            %% Middle volume is loudest
            %% Drive to the middle
            driveLeft90(mA, mC);
        else
            %% Left volume is loudest
            driveLeft90(mA, mC);
            driveLeft90(mA, mC);
        end
        mA.Power = 30;
        mC.Power = 30;
        mA.SendToNXT();
        mC.SendToNXT();
        pause(4); %% Drive forward 4 seconds
    end
            
