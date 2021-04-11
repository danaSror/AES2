public class BreakAes2 {

    private Message key;
    private Block msg, c, temp_res, k1, k2;

    //<editor-fold desc="Constractor">
    public BreakAes2(String plaintext_path,String ciphertext_path) {
        Message plaintext = new Message(plaintext_path);
        Message ciphertext = new Message(ciphertext_path);
        this.key = new Message();
        this.msg = plaintext.getBlock(0);
        this.c = ciphertext.getBlock(0);
        this.temp_res = this.c.xor(this.msg);
    }
    //</editor-fold>

    //<editor-fold desc="Operators">
    public Message encrypt(String msg_path, String output_path) {
        Message msg = new Message(msg_path);
        Message ciphertext = AES2.encrypt(msg, this.k1, this.k2);
        utils.writeMsgToFileInBytes(ciphertext,output_path);
        return ciphertext;
    }

    public Message decrypt(String ciphertext_path, String output_path) {
        Message ciphertext = new Message(ciphertext_path);
        Message msg = AES2.decrypt(ciphertext, this.k1, this.k2);
        utils.writeMsgToFileInBytes(msg,output_path);
        return msg;
    }

    public Message breakout(String output_path) {
        this.k1 = new Block();
        String hexadecimal = Integer.toHexString(1 & 0xff);
        while(!k1.isFull()){
            k1.insert(hexadecimal);
        }
        this.k2 = this.temp_res.xor(this.k1);
        this.key.insertBlock(this.k1);
        this.key.insertBlock(this.k2);
        utils.writeMsgToFileInBytes(this.key, output_path);
        return this.key;
    }
    //</editor-fold>


}
