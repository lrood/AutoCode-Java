# AutoCode-Java
This program sends a command to an ELM327 interface and returns and processes a string into an OBD II code
This program was written for Linux. 

You must download and compile SerialConnection.java and AutoCode.java(make .class files)  You will also need to 
download jssc.jar from the internet and extract the files.  Grab SerialPortException.class and SerialPort.class  Put SerialConnection.class, AutoCode.class, SerialPortException.class, and SerialPort.class in the same folder and run AutoCode.class.

You may also compile all four files into a jar file.
This program should work on most vehicles 1996 and newer. You must have an ELM327 USB interface. Mine worked at 
38400 baud.  The port is set as /dev/ttyUSB0
