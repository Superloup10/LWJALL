package eu.thog92.lwjall;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public interface ICodec
{
    /**
     * Should make any preperations required before reading from the audio stream.
     * If another stream is already opened, it should be closed and a new audio
     * stream opened in its place. This method is used internally
     * not only to initialize a stream, but also to rewind streams and to switch
     * stream sources on the fly.
     * 
     * @return False if an error occurred or if end of stream was reached.
     * @throws UnsupportedAudioFileException
     */
    boolean initialize(URL url, IChannel channel) throws IOException, UnsupportedAudioFileException;

    /**
     * Should return false if the stream is busy initializing.  To prevent bad
     * data from being returned by this method, derived classes should internally
     * synchronize with any elements used by both the initialized() and initialize()
     * methods.
     * @return True if steam is initialized.
     */
    boolean initialized();

    void cleanup();

    AudioBuffer read(int n) throws IOException;

    AudioBuffer readAll() throws IOException;

    /**
     * Should return the audio format of the data being returned by the read() and
     * readAll() methods.
     * @return Information wrapped into an AudioFormat context.
     */
    AudioFormat getAudioFormat();

    void setAudioFormat(AudioFormat format);

    void update(int buffersProcessed);

    InputStream getInputStream();

    boolean prepareBuffers(int n) throws IOException;

}
