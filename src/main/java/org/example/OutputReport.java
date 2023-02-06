package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OutputReport extends JFrame {
	private JFrame frame;
	private JPanel panel;
	private JPanel okPanel;
	private JPanel tablePanel;
	private JButton button;
	private JTable table;
	private JFileChooser fileChooser;
	private FileInputStream inFile;
	private String filePath;
	private String content;
	public OutputReport() {
		frame = new JFrame("Hakim Khan");
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		okPanel = new JPanel();
		tablePanel = new JPanel();
		okPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		tablePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		button = new JButton("Open");
		fileChooser = new JFileChooser(System.getProperty("user.dir"));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.showOpenDialog(frame);
				tablePanel.removeAll();
				filePath = fileChooser.getSelectedFile().toString();
				try {
					openFile(filePath);
				} catch (IOException ex) {
					throw new RuntimeException(ex);
				}
			}
		});

		okPanel.add(new JLabel("Hakim Khan"));
		okPanel.add(Box.createHorizontalStrut(50));
		okPanel.add(button);
		panel.add(okPanel);
		panel.add(tablePanel);
		frame.setSize(600, 400);
		frame.setVisible(true);
		frame.add(panel);
	}

	public void openFile(String filePath) throws IOException {
		try {
			inFile = new FileInputStream(filePath);
			content = "";
			int c;
			while((c = inFile.read()) != -1) {
				content += (char) c;
			}
			if (content.contains("Hakim Khan")) {
				outputResult(content);
			} else {
				throw new RuntimeException("Invalid File!");
			};
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void outputResult(String content) {
		String[] columnNames = {"Size", "Avg Count", "Coef Count", "Avg Count", "Coef Count"};
		String[][] resultStringData = new String[13][5];

		resultStringData[0][0] = "Size";
		resultStringData[0][1] = "Avg Count";
		resultStringData[0][2] = "Coef Count";
		resultStringData[0][3] = "Avg Count";
		resultStringData[0][4] = "Coef Count";

		double[][] resultDoubleData = new double[12][4];
		long[][][] longData = new long[12][40][2];
		int startIndex = 0, endIndex = 0;

		for (int i = 0; i < 12; i ++) {
			long count = 0, time = 0;
			for (int j = 0; j < 40; j ++) {
				startIndex = content.indexOf('[', startIndex) + 1;
				endIndex = content.indexOf(',', startIndex);
				longData[i][j][0] = Long.parseLong(content.substring(startIndex, endIndex));
				count += longData[i][j][0];
				startIndex = endIndex + 2;
				endIndex = content.indexOf(']', startIndex);
				longData[i][j][1] = Long.parseLong(content.substring(startIndex, endIndex));
				time += longData[i][j][1];
			}
			resultDoubleData[i][0] = (double) count / 40;
			resultDoubleData[i][2] = (double) time / 40;
		}

		for (int i = 0; i < 12; i ++) {
			double countSum = 0, timeSum = 0;
			for(int j = 0; j < 40; j ++) {
				countSum += (((double) longData[i][j][0] - resultDoubleData[i][0]) * ((double) longData[i][j][0] - resultDoubleData[i][0]));
				timeSum += (((double) longData[i][j][1] - resultDoubleData[i][2]) * ((double) longData[i][j][1] - resultDoubleData[i][2]));
			}
			resultDoubleData[i][1] = Math.sqrt((countSum) / 39) / resultDoubleData[i][0] * 100;
			resultDoubleData[i][3] = Math.sqrt((timeSum) / 39) / resultDoubleData[i][2] * 100;
		}

		for (int i = 0; i < 12; i ++) {
			resultStringData[i + 1][0] = Integer.toString(100 * (i + 1));
			resultStringData[i + 1][1] = String.format("%.2f", resultDoubleData[i][0]);
			resultStringData[i + 1][2] = String.format("%.2f", resultDoubleData[i][1]) + "%";
			resultStringData[i + 1][3] = String.format("%.2f", resultDoubleData[i][2]);
			resultStringData[i + 1][4] = String.format("%.2f", resultDoubleData[i][3]) + "%";
		}

		table = new JTable(resultStringData, columnNames);
		table.setMinimumSize(new Dimension(600, 400));
		DefaultTableCellRenderer columnModel = new DefaultTableCellRenderer();
		columnModel.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		table.getColumnModel().getColumn(1).setCellRenderer(columnModel);
		table.getColumnModel().getColumn(2).setCellRenderer(columnModel);
		table.getColumnModel().getColumn(3).setCellRenderer(columnModel);
		table.getColumnModel().getColumn(4).setCellRenderer(columnModel);
		tablePanel.add(table);
		frame.repaint();
		frame.show();
	}

	public static void main(String[] args) {
		new OutputReport();
	}

}
