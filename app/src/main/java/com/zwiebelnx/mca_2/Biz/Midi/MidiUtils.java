package com.zwiebelnx.mca_2.Biz.Midi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 78422 on 2018/5/8.
 */

public class MidiUtils {
    public static final int SPQN=500000;
    public static final int QNPB=4;
    public static final int DYNAMICS=127;
    public static final int NOTE_LEN=120;

    public static byte[] List2Arr(ArrayList<Byte> list) {
        byte[] arr=new byte[list.size()];
        for(int i=0;i<list.size();i++) {
            arr[i]=list.get(i);
        }
        return arr;
    }

    public static ArrayList<Byte> Int2List(int data,int count){
        ArrayList<Byte> ret=new ArrayList();
        for(int i=0;i<count;i++) {
            ret.add((byte)(data&0xff));
            data>>=8;
        }
        Collections.reverse(ret);
        return ret;
    }

    static ArrayList<Byte> Time2List(int time) {
        ArrayList<Byte> arr=new ArrayList();
        int mark=0;
        for(int i=time;i>0;i>>=7) {
            arr.add((byte)((i&0x7f)+mark));
            mark=0x80;
        }
        if(arr.size()==0) {
            arr.add((byte)0);
        }
        Collections.reverse(arr);
        return arr;
    }

    static ArrayList<Byte> CreateBlock(String type,ArrayList<Byte> data){
        ArrayList<Byte> ret=new ArrayList();
        byte[] msg=type.getBytes();
        for(int i=0;i<4;i++) {
            ret.add(msg[i]);
        }
        ret.addAll(Int2List(data.size(),4));
        ret.addAll(data);
        return ret;
    }

    public static ArrayList<Byte> CreateHead(int type,int tracks,int TPQN){
        if(type!=0) {
            tracks+=1;
        }
        ArrayList<Byte> data=new ArrayList();
        data.addAll(Int2List(type,2));
        data.addAll(Int2List(tracks,2));
        data.addAll(Int2List(TPQN,2));
        return CreateBlock("MThd",data);
    }

    public static ArrayList<Byte> CreateTrack(ArrayList<MidiEvent> events){
        ArrayList<Byte> data=new ArrayList();
        for(MidiEvent event:events) {
            data.addAll(Time2List(event.time));
            data.add(event.code);
            data.addAll(event.data);
        }
        return CreateBlock("MTrk",data);
    }

    public static byte[] Generate(List<Integer> Slist, int instrument){
        ArrayList<MidiEvent> events=new ArrayList();
        events.add(MidiEvent.Meta.Speed(SPQN));
        events.add(MidiEvent.Meta.Meter(QNPB, 2));
        events.add(MidiEvent.Meta.Signature(0, 0));
        events.add(MidiEvent.Instrument(0, instrument));
        for(Integer s:Slist) {
            events.add(MidiEvent.NoteOn(0, 0, s, DYNAMICS));
            events.add(MidiEvent.NoteOff(NOTE_LEN, 0, s, DYNAMICS));
        }
        events.add(MidiEvent.Meta.Stop());
        ArrayList<Byte> list=CreateHead(0,1,120);
        list.addAll(CreateTrack(events));
        return List2Arr(list);
    }
}
