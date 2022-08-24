package de.lioker.passwordgenerator.generator;

import java.util.Random;

public class Generator {

    public static final String upperCaseLetters = "AABBCCDDEEFFGGHHIIJJKKLLMMNNOOPPQQRRSSTTUUVVWWXXYYZZ";
    public static final String lowerCaseLetters = "aabbccddeeffgghhiijjkkllmmnnooppqqrrssttuuvvwwxxyyzz";
    public static final String numbers = "00112233445566778899";
    public static final String specialCharacters = "!§$%&/(~)=?`´*\\+#'-\"_.:,;<>|^°!§$%&/(~)=?`´*\\+#'-\"_.:,;<>|^°";


    public String generatePassword(int length) {
        String tempPassword = "";
        tempPassword = createRandomPasswordString(length);

        while (!checkPassword(tempPassword)) {
            tempPassword = createRandomPasswordString(length);
        }

        return tempPassword;
    }

    public String createRandomPasswordString(int length) {
        String generatedPasswordString = "";

        generatedPasswordString = upperCaseLetters + lowerCaseLetters + numbers + specialCharacters;
        generatedPasswordString = shuffle(generatedPasswordString);
        generatedPasswordString = generatedPasswordString.substring(0, length);

        return generatedPasswordString;
    }

    public boolean checkPassword(String password) {
        char ch;
        boolean numberFlag = false;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean specialCharacterFlag = false;

        for(int i=0;i < password.length();i++) {
            ch = password.charAt(i);
            if(Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            } else if (specialCharacters.contains(String.valueOf(ch))) {
                specialCharacterFlag = true;
            }

            if(numberFlag && capitalFlag && lowerCaseFlag && specialCharacterFlag){
                return true;
            }
        }
        return false;
    }

    public boolean[] checkPasswordWithExtendedReturnData(String password){
        char ch;
        boolean numberFlag = false;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean specialCharacterFlag = false;

        for(int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if(Character.isDigit(ch)) {
                numberFlag = true;
            }
        }

        for(int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if(Character.isUpperCase(ch)) {
                capitalFlag = true;
            }
        }

        for(int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if(Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
        }

        for(int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if(specialCharacters.contains(Character.toString(ch))) {
                specialCharacterFlag = true;
            }
        }

        return new boolean[]{numberFlag, capitalFlag, lowerCaseFlag, specialCharacterFlag};
    }


    public String shuffle(String string) {
        Random rand = new Random();
        char[] ch = new char[string.length()];

        for (int i = 0; i < string.length(); i++) {
            ch[i] = string.charAt(i);
        }

        for (int i = 0; i < 100; i++) {
            int n1 = rand.nextInt(string.length());
            int n2 = rand.nextInt(string.length());

            char tempChar1 = ch[n1];
            char tempChar2 = ch[n2];

            ch[n1] = tempChar2;
            ch[n2] = tempChar1;
        }

        return new String(ch);
    }
}
