/*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*    (c) Arvind Kumar 2011 . All rights Reserved
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class Imagepad 
{
JFrame frame;
static String imf,gd;
public static void main(String args[])throws IOException
{
    BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
    try { gd=args[0]; }
    catch (ArrayIndexOutOfBoundsException aioob)
        {
            System.out.println("Enter path : ");
            gd=b.readLine();
        }
   imf=gd;
    Imagepad inst=new Imagepad();
    inst.initial();
}

public void initial()throws IOException
{
    System.out.println("Console");
    frame=new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    Paint screen=new Paint();

    BufferedImage img = ImageIO.read(new File(imf));

    frame.getContentPane().add(BorderLayout.CENTER, screen);
    frame.setSize(img.getWidth(null),img.getHeight(null));
    frame.setVisible(true);
}

class Paint extends JPanel
{
public void paintComponent(Graphics g)
{   
    Image image=new ImageIcon(imf).getImage();
    g.drawImage(image,0,0,this);
}
}

}