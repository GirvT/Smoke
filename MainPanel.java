//Author: Manish Suresh
//Date : 05/03/17

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.text.*;
 
/**
@author Manish Suresh
"MainPanel" is the class that contains all other panels and instaniates from the Smoke class to use
*/
 
public class MainPanel extends JPanel
{
   //Instaniate used objects
   GamesLoader gl = new GamesLoader();
   ReadData rd = new ReadData();//Instantiate the class ReadData
   Records re = new Records();//Instantiate the class Records
   Sort s = new Sort();
   Add ad = new Add();
   Smoke sMain = new Smoke();
   HelpBox help = new HelpBox();
   
   //Refrenced files
   static String fileName = "records.txt";//fileName = "records.txt"
   static String logName = "log.txt";//fileName = "records.txt"

   JTextArea display = new JTextArea();
   DefaultHighlighter highlighter =  (DefaultHighlighter)display.getHighlighter();
   DefaultHighlighter.DefaultHighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.GRAY);
   DefaultHighlighter.DefaultHighlightPainter highlight = new DefaultHighlighter.DefaultHighlightPainter(Color.DARK_GRAY);

   String input, st;
   final int MAX = 10;//Set MAX number of records
   int line = 0;
   String rows[] = new String[MAX];//Store max 20 records
   String info[][] = new String[MAX][6];//Store records in 2d array
      
   /**
   "MainPanel" does the main() method while retaning the name of the class
   @param JFrame The main JFrame that instantiates "login"
   @param access The access level of the user
   */
   public MainPanel(final JFrame Frame, final String access)
   {
      main(Frame,access);
   }
   
   /**
   "main" creates a JPanel that contains buttons to access classes instantiated from "Smoke"
   @param JFrame The main JFrame that instantiates "login"
   @param access The access level of the user
   */
   public void main(final JFrame frame, final String Access)
   {
      final Image image = requestImage();

        JPanel panel = new JPanel() 
        {
            @Override
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                g.drawImage(image, 0, 0,null);
            }
        };
         
         panel.setLayout(null);
      
      //Added Buttons    
      JButton Search = new JButton("Search");
      JButton Add = new JButton("Add");      
      JButton Delete = new JButton("Delete");
      JButton Help = new JButton("Help"); 
      JButton Launch = new JButton("Launch");
      JButton SortN = new JButton("Sort by Name");
      JButton SortA = new JButton("Sort by Author");
      JButton SortR = new JButton("Sort by Date");      
      JButton SortD = new JButton("Sort by Method Name");    
      JButton Quit = new JButton("Quit");  
      JButton Refresh = new JButton("Refresh");     
      
      Refresh.setFocusPainted(false);
      Refresh.setContentAreaFilled(false);
      Refresh.setForeground(new Color(234, 234, 225));
            
      Delete.setFocusPainted(false);
      Delete.setContentAreaFilled(false);
      Delete.setForeground(new Color(234, 234, 225));
      Delete.setMargin( new Insets(1, 1, 1, 1) );
      
      Add.setFocusPainted(false);
      Add.setContentAreaFilled(false);
      Add.setForeground(new Color(234, 234, 225));
     
      Search.setFocusPainted(false);
      Search.setContentAreaFilled(false);
      Search.setForeground(new Color(234, 234, 225));
           
      SortN.setFocusPainted(false);
      SortN.setContentAreaFilled(false);
      SortN.setForeground(new Color(234, 234, 225));
      
      SortA.setFocusPainted(false);
      SortA.setContentAreaFilled(false);
      SortA.setForeground(new Color(234, 234, 225));
            
      SortR.setFocusPainted(false);
      SortR.setContentAreaFilled(false);
      SortR.setForeground(new Color(234, 234, 225));
      
      SortD.setFocusPainted(false);
      SortD.setContentAreaFilled(false);
      SortD.setForeground(new Color(234, 234, 225));
            
      Launch.setFocusPainted(false);
      Launch.setContentAreaFilled(false);
      Launch.setForeground(new Color(234, 234, 225));
      
      Help.setFocusPainted(false);
      Help.setContentAreaFilled(false);
      Help.setForeground(new Color(234, 234, 225));
            
      SortN.setBounds(70,40,120,30);     
      SortA.setBounds(215,40,120,30);
      SortR.setBounds(510,40,120,30);
      SortD.setBounds(360,40,130,30);            
      Search.setBounds(70,290,100,30);
      Add.setBounds(225,290,100,30);           
      Delete.setBounds(380,290,100,30);
      Refresh.setBounds(530,290,100,30); 
      Launch.setBounds(300,350,100,30);  
      Help.setBounds(0,0, 60,20);

      display.setEditable(false);
      display.setText("");
      rows = rd.readFile(fileName, 10);
      info = re.getRecords(rows);
      loading();
      display.append("     |Name|" + "\t|FileName|" + "\t|Method Name|" + "\t|Date Created|" + "\t|Rating|" + "\t|Publisher|" + "\n");
      display.append(st);
      lightup();
      display.setOpaque(false);
      display.setForeground(new Color(0, 255, 0));
      
      JScrollPane scrollPane = new JScrollPane(display,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      scrollPane.setBounds(70, 80, 560, 200);
      scrollPane.getViewport().setOpaque(false);
      scrollPane.setOpaque(false);
      
      panel.add(Search);
      panel.add(Add);
      panel.add(Delete);     
      panel.add(Launch);
      panel.add(SortN);
      panel.add(SortA); 
      panel.add(SortD);     
      panel.add(SortR); 
      panel.add(Refresh); 
      panel.add(scrollPane); 
      panel.add(Help);
      add(panel);        
      
      //Search button
      Search.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {   
            frame.dispose();
            sMain.searchFrame(Access);                      
         }
      });
      
      //Sort by name button
      SortN.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            display.setText("");
            sorting(0);
         }
      });// end Sort ActionListener
      
      //Sort by author button
      SortA.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            display.setText("");
            sorting(5);
         }
      });// end Sort ActionListener
      
      //Sort by rating button
      SortR.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            display.setText("");
            sorting(3);
         }
      });// end Sort ActionListener

      //Sort by date created button
      SortD.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            display.setText("");
            sorting(2);
         }
      });// end Sort ActionListener
      
       //Mouse select 
       display.addMouseListener(new MouseAdapter() 
       {
         @Override
         public void mouseClicked(MouseEvent e) 
         {
            if (e.getButton() != MouseEvent.BUTTON1) {
               return;
            }
            if (e.getClickCount() != 1) 
            {  
               return;
            }
            int offset = display.viewToModel(e.getPoint());
            try 
            {
               // begin To clear the previous selesction
               display.setText("");
               loading();
               display.append("     |Name|" + "\t|FileName|" + "\t|Method Name|" + "\t|Date Created|" + "\t|Rating|" + "\t|Publisher|" + "\n");
               display.append(st);
               // end To clear the previous selesction
               lightup();
               int line_max = display.getLineCount();
               int count = display.getLineEndOffset(line_max - 1);
               int rowStart = Utilities.getRowStart(display, offset);
               int rowEnd = Utilities.getRowEnd(display, offset);
               String selectedLine = display.getText().substring(rowStart, rowEnd);
               input = selectedLine;
               if (74 < offset && offset < count)
               {
                  int line = display.getLineOfOffset(offset);
                  int start =  display.getLineStartOffset(line);
                  int end =    display.getLineEndOffset(line);
                  highlighter.addHighlight(start, end, painter);
               }
            } 
            catch (BadLocationException e1)
            {
               e1.printStackTrace();
            }
         }
      });
      
      //Launch button press
      Launch.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent a)
         {
            String input2 = input;
            rows = rd.readFile(fileName, 10);
            info = re.getRecords(rows);
            gl.load(input2, info);
         }
      });// end Launch ActionListener
       
       //Refresh button press
       Refresh.addActionListener(new ActionListener()
       {
         public void actionPerformed(ActionEvent e)
         {
            frame.dispose();
            sMain.mainFrame(Access);
         }
        });  
            
      //Help button press
      Help.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
          help.helpbox();  
         }
           
      });
       
       //Quit button press   
       Quit.addActionListener(new ActionListener()
       {      
          public void actionPerformed(ActionEvent e)
          {
             System.exit(0);
          }
      });// end Quit ActionListener
      
      //"Admin" user level
      if (Access.equals("Admin"))
      {

         //Add button press
         Add.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {   
               frame.dispose();
               sMain.addFrame(Access);                       
            }
         });
     
         //Delete button press
         Delete.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {   
               frame.dispose();
               sMain.deleteFrame(Access);                       
            }
         }); 
      } 
      
      //Next "User" user level
      else if(Access.equals("User"))
      {
         //Add button Press
         Add.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {   
               JOptionPane.showMessageDialog(null, "Not Allowed.");
            }
         });
         
         //Delete button Press
         Delete.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
            {   
               JOptionPane.showMessageDialog(null, "Not Allowed.");
            }
         });
      }
        
        panel.setPreferredSize(new Dimension(700, 400));

   }
  
  /**
  "loading" translates the selected data into a form the program can use
  */
  public String loading()
      {
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < info.length; i++)
         {
            for(int j = 0; j < info[0].length; j++)
            {
               sb.append(info[i][j] + "\t");
            }
            sb.append("\n");
         }
         st = sb.toString().trim();
         return st;
      }
   
  /**
  "loading" sorts the data depending on the cateogry determined by the paramater
  @param a is the numerical value for the cataegory to be searched by
               0 is Name
               1 is FileName
               2 is MethodName
               3 is Date Created
               4 is Rating
               5 is Publisher
  */
   public void sorting(int a)
      {
         info = s.sortGame(info,a);
         StringBuilder sb2 = new StringBuilder();
         for (int  i = 0; i < info.length; i++)
         {
            for (int  j = 0; j < 6; j++)
            {
               if (rows[i] !=null)
               {
                  sb2.append(info[i][j] + "\t");
               }
            }
            sb2.append("\n");
         }
         display.append("     |Name|" + "\t|FileName|" + "\t|Method Name|" + "\t|Date Created|" + "\t|Rating|" + "\t|Publisher|" + "\n");
         display.append(sb2.toString().trim());
         lightup();
      }
   
   /**
   "lightup" highlights the selected data
   */
   private void lightup()
   {
      try 
      {
         int begin =  display.getLineStartOffset(0);
         int finish =    display.getLineEndOffset(0);
         highlighter.addHighlight(begin, finish, highlight);
      }catch (BadLocationException e1)
      {
         e1.printStackTrace();
      }
   }
   
   private Image requestImage()
    {
        Image image = null;

        try 
        {
            image = ImageIO.read(new File("aa.png"));
        } catch (IOException e) 
        {
            e.printStackTrace();
        }

        return image;
    }
}