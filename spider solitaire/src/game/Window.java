package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import view.Viewer;

import logic.Board;

public class Window extends JFrame {

	Board board;
	
	private Image dbImage;
    private Graphics dbg;
	
	public Window(Board board){
		super("Spider Solitaire");
		this.board = board;
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println(screen.height);
		this.setSize(screen.width, screen.height);
//		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.setIgnoreRepaint(true);
		this.createBufferStrategy(3);
	}
	
	public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
    
    public void paintComponent(Graphics g){
    	g.setColor(Color.getHSBColor(0.35f, 1, 0.45f));
		g.fillRect(0, 0, getWidth(), getHeight());
		Viewer.renderBoard(g, board); 
    }
}