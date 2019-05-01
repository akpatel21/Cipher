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
			inputString += "\n";
		}
		System.out.print(inputString);
		char p = inputString.charAt(7);
		if(p=='\n')
			System.out.print("\\n");
		String outputString = "";
		char c;
		int num = 0;
		System.out.print(inputString.indexOf('\n'));
		for(int i = 0; i < inputString.length(); i++)
		{
			c = inputString.charAt(i);
			if(c=='\n')
				outputString += "\n";
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

}
