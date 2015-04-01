package net.psimarron.boolit;

public abstract class Calculator {
    private final static Class<?>[] _CalculatorClasses =
            {
                    AndCalculator.class,
                    OrCalculator.class,
                    NotAndCalculator.class,
                    NotOrCalculator.class,
                    XorCalculator.class,
                    NotXorCalculator.class,
                    NotCalculator.class,
            };
    private final boolean[] m_inputs;
    private int m_index;

    protected Calculator(int numberOfInputs) {
        m_inputs = new boolean[numberOfInputs];
    }

    public static int getCalculatorCount() {
        return _CalculatorClasses.length;
    }

    public static Calculator createCalculator(int index) {
        try {
            Calculator calculator = (Calculator) _CalculatorClasses[index].newInstance();

            calculator.m_index = index;

            return calculator;
        } catch (InstantiationException e) {
            throw new IndexOutOfBoundsException("createCalculator");
        } catch (IllegalAccessException e) {
            throw new IndexOutOfBoundsException("createCalculator");
        }
    }

    public int getIndex() {
        return m_index;
    }

    public int getCount() {
        return m_inputs.length;
    }

    public boolean getInput(int index) {
        return m_inputs[index];
    }

    public void setInput(int index, boolean state) {
        m_inputs[index] = state;
    }

    public abstract boolean getOutput();

    public abstract int getImageResourceId();

    public abstract int getShortNameId();
}
