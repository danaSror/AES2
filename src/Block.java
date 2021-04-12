public class Block {

    private String [][] block_val;
    private final int ROW_SIZE = 4;
    private final int COL_SIZE = 4;
    private int row_offset;
    private int col_offset;


    //<editor-fold desc="Constructors">
    public Block(){
        this.row_offset = 0;
        this.col_offset = 0;
        this.block_val = new String[ROW_SIZE][COL_SIZE];
    }
    public Block(Block b) {
        this.row_offset = 0;
        this.col_offset = 0;
        this.block_val = new String[ROW_SIZE][COL_SIZE];
        for(int r = 0 ; r < ROW_SIZE ; r++){
            for(int c = 0 ; c < COL_SIZE ; c++){
                this.block_val[r][c] = b.getCellValue(r,c);
            }
        }
    }
    //</editor-fold>

    //<editor-fold desc="Operators">
    /**
     *
     * @return True if there is still place in the block
     */
    public boolean isFull(){
        return (this.row_offset >= ROW_SIZE) || (this.col_offset >= COL_SIZE);
    }

    public boolean insert(String val){
        if(this.isFull() == false){
            this.block_val[this.row_offset][this.col_offset] = val;
            this.updateBlockOffsets(); //increase the offsets after insert new value
            return true;
        }
        return false;
    }
    public byte[] blockToBytes() {
        byte[] result = new byte[ROW_SIZE * COL_SIZE];
        for(int r = 0; r < ROW_SIZE; r++) {
            for(int c = 0; c < COL_SIZE; c++) {
                String hex = this.getCellValue(r, c);
                byte b;
                if(hex.length()<2){
                    b = (byte)toDigit(hex.charAt(0));
                }
                else{
                    int digit1 = toDigit(hex.charAt(0));
                    int digit2 = toDigit(hex.charAt(1));
                    b = (byte)((digit1 << 4) + digit2);
                }
                result[r * ROW_SIZE + c] = b;
            }
        }
        return result;
    }

    private static int toDigit(char hexadecimal_Char) {
        int new_digit = Character.digit(hexadecimal_Char, 16);
        if(new_digit == -1) {}
        return new_digit;
    }

    private void updateBlockOffsets(){
        if(this.row_offset < ROW_SIZE){
            if(this.col_offset < COL_SIZE - 1){
                this.col_offset++;
            }
            else if(this.row_offset < ROW_SIZE){
                this.col_offset = 0;
                this.row_offset++;
            }
        }
    }

    /**
     *
     * @param other_block
     * @return new_block which is the result of the xor
     *         operation between the two blocks
     */
    public Block xor(Block other_block){
        Block new_block = new Block();
        for(int r = 0; r < ROW_SIZE; r++) {
            for(int c = 0; c < COL_SIZE; c++) {
                String this_val_hexadecimal = this.getCellValue(r,c);
                String other_val_hexadecimal = other_block.getCellValue(r,c);
                int num1 = Integer.parseInt(this_val_hexadecimal, 16);
                int num2 = Integer.parseInt(other_val_hexadecimal, 16);
                byte xor = (byte)(num1 ^ num2);
                new_block.insert(Integer.toHexString(xor & 0xff));
            }
        }
        return new_block;
    }

    /**
     * This function swape each cell in position (i,j)
     * with the cell in position (j,i)
     */
    public void swapeIndexex(){
        for(int r = 0; r < ROW_SIZE; r++) {
            for(int c = r + 1; c < COL_SIZE; c++) {
                String temp = this.block_val[r][c];
                this.block_val[r][c] = this.block_val[c][r];
                this.block_val[c][r] = temp;
            }
        }
    }

    /**
     *
     * @param other_block
     * @return true if the other block equals to this block, otherwise return false
     */
    public boolean isEquals(Block other_block) {
        for(int r = 0; r < ROW_SIZE; r++) {
            for(int c = 0; c < COL_SIZE; c++) {
                String this_val = this.getCellValue(r, c);
                String other_val = other_block.getCellValue(r, c);
                if(!(this_val.equals(other_val))) return false;
            }
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Getters">
    /**
     *
     * @param row and col of the specific cell in the block
     * @return the value of a specific cell in the block
     */
    private String getCellValue(int row,int col){
        return this.block_val[row][col];
    }
    //</editor-fold>


}//end class
