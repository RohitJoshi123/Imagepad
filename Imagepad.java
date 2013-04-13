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

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class grayconverter extends JFrame implements ActionListener{


    
	String IMG="";
	public JTextField jt;
    public JButton load;
    public JButton brows;
    public JButton org;
    public JButton gray;
    public JButton hist;
    public BufferedImage img1;
    public BufferedImage imggray;
    public int hist1[];
    public int max_hist1;
    
    public void init() throws Exception
    {
    	img1=null;
    	jt=new JTextField(50);
        load=new JButton("LOAD IMAGE");load.addActionListener(this);
        brows=new JButton("BROWSE IMAGE");brows.addActionListener(this);
        org=new JButton("ORIGINAL IMAGE");org.addActionListener(this);
        gray=new JButton("GRAY IMAGE");gray.addActionListener(this);
        hist=new JButton("HISTOGRAM");hist.addActionListener(this);
        Container content=getContentPane();
        content.setLayout(null);
        
        
        jt.setBounds(30,130,150,30);  
        load.setBounds(30,170,150,30); 
        brows.setBounds(250,130,150,30);
        org.setBounds(250,170,150,30);
        gray.setBounds(500,170,150,30);
        hist.setBounds(30,250,150,30);
        
        
        content.add(jt);
        content.add(load);
        content.add(brows);
        content.add(org);
        content.add(gray);
        content.add(hist);
        repaint();
        setSize(800,600);
		setVisible(true);
 
    }

	public static void main(String[] args) throws Exception {
		
		grayconverter g=new grayconverter();
		g.init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==brows)
	 	{
	 		JFileChooser jfc=new JFileChooser();
	 		if(jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION);
	 		{
	 			File f=jfc.getSelectedFile();
	 			jt.setText(f.getPath());
	 		}
	 			
	 	}
		if(e.getSource()==load)
                {
                     IMG=jt.getText();
                     System.out.println(IMG);
                     try
                     {
                     img1 = ImageIO.read(new File(IMG));
                     }
                     catch(Exception e1)
                     {
                                 JOptionPane.showMessageDialog(null, e1);
                     }
                     
                     hist1=new int[256];
                     max_hist1=0;
                     
                     for(int i=0;i<256;i++){
                    	 
                    	 
                    	 hist1[i]=0;
                    	 
                    	 
                     }
                     
                     
                     
                     
             }
		if(e.getSource()==org)
		{
			JFrame frame1 = new JFrame();
            JLabel label1 = new JLabel(new ImageIcon(img1));
            frame1.setTitle("ORIGINAL IMAGE");
            frame1.getContentPane().add(label1);
            frame1.pack();
            frame1.setSize(400,400);
            frame1.setVisible(true);
		}
		
   		if(e.getSource()==gray)
   		{
   			imggray=togray(img1);
            JFrame frame1 = new JFrame();
             JLabel label1 = new JLabel(new ImageIcon(imggray));
             frame1.setTitle("GRAY IMAGE");
             frame1.getContentPane().add(label1);
             frame1.pack();
             frame1.setSize(400,400);
             frame1.setVisible(true);
            
             
   		}
   		if(e.getSource()==hist)
   		{
   			
   			JFrame frame1=new JFrame();
   			
   			
   			class DrawPane extends JPanel
   			{
   				
   				public void paintComponent(Graphics g){
   					
   					
   					for(int i=0;i<256;i++){
   						if(hist1[i]>max_hist1)
   						{
   							max_hist1=hist1[i];
   		
   						}
   					
   						
   				//		 t.println(max_hist1);
   						
   					}
   					
   					
   					
   					g.setColor(Color.black);
   					g.drawString("|", 30, 205);
   					g.drawString("0", 30, 215);
   					g.drawString("|", 80, 205);
   					g.drawString("50", 80, 215);
   					g.drawString("|", 130, 205);
   					g.drawString("100", 130, 215);
   					g.drawString("|",  180, 205);
   					g.drawString("150", 180, 215);
   					g.drawString("|", 230, 205);
   					g.drawString("200", 230, 215);
   					g.drawString("|", 280, 205);
   					g.drawString("255", 280, 215);
   					
   					
   					String ss1=String.valueOf(max_hist1);
   					g.drawString(ss1 + "-", 0, 10);
   					String ss2=String.valueOf(max_hist1/2);
   					g.drawString(ss2 + "-", 0,110);
   					String ss3=String.valueOf(max_hist1/4);
   					g.drawString(ss3 + "-", 0,160);
   					String ss4=String.valueOf(max_hist1*3/4);
   					g.drawString(ss4 + "-", 0,60);
   					g.drawString("Image Histogram", 50, 250);
   					g.drawString("X- Axis    - Gray Level", 50, 270);
   					g.drawString("Y- Axis    - No. Of Pixels", 50, 290);
   					g.drawString("Maximum No. Of pixels are " +max_hist1, 50, 310);
   					
   					int w=200,h1=200;
   					int x=(w-256)/max_hist1;
   					int lasty=h1-h1*hist1[0]/max_hist1;
   					/*******************************************HIST FOR ORIGINAL IMAGE *****************************************************/
   					for(int i=0;i<256;i++,x++){
   						int y=h1-h1*hist1[i]/max_hist1;
   						
   						
   						
   					if(i<10 || i<60&&i>50){	
   					g.setColor(Color.pink);
   					}
   					if(i<20&&i>9 || i<80&&i>70){	
   	   					g.setColor(Color.green);
   	   					}
   					if(i<30 && i>19 || i<100&&i>90){	
   	   					g.setColor(Color.blue);
   	   					}
   					if(i<110&&i>99 || i<160&&i>150){	
   	   					g.setColor(Color.red);
   	   					}
   	   					if(i<120&&i>109 || i<180&&i>170){	
   	   	   					g.setColor(Color.yellow);
   	   	   					}
   	   					if(i<130 && i>119 || i<200&&i>190){	
   	   	   					g.setColor(new Color(100,240,150));
   	   	   					}
   	   					
   	   				if(i<210&&i>199 || i<256&&i>245){	
   	   					g.setColor(Color.pink);
   	   					}
   	   					if(i<230&&i>220){	
   	   	   					g.setColor(Color.green);
   	   	   					}
   	   				
   					g.fillRect(x+30, y, 1, 200-y);
   				    g.setColor(Color.black);
   					g.drawRect(0+30, 0, 256+30, 200);
   					g.setColor(Color.black);
   					g.drawLine(x-1+30, lasty, x+30, y);
   					lasty=y;
   				}
   					
   					
   					
   					
   					
   					
   					
   				}
   			}
   			frame1.setContentPane(new DrawPane());
   			frame1.setTitle("ORIGINAL IMAGE HISTOGRAM");
   			frame1.setSize(800, 800);
   			frame1.setVisible(true);
   			
   			
   			
   			
   		}
   		
		
		
	}
	 public BufferedImage togray(BufferedImage img1)
	    {
	        BufferedImage i2=new BufferedImage(img1.getWidth(),img1.getHeight(),1);
	    //i2.createImage(img.getWidth(),img.getHeight());
	    
	        int rgb[];
	        int k=0;
	         for(int i = 0; i < img1.getHeight(); i++)
	            {
	                for(int j = 0; j < img1.getWidth(); j++)
	                {
	                    rgb = getPixelData(img1, j, i);
	                    int r=0xff & rgb[0];
	                     int g=0xff & rgb[1];
	                        int b=0xff & rgb[2];
	                    int gray=(int)(0.33*r+0.56*g+0.11*b);
	                   
	         //           pixdata[k]=gray;
	                    
	                    hist1[gray]++;
	                  
	                    i2.setRGB(j,i,0xff000000|gray<<16|gray<<8|gray);
	                    
	                    k++;
	                    
	                  
	                
	                }
	            }
	         return i2;
	         
	    }
	 private static int[] getPixelData(BufferedImage img, int x, int y) 
	    {
	        int argb = img.getRGB(x, y);

	        int rgb[] = new int[] 
	                    {
	                     (argb >> 16) & 0xff, //red
	                     (argb >>  8) & 0xff, //green
	                     (argb      ) & 0xff  //blue
	                    };
	        return rgb;
	    }
	 


}
