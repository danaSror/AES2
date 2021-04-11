import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class utils {

    public static void writeMsgToFileInBytes(Message msg,String output_path) {
        try(FileOutputStream out_file = new FileOutputStream(output_path)){
            out_file.write("".getBytes());
            for(Block b : msg.getMsg_blocks()){
                byte[] data = b.blockToBytes();
                out_file.write(data);
            }
            out_file.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void readMsgFromFileInBytes(Message msg,String msg_path){
        File file = new File(msg_path);
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            Block block = new Block();
            if (bytes != null) {
                msg.insertBlock(block);
                for (byte b : bytes) {
                    if(block.isFull()) {
                        block = new Block();
                        msg.insertBlock(block);
                    }
                    String hexString = Integer.toHexString(b & 0xff);
                    block.insert(hexString);
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
