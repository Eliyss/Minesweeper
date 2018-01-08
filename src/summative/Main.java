
package summative;

//Importing in swing GUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javafx.scene.input.MouseButton;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) 
    {
        //To set the amount of buttons
        boolean pass;
        do {
            p.t("What do you want the difficulty to be? Easy, Normal, Hard or Custom?");
            String diff = In.getString();
            diff = diff.toLowerCase();
            pass = false;
            switch(diff)
            {
                case "easy":
                    vars.width = 5;
                    vars.height = 5;
                    break;
                case "normal":
                    vars.width = 10;
                    vars.height = 10;
                    break;
                case "hard":
                    vars.width = 15;
                    vars.height = 25;
                    break;
                case "custom":
                    p.t("What will the width be? (Between 1 and 30)");
                    vars.width = In.getInt();
                    while (vars.width > 30 || vars.width < 1) {
                        p.t("Invalid Input");
                        p.t("What will the width be? (Between 1 and 30)");
                        vars.width = In.getInt();
                    }
                    p.t("What will the height be? (Between 1 and 15)");
                    vars.height = In.getInt();
                    while (vars.height > 15 || vars.height < 1) {
                        p.t("Invalid Input");
                        p.t("What will the width be? (Between 1 and 15)");
                        vars.height = In.getInt();
                    }
                    break;
                default:
                    pass = true;
                    p.t("Invalid Input. Please try again.");
            }
        } while (pass);
        
        

        //vars.width = 10;
        //vars.height = 15;
        new Main();
    }
    
    public Main()
    {
        /*LoveEdward();
    }
    
    public void LoveEdward()
    { */
        //Initializing the popup window
        int w = vars.width;
        int h = vars.height;
        int totbut = w * h;
        JFrame background = new JFrame("Minesweeper");
        
        background.setVisible(true);
        background.setSize(800, 400);
        background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background.setLayout(null);
        
        JLabel counterlbl = new JLabel("Mines Left: 100");
        counterlbl.setBounds(52*w/2-62, 65, 200, 20);
        background.add(counterlbl);
        JButton restart = new JButton("Restart?");
        restart.setBounds(52*w/2-67, 20, 100, 40);
        background.add(restart);
        JButton tets = new JButton("test?");
        tets.setBounds(52*w/2-67, 30, 100, 50);
        background.add(tets);
        
        //To restart the game
        restart.addMouseListener (new MouseListener () 
            {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { }
                @Override public void mouseExited (MouseEvent e) { }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e)
                {
                    if (e.getButton () == MouseEvent.BUTTON1){
                        background.setVisible(false);
                        new Main();
                    }
                }
            });
        
        //More setup
        JButton[] buttons = new JButton[totbut];
        for (int a = 0; a < h; a++)
        {
            for (int b = 0; b < w; b++)
            {
                buttons[(a*w+b)] = new JButton();
                buttons[(a*w+b)].setBounds((b*50), (a*50+100), 50, 50);
                background.add(buttons[(a*w+b)]);
            }
        }
        
        //Failed Failure
        /*JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setSize(200, 100);
        panel.setLayout(null);
        JLabel winlbl = new JLabel("You won! Grats!");
        counterlbl.setBounds(52*w/2-62, 65, 200, 20);
        panel.add(winlbl);
        JButton playagain = new JButton("Play Again?");
        restart.setBounds(52*w/2-67, 20, 100, 40);
        panel.add(playagain);
        background.add(panel);
        */
        
        //Array of tiles and the creation of mines
        int[] tiles = new int[totbut];
        
        for (int a = 0; a < totbut; a++) {
            int b = (int)(Math.random() * ((4) + 1) + 0);
            if (b == 0) {
                tiles[a] = 9;
            }
        }
        
        //Getting amount of mines surrounding any button
        for (int a = 0; a < totbut; a++) {  
            if (tiles[a] != 9) {
                if (a == 0) {
                    tiles[a] = getE(tiles, a) + getSE(tiles, a) + getS(tiles, a);
                    
                } else if (a == w - 1) {
                    tiles[a] = getW(tiles, a) + getSW(tiles, a) + getS(tiles, a);
                    
                } else if (a == (h - 1) * w) {
                    tiles[a] = getE(tiles, a) + getNE(tiles, a) + getN(tiles, a);
                    
                } else if (a == h * w -1) {
                    tiles[a] = getW(tiles, a) + getNW(tiles, a) + getN(tiles, a);
                    
                } else if (a <= w - 1) {
                    tiles[a] = getE(tiles, a) + getSE(tiles, a) + getS(tiles, a)
                               + getSW(tiles, a) + getW(tiles, a);
                    
                } else if (a >= (h - 1) * w) {
                    tiles[a] = getE(tiles, a) + getNE(tiles, a) + getN(tiles, a)
                               + getNW(tiles, a) + getW(tiles, a);
                    
                } else if ((a + 1) % w == 0) {
                    tiles[a] = getN(tiles, a) + getNW(tiles, a) + getW(tiles, a)
                               + getSW(tiles, a) + getS(tiles, a);
                    
                } else if (a % w == 0) {
                    tiles[a] = getN(tiles, a) + getNE(tiles, a) + getE(tiles, a)
                               + getSE(tiles, a) + getS(tiles, a);
                    
                } else {
                    tiles[a] = getN(tiles, a) + getNE(tiles, a) + getE(tiles, a)
                               + getSE(tiles, a) + getS(tiles, a) + getSW(tiles, a)
                               + getW(tiles, a) + getNW(tiles, a);
                }
            }
        }
        
        //Initializing all the variables
        vars.counter = 0;
        vars.mark = new int[totbut];
        vars.see = new boolean[totbut];
        
        try {
            vars.dead = ImageIO.read(getClass().getResource("dead.jpeg"));
        }catch (Exception ex) {
        }

        for (int a = 0; a < totbut; a ++) {
            if (tiles[a] == 9) {
            vars.counter = vars.counter + 1;
            }
        }
        vars.mines = vars.counter;
        
        //Commands for all the buttons 
        for (int a = 0; a < totbut; a ++) {
            int d = a;
            vars.mark[d] = 0;
            vars.see[d] = true;
            
            buttons[d].addMouseListener (new MouseListener () {
                @Override public void mouseClicked (MouseEvent e) { }
                @Override public void mouseEntered (MouseEvent e) { }
                @Override public void mouseExited (MouseEvent e) { }
                @Override public void mousePressed (MouseEvent e) { }
                @Override public void mouseReleased (MouseEvent e) {
                    //If Left Clicked
                    if (e.getButton () == MouseEvent.BUTTON1) {
                        if (tiles[d] == 9){
                            for (int b = 0; b < totbut; b++) {
                                vars.mark[b] = 3;
                                    if (tiles[b] == 9) {
                                    buttons[b].setIcon(new ImageIcon (vars.dead));
                                }
                            }
                            restart.setText("Try Again?");
                            counterlbl.setText("You died. Messily");
                        } else {
                        ubermethod(buttons, tiles, d);
                        }
                    
                    //If Right Clicked
                    } else if (e.getButton () == MouseEvent.BUTTON3 ) {
                        if (vars.mark[d] == 0) {
                            try {
                                Image img = ImageIO.read(getClass().getResource("flag.png"));
                                buttons[d].setIcon(new ImageIcon (img));
                                vars.mark[d] = 2;
                                vars.counter = vars.counter - 1;
                                counterlbl.setText("Mines Left: " + vars.counter);
                            } 
                            catch (Exception ex) {
                            }
                        } 
                        
                        else if (vars.mark[d] == 2) {
                            buttons[d].setIcon((null));
                            vars.mark[d] = 0;
                            vars.counter = vars.counter + 1;
                            counterlbl.setText("Mines Left: " + vars.counter);
                        }
                        
                        if (vars.counter == 0) {
                            for (int b = 0; b < totbut; b++) {
                                if (vars.mark[b] == 2 && tiles[b] == 9){
                                    vars.mines = vars.mines - 1;
                                }
                                if (vars.mines == 0) {
                                    restart.setText("Play Again?");
                                    counterlbl.setText("You won! Grats!");
                                }    
                            }
                        }
                    }
                }
            });
        }
        
        //Initialising the label
        counterlbl.setText("Mines Left: " + vars.counter);
    }
    public static void ubermethod(JButton buttons[], int[] tiles, int d) {
        
        /*
        Some seriously inefficent code that deals with pressing a 0
        Pressing a 0 leads to it being invisible, revealing everything surrounding it
        and if the revealed number is also a 0, it also has to reveal everything around it
        and so forth
        */
        
        int w = vars.width;
        int h = vars.height;
        if (vars.mark[d] == 2 || vars.mark[d] == 3){
            return;
        }
        if (tiles[d] == 9) {
            buttons[d].setIcon(new ImageIcon (vars.dead));
            vars.mark[d] = 1;
            
        } else if (tiles[d] == 0) {
            buttons[d].setVisible(false);
            vars.see[d] = false;
            
            if (d == 0) {
                if (checkE(tiles, d) == 0 && vars.see[d + 1]) {
                    ubermethod(buttons, tiles, d + 1);
                } else if (tiles[d + 1] != 0) {
                    buttons[d + 1].setText(""+tiles[d + 1]);
                    vars.mark[d + 1] = 1;
                }
                
                if (checkSE(tiles, d) == 0 && vars.see[d + w + 1]) {
                    ubermethod(buttons, tiles, d + w + 1);
                } else if (tiles[d + w + 1] != 0) {
                    buttons[d + w + 1].setText(""+tiles[d + w + 1]);
                    vars.mark[d + w + 1] = 1;
                }
                
                if (checkS(tiles, d) == 0 && vars.see[d + w]) {
                        ubermethod(buttons, tiles, d + w);
                } else if (tiles[d + w] != 0) {
                    buttons[d + w].setText(""+tiles[d + w]);
                    vars.mark[d + w] = 1;
                }
                
            } else if (d == w - 1) {
                if (checkW(tiles, d) == 0 && vars.see[d - 1]) {
                    ubermethod(buttons, tiles, d - 1);
                } else if (tiles[d - 1] != 0) {
                    buttons[d - 1].setText(""+tiles[d - 1]);
                    vars.mark[d - 1] = 1;
                }
                
                if (checkSW(tiles, d) == 0 && vars.see[d + w - 1]) {
                    ubermethod(buttons, tiles, d + w - 1);
                }else if (tiles[d + w - 1] != 0) {
                    buttons[d + w - 1].setText(""+tiles[d + w - 1]);
                    vars.mark[d + w - 1] = 1;
                }
                
                if (checkS(tiles, d) == 0 && vars.see[d + w]) {
                        ubermethod(buttons, tiles, d + w);
                } else if (tiles[d + w] != 0) {
                    buttons[d + w].setText(""+tiles[d + w]);
                    vars.mark[d + w] = 1;
                }
                
            } else if (d == (h - 1) * w) {
                if (checkE(tiles, d) == 0 && vars.see[d + 1]) {
                    ubermethod(buttons, tiles, d + 1);
                } else if (tiles[d + 1] != 0) {
                    buttons[d + 1].setText(""+tiles[d + 1]);
                    vars.mark[d + 1] = 1;
                }
                
                if (checkNE(tiles, d) == 0 && vars.see[d - w + 1]) {
                    ubermethod(buttons, tiles, d - w + 1);
                } else if (tiles[d - w + 1] != 0) {
                    buttons[d - w + 1].setText(""+tiles[d - w + 1]);
                    vars.mark[d - w + 1] = 1;
                }
                
                if (checkN(tiles, d) == 0 && vars.see[d - w]) {
                        ubermethod(buttons, tiles, d - w);
                }else if (tiles[d - w] != 0) {
                    buttons[d - w].setText(""+tiles[d - w]);
                    vars.mark[d - w] = 1;
                }
                
            } else if (d == h * w -1) {
                if (checkW(tiles, d) == 0 && vars.see[d - 1]) {
                    ubermethod(buttons, tiles, d - 1);
                } else if (tiles[d - 1] != 0) {
                    buttons[d - 1].setText(""+tiles[d - 1]);
                    vars.mark[d - 1] = 1;
                }
                
                if (checkNW(tiles, d) == 0 && vars.see[d - w - 1]) {
                    ubermethod(buttons, tiles, d - w - 1);
                } else if (tiles[d - w - 1] != 0) {
                    buttons[d - w - 1].setText(""+tiles[d - w - 1]);
                    vars.mark[d - w - 1] = 1;
                }
                
                if (checkN(tiles, d) == 0 && vars.see[d - w]) {
                        ubermethod(buttons, tiles, d - w);
                }else if (tiles[d - w] != 0) {
                    buttons[d - w].setText(""+tiles[d - w]);
                    vars.mark[d - w] = 1;
                }
                
            } else if (d <= w - 1) {
                if (checkE(tiles, d) == 0 && vars.see[d + 1]) {
                    ubermethod(buttons, tiles, d + 1);
                } else if (tiles[d + 1] != 0) {
                    buttons[d + 1].setText(""+tiles[d + 1]);
                    vars.mark[d + 1] = 1;
                }
                
                if (checkSE(tiles, d) == 0 && vars.see[d + w + 1]) {
                    ubermethod(buttons, tiles, d + w + 1);
                } else if (tiles[d + w + 1] != 0) {
                    buttons[d + w + 1].setText(""+tiles[d + w + 1]);
                    vars.mark[d + w + 1] = 1;
                }
                
                if (checkS(tiles, d) == 0 && vars.see[d + w]) {
                        ubermethod(buttons, tiles, d + w);
                } else if (tiles[d + w] != 0) {
                    buttons[d + w].setText(""+tiles[d + w]);
                    vars.mark[d + w] = 1;
                }
                
                if (checkSW(tiles, d) == 0 && vars.see[d + w - 1]) {
                    ubermethod(buttons, tiles, d + w - 1);
                }else if (tiles[d + w - 1] != 0) {
                    buttons[d + w - 1].setText(""+tiles[d + w - 1]);
                    vars.mark[d + w - 1] = 1;
                }
                
                if (checkW(tiles, d) == 0 && vars.see[d - 1]) {
                    ubermethod(buttons, tiles, d - 1);
                } else if (tiles[d - 1] != 0) {
                    buttons[d - 1].setText(""+tiles[d - 1]);
                    vars.mark[d - 1] = 1;
                }
                
            } else if (d >= (h - 1) * w) {
                if (checkE(tiles, d) == 0 && vars.see[d + 1]) {
                    ubermethod(buttons, tiles, d + 1);
                } else if (tiles[d + 1] != 0) {
                    buttons[d + 1].setText(""+tiles[d + 1]);
                    vars.mark[d + 1] = 1;
                }
                
                if (checkNE(tiles, d) == 0 && vars.see[d - w + 1]) {
                    ubermethod(buttons, tiles, d - w + 1);
                } else if (tiles[d - w + 1] != 0) {
                    buttons[d - w + 1].setText(""+tiles[d - w + 1]);
                    vars.mark[d - w + 1] = 1;
                }
                
                if (checkN(tiles, d) == 0 && vars.see[d - w]) {
                        ubermethod(buttons, tiles, d - w);
                }else if (tiles[d - w] != 0) {
                    buttons[d - w].setText(""+tiles[d - w]);
                    vars.mark[d - w] = 1;
                }
                
                if (checkNW(tiles, d) == 0 && vars.see[d - w - 1]) {
                    ubermethod(buttons, tiles, d - w - 1);
                } else if (tiles[d - w - 1] != 0) {
                    buttons[d - w - 1].setText(""+tiles[d - w - 1]);
                    vars.mark[d - w - 1] = 1;
                }
                
                if (checkW(tiles, d) == 0 && vars.see[d - 1]) {
                    ubermethod(buttons, tiles, d - 1);
                } else if (tiles[d - 1] != 0) {
                    buttons[d - 1].setText(""+tiles[d - 1]);
                    vars.mark[d - 1] = 1;
                }
                
            } else if ((d + 1) % w == 0) {
                if (checkN(tiles, d) == 0 && vars.see[d - w]) {
                        ubermethod(buttons, tiles, d - w);
                }else if (tiles[d - w] != 0) {
                    buttons[d - w].setText(""+tiles[d - w]);
                    vars.mark[d - w] = 1;
                }
                
                if (checkNW(tiles, d) == 0 && vars.see[d - w - 1]) {
                    ubermethod(buttons, tiles, d - w - 1);
                } else if (tiles[d - w - 1] != 0) {
                    buttons[d - w - 1].setText(""+tiles[d - w - 1]);
                    vars.mark[d - w - 1] = 1;
                }
                
                if (checkW(tiles, d) == 0 && vars.see[d - 1]) {
                    ubermethod(buttons, tiles, d - 1);
                } else if (tiles[d - 1] != 0) {
                    buttons[d - 1].setText(""+tiles[d - 1]);
                    vars.mark[d - 1] = 1;
                }
                
                if (checkSW(tiles, d) == 0 && vars.see[d + w - 1]) {
                    ubermethod(buttons, tiles, d + w - 1);
                }else if (tiles[d + w - 1] != 0) {
                    buttons[d + w - 1].setText(""+tiles[d + w - 1]);
                    vars.mark[d + w - 1] = 1;
                }
                
                if (checkS(tiles, d) == 0 && vars.see[d + w]) {
                        ubermethod(buttons, tiles, d + w);
                } else if (tiles[d + w] != 0) {
                    buttons[d + w].setText(""+tiles[d + w]);
                    vars.mark[d + w] = 1;
                }
                
            } else if (d % w == 0) {
                if (checkN(tiles, d) == 0 && vars.see[d - w]) {
                        ubermethod(buttons, tiles, d - w);
                }else if (tiles[d - w] != 0) {
                    buttons[d - w].setText(""+tiles[d - w]);
                    vars.mark[d - w] = 1;
                }
                
                if (checkNE(tiles, d) == 0 && vars.see[d - w + 1]) {
                    ubermethod(buttons, tiles, d - w + 1);
                } else if (tiles[d - w + 1] != 0) {
                    buttons[d - w + 1].setText(""+tiles[d - w + 1]);
                    vars.mark[d - w + 1] = 1;
                }
                
                if (checkE(tiles, d) == 0 && vars.see[d + 1]) {
                    ubermethod(buttons, tiles, d + 1);
                } else if (tiles[d + 1] != 0) {
                    buttons[d + 1].setText(""+tiles[d + 1]);
                    vars.mark[d + 1] = 1;
                }
                
                if (checkSE(tiles, d) == 0 && vars.see[d + w + 1]) {
                    ubermethod(buttons, tiles, d + w + 1);
                } else if (tiles[d + w + 1] != 0) {
                    buttons[d + w + 1].setText(""+tiles[d + w + 1]);
                    vars.mark[d + w + 1] = 1;
                }
                
                if (checkS(tiles, d) == 0 && vars.see[d + w]) {
                        ubermethod(buttons, tiles, d + w);
                } else if (tiles[d + w] != 0) {
                    buttons[d + w].setText(""+tiles[d + w]);
                    vars.mark[d + w] = 1;
                }
                
            } else {
                if (checkN(tiles, d) == 0 && vars.see[d - w]) {
                        ubermethod(buttons, tiles, d - w);
                }else if (tiles[d - w] != 0) {
                    buttons[d - w].setText(""+tiles[d - w]);
                    vars.mark[d - w] = 1;
                }
                
                if (checkNE(tiles, d) == 0 && vars.see[d - w + 1]) {
                    ubermethod(buttons, tiles, d - w + 1);
                } else if (tiles[d - w + 1] != 0) {
                    buttons[d - w + 1].setText(""+tiles[d - w + 1]);
                    vars.mark[d - w + 1] = 1;
                }
                
                if (checkE(tiles, d) == 0 && vars.see[d + 1]) {
                    ubermethod(buttons, tiles, d + 1);
                } else if (tiles[d + 1] != 0) {
                    buttons[d + 1].setText(""+tiles[d + 1]);
                    vars.mark[d + 1] = 1;
                }
                
                if (checkSE(tiles, d) == 0 && vars.see[d + w + 1]) {
                    ubermethod(buttons, tiles, d + w + 1);
                } else if (tiles[d + w + 1] != 0) {
                    buttons[d + w + 1].setText(""+tiles[d + w + 1]);
                    vars.mark[d + w + 1] = 1;
                }
                
                if (checkS(tiles, d) == 0 && vars.see[d + w]) {
                        ubermethod(buttons, tiles, d + w);
                } else if (tiles[d + w] != 0) {
                    buttons[d + w].setText(""+tiles[d + w]);
                    vars.mark[d + w] = 1;
                }
                
                if (checkSW(tiles, d) == 0 && vars.see[d + w - 1]) {
                    ubermethod(buttons, tiles, d + w - 1);
                }else if (tiles[d + w - 1] != 0) {
                    buttons[d + w - 1].setText(""+tiles[d + w - 1]);
                    vars.mark[d + w - 1] = 1;
                }
                
                if (checkW(tiles, d) == 0 && vars.see[d - 1]) {
                    ubermethod(buttons, tiles, d - 1);
                } else if (tiles[d - 1] != 0) {
                    buttons[d - 1].setText(""+tiles[d - 1]);
                    vars.mark[d - 1] = 1;
                }
                
                if (checkNW(tiles, d) == 0 && vars.see[d - w - 1]) {
                    ubermethod(buttons, tiles, d - w - 1);
                } else if (tiles[d - w - 1] != 0) {
                    buttons[d - w - 1].setText(""+tiles[d - w - 1]);
                    vars.mark[d - w - 1] = 1;
                }
            }
        } else {
            buttons[d].setText(""+tiles[d]);
            vars.mark[d] = 1;
        }
    }

    //All the methods used to see if the vaule to the north, south etc was a 9
    public static int getN(int[] x, int value)
    {
        if (x[value - vars.width] == 9)
        {
            return 1;
        }
        
        return 0;
    }
    
    public static int getNE(int[] x, int value)
    {
        if (x[value - vars.width + 1] == 9)
        {
            return 1;
        }
        
        return 0;
    }
    
    
    public static int getE(int[] x, int value)
    {
        if (x[value + 1] == 9)
        {
            return 1;
        }
        
        return 0;
    }
    
    public static int getSE(int[] x, int value)
    {
        if (x[value + vars.width + 1] == 9)
        {
            return 1;
        }
        
        return 0;
    }
    
    public static int getS(int[] x, int value)
    {
        if (x[value + vars.width] == 9)
        {
            return 1;
        }
        
        return 0;
    }
    
    public static int getSW(int[] x, int value)
    {
        if (x[value + vars.width - 1] == 9)
        {
            return 1;
        }
        
        return 0;
    }
    
    public static int getW(int[] x, int value)
    {
        if (x[value - 1] == 9)
        {
            return 1;
        }
        
        return 0;
    }
    
    public static int getNW(int[] x, int value)
    {
        if (x[value - vars.width - 1] == 9)
        {
            return 1;
        }
        
        return 0;
    }
    //All the methods returning the value of the button to the north...
    public static int checkN(int[] x, int value)
    {
         return x[value - vars.width];      
    }
    
    public static int checkNE(int[] x, int value)
    {
        return x[value - vars.width + 1];
    }
        
    public static int checkE(int[] x, int value)
    {
        return x[value + 1];   
    }
    
    public static int checkSE(int[] x, int value)
    {
        return x[value + vars.width + 1];    
    }
    
    public static int checkS(int[] x, int value)
    {
        return x[value + vars.width];       
    }
    
    public static int checkSW(int[] x, int value)
    {
        return x[value + vars.width - 1];        
    }
    
    public static int checkW(int[] x, int value)
    {
        return x[value - 1];  
    }
    
    public static int checkNW(int[] x, int value)
    {
        return x[value - vars.width - 1];
    }
    
    //Something I was testing
    //Not really part of the code
    private void listen (JButton x)
    {
        x.addMouseListener (new MouseListener () 
        {
            @Override public void mouseClicked (MouseEvent e) { }
            @Override public void mouseEntered (MouseEvent e) { }
            @Override public void mouseExited (MouseEvent e) { }
            @Override public void mousePressed (MouseEvent e) { }
            @Override public void mouseReleased (MouseEvent e)
            {
                if (e.getButton () == MouseEvent.BUTTON1) 
                {
                    x.setText("10");
                } 
                else if (e.getButton () == MouseEvent.BUTTON3)
                {
                    x.setText("2");
                } 
            }                        
        });
    }
    
            
}





    



