package fr.hattane.ilias.games.sample.isometric2d.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.hattane.ilias.games.sample.isometric2d.config.BuildingObjects;
import fr.hattane.ilias.games.sample.isometric2d.config.MapObjectTypes;
import fr.hattane.ilias.games.sample.isometric2d.config.NaturalObjects;
import fr.hattane.ilias.games.sample.isometric2d.ui.panels.EditorPanel;

public class ObjectsFrame extends JFrame {

	private static final long serialVersionUID = -2963776684784282806L;
	
	public static final int OBJECT_SIZE = 100;
	
	private JLabel titleNatural;
	private JLabel titleBuildings;
	
	private EditorPanel editor;
	
	public ObjectsFrame(EditorPanel editor) {
		
		this.editor = editor;
		
		int width = 1030;
		int height = 800;
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Sélection d'un objet");
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		this.setContentPane(new JPanel());
		this.getContentPane().setLayout(null);
		this.getContentPane().setLocation(0, 0);
		this.getContentPane().setSize(width, height);
		
		int startX = 10;

		titleNatural = new JLabel("Objets naturels");
		titleNatural.setFont(new Font("Consolas", Font.BOLD, 50));
		titleNatural.setLocation(startX, 10);
		titleNatural.setSize(getWidth(), 60);
		titleNatural.setVisible(true);
		this.getContentPane().add(titleNatural);
		
		int startY = 70;
		
		int x = 0;
		int y = 0;
		for (NaturalObjects obj : NaturalObjects.values()) {
			
			if (obj != NaturalObjects.NONE) {
				JButton button = new JButton(new ImageIcon(obj.getImage().getImage()));
				button.setFocusPainted(false);
				button.setSize(OBJECT_SIZE, OBJECT_SIZE);
				button.setLocation(startX + x*OBJECT_SIZE, startY + y*OBJECT_SIZE);
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						editor.setSelectedType(MapObjectTypes.NATURAL);
						editor.setSelectedIndex(obj.getValueIndex());
						dispose();
					}
				});
				button.setVisible(true);
				this.getContentPane().add(button);
				
				x++;
				if (x >= 10) {
					y++;
					x = 0;
				}
			}
			
		}
		
		startY += + (y+1)*OBJECT_SIZE + 20;
		
		titleBuildings = new JLabel("Bâtiments");
		titleBuildings.setFont(new Font("Consolas", Font.BOLD, 50));
		titleBuildings.setLocation(startX, startY);
		titleBuildings.setSize(getWidth(), 60);
		titleBuildings.setVisible(true);
		this.getContentPane().add(titleBuildings);
		
		startY += 50 + 10;
		x = 0;
		y = 0;
		for (BuildingObjects obj : BuildingObjects.values()) {
			
			if (obj != BuildingObjects.NONE) {
				JButton button = new JButton(new ImageIcon(obj.getImage().getImage()));
				button.setFocusPainted(false);
				button.setSize(OBJECT_SIZE, OBJECT_SIZE);
				button.setLocation(startX + x*OBJECT_SIZE, startY + y*OBJECT_SIZE);
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						editor.setSelectedType(MapObjectTypes.BUILDING);
						editor.setSelectedIndex(obj.getValueIndex());
						dispose();
					}
				});
				button.setVisible(true);
				this.getContentPane().add(button);
				
				x++;
				if (x >= 10) {
					y++;
					x = 0;
				}
			}
			
		}
		
		this.getContentPane().setVisible(true);
		this.setVisible(true);
		
	}

	public JLabel getTitleNatural() {
		return titleNatural;
	}

	public void setTitleNatural(JLabel titleNatural) {
		this.titleNatural = titleNatural;
	}

	public JLabel getTitleBuildings() {
		return titleBuildings;
	}

	public void setTitleBuildings(JLabel titleBuildings) {
		this.titleBuildings = titleBuildings;
	}

	public EditorPanel getEditor() {
		return editor;
	}

	public void setEditor(EditorPanel editor) {
		this.editor = editor;
	}
	
}
