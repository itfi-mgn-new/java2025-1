package lesson14;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.function.BiFunction;
import java.util.function.Function;

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
		
		value.setFont(new Font("Courier",Font.BOLD|Font.ITALIC,36));	// Наводим красоту на "индикатор"
		value.setFocusable(false);
		value.setEditable(false);
		value.setForeground(Color.GREEN);
		value.setBackground(Color.BLACK);
		value.setHorizontalAlignment(JTextField.RIGHT);
		value.setText(String.valueOf(automat.getValue()));
		
		getContentPane().setLayout(new BorderLayout());					// Готовим панель кнопок
		
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
		appendButton(buttons,"<==",KeyboardButton.KB_BKSP);

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
		
		getContentPane().add(value,BorderLayout.NORTH);			// Формируем окно и располагаем его на экране
		getContentPane().add(buttons,BorderLayout.CENTER);
		setSize(400,300);
		setLocationRelativeTo(null);
		
		// Вот таким способом можно назначить кавишу ESC для завершения работы Swing-приложения
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
		
		btn.addActionListener((e)->{		// Обработчик нажатия кнопок  
			automat.process(terminal);		// Обработка клавиш калькулятора
			value.setText(String.valueOf(automat.getValue())); // Обновление содержимого "индикатора".
		});
		panel.add(btn);
	}

	public static void main(String[] args) {
		new Calculator(new CalculatorAutomat()).setVisible(true);
	}
}

enum TerminalGroup {					// Типы терминальных символов автомата
	UNARY, BINARY, TOTAL, RESET
}

enum KeyboardButton {					// Обратите внимание - нумерации, как и любой класс, можно расширять дополнительными полями и методами
	KB_0((x)->10*x+0,TerminalGroup.UNARY),
	KB_1((x)->10*x+1,TerminalGroup.UNARY),
	KB_2((x)->10*x+2,TerminalGroup.UNARY),
	KB_3((x)->10*x+3,TerminalGroup.UNARY),
	KB_4((x)->10*x+4,TerminalGroup.UNARY),
	KB_5((x)->10*x+5,TerminalGroup.UNARY),
	KB_6((x)->10*x+6,TerminalGroup.UNARY),
	KB_7((x)->10*x+7,TerminalGroup.UNARY),
	KB_8((x)->10*x+8,TerminalGroup.UNARY),
	KB_9((x)->10*x+9,TerminalGroup.UNARY),
	KB_BKSP((x)->x/10,TerminalGroup.UNARY),
	KB_SIGN((x)->-x,TerminalGroup.UNARY),
	KB_PLUS((x,y)->x+y,TerminalGroup.BINARY),
	KB_MINUS((x,y)->x-y,TerminalGroup.BINARY),
	KB_MUL((x,y)->x*y,TerminalGroup.BINARY),
	KB_DIV((x,y)->x/y,TerminalGroup.BINARY),
	KB_TOTAL(TerminalGroup.TOTAL),
	KB_CLEAR(TerminalGroup.RESET);
	
	private final int							value;
	private final Function<Long,Long>			unaryAction;
	private final BiFunction<Long,Long,Long>	binaryAction;
	private final TerminalGroup					group;

	KeyboardButton(final TerminalGroup group) {
		this.unaryAction = null;
		this.binaryAction = null;
		this.value = 0;
		this.group = group;
	}
	
	KeyboardButton(final int value, final TerminalGroup group) {
		this.unaryAction = null;
		this.binaryAction = null;
		this.value = value;
		this.group = group;
	}

	KeyboardButton(final Function<Long,Long> action, final TerminalGroup group) {
		this.unaryAction = action;
		this.binaryAction = null;
		this.value = 0;
		this.group = group;
	}
	
	KeyboardButton(final BiFunction<Long,Long,Long> action, final TerminalGroup group) {
		this.unaryAction = null;
		this.binaryAction = action;
		this.value = 0;
		this.group = group;
	}
	
	public int value() {
		return value;
	}

	public Function<Long,Long> unaryAction() {
		return unaryAction;
	}
	
	public BiFunction<Long,Long,Long> binaryAction() {
		return binaryAction;
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
	private static enum AutomatState {
		STATE_INITIAL,
		STATE_FIRST_OPERAND,
		STATE_BEFORE_SECOND_OPERAND,
		STATE_SECOND_OPERAND
	}

	@FunctionalInterface
	private interface Action {
		void process(final CalculatorAutomat automat, final KeyboardButton btn);
	}
	
	private AutomatState	currentState = AutomatState.STATE_INITIAL;			// Текущее состояние автомата
	
	private long			currentValue = 0, previousValue = 0;
	private BiFunction<Long,Long,Long>	prevOperator = null;

	private class AutomatTable {	// Класс, описывающий одну строку автоматной таблицы  
		private final AutomatState	state;
		private final TerminalGroup	terminal;
		private final AutomatState	newState;
		private final Action		action;
		
		public AutomatTable(final AutomatState state, final TerminalGroup terminal, final AutomatState newState, final Action action) {
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

	// Граф переходов автомата:
	private final AutomatTable[]	AT = {	// Начальное состояние автомата
											new AutomatTable(AutomatState.STATE_INITIAL,TerminalGroup.RESET,AutomatState.STATE_INITIAL,(automat,btn)->{
												currentValue = previousValue = 0;
											}),
											new AutomatTable(AutomatState.STATE_INITIAL,TerminalGroup.UNARY,AutomatState.STATE_FIRST_OPERAND,(automat,btn)->{
												currentValue = btn.unaryAction().apply(0L);
											}),
											// Ввод первого операнда
											new AutomatTable(AutomatState.STATE_FIRST_OPERAND,TerminalGroup.UNARY,AutomatState.STATE_FIRST_OPERAND,(automat,btn)->{
												currentValue = btn.unaryAction().apply(currentValue);
											}),
											new AutomatTable(AutomatState.STATE_FIRST_OPERAND,TerminalGroup.RESET,AutomatState.STATE_INITIAL,(automat,btn)->{
												currentValue = previousValue = 0;
											}),
											new AutomatTable(AutomatState.STATE_FIRST_OPERAND,TerminalGroup.BINARY,AutomatState.STATE_BEFORE_SECOND_OPERAND,(automat,btn)->{
												prevOperator = btn.binaryAction();
											}),
											// Начало ввода второго операнда
											new AutomatTable(AutomatState.STATE_BEFORE_SECOND_OPERAND,TerminalGroup.RESET,AutomatState.STATE_INITIAL,(automat,btn)->{
												currentValue = previousValue = 0;
											}),
											new AutomatTable(AutomatState.STATE_BEFORE_SECOND_OPERAND,TerminalGroup.UNARY,AutomatState.STATE_SECOND_OPERAND,(automat,btn)->{
												previousValue = currentValue;
												currentValue = btn.unaryAction().apply(0L);
											}),
											// Ввод второго операнда
											new AutomatTable(AutomatState.STATE_SECOND_OPERAND,TerminalGroup.UNARY,AutomatState.STATE_SECOND_OPERAND,(automat,btn)->{
												currentValue = btn.unaryAction().apply(currentValue);
											}),
											new AutomatTable(AutomatState.STATE_SECOND_OPERAND,TerminalGroup.RESET,AutomatState.STATE_INITIAL,(automat,btn)->{
												currentValue = previousValue = 0;
											}),
											new AutomatTable(AutomatState.STATE_SECOND_OPERAND,TerminalGroup.BINARY,AutomatState.STATE_BEFORE_SECOND_OPERAND,(automat,btn)->{
												currentValue = prevOperator.apply(previousValue,currentValue);
												prevOperator = btn.binaryAction();
											}),
											new AutomatTable(AutomatState.STATE_SECOND_OPERAND,TerminalGroup.TOTAL,AutomatState.STATE_INITIAL,(automat,btn)->{
												currentValue = prevOperator.apply(previousValue,currentValue);
											}),
										}; 
	
	public void process(final KeyboardButton btn) {	// Интерпретатор автоматной таблицы
		for (AutomatTable item : AT) {
			if (item.state == currentState && btn.terminalGroup() == item.terminal) {
				currentState = item.newState;
				item.action.process(this,btn);
				return;		// Выход обязателен!
			}
		}
		// Очень легко отлавливать неучтенные переходы в графе!
		System.err.println("No action: current state="+currentState+", terminal="+btn);
	}
	
	public long getValue() {
		return currentValue;
	}
}