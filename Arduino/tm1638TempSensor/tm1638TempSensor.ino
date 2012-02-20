/*
Copyright (C) 2012 Sebastian Österlund <sirmcx at gmail dot com>

based on:

TM1638.h - Library for TM1638.
Copyright (C) 2011 Ricardo Batista <rjbatista at gmail dot com>

This program is free software: you can redistribute it and/or modify
it under the terms of the version 3 GNU General Public License as
published by the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

#include <TM1638.h>

//Temp sensor
int scale = 1; // 0=Kelvin, 1=Celsius, 2=Fahrenheit
int a; // analog 0 read variable
int del=1000; // duration between temperature readings
float temperature;
int B=3975; 
float resistance;
int refreshCount = 0;

// define a module on data pin 8, clock pin 9 and strobe pin 7
TM1638 module(8, 9, 7);

void setup() {
  // display a hexadecimal number and set the left 4 dots
  module.setDisplayToDecNumber(151, 2, false);
  Serial.begin(9600);
}

void loop() {
  // Get pressed buttons
  byte keys = module.getButtons();
  
  // Change temperature scale, button 1 (S1) = kelvin, S2 = celsius, S3 = fahrenheit
  int keynr = (int)keys;
  keynr = log(keys << 1)/log(2);
  if (keynr < 4 && keynr > 0)
  {
    scale = keynr-1;
  }
  
  // Check temperature on serial and display temperature on tm1638
  Serial.println(getTemperature());
  module.setDisplayToDecNumber(getTemperature(), 2, false);
  

  
  // light the first 4 red LEDs and the last 4 green LEDs as the buttons are pressed
  module.setLEDs(((keys & 0xF0) << 8) | (keys & 0xF));
}

int getTemperature()
{
  // Wait del times to measure temperature
  if (refreshCount < del)
  {
    refreshCount++;
  }
  else
  {
  // Read temperature on analog pin 0
  a=analogRead(0);
  resistance=(float)(1023-a)*10000/a; 
  temperature=1/(log(resistance/10000)/B+1/298.15); // temperature in kelvin
  
  refreshCount = 0;
  
  // Convert to chosen temperature scale
  switch (scale) {
  case 0:
    temperature = temperature; // Kelvin
    break;
  case 1:
    temperature = temperature-273.15; // Celsius
    break;
  case 2:
    temperature = ((temperature - 273) * 1.8 ) + 32; //Fahrenheit
  
  }
  temperature = temperature*10.0; // Round to 3 significant figures.
  }
  
  
  
  return (int)temperature;
  
}
