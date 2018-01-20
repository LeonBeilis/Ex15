public class Main {

    public static void main(String args[]){
        //aabbbacddd
        /**
         * contains:
         * -    self letter
         * -    times repeated in row
         * -    next node
         */
//        String a = "aabbbacddd";

//        StringList s = new StringList("aabbbacddd");
        //aaaaaaaaaaaaab
        //aaaaaaaaaaaaab
        StringList s = new StringList("aabbbacddd");
        StringList s1 = new StringList("abbba");
        StringList s2 = new StringList(s1);
        System.out.println("copied string is: " + s2);
        System.exit(0);
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s2);
//        System.out.println("the concat of string s and string s1 is: " + s1.concat(s1));
//        System.out.println(b);
//        System.out.println(b.charAt(2));
    }

}
