package com.company;

interface HighestVoltageConnection {
    void Charge();
    void HighCharge();
}

interface LowVoltageConnection {
    void Charge();
    void LowCharge();
}

class HighVoltage implements HighestVoltageConnection {
    public void Charge(){
        System.out.println("The phone is connected to charging:");
    }
    public void HighCharge(){
        System.out.println("\t" + "(Socket power 380 Volts)" + "\n");
    }
}

class LowVoltage implements LowVoltageConnection {
    public void Charge(){
        System.out.println("The phone is connected to charging:");
    }
    public void LowCharge(){
        System.out.println("\t" + "(Socket power 220 Volts)");
    }
}

class SocketPower {
    private HighestVoltageConnection highConnection;
    public SocketPower(HighestVoltageConnection highConnection){
        this.highConnection = highConnection;
    }
    public void Connect(){
        highConnection.Charge();
        highConnection.HighCharge();
    }
}

class SocketPowerAdapter implements HighestVoltageConnection {
    LowVoltageConnection lowVoltageConnection;
    public SocketPowerAdapter(LowVoltageConnection lowConnection){
        this.lowVoltageConnection = lowConnection;
    }
    public void Charge(){
        lowVoltageConnection.Charge();
    }
    public void HighCharge(){
        lowVoltageConnection.LowCharge();
    }
}

public class Main {
    public static void main(String[] args) {
        HighVoltage highVoltage = new HighVoltage();
        SocketPower highPower = new SocketPower(highVoltage);
        highPower.Connect();
        SocketPowerAdapter socketPowerAdapter = new SocketPowerAdapter(new LowVoltage());
        SocketPower socketLowPower = new SocketPower(socketPowerAdapter);
        socketLowPower.Connect();
    }
}