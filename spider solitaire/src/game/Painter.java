package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import view.Viewer;

import logic.Board;

public class Painter extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Board board;
	
	public Painter(Board board){
		this.board = board;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.getHSBColor(0.35f, 1, 0.45f));
		g.fillRect(0, 0, getWidth(), getHeight());
		Viewer.renderBoard(g, board);
	}
}