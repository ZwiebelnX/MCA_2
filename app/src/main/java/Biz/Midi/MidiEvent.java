package Biz.Midi;

import java.util.ArrayList;

/**
 * Created by 78422 on 2018/5/8.
 */

public class MidiEvent {
    public int time;
    public byte code;
    public ArrayList<Byte> data=new ArrayList<Byte>();

    public static class Meta{
        public static MidiEvent MetaEvent(int subcode,ArrayList<Byte> data) {
            MidiEvent ret=new MidiEvent();
            ret.time=0;
            ret.code=(byte)0xff;
            ret.data.add((byte)(subcode&0xff));
            ret.data.add((byte)(data.size()&0xff));
            ret.data.addAll(data);
            return ret;
        }

        public static MidiEvent Commit(String commit) {
            ArrayList<Byte> data=new ArrayList<Byte>();
            byte[] dataArr=commit.getBytes();
            for(int i=0;i<dataArr.length;i++) {
                data.add(dataArr[i]);
            }
            return MetaEvent(0x01,data);
        }

        public static MidiEvent Copyright(String copyright) {
            ArrayList<Byte> data=new ArrayList<Byte>();
            byte[] dataArr=copyright.getBytes();
            for(int i=0;i<dataArr.length;i++) {
                data.add(dataArr[i]);
            }
            return MetaEvent(0x02,data);
        }

        public static MidiEvent Title(String title) {
            ArrayList<Byte> data=new ArrayList<Byte>();
            byte[] dataArr=title.getBytes();
            for(int i=0;i<dataArr.length;i++) {
                data.add(dataArr[i]);
            }
            return MetaEvent(0x03,data);
        }

        public static MidiEvent InstrumentName(String name) {
            ArrayList<Byte> data=new ArrayList<Byte>();
            byte[] dataArr=name.getBytes();
            for(int i=0;i<dataArr.length;i++) {
                data.add(dataArr[i]);
            }
            return MetaEvent(0x04,data);
        }

        //public static MidiEvent Words(String words);//0x05
        //public static MidiEvent Marker(String marker);//0x06
        //public static MidiEvent StartPoint(String text);//0x07

        public static MidiEvent ProgramName(String name) {
            ArrayList<Byte> data=new ArrayList<Byte>();
            byte[] dataArr=name.getBytes();
            for(int i=0;i<dataArr.length;i++) {
                data.add(dataArr[i]);
            }
            return MetaEvent(0x08,data);
        }

        public static MidiEvent DeviceName(String name) {
            ArrayList<Byte> data=new ArrayList<Byte>();
            byte[] dataArr=name.getBytes();
            for(int i=0;i<dataArr.length;i++) {
                data.add(dataArr[i]);
            }
            return MetaEvent(0x09,data);
        }

        public static MidiEvent Channel(int channel) {
            MidiEvent ret=new MidiEvent();
            ret.time=0;
            ret.code=(byte)0xff;
            ret.data.add((byte)0x20);
            ret.data.add((byte)0x01);
            ret.data.add((byte)(channel&0xf));
            return ret;
        }

        public static MidiEvent Port(int port) {
            MidiEvent ret=new MidiEvent();
            ret.time=0;
            ret.code=(byte)0xff;
            ret.data.add((byte)0x21);
            ret.data.add((byte)0x01);
            ret.data.add((byte)(port&0xf));
            return ret;
        }

        public static MidiEvent Stop() {
            MidiEvent ret=new MidiEvent();
            ret.time=0;
            ret.code=(byte)0xff;
            ret.data.add((byte)0x2f);
            ret.data.add((byte)0);
            return ret;
        }

        public static MidiEvent Speed(int speed) {//base us
            return MetaEvent(0x51,MidiUtils.Int2List(speed,3));
        }

        public static MidiEvent SMPTETime(int hour,int minute,int second,int frame,int multiframe) {
            MidiEvent ret=new MidiEvent();
            ret.time=0;
            ret.code=(byte)0xff;
            ret.data.add((byte)0x54);
            ret.data.add((byte)0x05);
            ret.data.add((byte)(hour&0xff));
            ret.data.add((byte)(minute&0xff));
            ret.data.add((byte)(second&0xff));
            ret.data.add((byte)(frame&0xff));
            ret.data.add((byte)(multiframe&0xff));
            return ret;
        }

        public static MidiEvent Meter(int num,int denom) {
            MidiEvent ret=new MidiEvent();
            ret.time=0;
            ret.code=(byte)0xff;
            ret.data.add((byte)0x58);
            ret.data.add((byte)0x04);
            ret.data.add((byte)(num&0xff));
            ret.data.add((byte)(denom&0xff));
            ret.data.add((byte)0x18);
            ret.data.add((byte)0x08);
            return ret;
        }

        public static MidiEvent Signature(int signCount,int type) {
            MidiEvent ret=new MidiEvent();
            ret.time=0;
            ret.code=(byte)0xff;
            ret.data.add((byte)0x59);
            ret.data.add((byte)0x02);
            ret.data.add((byte)(signCount&0xff));
            ret.data.add((byte)(type&0xff));
            return ret;
        }
    }

    public static MidiEvent NoteOff(int timeOffset,int channel,int pitch,int dynamics) {
        MidiEvent ret=new MidiEvent();
        ret.time=timeOffset;
        ret.code=(byte)(0x80+(channel&0xf));
        ret.data.add((byte)(pitch&0x7f));
        ret.data.add((byte)(dynamics&0x7f));
        return ret;
    }

    public static MidiEvent NoteOn(int timeOffset,int channel,int pitch,int dynamics) {
        MidiEvent ret=new MidiEvent();
        ret.time=timeOffset;
        ret.code=(byte)(0x90+(channel&0xf));
        ret.data.add((byte)(pitch&0x7f));
        ret.data.add((byte)(dynamics&0x7f));
        return ret;
    }

    public static MidiEvent KeyAfterTouch(int timeOffset,int channel,int pitch,int dynamics) {
        MidiEvent ret=new MidiEvent();
        ret.time=timeOffset;
        ret.code=(byte)(0xa0+(channel&0xf));
        ret.data.add((byte)(pitch&0x7f));
        ret.data.add((byte)(dynamics&0x7f));
        return ret;
    }

    public static MidiEvent Control(int timeOffset,int channel,int id,int param) {
        MidiEvent ret=new MidiEvent();
        ret.time=timeOffset;
        ret.code=(byte)(0xb0+(channel&0xf));
        ret.data.add((byte)(id&0x7f));
        ret.data.add((byte)(param&0x7f));
        return ret;
    }

    public static MidiEvent Instrument(int channel,int id) {
        MidiEvent ret=new MidiEvent();
        ret.time=0;
        ret.code=(byte)(0xc0+(channel&0xf));
        ret.data.add((byte)(id&0x7f));
        return ret;
    }

    public static MidiEvent AfterTouch(int timeOffset,int channel,int value) {
        MidiEvent ret=new MidiEvent();
        ret.time=timeOffset;
        ret.code=(byte)(0xd0+(channel&0xf));
        ret.data.add((byte)(value&0x7f));
        return ret;
    }

    public static MidiEvent Portamento(int timeOffset,int channel,int pitch) {
        MidiEvent ret=new MidiEvent();
        ret.time=timeOffset;
        ret.code=(byte)(0x0+(channel&0xf));
        ret.data.add((byte)(pitch&0x7f));
        ret.data.add((byte)((pitch>>7)&0x7f));
        return ret;
    }

    public static MidiEvent SystemCode(int timeOffset,ArrayList<Byte> data) {
        if(data.get(data.size()-1)!=0xf7) {
            data.add((byte)0xf7);
        }
        MidiEvent ret=new MidiEvent();
        ret.time=timeOffset;
        ret.code=(byte)(0xf0);
        ret.data.add((byte)(data.size()&0x7f));
        ret.data.addAll(data);
        return ret;
    }
}
