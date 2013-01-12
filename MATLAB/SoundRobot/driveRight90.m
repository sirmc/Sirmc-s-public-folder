function [] = driveRight90(mA, mC)
        %% Function to drive right 90 degrees
        mA.Stop('brake');
        mC.Stop('brake');  
        
        mA.Power = 0;
        mC.Power= 100;
        mA.SendToNXT();
        mC.SendToNXT(); 
        global i;
        i=0;
        mC.ResetPosition(); % Reset wheel encoder
        mC.ReadFromNXT.Position(); % Read postition from motor wheel encoder
        while (mC.ReadFromNXT.Position() < 360)
            %% Loop until robot has rotated 90 degrees
            
        end
        mA.Power = 0;
        mC.Power= 0;
        
        mA.Stop('brake');
        mC.Stop('brake'); 
        mA.SendToNXT();
        mC.SendToNXT();          


    
        
    
 
    
