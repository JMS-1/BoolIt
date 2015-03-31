package net.psimarron.boolit;

public abstract class Calculator {
    public final int ImageResourceId;

    private final boolean[] m_inputs;

    protected Calculator(int numberOfInputs, int imageResource) {
        ImageResourceId = imageResource;

        m_inputs = new boolean[numberOfInputs];
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
}
