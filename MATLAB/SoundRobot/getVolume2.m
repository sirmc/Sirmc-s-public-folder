

function [volume] = getVolume2()
    SampleSize = 200; % Number of samples
    global i; % Sample counter
    i = 1;
    volumeVector = (1:i); % Result vector
    while (i<=SampleSize)
        vol = GetSound(SENSOR_1);
        volumeVector(i) = vol*vol; % Save square of measured volume
        i = i+1
    end
    volume = mean(volumeVector); % Return mean value of result vector



