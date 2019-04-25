import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class CipherMethods 
{
	Scanner scan = new Scanner(System.in);
	public static void main(String[] args) 
	{
		System.out.print("Would you like to encrypt or decrypt a file? ");
		String encryptOrDecrypt = scan.nextLine();
		encryptOrDecrypt = encryptOrDecrypt.toLowerCase();
		if(encryptOrDecrypt.equals("encrypt"))
		{
			
		}
		System.out.print("\nHow many places should the alphabet be shifted? ");
		System.out.print("Enter a filename to " + + ": ");
	}
	
	public static String caesar_cipher(String fileName, boolean encrypt, int shiftAmount) throws IOException
	{
		Scanner inputFile = new Scanner(new File(fileName));
		String inputString = "";
		while(inputFile.hasNext())
		{
			inputString += inputFile.nextLine();
		}
		String outputString = "";
		char c;
		int num = 0;
		if(encrypt)
		{
			PrintWriter outputFile = new PrintWriter(fileName.substring(0, fileName.length() - 4) + "_ENC.txt");
			for(int i = 0; i < inputString.length(); i++)
			{
				c = inputString.charAt(i);
				if(c >= 'a' && c <= 'z')
				{
					num = c - 'a';
					if(num <= 3)
					{
						num += 26;
					}
					outputString += (char) ((num + shiftAmount) % 26 + 'a');
				}
				else if(c >= 'A' && c <= 'Z')
				{
					num = c - 'A';
					if(num <= 3)
					{
						num += 26;
					}
					outputString += (char) ((num + shiftAmount) % 26 + 'A');
				}
				else
				{
					outputString += c;
				}
			}
			outputFile.print(outputString);
			outputFile.close();
		}
		else if(!encrypt)
		{
			PrintWriter outputFile = new PrintWriter(fileName.substring(0, fileName.length() - 4) + "_DEC.txt");
			for(int i = 0; i < inputString.length(); i++)
			{
				c = inputString.charAt(i);
				if(c >= 'a' && c <= 'z')
				{
					num = c - 'a';
					if(num <= 3)
					{
						num += 26;
					}
					outputString += (char) ((num + shiftAmount) % 26 + 'a');
				}
				else if(c >= 'A' && c <= 'Z')
				{
					num = c - 'A';
					if(num <= 3)
					{
						num += 26;
					}
					outputString += (char) ((num + shiftAmount) % 26 + 'A');
				}
				else
				{
					outputString += c;
				}
			}
			outputFile.print(outputString);
			outputFile.close();
		}
		inputFile.close();
		return outputString;
	}
}
