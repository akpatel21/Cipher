import java.util.Scanner;

public class Encrypt 
{

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		EncryptMethods em = new EncryptMethods();
		System.out.print("Please enter a string to encrypt: ");
		String str = scan.nextLine();
		String str_ENC = "";
		char c = ' ';
		int num = 0;
		for(int i = 0; i < str.length(); i++)
		{
			c = str.charAt(i);
			if(em.isLower(str.charAt(i)))
			{
				num = c - 'a';
				str_ENC += (char) ((num + 3) % 26 + 'a');
			}
			else if(em.isUpper(str.charAt(i)))
			{
				num = c - 'A';
				str_ENC += (char) ((num + 3) % 26 + 'A');
			}
			else
			{
				str_ENC += c;
			}
		}
		System.out.print(str_ENC);

	}

}
