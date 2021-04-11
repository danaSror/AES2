public class Main {

    public static void main(String[] args) {

        if(args.length==7) {
            System.out.println("test");

////            if (args[0].equals("-e") || args[0].equals("-d")){
////                if(!args[1].equals("-k"))
////                    System.out.println("Error in second argument, Please enter -k");
////                if(!args[3].equals("-i"))
////                    System.out.println("Error in the fourth argument,Please enter -i");
////                if(!args[5].equals("-o"))
////                    System.out.println("Error in the sixth argument, Please enter -o");
////                else
////                    AES.encryptOrDecrypt(args);
//            }
//            else {
//                if(!args[1].equals("-m"))
//                    System.out.println("Error in second argument, Please enter -m");
//                if(!args[3].equals("-c"))
//                    System.out.println("Error in the fourth argument,Please enter -c");
//                if(!args[5].equals("-o"))
//                    System.out.println("Error in the sixth argument, Please enter -o");
//                else
//                    AES.breakingEncrypt(args);
//            }
        }
        else{
            System.out.println("Error! one or more of the arguments are missing");
        }

    }
}
