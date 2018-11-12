/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cinco.midiorganizer;
import javax.sound.midi.*;
import java.util.List;

public class MidiMulti
{

    public MidiMulti()
    {
        MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (MidiDevice.Info info : infos) {
            try {
                device = MidiSystem.getMidiDevice(info);
                //does the device have any transmitters?
                //if it does, add it to the device list
                System.out.println(info);
                //get all transmitters
                List<Transmitter> transmitters = device.getTransmitters();
                //and for each transmitter
                for(int j = 0; j<transmitters.size();j++) {
                    //create a new receiver
                    transmitters.get(j).setReceiver(
                            //using my own MidiInputReceiver
                            new MidiInputReceiver(device.getDeviceInfo().toString())
                    );
                }   Transmitter trans = device.getTransmitter();
                trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));
                //open each device
                device.open();
                //if code gets this far without throwing an exception
                //print a success message
                System.out.println(device.getDeviceInfo()+" Was Opened");
            }catch (MidiUnavailableException e) {}
        }

}
//tried to write my own class. I thought the send method handles an MidiEvents sent to it
public class MidiInputReceiver implements Receiver {
    public String name;
    public MidiInputReceiver(String name) {
        this.name = name;
    }
    @Override
    public void send(MidiMessage msg, long timeStamp) {
        System.out.println("midi received");
    }
    @Override
    public void close() {}
    }
}
