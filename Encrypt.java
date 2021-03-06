import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class Encrypt
{

	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter the name of the file to encrypt: ");
		String fileName = scan.nextLine();
		Scanner inputFile = new Scanner(new File(fileName));
		PrintWriter outputFile = new PrintWriter(fileName.substring(0, fileName.length() - 4) + "_ENC.txt");
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
				outputString += (char) ((num - 3) % 26 + 'a');
			}
			else if(isUpper(c))
			{
				num = c - 'A';
				if(num <= 3)
				{
					num += 26;
				}
				outputString += (char) ((num - 3) % 26 + 'A');
			}
			else
			{
				outputString += c;
			}
		}
		inputFile.close();
		for(int i = 0; i < outputString.length(); i++)
		{
			outputFile.print(outputString.charAt(i));
			if(outputString.charAt(i) == '\n')
			{
				outputFile.println();
			}
		}
		outputFile.close();

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
