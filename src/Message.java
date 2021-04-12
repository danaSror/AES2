import java.util.ArrayList;
import java.util.List;

public class Message {

    private List<Block> msg_blocks;

    //<editor-fold desc="Constructors">
    public Message() {
        this.msg_blocks = new ArrayList<>();
    }

    public Message(Message m) {
        this.msg_blocks = new ArrayList<>();
        for(int b = 0; b < m.msg_blocks.size(); b++){   //TODO cheak this loop
            Block new_block = new Block(m.msg_blocks.get(b));
            this.msg_blocks.add(new_block);
        }
    }

    public Message(String msg_path){
        this.msg_blocks = new ArrayList<>();
        utils.readMsgFromFileInBytes(this, msg_path);
    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    public List<Block> getMsg_blocks() {
        return msg_blocks;
    }

    /**
     *
     * @param index
     * @return Specific block from the message blocks list
     */
    public Block getBlock(int index) {
        if(index >= this.msg_blocks.size()) return null;
        return this.msg_blocks.get(index);
    }
    //</editor-fold>

    //<editor-fold desc="Operators">
    public Message xor(Block other_block) {
        Message result_msg = new Message();
        for(Block block:this.msg_blocks) {
            result_msg.insertBlock(block.xor(other_block));
        }
        return result_msg;
    }

    public void swapIndex() {
        for(Block block : this.msg_blocks)
            block.swapeIndexex();
    }

    public void insertBlock(Block block) {
        this.msg_blocks.add(block);

    }

    public boolean isEquals(Message other) {
        for(int i=0; i < this.msg_blocks.size(); i++) {
            if(!(this.getBlock(i).isEquals(other.getBlock(i))))
                return false;
        }
        return true;
    }
    //</editor-fold>
}
