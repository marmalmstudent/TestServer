// 
// Decompiled by Procyon v0.5.29
// 
package Server;

public class stream
{
    private static final int frameStackSize = 10;
    private int frameStackPtr;
    private int[] frameStack;
    public byte[] buffer;
    public int currentOffset;
    public int bitPosition;
    public static int[] bitMaskOut;
    public Cryption packetEncryption;
    
    public stream() {
        this.frameStackPtr = -1;
        this.frameStack = new int[10];
        this.buffer = null;
        this.currentOffset = 0;
        this.bitPosition = 0;
        this.packetEncryption = null;
    }
    
    public stream(final byte[] buffer) {
        this.frameStackPtr = -1;
        this.frameStack = new int[10];
        this.buffer = null;
        this.currentOffset = 0;
        this.bitPosition = 0;
        this.packetEncryption = null;
        this.buffer = buffer;
        this.currentOffset = 0;
    }
    
    public byte readSignedByteA() {
        return (byte)(this.buffer[this.currentOffset++] - 128);
    }
    
    public byte readSignedByteC() {
        return (byte)(-this.buffer[this.currentOffset++]);
    }
    
    public byte readSignedByteS() {
        return (byte)(128 - this.buffer[this.currentOffset++]);
    }
    
    public int readUnsignedByteA() {
        return this.buffer[this.currentOffset++] - 128 & 0xFF;
    }
    
    public int readUnsignedByteC() {
        return -this.buffer[this.currentOffset++] & 0xFF;
    }
    
    public int readUnsignedByteS() {
        return 128 - this.buffer[this.currentOffset++] & 0xFF;
    }
    
    public void writeByteA(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n + 128);
    }
    
    public void writeByteS(final int n) {
        this.buffer[this.currentOffset++] = (byte)(128 - n);
    }
    
    public void writeByteC(final int n) {
        this.buffer[this.currentOffset++] = (byte)(-n);
    }
    
    public int readSignedWordBigEndian() {
        this.currentOffset += 2;
        int n = ((this.buffer[this.currentOffset - 1] & 0xFF) << 8) + (this.buffer[this.currentOffset - 2] & 0xFF);
        if (n > 32767) {
            n -= 65536;
        }
        return n;
    }
    
    public int readSignedWordA() {
        this.currentOffset += 2;
        int n = ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] - 128 & 0xFF);
        if (n > 32767) {
            n -= 65536;
        }
        return n;
    }
    
    public int readSignedWordBigEndianA() {
        this.currentOffset += 2;
        int n = ((this.buffer[this.currentOffset - 1] & 0xFF) << 8) + (this.buffer[this.currentOffset - 2] - 128 & 0xFF);
        if (n > 32767) {
            n -= 65536;
        }
        return n;
    }
    
    public int readUnsignedWordBigEndian() {
        this.currentOffset += 2;
        return ((this.buffer[this.currentOffset - 1] & 0xFF) << 8) + (this.buffer[this.currentOffset - 2] & 0xFF);
    }
    
    public int readUnsignedWordA() {
        this.currentOffset += 2;
        return ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] - 128 & 0xFF);
    }
    
    public int readUnsignedWordBigEndianA() {
        this.currentOffset += 2;
        return ((this.buffer[this.currentOffset - 1] & 0xFF) << 8) + (this.buffer[this.currentOffset - 2] - 128 & 0xFF);
    }
    
    public void writeWordBigEndianA(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n + 128);
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
    }
    
    public void writeWordA(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)(n + 128);
    }
    
    public void writeWordBigEndian_dup(final int n) {
        this.buffer[this.currentOffset++] = (byte)n;
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
    }
    
    public int readDWord_v1() {
        this.currentOffset += 4;
        return ((this.buffer[this.currentOffset - 2] & 0xFF) << 24) + ((this.buffer[this.currentOffset - 1] & 0xFF) << 16) + ((this.buffer[this.currentOffset - 4] & 0xFF) << 8) + (this.buffer[this.currentOffset - 3] & 0xFF);
    }
    
    public int readDWord_v2() {
        this.currentOffset += 4;
        return ((this.buffer[this.currentOffset - 3] & 0xFF) << 24) + ((this.buffer[this.currentOffset - 4] & 0xFF) << 16) + ((this.buffer[this.currentOffset - 1] & 0xFF) << 8) + (this.buffer[this.currentOffset - 2] & 0xFF);
    }
    
    public void writeDWord_v1(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)n;
        this.buffer[this.currentOffset++] = (byte)(n >> 24);
        this.buffer[this.currentOffset++] = (byte)(n >> 16);
    }
    
    public void writeDWord_v2(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n >> 16);
        this.buffer[this.currentOffset++] = (byte)(n >> 24);
        this.buffer[this.currentOffset++] = (byte)n;
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
    }
    
    public void readBytes_reverse(final byte[] array, final int n, final int n2) {
        for (int i = n2 + n - 1; i >= n2; --i) {
            array[i] = this.buffer[this.currentOffset++];
        }
    }
    
    public void writeBytes_reverse(final byte[] array, final int n, final int n2) {
        for (int i = n2 + n - 1; i >= n2; --i) {
            this.buffer[this.currentOffset++] = array[i];
        }
    }
    
    public void readBytes_reverseA(final byte[] array, final int n, final int n2) {
        for (int i = n2 + n - 1; i >= n2; --i) {
            array[i] = (byte)(this.buffer[this.currentOffset++] - 128);
        }
    }
    
    public void writeBytes_reverseA(final byte[] array, final int n, final int n2) {
        for (int i = n2 + n - 1; i >= n2; --i) {
            this.buffer[this.currentOffset++] = (byte)(array[i] + 128);
        }
    }
    
    public void createFrame(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n + this.packetEncryption.getNextKey());
    }
    
    public void createFrameVarSize(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n + this.packetEncryption.getNextKey());
        this.buffer[this.currentOffset++] = 0;
        if (this.frameStackPtr >= 9) {
            throw new RuntimeException("Stack overflow");
        }
        this.frameStack[++this.frameStackPtr] = this.currentOffset;
    }
    
    public void createFrameVarSizeWord(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n + this.packetEncryption.getNextKey());
        this.writeWord(0);
        if (this.frameStackPtr >= 9) {
            throw new RuntimeException("Stack overflow");
        }
        this.frameStack[++this.frameStackPtr] = this.currentOffset;
    }
    
    public void endFrameVarSize() {
        if (this.frameStackPtr < 0) {
            throw new RuntimeException("Stack empty");
        }
        this.writeFrameSize(this.currentOffset - this.frameStack[this.frameStackPtr--]);
    }
    
    public void endFrameVarSizeWord() {
        if (this.frameStackPtr < 0) {
            throw new RuntimeException("Stack empty");
        }
        this.writeFrameSizeWord(this.currentOffset - this.frameStack[this.frameStackPtr--]);
    }
    
    public void writeByte(final int n) {
        this.buffer[this.currentOffset++] = (byte)n;
    }
    
    public void writeWord(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)n;
    }
    
    public void writeWordBigEndian(final int n) {
        this.buffer[this.currentOffset++] = (byte)n;
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
    }
    
    public void write3Byte(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n >> 16);
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)n;
    }
    
    public void writeDWord(final int n) {
        this.buffer[this.currentOffset++] = (byte)(n >> 24);
        this.buffer[this.currentOffset++] = (byte)(n >> 16);
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)n;
    }
    
    public void writeDWordBigEndian(final int n) {
        this.buffer[this.currentOffset++] = (byte)n;
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)(n >> 16);
        this.buffer[this.currentOffset++] = (byte)(n >> 24);
    }
    
    public void writeQWord(final long n) {
        this.buffer[this.currentOffset++] = (byte)(n >> 56);
        this.buffer[this.currentOffset++] = (byte)(n >> 48);
        this.buffer[this.currentOffset++] = (byte)(n >> 40);
        this.buffer[this.currentOffset++] = (byte)(n >> 32);
        this.buffer[this.currentOffset++] = (byte)(n >> 24);
        this.buffer[this.currentOffset++] = (byte)(n >> 16);
        this.buffer[this.currentOffset++] = (byte)(n >> 8);
        this.buffer[this.currentOffset++] = (byte)n;
    }
    
    public void writeString(final String s) {
        s.getBytes(0, s.length(), this.buffer, this.currentOffset);
        this.currentOffset += s.length();
        this.buffer[this.currentOffset++] = 10;
    }
    
    public void writeBytes(final byte[] array, final int n, final int n2) {
        for (int i = n2; i < n2 + n; ++i) {
            this.buffer[this.currentOffset++] = array[i];
        }
    }
    
    public void writeFrameSize(final int n) {
        this.buffer[this.currentOffset - n - 1] = (byte)n;
    }
    
    public void writeFrameSizeWord(final int n) {
        this.buffer[this.currentOffset - n - 2] = (byte)(n >> 8);
        this.buffer[this.currentOffset - n - 1] = (byte)n;
    }
    
    public int readUnsignedByte() {
        return this.buffer[this.currentOffset++] & 0xFF;
    }
    
    public byte readSignedByte() {
        return this.buffer[this.currentOffset++];
    }
    
    public int readUnsignedWord() {
        this.currentOffset += 2;
        return ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] & 0xFF);
    }
    
    public int readSignedWord() {
        this.currentOffset += 2;
        int n = ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] & 0xFF);
        if (n > 32767) {
            n -= 65536;
        }
        return n;
    }
    
    public int readDWord() {
        this.currentOffset += 4;
        return ((this.buffer[this.currentOffset - 4] & 0xFF) << 24) + ((this.buffer[this.currentOffset - 3] & 0xFF) << 16) + ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] & 0xFF);
    }
    
    public long readQWord() {
        return ((this.readDWord() & 0xFFFFFFFFL) << 32) + (this.readDWord() & 0xFFFFFFFFL);
    }
    
    public long readQWord2() {
        this.currentOffset += 8;
        return ((this.buffer[this.currentOffset - 8] & 0xFF) << 56) + ((this.buffer[this.currentOffset - 7] & 0xFF) << 48) + ((this.buffer[this.currentOffset - 6] & 0xFF) << 40) + ((this.buffer[this.currentOffset - 5] & 0xFF) << 32) + ((this.buffer[this.currentOffset - 4] & 0xFF) << 24) + ((this.buffer[this.currentOffset - 3] & 0xFF) << 16) + ((this.buffer[this.currentOffset - 2] & 0xFF) << 8) + (this.buffer[this.currentOffset - 1] & 0xFF);
    }
    
    public String readString() {
        final int currentOffset = this.currentOffset;
        while (this.buffer[this.currentOffset++] != 10) {}
        return new String(this.buffer, currentOffset, this.currentOffset - currentOffset - 1);
    }
    
    public void readBytes(final byte[] array, final int n, final int n2) {
        for (int i = n2; i < n2 + n; ++i) {
            array[i] = this.buffer[this.currentOffset++];
        }
    }
    
    public void initBitAccess() {
        this.bitPosition = this.currentOffset * 8;
    }
    
    public void writeBits(int i, final int n) {
        int n2 = this.bitPosition >> 3;
        int n3 = 8 - (this.bitPosition & 0x7);
        this.bitPosition += i;
        while (i > n3) {
            final byte[] buffer = this.buffer;
            final int n4 = n2;
            buffer[n4] &= (byte)~stream.bitMaskOut[n3];
            final byte[] buffer2 = this.buffer;
            final int n5 = n2++;
            buffer2[n5] |= (byte)(n >> i - n3 & stream.bitMaskOut[n3]);
            i -= n3;
            n3 = 8;
        }
        if (i == n3) {
            final byte[] buffer3 = this.buffer;
            final int n6 = n2;
            buffer3[n6] &= (byte)~stream.bitMaskOut[n3];
            final byte[] buffer4 = this.buffer;
            final int n7 = n2;
            buffer4[n7] |= (byte)(n & stream.bitMaskOut[n3]);
        }
        else {
            final byte[] buffer5 = this.buffer;
            final int n8 = n2;
            buffer5[n8] &= (byte)~(stream.bitMaskOut[i] << n3 - i);
            final byte[] buffer6 = this.buffer;
            final int n9 = n2;
            buffer6[n9] |= (byte)((n & stream.bitMaskOut[i]) << n3 - i);
        }
    }
    
    public void finishBitAccess() {
        this.currentOffset = (this.bitPosition + 7) / 8;
    }
    
    static {
        stream.bitMaskOut = new int[32];
        for (int i = 0; i < 32; ++i) {
            stream.bitMaskOut[i] = (1 << i) - 1;
        }
    }
}
