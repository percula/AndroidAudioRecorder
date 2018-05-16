package cafe.adriel.androidaudiorecorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;

import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;

public class AndroidAudioRecorder {

    protected static final String EXTRA_FILE_PATH = "filePath";
    protected static final String EXTRA_COLOR = "color";
    protected static final String EXTRA_SOURCE = "source";
    protected static final String EXTRA_CHANNEL = "channel";
    protected static final String EXTRA_SAMPLE_RATE = "sampleRate";
    protected static final String EXTRA_AUTO_START = "autoStart";
    protected static final String EXTRA_KEEP_DISPLAY_ON = "keepDisplayOn";

    private String filePath = Environment.getExternalStorageDirectory() + "/recorded_audio.wav";
    private AudioSource source = AudioSource.MIC;
    private AudioChannel channel = AudioChannel.STEREO;
    private AudioSampleRate sampleRate = AudioSampleRate.HZ_44100;
    private int color = Color.parseColor("#546E7A");
    private int requestCode = 0;
    private boolean autoStart = false;
    private boolean keepDisplayOn = false;

    public AndroidAudioRecorder() {}

    public AndroidAudioRecorder setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }

    public AndroidAudioRecorder setColor(int color) {
        this.color = color;
        return this;
    }

    public AndroidAudioRecorder setRequestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    public AndroidAudioRecorder setSource(AudioSource source) {
        this.source = source;
        return this;
    }

    public AndroidAudioRecorder setChannel(AudioChannel channel) {
        this.channel = channel;
        return this;
    }

    public AndroidAudioRecorder setSampleRate(AudioSampleRate sampleRate) {
        this.sampleRate = sampleRate;
        return this;
    }

    public AndroidAudioRecorder setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
        return this;
    }

    public AndroidAudioRecorder setKeepDisplayOn(boolean keepDisplayOn) {
        this.keepDisplayOn = keepDisplayOn;
        return this;
    }

    public void record(Activity activity) {
        activity.startActivityForResult(makeRecordIntent(activity), requestCode);
    }

    public void record(Fragment fragment) {
        fragment.startActivityForResult(makeRecordIntent(fragment.getContext()), requestCode);
    }

    public Bundle makeIntentBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_FILE_PATH, filePath);
        bundle.putInt(EXTRA_COLOR, color);
        bundle.putSerializable(EXTRA_SOURCE, source);
        bundle.putSerializable(EXTRA_CHANNEL, channel);
        bundle.putSerializable(EXTRA_SAMPLE_RATE, sampleRate);
        bundle.putBoolean(EXTRA_AUTO_START, autoStart);
        bundle.putBoolean(EXTRA_KEEP_DISPLAY_ON, keepDisplayOn);
        return bundle;
    }

    public Intent makeRecordIntent(Context context) {
        Intent intent = new Intent(context, AudioRecorderActivity.class);
        intent.putExtras(makeIntentBundle());
        return intent;
    }

}
