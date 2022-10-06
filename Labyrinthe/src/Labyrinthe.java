import java.util.Random;

public class Labyrinthe 
{
    int[] grid;
    int colonne;
    int ligne;
    int taille;

    Labyrinthe(int colonne, int ligne)
    {
        this.grid = new int[(colonne*2+1)*(ligne*2+1)];
        this.colonne = colonne*2+1;
        this.ligne = ligne*2+1;
        this.taille = grid.length;
    }
    
    /* ********************************************************************
     *  Getter
     *********************************************************************/

    int[] getGrid()
    {return grid;}

    int getColonne()
    {return colonne;}

    int getLigne()
    {return ligne;}

    int getTaille()
    {return taille;}

    /**********************************************************************
     * Setter
     *********************************************************************/

    void setbox(int ligne, int colonne, int index, int value)
    {
        if(index == 0)
        {this.grid[ligne + colonne*getLigne()] = value;}
        else
        {this.grid[index] = value;}    
    }

    void setLigne(int value)
    {this.ligne = value;}

    void setColonne(int value)
    {this.colonne = value;}

    void afficheGrid()
    {
        for(int x = 0; x < ligne; x++)
        {
            for(int y = 0; y < colonne; y++)
            {
                System.out.print("[");
                if(grid[x * colonne + y] !=-1)// pas de mine
                {
                    System.out.print("O");
                    //System.out.print(grid[x * ligne + y]);
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
    
    void gridReset()
    {
        System.out.println("Il y a "+ (taille)+ " case dans le tableau");
        int i = 1;
        for(int j=0; j < ligne; j++)
        {
            for(int k=0; k < colonne; k++)
            {
                if((k+1)%2 == 1)
                {   
                    setbox(k, j, 0, -1);
                }
                else if((j+1)%2 == 1)
                {
                    setbox(k, j, 0,  -1);
                }
                else
                {
                    setbox(k, j, 0, i);
                    i++;
                }
            }
        }
    }

    void wallBreaker()
    {
        /*
         * on choisi une case qui n'est pas un mur on verifie ensuite si les cases a cotes sont des murs ou non et on casse se mur si la valeur deriere est different de celle
         * de la case de depart.
         * 
         */
        Random r = new Random();
        int index;
        int[] cardinal; //sur les cotés à 1 de distance.
        int[] cardinal2; //sur les cotés à 2 de distance.
        int value;

        int case1; //case de base
        int wall;
        int case2; //case deriere le mur casser

        index  = r.nextInt(taille);
        //while(grid[index] == -1)  //choisi un indice dans le tableau different d'un mur.
        //{
            
        //}

        //index = 12;

        cardinal = new int[] {index--, index++, index - ligne, index +ligne};
        cardinal2 = new int[] {index +2, index-2, index -2*ligne, index + 2*ligne};

        value = 0;

        case1 = 0;
        case2 = 0;
        wall = 0;
        
        
        while(case2 == case1 || wall != -1 || case2 == -1 || case1 == -1)//regarde si la valeur derière le mur et la meme et si le mur choisi est bien un mur.
        {
            value = r.nextInt(4);
            index = r.nextInt(taille); 
            try
            {
                wall = grid[cardinal[value]];
                case1 = grid[index];
                case2 = grid[cardinal2[value]];
            }catch(ArrayIndexOutOfBoundsException e){
                wall = 0;
                case1 = 0;
                case2 = 0;
                System.out.println("je suis une erreur");
            }
        }
        System.out.println(case1);
        System.out.println(case2);
        System.out.println(value);

        int[] array = new int[2];
        if(case1 < case2)
        {
            grid[cardinal[value]] = case1;
            array[0] = case1;
            array[1] = case2;
        }
        else
        {
            grid[cardinal[value]] = case2;
            array[0] = case2;
            array[1] = case1;
        }

        for(int i =0; i<taille; i++)
        {
            boxRemplissage(i, array[1], array[0]);
        }

      
        
    }

    void wallBreaker2()
    {
       Random r = new Random();
       int[] card;
       int[] card2;

       boolean exit;

       int count;
       int index;
       int value;
       int case1;
       int case2;
       int wall;

       index = -1;
       while(index == -1)
       {
        index = r.nextInt(taille);
       }

       card = new int[] {index--, index++, index - ligne, index + ligne};
       card2 = new int[] {index - 2, index + 2, index - 2*ligne, index + 2*ligne};

       value = 0;
       count = 0;
       exit = false;
       while(grid[value] == grid[card2[value]] || grid[card2[value]] == -1 || grid[card[value]] != -1 && !exit)
       {
        if(count > 5){exit=true;}
        try
        {
            value = r.nextInt(4); 
        }catch(ArrayIndexOutOfBoundsException e){System.out.println("Erreur! ");}
       }

       case1 = grid[value];
       case2 = grid[card2[value]];
       wall = grid[card[value]];

       if(case1<case2)
       {
        for(int i=0; i<taille; i++){boxRemplissage(i, case2, case1);
                                    grid[card[value]] = case1;}
       }
       else
       {
        for(int i=0; i<taille; i++){boxRemplissage(i, case1, case2);
                                    grid[card[value]] = case2;}
       }
       count++;
    }


    boolean cherche()
    {
        int index = 0;
        boolean trouve = false;
        while(index<taille && !trouve)
        {
            if(grid[index] != -1 && grid[index] != 1 )
            {
                trouve = true;
            }
            index++;
        }

        if(trouve)
        {
            System.out.println("trouvé");
        }
        else
        {
            System.out.println("pas trouvé");
        }
        return trouve;
    }

    boolean estUnBord(int index)
    {
        boolean bord = false;
        if(index < ligne || index > taille - ligne || (index+2)%ligne == 1 || index%ligne == 0)
        {
            bord = true;
        }
        return bord;
    }

    boolean estUnMur(int index)
    {
        boolean mur = false;
        if(grid[index] == -1)
        {
            mur = true;
        }
        return mur;
    }

    boolean estUnCouloir(int index)
    {
        boolean couloir = true;
        if(grid[index] == -1)
        {
            couloir = false;
        }
        return couloir;
    }

    void testBord()
    {
        for(int i=0; i<taille; i++)
        {
            if(estUnBord(i))
            {
                setbox(0, 0, i, 0);
            }
        }
    }

    void boxRemplissage(int index, int boxcible, int boxrep)
    {
        if(grid[index] == boxcible)
        {
            setbox(0, 0, index, boxrep);
        }
    }



}
