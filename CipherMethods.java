import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class CipherMethods 
{
	
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Would you like to encrypt or decrypt a file? ");
		String encryptOrDecrypt = scan.nextLine();
		encryptOrDecrypt = encryptOrDecrypt.toLowerCase();
		while(!encryptOrDecrypt.equals("encrypt") && !encryptOrDecrypt.equals("decrypt"))
		{
			System.out.print("Please enter \"encrypt\" or \"decrypt\": ");
			encryptOrDecrypt = scan.nextLine();
		}
		System.out.print("How many places should the alphabet be shifted? ");
		int shift = scan.nextInt();
		scan.nextLine();
		System.out.print("Enter a filename to " + encryptOrDecrypt + ": ");
		String inputFile = scan.nextLine();
		if(encryptOrDecrypt.equals("encrypt"))
		{
			PrintWriter outputFile = new PrintWriter(inputFile.substring(0, inputFile.length() - 4) + "_ENC.txt");
			outputFile.print(caesar_cipher(inputFile, true, shift));
			outputFile.close();
			System.out.print("Result written to " + inputFile.substring(0, inputFile.length() - 4) + "_ENC.txt");
		}
		else
		{
			PrintWriter outputFile = new PrintWriter(inputFile.substring(0, inputFile.length() - 4) + "_DEC.txt");
			outputFile.print(caesar_cipher(inputFile, false, shift));
			outputFile.close();
			System.out.print("Result written to " + inputFile.substring(0, inputFile.length() - 4) + "_DEC.txt");
		}
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
		}
		else if(!encrypt)
		{
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
					outputString += (char) ((num - shiftAmount) % 26 + 'a');
				}
				else if(c >= 'A' && c <= 'Z')
				{
					num = c - 'A';
					if(num <= 3)
					{
						num += 26;
					}
					outputString += (char) ((num - shiftAmount) % 26 + 'A');
				}
				else
				{
					outputString += c;
				}
			}
		}
		inputFile.close();
		return outputString;
	}
}
