public class main {

    public static void main(String[] args)
    {
        Labyrinthe grille = new Labyrinthe(5, 5);
        System.out.println(grille.getLigne());
        System.out.println(grille.getColonne());
        grille.gridReset();
        grille.afficheGrid();

        System.out.println("\n\n\n");
        //grille.wallBreaker();
        //grille.afficheGrid();
        while(grille.cherche())
        {
            grille.wallBreaker2();
            grille.afficheGrid();
            System.out.println("\n\n\n");
            System.out.println("\n\n\n");
        }
        grille.afficheGrid();
        

    }    
}
