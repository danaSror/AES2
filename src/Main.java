import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if(args.length == 7) {
            String key_path = args[2];
            String input_path = args[4];
            String output_path = args[6];
            String instruction = args[0].toLowerCase();
            switch(instruction) {
                case "-e":
                    AES2.encrypt(input_path, key_path, output_path);
                    System.out.println("Message encripted to: '"+output_path+"'");
                    break;
                case "-d":
                    AES2.decrypt(input_path, key_path, output_path);
                    System.out.println("Ciphertext dcripted to: '"+output_path+"'");
                    break;
                case "-b":
                    String msg_path = args[2];
                    String cipher_path = args[4];
                    BreakAes2 attack = new BreakAes2(msg_path, cipher_path);
                    attack.breakout(output_path);
                    System.out.println("AES2 was broken, you can find the keys on: '"+output_path+"'");
                    break;
                default:

            }
        }
        else{
            System.out.println("Error! one or more of the arguments are missing");
        }
    }


}
