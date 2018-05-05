package az.hackathon.util;

public class Validator {
    public static boolean validate(Object... args){

        for (Object obj : args){
            if (obj==null){
                return false;
            }

            String value = obj.toString();
            if (value.trim().length()==0){
                return false;
            }


        }

        return true;

    }
}
