package example;

import org.bouncycastle.crypto.generators.SCrypt;

public class Main {
    private static final String SALT = "abc123";

    // DifficultyFactor
    // These should be powers of 2
    private static final int cpu = 8;
    private static final int memory = 8;
    private static final int parallelism = 8;
    private static final int outputLength = 32;
    /**
     * Main unit test method.
     * @param args Command line arguments.
     *
     */
    public static void main(String args[]) {
      String plainText = "Hello World!";
      String stored = hashPsw(plainText);
      boolean isMatch = checkPsw(plainText, stored);

      System.out.println("Original: " + plainText);
      System.out.println("Hash: " + stored);
      System.out.println("Verified: " + isMatch);
    }
    private static String hashPsw(String plainText) {
        byte[] hash = SCrypt.generate(plainText.getBytes(), SALT.getBytes(), cpu, memory, parallelism, outputLength);
        String stored = new String(hash);
        return stored;
    }
    private static boolean checkPsw(String plainText, String hashedStr) {
        byte[] hash = SCrypt.generate(plainText.getBytes(), SALT.getBytes(), cpu, memory, parallelism, outputLength);
        String stored = new String(hash);
        return stored.equals(hashedStr);
    }
}
