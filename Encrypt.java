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
		}
		String outputString = "";
		char c;
		int num = 0;
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
				outputString += (char) ((num - 3) % 26 + 'a');
			}
			else if(c >= 'A' && c <= 'Z')
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
				System.out.println(c);
				outputString += c;
			}
		}
		inputFile.close();
		outputFile.print(outputString);
		outputFile.close();

	}

}
