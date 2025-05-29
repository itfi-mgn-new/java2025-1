package lesson14;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.function.BiFunction;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


public class Calculator extends JFrame {
	private static final long serialVersionUID = 1L;

	private final CalculatorAutomat	automat;
	private final JTextField			value = new JTextField();
	
	public Calculator(final CalculatorAutomat automat) {
		super("Java Swing forever!");
		this.automat = automat;
		
		value.setFont(new Font("Courier",Font.BOLD|Font.ITALIC,36));	// ������� ������� �� "���������"
		value.setFocusable(false);
		value.setEditable(false);
		value.setForeground(Color.GREEN);
		value.setBackground(Color.BLACK);
		value.setHorizontalAlignment(JTextField.RIGHT);
		value.setText(String.valueOf(automat.getValue()));
		
		getContentPane().setLayout(new BorderLayout());					// ������� ������ ������
		
		final JPanel	buttons = new JPanel(new GridLayout(4,6,2,2));
		
		appendButton(buttons,"7",KeyboardButton.KB_7);
		appendButton(buttons,"8",KeyboardButton.KB_8);
		appendButton(buttons,"9",KeyboardButton.KB_9);
		buttons.add(new JLabel(""));
		appendButton(buttons,"+",KeyboardButton.KB_PLUS);
		appendButton(buttons,"C",KeyboardButton.KB_CLEAR);
		
		appendButton(buttons,"4",KeyboardButton.KB_4);
		appendButton(buttons,"5",KeyboardButton.KB_5);
		appendButton(buttons,"6",KeyboardButton.KB_6);
		buttons.add(new JLabel(""));
		appendButton(buttons,"-",KeyboardButton.KB_MINUS);
		appendButton(buttons,"<-",KeyboardButton.KB_BKSP);

		appendButton(buttons,"1",KeyboardButton.KB_1);
		appendButton(buttons,"2",KeyboardButton.KB_2);
		appendButton(buttons,"3",KeyboardButton.KB_3);
		buttons.add(new JLabel(""));
		appendButton(buttons,"*",KeyboardButton.KB_MUL);
		buttons.add(new JLabel(""));

		appendButton(buttons,"0",KeyboardButton.KB_0);
		appendButton(buttons,"/-/",KeyboardButton.KB_SIGN);
		appendButton(buttons,"=",KeyboardButton.KB_TOTAL);
		buttons.add(new JLabel(""));
		appendButton(buttons,"/",KeyboardButton.KB_DIV);
		buttons.add(new JLabel(""));
		
		getContentPane().add(value,BorderLayout.NORTH);			// ��������� ���� � ����������� ��� �� ������
		getContentPane().add(buttons,BorderLayout.CENTER);
		setSize(400,300);
		setLocationRelativeTo(null);
		
		// ��� ����� �������� ����� ��������� ������ ESC ��� ���������� ������ Swing-����������
		((JPanel)getContentPane()).getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0),"closeApplication");
		((JPanel)getContentPane()).getActionMap().put("closeApplication",new AbstractAction() {
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent e) {
				Calculator.this.setVisible(false);
				Calculator.this.dispose();
			}
		});
	}
	
	private void appendButton(final JPanel panel,final String caption, final KeyboardButton terminal) {
		final JButton	btn = new JButton(caption);
		
		btn.addActionListener((e)->{		// ���������� ������� ������ 
			automat.process(terminal);		// ��������� ������ ������������
			value.setText(String.valueOf(automat.getValue())); // ���������� ����������� "����������".
		});
		panel.add(btn);
	}

	public static void main(String[] args) {
		new Calculator(new CalculatorAutomat()).setVisible(true);
	}
}

enum TerminalGroup {					// ���� ������������ �������� ��������
	NUMBER, ARITHMETIC, SIGN, EDIT, TOTAL, RESET
}

enum KeyboardButton {					// �������� �������� - ���������, ��� � ����� �����, ����� ��������� ��������������� ������ � ��������
	KB_0(0,TerminalGroup.NUMBER),
	KB_1(1,TerminalGroup.NUMBER),
	KB_2(2,TerminalGroup.NUMBER),
	KB_3(3,TerminalGroup.NUMBER),
	KB_4(4,TerminalGroup.NUMBER),
	KB_5(5,TerminalGroup.NUMBER),
	KB_6(6,TerminalGroup.NUMBER),
	KB_7(7,TerminalGroup.NUMBER),
	KB_8(8,TerminalGroup.NUMBER),
	KB_9(9,TerminalGroup.NUMBER),
	KB_SIGN(TerminalGroup.SIGN),
	KB_PLUS((x,y)->x+y,TerminalGroup.ARITHMETIC),
	KB_MINUS((x,y)->x-y,TerminalGroup.ARITHMETIC),
	KB_MUL((x,y)->x*y,TerminalGroup.ARITHMETIC),
	KB_DIV((x,y)->x/y,TerminalGroup.ARITHMETIC),
	KB_TOTAL(TerminalGroup.TOTAL),
	KB_BKSP(TerminalGroup.EDIT),
	KB_CLEAR(TerminalGroup.RESET);
	
	private final int							value;
	private final BiFunction<Long,Long,Long>	action;
	private final TerminalGroup					group;

	KeyboardButton(TerminalGroup group) {
		this.action = null;
		this.value = 0;
		this.group = group;
	}
	
	KeyboardButton(int value, TerminalGroup group) {
		this.action = null;
		this.value = value;
		this.group = group;
	}

	KeyboardButton(BiFunction<Long,Long,Long> action, TerminalGroup group) {
		this.action = action;
		this.value = 0;
		this.group = group;
	}
	
	public int value() {
		return value;
	}
	
	public BiFunction<Long,Long,Long> function() {
		return action;
	}

	public TerminalGroup terminalGroup() {
		return group;
	}
	
	@Override
	public String toString() {
		return '[' + super.toString() + ", terminal group = " + terminalGroup() + ", value=" + value + ']';
	}
}

class CalculatorAutomat {
	public static final int	STATE_INITIAL = 0;
	public static final int	STATE_FIRST_OPERAND = 1;
	public static final int	STATE_BEFORE_SECOND_OPERAND = 2;
	public static final int	STATE_SECOND_OPERAND = 3;

	@FunctionalInterface
	private interface Action {
		void process(final CalculatorAutomat automat, final KeyboardButton btn);
	}
	
	private int		currentState = STATE_INITIAL;			// ������� ��������� ��������
	
	private long	currentValue = 0, previousValue = 0;
	private BiFunction<Long,Long,Long>	prevOperator = null;

	private class AutomatTable {	// �����, ����������� ���� ������ ���������� ������� 
		private final int 			state;
		private final TerminalGroup	terminal;
		private final int			newState;
		private final Action		action;
		
		public AutomatTable(int state, TerminalGroup terminal, int newState, Action action) {
			this.state = state;
			this.terminal = terminal;
			this.newState = newState;
			this.action = action;
		}

		@Override
		public String toString() {
			return "AutomatTable [state=" + state + ", terminal=" + terminal + ", newState=" + newState + ", action=" + action + "]";
		}
	}

	// ���� ��������� ��������:
	private final AutomatTable[]	AT = {	// ��������� ��������� ��������
											new AutomatTable(STATE_INITIAL,TerminalGroup.RESET,STATE_INITIAL,(automat,btn)->{
												currentValue = previousValue = 0;
											}),
											new AutomatTable(STATE_INITIAL,TerminalGroup.NUMBER,STATE_FIRST_OPERAND,(automat,btn)->{
												currentValue = btn.value();
											}),
											// ���� ������� ��������
											new AutomatTable(STATE_FIRST_OPERAND,TerminalGroup.NUMBER,STATE_FIRST_OPERAND,(automat,btn)->{
												currentValue = 10 * currentValue + btn.value();
											}),
											new AutomatTable(STATE_FIRST_OPERAND,TerminalGroup.EDIT,STATE_FIRST_OPERAND,(automat,btn)->{
												currentValue = trimValue(currentValue);
											}),
											new AutomatTable(STATE_FIRST_OPERAND,TerminalGroup.SIGN,STATE_FIRST_OPERAND,(automat,btn)->{
												currentValue = -currentValue;
											}),
											new AutomatTable(STATE_FIRST_OPERAND,TerminalGroup.RESET,STATE_INITIAL,(automat,btn)->{
												currentValue = previousValue = 0;
											}),
											new AutomatTable(STATE_FIRST_OPERAND,TerminalGroup.ARITHMETIC,STATE_BEFORE_SECOND_OPERAND,(automat,btn)->{
												prevOperator = btn.function();
											}),
											// ������ ����� ������� ��������
											new AutomatTable(STATE_BEFORE_SECOND_OPERAND,TerminalGroup.RESET,STATE_INITIAL,(automat,btn)->{
												currentValue = previousValue = 0;
											}),
											new AutomatTable(STATE_BEFORE_SECOND_OPERAND,TerminalGroup.NUMBER,STATE_SECOND_OPERAND,(automat,btn)->{
												previousValue = currentValue;
												currentValue = btn.value();
											}),
											// ���� ������� ��������
											new AutomatTable(STATE_SECOND_OPERAND,TerminalGroup.NUMBER,STATE_SECOND_OPERAND,(automat,btn)->{
												currentValue = 10 * currentValue + btn.value();
											}),
											new AutomatTable(STATE_SECOND_OPERAND,TerminalGroup.EDIT,STATE_SECOND_OPERAND,(automat,btn)->{
												currentValue = trimValue(currentValue);
											}),
											new AutomatTable(STATE_SECOND_OPERAND,TerminalGroup.SIGN,STATE_SECOND_OPERAND,(automat,btn)->{
												currentValue = -currentValue;
											}),
											new AutomatTable(STATE_SECOND_OPERAND,TerminalGroup.RESET,STATE_INITIAL,(automat,btn)->{
												currentValue = previousValue = 0;
											}),
											new AutomatTable(STATE_SECOND_OPERAND,TerminalGroup.ARITHMETIC,STATE_BEFORE_SECOND_OPERAND,(automat,btn)->{
												currentValue = prevOperator.apply(previousValue,currentValue);
												prevOperator = btn.function();
											}),
											new AutomatTable(STATE_SECOND_OPERAND,TerminalGroup.TOTAL,STATE_INITIAL,(automat,btn)->{
												currentValue = prevOperator.apply(previousValue,currentValue);
											}),
										}; 
	
	
	public CalculatorAutomat() {
	}

	public void process(final KeyboardButton btn) {	// ������������� ���������� �������
		for (AutomatTable item : AT) {
			if (item.state == currentState && btn.terminalGroup() == item.terminal) {
				currentState = item.newState;
				item.action.process(this,btn);
				return;		// ����� ����������!
			}
		}
		System.err.println("No action: current state="+currentState+", terminal="+btn);	// ����� ����� ����������� ���������� �������� � �����!
	}
	
	public long getValue() {
		return currentValue;
	}

	private long trimValue(final long currentValue) {
		final String	tmp = String.valueOf(currentValue);
		
		if (tmp.length() == 1 || tmp.length() == 2 && currentValue < 0) {
			return 0;
		}
		else {
			return Long.valueOf(tmp.substring(0,tmp.length()-1));
		}
	}
	
}