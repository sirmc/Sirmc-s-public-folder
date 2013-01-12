function [] = driveLeft90(mA, mC)
        %% Function to rotate left 90 degrees
        mA.Stop('brake');
        mC.Stop('brake');  
        
        mA.Power = 100;
        mC.Power= 0;
        mA.SendToNXT();
        mC.SendToNXT(); 
        global i;
        i=0;
        mA.ResetPosition(); % Reset wheel encoder
        mA.ReadFromNXT.Position(); % Read wheel position
        %s = NXT_GetOutputState( MOTOR_A );
        while (mA.ReadFromNXT.Position() < 360)
            %% Loop until robot has rotated 90 degrees
            
        end
        mA.Power = 0;
        mC.Power= 0;
        
        mA.Stop('brake');
        mC.Stop('brake'); 
        mA.SendToNXT();
        mC.SendToNXT();          


    
        
    
 
    
