import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


    /**
     * @Author   Ali jafaripour
     * @Data     1401/12/05
     */
    public class Game {

        static Scanner reader = new Scanner(System.in);


        public static void main(String[] args)
        {
            welcomMassage();

            restart();
        }
        public static void restart()
        {
            // ------ Defalt Setting --------

            int board  = 4;
            int locknum = 3;
            String playername_one =  "\033[95mPlayer(1)\033[97m";
            String playername_two = "\033[96mPlayer(2)\033[97m";

            menuBack(board,locknum,playername_one,playername_two);

        }





//----------------------------------------------------------------------------------------------------------------------------


        public static void menuBack (int board,int locknum,String playername_one,String playername_two)
        {
            menuFront();

            int num = reader.nextInt();

            clear();

            switch (num)
            {
                // ------------------- multiplay -------------------
                case 1:

                    List lock = new ArrayList<Integer>();
                    String[][] matrix = new String[board][board];

                    lockNum(lock,locknum,board);

                    fillMatirx(matrix,lock,locknum);

                    multilPlay(matrix,playername_one,playername_two);

                    break;

                // ------------------- Singelplay -------------------
                case 2:

                    List lock2 = new ArrayList<Integer>();
                    String[][] matrix2 = new String[board][board];

                    lockNum(lock2,locknum,board);

                    fillMatirx(matrix2,lock2,locknum);

                    int computerRand = (board * board); //تعداد اعدادی که کام‍یوتر به صپرت رندوم انتخاب می کند
                    singelPlayer(matrix2,playername_one,computerRand);

                    break;

                case 3:
                    settingBack(board, locknum, playername_one, playername_two);
                    break;
                default:

                    System.out.println("\t\t\t\t\t\t\t\t\t\t\033[91m ==> Rong choice <==\033[97m");
                    System.out.print("\t\t\t\t\t\t\t\t\t\t\t\033[33mPlese try again\033[97m");
                    menuBack(board,locknum,playername_one,playername_two);
                    break;
            }

        }


//----------------------------------------------------------------------------------------------------------------------------


        /**
         * This function print menu of game to choose type of game.
         */
        public static void menuFront ()
        {

            System.out.println("\n\n\n");

            System.out.println("\033[36m");

            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.println("\t\t\t\t      ____  __  ___    ____  __    ___    ____  __    __      ");

            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.println("\t\t\t\t     (_  _)(  )/ __)  (_  _)/ _\\  / __)  (_  _)/  \\  / _\\     ");

            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.println("\t\t\t\t       )(   )(( (__     )( /    \\( (__     )( (  O )/    \\    ");

            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.println("\t\t\t\t      (__) (__)\\___)   (__)\\_/\\_/ \\___)   (__) \\__/ \\_/\\_/    ");

            try{Thread.sleep(80);}catch(InterruptedException e) {};



            System.out.println("\n\n");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            System.out.println("\033[35m\t\t\t\t\t\t+-----------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.println("\t\t\t\t\t\t|          " + "\033[36m˙˚meniu˚˙" +"\033[35m          |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t+-----------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|      " + "\033[36m 1.multi player"+"\033[35m        |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|       "+"\033[36m2.singel player"+"\033[35m       |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|       "+"\033[36m3.Setting"+"\033[35m             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t+-----------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.print("\t\t\t\t\t\t==> ");

        }


//----------------------------------------------------------------------------------------------------------------------------


        public static void singelPlayer(String[][] matrix, String playername_one ,int computer_rand)
        {
            String playerMove = "\033[95m X\033[97m";
            String computerMove = "\033[96m O\033[97m";




            while(true)
            {


                //------------------- Player 1 ----------------------


                String input;
                String move;

                // 0 ===>  used
                // 1 ===>  dont used

                while(true)
                {
                    //---- Print game board (1)-----
                    System.out.println("\t\t\t\t\t      "+playername_one);
                    gameBoard(matrix);
                    System.out.print("\n\t\t\t\t\tChoose your target ==> ");

                    input = reader.next();
                    clear();

                    move = fixInput(input);
                    int use = ifUse(matrix, move);

                    if(use == 1)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("\t\t\t\t\t\033[91m   Wrong choose\033[97m");
                    }

                }



                clear();


                //---- change number to (X) --
                int[] num_location1 = change(matrix, move,playerMove );

                int row1 = num_location1[0];
                int colum1 = num_location1[1];

                //------ Win / Draw ------
                boolean test1 = winTest(matrix,  row1,  colum1);
                boolean draw = drawTest(matrix);

                if(test1)
                {
                    winBackend(playername_one);
                    break;
                }

                if(draw)
                {
                    System.out.print("\n\t\t\t\033[97m  <<< The Game was Draw >>> \n\n ");
                    try{Thread.sleep(1400);}catch(InterruptedException e) {};

                    restart();
                }






                //------------------- Computer ----------------------





                List<Integer> cin = new ArrayList<>();

                // Random random = new Random();
                int count = 0;
                computerInput(cin,computer_rand);
                String input2;
                String move2;


                // 0 ===>  used
                // 1 ===>  dont used

                while(true)
                {

                    input2 = String.valueOf(cin.get(count));
                    count++;

                    clear();

                    move2 = fixInput(input2);
                    int use2 = ifUse(matrix, move2);


                    if(use2 == 1)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("\t\t\t\t\t\033[91m    Wrong choose\033[97m");
                    }
                }




                //---- change number to (X) --
                int[] num_location2 = change(matrix, move2,computerMove );

                int row2 = num_location2[0];
                int colum2 = num_location2[1];

                //------ Win / Draw ------
                Boolean test2 = winTest(matrix,  row2,  colum2);
                boolean draw2 = drawTest(matrix);
                if(test2==true)
                {
                    String name2 = "\033[95mComputer\033[97m";
                    winBackend(name2);

                    break;
                }

                if(draw2)
                {
                    System.out.print("\n\t\t\t\033[97m  <<< The Game was Draw >>> \n\n ");
                    try{Thread.sleep(1400);}catch(InterruptedException e) {};

                    restart();
                }
            }

        }


//----------------------------------------------------------------------------------------------------------------------------


        public static void multilPlay(String[][] matrix,String playername_one,String playername_two)
        {
            String player1Move = "\033[95m X\033[97m";
            String player2Move = "\033[96m O\033[97m";




            while(true)
            {


                //------------------- Player 1 ----------------------


                String input1;
                String move1 ;

                // 0 ===>  used
                // 1 ===>  dont used

                while(true)
                {
                    //---- Print game board (1)-----
                    System.out.println("\t\t\t\t\t      "+playername_one);
                    gameBoard(matrix);
                    System.out.print("\n\t\t\t\t\tChoose your target ==> ");

                    input1 = reader.next();
                    clear();

                    move1 = fixInput(input1);
                    int use = ifUse(matrix, move1);

                    if(use == 1)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("\t\t\t\t\t\033[91m     Wrong choose\033[97m");
                    }
                }
                clear();


                //---- change number to (X) --
                int[] num_location1 = change(matrix, move1,player1Move );

                int row1 = num_location1[0];
                int colum1 = num_location1[1];

                //------ Win / Draw ------
                boolean test = winTest(matrix,  row1,  colum1);
                boolean draw = drawTest(matrix);

                if(test)
                {
                    winBackend(playername_one);
                    break;
                }

                if(draw)
                {
                    System.out.print("\n\t\t\t\033[97m  <<< The Game was Draw >>> \n\n ");
                    try{Thread.sleep(1400);}catch(InterruptedException e) {};

                    restart();
                }





                //------------------- Player 2 ----------------------


                String input2;
                String move2;

                // 0 ===>  used
                // 1 ===>  dont used

                while(true)
                {
                    //---- Print game board (2)-----
                    System.out.println("\t\t\t\t\t      "+playername_two);
                    gameBoard(matrix);
                    System.out.print("\n\t\t\t\t\tChoose your target ==> ");

                    input2 = reader.next();

                    clear();

                    move2 = fixInput(input2);
                    int use2 = ifUse(matrix, move2);


                    if(use2 == 1)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("\t\t\t\t\t\033[91m    Wrong choose\033[97m");
                    }
                }





                //---- change number to (O) --

                int[] num_location2 = change(matrix, move2,player2Move );

                int row2 = num_location2[0];
                int colum2 = num_location2[1];

                //------ Win / Draw ------
                boolean test2 = winTest(matrix,  row2,  colum2);
                boolean draw2 = drawTest(matrix);
                if(test2)
                {
                    winBackend(playername_two);
                    break;
                }
                if(draw2)
                {
                    System.out.print("\n\t\t\t\033[97m  <<< The Game was Draw >>> \n\n ");
                    try{Thread.sleep(1400);}catch(InterruptedException e) {};

                    restart();
                }
            }
        }




//----------------------------------------------------------------------------------------------------------------------------

        public static void lockNum(List lock, int lock_num,int board)
        {

            int pow = (board * board);
            Random random = new Random();

            int count = 0;

            while (count <lock_num)
            {
                int nm = random.nextInt(pow)+1;
                if(lock.contains(nm)) {} else
                {
                    lock.add(count,nm);
                    count++;
                }
            }
            System.out.println("\n");
        }

//----------------------------------------------------------------------------------------------------------------------------

        public static void fillMatirx(String[][] matrix, List lock,int lock_num) {

            // this function Complete a matrix


            int num = 1;
            for (int i = 0; i < matrix.length; i++)
            {
                for (int j = 0; j < matrix.length; j++)
                {

                    String num1 = String.valueOf(num);
                    if(num<10)
                    {
                        matrix[i][j] =  "\033[33m "+ num1 +"\033[97m";
                    }
                    else
                    {
                        matrix[i][j] =  "\033[33m"+num1+"\033[97m";
                    }

                    // ------ traveling on lock list-------

                    int lock_length = lock_num;

                    for (int l = 0; l < lock_length; l++)
                    {
                        String luk = String.valueOf(lock.get(l));

                        if (num1.equals(luk) == true)
                        {
                            matrix[i][j] = "\033[91m L\033[97m";
                        }

                    }
                    num++;

                }

            }
        }

//----------------------------------------------------------------------------------------------------------------------------

        public static void gameBoard(String[][] matrix)
        {
            System.out.print("\n\t\t\t\t\033[90m    << write (exit) to go back >>\033[97m\n");

            System.out.println("\033[97m");

            for (int i = 0; i < matrix.length; i++)
            {

                System.out.print("\t\t\t\t\t");
                for (int n = 0; n < matrix.length; n++)
                {
                    System.out.print("+----");
                }


                System.out.print("+\n\t\t\t\t\t");
                for (int j = 0; j <  matrix.length; j++)
                {
                    System.out.printf("| %2s ",matrix[i][j]);
                }
                System.out.println("|");


            }


            System.out.print("\t\t\t\t\t");

            for (int n = 0; n < matrix.length; n++)
            {
                System.out.print("+----");
            }
            System.out.print("+");


        }

//----------------------------------------------------------------------------------------------------------------------------

        public static boolean winTest(String[][] matrix, int  row, int colum){


            //--------- puting matrix in big one ---------------

            int x = matrix.length + 4;
            String[][] str = new String[x][x];


            for (int i = 0; i < str.length; i++) {
                for (int j = 0; j < str.length; j++) {
                    str[i][j] = " ";
                }
            }


            int k = 0;
            for (int i = 2; i < matrix.length+2; i++)
            {
                int n = 0;
                for (int j = 2; j < matrix.length+2; j++)
                {
                    str[i][j] = matrix[k][n];
                    n++;
                }
                k++;
            }


            boolean flag = false;

            int i = row;
            int j = colum;



            //------------------------ Diameter Win Rule -----------------------------

            if( str[i+2][j+2].equals(str[i+1][j+1]) && str[i+2][j+2].equals(str[i][j]))

            {
                flag = true;
            }

            if(str[i+2][j+2].equals(str[i+1][j+1]) && str[i+2][j+2].equals(str[i+3][j+3]))
            {
                flag = true;
            }

            if(str[i+2][j+2].equals(str[i+3][j+3]) && str[i+2][j+2].equals(str[i+4][j+4]))
            {
                flag = true;
            }



            //------------------------ Sub diameter Win Rule -----------------------------

            if( str[i+2][j+2].equals(str[i+3][j+1]) && str[i+2][j+2].equals(str[i+4][j]))

            {
                flag = true;
            }

            if(str[i+2][j+2].equals(str[i+1][j+3]) && str[i+2][j+2].equals(str[i+3][j+1]))
            {
                flag = true;
            }

            if(str[i+2][j+2].equals(str[i+1][j+3]) && str[i+2][j+2].equals(str[i][j+4]))
            {
                flag = true;
            }




            //------------------------ Vertical Win Rule -----------------------------

            if( str[i+2][j+2].equals(str[i+2][j+3]) && str[i+2][j+2].equals(str[i+2][j+4]))

            {
                flag = true;
            }

            if(str[i+2][j+2].equals(str[i+2][j+1]) && str[i+2][j+2].equals(str[i+2][j]))
            {
                flag = true;
            }

            if(str[i+2][j+2].equals(str[i+2][j+1]) && str[i+2][j+2].equals(str[i+2][j+3]))
            {
                flag = true;
            }




            //------------------------ Horizontal Win Rule -----------------------------

            if( str[i+2][j+2].equals(str[i+1][j+2]) && str[i+2][j+2].equals(str[i][j+2]))

            {
                flag = true;
            }

            if(str[i+2][j+2].equals(str[i+1][j+2]) && str[i+2][j+2].equals(str[i+3][j+2]))
            {
                flag = true;
            }

            if(str[i+2][j+2].equals(str[i+3][j+2]) && str[i+2][j+2].equals(str[i+4][j+2]))
            {
                flag = true;
            }





            return flag;
        }

//----------------------------------------------------------------------------------------------------------------------------

        public static String fixInput(String move)
        {
            String input = move;
            if(input.contains("exit") == true)
            {
                System.out.print("\033[H\033[2J");
                restart();
            }
            if(Integer.parseInt(input) < 10)
            {
                input =  "\033[33m "+ input+"\033[97m";
            }
            else
            {
                input =  "\033[33m"+input+"\033[97m";
            }

            return input;
        }

//----------------------------------------------------------------------------------------------------------------------------

        public static int ifUse(String[][] matrix,String input)
        {

            // 0 ===>  used
            // 1 ===>  dont used
            int flag = 0;
            int count = 0;
            for (int i = 0; i < matrix.length; i++)
            {
                for (int j = 0; j < matrix.length; j++)
                {
                    String test = matrix[i][j];

                    if (test.equals(input) == true)
                    {
                        count = count +1;
                    }
                }

            }
            if(count != 0)
            {
                flag = 1;
            }

            return flag;
        }

//----------------------------------------------------------------------------------------------------------------------------

        public static int[] change(String[][] matrix,String input,String movement)
        {

            //------- change number to (X / O) -----


            // [0] ==> row
            // [1] ==> colum

            int [] c = new int[2];
            for (int i = 0; i < matrix.length; i++)
            {
                for (int j = 0; j < matrix.length; j++)
                {
                    String num = matrix[i][j];

                    if (num.equals(input) == true)
                    {
                        matrix[i][j] = movement;
                        c[0] = i; // row
                        c[1] = j; // colum
                    }


                }

            }
            return c;

        }

//----------------------------------------------------------------------------------------------------------------------------

        public static void welcomMassage()
        {

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

            System.out.println("\033[36m\t\t\t     ___                ____________ __                             ");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t     \\  \\     ____     /  /  _______|  |     ______  ___   ___   _____ _______");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t      \\  \\   /    \\   /  /|  ____   |  |    /  ____/  _  \\|    \\/    |   _____|");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t       \\  \\ /  /\\  \\ /  / |   __|   |  |   |  |   |  | |  |  |\\  /|  |    __|");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t        \\  V  /  \\  V  /  |  |______|  |___|  |___|  |_|  |  | \\/ |  |   |____");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t         \\___/    \\___/   |_________|_______\\______\\_____/|__|    |__|________|");



            try{Thread.sleep(1400);}catch(InterruptedException e) {};

            clear();

        }

//----------------------------------------------------------------------------------------------------------------------------

        public static void computerInput(List num, int pow)
        {
            Random random = new Random();

            int count = 0;

            while (count < pow)
            {
                int nm = random.nextInt(pow)+1;
                if (!num.contains(nm)) {
                    num.add(count,nm);
                    count++;
                }
            }
        }

//----------------------------------------------------------------------------------------------------------------------------

        public static void winFront(String name)
        {

            System.out.println("\t\t\t\t  ˙˚"+name+"˚˙");
            System.out.print("\033[32m ");
            System.out.println("\t\t\t\t                .-----.                  ");
            System.out.println("\t\t\t\t               / .===. \\                ");
            System.out.println("\t\t\t\t               \\/ 6 6 \\/               ");
            System.out.println("\t\t\t\t               ( \\___/ )                ");
            System.out.println("\t\t\t\t  _________ooo__\\_____/____________     ");
            System.out.println("\t\t\t\t /                                  \\   ");
            System.out.println("\t\t\t\t|\033[31m           WIIIIIIIIIIIIN           \033[32m|   ");
            System.out.println("\t\t\t\t|                                    |   ");
            System.out.println("\t\t\t\t|          1.GO back to menu         |   ");
            System.out.println("\t\t\t\t|                                    |   ");
            System.out.println("\t\t\t\t|\033[90m     Thanks for using this game :)  \033[32m|   ");
            System.out.println("\t\t\t\t \\ _______________________ooo_______/   ");
            System.out.println("\t\t\t\t                |  |  |                  ");
            System.out.println("\t\t\t\t                |_ | _|                  ");
            System.out.println("\t\t\t\t                |  |  |                  ");
            System.out.println("\t\t\t\t                |__|__|                  ");
            System.out.println("\t\t\t\t                /-'Y'-\\                 ");
            System.out.println("\t\t\t\t               (__/ \\__)                ");
            System.out.print("\033[97m");









        }

//----------------------------------------------------------------------------------------------------------------------------

        public static void winBackend(String name)
        {
            winFront(name);

            int cin = reader.nextInt();

            System.out.print("\033[H\033[2J");

            if (cin == 1)
            {
                restart();
            } else
            {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\033[91m ==> Rong choice <==\033[97m");
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\033[33mPlese try again\033[97m");
                winBackend(name);
            }

        }

//----------------------------------------------------------------------------------------------------------------------------

        public static  Boolean drawTest(String[][] matrix)
        {
            String lock = "\033[91m L\033[97m";
            String move1 = "\033[95m X\033[97m";
            String move2 = "\033[96m O\033[97m";

            boolean flag = false;
            for (String[] strings : matrix) {
                for (int j = 0; j < matrix.length; j++)
                {
                    if (strings[j].equals(move1) || strings[j].equals(move2) || strings[j].equals(lock))
                        flag = true;
                    else {
                        flag = false;
                        break;
                    }
                }
            }

            return  flag;
        }


//----------------------------------------------------------------------------------------------------------------------------

        public static void clear()
        {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

//----------------------------------------------------------------------------------------------------------------------------






//
//
//
//
//
//
//         SSSSSSSSSSSSSSS                              tttt               tttt            iiii
//        SS:::::::::::::::S                          ttt:::t            ttt:::t           i::::i
//       S:::::SSSSSS::::::S                          t:::::t            t:::::t            iiii
//       S:::::S     SSSSSSS                          t:::::t            t:::::t
//       S:::::S                eeeeeeeeeeee    ttttttt:::::tttttttttttttt:::::ttttttt    iiiiiiinnnn  nnnnnnnn       ggggggggg   ggggg
//       S:::::S              ee::::::::::::ee  t:::::::::::::::::tt:::::::::::::::::t    i:::::in:::nn::::::::nn    g:::::::::ggg::::g
//        S::::SSSS          e::::::eeeee:::::eet:::::::::::::::::tt:::::::::::::::::t     i::::in::::::::::::::nn  g:::::::::::::::::g
//         SS::::::SSSSS    e::::::e     e:::::etttttt:::::::tttttttttttt:::::::tttttt     i::::inn:::::::::::::::ng::::::ggggg::::::gg
//           SSS::::::::SS  e:::::::eeeee::::::e      t:::::t            t:::::t           i::::i  n:::::nnnn:::::ng:::::g     g:::::g
//              SSSSSS::::S e:::::::::::::::::e       t:::::t            t:::::t           i::::i  n::::n    n::::ng:::::g     g:::::g
//                   S:::::Se::::::eeeeeeeeeee        t:::::t            t:::::t           i::::i  n::::n    n::::ng:::::g     g:::::g
//                   S:::::Se:::::::e                 t:::::t    tttttt  t:::::t    tttttt i::::i  n::::n    n::::ng::::::g    g:::::g
//       SSSSSSS     S:::::Se::::::::e                t::::::tttt:::::t  t::::::tttt:::::ti::::::i n::::n    n::::ng:::::::ggggg:::::g
//      S:::::::SSSSSS:::::S e::::::::eeeeeeee        tt::::::::::::::t  tt::::::::::::::ti::::::i n::::n    n::::n g::::::::::::::::g
//      S:::::::::::::::SS   ee:::::::::::::e          tt:::::::::::tt    tt:::::::::::tti::::::i n::::n    n::::n  gg::::::::::::::g
//       SSSSSSSSSSSSSSS       eeeeeeeeeeeeee            ttttttttttt        ttttttttttt  iiiiiiii nnnnnn    nnnnnn    gggggggg::::::g
//                                                                                                                            g:::::g
//                                                                                                               gggggg       g:::::g
//                                                                                                               g:::::gg    gg:::::g
//                                                                                                                g::::::g  g:::::::g
//                                                                                                                  gg:::::::::::::g
//                                                                                                                      ggg::::::ggg
//                                                                                                                         gggggg











        //----- first menu -----//

//----------------------------------------------------------------------------------------------------------------------------

        public static void settingBack(int board,int locknum,String playername_one,String playername_two)
        {
            settingFront();

            int key = reader.nextInt();

            clear();

            int locknum2 = locknum;
            int  board2 = board;
            String playername_one2 =  playername_one;
            String playername_two2 = playername_two;

            switch (key) {
                case 1:

                    board2 = boardBack(board, locknum, playername_one, playername_two);

                    settingBack(board2, locknum2, playername_one2, playername_two2);
                    break;

                case 2:
                    locknum2 = locknumBack(board, locknum, playername_one, playername_two);

                    settingBack(board2, locknum2, playername_one2, playername_two2);
                    break;

                case 3:

                    nameBack(board, locknum, playername_one, playername_two);

                    settingBack(board2, locknum2, playername_one2, playername_two2);
                    break;

                case 4:
                    menuBack(board2, locknum2, playername_one2, playername_two2);
                    //menuBack(board, locknum, playername_one, playername_two);

                    break;

                default:

                    System.out.println("\t\t\t\t\t\t\t\t\t\t\033[91m ==> Rong choice <==\033[97m");
                    System.out.print("\t\t\t\t\t\t\t\t\t\t\t\033[33mPlese try again\033[97m");
                    settingBack(board2, locknum2, playername_one2, playername_two2);
                    break;
            }



        }

//----------------------------------------------------------------------------------------------------------------------------

        public static void settingFront(){

            System.out.println("\n\n");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            System.out.println("\033[35m\t\t\t\t\t\t+-----------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.println("\t\t\t\t\t\t|         " + "\033[36m˙˚Setting˚˙" +"\033[35m         |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t+-----------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|      " + "\033[36m 1.Game board"+"\033[35m          |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|       "+"\033[36m2.lock number"+"\033[35m         |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|       "+"\033[36m3.Player name "+"\033[35m        |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|            4.exit           |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t+-----------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.print("\t\t\t\t\t\t==> ");

        }

//----------------------------------------------------------------------------------------------------------------------------






        //----- board menu -----//

//----------------------------------------------------------------------------------------------------------------------------

        public static int boardBack(int board,int locknum,String playername_one,String playername_two){
            boardFront();
            int key = reader.nextInt();

            clear();

            int change_board = 0;

            switch (key) {
                case 1:

                    change_board = 3;
                    System.out.print("\n\t\t\t\t\033[32m  <<< change Saved >>> ");
                    try{Thread.sleep(1500);}catch(InterruptedException e) {};
                    clear();

                    break ;

                case 2:
                    change_board = 6;
                    System.out.print("\n\t\t\t\t\033[32m  <<< change Saved >>> ");
                    try{Thread.sleep(1500);}catch(InterruptedException e) {};
                    clear();

                    break;

                case 3:
                    change_board = 8;
                    System.out.print("\n\t\t\t\t\033[32m  <<< change Saved >>> ");
                    try{Thread.sleep(1500);}catch(InterruptedException e) {};
                    clear();

                    break;

                case 4:
                    change_board = 10;
                    System.out.print("\n\t\t\t\t\033[32m  <<< change Saved >>> ");
                    try{Thread.sleep(1500);}catch(InterruptedException e) {};
                    clear();

                    break;

                case 5:
                    settingBack( board, locknum, playername_one, playername_two);
                    break;

                default:

                    System.out.println("\t\t\t\t\t\t\t\t\t\t\033[91m ==> Rong choice <==\033[97m");
                    System.out.print("\t\t\t\t\t\t\t\t\t\t\t\033[33mPlese try again\033[97m");
                    boardBack( board, locknum, playername_one, playername_two);
                    break;
            }

            return change_board;
        }

//----------------------------------------------------------------------------------------------------------------------------

        public static void boardFront(){
            System.out.println("\n\n");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            System.out.println("\033[35m\t\t\t\t\t\t+-----------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.println("\t\t\t\t\t\t|         " + "\033[36m˙˚Setting˚˙" +"\033[35m         |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t+-----------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|         " + "\033[36m 1. 3 * 3"+"\033[35m           |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|          "+"\033[36m2. 6 * 6"+"\033[35m           |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|          "+"\033[36m3. 8 * 8 "+"\033[35m          |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|          "+"\033[36m4.10 * 10 "+"\033[35m         |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                             |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|            5.exit           |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t+-----------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.print("\t\t\t\t\t\t==> ");

        }

//----------------------------------------------------------------------------------------------------------------------------






        //----- locknum menu -----//

//----------------------------------------------------------------------------------------------------------------------------

        public static int locknumBack(int board,int locknum,String playername_one,String playername_two)
        {
            clear();
            locknumFront();
            int change_lock = reader.nextInt();

            while(true)
            {
                if(change_lock > (board * board))
                {
                    System.out.println("\n\n\n\t\t\t\t\t\t\t\033[91m ==> Rong choice <==\033[97m");
                    System.out.print("\t\t\t\t\t\t\t\033[33mPlese try again\033[97m");
                    try{Thread.sleep(1000);}catch(InterruptedException e) {};
                    clear();
                    locknumBack(board, locknum, playername_one, playername_two);
                }
                else
                {
                    break;
                }
            }

            clear();
            System.out.print("\n\t\t\t\t\033[32m  <<< change Saved >>> ");
            try{Thread.sleep(1500);}catch(InterruptedException e) {};

            clear();

            return change_lock;
        }

//----------------------------------------------------------------------------------------------------------------------------

        public static void locknumFront()
        {
            System.out.println("\n\n");
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            System.out.println("\033[35m\t\t\t\t\t\t+--------------------------------+");

            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.println("\t\t\t\t\t\t|   " + "\033[36m˙˚ Write number of lock ˚˙" +"\033[35m   |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                                |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|          ˙˚ 1.exit ˚˙          |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t+--------------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.print("\t\t\t\t\t\tnumber ==> ");
        }

//----------------------------------------------------------------------------------------------------------------------------







        //----- name menu -----//
//----------------------------------------------------------------------------------------------------------------------------

        public static void nameBack(int board,int locknum,String playername_one,String playername_two)
        {
            nameFront();

            int key = reader.nextInt();
            clear();



            switch (key)
            {
                case 1:
                    System.out.print("\t\t\t\033[31m Write your name==>\033[97m");
                    playername_one = reader.next();

                    System.out.print("\n\t\t\t\033[97m  <<< Name Saved >>> \n\n");
                    try{Thread.sleep(1400);}catch(InterruptedException e) {};

                    clear();
                    nameBack(board, locknum, playername_one, playername_two);
                    break;


                case 2:
                    System.out.print("\t\t\t\033[31m Write your name==>\033[97m");
                    playername_two = reader.next();

                    System.out.print("\n\t\t\t\033[97m  <<< Name Saved >>> \n\n ");
                    try{Thread.sleep(1400);}catch(InterruptedException e) {};

                    clear();
                    nameBack(board, locknum, playername_one,playername_two );
                    break;

                case 3:
                    settingBack( board, locknum, playername_one, playername_two);
                    break;

                default:

                    System.out.println("\t\t\t\t\t\t\t\t\t\t\033[91m ==> Rong choice <==\033[97m");
                    System.out.print("\t\t\t\t\t\t\t\t\t\t\t\033[33mPlese try again\033[97m");
                    nameBack( board, locknum, playername_one, playername_two);
                    break;
            }

        }

//----------------------------------------------------------------------------------------------------------------------------

        public static void nameFront()
        {
            try{Thread.sleep(500);}catch(InterruptedException e) {};
            System.out.println("\033[35m\t\t\t\t\t\t+--------------------------------+");

            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.println("\t\t\t\t\t\t|   " + "\033[36m˙˚ Write name of player ˚˙" +"\033[35m   |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                                |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|          1.player one          |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                                |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|          2.player two          |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|                                |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t|          ˙˚ 3.exit ˚˙          |");
            try{Thread.sleep(80);}catch(InterruptedException e) {};

            System.out.println("\t\t\t\t\t\t+--------------------------------+");
            try{Thread.sleep(80);}catch(InterruptedException e) {};
            System.out.print("\t\t\t\t\t\t==> ");



        }

//----------------------------------------------------------------------------------------------------------------------------












































    }


    //                                      ___  ____  ____  ____  ____  _  _  ___
    //                                     / __)( ___)(_  _)(_  _)(_  _)( \( )/ __)
    //                                     \__ \ )__)   )(    )(   _)(_  )  (( (_-.
    //                                     (___/(____) (__)  (__) (____)(_)\_)\___/
    //








    //                                   ____  __  ___    ____  __    ___    ____  __    __
    //                                  (_  _)(  )/ __)  (_  _)/ _\  / __)  (_  _)/  \  / _\
    //                                    )(   )(( (__     )( /    \( (__     )( (  O )/    \
    //                                   (__) (__)\___)   (__)\_/\_/ \___)   (__) \__/ \_/\_/









    //                   .-"""-.
    //                  / .===. \
    //                  \/ 6 6 \/
    //                  ( \___/ )
    //   ___________ooo__\_____/__________
    //  /  ˙˚ALi˚˙                         \
    // |     Thanks for using this game :)  |
    //  \ _______________________ooo_______/
    //                   |  |  |
    //                   |_ | _|
    //                   |  |  |
    //                   |__|__|
    //                   /-'Y'-\
    //                  (__/ \__)
    //








