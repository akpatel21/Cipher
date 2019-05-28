import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class PermutationCipher 
{
	
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Would you like to encrypt, decrypt, or crack a file? ");
		String encryptOrDecrypt = scan.nextLine();
		encryptOrDecrypt = encryptOrDecrypt.toLowerCase();
		while(!encryptOrDecrypt.equals("encrypt") && !encryptOrDecrypt.equals("decrypt") && !encryptOrDecrypt.equals("crack"))
		{
			System.out.print("Please enter \"encrypt\", \"decrypt\", or \"crack\": ");
			encryptOrDecrypt = scan.nextLine();
			encryptOrDecrypt = encryptOrDecrypt.toLowerCase();
		}
		if(!encryptOrDecrypt.equals("crack"))
		{
			System.out.print("What algorithm would you like to use? (\'c\' for caesar or \'p\' for permutation) ");
			String caesarOrPurmutation = scan.nextLine();
			caesarOrPurmutation = caesarOrPurmutation.toLowerCase();
			while(!caesarOrPurmutation.equals("c") && !caesarOrPurmutation.equals("p"))
			{
				System.out.print("Please enter \'c\' or \'p\': ");
				caesarOrPurmutation = scan.nextLine();
				caesarOrPurmutation = caesarOrPurmutation.toLowerCase();
			}
			if(caesarOrPurmutation.equals("c"))
			{
				if(encryptOrDecrypt.equals("encrypt"))
				{
					System.out.print("How many places should the alphabet be shifted? ");
					int shift = scan.nextInt();
					scan.nextLine();
					System.out.print("Enter a filename to encrypt: ");
					String inputFile = scan.nextLine();
					PrintWriter outputFile = new PrintWriter(inputFile.substring(0, inputFile.length() - 4) + "_ENC.txt");
					String outputString = caesar_cipher(inputFile, true, shift);
					for(int i = 0; i < outputString.length(); i++)
					{
						outputFile.print(outputString.charAt(i));
					}
					outputFile.close();		
					System.out.print("Result written to " + inputFile.substring(0, inputFile.length() - 4) + "_ENC.txt");
				}
				else
				{
					System.out.print("How many places should the alphabet be shifted? ");
					int shift = scan.nextInt();
					scan.nextLine();
					System.out.print("Enter a filename to decrypt: ");
					String inputFile = scan.nextLine();
					PrintWriter outputFile = new PrintWriter(inputFile.substring(0, inputFile.length() - 4) + "_DEC.txt");
					String outputString = caesar_cipher(inputFile, false, shift);
					for(int i = 0; i < outputString.length(); i++)
					{
						outputFile.print(outputString.charAt(i));
					}
					outputFile.close();
					System.out.print("Result written to " + inputFile.substring(0, inputFile.length() - 4) + "_DEC.txt");
				}
			}
			else
			{
				if(encryptOrDecrypt.equals("encrypt"))
				{
					Random rand = new Random();
					int random;
					char exchange;
					char[] perm = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
					for(int i = 0; i < 26; i++)
					{
						random = rand.nextInt(25) + 1;
						exchange = perm[i];					
						perm[i] = perm[random];
						perm[random] = exchange;
					}
					System.out.println("The following permuted alphabet will be used for encryption:");
					for(int i = 0; i < 26; i++)
					{
						System.out.print(perm[i]);
					}
					System.out.print("\nEnter a filename to encrypt: ");
					String inputFile = scan.nextLine();
					PrintWriter outputFile = new PrintWriter(inputFile.substring(0, inputFile.length() - 4) + "_ENC.txt");
					String outputString = perm_cipher(inputFile, true, perm);
					for(int i = 0; i < outputString.length(); i++)
					{
						outputFile.print(outputString.charAt(i));
					}
					outputFile.close();
					System.out.print("Result written to " + inputFile.substring(0, inputFile.length() - 4) + "_ENC.txt");
				}
				else
				{
					System.out.println("Enter a permuted alphabet: ");
					String inputAlphabet = scan.nextLine();
					char[] perm = new char[26];
					for(int i = 0; i < 26; i++)
					{
						perm[i] = inputAlphabet.charAt(i);
					}
					System.out.print("Enter a filename to decrypt: ");
					String inputFile = scan.nextLine();
					PrintWriter outputFile = new PrintWriter(inputFile.substring(0, inputFile.length() - 4) + "_DEC.txt");
					String outputString = perm_cipher(inputFile, false, perm);
					for(int i = 0; i < outputString.length(); i++)
					{
						outputFile.print(outputString.charAt(i));
					}
					outputFile.close();
					System.out.print("Result written to " + inputFile.substring(0, inputFile.length() - 4) + "_DEC.txt");
				}
			}
		}
		else
		{
			System.out.print("Enter a filename to crack: ");
			String inputFile = scan.nextLine();
			String outputString;
			String correct;
			if(caesar_cipher(inputFile, false, 0).length() >= 100)
			{
				for(int i = 1; i < 26; i++)
				{
					System.out.println("---\n" + caesar_cipher(inputFile, false, i).substring(0, 100));
					System.out.print("---\nDoes this look right? ");
					correct = scan.nextLine();
					correct = correct.toLowerCase();
					while(!correct.equals("yes") && !correct.equals("no"))
					{
						System.out.print("\nPlease answer \"yes\" or \"no\": ");
						correct = scan.nextLine();
						correct = correct.toLowerCase();
					}
					if(correct.equals("yes"))
					{
						PrintWriter outputFile = new PrintWriter(inputFile.substring(0, inputFile.length() - 4) + "_DEC.txt");
						outputString = caesar_cipher(inputFile, false, i);
						for(int j = 0; j < outputString.length(); j++)
						{
							outputFile.print(outputString.charAt(j));
						}
						outputFile.close();
						System.out.print("Result written to " + inputFile.substring(0, inputFile.length() - 4) + "_DEC.txt");
						break;
					}
					System.out.println();
				}
			}
			else
			{
				for(int i = 1; i < 26; i++)
				{
					System.out.println("---\n" + caesar_cipher(inputFile, false, i).substring(0, inputFile.length() - 1));
					System.out.print("---\nDoes this look right? ");
					correct = scan.nextLine();
					correct = correct.toLowerCase();
					while(!correct.equals("yes") && !correct.equals("no"))
					{
						System.out.print("\nPlease answer \"yes\" or \"no\": ");
						correct = scan.nextLine();
						correct = correct.toLowerCase();
					}
					if(correct.equals("yes"))
					{
						PrintWriter outputFile = new PrintWriter(inputFile.substring(0, inputFile.length() - 4) + "_DEC.txt");
						outputString = caesar_cipher(inputFile, false, i);
						for(int j = 0; j < outputString.length(); j++)
						{
							outputFile.print(outputString.charAt(j));
						}
						outputFile.close();
						System.out.print("Result written to " + inputFile.substring(0, inputFile.length() - 4) + "_DEC.txt");
						break;
					}
					System.out.println();
				
				}
			}
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
					while((num - shiftAmount) < 0)
					{
						num += 26;
					}
					outputString += (char) ((num + shiftAmount) % 26 + 'a');
				}
				else if(isUpper(c))
				{
					num = c - 'A';
					while((num - shiftAmount) < 0)
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
		else
		{
			for(int i = 0; i < inputString.length(); i++)
			{
				c = inputString.charAt(i);
				if(isLower(c))
				{
					num = c - 'a';
					while((num - shiftAmount) < 0)
					{
						num += 26;
					}
					outputString += (char) ((num - shiftAmount) % 26 + 'a');
				}
				else if(isUpper(c))
				{
					num = c - 'A';
					while((num - shiftAmount) < 0)
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
	
	public static String perm_cipher(String fileName, boolean encrypt, char[] perm) throws IOException
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
		String upperCase = "";
		char c;
		if(encrypt)
		{
			for(int i = 0; i < inputString.length(); i++)
			{
				c = inputString.charAt(i);
				if(isLower(c))
				{
					outputString += perm[c - 'a'];
				}
				else if(isUpper(c))
				{
					upperCase += perm[c - 'A'];
					upperCase = upperCase.toUpperCase();
					outputString += upperCase;
					upperCase = "";
				}
				else
				{
					outputString += c;
				}
			}
		}
		else
		{
			char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
			String permAlphabet = "";
			for(int i = 0; i < 26; i++)
			{
				permAlphabet += perm[i];
			}
			for(int i = 0; i < inputString.length(); i++)
			{
				c = inputString.charAt(i);
				if(isLower(c))
				{
					outputString += alphabet[permAlphabet.indexOf(c)];
				}
				else if(isUpper(c))
				{
					upperCase += alphabet[permAlphabet.indexOf(c)];
					upperCase = upperCase.toUpperCase();
					outputString += upperCase;
					upperCase = "";
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
