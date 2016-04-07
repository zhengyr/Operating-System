//Yiran Zheng
//CS131
//zhengyr@brandeis.edu

package fileSystem;

/**
 * Manage bitwise operations in a byte or array of bytes.
 *
 * Unit tests are in {@see TestBitwise}. See TestBitwise.java.
 */
public class Bitwise {

    private static final int bitmasks[] = {1, 2, 4, 8, 16, 32, 64, 128};

    /**
     * Check to see if bit i is set in byte. Returns true if it is
     * set, false otherwise.
     */
    public static boolean isset(int i, byte b) {//compare the given byte with bitmasks using & operating
    	return (bitmasks[i] & b) == bitmasks[i];
    }

    /**
     * Check to see if bit i is set in array of bytes. Returns true if
     * it is set, false otherwise.
     */
    public static boolean isset(int i, byte bytes[]) {//find the byte in the array and use previous method 
    	return isset(i%bitmasks.length, bytes[bytes.length-i/bitmasks.length-1]);
    }

    /**
     * Set bit i in byte and return the new byte.
     */
    public static byte set(int i, byte b) {// | operating set the byte to 1
    	return (byte) (b | bitmasks[i]);
    }

    /**
     * Set bit i in array of bytes.
     */
    public static void set(int i, byte bytes[]) {// find the wanted byte in the array and use previous method
    	bytes[bytes.length - i/bitmasks.length -
    	      1]=set(i%bitmasks.length, bytes[bytes.length-i/bitmasks.length-1]);
    }

    /**
     * Clear bit i in byte and return the new byte.
     */
    public static byte clear(int i, byte b) {// first check is we are clear or not, if not, then clear it use ^
    	if(isset(i, b)) return (byte) (bitmasks[i] ^ b);
    	return b;
    }

    /**
     * Clear bit i in array of bytes and return true if the bit was 1
     * before clearing, false otherwise.
     */
    public static boolean clear(int i, byte bytes[]) {//find the correct position and then use previous method to clear
    	int indexOfBytes = bytes.length - i/bitmasks.length - 1;
    	byte oldValue = bytes[indexOfBytes];
    	bytes[indexOfBytes] = clear(i%bitmasks.length, bytes[indexOfBytes]);
    	return isset(i%bitmasks.length, oldValue);

    }

    /**
     * Clear every bit in array of bytes.
     *
     * There is no clearAll for a single byte, you can just get a new
     * byte for that.
     */
    public static void clearAll(byte bytes[]) {
        for(int i = 0; i < bytes.length; ++i) {
            bytes[i] = 0;
        }
    }

    /**
     * Convert byte to a string of bits. Each bit is represented as
     * "0" if it is clear, "1" if it is set.
     */
    public static String toString(byte b) {
    	String result = "";//go through each bit and add the result to the string;
    	for (int i = 0; i < bitmasks.length; i++){
    		if(isset(i, b)) result = "1" + result;
    		else result = "0" + result;
    	}
    	return result;
    }

    /**
     * Convert array of bytes to string of bits (each byte converted
     * to a string by calling {@link #byteToString(byte b)}, every
     * byte separated by sep, every "every" bytes separated by lsep.
     */
    public static String toString(byte bytes[], String sep,
                                  String lsep, int every) {
        String s = "";
        for(int i = bytes.length * 8 - 1; i >= 0; --i) {
        	s += isset(i, bytes) ? "1" : "0";
        	if(i > 0)
                if(every > 0 && i % (8 * every) == 0)
                    s += lsep;
                else if(i % 8 == 0)
                    s += sep;
        }
        return s;
    }

    /**
     * Convert array of bytes to string of bits, each byte separated
     * by sep. See {@link #byteToString(byte bytes[], String sep)}.
     */
    public static String toString(byte bytes[], String sep) {
        return toString(bytes, sep, null, 0);
    }

    /**
     * Convert array of bytes to string of bits, each byte separated
     * by a comma, and every 8 bytes separated by a newline. See
     * {@link #byteToString(byte bytes[], String sep)}.
     */
    public static String toString(byte bytes[]) {
        return toString(bytes, ",", "\n", 8);
    }
}
