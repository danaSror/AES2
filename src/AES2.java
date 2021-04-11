public class AES2 {

    //<editor-fold desc="encrypt">
    public static Message encrypt(Message m, Block key1,Block key2) {
       return AES1.encrypt(AES1.encrypt(m, key1), key2);
    }

    public static Message encrypt(String input_msg_path, String key_path, String output_path){
        Message key = new Message(key_path);
        Message msg = new Message(input_msg_path);
        Block k1 = key.getBlock(0);
        Block k2 = key.getBlock(1);
        Message ciphertext = AES1.encrypt(AES1.encrypt(msg, k1),k2);
        utils.writeMsgToFileInBytes(ciphertext,output_path);
        return ciphertext;
    }
    //</editor-fold>

    //<editor-fold desc="decrypt">
    public static Message decrypt(Message ciphertext, Block key1,Block key2) {
       return AES1.decrypt(AES1.decrypt(ciphertext,key2),key1);
    }

    public static Message decrypt(String inut_cipher_path, String key_path, String output_path) {
        Message key = new Message(key_path);
        Message cipher = new Message(inut_cipher_path);
        Block k1 = key.getBlock(0);
        Block k2 = key.getBlock(1);
        Message msg = AES1.decrypt(AES1.decrypt(cipher, k2),k1);
        utils.writeMsgToFileInBytes(msg,output_path);
        return msg;
    }
    //</editor-fold>
    
}
