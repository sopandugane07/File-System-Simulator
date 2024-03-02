package Mini;

import java.util.Scanner;

class FileSystemNode  
{
    String name; 
    boolean isDirectory;
    FileSystemNode children[];  
    int childCount;

    public FileSystemNode(String name, boolean isDirectory, int maxChildren) 
    {
        this.name = name;
        this.isDirectory = isDirectory;
        this.children = new FileSystemNode[maxChildren];
        this.childCount = 0; 
    }
}

public class FileSystemSimulator 
{
    private FileSystemNode root;
    private FileSystemNode currentDirectory; 

    public FileSystemSimulator() 
    {
        root = new FileSystemNode("root", true, 10);
        currentDirectory = root;
    }

    public void createFile(String name) 
    {
        FileSystemNode newNode = new FileSystemNode(name, false, 0);
        currentDirectory.children[currentDirectory.childCount++] = newNode;
        System.out.println("File created: " + name);
    }

    public void createDirectory(String name) 
    {
        FileSystemNode newNode = new FileSystemNode(name, true, 10);
        currentDirectory.children[currentDirectory.childCount++] = newNode;
        System.out.println("Directory created: " + name);
    }

    public void deleteFile(String name) 
    {
        for (int i = 0; i < currentDirectory.childCount; i++) 
        {
            FileSystemNode child = currentDirectory.children[i];
            if (!child.isDirectory && child.name.equals(name)) 
            {
                // Shift elements to fill the gap
                for (int j = i; j < currentDirectory.childCount - 1; j++) 
                {
                    currentDirectory.children[j] = currentDirectory.children[j + 1];
                }
                currentDirectory.childCount--;
                System.out.println("File deleted: " + name);
                return;
            }
        }
        System.out.println("File not found: " + name);
    }

    public void deleteDirectory(String name) 
    {
        for (int i = 0; i < currentDirectory.childCount; i++) 
        {
            FileSystemNode child = currentDirectory.children[i];
            if (child.isDirectory && child.name.equals(name)) 
            {
                // Shift elements to fill the gap
                for (int j = i; j < currentDirectory.childCount - 1; j++) 
                {
                    currentDirectory.children[j] = currentDirectory.children[j + 1];
                }
                currentDirectory.childCount--;
                System.out.println("Directory deleted: " + name);
                return;
            }
        }
        System.out.println("Directory not found: " + name);
    }

    public void listFiles() 
    {
        System.out.println("Files in " + currentDirectory.name + ":");
        for (int i = 0; i < currentDirectory.childCount; i++) 
        {
            FileSystemNode child = currentDirectory.children[i];
            if (!child.isDirectory) 
            {
                System.out.println(child.name);
            }
        }
    }

    public void listDirectories() 
    {
        System.out.println("Directories in " + currentDirectory.name + ":");
        for (int i = 0; i < currentDirectory.childCount; i++) 
        {
            FileSystemNode child = currentDirectory.children[i];
            if (child.isDirectory) 
            {
                System.out.println(child.name);
            }
        }
    }

    public void changeDirectory(String name) 
    {
        for (int i = 0; i < currentDirectory.childCount; i++) 
        {
            FileSystemNode child = currentDirectory.children[i];
            if (child.isDirectory && child.name.equals(name)) 
            {
                currentDirectory = child;
                System.out.println("Changed to directory: " + name);
                return;
            }
        }
        System.out.println("Directory not found: " + name);
    }

    public static void main(String args[]) 
    {
        FileSystemSimulator fs = new FileSystemSimulator();
        Scanner sc = new Scanner(System.in);

        while (true) 
        {
        	System.out.println("*********Choose an option:***********");
            System.out.println("1. createFile <filename>");
            System.out.println("2. createDirectory <dirname>");
            System.out.println("3. deleteFile <filename>");
            System.out.println("4. deleteDirectory <dirname>");
            System.out.println("5. listFiles");
            System.out.println("6. listDirectories");
            System.out.println("7. changeDirectory <dirname>");
            System.out.println("8. exit");
        	System.out.println("------------------------------------");
            System.out.print("Choose Option : ");
            String input = sc.nextLine();

            if (input.equals("exit")) 
            {
                System.out.println("Exiting file system simulator. Goodbye!");
                break;
            }

            String parts[] = input.split(" ");
            //String action = parts[0];
            
            try 
            {
            	int Option = Integer.parseInt(parts[0]);
         
	            switch (Option) 
	            {
	            	
	                case 1:
	                	
	                    if (parts.length > 1) 
	                    {
	                    	System.out.println("File name: "+parts[1]);
	                        fs.createFile(parts[1]); 
	                    } 
	                    else 
	                    {
	                        System.out.println("Plz Enter FileName: createFile <filename>");
	                    }
	                    break;
	                case 2:
	                    if (parts.length > 1) 
	                    {
	                        fs.createDirectory(parts[1]);
	                    } 
	                    else 
	                    {
	                        System.out.println("Plz Enter Directory Name: createDirectory <dirname>");
	                    }
	                    break;
	                case 3:
	                    if (parts.length > 1) 
	                    {
	                        fs.deleteFile(parts[1]);
	                    } 
	                    else 
	                    {
	                        System.out.println("Plz Enter FileName: deleteFile <filename>");
	                    }
	                    break;
	                case 4:
	                    if (parts.length > 1) 
	                    {
	                        fs.deleteDirectory(parts[1]);
	                    } 
	                    else 
	                    {
	                        System.out.println("Plz Enter Directory Name: deleteDirectory <dirname>");
	                    }
	                    break;
	                case 5:
	                    fs.listFiles();
	                    break;
	                case 6:
	                    fs.listDirectories();
	                    break;
	                case 7:
	                    if (parts.length > 1)  
	                    {
	                        fs.changeDirectory(parts[1]);
	                    } 
	                    else 
	                    {
	                        System.out.println("Plz Enter Directory Name: changeDirectory <dirname>");
	                    }
	                    break;
	                case 8:
	                	{
	                		System.out.println("Exiting file system simulator. Goodbye!");
	                	}
	                	break;
	                default:
	                    System.out.println("Invalid command. Try again.");
	            }
            }
            catch(NumberFormatException e)
            {
            	System.out.println("Invalid input!. Please enter a number.");
            }
        }
        sc.close();
    }

}
