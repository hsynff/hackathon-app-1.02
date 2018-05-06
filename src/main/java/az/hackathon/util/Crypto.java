package az.hackathon.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Random;

public class Crypto {

    public static String pwdToHash(String input) throws Exception{
        String inputToSha = pwdToSha(input);
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inputToSha.getBytes());

        byte[] result = md.digest();

        return DatatypeConverter.printBase64Binary(result);
    }

    private static String pwdToSha(String input) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input.getBytes());

        byte[] result = md.digest();

        return DatatypeConverter.printBase64Binary(result);
    }

    public static String generateTrackingNumber(){
        Random random = new Random();

        int part1 = random.nextInt(900)+100;
        int part2 = random.nextInt(900)+100;
        int part3 = random.nextInt(900)+100;

        return part1+"-"+part2+"-"+part3;
    }
}
