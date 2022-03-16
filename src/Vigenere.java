public class Vigenere {

    public static String vcriptare(String text, final String key)

    {

        String res = "";
        text = text.toUpperCase();

        for (int i = 0, j = 0; i < text.length(); i++)

        {

            char c = text.charAt(i);

            if (c < 'A' || c > 'Z')

                continue;

            res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');

            j = ++j % key.length();

        }

        return res;

    }

 

    public static String vdecriptare(String text, final String key)

    {

        String res = "";

        text = text.toUpperCase();

        for (int i = 0, j = 0; i < text.length(); i++)

        {

            char c = text.charAt(i);

            if (c < 'A' || c > 'Z')

                continue;

            res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');

            j = ++j % key.length();

        }

        return res;

    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        String key = "CIPHER";

        String message = "AWJHVVPWLTEJVMGPRXXQVLRVTMROMGGZ";

        System.out.println("Mesajul: " + message);

        System.out.println("Mesajul decriptat: " + vdecriptare(message, key));
	}

}
