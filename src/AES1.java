public class AES1 {

    public static Message encrypt(Message m, Block key) {
        m.swapIndex();
        return m.xor(key);
    }

    public static Message decrypt(Message c, Block key) {
        Message ciphertext = c.xor(key);
        ciphertext.swapIndex();
        return ciphertext;
    }
}
