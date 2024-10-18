package com.joonseolee.jni;

public class JoonseoJNI {
    public JoonseoJNI() {
    }

    private native void joonseoMethod();

    public static void main(String[] args) {
        JoonseoJNI joonseoJNI = new JoonseoJNI();
        joonseoJNI.joonseoMethod();
    }

    static {
        System.loadLibrary("joonseoJNI");
    }
}
