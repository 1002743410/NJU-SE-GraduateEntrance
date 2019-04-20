import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
public class TestTable
{
	public static void main(String args[]){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame("JTable�Ĺ��˲���");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Object rows[][] ={{ "����", "�й�", 44 },
		{ "Ҧ��", "�й�", 25 },
		{ "������", "����", 1234 },
		{ "�ܲ�", "��κ", 2112 },
		{ "Bill Gates", "����", 45 },
		{ "Mike", "Ӣ��", 33 } };
		String columns[] ={ "����", "����", "����" };
		
		TableModel model = new DefaultTableModel(rows, columns){
			public Class getColumnClass(int column){//�ڲ��ࣿ���ԣ�����DefaultTalbeModel�ӿ����getColumnClass��������Class����
				Class returnValue;
				if ((column >= 0) && (column < getColumnCount())){
					
					returnValue = getValueAt(0, column).getClass();
					
				}else{
					returnValue = Object.class;
				}
				return returnValue;
			}
		};
		
		final JTable table = new JTable(model);
		
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		
		table.setRowSorter(sorter);
		
		JScrollPane pane = new JScrollPane(table);
		frame.add(pane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("����");
		panel.add(label, BorderLayout.WEST);
		final JTextField filterText = new JTextField("");
		
		panel.add(filterText, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.NORTH);
		
		JButton button = new JButton("����");
		
		button.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String text = filterText.getText();
			if (text.length() == 0){
				sorter.setRowFilter(null);
			}else{
				sorter.setRowFilter(RowFilter.regexFilter(text));
			}
		}
		});
		
		frame.add(button, BorderLayout.SOUTH);
		frame.setSize(300, 250);
		frame.setVisible(true);
	}
}