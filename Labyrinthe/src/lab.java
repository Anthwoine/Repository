import java.util.ArrayList;

public class lab {

    public static void main(String[] args)
    {
        final int case_height;
        final int case_width;
        final int grid_heigth;
        final int grid_width;
        int[] grid;

        case_height = 5;
        case_width = 5;
        grid_heigth = 2*case_height+1;
        grid_width = 2*case_width+1;

        grid = grid_generator(grid_heigth, grid_width);
        finder(grid);
        affiche_tab(grid_heigth, grid_width, grid);
    }

    private static int[] grid_generator(int grid_heigth, int grid_width)
    {
        System.out.println("Il y a "+ grid_heigth*grid_width +" case dans le tableau");

        int[] grid = new int[grid_heigth*grid_width];
        int i = 1;

        for(int ligne=0; ligne < grid_width; ligne++)
        {
            for(int colonne=0; colonne < grid_heigth; colonne++)
            {
                if((colonne+1)%2 == 1)
                { 
                    grid[colonne + ligne*grid_heigth] = -1;
                }
                else if((ligne+1)%2 == 1)
                {
                    grid[colonne + ligne*grid_heigth] = -1;
                }
                else
                {
                    grid[colonne + ligne*grid_heigth] = i;
                    i++;
                }
            }
        }
        return grid;
    }

    private static int[] lab_generator(int[] grid)
    {
        int[] occurence;
        //while(occurence.length < 2)
        return grid;
    }

    private static ArrayList<Integer> finder(int[] grid)
    {
        ArrayList<Integer> occurence;
        occurence = new ArrayList<Integer>();
        
        int j;
        boolean fond;
        int size = 0;
        for(int i=0; i<grid.length; i++)
        {
            fond = false;
            j=0;
            System.out.println("size= "+ size);
            while(j<size && j!=-7)
            {
                System.out.println(j);
                
                if(occurence.get(j) == grid[i]);
                {
                    fond = true;
                }
                j++;
            }            
            if(fond == false)
            {
                occurence.add(grid[i]);
            }
            size++;
            System.out.println("stop");
        }

        //System.out.println("\nLes valeur sont: ");
        for(int i=0; i<occurence.size(); i++)
        {
            //System.out.println(occurence.get(i));
        }
        return occurence;
    }

    private static void affiche_tab(int GRID_HEIGHT, int GRID_WIDTH, int[] grid)
    {
        for(int x = 0; x < GRID_HEIGHT; x++)
        {
            for(int y = 0; y < GRID_WIDTH; y++)
            {
                System.out.print("[");
                if(grid[x * GRID_WIDTH + y] !=-1)// pas de mine
                {
                    System.out.print("O");
                    //System.out.print(grid[x * GRID_WIDTH + y]);
                }
                else
                // mine
                {
                    System.out.print("X");
                }
                System.out.print("]");
            }
            System.out.print("\n");
        }
    }
    
}
