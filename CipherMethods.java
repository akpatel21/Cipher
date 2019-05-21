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
			encryptOrDecrypt = encryptOrDecrypt.toLowerCase();
		}
		System.out.print("How many places should the alphabet be shifted? ");
		int shift = scan.nextInt();
		scan.nextLine();
		System.out.print("Enter a filename to " + encryptOrDecrypt + ": ");
		String inputFile = scan.nextLine();
		if(encryptOrDecrypt.equals("encrypt"))
		{
			PrintWriter outputFile = new PrintWriter(inputFile.substring(0, inputFile.length() - 4) + "_ENC.txt");
			String outputString = caesar_cipher(inputFile, true, shift);
			for(int i = 0; i < outputString.length(); i++)
			{
				outputFile.print(outputString.charAt(i));
				if(outputString.charAt(i) == '\n')
				{
					outputFile.println();
				}
			}
			outputFile.close();
			System.out.print("Result written to " + inputFile.substring(0, inputFile.length() - 4) + "_ENC.txt");
		}
		else
		{
			PrintWriter outputFile = new PrintWriter(inputFile.substring(0, inputFile.length() - 4) + "_DEC.txt");
			String outputString = caesar_cipher(inputFile, false, shift);
			for(int i = 0; i < outputString.length(); i++)
			{
				outputFile.print(outputString.charAt(i));
				if(outputString.charAt(i) == '\n')
				{
					outputFile.println();
				}
			}
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
			if(inputFile.hasNext())
			{
				inputString += '\n';
			}
		}
		String outputString = "";
		char c;
		int num = 0;
		if(encrypt)
		{
			for(int i = 0; i < inputString.length(); i++)
			{
				c = inputString.charAt(i);
				if(isLower(c))
				{
					num = c - 'a';
					if(num <= 3)
					{
						num += 26;
					}
					outputString += (char) ((num + shiftAmount) % 26 + 'a');
				}
				else if(isUpper(c))
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
				if(isLower(c))
				{
					num = c - 'a';
					if(num <= 3)
					{
						num += 26;
					}
					outputString += (char) ((num - shiftAmount) % 26 + 'a');
				}
				else if(isUpper(c))
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

	public static boolean isLower(char c)
	{
		return c >= 'a' && c <= 'z';
	}
	
	public static boolean isUpper(char c)
	{
		return c >= 'A' && c <= 'Z';
	}
}
